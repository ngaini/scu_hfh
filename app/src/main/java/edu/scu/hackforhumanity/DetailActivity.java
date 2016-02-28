package edu.scu.hackforhumanity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailActivity extends AppCompatActivity {
    private static String phone=null;
    private static String need=null;
    EditText time_id;
    EditText date_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String name= bundle.getString("name");
         need= bundle.getString("need");
         phone= bundle.getString("phone");

         time_id = (EditText)findViewById(R.id.time_id);
         date_id = (EditText)findViewById(R.id.date_id);
        EditText location_id = (EditText)findViewById(R.id.location_id);
        TextView heading_id = (TextView)findViewById(R.id.needy_name_id);
        TextView subheading_id = (TextView)findViewById(R.id.need_id);

        String time = time_id.getText().toString();
        String date = date_id.getText().toString();
        String location = location_id.getText().toString();

        String assitMsg="Assisting: "+name;
        String needMsg ="Necessity: "+need;
        heading_id.setText(assitMsg);
        subheading_id.setText(needMsg);


    }

    public void goBack(View view)
    {
        Intent backIntent = new Intent(DetailActivity.this, ListActivity1.class);
        startActivity(backIntent);
    }

    public void sendMsg(View view)
    {
        time_id = (EditText)findViewById(R.id.time_id);
        date_id = (EditText)findViewById(R.id.date_id);
        EditText location_id = (EditText)findViewById(R.id.location_id);

        String time = time_id.getText().toString();
        String date = date_id.getText().toString();
        String location = location_id.getText().toString();

        if(time.trim().isEmpty()|| date.trim().isEmpty()|| location.trim().isEmpty())
        {
            if(time.trim().isEmpty())
            {
                time_id.setError("Cannot be empty");
            }
            else if(date.trim().isEmpty())
            {
                date_id.setError("Cannot be empty");
            }
            else if(location.trim().isEmpty())
            {
                location_id.setError("Cannot be empty");
            }
            else if(!(isValidFormat("dd/MM/yyyy",date)))
            {
                date_id.setError("Invalid Date");
            }


        }
        else
        {
            String msgString= "You are being assisted with"+need+".\nDetails:\n Date:"+date+" Time:"+time+"\nLocation:"+location;
            Intent smsIntent = new Intent(Intent.ACTION_VIEW);
            smsIntent.setType("vnd.android-dir/mms-sms");
            smsIntent.putExtra("address", phone);
            smsIntent.putExtra("sms_body",msgString);
            Toast.makeText(DetailActivity.this,"Thank you for your donations",Toast.LENGTH_LONG).show();
            startActivity(smsIntent);
//            initializeRequestQueue(phone, msgString);

        }
    }


    public static boolean isValidFormat(String format, String value) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(value);
            if (!value.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return date != null;
    }

    private void initializeRequestQueue(String phone, String message) {


//        phone ="6692346605";
//        message="Good Morning";

        //http://aabtech.us/HFH/sendnotification.php?number=5109622263&message=test
//        String url = "http://aabtech.us/HFH/register.php?email="+email+"&name="+name+"&phone="+phone+"&password="+pass;
        String url = "http://aabtech.us/HFH/sendnotification.php?number="+phone+"&message='"+message+"'";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //TextView responseMessage = new TextView(getApplicationContext());
                        //responseMessage.setText("Response is: "+ response);
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                        if(response.matches("REG_SUCCESS") )
                        {
//                            Intent loginIntent = new Intent(DetailActivity.this, MainActivity.class);
//                            startActivity(loginIntent);
                            Toast.makeText(DetailActivity.this,"Thank you for your donation",Toast.LENGTH_LONG).show();

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetailActivity.this,"Nahi Chala",Toast.LENGTH_LONG).show();
            }
        });
        // Add the request to the RequestQueue.
        TapHelpRequestQueue.getInstance(this).addToRequestQueue(stringRequest);
    }
}
