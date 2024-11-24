package com.example.project;

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 8db2bd477f358e9f748ac9af52e711a1880efb9a
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.Toast;
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

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 8db2bd477f358e9f748ac9af52e711a1880efb9a
import com.example.project.activities.SignInActivity;
import com.example.project.databinding.ActivityProfileBinding;
import com.example.project.utilites.Constants;
import com.example.project.utilites.PreferenceManager;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Profile extends AppCompatActivity {
    private ActivityProfileBinding binding;
    private PreferenceManager preferenceManager;
<<<<<<< HEAD
=======
=======
import com.example.project.databinding.ActivityProfileBinding;

public class Profile extends AppCompatActivity {
    private ActivityProfileBinding binding;
>>>>>>> 05a893865e156dc3b8a6615755e6c60f1ccfc6fa
>>>>>>> 8db2bd477f358e9f748ac9af52e711a1880efb9a
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_profile);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        binding = com.example.project.databinding.ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
<<<<<<< HEAD
        preferenceManager = new PreferenceManager(getApplicationContext());
        loadDetails();
=======
<<<<<<< HEAD
        preferenceManager = new PreferenceManager(getApplicationContext());
        loadDetails();
=======
>>>>>>> 05a893865e156dc3b8a6615755e6c60f1ccfc6fa
>>>>>>> 8db2bd477f358e9f748ac9af52e711a1880efb9a
        setListeners();
    }

    private void setListeners(){
        binding.backbtn.setOnClickListener(v ->
                finish()
        );
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 8db2bd477f358e9f748ac9af52e711a1880efb9a
        binding.signoutbtn.setOnClickListener(v->
                signout()
        );

    }

    private void loadDetails(){
        binding.fname.setText(preferenceManager.getString(Constants.KEY_FIRST_NAME));
        binding.LastnLabel.setText(preferenceManager.getString(Constants.KEY_LAST_NAME));
        binding.emailLabel.setText(preferenceManager.getString(Constants.KEY_EMAIL));

        byte[] bytes = Base64.decode(preferenceManager.getString(Constants.KEY_IMAGE), Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        binding.imageProfile.setImageBitmap(bitmap);
    }


    public void signout(){
        Toast.makeText(getApplicationContext(), "Signing out...", Toast.LENGTH_SHORT).show();
        /*
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        DocumentReference documentReference = database.collection(Constants.KEY_COLLECTION_USERS)
                .document(preferenceManager.getString(Constants.KEY_USER_ID));
        */
        preferenceManager.clear();
        startActivity(new Intent(getApplicationContext(), SignInActivity.class));
        finish();



    }

<<<<<<< HEAD
=======
=======

    }
>>>>>>> 05a893865e156dc3b8a6615755e6c60f1ccfc6fa
>>>>>>> 8db2bd477f358e9f748ac9af52e711a1880efb9a
}