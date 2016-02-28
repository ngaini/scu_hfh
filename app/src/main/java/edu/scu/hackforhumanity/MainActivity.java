package edu.scu.hackforhumanity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    private EditText email_id;
    private EditText password_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * method for register button
     * move to registration activity
     * @param view
     */
    public void registerAction(View view)
    {
        Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(registerIntent);
    }


    public final static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }




    public void loginAction(View view)
    {
        email_id = (EditText)findViewById(R.id.login_email_editText);
        password_id = (EditText)findViewById(R.id.login_password_editText);

        String login_email = email_id.getText().toString().trim();
        String login_password = password_id.getText().toString().trim();
        // edit text empty validation
        if(login_email.isEmpty()|| login_password.isEmpty())
        {
            if(login_email.isEmpty())
            {
                email_id.setError("Cannot be empty");
            }
            else if(login_password.isEmpty())
            {
                password_id.setError("Cannot be empty");
            }
        }
        else if(!isValidEmail(login_email))
        {
            email_id.setError("Invalid format");
        }
        else
        {

            initializeRequestQueue(login_email,login_password);
////            Toast.makeText(MainActivity.this," all good", Toast.LENGTH_SHORT).show();
//            String link ="http://aabtech.us/HFH/register.php?email="+login_email+"&name=abhishek&phone=1234567&password="+login_password;
//            JsonObjectRequest request = new JsonObjectRequest(link, null,
//                    new Response.Listener<JSONObject>() {
//
//                        @Override
//                        public void onResponse(JSONObject response) {
//
////                            mTextView.setText(response.toString());
//                            Toast.makeText(MainActivity.this,response.toString(),Toast.LENGTH_LONG).show();
//                        }
//
//                    },
//
//                    new Response.ErrorListener() {
//
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
////                            mTextView.setText(error.toString());
//                            Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG).show();
//                        }
//                    }
//            );
//            VolleyApplication.getInstance().getRequestQueue().add(request);
//
////            insertIntoDb(login_email,login_password);
        }

    }

    private void initializeRequestQueue(String email,String password) {
        String url = "http://aabtech.us/HFH/login.php?email="+email+"&password="+password;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //TextView responseMessage = new TextView(getApplicationContext());
                        //responseMessage.setText("Response is: "+ response);
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                        if(response.matches("LOGIN_SUCCESS"))
                        {
                            Intent intent = new Intent(MainActivity.this,ListActivity.class );
                            startActivity(intent);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"Nahi Chala",Toast.LENGTH_LONG);
            }
        });
        // Add the request to the RequestQueue.
        TapHelpRequestQueue.getInstance(this).addToRequestQueue(stringRequest);
    }



    /**
     * insert into php database using api call
     * @param login_email
     * @param login_password
     */
    private void insertIntoDb(String login_email, String login_password) {


    }
}
