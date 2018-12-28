package com.example.aniketagarwalla.task1;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.google.firebase.auth.FirebaseAuth.getInstance;

public class dashboard extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseFirestore db;
    String currentuser;
    TextView Nam,Email;
    CircleImageView civ_profile;
    GoogleSignInClient mGoogleSignInClient;
    GoogleSignInOptions gso;
    TextView easy,medium,hard;
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

            //firestore
            mAuth = FirebaseAuth.getInstance();
            db = FirebaseFirestore.getInstance();
            currentuser = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
            //brainbuzz
            easy =findViewById(R.id.scorebe);
            medium =findViewById(R.id.scorebm);
            hard =findViewById(R.id.scorebh);
            db.collection("users").document(currentuser)
                    .collection("Brainbuzz").document("score").get().addOnCompleteListener(new OnCompleteListener< DocumentSnapshot >() {

                @Override

                public void onComplete(@NonNull Task< DocumentSnapshot > task) {

                    if (task.isSuccessful())
                    {

                        DocumentSnapshot doc = task.getResult();
                        assert doc != null;
                        long es = (long) doc.get("easy");
                        long es1= (long) doc.get("easy1");
                        String sc,st;
                        sc = "easy : " + es + " out of " + es1 +" attempts";
                        easy.setText(sc);
                         es = (long) doc.get("medium");
                        es1= (long) doc.get("medium1");
                        sc = "medium : " + es + " out of " + es1 +" attempts";
                        medium.setText(sc);
                        es = (long) doc.get("hard");
                        es1= (long) doc.get("hard1");
                        sc = "hard : " + es + " out of " + es1 +" attempts";
                        hard.setText(sc);






                    }

                }

            })

                    .addOnFailureListener(new OnFailureListener() {

                        @Override

                        public void onFailure(@NonNull Exception e) {

                        }

                    });

        }


        }
    }

