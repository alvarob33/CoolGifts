package com.example.coolgifts;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.coolgifts.api.APIFriends;
import com.example.coolgifts.users.User;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    TextView tvNameUser;
    TextView tvUsermail;
    Button btnAddFriend;

    User user;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment(User user) {
        // Required empty public constructor
        this.user = user;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment(null);
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

        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        tvNameUser = (TextView) v.findViewById(R.id.tvNameUser);
        tvUsermail = (TextView) v.findViewById(R.id.tvUsermail);
        btnAddFriend = (Button) v.findViewById(R.id.btnAddFriend);

        tvNameUser.setText(user.getName());
        tvUsermail.setText(user.getEmail());

        btnAddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Enviar peticion de amistad
                APIFriends.sendRequestFriend(user.getId(), getActivity());
                //Hacer invisible boton para enviar peticion de amistad
                btnAddFriend.setVisibility(View.INVISIBLE);

            }
        });

        return v;
    }
}