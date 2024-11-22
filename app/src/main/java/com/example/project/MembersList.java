package com.example.project;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.databinding.ActivityMembersListBinding;
import com.example.project.utilites.PreferenceManager;
import com.example.project.utilites.Constants;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MembersList extends AppCompatActivity {

    private ActivityMembersListBinding binding;
    private PreferenceManager preferenceManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = com.example.project.databinding.ActivityMembersListBinding.inflate(getLayoutInflater());
        preferenceManager = new PreferenceManager(getApplicationContext());
        setContentView(binding.getRoot());
        setListeners();
        getUsers();

    }

    /**
     * Sets listener for back button
     */
    private void setListeners(){
        binding.backbtn.setOnClickListener(v ->
                finish()
        );

    }

    /**
     * Gets the collection of Members from the Firebase Database
     */
    private void getUsers(){
        loading(true);
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        database.collection(Constants.KEY_COLLECTION_USERS).get()
                .addOnCompleteListener(task -> {
                    loading(false);
                    String currentUserID = preferenceManager.getString(Constants.KEY_USER_ID);
                    if(task.isSuccessful() && task.getResult() != null){
                        List<Member> members = new ArrayList<>();
                        for(QueryDocumentSnapshot queryDocumentSnapshot: task.getResult()){
                            if(currentUserID.equals(queryDocumentSnapshot.getId())){
                                continue;
                            }
                            Member member = new Member();
                            member.name = queryDocumentSnapshot.getString(Constants.KEY_FIRST_NAME);

                            //member.profileImage = queryDocumentSnapshot.getString(Constants.KEY_IMAGE);
                            byte[] bytes = Base64.decode(queryDocumentSnapshot.getString(Constants.KEY_IMAGE), Base64.DEFAULT);
                            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                            member.profileImage = bitmap;

                            member.id = queryDocumentSnapshot.getString(Constants.KEY_USER_ID);
                            //member.officerStatus = queryDocumentSnapshot.getString(Constants.KEY_OFFICER_STATUS);
                            members.add(member);
                        }
                        if(members.size() > 0){
                            MemberAdapter usersAdapter = new MemberAdapter(members);
                            binding.membersRecyclerView.setAdapter(usersAdapter);
                            binding.membersRecyclerView.setVisibility(View.VISIBLE);
                        } else {
                            showErrorMessage();
                        }
                    } else {
                        showErrorMessage();
                    }
                });
    }

    /**
     * Shows an error message if there are no members in the Firebase
     */
    private void showErrorMessage(){
        binding.textErrorMessage.setText(String.format("%s", "No members available"));
        binding.textErrorMessage.setVisibility(View.VISIBLE);
    }

    /**
     * Shows a loading bar when something is loading
     * @param isLoading if something is loading
     */
    private void loading(Boolean isLoading){
        if(isLoading){
            binding.progressBar.setVisibility(View.VISIBLE);
        } else {
            binding.progressBar.setVisibility(View.INVISIBLE);
        }
    }
}