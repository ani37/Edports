package com.example.aniketagarwalla.task1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.google.firebase.auth.FirebaseAuth.getInstance;

public class Main extends AppCompatActivity
{
    private ActionBarDrawerToggle mToggle;
    FirebaseAuth mAuth;
    TextView Nam,Email;
    CircleImageView civ_profile;
    GoogleSignInClient mGoogleSignInClient;
    GoogleSignInOptions gso;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    DrawerLayout drawerLayout;
    ArrayList<String> alName;
    ArrayList<Integer> alImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
       Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        //auth
        mAuth = getInstance();
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount user = GoogleSignIn.getLastSignedInAccount(this);
        final NavigationView navigationView = findViewById(R.id.nav);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        int id = menuItem.getItemId();
                        Log.d("id", String.valueOf(id));
                       drawerLayout.closeDrawers();
                        if (id == R.id.Logout)
                        {
                            logout();
                            return true;
                        }
                        if (id == R.id.dashboard)
                        {
                            Intent intent = new Intent(Main.this, dashboard.class);
                            startActivity(intent);
                            return true;
                        }
                        if (id == R.id.settings)
                        {
                            Toast.makeText(Main.this, "To be updated", Toast.LENGTH_SHORT).show();
                            return true;
                        }
                        if (id == R.id.search)
                        {
                            Toast.makeText(Main.this, "To be updated", Toast.LENGTH_SHORT).show();
                            return true;
                        }
                        return true;
                    }
                });
        if (user != null) {

            View header=navigationView.getHeaderView(0);
            /*View view=navigationView.inflateHeaderView(R.layout.nav_header_main);*/
            Nam = header.findViewById(R.id.nam);
            Email = header.findViewById(R.id.emal);
            civ_profile=header.findViewById(R.id.dp);
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();

            String email = user.getEmail();
            Nam.setText(name);
            Email.setText(email);
            Glide.with(this).load(user.getPhotoUrl()).into(civ_profile);


        }

        //recycleview
        alImage = new ArrayList<>(Arrays.asList(R.drawable.cal, R.drawable.add, R.drawable.b, R.drawable.d));
        alName = new ArrayList<>(Arrays.asList( "Scientific Calculator","BrainBuzz","GuessNum!", "ARpuzz"));

        // Calling the RecyclerView
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // The number of Columns
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new HLVAdapter(Main.this, alName, alImage);
        mRecyclerView.setAdapter(mAdapter);
        //math
        Button angryButton = findViewById(R.id.angry_btn);
        angryButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, math.class);
                startActivity(intent);
            }
        });
        Button angry = findViewById(R.id.angry_bt);
        angry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, math1.class);
                startActivity(intent);
            }
        });
        Button angry1 = findViewById(R.id.angry_b);
        angry1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, math3.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        if(mToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void logout() {
        getInstance().signOut();
        Intent intent = new Intent(Main.this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

}
