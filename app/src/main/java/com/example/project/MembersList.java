package com.example.project;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.databinding.ActivityMembersListBinding;

import java.util.ArrayList;
import java.util.List;

public class MembersList extends AppCompatActivity {

    private ActivityMembersListBinding binding;
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

    private void setListeners(){
        binding.backbtn.setOnClickListener(v ->
                finish()
        );

    }
}