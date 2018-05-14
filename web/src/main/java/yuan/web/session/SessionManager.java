package yuan.web.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import yuan.web.base.StatusCode;
import yuan.web.exception.PFTException;


public class SessionManager {

	public static final String SESSION_KEY = "session.key";


	/**
	 * 获取request
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		return requestAttributes.getRequest();
	}

	/**
	 * 获取session
	 * @return
	 */
	public static HttpSession getSession() {
		return getRequest().getSession();
	}

	/**
	 * 获取session
	 * @return
	 */
	public static SessionUser get() {
		return (SessionUser) getRequest().getSession().getAttribute(SESSION_KEY);
	}

	/**
	 * 将登录用户的bean保存到session中
	 * @param bean
	 */
	public static void login(SessionUser bean){
		Assert.notNull(bean,"当前用户为空");
		getSession().setAttribute(SESSION_KEY, bean);
	}

	/**
	 * 检查是否已经登录，未登录时抛出
	 */
	public void checkLogin() {

		if (getCurrentUser()==null){
			throw PFTException.build(StatusCode.BUSS_NO_LOGIN);
		}
	}

	private Object getCurrentUser() {
		return getSession().getAttribute(SESSION_KEY);
	}


	public static void logout() {
		getSession().removeAttribute(SESSION_KEY);
	}



}
