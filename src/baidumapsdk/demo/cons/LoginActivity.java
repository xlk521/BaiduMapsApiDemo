package baidumapsdk.demo.cons;
/*DerekXie 20140709
 * 由于要在ViewPager中调用该界面以及相应功能，只能把所有功能放在onCreate函数中
 * */
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

import baidumapsdk.demo.OverlayDemo;
import baidumapsdk.demo.R;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class LoginActivity extends BaseActivity {

	private EditText username_edit = null;
	private EditText password_edit = null;
	private Spinner duan_spinner = null;
	private EditText phone_edit = null;
	private String nowDeptCode = "";
	private String nowBureauCode = "";
	private String userName = "";
	private String password = "";
	private String phone = "";
	private Button loginBtn = null;
	private GetJsonFromHttp getJsonFromHttp = null;
	private String tag = "LoginActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		username_edit = (EditText)this.findViewById(R.id.username_edit);
		password_edit = (EditText)this.findViewById(R.id.password_edit);
		duan_spinner = (Spinner)this.findViewById(R.id.duan_spinner);
		phone_edit = (EditText)this.findViewById(R.id.phone_edit);
		loginBtn = (Button)this.findViewById(R.id.loginBtn);
		getJsonFromHttp = new GetJsonFromHttp(getApplicationContext());
		init();
	}
	
	@Override
	protected void onResume() {
		super.onResume();

	}
	private void init(){
		//若已登录成功,未退乘过,则直接跳转至主界面
		SharedPreferences preferen = getSharedPreferences(Constant.sharePerferceStr, 0);
		if (preferen != null) {
			userName = preferen.getString(Key.user_id, null);
			nowDeptCode = preferen.getString(Key.dept, null);
			nowBureauCode = preferen.getString(Key.bureau, null);
			if(userName != null && nowDeptCode != null){//已经登录
				activityHandler.sendEmptyMessage(Constant.loginSuccess);
			}else{//重新登录
				try {
					Log.e(tag, "1");
					ArrayList<String> listDeptName = new ArrayList<String>();
					ArrayList<String> listDeptCode = new ArrayList<String>();
					ArrayList<String> listBureauCode = new ArrayList<String>();
					JSONArray data = new JSONArray(Constant.DEPTSTR);
					if (data != null && data.length() > 0) {
						Log.e(tag, "2");
						for(int i = 0; i<data.length(); i++){
							JSONObject obj = data.getJSONObject(i);
							listDeptName.add(obj.getString("DeptName"));
							listDeptCode.add(obj.getString("DeptCode"));
							listBureauCode.add(obj.getString("BureauCode"));
						}

						Log.e(tag, "3");
						if (listDeptName != null && listDeptName.size() > 0) {
							Log.e(tag, "4");
							if (listDeptCode != null && listDeptCode.size() > 0) {
								Log.e(tag, "5");
								if (listBureauCode != null && listBureauCode.size() > 0) {
									Log.e(tag, "6");
									final ArrayList<String> listDeptName2 = listDeptName;
									final ArrayList<String> listDeptCode2 = listDeptCode;
									final ArrayList<String> listBureauCode2 = listBureauCode;
									CrewBreaksSpinnerAdapter spinnerAdapter = new CrewBreaksSpinnerAdapter(LoginActivity.this, listDeptName2);
									duan_spinner.setAdapter(spinnerAdapter);
									duan_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
										@Override
										public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
											try {
												nowDeptCode = listDeptCode2.get(arg2);
												nowBureauCode = listBureauCode2.get(arg2);
											} catch (Exception e) {
												e.printStackTrace();
											}
										}

										@Override
										public void onNothingSelected(AdapterView<?> arg0) {
										}
									});
									loginBtn.setOnClickListener(new OnClickListener() {
										@Override
										public void onClick(View v) {
											try {
												userName = username_edit.getText().toString();
												password = password_edit.getText().toString();
												phone = phone_edit.getText().toString();
												if (userName != null && !userName.equals("")) {
													if (password != null && !userName.equals("")) {
														if (phone != null && !userName.equals("")) {
															if (nowDeptCode != null && !nowDeptCode.equals("")) {

//																SharedPreferences preference1 = context.getSharedPreferences(Key.sharePerferceStr, 0);
																//开启线程，用于更新数据
																new Thread(new Runnable() {
																	@Override
																	public void run() {
																		try {
																			String jsonResult = getJsonFromHttp.getJsonByHttpForLogin(userName, password, nowDeptCode, phone);
																			if (jsonResult != null && !jsonResult.equals("")){
																				Log.i(tag ,jsonResult);
																				if (jsonResult.equals(Constant.loginOut)) {
																					//login out
																				}else if (jsonResult.equals(Constant.jsonObjectErr)) {
																					// catch err
																				}else if (jsonResult.equals(Constant.postResult_noIp)) {
																					//no wifi
																				}else{
																					try {
																						JSONObject jsonObject = new JSONObject(jsonResult);
																						String result = jsonObject.getString(Key.result);
																						if (result.equals(Key.resultOk)) {
																							SharedPreferences trainShare1 = LoginActivity.this.getSharedPreferences(Constant.sharePerferceStr,0);
																						    trainShare1.edit().putString(Key.bureau, nowDeptCode).commit();
																						    trainShare1.edit().putString(Key.dept, nowBureauCode).commit();
																						    trainShare1.edit().putString(Key.user_id, userName).commit();
																						    trainShare1.edit().putString(Key.my_device, phone).commit();
																							//登录成功
																							Message msg = new Message();
																							msg.what = Constant.loginSuccess;
																							activityHandler.sendMessage(msg);
																						}else{
																							//提示重新登录
																							Message msg = new Message();
																							msg.what = Constant.loginFail;
																							baseActivityHandler.sendMessage(msg);
																						}
																					} catch (Exception e) {
																						e.printStackTrace();
																					}
																				}
																			}
																		} catch (Exception e) {
																			e.printStackTrace();
																		}
																	}
																}).start();
															}else{
																Message msg = new Message();
																msg.what = Constant.Null_Duan;
																baseActivityHandler.sendMessage(msg);
															}
														}else{
															Message msg = new Message();
															msg.what = Constant.Null_Phone;
															baseActivityHandler.sendMessage(msg);
														}
													}else{
														Message msg = new Message();
														msg.what = Constant.Null_Password;
														baseActivityHandler.sendMessage(msg);
													}
												}else{
													Message msg = new Message();
													msg.what = Constant.Null_Username;
													baseActivityHandler.sendMessage(msg);
												}
											} catch (Exception e) {
												e.printStackTrace();
											}
										}
									});
								}
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	public Handler activityHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case Constant.loginSuccess:
				Toast.makeText(getApplicationContext(), "登录成功！", Toast.LENGTH_LONG).show();
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), OverlayDemo.class);
				startActivity(intent);
				finish();
				break;
			}
		}
	};
	//forget password
	public void login_pw(View v) {
      	Toast.makeText(getApplicationContext(), "请联系管理员", Toast.LENGTH_LONG).show();
    }
}
