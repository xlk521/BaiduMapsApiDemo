package baidumapsdk.demo.cons;

import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class GetJsonFromHttp {

	private String TAG = "GetJsonFromHttp";
	Context context = null;
	public GetJsonFromHttp(Context c){
		this.context = c;
	}
	/*DerekXie 20140707
	 * 地图请求数据
	 * */
	public String getJsonByHttpForMap(final String train, final String wds, final String wde, final String md, final String mdi) {
		if (Tools.isMobileConnected(context) || Tools.isWIFIConnected(context)) {
			SharedPreferences preference1 = context.getSharedPreferences(Key.sharePerferceStr, 0);
			String bureauCode = preference1.getString(Key.bureau, null);
			String deptCode = preference1.getString(Key.dept, null);
//			if (bureauCode == null || deptCode == null || bureauCode.equals(Constant.bureauCode) || deptCode.equals(Constant.deptCode)) {
//				//表示没有获取到正确信息，需要退出重新登录
//				return Constant.loginOut;
//			}
			JSONObject jsonObject = new JSONObject();
			try {
				jsonObject.put(Key.bureau, bureauCode);
				jsonObject.put(Key.dept, deptCode);
				jsonObject.put(Key.train, train);
				jsonObject.put(Key.wds, wds);
				jsonObject.put(Key.wde, wde);
				jsonObject.put(Key.md, md);
				jsonObject.put(Key.mdi, mdi);
				//获取服务器地址
				String url = Constant.APK_CHECK;
				//连接服务器并获取反馈的信息
				String result = TKYHttpClient.connect(url, jsonObject.toString());
				if (result == null || (result != null && result.equals(""))) {
					return null;
				}
				return result;
			} catch (JSONException e) {
				e.printStackTrace();
				return Constant.jsonObjectErr;
			}
		} else {
			return Constant.postResult_noIp;
		}
	}
}
