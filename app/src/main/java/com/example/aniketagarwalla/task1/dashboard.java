package com.example.aniketagarwalla.task1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.google.firebase.auth.FirebaseAuth.getInstance;

public class dashboard extends AppCompatActivity {
    FirebaseAuth mAuth;
    TextView Nam,Email;
    CircleImageView civ_profile;
    GoogleSignInClient mGoogleSignInClient;
    GoogleSignInOptions gso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mAuth = getInstance();
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount user = GoogleSignIn.getLastSignedInAccount(this);
        if (user != null) {


            Nam = findViewById(R.id.nam);
            Email = findViewById(R.id.emal);
            civ_profile=findViewById(R.id.dp);
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();

            String email = user.getEmail();
            Nam.setText(name);
            Email.setText(email);
            Glide.with(this).load(user.getPhotoUrl()).into(civ_profile);


        }
    }
}
