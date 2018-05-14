package yuan.web.session;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CheckPermission {
	/**
	 * 权限代码数组，
	 * 未提供权限代码时表示可以只需要登录即可
	 * 提供多个权限代码时，只需要具有其中任何一个就允许通过
	 * @return
	 */
	String[] value() default {};
}