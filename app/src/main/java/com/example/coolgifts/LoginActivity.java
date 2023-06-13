package com.example.coolgifts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.coolgifts.api.APIUser;

public class LoginActivity extends AppCompatActivity {

    TextView forgotPassword;
    Button loginButton;
    EditText textMail;
    EditText textPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        forgotPassword = findViewById(R.id.forgotPassword);
        loginButton = findViewById(R.id.btn_login);
        textMail = findViewById(R.id.et_mail);
        textPassword = findViewById(R.id.et_password);

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(LoginActivity.this, "Forgot Password clicked", Toast.LENGTH_SHORT).show();
                // TODO: Logica para "ForgotPassword"
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                APIUser.login(textMail.getText().toString(),textPassword.getText().toString(), LoginActivity.this);
            }
        });
    }
    public void startMenu(){
        Intent loginIntent = new Intent(this, MenuActivity.class);
        startActivity(loginIntent);
    }
}