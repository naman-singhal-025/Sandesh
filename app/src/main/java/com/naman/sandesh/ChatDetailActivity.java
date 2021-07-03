package com.naman.sandesh;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.naman.sandesh.Adapters.ChatAdapter;
import com.naman.sandesh.Crypt.Decode;
import com.naman.sandesh.Crypt.Encode;
import com.naman.sandesh.databinding.ActivityChatDetailBinding;
import com.naman.sandesh.models.MessagesModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

public class ChatDetailActivity extends AppCompatActivity {
    ActivityChatDetailBinding binding;
    FirebaseDatabase database;
    FirebaseAuth auth;
    private String senderRoom;
    private String receiverRoom;
    private ArrayList<MessagesModel> messagesModels;
    private ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatDetailBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        final String senderId = auth.getUid();
        String recieveId = getIntent().getStringExtra("userId");
        String userName = getIntent().getStringExtra("userName");
        String profilePic = getIntent().getStringExtra("profilePic");

        senderRoom = senderId + recieveId;
        receiverRoom = recieveId + senderId;

        binding.userName2.setText(userName);
        Picasso.get().load(profilePic).placeholder(R.drawable.avatar).into(binding.profileImage);

        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatDetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        messagesModels = new ArrayList<>();

        chatAdapter = new ChatAdapter(messagesModels, this, recieveId);
        binding.chatRecyclerView.setAdapter(chatAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setStackFromEnd(true);
        binding.chatRecyclerView.setLayoutManager(layoutManager);
//        binding.chatRecyclerView.smoothScrollToPosition(chatAdapter.getItemCount());
        RetrieveUserInfo();

//        database.getReference().child("chats")
//                .child(senderRoom)
//                .addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        messagesModels.clear();
//                        for(DataSnapshot snapshot1 : snapshot.getChildren())
//                        {
//                            MessagesModel model  = snapshot1.getValue(MessagesModel.class);
//                            model.setMessageId(snapshot1.getKey());
//                            messagesModels.add(model);
//                        }
//
//                    }

        ImageView send = findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(ChatDetailActivity.this, send);
                // Inflating popup menu from popup_menu.xml file
                popupMenu.getMenuInflater().inflate(R.menu.btn_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId())
                        {
                            case R.id.java:
                                String message = binding.etMessage.getText().toString();
                                final MessagesModel model1 = new MessagesModel(senderId, message);
                                String Emessage = Encode.encode(message);
                                Log.d("mainhoon", Decode.decode(Emessage));
                                final MessagesModel model = new MessagesModel(senderId, Emessage);

                                model.setTimestamp(new Date().getTime());
                                model1.setTimestamp(new Date().getTime());
                                binding.etMessage.setText("");
                                database.getReference().child("chats")
                                        .child(senderRoom).push().setValue(model)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                database.getReference().child("chats")
                                                        .child(receiverRoom).push()
                                                        .setValue(model)
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {

                                                            }
                                                        });
                                                   }
                                        });
                                break;
                            case R.id.kotlin:
                                break;

                            case R.id.android:
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });

//                binding.send.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String message = binding.etMessage.getText().toString();
//                final MessagesModel model = new MessagesModel(senderId, message);
//                model.setTimestamp(new Date().getTime());
//                binding.etMessage.setText("");
//
////                final  String senderRoom = senderId+recieveId;
////                final String receiverRoom = recieveId+senderId;
//
//
////                database.getReference().child("chats")
////                        .child(senderRoom)
////                        .addValueEventListener(new ValueEventListener() {
////                            @Override
////                            public void onDataChange(@NonNull DataSnapshot snapshot) {
////                                messagesModels.clear();
////                                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
////                                    MessagesModel model = snapshot1.getValue(MessagesModel.class);
////                                    model.setMessageId(snapshot1.getKey());
////                                    messagesModels.add(model);
////                                }
////                                chatAdapter.notifyDataSetChanged();
////
////                            }
////
////                            @Override
////                            public void onCancelled(@NonNull DatabaseError error) {
////
////                            }
////                        });
//
//                database.getReference().child("chats")
//                        .child(senderRoom).push().setValue(model)
//                        .addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void aVoid) {
//                                database.getReference().child("chats")
//                                        .child(receiverRoom).push()
//                                        .setValue(model)
//                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                            @Override
//                                            public void onSuccess(Void aVoid) {
//
//                                            }
//                                        });
//                            }
//                        });
//
//            }
//        });
    }

    public void RetrieveUserInfo() {
        database.getReference().child("chats")
                .child(senderRoom)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        messagesModels.clear();
                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                            MessagesModel model = snapshot1.getValue(MessagesModel.class);
                            model.setMessageId(snapshot1.getKey());
//                            model.setMessage(Decode.decode(model.getMessage()));
                            messagesModels.add(model);
                        }
                        chatAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}

