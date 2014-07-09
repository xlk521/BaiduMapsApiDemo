package baidumapsdk.demo.cons;

import baidumapsdk.demo.R;
import baidumapsdk.demo.R.layout;
import baidumapsdk.demo.R.menu;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.widget.Toast;

public class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public Handler baseActivityHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case Constant.Null_Username:
				Toast.makeText(getApplicationContext(), "请添加工号！", Toast.LENGTH_LONG).show();
				break;
			case Constant.Null_Password:
				Toast.makeText(getApplicationContext(), "请添加密码！", Toast.LENGTH_LONG).show();
				break;
			case Constant.Null_Phone:
				Toast.makeText(getApplicationContext(), "请添加设备号！", Toast.LENGTH_LONG).show();
				break;
			case Constant.Null_Duan:
				Toast.makeText(getApplicationContext(), "请选择所属段！", Toast.LENGTH_LONG).show();
				break;
			case Constant.loginSuccess:
				Toast.makeText(getApplicationContext(), "登录成功！", Toast.LENGTH_LONG).show();
				break;
			case Constant.loginFail:
				Toast.makeText(getApplicationContext(), "登录失败！", Toast.LENGTH_LONG).show();
				break;
			}
		}
	};
}
