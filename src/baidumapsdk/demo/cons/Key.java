package baidumapsdk.demo.cons;

public class Key {

	//领导登录请求flag_login
	public static final String flag = "flag";
	public static final String flag_login = "flag_login";
	public static final String my_device = "my_device";//设备号
	public static final String DeptCode = "DeptCode";//所属客运段段码
	public static final String user_id = "user_id";//当前用户ID
	public static final String password = "password";//密码
	//领导登录响应
	public static final String result = "result";//0000：成功 其他：失败
	public static final String error = "error";//错误事由
	public static final String resultOk = "0000";

	//向服务端发送领导查看地图请求
	public static final String bureau = "bureau";//局码
	public static final String dept = "dept";//段码
	public static final String train = "train";//车次
	public static final String wds = "wds";//晚点时间区段开始
	public static final String wde = "wde";//晚点时间区段结束
	public static final String md = "md";//提取数据的时间单位
	public static final String mdi = "mdi";//提取数据的时间长度
	//返回的地图数据
	public static final String zttime = "zttime";//最近定位时间
	public static final String ztcheci = "ztcheci";//车次
	public static final String zttel = "zttel";//电话
	public static final String lng = "lng";//经度
	public static final String lat = "lat";//纬度
	public static final String pos00 = "pos00";//车长信息（当班车长）
	public static final String pos1 = "pos1";//晚点情况
	public static final String TrainDirection = "TrainDirection";//列车运行方向
	public static final String wdminu = "wdminu";//晚点时间（显示用的，文本型，自动转换成小时+分钟数）
	public static final String shifa = "shifa";//车次始发时间
	public static final String BianZu = "BianZu";//编组，包括编组，按车型分的数量
	public static final String XiaZhan = "XiaZhan";//目前运行区间，以及下站距离
	public static final String peoplecount = "peoplecount";//全列定员+实时车内人数
	public static final String BureauName = "BureauName";//局名
	public static final String DeptName = "DeptName";//段名
	public static final String TeamName = "TeamName";//车队名
	public static final String banzu = "banzu";//班组名
	public static final String biaoshi = "biaoshi";//标识
	
	//还有其他，需要的话再继续添加

}
