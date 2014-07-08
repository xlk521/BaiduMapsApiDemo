package baidumapsdk.demo.cons;

public class Constant {

	public final static boolean ISENCRYPT = false;
	//用来存放局码和段码的当前值，如果是下面的默认值，则表示数据失败，需要取临时数据库的数据，否则需要重新登录
	public static String bureauCode = "bureauCode";
	public static String deptCode = "deptCode";
	
	public static final String APK_CHECK = "http://115.29.172.156:8000/users/map";
	//返回值不存在
	//网络不通
	public static final String postResult_noIp = "noIp";
	//登录失效，重新登录
	public static final String loginOut = "loginOut";
	//json请求中报错
	public static final String jsonObjectErr = "jsonObjectErr";
}
