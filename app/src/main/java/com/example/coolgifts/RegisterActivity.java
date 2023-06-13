package com.example.coolgifts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coolgifts.api.APIUser;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class RegisterActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText repeatPasswordEditText;
    private Button registerBtn;

    private TextView passwordErrorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameEditText = findViewById(R.id.et_name);
        emailEditText = findViewById(R.id.et_mail);
        passwordEditText = findViewById(R.id.et_password);
        repeatPasswordEditText = findViewById(R.id.et_repeatPassword);
        registerBtn = findViewById(R.id.btn_login);
        passwordErrorTextView = findViewById(R.id.tv_passwordError);
        passwordEditText.addTextChangedListener(passwordTextWatcher);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nameEditText.getText().toString().length() < 1) {
                    //Comprobamos si el nombre es correcto
                    Toast.makeText(RegisterActivity.this, "The Name field is empty.", Toast.LENGTH_SHORT).show();
                } else {
                    //Comprobamos si el mail es correcto
                    if (!isValidMail(emailEditText.getText().toString())) {
                        Toast.makeText(RegisterActivity.this, "The Mail is not correct.", Toast.LENGTH_SHORT).show();
                    } else {
                        //Comprobamos si la contrasena es correcta
                        if ( !isValidPassword(passwordEditText.getText().toString()) ) {
                            Toast.makeText(RegisterActivity.this, "Password does not meet the requirements.", Toast.LENGTH_SHORT).show();
                        } else {
                            //Comprobamos si la contrasena repetida es igual a la anterior introducida
                            if (!passwordEditText.getText().toString().equals(repeatPasswordEditText.getText().toString())) {
                                Toast.makeText(RegisterActivity.this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
                            } else {
                                APIUser.register(nameEditText.getText().toString(),emailEditText.getText().toString(),passwordEditText.getText().toString(), RegisterActivity.this);
                            }
                        }
                    }
                }
            }
        });
    }

    private TextWatcher passwordTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            if ( !isValidPassword(passwordEditText.getText().toString()) ) {
                passwordErrorTextView.setText("Password needs at least 8 characters, 1 uppercase and 1 number.");
                passwordErrorTextView.setTextColor(Color.RED);
                passwordErrorTextView.setVisibility(View.VISIBLE);
            } else {
                passwordErrorTextView.setTextColor(Color.GREEN);
                passwordErrorTextView.setVisibility(View.VISIBLE);
            }
        }
    };

    /**
     * Verifica si el correo electrónico dado es válido
     * @param mail el correo electrónico a verificar
     * @return true si el correo electrónico es válido, false en caso contrario
     */
    private boolean isValidMail(String mail){
        boolean result = true;

        try {
            InternetAddress emailAddr = new InternetAddress(mail);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;

    }

    /**
     * Verifica si la contraseña dada es válida
     * @param password la contraseña a verificar
     * @return true si la contraseña es válida, false en caso contrario
     */
    private boolean isValidPassword(String password) {
        boolean isValid = false;
        if (password != null) {
            // Verifica que la contraseña tenga al menos 8 caracteres y contenga al menos una letra mayúscula y un número
            String regex = "^(?=.*[A-Z])(?=.*\\d).{8,}$";
            isValid = password.matches(regex);
        }
        return isValid;
    }
    public void startMenu(){
        Intent loginIntent = new Intent(this, MenuActivity.class);
        startActivity(loginIntent);
    }
}