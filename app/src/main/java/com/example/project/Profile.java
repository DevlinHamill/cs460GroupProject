package com.example.project;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.project.activities.SignInActivity;
import com.example.project.databinding.ActivityProfileBinding;
import com.example.project.utilites.Constants;
import com.example.project.utilites.PreferenceManager;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Profile extends AppCompatActivity {
    /**
     * binds the xml to the java file
     */
    private ActivityProfileBinding binding;
    /**
     * create a preference manger object to get the current users info
     */
    private PreferenceManager preferenceManager;

    /**
     * creates the application
     * @param savedInstanceState contains the current instance of the aplicaiton
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = com.example.project.databinding.ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferenceManager = new PreferenceManager(getApplicationContext());
        loadDetails();
        setListeners();
    }

    /**
     * sets the listeners for the buttons
     */
    private void setListeners(){
        binding.backbtn.setOnClickListener(v ->
                finish()
        );
        binding.signoutbtn.setOnClickListener(v->
                signout()
        );

    }

    /**
     * loads the user details so it can be display on the profile page
     */
    private void loadDetails(){
        binding.fname.setText(preferenceManager.getString(Constants.KEY_FIRST_NAME));
        binding.LastnLabel.setText(preferenceManager.getString(Constants.KEY_LAST_NAME));
        binding.emailLabel.setText(preferenceManager.getString(Constants.KEY_EMAIL));
        /**
         * creates a byte array to store the decode image
         */
        byte[] bytes = Base64.decode(preferenceManager.getString(Constants.KEY_IMAGE), Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        binding.imageProfile.setImageBitmap(bitmap);
    }

    /**
     * signs the user out
     */
    public void signout(){
        Toast.makeText(getApplicationContext(), "Signing out...", Toast.LENGTH_SHORT).show();
        preferenceManager.clear();
        startActivity(new Intent(getApplicationContext(), SignInActivity.class));
        finish();

    }

}