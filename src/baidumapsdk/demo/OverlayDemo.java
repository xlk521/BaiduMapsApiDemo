package baidumapsdk.demo;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;
import baidumapsdk.demo.cons.Constant;
import baidumapsdk.demo.cons.GetJsonFromHttp;
import baidumapsdk.demo.cons.Key;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.GroundOverlayOptions;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.InfoWindow.OnInfoWindowClickListener;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;

/**
 * 演示覆盖物的用法
 */
public class OverlayDemo extends Activity {

	/**
	 * MapView 是地图主控件
	 */
	private MapView mMapView;
	private BaiduMap mBaiduMap;
	private Marker mMarkerD = null;
	private ArrayList<View> ViewList = null;
	private InfoWindow mInfoWindow;
	private Button freshBtn = null;
	private GetJsonFromHttp getJsonFromHttp = null;
	private String tag = "OverlayDemo";
	private JSONArray jsonArray = null;
	private View view = null;
	private TextView checiView = null;
	private TextView dandangju1View = null;
	private TextView dandangju2View = null;
	private TextView dandangju3View = null;
	private TextView shifaView = null;
	private TextView chezhangView = null;
	private TextView bianzuView = null;
	private TextView dingyuanView = null;
	private TextView renshu1View = null;
	private TextView renshu2View = null;
	private TextView yunxing1View = null;
	private TextView yunxing2View = null;
	private TextView yunxing3View = null;
	private TextView dingweishijianView = null;
	// 初始化全局 bitmap 信息，不用时及时 recycle
	BitmapDescriptor bdD = BitmapDescriptorFactory.fromResource(R.drawable.icon_markd);
	BitmapDescriptor bd = BitmapDescriptorFactory.fromResource(R.drawable.icon_gcoding);
	BitmapDescriptor bdGround = BitmapDescriptorFactory.fromResource(R.drawable.ground_overlay);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_overlay);
		mMapView = (MapView) findViewById(R.id.bmapView);
		freshBtn = (Button)this.findViewById(R.id.fresh);
		checiView = (TextView)this.findViewById(R.id.checi);
		view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.window, null);
		checiView = (TextView)view.findViewById(R.id.checi);
		dandangju1View = (TextView)view.findViewById(R.id.dandangju1);
		dandangju2View = (TextView)view.findViewById(R.id.dandangju2);
		dandangju3View = (TextView)view.findViewById(R.id.dandangju3);
		shifaView = (TextView)view.findViewById(R.id.shifa);
		chezhangView = (TextView)view.findViewById(R.id.chezhang);
		bianzuView = (TextView)view.findViewById(R.id.bianzu);
		dingyuanView = (TextView)view.findViewById(R.id.dingyuan);
		renshu1View = (TextView)view.findViewById(R.id.renshu1);
		renshu2View = (TextView)view.findViewById(R.id.renshu2);
		yunxing1View = (TextView)view.findViewById(R.id.yunxing1);
		yunxing2View = (TextView)view.findViewById(R.id.yunxing2);
		yunxing3View = (TextView)view.findViewById(R.id.yunxing3);
		dingweishijianView = (TextView)view.findViewById(R.id.dingweishijian);
		getJsonFromHttp = new GetJsonFromHttp(getApplicationContext());
		mBaiduMap = mMapView.getMap();
		MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(13.0f);//调节地图初始化时的显示缩放度
		mBaiduMap.setMapStatus(msu);
		initOverlay();
		//设置刷新按钮的功能
		freshBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//重新设定界面布局
				resetOverlay(null);
			}
		});
	}

	public void initOverlay() {
		//开启线程，用于更新数据
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					String jsonResult = getJsonFromHttp.getJsonByHttpForMap("", "", "", "", "");
					if (jsonResult != null && !jsonResult.equals("")) {
						Log.i(tag ,jsonResult);
						if (jsonResult.equals(Constant.loginOut)) {
							//login out
						}else if (jsonResult.equals(Constant.jsonObjectErr)) {
							// catch err
						}else if (jsonResult.equals(Constant.postResult_noIp)) {
							//no wifi
						}else{
							//json
//							JSONObject jsonObject = new JSONObject(jsonResult);
							jsonArray = new JSONArray(jsonResult);
							if (jsonArray != null && jsonArray.length() > 0) {
//								initOverlay(jsonArray);
								
								LatLng llD2 = new LatLng(39.956965, 116.401394);
								OverlayOptions ooD = null;
								double d = 0.010000;
								double a = 39.906965;
								double b = 116.401394;
								OverlayOptions ooText = null;
								ViewList = new ArrayList<View>();
								for (int i = 0; i < jsonArray.length(); i++) {
									JSONObject obj = jsonArray.getJSONObject(i);
//									String zttype = obj.getString(Key.);
									String zttime = obj.getString(Key.zttime);
									String ztcheci = obj.getString(Key.ztcheci);
									String zttel = obj.getString(Key.zttel);
//									String biaoshi = obj.getString(Key.);
									String lng = obj.getString(Key.lng);
									String lat = obj.getString(Key.lat);
									String pos00 = obj.getString(Key.pos00);
//									String pos0 = obj.getString(Key.);
									String pos1 = obj.getString(Key.pos1);
									String TrainDirection = obj.getString(Key.TrainDirection);
//									String wdmin = obj.getString(Key.);
									String wdminu = obj.getString(Key.wdminu);
									String shifa = obj.getString(Key.shifa);
									String BianZu = obj.getString(Key.BianZu);
//									String CheDui = obj.getString(Key.CheDui);
									String XiaZhan = obj.getString(Key.XiaZhan);
									String peoplecount = obj.getString(Key.peoplecount);
									String BureauName = obj.getString(Key.BureauName);
//									String BureauNameCode = obj.getString(Key.);
									String DeptName = obj.getString(Key.DeptName);
//									String DeptNameCode = obj.getString(Key.);
									String TeamName = obj.getString(Key.TeamName);
//									String TeamValue = obj.getString(Key.);
									String banzu = obj.getString(Key.banzu);
//									String GroupValue = obj.getString(Key.);
									
									double latDouble = Double.valueOf(lat);
									double lngDouble = Double.valueOf(lng);
									//设置坐标位置
									llD2 = new LatLng(latDouble, lngDouble);
									//设置显示位置
									ooD = new MarkerOptions().position(llD2).icon(bd).perspective(false).zIndex(7);
									mMarkerD = (Marker)mBaiduMap.addOverlay(ooD);
									mMarkerD.setTitle(""+i);
									//添加文字
									ooText = new TextOptions().bgColor(0xAAFFFF00).fontSize(24).fontColor(0xFFFF00FF).text(ztcheci).rotate(0).position(llD2);
									mBaiduMap.addOverlay(ooText);

									checiView.setText(ztcheci+"("+TrainDirection+")");
									dandangju1View.setText(BureauName);
									dandangju2View.setText(DeptName);
									dandangju3View.setText(TeamName+" "+banzu);
									shifaView.setText(shifa+"始发");
									chezhangView.setText("列车长:"+pos00+"("+zttel+")");
									bianzuView.setText("编组:"+BianZu);
									dingyuanView.setText("全列定员:"+peoplecount);
									renshu1View.setText("车内人数:共计"+peoplecount);
									renshu2View.setText("CA:22人 RW:44人 YW：333人 YZ:999人");
									yunxing1View.setText(XiaZhan);
									yunxing2View.setText("区间（站间距23公里），距离前方站济南23.5公里");
									yunxing3View.setText(pos1);
									dingweishijianView = (TextView)view.findViewById(R.id.dingweishijian);
									ViewList.add(view);
								}
								if (ViewList != null && ViewList.size() > 0) {
									//添加点击事件
									mBaiduMap.setOnMarkerClickListener(new OnMarkerClickListener() {
										public boolean onMarkerClick(final Marker marker) {
											String title = marker.getTitle();
											if (title != null && !title.equals("")) {
												try {
													int titInt = Integer.parseInt(title);
													//DerekXie 20140704:调取外部view的方式
													View view = null;//LayoutInflater.from(getApplicationContext()).inflate(R.layout.window, null);
													view = ViewList.get(titInt);
													final LatLng ll = marker.getPosition();
													Point p = mBaiduMap.getProjection().toScreenLocation(ll);
													p.y -= 47;
													LatLng llInfo = mBaiduMap.getProjection().fromScreenLocation(p);
													//设置监听事件
													OnInfoWindowClickListener listener = null;
													listener = new OnInfoWindowClickListener() {
														public void onInfoWindowClick() {
															//隐藏弹出框
															mBaiduMap.hideInfoWindow();
														}
													};
													//设置显示框、显示位置、监听事件
													mInfoWindow = new InfoWindow(view, llInfo, listener);
													mBaiduMap.showInfoWindow(mInfoWindow);
												} catch (Exception e) {
													e.printStackTrace();
												}
											}
											return true;
										}
									});
								}
							}
						}
					}else{
						Log.i(tag, "noJson");
					}
				} catch (Exception e) {
					Log.i(tag, "catch");
					e.printStackTrace();
				}
			}}).start();
//		LatLng llD2 = new LatLng(39.956965, 116.401394);
//		OverlayOptions ooD = null;
//		double d = 0.010000;
//		double a = 39.906965;
//		double b = 116.401394;
//		OverlayOptions ooText = null;
//		for (int i = 0; i < 6; i++) {
//			a = a + d;
//			//设置坐标位置
//			llD2 = new LatLng(a, b);
//			//设置显示位置
//			ooD = new MarkerOptions().position(llD2).icon(bd).perspective(false).zIndex(7);
//			mMarkerD = (Marker)mBaiduMap.addOverlay(ooD);
//			mMarkerD.setTitle("T66"+i);
////			markerList.add(mMarkerD);
//			//添加文字
//			ooText = new TextOptions().bgColor(0xAAFFFF00).fontSize(24).fontColor(0xFFFF00FF).text("K11"+i).rotate(0).position(llD2);
//			mBaiduMap.addOverlay(ooText);
////			Toast.makeText(getApplicationContext(), "a:"+a+";b:"+b, 2000).show();
//		}
//		//添加点击事件
//		mBaiduMap.setOnMarkerClickListener(new OnMarkerClickListener() {
//			public boolean onMarkerClick(final Marker marker) {
//				//DerekXie 20140704:调取外部view的方式
//				View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.window, null);
//				final LatLng ll = marker.getPosition();
//				Point p = mBaiduMap.getProjection().toScreenLocation(ll);
//				Log.i(tag, "title:"+marker.getTitle());
//				p.y -= 47;
//				LatLng llInfo = mBaiduMap.getProjection().fromScreenLocation(p);
//				//设置监听事件
//				OnInfoWindowClickListener listener = null;
//				listener = new OnInfoWindowClickListener() {
//					public void onInfoWindowClick() {
//						//隐藏弹出框
//						mBaiduMap.hideInfoWindow();
//					}
//				};
//				//设置显示框、显示位置、监听事件
//				mInfoWindow = new InfoWindow(view, llInfo, listener);
//				mBaiduMap.showInfoWindow(mInfoWindow);
//				return true;
//			}
//		});
	}

	/**
	 * 清除所有Overlay
	 * 
	 * @param view
	 */
	public void clearOverlay(View view) {
		mBaiduMap.clear();
	}

	/**
	 * 重新添加Overlay
	 * 
	 * @param view
	 */
	public void resetOverlay(View view) {
		clearOverlay(null);
		initOverlay();
	}

	@Override
	protected void onPause() {
		// MapView的生命周期与Activity同步，当activity挂起时需调用MapView.onPause()
		mMapView.onPause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		// MapView的生命周期与Activity同步，当activity恢复时需调用MapView.onResume()
		mMapView.onResume();
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		// MapView的生命周期与Activity同步，当activity销毁时需调用MapView.destroy()
		mMapView.onDestroy();
		super.onDestroy();
		// 回收 bitmap 资源
		bdD.recycle();
		bd.recycle();
		bdGround.recycle();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);  
		menu.add(Menu.NONE, 1, 1, getString(R.string.fresh)).setIcon(android.R.drawable.ic_input_get);
	    return true;  
	}
	
	@SuppressWarnings("unused")
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			resetOverlay(null);
			break;
		}
		return false;
	}
}
