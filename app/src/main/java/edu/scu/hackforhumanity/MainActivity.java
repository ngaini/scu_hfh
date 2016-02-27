package edu.scu.hackforhumanity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

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
        }

    }
}
