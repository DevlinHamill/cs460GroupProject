package com.example.project.adapters;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.databinding.ItemContainerReceivedMessageBinding;
import com.example.project.databinding.ItemContainerSentMessageBinding;
import com.example.project.model.ChatMessage;

import java.util.List;

/**
 * @author BT&T
 * CS 460
 */
public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    /**
     * bitmap that holds the receiving chat image data
     */
    private Bitmap receiverProfileImage;
    /**
     * contains all chat messages exchanged between the user and the reciever
     */
    private final List<ChatMessage> chatMessages;
    /**
     * contains the senders identification
     */
    private final String sendId;
    /**
     * dictates if the item on the recycle view is a sender message
     */
    public static final int VIEW_TYPE_SENT = 1;
    /**
     * dictates if the item on the recycle view is a reciever message
     */
    public static final int VIEW_TYPE_RECEIVED = 2;

    /**
     * Constructor of Chat Adapter
     * @param bitmap profile image bitmap
     * @param chatMessages messages sent between members
     * @param sendId sender id
     */
    public ChatAdapter(Bitmap bitmap, List<ChatMessage> chatMessages, String sendId){
        receiverProfileImage = bitmap;
        this.chatMessages = chatMessages;
        this.sendId = sendId;
    }

    /**
     * Determines if member is the sender or receiver
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return a ViewHolder based on if they are the sender or receiver
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_SENT){
            return  new SentMessageViewHolder(ItemContainerSentMessageBinding
                    .inflate(LayoutInflater.from(parent.getContext()), parent, false));
        }else {
            return  new RecieverMessageViewHolder(ItemContainerReceivedMessageBinding
                    .inflate(LayoutInflater.from(parent.getContext()), parent, false));
        }
    }

    /**
     * Sets data based on whether the member is a sender or receiver
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == VIEW_TYPE_SENT){
            ((SentMessageViewHolder)holder).setData(chatMessages.get(position));
        }else {
            ((RecieverMessageViewHolder)holder).setData(chatMessages.get(position), receiverProfileImage);
        }
    }

    /**
     * Gets the number of messages in the chat
     * @return number of messages in the chat
     */
    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    /**
     * Determines if message is being sent or received
     * @param position position to query
     * @return if message is being sent or received
     */
    @Override
    public int getItemViewType(int position){
        if(chatMessages.get(position).senderId.equals(sendId)){
            return VIEW_TYPE_SENT;
        }else {
            return VIEW_TYPE_RECEIVED;
        }
    }

    static class SentMessageViewHolder extends RecyclerView.ViewHolder{
        /**
         * connects the sent item xml to the current java file
         */
        private final ItemContainerSentMessageBinding binding;

        /**
         * Constructor of SentMessageViewHolder
         * @param itemContainerSentMessageBinding container of sent messages
         */
        public SentMessageViewHolder(ItemContainerSentMessageBinding itemContainerSentMessageBinding) {
            super(itemContainerSentMessageBinding.getRoot());
            binding = itemContainerSentMessageBinding;
        }

        /**
         * Sets up data of Member being messaged
         * @param chatMessage Message that was sent
         */
        void setData(ChatMessage chatMessage){
            binding.textMessage.setText(chatMessage.message);
            binding.textDateTime.setText(chatMessage.dateTime);
        }
    }

    static class RecieverMessageViewHolder extends RecyclerView.ViewHolder{

        /**
         * connects the reciever message binding xml of this java file
         */
        private final ItemContainerReceivedMessageBinding binding;

        /**
         * Constructor of RecieverMessageViewHolder
         * @param itemContainerReceivedMessageBinding container of received messages
         */
        public RecieverMessageViewHolder(ItemContainerReceivedMessageBinding itemContainerReceivedMessageBinding) {
            super(itemContainerReceivedMessageBinding.getRoot());
            binding = itemContainerReceivedMessageBinding;
        }

        /**
         * Sets up data of Member being messaged
         * @param chatMessage Message that was received
         * @param receiverProfileImage profile image of Member getting messages
         */
        void setData(ChatMessage chatMessage, Bitmap receiverProfileImage){
            binding.textMessage.setText(chatMessage.message);
            binding.textDateTime.setText(chatMessage.dateTime);
            binding.imageProfile.setImageBitmap(receiverProfileImage);
        }
    }
}
