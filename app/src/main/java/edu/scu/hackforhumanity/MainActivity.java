package edu.scu.hackforhumanity;

import android.content.Intent;
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

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

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
            Toast.makeText(MainActivity.this," all good", Toast.LENGTH_SHORT).show();


            insertIntoDb(login_email,login_password);
        }

    }

    /**
     * insert into php database using api call
     * @param login_email
     * @param login_password
     */
    private void insertIntoDb(String login_email, String login_password) {

        String email=login_email;
        String password = login_password;
//        String link = "http://aabtech.us/HFH/register.php?email="+email+"&name=abhishek&phone=1234567&password="+password;
//        Log.e("DB", ""+link);


        HttpClient client =  new DefaultHttpClient();
        HttpPost http_post = new HttpPost("http://aabtech.us/HFH/login.php?");

        //Post data
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
        nameValuePair.add(new BasicNameValuePair("email", email));
        nameValuePair.add(new BasicNameValuePair("password", password));


        try {
            //encoding POST data
            http_post.setEntity(new UrlEncodedFormEntity(nameValuePair));

            //making POST request
            HttpResponse response = client.execute(http_post);
            Log.d("Http Post Response:", response.toString());
            HttpEntity entity = response.getEntity();
            Log.e("entity", "entity value is :"+entity);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
