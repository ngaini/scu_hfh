package edu.scu.hackforhumanity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//import com.example.listview_load_data_from_json.R;
//import com.kaleidosstudio.listview_load_data_from_json.Download_data.download_complete;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;


public class ListActivity1 extends Activity implements Download_data.download_complete,LocationListener {

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
//			Toast.makeText(ListActivity1.this, "Network Issues unable to fetch Loaction", Toast.LENGTH_SHORT);
//			latituteField.setText("Location not available");
//			longitudeField.setText("Location not available");
		}


		//quering for the list
		if(zipCode!=null)
		{
			Download_data download_data = new Download_data((Download_data.download_complete) this);
			download_data.download_data_from_link("http://aabtech.us/HFH/findpeople.php?pin="+zipCode);
			Log.e("URLTHIS","http://aabtech.us/HFH/findpeople.php?pin="+zipCode);
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
}
