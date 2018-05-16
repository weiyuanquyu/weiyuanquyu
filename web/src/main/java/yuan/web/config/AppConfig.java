package yuan.web.config;

import com.alibaba.druid.pool.DruidDataSource;

import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.nutz.dao.impl.NutDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import yuan.web.core.SpringDaoRunner;
import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class AppConfig {

    @Bean
    @ConfigurationProperties(prefix = "jdbc")
    public JdbcBean jdbcBean(){
        JdbcBean jdbcBean=new JdbcBean();
        return jdbcBean;
    }


    @Bean
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver multipartResolver =new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(10485760);//10M
        return multipartResolver;
    }

    @Bean
    public DataSource dataSource() throws SQLException {
        JdbcBean jdbcBean=jdbcBean();
        DruidDataSource dataSource=new DruidDataSource();
        dataSource.setDriverClassName(jdbcBean.getDriver());
        dataSource.setUrl(jdbcBean.getUrl());
        dataSource.setUsername(jdbcBean.getUsername());
        dataSource.setPassword(jdbcBean.getPassword());

        dataSource.setMaxActive(jdbcBean.getMaxActive());
        dataSource.setMinIdle(jdbcBean.getMinIdle());
        dataSource.setInitialSize(jdbcBean.getInitialSize());

        dataSource.setMaxWait(60000);
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        dataSource.setMinEvictableIdleTimeMillis(30000);

        dataSource.setValidationQuery("select 'x' ");
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        dataSource.setRemoveAbandoned(true);
        dataSource.setRemoveAbandonedTimeout(1800);

        dataSource.setRemoveAbandoned(true);
        dataSource.setFilters("stat");

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        JdbcTemplate jdbcTemplate=new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }

    @Bean
    public DataSourceTransactionManager txManager(DataSource dataSource){
        DataSourceTransactionManager txManager=new DataSourceTransactionManager(dataSource);
        return txManager;
    }

    @Bean
    public SpringDaoRunner springDaoRunner(){
        return new SpringDaoRunner();
    }

    @Bean
    public NutDao nutDao(DataSource dataSource,SpringDaoRunner springDaoRunner){
        NutDao dao=new NutDao();
        dao.setDataSource(dataSource);
        dao.setRunner(springDaoRunner);
        return dao;
    }

    @Bean(name = "beetlConfig",initMethod = "init")
    public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration(){
        BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
        try {
            ClasspathResourceLoader cploder = new ClasspathResourceLoader(AppConfig.class.getClassLoader(),"templates");
            beetlGroupUtilConfiguration.setResourceLoader(cploder);
            return beetlGroupUtilConfiguration;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Bean(name="beetlViewResolver")
    public BeetlSpringViewResolver getBeetlSpringViewResolver(@Qualifier("beetlConfig") BeetlGroupUtilConfiguration beetlGroupUtilConfiguration){
        BeetlSpringViewResolver viewResolver=new BeetlSpringViewResolver();
        viewResolver.setContentType("text/html;charset=UTF-8");
        viewResolver.setOrder(0);
        viewResolver.setConfig(beetlGroupUtilConfiguration);
        return viewResolver;
    }
}
