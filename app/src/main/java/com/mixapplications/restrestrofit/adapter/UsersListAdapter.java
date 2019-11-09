package com.mixapplications.restrestrofit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mixapplications.restrestrofit.R;
import com.mixapplications.restrestrofit.model.User;
import com.mixapplications.restrestrofit.ui.UsersModelView;

import java.util.ArrayList;

public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.UserViewHolder> {
    private ArrayList<User> usersArrayList = new ArrayList<>();
    private UsersModelView usersModelView;
    private Context context;

    public UsersListAdapter(Context context , UsersModelView usersModelView) {
        this.context = context;
        this.usersModelView = usersModelView;
        usersModelView.loadPage();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(context).inflate(R.layout.user_item_view , parent ,false));
    }

    public void addUsers(ArrayList<User> users){
        usersArrayList.addAll(users);
        notifyDataSetChanged();

    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.email.setText(usersArrayList.get(position).getEmail());
        holder.firstName.setText(usersArrayList.get(position).getFirst_name());
        holder.lastName.setText(usersArrayList.get(position).getLast_name());
        Glide.with(context).load(usersArrayList.get(position).getAvatar()).into(holder.avatar);
        if(position == usersArrayList.size()-1 && usersModelView.nextPage <= usersModelView.totalPages )
            usersModelView.loadPage();
    }

    @Override
    public int getItemCount() {
        return usersArrayList.size();
    }

     class UserViewHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView firstName;
        TextView lastName;
        TextView email;
         UserViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.img_avatar);
            firstName = itemView.findViewById(R.id.tv_first_name);
            lastName = itemView.findViewById(R.id.tv_last_name);
            email = itemView.findViewById(R.id.tv_email);
        }
    }
}
