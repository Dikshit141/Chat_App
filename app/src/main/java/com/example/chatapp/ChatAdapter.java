package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.Gravity;
import android.widget.LinearLayout;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MessageViewHolder> {
    private List<Message> messages;

    public ChatAdapter(List<Message> messages) {
        this.messages = messages;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_message, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Message message = messages.get(position);
        holder.bind(message);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    static class MessageViewHolder extends RecyclerView.ViewHolder {
        private TextView messageTextView;
        private LinearLayout messageContainer;

        public MessageViewHolder(@NonNull View itemView){
            super(itemView);
            messageTextView = itemView.findViewById(R.id.messageTextView);
            messageContainer = itemView.findViewById(R.id.messageContainer);
        }

        public void bind(Message message){
            messageTextView.setText(message.getText());

            int backgroundColor = message.isUser1() ? R.color.user1Background : R.color.user2Background;
            messageContainer.setBackgroundResource(backgroundColor);

            if(message.isUser1()){
                messageTextView.setGravity(Gravity.START);
            } else{
                messageTextView.setGravity(Gravity.END);
            }
        }
    }
}
