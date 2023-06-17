package com.xcyh.xcyhtoufang.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

/**
 * 数据源属性
 * Created by q on 2019/12/26.
 */
@Configuration
public class DataSourceProperties {

    @Value("${druid.driver.class.name}")
    private String driverClassName;

    @Value("${druid.max.wait}")
    private long maxWait = 60 * 1000L;

    @Value("${druid.filters}")
    private String filters = "stat";

    @Value("${druid.test.while.idle}")
    private boolean testWhileIdle = true;

    @Value("${druid.time.between.eviction.runs.millis}")
    private long timeBetweenEvictionRunsMillis = 60 * 1000L;

    @Value("${druid.validation.query}")
    private String validationQuery = "select 1";

    @Value("${druid.test.on.borrow}")
    private boolean testOnBorrow = false;

    @Value("${druid.test.on.return}")
    private boolean testOnReturn = false;

    @Value("${druid.max.open.prepared.statements}")
    private int maxOpenPreparedStatements = -1;

    public DruidDataSource buildDruidDataSource(DataSourceProperties properties, String url, String username, String password,
                                                int initialSize, int maxActive, int minIdle) {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(properties.getDriverClassName());
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);

        druidDataSource.setInitialSize(initialSize);
        druidDataSource.setMaxActive(maxActive);
        druidDataSource.setMinIdle(minIdle);
        druidDataSource.setMaxWait(properties.getMaxWait());
        druidDataSource.setTestWhileIdle(properties.isTestWhileIdle());
        druidDataSource.setTimeBetweenEvictionRunsMillis(properties.getTimeBetweenEvictionRunsMillis());
        druidDataSource.setValidationQuery(properties.getValidationQuery());
        druidDataSource.setTestOnBorrow(properties.isTestOnBorrow());
        druidDataSource.setTestOnReturn(properties.isTestOnReturn());
        druidDataSource.setMaxOpenPreparedStatements(properties.getMaxOpenPreparedStatements());

        try {
            druidDataSource.setFilters(properties.getFilters());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return druidDataSource;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public long getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(long maxWait) {
        this.maxWait = maxWait;
    }

    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }

    public boolean isTestWhileIdle() {
        return testWhileIdle;
    }

    public void setTestWhileIdle(boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public long getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(long timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public String getValidationQuery() {
        return validationQuery;
    }

    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public boolean isTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public int getMaxOpenPreparedStatements() {
        return maxOpenPreparedStatements;
    }

    public void setMaxOpenPreparedStatements(int maxOpenPreparedStatements) {
        this.maxOpenPreparedStatements = maxOpenPreparedStatements;
    }
}
