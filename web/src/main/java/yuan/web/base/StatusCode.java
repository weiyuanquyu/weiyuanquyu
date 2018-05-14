package yuan.web.base;

import java.util.HashMap;
import java.util.Map;

public class StatusCode {

	public final static String SUCCESS = "1";

	//json消息错误 001
	public final static String JSON_FAIL_MSG_FORMAT = "00101";   // 消息JSON格式错误
	
	public final static String JSON_FAIL_MSG_PROTOCOL = "00102"; // 消息协议错误为空
	
	public final static String JSON_FAIL_MSG_IMEI = "00103";     // 手机未授权或唯一码为空
	
	
	//消息处理错误 002
	public final static String DEAL_FAIL_MSG_TIME = "00201"; 	 // 时间异常，与服务器差距太大
	
	public final static String DEAL_FAIL_MSG_NO_LOGINID = "00202";// 没有登录账号或密码 
	
	public final static String DEAL_FAIL_MSG_PASS_ERR = "00203";  // 密码错误
	
	public final static String DEAL_FAIL_MSG_PASS_INIT = "00204"; // 初始密码不允许
	
	public final static String DEAL_FAIL_MSG_PASS_NOUSE = "00205";// 密码过于简单
	
	public final static String DEAL_FAIL_MSG_SERVICE = "00209";  // 消息处理错误
	
	
	//业务系统错误101
	public final static String BUSS_NO_LOGIN = "10101";//未登录
	
	public final static String BUSS_NO_PERMISSION = "10102";//没有相应权限
	
	public final static String BUSS_NOT_DELETE_ADMIN = "10103";//无法删除管理员
	
	public final static String BUSS_EXISTS_DATA = "10104";//添加的内容已经存在
	
	public final static String BUSS_PASS_NOT_SAME = "10105";//两次输入的密码不同
	
	public final static String BUSS_USER_NOT_EXISTS = "10106";//没有对应员工
	
	public final static String BUSS_USER_DISABLED = "10107";//员工被禁用
	
	//表单字段处理错误
	public final static String FIELD_NOT_NULL = "20101";//字段不能为空
	
	
	public final static String FILE_FORMAT_ERR = "20201";//文件类型不对
	public final static String FILE_UNZIP_ERR = "20202";//文件解压错误	
	
	//数据库异常
	public final static String DB_INSERT_ERR = "30101";//插入错误
	public final static String DB_UPDATE_ERR = "30102";//更新错误
	
	
	
	//抄表内容状态
	public final static String CB_BCXX_NO_ONE = "50101";//没有表册信息
	
	
	public final static String UNKNOW = "99999"; // 未知异常码
	
	
	private static Map<String, String> codes = new HashMap<String, String>();
	
	public static String getErrorMessage(String errorcode){
		if (errorcode == null) return null;
		
		if (codes.containsKey(errorcode))
			return codes.get(errorcode);
		else
			return errorcode;
	}
	
	static{
		codes.put("1", "成功");
		codes.put(JSON_FAIL_MSG_FORMAT, "消息JSON格式错误");
		codes.put(JSON_FAIL_MSG_PROTOCOL, "消息协议错误");
		codes.put(JSON_FAIL_MSG_IMEI, "手机未授权或为空");
		codes.put(DEAL_FAIL_MSG_TIME, "时间异常，与服务器差距太大");
		codes.put(DEAL_FAIL_MSG_SERVICE, "消息处理错误");
		codes.put(DEAL_FAIL_MSG_NO_LOGINID, "没有登录账号或密码错误");
		codes.put(DEAL_FAIL_MSG_PASS_INIT, "初始化密码请修改");
		codes.put(DEAL_FAIL_MSG_PASS_ERR, "密码错误");
		codes.put(DEAL_FAIL_MSG_PASS_NOUSE, "密码强度不符合");
		
		codes.put(BUSS_NO_LOGIN, "未登陆,请登陆");
		codes.put(BUSS_NO_PERMISSION, "没有相关权限访问");
		codes.put(BUSS_NOT_DELETE_ADMIN, "无法删除管理员");
		codes.put(BUSS_EXISTS_DATA, "已经存在添加的内容");
		codes.put(BUSS_PASS_NOT_SAME, "两次输入的密码不同");
		codes.put(BUSS_USER_NOT_EXISTS, "员工不存在");
		
		codes.put(FIELD_NOT_NULL, "字段不能为空");
		codes.put(FILE_FORMAT_ERR, "文件类型不对");
		codes.put(FILE_UNZIP_ERR, "解压错误");
		
		
		codes.put(DB_INSERT_ERR, "数据库插入异常");
		codes.put(DB_UPDATE_ERR, "数据库更新异常");
		
		codes.put(CB_BCXX_NO_ONE, "无可抄写表册");
		
		
		codes.put(UNKNOW, "系统未处理异常，请联系管理员");
	}
}
