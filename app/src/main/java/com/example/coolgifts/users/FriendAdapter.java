package com.example.coolgifts.users;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coolgifts.ChatActivity;
import com.example.coolgifts.FriendsActivity;
import com.example.coolgifts.MenuActivity;
import com.example.coolgifts.MessagesActivity;
import com.example.coolgifts.PerfilActivity;
import com.example.coolgifts.PresentsActivity;
import com.example.coolgifts.R;
import com.example.coolgifts.WishListActivity;
import com.example.coolgifts.api.APIPresent;

import java.util.List;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendViewHolder> {
    private List<User> users;
    private Context context;

    public FriendAdapter(List<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @NonNull
    @Override
    public FriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.friend_list_item, parent, false);
        return new FriendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendViewHolder holder, int position) {
        User user = users.get(position);
        holder.fullName.setText(user.getName());
        holder.profilePicture.setImageResource(R.drawable.marco_foto_usuario);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Intent
                Intent intent = new Intent(context, PerfilActivity.class);
                intent.putExtra(PerfilActivity.ID_USUARIO, user.getId());
                context.startActivity(intent);

            }
        });

        holder.chatIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra(PerfilActivity.ID_USUARIO, user.getId());
                context.startActivity(intent);
            }
        });

        holder.giftIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, WishListActivity.class);
                intent.putExtra(WishListActivity.INTENT_USER_ID, user.getId());
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class FriendViewHolder extends RecyclerView.ViewHolder {
        ImageView profilePicture;
        TextView fullName;
        ImageView chatIcon;
        ImageView giftIcon;
        ImageView giftBoxIcon;



        public FriendViewHolder(@NonNull View itemView) {
            super(itemView);
            profilePicture = itemView.findViewById(R.id.profile_picture);
            fullName = itemView.findViewById(R.id.full_name);
            chatIcon = itemView.findViewById(R.id.chat_icon);
            giftIcon = itemView.findViewById(R.id.gift_icon);
            giftBoxIcon = itemView.findViewById(R.id.gift_box_icon);


        }
    }

    public void addFriend(User user) {
        users.add(user);
        notifyDataSetChanged();
    }
}
