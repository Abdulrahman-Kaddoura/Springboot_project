package com.springProject.boot.Bucket4j;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "ratelimit")
@EnableConfigurationProperties
public class RateLimitProperties {

    private Endpoint account = new Endpoint();
    private Endpoint accountHolder = new Endpoint();
    private Endpoint card = new Endpoint();
    private Endpoint accountCard = new Endpoint();
    private Endpoint transaction = new Endpoint();

    @Getter
    @Setter
    public static class Endpoint {
        // global limits
        private int createLimit;
        private int linkLimit;
        private int durationSeconds;

        // per-IP limits
        private int createLimitPerIp;
        private int linkLimitPerIp;
        private int durationSecondsPerIp;
    }
}
