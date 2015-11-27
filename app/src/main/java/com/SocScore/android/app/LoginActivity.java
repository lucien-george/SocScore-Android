package com.SocScore.android.app;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button login;
    private Button signing_up_dialog;
    private FloatingActionButton sign_up;
    private ImageButton close_dialog_button;
    private TextView password_forgotten;
    private TextView internet_connection_error;
    private TextView error;
    private Dialog dialog = null;
    private Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_login);
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        setUpVariables();
        context = LoginActivity.this;
        dialog = new Dialog(context);
        setUpDialog();
    }

    public void setUpVariables()
    {
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.sign_in_button);
        password_forgotten = (TextView) findViewById(R.id.password_forgotten);
        internet_connection_error = (TextView) findViewById(R.id.internet_connection_error);
        error = (TextView) findViewById(R.id.error);
        sign_up = (FloatingActionButton) findViewById(R.id.sign_up);
    }

    public void setUpDialog()
    {
        dialog.setContentView(R.layout.dialog_sign_up);
        dialog.setTitle("Sign up");
        signing_up_dialog = (Button) dialog.findViewById(R.id.signing_up);
        close_dialog_button = (ImageButton) dialog.findViewById(R.id.close_dialog);
    }

    public void password_forgotten(View view)
    {
        password_forgotten.setVisibility(View.VISIBLE);
        password_forgotten.setMovementMethod(LinkMovementMethod.getInstance());
    }

    //checks for available network
    private boolean isNetworkAvailable()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if((activeNetworkInfo != null) && activeNetworkInfo.isConnected())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    //checks credentials
    public void userIsValid(String username_value, String password_value)
    {

        if (username_value.equals("") && password_value.equals(""))
        {

            goToMainActivity();
        }
        //displays error message for username or password
        else
        {
            error.setText("Incorrect Username or Password");
            password_forgotten(password_forgotten);
        }
    }



    public void goToMainActivity()
    {
        Intent mainActivity = new Intent(this , MainActivity.class);
        startActivity(mainActivity);
    }

    //When login button is clicked, checks credentials to login
    public void login(View view)
    {
        //TODO: create an http client
        login.setBackgroundResource(R.drawable.sign_in_background_selected);
        String username_value = username.getText().toString();
        String password_value = password.getText().toString();
        //checks for available network
        if (isNetworkAvailable())
        {
            //checks credentials
            userIsValid(username_value, password_value);
        }
        else
        {
            //displays error message for connection
            internet_connection_error.setText("Check Internet Connection");
        }
    }

    public void signUp(View view)
    {
        sign_up.setBackgroundResource(R.drawable.sign_in_background_selected);
        dialog.show();
    }

    public void signing_up(View view)
    {
        Intent login_page = new Intent(this , LoginActivity.class);
        startActivity(login_page);
        signing_up_dialog.setBackgroundResource(R.drawable.sign_in_background_selected);
        //TODO: create a real AnalysisViewer
    }

    public void close_dialog(View view)
    {
        dialog.dismiss();
    }
}

