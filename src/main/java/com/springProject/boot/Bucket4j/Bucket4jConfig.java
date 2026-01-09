package com.springProject.boot.Bucket4j;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class Bucket4jConfig {

    private final RateLimitProperties props;

    public Bucket4jConfig(RateLimitProperties props) {
        this.props = props;
    }

    @Bean
    public Bucket createAccountGlobalBucket() {
        return Bucket.builder()
                .addLimit(Bandwidth.classic(
                        props.getAccount().getCreateLimit(),
                        Refill.intervally(
                                props.getAccount().getCreateLimit(),
                                Duration.ofSeconds(props.getAccount().getDurationSeconds())
                        )
                ))
                .build();
    }

    @Bean
    public Bucket createAccountHolderGlobalBucket() {
        return Bucket.builder()
                .addLimit(Bandwidth.classic(
                        props.getAccountHolder().getCreateLimit(),
                        Refill.intervally(
                                props.getAccountHolder().getCreateLimit(),
                                Duration.ofSeconds(props.getAccountHolder().getDurationSeconds())
                        )
                ))
                .build();
    }

    @Bean
    public Bucket createCardGlobalBucket() {
        return Bucket.builder()
                .addLimit(Bandwidth.classic(
                        props.getCard().getCreateLimit(),
                        Refill.intervally(
                                props.getCard().getCreateLimit(),
                                Duration.ofSeconds(props.getCard().getDurationSeconds())
                        )
                ))
                .build();
    }

    @Bean
    public Bucket linkAccountCardGlobalBucket() {
        return Bucket.builder()
                .addLimit(Bandwidth.classic(
                        props.getAccountCard().getLinkLimit(),
                        Refill.intervally(
                                props.getAccountCard().getLinkLimit(),
                                Duration.ofSeconds(props.getAccountCard().getDurationSeconds())
                        )
                ))
                .build();
    }

    @Bean
    public Bucket createTransactionGlobalBucket() {
        return Bucket.builder()
                .addLimit(Bandwidth.classic(
                        props.getTransaction().getCreateLimit(),
                        Refill.intervally(
                                props.getTransaction().getCreateLimit(),
                                Duration.ofSeconds(props.getTransaction().getDurationSeconds())
                        )
                ))
                .build();
    }
}
