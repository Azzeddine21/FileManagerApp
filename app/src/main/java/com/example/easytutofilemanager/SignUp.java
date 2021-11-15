package com.example.easytutofilemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class SignUp extends AppCompatActivity {

    TextInputEditText mTextInputEditTextUsername, mTextInputEditTextPassword, mTextInputEditTextFullname, mTextInputEditTextEmail;
    Button mButtonSignUp;
    TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mTextInputEditTextUsername = findViewById(R.id.loginSignUp_textInputLayout_username);
        mTextInputEditTextPassword = findViewById(R.id.loginSignUp_textInputLayout_password);
        mTextInputEditTextFullname = findViewById(R.id.loginSignUp_textInputLayout_fullname);
        mTextInputEditTextEmail = findViewById(R.id.loginSignUp_textInputLayout_email);
        mTextView = findViewById(R.id.loginSignUp_textView);
        mButtonSignUp = findViewById(R.id.loginSignUp_button_SignUp);

        mButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username, password, fullname, email;
                username = String.valueOf(mTextInputEditTextUsername.getText());
                password = String.valueOf(mTextInputEditTextPassword.getText());
                fullname = String.valueOf(mTextInputEditTextFullname.getText());
                email = String.valueOf(mTextInputEditTextEmail.getText());

                if(!username.equals("") && !password.equals("") && !fullname.equals("") && !email.equals("")){
                //Start ProgressBar first (Set visibility VISIBLE)
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //Starting Write and Read data with URL
                        //Creating array for parameters
                        String[] field = new String[4];
                        field[0] = "username";
                        field[1] = "password";
                        field[2] = "fullname";
                        field[3] = "email";
                        //Creating array for data
                        String[] data = new String[4];
                        data[0] = username;
                        data[1] = password;
                        data[2] = fullname;
                        data[3] = email;
                        //Envoie des donnéeees
                        PutData putData = new PutData("http://192.168.148.191/php_androidProject/signup.php", "POST", field, data);
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                String result = putData.getResult();
                                if(result.equals("Sign Up Success")){
                                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), Login.class);
                                    startActivity(intent);
                                    finish();

                                }else{
                                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                }
                                //End ProgressBar (Set visibility to GONE)
                                Log.i("PutData", result);
                            }
                        }
                        //End Write and Read data with URL
                    }
                });
                }else{
                    Toast.makeText(getApplicationContext(), "Tout les champs doivent êtres renseignés", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}