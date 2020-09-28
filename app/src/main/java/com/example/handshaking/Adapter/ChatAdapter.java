package com.example.handshaking.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.handshaking.Activities.Chat.ChatActivity;
import com.example.handshaking.Entity.Chat;
import com.example.handshaking.R;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {


    private ArrayList<Chat> chats;
    public ChatAdapter(ArrayList<Chat> chats) {
        this.chats = chats;
    }

    public static class ChatViewHolder extends RecyclerView.ViewHolder {

        public CardView rootView;
        public TextView chatUserName;
        public TextView chatLastSeen;
        public TextView chatLastMessage;

        public ChatViewHolder(View item){
            super(item);
            rootView = item.findViewById(R.id.chatRoot);
            chatUserName = item.findViewById(R.id.chatUserName);
            chatLastSeen = item.findViewById(R.id.chatUserLastSeen);
            chatLastMessage = item.findViewById(R.id.chatLastMessage);
        }
    }


    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_layout, parent, false);
        return new ChatViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        final Context context = holder.rootView.getContext();
        final Chat chat = this.chats.get(position);

        holder.chatUserName.setText(chat.getUserName());
        holder.chatLastSeen.setText(chat.getLastSeenDate());
        holder.chatLastMessage.setText(chat.getMessages().get(chat.getMessages().size()-1).getMessage());

        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chatDetailIntent = new Intent(context, ChatActivity.class);
                chatDetailIntent.putExtra("chat_info", chat);
                context.startActivity(chatDetailIntent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return this.chats.size();
    }
}
