package com.example.chatapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText inputEditText;
    private ChatAdapter chatAdapter;
    private List<Message> messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        recyclerView = findViewById(R.id.recyclerView);
        inputEditText = findViewById(R.id.inputEditText);

        messages = new ArrayList<>();
        chatAdapter = new ChatAdapter(messages);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(chatAdapter);

        simulateInitialChat();

        Button sendButton = findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String user1Message = inputEditText.getText().toString().trim();
                sendMessage(user1Message, true);

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable(){
                    @Override
                    public void run(){
                        String user2Message = "User2's reply";
                        sendMessage(user2Message, false);
                    }
                }, 1000);
            }
        });
    }

    private void sendMessage(String text, boolean isUser1){
        Message message = new Message(text, isUser1);
        messages.add(message);
        chatAdapter.notifyDataSetChanged();
        recyclerView.smoothScrollToPosition(messages.size() - 1);

        inputEditText.getText().clear();
    }

    private void simulateInitialChat(){
        sendMessage("Hello, this is User1", true);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                sendMessage("Hi, this is User2", false);
            }
        }, 1000);
    }
}