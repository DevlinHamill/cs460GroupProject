package com.example.project.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.Member;
import com.example.project.databinding.ItemContainerMemberBinding;
import com.example.project.listeners.MemberListener;

import java.util.List;
/**
 * @author BT&T
 * CS 460
 */
public class MembersAdapter extends RecyclerView.Adapter<MembersAdapter.MemberViewHolder> {
    /**
     * keeps track of all members
     */
    private final List<Member> members;
    /**
     * creates a member listener object to allow for the member to be clicked on
     */
    private final MemberListener memberListener;

    /**
     * Constructor
     * @param members to be held in MemberAdapter
     */
    public MembersAdapter(List<Member> members, MemberListener memberListener) {
        this.members = members;
        this.memberListener = memberListener;
    }

    /**
     * Creates a ViewHolder
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return MemberViewHolder
     */
    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemContainerMemberBinding itemContainerMemberBinding = ItemContainerMemberBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MemberViewHolder(itemContainerMemberBinding);
    }

    /**
     * Binds ViewHolder
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull MemberViewHolder holder, int position) {
        holder.setMemberData(members.get(position));
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return members.size();
    }

    class MemberViewHolder extends RecyclerView.ViewHolder{
        /**
         * refrences the xml container for a member to be used within the recycle view
         */
        ItemContainerMemberBinding binding;

        /**
         * Constructor
         * @param itemContainerMemberBinding item to be held
         */
        public MemberViewHolder(ItemContainerMemberBinding itemContainerMemberBinding) {
            super(itemContainerMemberBinding.getRoot());
            binding = itemContainerMemberBinding;
        }

        /**
         * Sets up Member data
         * @param member Member to be set up
         */
        void setMemberData(Member member){
            binding.memberName.setText(member.Fname+ " "+ member.Lname);
            binding.imageProfile.setImageBitmap(getMemberImage(member.image));
            binding.getRoot().setOnClickListener(v -> memberListener.onMemberClicked(member));
        }
    }


    /**
     * Decodes Member's image
     * @param encodeImage Member's encoded image
     * @return Member's decoded image
     */
    private Bitmap getMemberImage(String encodeImage){
        /**
         * creates a byte array to store the decoded image info
         */
        byte [] bytes = Base64.decode(encodeImage, Base64.DEFAULT);

        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}
