package edu.scu.hackforhumanity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

//import com.example.listview_load_data_from_json.R;
//import com.kaleidosstudio.listview_load_data_from_json.Download_data.download_complete;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class ListActivity1 extends AppCompatActivity implements Download_data.download_complete,LocationListener {

	public ListView list;
    public ArrayList<Countries> countries = new ArrayList<Countries>();
    public ListAdapter adapter;
	private static String zipCode;
	private LocationManager locationManager;
	private String provider;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		
		list = (ListView) findViewById(R.id.list_thelist_listView);
		adapter = new ListAdapter(this);
		list.setAdapter(adapter);

		//Code for zipcode
		// Get the location manager
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		// Define the criteria how to select the locatioin provider -> use
		// default
		Criteria criteria = new Criteria();
		provider = locationManager.getBestProvider(criteria, false);
		if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
			// TODO: Consider calling
			//    ActivityCompat#requestPermissions
			// here to request the missing permissions, and then overriding
			//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
			//                                          int[] grantResults)
			// to handle the case where the user grants the permission. See the documentation
			// for ActivityCompat#requestPermissions for more details.
			return;
		}
		Location location = locationManager.getLastKnownLocation(provider);

		// Initialize the location fields
		if (location != null) {
			System.out.println("Provider " + provider + " has been selected.");
			onLocationChanged(location);
			Toast.makeText(ListActivity1.this, ""+zipCode, Toast.LENGTH_SHORT);
		} else {
			Toast.makeText(ListActivity1.this, "Network Issues unable to fetch Loaction", Toast.LENGTH_SHORT);
//			latituteField.setText("Location not available");
//			longitudeField.setText("Location not available");
		}


		//quering for the list
		if(zipCode!=null)
		{
			Download_data download_data = new Download_data((Download_data.download_complete) this);
			download_data.download_data_from_link("http://aabtech.us/HFH/findpeople.php?pin="+zipCode);
			Log.e("URLTHIS", "http://aabtech.us/HFH/findpeople.php?pin=" + zipCode);

//			list.setOnClickListener(new AdapterView.OnItemClickListener() {
//				@Override
//				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//					TextView need_value=(TextView)findViewById(R.id.code);
//				}
//			});
			list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//					//fetch values of the item selected
					TextView need_id = (TextView)view.findViewById(R.id.code);
					TextView name_id = (TextView)view.findViewById(R.id.name);
					TextView zip_id = (TextView)view.findViewById(R.id.cell_zip_textView);
					TextView phone_id = (TextView)view.findViewById(R.id.cell_phone_textView);
					String name_value =name_id.getText().toString();
					String need_value = need_id.getText().toString();
					String zip_value = zip_id.getText().toString();
					String phone_value = phone_id.getText().toString();


					//if want to call default sms applicaiton
//					Intent smsIntent = new Intent(Intent.ACTION_VIEW);
//					smsIntent.setType("vnd.android-dir/mms-sms");
//					smsIntent.putExtra("address", phone_value);
//					smsIntent.putExtra("sms_body","I will be able to help you with "+need_value+" meet me at <Time and Location >");
//					startActivity(smsIntent);

					//if want to call another activity
					Intent myIntent = new Intent(ListActivity1.this, DetailActivity.class);
					myIntent.putExtra("name", name_value);
					myIntent.putExtra("need", need_value);
					myIntent.putExtra("phone", phone_value);
					startActivity(myIntent);

//					Toast.makeText(getApplicationContext(), phone_id.getText().toString()+" ::"+zip_id.getText().toString()+" ::"+name_id.getText().toString()+" ::"+need_id.getText().toString()+" ::"+position,
//							Toast.LENGTH_SHORT).show();
				}
			});
		}
		else
		{
			Toast.makeText(ListActivity1.this, "Network Issues unable to fetch Loaction", Toast.LENGTH_SHORT);
		}
		
	}
	


	public void get_data(String data)
	{
		
		try {
			JSONArray data_array=new JSONArray(data);
			
			for (int i = 0 ; i < data_array.length() ; i++)
			{
				JSONObject obj=new JSONObject(data_array.get(i).toString());

				Countries add=new Countries();
				add.PhoneNo = obj.getString("PhoneNo");
				add.Name = obj.getString("Name");
				add.Items = obj.getString("Items");
				add.Location = obj.getString("Location");
				countries.add(add);

			}

			adapter.notifyDataSetChanged();
			
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}


	@Override
	public void onLocationChanged(Location location) {
		double lat = (location.getLatitude());
		double lng = (location.getLongitude());
//		latituteField.setText(String.valueOf(lat));
//		longitudeField.setText(String.valueOf(lng));
		Geocoder geocoder = new Geocoder(this, Locale.ENGLISH);
		try {
			List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);


			if (addresses != null) {
				Address returnedAddress = addresses.get(0);
//                Address postal_code = addresses.get(0).getPostalCode();
				StringBuilder strReturnedAddress = new StringBuilder("Address:\n");
				for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++) {
					if(i==1)
					{
						strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
					}
				}
				zipCode = addresses.get(0).getPostalCode();
//				longitudeField.setText(addresses.get(0).getPostalCode());
			} else {
//				longitudeField.setText("No Address returned!");
			}

		} catch (IOException e) {
			e.printStackTrace();
//            e.printStackTrace();
//			longitudeField.setText("Canont get Address!");
		}
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {

	}

	@Override
	public void onProviderEnabled(String provider) {
		Toast.makeText(this, "Enabled new provider " + provider,
				Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onProviderDisabled(String provider) {
		Toast.makeText(this, "Disabled provider " + provider,
				Toast.LENGTH_SHORT).show();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		getMenuInflater().inflate(R.menu.menu_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (item.getItemId()) {
			case R.id.listMenu_logout:
				startActivity(new Intent(ListActivity1.this, MainActivity.class));
				break;
			case R.id.action_settings:
				break;
			default:
				break;
//		//noinspection SimplifiableIfStatement
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		if (id == R.id.listMenu_logout) {
//
//
//			return true;
		}



		return super.onOptionsItemSelected(item);
	}

	public void logoutAction(View view)
	{
		startActivity(new Intent(ListActivity1.this, MainActivity.class));
	}
}
