package edu.scu.hackforhumanity;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

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
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nathan on 2/27/2016.
 */
public class SignInActivity extends AsyncTask<String,Void , String> {


    private TextView statusField,roleField;
    private Context context;
    private int byGetOrPost = 0;

    @Override
    protected String doInBackground(String... params) {

//        String email=(String)params[0];
//        String password = (String)params[1];


        String email="abirjepatil@scu.edu";
        String password = "12345";
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
//            HttpEntity entity = response.getEntity();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    //flag 0 means get and 1 means post.(By default it is get.)
//    public SignInActivity(Context context,int flag) {
//        this.context = context; //
//        this.statusField = statusField; // logged in or not
//
//        byGetOrPost = flag;
//    }



}
