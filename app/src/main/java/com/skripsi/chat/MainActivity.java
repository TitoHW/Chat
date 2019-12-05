package com.skripsi.chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button button;
    private EditText editText;
    private AdapterChat adapterChat;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference chatReference = database.getReference().child("chat");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edCom);
        recyclerView = findViewById(R.id.rcChat);
        button = findViewById(R.id.btnSend);
        adapterChat = new AdapterChat(this);
        recyclerView.setAdapter(adapterChat);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
        chatListener();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMassge();
            }
        });
    }

    private void chatListener(){
        chatReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Chat> isiChat = new ArrayList<>();
                for (DataSnapshot data : dataSnapshot.getChildren()){
                    Chat chat = data.getValue(Chat.class);
                    isiChat.add(chat);
                    Log.d("Cek", chat.toString());
                }
                adapterChat.setData(isiChat);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void sendMassge(){
        String message = editText.getText().toString();
        Chat chat = new Chat("Tito", message);
        chatReference.push().setValue(chat);
        editText.getText().clear();
    }
}
