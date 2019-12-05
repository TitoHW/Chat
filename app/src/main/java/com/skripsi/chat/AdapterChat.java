package com.skripsi.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdapterChat extends RecyclerView.Adapter<AdapterChat.ViewHolder> {

    private Context context;
    private List<Chat> chatList;

    public AdapterChat(Context context) {
        this.context = context;
        chatList = new ArrayList<>();
    }

    public void setData(List<Chat> chat){
        chatList.clear();
        chatList.addAll(chat);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdapterChat.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_chat, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterChat.ViewHolder holder, int position) {
        holder.bindView(chatList.get(position));
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvUser, tvMass;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvUser = itemView.findViewById(R.id.tvUser);
            tvMass = itemView.findViewById(R.id.tvMass);
        }

        public void bindView(Chat chat){
            tvUser.setText(chat.getUsername());
            tvMass.setText(chat.getMessage());
        }
    }
}
