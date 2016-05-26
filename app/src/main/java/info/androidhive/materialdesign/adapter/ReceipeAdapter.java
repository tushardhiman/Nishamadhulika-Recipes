package info.androidhive.materialdesign.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import info.androidhive.materialdesign.Bean.ReceipSub_data;
import info.androidhive.materialdesign.ImageLoader.ImageLoader;
import info.androidhive.materialdesign.R;
import info.androidhive.materialdesign.Utilities.AvatarDownloader;

public class ReceipeAdapter extends BaseAdapter{
	private static ArrayList<ReceipSub_data> ArrayList;
	
	private LayoutInflater mInflater;
	private Context mContext;
	AvatarDownloader ad;
	ImageLoader mImageLoader;
	
	public ReceipeAdapter(Context context, ArrayList<ReceipSub_data> results) {
		ArrayList = results;
		mInflater = LayoutInflater.from(context);
		mContext=context;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return ArrayList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return ArrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.list_single, null);
			holder = new ViewHolder();
			
			holder.tv_Title = (TextView) convertView.findViewById(R.id.txt);
			holder.imgvw_pic = (ImageView) convertView.findViewById(R.id.img);
			//ad=new AvatarDownloader(mContext);
			mImageLoader=new ImageLoader(mContext);
			mImageLoader.DisplayImage(ArrayList.get(position).GetThumbURL(), ArrayList.get(position).GetTitle(), null, holder.imgvw_pic);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.tv_Title.setText(ArrayList.get(position).GetTitle());
		
		
			    				
		return convertView;
	}
	
	static class ViewHolder {
		TextView tv_Title;
		ImageView imgvw_pic;
	}

}
