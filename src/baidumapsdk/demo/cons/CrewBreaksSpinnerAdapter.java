package baidumapsdk.demo.cons;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CrewBreaksSpinnerAdapter   extends BaseAdapter{

	private ArrayList<String> items =  new ArrayList<String>();
	private Context context = null;
	public CrewBreaksSpinnerAdapter(Context con, ArrayList<String> item) {
		this.items = item;
		this.context = con;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@SuppressLint("ResourceAsColor")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// 动态生成每个下拉项对应的View，每个下拉项View由LinearLayout
		// 中包含一个ImageView及一个TextView构成
		// 初始化LinearLayout
		LinearLayout ll = new LinearLayout(context);
		ll.setOrientation(LinearLayout.HORIZONTAL);
		ImageView ii = new ImageView(context);
		ll.addView(ii);
		TextView tv = new TextView(context);
		tv.setText(items.get(position));
		tv.setTextSize(24);
		ll.addView(tv);
		return ll;
	}
}
