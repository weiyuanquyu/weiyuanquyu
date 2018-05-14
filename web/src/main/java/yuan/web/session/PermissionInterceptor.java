package yuan.web.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class PermissionInterceptor extends HandlerInterceptorAdapter {


    private SessionManager sessionManager;

    @Autowired
    public PermissionInterceptor(SessionManager sessionManager){
        this.sessionManager = sessionManager;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof HandlerMethod){
            HandlerMethod hm = (HandlerMethod)handler;

            handleClassCheckLogin(hm);
            handleMethodCheckLogin(hm);

        }

        return super.preHandle(request, response, handler);
    }

    private void handleClassCheckLogin(HandlerMethod hm){

        if ( AnnotationUtils.findAnnotation(hm.getBeanType(), CheckLogin.class) != null){

            sessionManager.checkLogin();
        }

    }

    private void handleMethodCheckLogin(HandlerMethod hm){

        if ( hm.getMethodAnnotation(CheckLogin.class) != null){
            sessionManager.checkLogin();
        }

    }

}
