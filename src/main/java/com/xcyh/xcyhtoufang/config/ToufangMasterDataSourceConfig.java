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

@Configuration
@MapperScan(basePackages = ToufangMasterDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "toufangMasterSqlSessionFactory")
public class ToufangMasterDataSourceConfig {

    // 精确到 master 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.xcyh.xcyhtoufang.dao.master";
    static final String MAPPER_LOCATION = "classpath:mapper/toufang/master/*.xml";

    @Value("${datasource.toufangMaster.url}")
    private String dbUrl;
    @Value("${datasource.toufangMaster.username}")
    private String dbUsername;
    @Value("${datasource.toufangMaster.password}")
    private String dbPassword;
    @Value("${datasource.toufangMaster.initialSize}")
    private int dbInitialSize;
    @Value("${datasource.toufangMaster.maxActive}")
    private int dbMaxActive;
    @Value("${datasource.toufangMaster.minIdle}")
    private int dbMinIdle;

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Bean(name = "toufangMasterDataSource")
    public DataSource toufangMasterDataSource() {
        return dataSourceProperties.buildDruidDataSource(dataSourceProperties, dbUrl, dbUsername, dbPassword, dbInitialSize,
                dbMaxActive, dbMinIdle);
    }

    @Bean(name = "toufangMasterTransactionManager")
    public DataSourceTransactionManager toufangMasterTransactionManager() {
        return new DataSourceTransactionManager(toufangMasterDataSource());
    }

    @Bean(name = "toufangMasterSqlSessionFactory")
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("toufangMasterDataSource") DataSource masterDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(masterDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(ToufangMasterDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

}
