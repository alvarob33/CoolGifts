package com.example.coolgifts;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.coolgifts.api.APIFriends;
import com.example.coolgifts.api.APIUser;
import com.example.coolgifts.api.ApiException;
import com.example.coolgifts.api.LoginToken;
import com.example.coolgifts.users.User;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonalProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonalProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    User user;

    EditText et_nameprofile;
    EditText et_mailprofile;
    EditText et_passprofile;

    Button btn_seePass;
    Button btnSaveChanges;
    Button btn_signOut;

    boolean visiblePass;


    public PersonalProfileFragment(User user) {
        // Required empty public constructor
        this.user = user;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PersonalProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonalProfileFragment newInstance(String param1, String param2) {
        PersonalProfileFragment fragment = new PersonalProfileFragment(null);
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_personal_profile, container, false);

        et_nameprofile = (EditText) v.findViewById(R.id.et_nameprofile);
        et_mailprofile = (EditText) v.findViewById(R.id.et_mailprofile);
        et_passprofile = (EditText) v.findViewById(R.id.et_passprofile);

        btn_seePass = (Button) v.findViewById(R.id.btn_seePass);
        btnSaveChanges = (Button) v.findViewById(R.id.btnSaveChanges);
        btn_signOut = (Button) v.findViewById(R.id.btn_signOut);

        et_nameprofile.setText(user.getName());
        et_mailprofile.setText(user.getEmail());

        visiblePass = false;
        et_passprofile.setText(R.string.passwordnotvisible);
        et_passprofile.setFocusable(false);
        et_passprofile.setClickable(false);
        et_passprofile.setCursorVisible(false);

        btn_seePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (visiblePass) {
                    visiblePass = false;
                    et_passprofile.setText(R.string.passwordnotvisible);
                    et_passprofile.setFocusable(false);
                    et_passprofile.setClickable(false);
                    et_passprofile.setCursorVisible(false);
                } else {
                    et_passprofile.setText(user.getPassword());
                    et_passprofile.setFocusableInTouchMode(true);
                    et_passprofile.setClickable(true);
                    et_passprofile.setCursorVisible(true);
                    visiblePass = true;
                }
            }
        });


        btnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (visiblePass) {
                    //Cambiar datos usuario en api (tambien contrasena)
                    APIUser.modifUser(et_nameprofile.getText().toString(), et_mailprofile.getText().toString(), et_passprofile.getText().toString(), getActivity());
                } else {
                    //Cambiar datos usuario en api (sin contrasena)
                    APIUser.modifUser(et_nameprofile.getText().toString(), et_mailprofile.getText().toString(), user.getPassword(), getActivity());

                }
            }
        });

        btn_signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    LoginToken.getInstance().logOut();
                } catch (ApiException e) {
                    throw new RuntimeException(e);
                }
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                //Finalizamos todas las actividades y cerramos la aplicación.
                getActivity().finishAffinity();
            }
        });


        return v;
    }
}