package baidumapsdk.demo.cons;
import java.util.ArrayList;

import baidumapsdk.demo.BMapApiDemoMain;
import baidumapsdk.demo.OverlayDemo;
import baidumapsdk.demo.R;
import android.os.Bundle;
import android.os.Parcelable;
import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

public class MainLeaderActivity extends BaseActivity {

	public static MainLeaderActivity instance = null;
	private LocalActivityManager manager = null;
	private MyViewPager mTabPager;
	private ImageView mTabImg;// 动画图片
	private ImageView mTab1,mTab2,mTab3,mTab4;
	private int zero = 0;// 动画图片偏移量
	private int currIndex = 0;// 当前页卡编号
	private int one;//单个水平动画位移
	private int two;
	private int three;
	private LinearLayout mClose;
    private LinearLayout mCloseBtn;
    private View layout;	
	private boolean menu_display = false;
	private PopupWindow menuWindow;
	private LayoutInflater inflater;
	private  PagerAdapter mPagerAdapter = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main_leader);
		
		 //启动activity时不自动弹出软键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); 
        instance = this;
        /*
        mRightBtn = (Button) findViewById(R.id.right_btn);
        mRightBtn.setOnClickListener(new Button.OnClickListener()
		{	@Override
			public void onClick(View v)
			{	showPopupWindow (MainWeixin.this,mRightBtn);
			}
		  });*/
        
        manager = new LocalActivityManager(this , true);
        manager.dispatchCreate(savedInstanceState);
        
        mTabPager = (MyViewPager)findViewById(R.id.tabpager);
        mTabPager.setOnPageChangeListener(new MyOnPageChangeListener());

        mTab1 = (ImageView) findViewById(R.id.img_weixin);
        mTab2 = (ImageView) findViewById(R.id.img_address);
        mTab3 = (ImageView) findViewById(R.id.img_friends);
        mTab4 = (ImageView) findViewById(R.id.img_settings);
        mTabImg = (ImageView) findViewById(R.id.img_tab_now);
        mTab1.setOnClickListener(new MyOnClickListener(0));
        mTab2.setOnClickListener(new MyOnClickListener(1));
        mTab3.setOnClickListener(new MyOnClickListener(2));
        mTab4.setOnClickListener(new MyOnClickListener(3));
        Display currDisplay = getWindowManager().getDefaultDisplay();//获取屏幕当前分辨率
        int displayWidth = currDisplay.getWidth();
        int displayHeight = currDisplay.getHeight();
        one = displayWidth/4; //设置水平动画平移大小
        two = one*2;
        three = one*3;
        //Log.i("info", "获取的屏幕分辨率为" + one + two + three + "X" + displayHeight);
        
        //InitImageView();//使用动画
        
        setPageAdapter();
//      //每个页面的view数据
//        final ArrayList<View> views = new ArrayList<View>();
//        Intent intent1 = new Intent(this, OverlayDemo.class);
//        views.add(getView("A", intent1));
//        Intent intent2 = new Intent(this, WelcomeActivity.class);
//        views.add(getView("B", intent2));
//        Intent intent3 = new Intent(this, WelcomeActivity.class);
//        views.add(getView("C", intent3));
//        Intent intent4 = new Intent(this, BMapApiDemoMain.class);
//        views.add(getView("D", intent4));
//      //填充ViewPager的数据适配器
//        mPagerAdapter = new PagerAdapter() {
//			
//			@Override
//			public boolean isViewFromObject(View arg0, Object arg1) {
//				return arg0 == arg1;
//			}
//			
//			@Override
//			public int getCount() {
//				return views.size();
//			}
//
//			@Override
//			public void destroyItem(View container, int position, Object object) {
//				((ViewPager)container).removeView(views.get(position));
//			}
//			
//			@Override
//			public Object instantiateItem(View container, int position) {
//				ViewPager pViewPager = ((ViewPager) container);
//	            pViewPager.addView(views.get(position));
//	            return views.get(position);
//			}
//		};
//		
//		mTabPager.setAdapter(mPagerAdapter);
	}
	private void setPageAdapter() {
		//设置预加载的个数，由于有4个tag，所以设置为3，可以防止地图被销毁
		mTabPager.setOffscreenPageLimit(3);
		  //每个页面的view数据
        final ArrayList<View> views = new ArrayList<View>();
        Intent intent1 = new Intent(this, OverlayDemo.class);
        views.add(getView("A", intent1));
        Intent intent2 = new Intent(this, WelcomeActivity.class);
        views.add(getView("B", intent2));
        Intent intent3 = new Intent(this, WelcomeActivity.class);
        views.add(getView("C", intent3));
        Intent intent4 = new Intent(this, BMapApiDemoMain.class);
        views.add(getView("D", intent4));
      //填充ViewPager的数据适配器
        mPagerAdapter = new PagerAdapter() {
			
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}
			
			@Override
			public int getCount() {
				return views.size();
			}

			@Override
			public void destroyItem(View container, int position, Object object) {
				((ViewPager)container).removeView(views.get(position));
			}
			
			@Override
			public Object instantiateItem(View container, int position) {
				ViewPager pViewPager = ((ViewPager) container);
	            pViewPager.addView(views.get(position));
	            return views.get(position);
			}
			@Override  
			public int getItemPosition(Object object) {  
				return POSITION_NONE;  
		    }
			@Override    
	       public void finishUpdate(View arg0) {}    
	           
	       @Override  
	       public void restoreState(android.os.Parcelable state, ClassLoader loader) {  
	          
	       }
	       @Override    
	       public void startUpdate(View arg0) {}
			@Override    
	       public Parcelable saveState() {    
	           return null;    
	       }
		};
		
		mTabPager.setAdapter(mPagerAdapter);
		
	}
	/**
     * 通过activity获取视图
     * @param id
     * @param intent
     * @return
     */
    @SuppressWarnings("deprecation")
	private View getView(String id, Intent intent) {
        return manager.startActivity(id, intent).getDecorView();
    }
	/**
	 * 头标点击监听
	 */
	public class MyOnClickListener implements View.OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;
		}
		@Override
		public void onClick(View v) {
			mTabPager.setCurrentItem(index);
		}
	};
	
	 /* 页卡切换监听(原作者:D.Winter)
		 */
	public class MyOnPageChangeListener implements OnPageChangeListener {
		@Override
		public void onPageSelected(int arg0) {
			Animation animation = null;
			switch (arg0) {
			case 0:
				mTab1.setImageDrawable(getResources().getDrawable(R.drawable.tab_weixin_pressed));
				if (currIndex == 1) {
					animation = new TranslateAnimation(one, 0, 0, 0);
					mTab2.setImageDrawable(getResources().getDrawable(R.drawable.tab_address_normal));
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, 0, 0, 0);
					mTab3.setImageDrawable(getResources().getDrawable(R.drawable.tab_find_frd_normal));
				}
				else if (currIndex == 3) {
					animation = new TranslateAnimation(three, 0, 0, 0);
					mTab4.setImageDrawable(getResources().getDrawable(R.drawable.tab_settings_normal));
				}
				break;
			case 1:
				mTab2.setImageDrawable(getResources().getDrawable(R.drawable.tab_address_pressed));
				if (currIndex == 0) {
					animation = new TranslateAnimation(zero, one, 0, 0);
					mTab1.setImageDrawable(getResources().getDrawable(R.drawable.tab_weixin_normal));
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, one, 0, 0);
					mTab3.setImageDrawable(getResources().getDrawable(R.drawable.tab_find_frd_normal));
				}
				else if (currIndex == 3) {
					animation = new TranslateAnimation(three, one, 0, 0);
					mTab4.setImageDrawable(getResources().getDrawable(R.drawable.tab_settings_normal));
				}
				break;
			case 2:
				mTab3.setImageDrawable(getResources().getDrawable(R.drawable.tab_find_frd_pressed));
				if (currIndex == 0) {
					animation = new TranslateAnimation(zero, two, 0, 0);
					mTab1.setImageDrawable(getResources().getDrawable(R.drawable.tab_weixin_normal));
				} else if (currIndex == 1) {
					animation = new TranslateAnimation(one, two, 0, 0);
					mTab2.setImageDrawable(getResources().getDrawable(R.drawable.tab_address_normal));
				}
				else if (currIndex == 3) {
					animation = new TranslateAnimation(three, two, 0, 0);
					mTab4.setImageDrawable(getResources().getDrawable(R.drawable.tab_settings_normal));
				}
				break;
			case 3:
				mTab4.setImageDrawable(getResources().getDrawable(R.drawable.tab_settings_pressed));
				if (currIndex == 0) {
					animation = new TranslateAnimation(zero, three, 0, 0);
					mTab1.setImageDrawable(getResources().getDrawable(R.drawable.tab_weixin_normal));
				} else if (currIndex == 1) {
					animation = new TranslateAnimation(one, three, 0, 0);
					mTab2.setImageDrawable(getResources().getDrawable(R.drawable.tab_address_normal));
				}
				else if (currIndex == 2) {
					animation = new TranslateAnimation(two, three, 0, 0);
					mTab3.setImageDrawable(getResources().getDrawable(R.drawable.tab_find_frd_normal));
				}
				break;
			}
			currIndex = arg0;
			animation.setFillAfter(true);// True:图片停在动画结束位置
			animation.setDuration(150);
			mTabImg.startAnimation(animation);
		}
		
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}
	
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {  //获取 back键
        	if(menu_display){         //如果 Menu已经打开 ，先关闭Menu
        		menuWindow.dismiss();
        		menu_display = false;
        	}else {
        		Intent intent = new Intent();
            	intent.setClass(MainLeaderActivity.this,ExitActivity.class);
            	startActivity(intent);
        	}
    	}else if(keyCode == KeyEvent.KEYCODE_MENU){   //获取 Menu键			
			if(!menu_display){
				//获取LayoutInflater实例
				inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
				//这里的main布局是在inflate中加入的哦，以前都是直接this.setContentView()的吧？呵呵
				//该方法返回的是一个View的对象，是布局中的根
				layout = inflater.inflate(R.layout.main_menu, null);
				
				//下面我们要考虑了，我怎样将我的layout加入到PopupWindow中呢？？？很简单
				menuWindow = new PopupWindow(layout,LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT); //后两个参数是width和height
//				menuWindow.showAsDropDown(layout); //设置弹出效果
//				menuWindow.showAsDropDown(null, 0, layout.getHeight());
				menuWindow.showAtLocation(this.findViewById(R.id.mainweixin), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
				//如何获取我们main中的控件呢？也很简单
				mClose = (LinearLayout)layout.findViewById(R.id.menu_close);
				mCloseBtn = (LinearLayout)layout.findViewById(R.id.menu_close_btn);
				
				//下面对每一个Layout进行单击事件的注册吧。。。
				//比如单击某个MenuItem的时候，他的背景色改变
				//事先准备好一些背景图片或者颜色
				mCloseBtn.setOnClickListener (new View.OnClickListener() {
					@Override
					public void onClick(View arg0) {					
						//Toast.makeText(Main.this, "退出", Toast.LENGTH_LONG).show();
						Intent intent = new Intent();
			        	intent.setClass(MainLeaderActivity.this,ExitActivity.class);
			        	startActivity(intent);
			        	menuWindow.dismiss(); //响应点击事件之后关闭Menu
					}
				});				
				menu_display = true;				
			}else{
				//如果当前已经为显示状态，则隐藏起来
				menuWindow.dismiss();
				menu_display = false;
			}
			return false;
		}
    	return false;
    }
	public void startchat(View v) {      //小黑  对话界面
//		Intent intent = new Intent (MainLeaderActivity.this,ChatActivity.class);
//		startActivity(intent);
		Toast.makeText(getApplicationContext(), "小黑  对话界面", Toast.LENGTH_LONG).show();
      }  
	public void exit_settings(View v) {                           //退出  伪“对话框”，其实是一个activity
//		Intent intent = new Intent (MainLeaderActivity.this,ExitFromSettings.class);
//		startActivity(intent);
		Toast.makeText(getApplicationContext(), "退出  伪“对话框”，其实是一个activity", Toast.LENGTH_LONG).show();
	 }
	public void btn_shake(View v) {                                   //手机摇一摇
//		Intent intent = new Intent (MainLeaderActivity.this,ShakeActivity.class);			
//		startActivity(intent);
		Toast.makeText(getApplicationContext(), "手机摇一摇", Toast.LENGTH_LONG).show();
	
	}
}
