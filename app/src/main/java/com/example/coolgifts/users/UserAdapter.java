package com.example.coolgifts.users;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coolgifts.R;
import com.example.coolgifts.api.APIFriends;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> users;
    private Activity context;

    public UserAdapter(List<User> users, Activity context) {
        this.users = users;
        this.context = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_list_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = users.get(position);
        holder.name.setText(user.getName());
        // Todo: Load image into ImageView

        //Click en boton anadir amigo
        holder.ibAddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Enviar peticion de amistad
                APIFriends.sendRequestFriend(user.getId(), context);
                //Hacer invisible boton para enviar peticion de amistad
                holder.ibAddFriend.setVisibility(View.INVISIBLE);

            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView profilePicture;
        ImageButton ibAddFriend;

        UserViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            profilePicture = itemView.findViewById(R.id.profile_picture);
            ibAddFriend = itemView.findViewById(R.id.ibAddFriend);
        }
    }

    public void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }
}
