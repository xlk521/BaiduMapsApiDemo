package baidumapsdk.demo.cons;
import baidumapsdk.demo.R;
import baidumapsdk.demo.R.layout;
import baidumapsdk.demo.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class WelcomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
//		setContentView(R.layout.login);
	}

	public void welcome_login(View v) {
      	Intent intent = new Intent();
		intent.setClass(this,LoginActivity.class);
		startActivity(intent);
		this.finish();
    }  
    public void welcome_register(View v) {
      	Intent intent = new Intent();
		intent.setClass(this,MainLeaderActivity.class);
		startActivity(intent);
		this.finish();
      	Toast.makeText(getApplicationContext(), "敬请期待", Toast.LENGTH_LONG).show();
    }
}
