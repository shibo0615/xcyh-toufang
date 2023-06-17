package com.xcyh.xcyhtoufang.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * tadu slave data source
 * Created by q on 2019/12/25.
 */
@Configuration
@MapperScan(basePackages = ToufangSlaveDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "toufangSlaveSqlSessionFactory")
public class ToufangSlaveDataSourceConfig {

    // 精确到tadu slave 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.xcyh.xcyhtoufang.dao.slave";
    static final String MAPPER_LOCATION = "classpath:mapper/toufang/slave/*.xml";

    @Value("${datasource.toufangSlave.url}")
    private String dbUrl;
    @Value("${datasource.toufangSlave.username}")
    private String dbUsername;
    @Value("${datasource.toufangSlave.password}")
    private String dbPassword;
    @Value("${datasource.toufangSlave.initialSize}")
    private int dbInitialSize;
    @Value("${datasource.toufangSlave.maxActive}")
    private int dbMaxActive;
    @Value("${datasource.toufangSlave.minIdle}")
    private int dbMinIdle;

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Bean(name = "toufangSlaveDataSource")
    public DataSource toufangSlaveDataSource() {
        return dataSourceProperties.buildDruidDataSource(dataSourceProperties, dbUrl, dbUsername, dbPassword, dbInitialSize,
                dbMaxActive, dbMinIdle);
    }

    @Bean(name = "toufangSlaveTransactionManager")
    public DataSourceTransactionManager toufangSlaveTransactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(toufangSlaveDataSource());
        return dataSourceTransactionManager;
    }

    @Bean(name = "toufangSlaveSqlSessionFactory")
    public SqlSessionFactory toufangSlaveSqlSessionFactory(@Qualifier("toufangSlaveDataSource") DataSource taduSlaveDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(taduSlaveDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(ToufangSlaveDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

}
