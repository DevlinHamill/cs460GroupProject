package com.example.project;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MemeberViewHolder>{


    private List<Member> memberList;

    @NonNull
    @Override
    public MemeberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MemeberViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_member,parent,false));
    }


    @Override
    public void onBindViewHolder(@NonNull MemeberViewHolder holder, int position) {
        holder.bindMembers(memberList.get(position));
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return memberList.size();
    }

    public MemberAdapter(List<Member> memberList){
        this.memberList = memberList;
    }

    class MemeberViewHolder extends RecyclerView.ViewHolder{

        LinearLayout containerLayout;
        RoundedImageView imageProfile;
        TextView memberName;

        public MemeberViewHolder(@NonNull View itemView) {
            super(itemView);
            containerLayout = itemView.findViewById(R.id.containerLayout);
            imageProfile = itemView.findViewById(R.id.imageProfile);
            memberName = itemView.findViewById(R.id.memberName);

        }

        /**
         * Binds member to member container
         * @param member
         */
        void bindMembers(final Member member){
            //I will add more things to be binded later
            memberName.setText(member.name);
<<<<<<< HEAD
            imageProfile.setImageBitmap(member.profileImage);
=======

>>>>>>> 05a893865e156dc3b8a6615755e6c60f1ccfc6fa
        }
    }
}

