package info.androidhive.materialdesign.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import info.androidhive.materialdesign.Bean.RecipeSubCatListResults;
import info.androidhive.materialdesign.R;
import info.androidhive.materialdesign.Utilities.AvatarDownloader;

public class RecipeSubCatListAdapter extends BaseAdapter{
	@Override
	public int getCount() {
		return 0;
	}

	@Override
	public Object getItem(int i) {
		return null;
	}

	@Override
	public long getItemId(int i) {
		return 0;
	}

	@Override
	public View getView(int i, View view, ViewGroup viewGroup) {
		return null;
	}

//	private static ArrayList<RecipeSubCatListResults> ArrayList;
//
//	private LayoutInflater mInflater;
//	private Context mContext;
//	AvatarDownloader ad;
//
//	public RecipeSubCatListAdapter(Context context, ArrayList<RecipeSubCatListResults> results) {
//		ArrayList = results;
//		mInflater = LayoutInflater.from(context);
//		mContext=context;
//	}
//
//	@Override
//	public int getCount() {
//		// TODO Auto-generated method stub
//		return ArrayList.size();
//	}
//
//	@Override
//	public Object getItem(int position) {
//		// TODO Auto-generated method stub
//		return ArrayList.get(position);
//	}
//
//	@Override
//	public long getItemId(int position) {
//		// TODO Auto-generated method stub
//		return position;
//	}
//
//	@Override
//	public View getView(int position, View convertView, ViewGroup parent) {
//		// TODO Auto-generated method stub
//		final ViewHolder holder;
//		if (convertView == null) {
//			convertView = mInflater.inflate(R.layout.subcat_row, null);
//			holder = new ViewHolder();
//
//			holder.tv_Title = (TextView) convertView.findViewById(R.id.txtsubcatTitle);
//			holder.imgvw_pic = (ImageView) convertView.findViewById(R.id.imageView_pic);
//			ad=new AvatarDownloader(mContext);
//			ad.DisplayImage(ArrayList.get(position).getPicURL(), ArrayList.get(position).getTitle(), null, holder.imgvw_pic);
//
//			convertView.setTag(holder);
//		} else {
//			holder = (ViewHolder) convertView.getTag();
//		}
//
//		holder.tv_Title.setText(ArrayList.get(position).getTitle());
//
//
//
//		return convertView;
//	}
//
//	static class ViewHolder {
//		TextView tv_Title;
//		ImageView imgvw_pic;
//	}

}
