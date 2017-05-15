package com.efan.jpaconfig;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author 程序猿DD
 * @version 1.0.0
 * @date 16/3/26 下午9:11.
 * @blog http://blog.didispace.com
 */
@Configuration
public class DataSourceConfig {
    @Bean(name = "primaryDataSource")
    @Qualifier("primaryDataSource")
    @ConfigurationProperties(prefix="spring.primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }


}
