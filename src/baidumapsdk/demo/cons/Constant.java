package baidumapsdk.demo.cons;

public class Constant {

	public final static boolean ISENCRYPT = false;
	//用来存放局码和段码的当前值，如果是下面的默认值，则表示数据失败，需要取临时数据库的数据，否则需要重新登录
	public static String bureauCode = "bureauCode";
	public static String deptCode = "deptCode";

	public static final String sharePerferceStr = "sharePerferceStr";
	public static final String APK_CHECK = "http://115.29.172.156:8000/users/map";

	public static final String LeaderLogin = "http://115.29.172.156:8000/users/leader";
	//返回值不存在
	//网络不通
	public static final String postResult_noIp = "noIp";
	//登录失效，重新登录
	public static final String loginOut = "loginOut";
	//json请求中报错
	public static final String jsonObjectErr = "jsonObjectErr";
	//登录信息反馈
	public static final int Null_Username = 401;
	public static final int Null_Password = 402;
	public static final int Null_Phone = 403;
	public static final int Null_Duan = 404;
	public static final int loginSuccess = 100;
	public static final int loginFail = 500;
	
	public final static String DEPTSTR = "[{\"DeptName\":\"哈尔滨客运段\",\"DeptCode\":\"HBB\",\"BureauCode\":\"HEB\"}," +
			"{\"DeptName\":\"齐齐哈尔客运段\",\"DeptCode\":\"QHX\",\"BureauCode\":\"HEB\"}," +
	"{\"DeptName\":\"牡丹江客运段\",\"DeptCode\":\"MDB\",\"BureauCode\":\"HEB\"},{\"DeptName\":\"长春客运段\",\"DeptCode\":\"CCT\",\"BureauCode\":\"SY\"}," +
	"{\"DeptName\":\"沈阳客运段\",\"DeptCode\":\"SYT\",\"BureauCode\":\"SY\"},{\"DeptName\":\"大连客运段\",\"DeptCode\":\"DLT\",\"BureauCode\":\"SY\"}," +
	"{\"DeptName\":\"锦州客运段\",\"DeptCode\":\"JZD\",\"BureauCode\":\"SY\"},{\"DeptName\":\"吉林客运段\",\"DeptCode\":\"JLL\",\"BureauCode\":\"SY\"}," +
	"{\"DeptName\":\"北京客运段\",\"DeptCode\":\"BJP\",\"BureauCode\":\"BJ\"},{\"DeptName\":\"天津客运段\",\"DeptCode\":\"TJP\",\"BureauCode\":\"BJ\"}," +
	"{\"DeptName\":\"石家庄客运段\",\"DeptCode\":\"SJP\",\"BureauCode\":\"BJ\"},{\"DeptName\":\"太原客运段\",\"DeptCode\":\"TYV\",\"BureauCode\":\"BY\"}," +
	"{\"DeptName\":\"包头客运段\",\"DeptCode\":\"BTC\",\"BureauCode\":\"HHHT\"},{\"DeptName\":\"郑州客运段\",\"DeptCode\":\"ZZF\",\"BureauCode\":\"ZZ\"}," +
	"{\"DeptName\":\"武汉客运段\",\"DeptCode\":\"WHN\",\"BureauCode\":\"WH\"},{\"DeptName\":\"襄阳客运段\",\"DeptCode\":\"XFN\",\"BureauCode\":\"WH\"}," +
	"{\"DeptName\":\"西安客运段\",\"DeptCode\":\"XAY\",\"BureauCode\":\"XA\"},{\"DeptName\":\"济南客运段\",\"DeptCode\":\"JNK\",\"BureauCode\":\"JN\"}," +
	"{\"DeptName\":\"青岛客运段\",\"DeptCode\":\"QDK\",\"BureauCode\":\"JN\"},{\"DeptName\":\"合肥客运段\",\"DeptCode\":\"HFH\",\"BureauCode\":\"SH\"}," +
	"{\"DeptName\":\"南京客运段\",\"DeptCode\":\"NJH\",\"BureauCode\":\"SH\"},{\"DeptName\":\"上海客运段\",\"DeptCode\":\"SHH\",\"BureauCode\":\"SH\"}," +
	"{\"DeptName\":\"杭州客运段\",\"DeptCode\":\"HZH\",\"BureauCode\":\"SH\"},{\"DeptName\":\"南昌客运段\",\"DeptCode\":\"NCG\",\"BureauCode\":\"NC\"}," +
	"{\"DeptName\":\"福州客运段\",\"DeptCode\":\"FZS\",\"BureauCode\":\"NC\"},{\"DeptName\":\"广州客运段\",\"DeptCode\":\"GZQ\",\"BureauCode\":\"GZ\"}," +
	"{\"DeptName\":\"长沙客运段\",\"DeptCode\":\"CSQ\",\"BureauCode\":\"GZ\"},{\"DeptName\":\"广九客运段\",\"DeptCode\":\"SZQ\",\"BureauCode\":\"GZ\"}," +
	"{\"DeptName\":\"肇庆客运段\",\"DeptCode\":\"ZVQ\",\"BureauCode\":\"GZ\"},{\"DeptName\":\"东莞东客运段\",\"DeptCode\":\"DMQ\",\"BureauCode\":\"GZ\"}," +
	"{\"DeptName\":\"南宁客运段\",\"DeptCode\":\"NNZ\",\"BureauCode\":\"NN\"},{\"DeptName\":\"成都客运段\",\"DeptCode\":\"CDW\",\"BureauCode\":\"CD\"}," +
	"{\"DeptName\":\"重庆客运段\",\"DeptCode\":\"CQW\",\"BureauCode\":\"CD\"},{\"DeptName\":\"贵阳客运段\",\"DeptCode\":\"GIW\",\"BureauCode\":\"CD\"}," +
	"{\"DeptName\":\"昆明客运段\",\"DeptCode\":\"KMM\",\"BureauCode\":\"KM\"},{\"DeptName\":\"兰州客运段\",\"DeptCode\":\"LZJ\",\"BureauCode\":\"LZ\"}," +
	"{\"DeptName\":\"银川客运段\",\"DeptCode\":\"YIJ\",\"BureauCode\":\"LZ\"},{\"DeptName\":\"乌鲁木齐客运段\",\"DeptCode\":\"WMR\",\"BureauCode\":\"WLMQ\"}," +
	"{\"DeptName\":\"西宁客运段\",\"DeptCode\":\"XNO\",\"BureauCode\":\"QZ\"}]";
}
