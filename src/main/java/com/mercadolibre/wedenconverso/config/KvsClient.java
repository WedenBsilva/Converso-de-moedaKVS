package com.mercadolibre.wedenconverso.config;
import com.mercadolibre.kvsclient.ContainerKvsLowLevelClient;
import com.mercadolibre.kvsclient.kvsapi.KvsapiConfiguration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KvsClient {

        @Value("${kvs.config.socket-timeout-millis}")
        private int socketTimeout;

        @Value("${kvs.config.max-wait}")
        private int maxWait;

        @Value("${kvs.config.connection-timeout-millis}")
        private int conectTimeout;

        @Value("${kvs.config.max-connections}")
        private int maxConections;

        @Value("${kvs.config.max-connections-per-route}")
        private int maxConnectionsPerRoute;

        @Value("${kvs.config.max-retries}")
        private int maxRetries;

        @Value("${kvs.config.retry-delay}")
        private int retryDelay;

        @Value("${kvs.container-name}")
        private String containerName;

        @Bean
        public ContainerKvsLowLevelClient kvs(){
            com.mercadolibre.kvsclient.kvsapi.KvsapiConfiguration configuration = com.mercadolibre.kvsclient.kvsapi.KvsapiConfiguration.builder()
                    .withSocketTimeout(socketTimeout)
                    .withMaxWait(maxWait)
                    .withConnectionTimeout(conectTimeout)
                    .withMaxConnections(maxConections)
                    .withMaxConnectionsPerRoute(maxConnectionsPerRoute)
                    .withMaxRetries(maxRetries)
                    .withRetryDelay(retryDelay)
                    .build();

            return new ContainerKvsLowLevelClient(configuration, System.getenv(containerName));
        }


    }

