package com.example.coolgifts.users;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coolgifts.R;

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
        // holder.chatIcon.setImageResource(R.drawable.chat_boton); // line removed
        //holder.giftIcon.setImageResource(R.drawable.ver_regalo_boton);
        //holder.giftBoxIcon.setImageResource(R.drawable.icono_regalo);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class FriendViewHolder extends RecyclerView.ViewHolder {
        ImageView profilePicture;
        TextView fullName;
        // ImageView chatIcon; // line removed
        ImageView giftIcon;
        ImageView giftBoxIcon;

        public FriendViewHolder(@NonNull View itemView) {
            super(itemView);
            profilePicture = itemView.findViewById(R.id.profile_picture);
            fullName = itemView.findViewById(R.id.full_name);
            // chatIcon = itemView.findViewById(R.id.chat_icon); // line removed
            giftIcon = itemView.findViewById(R.id.gift_icon);
            giftBoxIcon = itemView.findViewById(R.id.gift_box_icon);
        }
    }

    public void addFriend(User user) {
        users.add(user);
        notifyDataSetChanged();
    }
}
