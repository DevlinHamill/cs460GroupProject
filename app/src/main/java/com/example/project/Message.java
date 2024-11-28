package com.example.project;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.adapters.ChatAdapter;
import com.example.project.databinding.ActivityMessageBinding;
import com.example.project.model.ChatMessage;
import com.example.project.utilites.Constants;
import com.example.project.utilites.PreferenceManager;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
/**
 * @author BT&T
 * CS 460
 */
public class Message extends AppCompatActivity {
    /**
     * binds the xml to the java
     */
    private ActivityMessageBinding binding;
    /**
     * keeps track of the receiving member
     */
    private Member receiverMember;
    /**
     * keeps track of all message objects
     */
    private List<ChatMessage> chatMessages;
    /**
     * creates a chat adapter object
     */
    private ChatAdapter chatAdapter;
    /**
     * create a prefrence manger object to access sign in user info
     */
    private PreferenceManager preferenceManager;
    /**
     * allows for the database to be accessed acrossed multiple methods
     */
    private FirebaseFirestore database;

    /**
     * creates the application
     * @param savedInstanceState current app instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMessageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadReceiverDetails();
        setListeners();
        init();
        listenMessage();
    }

    /**
     * Initializes Message
     */
    private void init(){
        preferenceManager = new PreferenceManager(getApplicationContext());
        chatMessages = new ArrayList<>();
        chatAdapter = new ChatAdapter(
                getBitmapFromEncodedString(receiverMember.image),
                chatMessages,
                preferenceManager.getString(Constants.KEY_USER_ID)
        );
        binding.chatRecyclerView.setAdapter(chatAdapter);
        database = FirebaseFirestore.getInstance();
    }

    /**
     * Sends the message to the Firebase
     */
    private void sendMessages(){
        /**
         * stores the current message data as a hashmap
         */
        HashMap<String, Object> message = new HashMap<>();

        message.put(Constants.KEY_SENDER_ID, preferenceManager.getString(Constants.KEY_USER_ID));
        message.put(Constants.KEY_RECIEVER_ID, receiverMember.id);

        message.put(Constants.KEY_MESSAGE, binding.inputMessage.getText().toString());
        message.put(Constants.KEY_TIMESTAMP, new Date());

        database.collection(Constants.KEY_COLLECTION_CHAT).add(message);
        binding.inputMessage.setText(null);
    }

    /**
     * Listens for messages from the Firebase
     */
    private void listenMessage(){
        database.collection(Constants.KEY_COLLECTION_CHAT)
                .whereEqualTo(Constants.KEY_SENDER_ID, preferenceManager.getString(Constants.KEY_USER_ID))
                .whereEqualTo(Constants.KEY_RECIEVER_ID, receiverMember.id)
                .addSnapshotListener(eventListener);

        database.collection(Constants.KEY_COLLECTION_CHAT)
                .whereEqualTo(Constants.KEY_SENDER_ID, receiverMember.id)
                .whereEqualTo(Constants.KEY_RECIEVER_ID, preferenceManager.getString(Constants.KEY_USER_ID))
                .addSnapshotListener(eventListener);
    }

    /**
     * intializes an event listener for the overall members
     */
    private final EventListener<QuerySnapshot> eventListener = ((value, error) -> {
        if(error != null){
            return;
        }
        if(value != null){
            /**
             * saves the amount of chat messages as a integer
             */
            int count = chatMessages.size();
            for(DocumentChange documentChange: value.getDocumentChanges()){
                if(documentChange.getType() == DocumentChange.Type.ADDED){
                    ChatMessage chatMessage = new ChatMessage();
                    chatMessage.senderId = documentChange.getDocument().getString(Constants.KEY_SENDER_ID);
                    chatMessage.recieverId = documentChange.getDocument().getString(Constants.KEY_RECIEVER_ID);
                    chatMessage.message = documentChange.getDocument().getString(Constants.KEY_MESSAGE);
                    chatMessage.dateTime = getReadableDateTime(
                            documentChange.getDocument().getDate(Constants.KEY_TIMESTAMP));

                    chatMessage.dateObject = documentChange.getDocument().getDate(Constants.KEY_TIMESTAMP);
                    chatMessages.add(chatMessage);

                }
            }
            Collections.sort(chatMessages, (obj1, obj2) -> obj1.dateObject.compareTo(obj2.dateObject));
            if(count == 0){
                chatAdapter.notifyDataSetChanged();
            } else {
                chatAdapter.notifyItemRangeChanged(chatMessages.size(), chatMessages.size());

                binding.chatRecyclerView.smoothScrollToPosition(chatMessages.size()-1);
            }
            binding.chatRecyclerView.setVisibility(View.VISIBLE);
        }
        binding.progressBar.setVisibility(View.GONE);
    });

    /**
     * Decodes a Bitmap image
     * @param encodedImage image to be decoded
     * @return decoded image
     */
    private Bitmap getBitmapFromEncodedString(String encodedImage){
        /**
         * creates a byte array to store the decoded image
         */
        byte[] bytes = Base64.decode(encodedImage, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    /**
     * Gets data of Member reciving messages
     */
    private void loadReceiverDetails(){
        receiverMember = (Member) getIntent().getSerializableExtra(Constants.KEY_USER);
        binding.textName.setText(receiverMember.Fname+" "+ receiverMember.Lname);
    }

    /**
     * Sets up listeners
     */
    private void setListeners(){
        binding.backbtn.setOnClickListener(v ->
                onBackPressed()
        );
        binding.layoutSend.setOnClickListener(v -> sendMessages());
    }

    /**
     * Formats the data of messages
     * @param date date of message
     * @return readable date
     */
    private String getReadableDateTime(Date date){
        return new SimpleDateFormat("MMM dd, yyyy - hh:mm a",
                Locale.getDefault()).format(date);
    }
}