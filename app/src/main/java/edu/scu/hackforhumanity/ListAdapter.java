package edu.scu.hackforhumanity;

//import com.example.listview_load_data_from_json.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {

	ListActivity1 main;
	
	ListAdapter(ListActivity1 main)
	{
		this.main = main;
	}
	
	@Override
	public int getCount() {
		return  main.countries.size();
	}
 
	@Override
	public Object getItem(int position) {
		return null;
	}
 
	@Override
	public long getItemId(int position) {
		return 0;
	}
	
	static class ViewHolderItem {
		TextView name;
		TextView code;
		TextView zip;
		TextView phone;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		ViewHolderItem holder = new ViewHolderItem();
		if (convertView == null) {
		 LayoutInflater inflater = (LayoutInflater) main.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        convertView = inflater.inflate(R.layout.cell, null);
	        
	        holder.name = (TextView) convertView.findViewById(R.id.name);
	        holder.code = (TextView) convertView.findViewById(R.id.code);
	        holder.zip = (TextView) convertView.findViewById(R.id.cell_zip_textView);
	        holder.phone = (TextView) convertView.findViewById(R.id.cell_phone_textView);

	        convertView.setTag(holder);
		}
		else
		{
			 holder = (ViewHolderItem) convertView.getTag();
		}
	        
		
		holder.name.setText(this.main.countries.get(position).Name);
		holder.code.setText(this.main.countries.get(position).Items);
		holder.zip.setText(this.main.countries.get(position).Location);
		holder.phone.setText(this.main.countries.get(position).PhoneNo);

		return convertView;
	}

}
