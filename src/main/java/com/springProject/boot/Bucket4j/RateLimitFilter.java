package com.springProject.boot.Bucket4j;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitFilter extends OncePerRequestFilter {

    // Global buckets injected from Bucket4jConfig
    private final Bucket createAccountGlobal;
    private final Bucket createAccountHolderGlobal;
    private final Bucket createCardGlobal;
    private final Bucket linkAccountCardGlobal;
    private final Bucket createTransactionGlobal;

    // Per-IP buckets
    private final Map<String, Bucket> createAccountIpBuckets = new ConcurrentHashMap<>();
    private final Map<String, Bucket> createAccountHolderIpBuckets = new ConcurrentHashMap<>();
    private final Map<String, Bucket> createCardIpBuckets = new ConcurrentHashMap<>();
    private final Map<String, Bucket> linkAccountCardIpBuckets = new ConcurrentHashMap<>();
    private final Map<String, Bucket> createTransactionIpBuckets = new ConcurrentHashMap<>();

    private final RateLimitProperties props;

    public RateLimitFilter(
            @Qualifier("createAccountGlobalBucket") Bucket createAccountGlobal,
            @Qualifier("createAccountHolderGlobalBucket") Bucket createAccountHolderGlobal,
            @Qualifier("createCardGlobalBucket") Bucket createCardGlobal,
            @Qualifier("linkAccountCardGlobalBucket") Bucket linkAccountCardGlobal,
            @Qualifier("createTransactionGlobalBucket") Bucket createTransactionGlobal,
            RateLimitProperties props) {
        this.createAccountGlobal = createAccountGlobal;
        this.createAccountHolderGlobal = createAccountHolderGlobal;
        this.createCardGlobal = createCardGlobal;
        this.linkAccountCardGlobal = linkAccountCardGlobal;
        this.createTransactionGlobal = createTransactionGlobal;
        this.props = props;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();
        String ip = request.getRemoteAddr();

        // Check each endpoint
        if (path.equals("/account/create-account")) {
            if (!consume(createAccountGlobal, ip, createAccountIpBuckets,
                    props.getAccount().getCreateLimitPerIp(),
                    props.getAccount().getDurationSecondsPerIp())) {
                response.setStatus(429);
                response.getWriter().write("Too many requests for create-account");
                return;
            }
        } else if (path.equals("/account-holder/create-account-holder")) {
            if (!consume(createAccountHolderGlobal, ip, createAccountHolderIpBuckets,
                    props.getAccountHolder().getCreateLimitPerIp(),
                    props.getAccountHolder().getDurationSecondsPerIp())) {
                response.setStatus(429);
                response.getWriter().write("Too many requests for create-account-holder");
                return;
            }
        } else if (path.equals("/card/create-card")) {
            if (!consume(createCardGlobal, ip, createCardIpBuckets,
                    props.getCard().getCreateLimitPerIp(),
                    props.getCard().getDurationSecondsPerIp())) {
                response.setStatus(429);
                response.getWriter().write("Too many requests for create-card");
                return;
            }
        } else if (path.startsWith("/account-card/link-account-card")) {
            if (!consume(linkAccountCardGlobal, ip, linkAccountCardIpBuckets,
                    props.getAccountCard().getLinkLimitPerIp(),
                    props.getAccountCard().getDurationSecondsPerIp())) {
                response.setStatus(429);
                response.getWriter().write("Too many requests for link-account-card");
                return;
            }
        } else if (path.equals("/transaction-controller/create-transaction")) {
            if (!consume(createTransactionGlobal, ip, createTransactionIpBuckets,
                    props.getTransaction().getCreateLimitPerIp(),
                    props.getTransaction().getDurationSecondsPerIp())) {
                response.setStatus(429);
                response.getWriter().write("Too many requests for create-transaction");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    // -------------------------
    // Utility method to check both global + per-IP buckets
    // -------------------------
    private boolean consume(Bucket globalBucket, String ip, Map<String, Bucket> ipBuckets, int limitPerIp, int durationSecondsPerIp) {
        // Check global bucket first
        if (!globalBucket.tryConsume(1)) {
            return false;
        }

        // Get or create per-IP bucket
        Bucket ipBucket = ipBuckets.computeIfAbsent(ip, k -> Bucket.builder()
                .addLimit(Bandwidth.classic(limitPerIp, Refill.intervally(limitPerIp, Duration.ofSeconds(durationSecondsPerIp))))
                .build());

        // Check per-IP bucket
        return ipBucket.tryConsume(1);
    }
}
