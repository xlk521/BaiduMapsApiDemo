package baidumapsdk.demo.cons;

import baidumapsdk.demo.R;
import baidumapsdk.demo.R.layout;
import baidumapsdk.demo.R.menu;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
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

	//设置标题栏右侧按钮的作用
	public void btnmainright(View v) {
		Intent intent = new Intent (getApplicationContext(),MainTopRightDialogActivity.class);
		startActivity(intent);
	}  	
	public void login_back(View v) {     //标题栏 返回按钮
      	this.finish();
    }
	public void timeFresh(View v){
		String freshStr = "";
		if (Constant.isFresh == 1) {
			Constant.isFresh = 0;
			freshStr = "停止刷新";
		}else{
			Constant.isFresh = 1;
			freshStr = "开启刷新（每分钟）";
		}
		Toast.makeText(getApplicationContext(), freshStr, Toast.LENGTH_SHORT).show();
		finish();
	}
	public void forWeb(View v){
		Toast.makeText(getApplicationContext(), "正在研发，敬请期待", Toast.LENGTH_SHORT).show();
		finish();
	}
}
