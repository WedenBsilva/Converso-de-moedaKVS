package com.mercadolibre.wedenconverso.config;

import com.fury.api.FuryUtils;
import com.fury.api.exceptions.FuryDecryptException;
import com.fury.api.exceptions.FuryNotFoundAPPException;
import com.fury.api.exceptions.FuryUpdateException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    private static final String DATA_SOURCE_URL = "jdbc:mysql://%s/%s?serverTimezone=UTC";

    private static final String DATA_SOURCE_KEY = "datasource";


    @Bean
    @Qualifier(DATA_SOURCE_KEY)
    @Profile({"!integration_test && !local"})
    public DataSource getDataSource(
            final @Value("${datasource.driver-class-name}") String driver,
            final @Value("${datasource.jdbc-url}") String jdbcUrl,
            final @Value("${datasource.db}") String db,
            final @Value("${datasource.user}") String user,
            final @Value("${datasource.password}") String password,
            final @Value("${datasource.max-pool-size}") Integer poolsize)
            throws FuryDecryptException, FuryUpdateException, FuryNotFoundAPPException {
        return buildDataSource(driver, FuryUtils.getEnv(jdbcUrl), db, user, FuryUtils.getEnv(password), poolsize);
    }

    @Bean
    @Qualifier(DATA_SOURCE_KEY)
    @Profile("local")
    public DataSource getDataSourceLocal(
            final @Value("${datasource.driver-class-name}") String driver,
            final @Value("${datasource.jdbc-url}") String jdbcUrl,
            final @Value("${datasource.db}") String db,
            final @Value("${datasource.user}") String user,
            final @Value("${datasource.password}") String password,
            final @Value("${datasource.max-pool-size}") Integer poolsize) {
        return buildDataSource(driver, jdbcUrl, db, user, password, poolsize);
    }

    private DataSource buildDataSource(
            String driver, String jdbcUrl, String db, String user, String password, Integer poolsize) {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driver);
        config.setJdbcUrl(String.format(DATA_SOURCE_URL, jdbcUrl, db));
        config.setUsername(user);
        config.setPassword(password);
        config.setMaximumPoolSize(poolsize);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        return new HikariDataSource(config);
    }
}
