package baidumapsdk.demo.cons;
import baidumapsdk.demo.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ExitActivity extends Activity {

	private LinearLayout layout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exit);//activity_exit
	}

	@Override
	public boolean onTouchEvent(MotionEvent event){
		finish();
		return true;
	}
	public void exitbutton1(View v) {
    	this.finish();
      }  
	public void exitbutton0(View v) {
    	this.finish();
    	MainLeaderActivity.instance.finish();//关闭Main 这个Activity
    }
}
