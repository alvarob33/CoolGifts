package com.example.coolgifts;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;


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
    public void startMenu() {
        Intent loginIntent = new Intent(this, MenuActivity.class);
        startActivity(loginIntent);
    }


    //Mostrar l'error de credencials d'inici de sessió

    //Per crear una finestra Pop-Up
    private void showErrorMessageDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ERROR: Login Failed")
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }

    //Missatges d'error del quadre de diàleg

    @SuppressLint("SetTextI18n")
    public void setIncorrectPassOrMail() {
        showErrorMessageDialog("Incorrect password or email, please try again.");
    }




}