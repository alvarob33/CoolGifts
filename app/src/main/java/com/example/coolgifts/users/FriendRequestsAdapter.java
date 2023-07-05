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

public class FriendRequestsAdapter extends RecyclerView.Adapter<FriendRequestsAdapter.ViewHolder> {

    private List<FriendRequest> friendRequests;
    private Activity activity;

    public FriendRequestsAdapter(List<FriendRequest> friendRequests, Activity activity) {
        this.friendRequests = friendRequests;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.friend_request_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FriendRequest friendRequest = friendRequests.get(position);
        holder.username.setText(friendRequest.getUsername());
        // Set other data to the views in the item

        holder.acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Quan es selecciona la opcio Regals (WishList)
                APIFriends.acceptRequestFriend(friendRequest.getId(), activity);
                deleteFriendRequestsFromList(friendRequest);

                //todo: mostrar mensaje informando por pantalla

            }
        });

        holder.declineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Quan es selecciona la opcio Regals (WishList)
                APIFriends.delateRequestFriend(friendRequest.getId(), activity);
                deleteFriendRequestsFromList(friendRequest);

                //todo: mostrar mensaje informando por pantalla

            }
        });
    }

    @Override
    public int getItemCount() {
        return friendRequests.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView profileImage;
        public TextView username;
        public ImageButton acceptButton;
        public ImageButton declineButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.profile_image);
            username = itemView.findViewById(R.id.username);
            acceptButton = itemView.findViewById(R.id.accept_button);
            declineButton = itemView.findViewById(R.id.decline_button);
        }
    }

    public void setFriendRequests(List<FriendRequest> friendRequests) {
        this.friendRequests = friendRequests;
        notifyDataSetChanged();
    }

    public void deleteFriendRequestsFromList(FriendRequest friendRequest) {
        friendRequests.remove(friendRequest);
        notifyDataSetChanged();
    }
}
