package edu.scu.hackforhumanity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {


    private EditText name_id;
    private EditText email_id;
    private EditText phone_id;
    private EditText password_id;
    private EditText passwordConfirmation_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    /**
     * method for back action
     * move back to the login page
     * @param view
     */
    public void backAction(View view)
    {
        Intent backIntent= new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(backIntent);
    }

    /**
     * validation for email
     * @param target
     * @return true if the email adress is valid
     */
    public final static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }


    /**
     * minimum character validation for password
     * @param pass
     */
    public boolean minCharsValidation(String pass)
    {

//        password_id =(EditText) findViewById(R.id.register_password_editText);
//        passwordConfirmation_id= (EditText)findViewById(R.id.register_passwordConfirmation_editText);
//        pass = password_id.getText().toString().trim();
        if(TextUtils.isEmpty(pass) || pass.length() < 8)
        {
            //your edit text id
            password_id.setError("You must have x characters in your password");

            // probably return a true or false if minimum is met
            return false;
        }
        return true;
    }


    /**
     * password and confirmation fields equal check
     * @param password
     * @param confirmPassword
     * @return true if equal
     */
    public boolean checkPasswordAndConfirmPassword(String password,String confirmPassword)
    {
        boolean pstatus = false;
        if (confirmPassword != null && password != null)
        {
            if (password.equals(confirmPassword))
            {
                pstatus = true;
            }
        }
        return pstatus;
    }

    public void registerAction(View view)
    {
        //fetch all edit text values
        name_id = (EditText)findViewById(R.id.register_name_editText);
        email_id= (EditText)findViewById(R.id.register_email_editText);
        phone_id =(EditText)findViewById(R.id.register_phone_editText);
        password_id =(EditText) findViewById(R.id.register_password_editText);
        passwordConfirmation_id= (EditText)findViewById(R.id.register_passwordConfirmation_editText);

        //retrieve edit text values
        String pass = password_id.getText().toString().trim();
        String pass_confirmation = passwordConfirmation_id.getText().toString().trim();
        String phone = phone_id.getText().toString().trim();
        String name = name_id.getText().toString().trim();
        String email=email_id.getText().toString().trim();



        //if edit text empty validation
        if(name.isEmpty()||email.isEmpty()||phone.isEmpty()||pass_confirmation.isEmpty()||pass.isEmpty())
        {
            if(name.isEmpty())
            {
                name_id.setError("Invalid name");
            }
            else if((email.isEmpty()))
            {
                email_id.setError("Invalid email");
            }
            else if(phone.isEmpty())
            {
                phone_id.setError("Invalid number");
            }
            else if(pass.isEmpty())
            {
                password_id.setError("Invalid password");
            }
            else if(pass_confirmation.isEmpty())
            {
                passwordConfirmation_id.setError("Invalid Passowrd");
            }
        }
        else if(!isValidEmail(email)) //email id validation
        {
            email_id.setError("Invalid format");
        }
        else if(!minCharsValidation(pass) || !minCharsValidation(pass_confirmation))
        {
            password_id.setError("min 8 characters");
        }
        else if(!checkPasswordAndConfirmPassword(pass, pass_confirmation))
        {
            passwordConfirmation_id.setError("password does not match");
        }
        else
        {
            Toast.makeText(RegisterActivity.this," all good", Toast.LENGTH_SHORT).show();
        }





    }


}
