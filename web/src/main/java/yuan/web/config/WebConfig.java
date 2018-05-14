package yuan.web.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import yuan.web.session.PermissionInterceptor;
import yuan.web.session.SessionManager;

/**
 * web拦截器，检查是否登陆
 */



@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {


    @Bean
    public SessionManager getSessionManager(){
       return new SessionManager();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PermissionInterceptor(getSessionManager())).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
