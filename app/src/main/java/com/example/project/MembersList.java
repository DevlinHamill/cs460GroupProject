package com.example.project;

import android.content.Intent;
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 8db2bd477f358e9f748ac9af52e711a1880efb9a
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
<<<<<<< HEAD
=======
=======
import android.os.Bundle;
>>>>>>> 05a893865e156dc3b8a6615755e6c60f1ccfc6fa
>>>>>>> 8db2bd477f358e9f748ac9af52e711a1880efb9a

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

<<<<<<< HEAD
import com.example.project.adapters.MembersAdapter;
import com.example.project.databinding.ActivityMembersListBinding;
import com.example.project.listeners.MemberListener;
=======
import com.example.project.databinding.ActivityMembersListBinding;
<<<<<<< HEAD
>>>>>>> 8db2bd477f358e9f748ac9af52e711a1880efb9a
import com.example.project.utilites.PreferenceManager;
import com.example.project.utilites.Constants;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.HashMap;
<<<<<<< HEAD
import java.util.List;

public class MembersList extends AppCompatActivity implements MemberListener {

    private ActivityMembersListBinding binding;
=======
=======

import java.util.ArrayList;
>>>>>>> 05a893865e156dc3b8a6615755e6c60f1ccfc6fa
import java.util.List;

public class MembersList extends AppCompatActivity {

    private ActivityMembersListBinding binding;
<<<<<<< HEAD
>>>>>>> 8db2bd477f358e9f748ac9af52e711a1880efb9a
    private PreferenceManager preferenceManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

<<<<<<< HEAD
        binding = ActivityMembersListBinding.inflate(getLayoutInflater());
=======
        binding = com.example.project.databinding.ActivityMembersListBinding.inflate(getLayoutInflater());
>>>>>>> 8db2bd477f358e9f748ac9af52e711a1880efb9a
        preferenceManager = new PreferenceManager(getApplicationContext());
        setContentView(binding.getRoot());
        setListeners();
        getUsers();

    }

    /**
     * Sets listener for back button
     */
<<<<<<< HEAD
=======
=======
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*EdgeToEdge.enable(this);
        setContentView(R.layout.activity_members_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/




        binding = com.example.project.databinding.ActivityMembersListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();

        //Test data
        //Will update once project is connected to firebase
        RecyclerView memberRecyclerView = findViewById(R.id.memberRecyclerView);
        List<Member> memberList =new ArrayList<>();

        Member john = new Member();
        john.name = "John Doe";
        memberList.add(john);

        Member jane = new Member();
        jane.name = "Jane Smith";
        memberList.add(jane);

        Member ada = new Member();
        ada.name = "Ada Veum";
        memberList.add(ada);

        final MemberAdapter posterAdapter = new MemberAdapter(memberList);
        memberRecyclerView.setAdapter(posterAdapter);

    }

>>>>>>> 05a893865e156dc3b8a6615755e6c60f1ccfc6fa
>>>>>>> 8db2bd477f358e9f748ac9af52e711a1880efb9a
    private void setListeners(){
        binding.backbtn.setOnClickListener(v ->
                finish()
        );

    }
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 8db2bd477f358e9f748ac9af52e711a1880efb9a

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
<<<<<<< HEAD
                            /*byte[] bytes = Base64.decode(queryDocumentSnapshot.getString(Constants.KEY_IMAGE), Base64.DEFAULT);
                            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                            member.profileImage = bitmap;*/
                            member.image = queryDocumentSnapshot.getString(Constants.KEY_IMAGE);

                            //member.id = queryDocumentSnapshot.getString(Constants.KEY_USER_ID);
                            member.id = queryDocumentSnapshot.getId();
=======
                            byte[] bytes = Base64.decode(queryDocumentSnapshot.getString(Constants.KEY_IMAGE), Base64.DEFAULT);
                            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                            member.profileImage = bitmap;

                            member.id = queryDocumentSnapshot.getString(Constants.KEY_USER_ID);
>>>>>>> 8db2bd477f358e9f748ac9af52e711a1880efb9a
                            //member.officerStatus = queryDocumentSnapshot.getString(Constants.KEY_OFFICER_STATUS);
                            members.add(member);
                        }
                        if(members.size() > 0){
<<<<<<< HEAD
                            MembersAdapter usersAdapter = new MembersAdapter(members, this);
=======
                            MemberAdapter usersAdapter = new MemberAdapter(members);
>>>>>>> 8db2bd477f358e9f748ac9af52e711a1880efb9a
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
<<<<<<< HEAD

    /**
     * Brings user to Message activity
     * @param member member that was clicked
     */
    @Override
    public void onMemberClicked(Member member) {
        Intent intent = new Intent(getApplicationContext(), Message.class);
        intent.putExtra(Constants.KEY_USER, member);
        startActivity(intent);
        finish();
    }
=======
=======
>>>>>>> 05a893865e156dc3b8a6615755e6c60f1ccfc6fa
>>>>>>> 8db2bd477f358e9f748ac9af52e711a1880efb9a
}