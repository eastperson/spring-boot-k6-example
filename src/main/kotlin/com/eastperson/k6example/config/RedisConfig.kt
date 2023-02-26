package com.eastperson.k6example.config

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.springframework.context.annotation.Configuration
import redis.embedded.RedisServer

@Configuration
class RedisConfig {

    private lateinit var redisServer: RedisServer

    @PostConstruct
    fun redisServer() {
        this.redisServer = RedisServer(6379)
        this.redisServer.start()
    }

    @PreDestroy
    fun stopRedis() {
        if (redisServer != null) {
            redisServer.stop()
        }
    }
}