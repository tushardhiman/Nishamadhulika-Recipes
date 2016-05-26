package info.androidhive.materialdesign.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import info.androidhive.materialdesign.Bean.RecipeCatListResults;
import info.androidhive.materialdesign.R;

public class RecipeListAdapter extends BaseAdapter{
private static ArrayList<RecipeCatListResults> ArrayList;
	
	private LayoutInflater mInflater;
	
	public RecipeListAdapter(Context context, ArrayList<RecipeCatListResults> results) {
		ArrayList = results;
		mInflater = LayoutInflater.from(context);
	}
	public int getCount() {
		// TODO Auto-generated method stub
		return ArrayList.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return ArrayList.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		    final ViewHolder holder;
	        if(convertView==null)
	        {
	        	convertView = mInflater.inflate(R.layout.list_single, null);
	        holder = new ViewHolder();
			
			holder.tv_profilename = (TextView) convertView.findViewById(R.id.txt);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
	        // Setting all values in listview
	        holder.tv_profilename.setText(ArrayList.get(position).getRecipeCategoryName());
	        
	        return convertView;
	}
class ViewHolder
{
	TextView tv_profilename;
}

}
