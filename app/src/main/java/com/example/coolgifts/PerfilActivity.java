package com.example.coolgifts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.coolgifts.BasicActivity;
import com.example.coolgifts.MainActivity;
import com.example.coolgifts.MenuActivity;
import com.example.coolgifts.R;
import com.example.coolgifts.api.APIUser;
import com.example.coolgifts.api.ApiException;
import com.example.coolgifts.api.LoginToken;
import com.example.coolgifts.users.User;

public class PerfilActivity extends AppCompatActivity {

    public static final String ID_USUARIO = "ID_USUARIO";
    private int userId;

    Fragment fragment;
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        userId = getIntent().getIntExtra(ID_USUARIO, -1);
        if (userId == -1) {
            try {
                userId = LoginToken.getInstance().getId();
            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
        }
        APIUser.getUserWithId(userId, this);

        fm = getSupportFragmentManager();
        fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);


    }


    public void showUserData(User user) throws ApiException {
        //mostrar informacion de user por la activity
        if (user.getId() == -1 || user.getId() == LoginToken.getInstance().getId()) {
            //el usuario es el logueado

            if (fragment == null) {
                fragment = new PersonalProfileFragment(user);
                fm.beginTransaction()
                        .add(R.id.fragment_container, fragment)
                        .commit();
            }
        } else {
            //el usuario no es el logueado
            ProfileFragment profileFragment = new ProfileFragment(user);
            if (fragment == null) {
                fragment = new ProfileFragment(user);
                fm.beginTransaction()
                        .add(R.id.fragment_container, fragment)
                        .commit();
            }
        }
    }

    public void onClickHome(View view) {
        Intent MenuIntent = new Intent(this, MenuActivity.class);
        startActivity(MenuIntent);
    }

}