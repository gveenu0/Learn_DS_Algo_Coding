package com.softtechnotech.learndsalgocoding;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DsAlgoAct extends AppCompatActivity {
    com.softtechnotech.learndsalgocoding.DatabaseHelper myDb;
    DatabaseReference rootRef, demoRef;
    NavigationView nav_view;
    private FirebaseAuth mAuth;
    private SharedPreferences sharedPreferences;
    private ActionBarDrawerToggle abdt;
    private DrawerLayout dl;
    private String strPassword;
    public static int flag = 0, flagLogin = 0;
    public static String preEmail, userName,strStudentName,strStudentMobile,strHighestEducation,strEducationField,strStudentEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_algo);
        TextView tv_array = findViewById(R.id.tv_array);
        TextView tv_matrix = findViewById(R.id.tv_matrix);
        TextView tv_string= findViewById(R.id.tv_string);
        TextView tv_search_sort = findViewById(R.id.tv_search_sort);
        TextView tv_linked_list = findViewById(R.id.tv_linkedlist);
        TextView tv_tree = findViewById(R.id.tv_binary_tree);
        TextView tv_misc = findViewById(R.id.tv_binary_search_tree);
        TextView tv_greedy= findViewById(R.id.tv_greedy);
        TextView tv_back_track = findViewById(R.id.tv_back_tracking);
        TextView tv_stack_queue = findViewById(R.id.tv_stack_Qurues);
        TextView tv_heap = findViewById(R.id.tv_heap);
        TextView tv_graph = findViewById(R.id.tv_graph);
        TextView tv_trie = findViewById(R.id.tv_trie);
        TextView tv_dp = findViewById(R.id.tv_dp);
        TextView tv_bit = findViewById(R.id.tv_bit_manipulation);
        nav_view = (NavigationView)findViewById(R.id.nav_view);
        final BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.navigationView);
        myDb = new com.softtechnotech.learndsalgocoding.DatabaseHelper(this);
        mAuth = FirebaseAuth.getInstance();
        rootRef = FirebaseDatabase.getInstance().getReference();
        initDB();


        tv_array.setOnClickListener(view -> {
            Intent intent = new Intent(DsAlgoAct.this, ArrayDSA.class);
            startActivity(intent);
        });
        tv_matrix.setOnClickListener(view -> {
            Intent intent = new Intent(DsAlgoAct.this, MatrixDSA.class);
            startActivity(intent);
        });
        tv_string.setOnClickListener(view -> {
            Intent intent = new Intent(DsAlgoAct.this, StringDSA.class);
            startActivity(intent);
        });
        tv_search_sort.setOnClickListener(view -> {
            Intent intent = new Intent(DsAlgoAct.this, SearchSortDSA.class);
            startActivity(intent);
        });
        tv_linked_list.setOnClickListener(view -> {
            Intent intent = new Intent(DsAlgoAct.this, LinkedListDSA.class);
            startActivity(intent);
        });
        tv_tree.setOnClickListener(view -> {
            Intent intent = new Intent(DsAlgoAct.this, TreeDSA.class);
            startActivity(intent);
        });
        tv_misc.setOnClickListener(view -> {
            Intent intent = new Intent(DsAlgoAct.this, MiscDSA.class);
            startActivity(intent);
        });
        tv_greedy.setOnClickListener(view -> {
            Intent intent = new Intent(DsAlgoAct.this, GreedyDSA.class);
            startActivity(intent);
        });
        tv_back_track.setOnClickListener(view -> {
            Intent intent = new Intent(DsAlgoAct.this, BackTrackDSA.class);
            startActivity(intent);
        });
        tv_stack_queue.setOnClickListener(view -> {
            Intent intent = new Intent(DsAlgoAct.this, StackQueueDSA.class);
            startActivity(intent);
        });
        tv_heap.setOnClickListener(view -> {
            Intent intent = new Intent(DsAlgoAct.this, HeapDSA.class);
            startActivity(intent);
        });
        tv_graph.setOnClickListener(view -> {
            Intent intent = new Intent(DsAlgoAct.this, GraphDSA.class);
            startActivity(intent);
        });
        tv_trie.setOnClickListener(view -> {
            Intent intent = new Intent(DsAlgoAct.this, TrieDSA.class);
            startActivity(intent);
        });
        tv_dp.setOnClickListener(view -> {
            Intent intent = new Intent(DsAlgoAct.this, DpDSA.class);
            startActivity(intent);
        });
        tv_bit.setOnClickListener(view -> {
            Intent intent = new Intent(DsAlgoAct.this, BitDSA.class);
            startActivity(intent);
        });

        dl = (DrawerLayout) findViewById(R.id.dl);
        abdt = new ActionBarDrawerToggle(this,dl,R.string.Open,R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);
        dl.addDrawerListener(abdt);
        abdt.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.aboutApp){
                    startAboutActivity();
                }
                else if(id == R.id.updatePassword){
                    startUpdatePasswordActivity();
                }
                else if(id == R.id.contactUs){
                    startContactUsActivity();
                }
                else if(id == R.id.updateYourDetails){
                    startUpdateYourDetailsActivity();
                }
                else if(id == R.id.shareApp){
                    shareText();
                }
                else if(id == R.id.Logout){
                    if(flag == 1 || flagLogin == 1){
                        FirebaseAuth.getInstance().signOut();
                        startLogoutActivity();
                    }
                    else if(flag == 2 || flagLogin == 2){
                        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Preferences", 0);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("LOGIN");
                        editor.commit();
                        FirebaseAuth.getInstance().signOut();
                        startLogoutActivity();
                    }
                    else{
                        startSmwActivity();
                    }
                }
                return false;
            }
        });

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.ds_algo){
                    startDsAlgoActivity();
                }
                else if(id == R.id.python){
                    startPythonActivity();
                }
                else if(id == R.id.java){
                    startJavaActivity();
                }
                return false;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void startUpdatePasswordActivity(){
        Intent intent = new Intent(this, updatePassword.class);
        startActivity(intent);
    }
    public void startContactUsActivity(){
        Intent intent = new Intent(this, com.softtechnotech.learndsalgocoding.contactUs.class);
        startActivity(intent);
    }
    public void startAboutActivity(){
        Intent intent = new Intent(this, com.softtechnotech.learndsalgocoding.aboutPage.class);
        startActivity(intent);
    }
    public void startUpdateYourDetailsActivity(){
        Intent intent = new Intent(this, updateYourDetails.class);
        startActivity(intent);
    }
    public void  shareText(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String shareText = "Please, share with friends to support my Efforts. Thanks in Advance. Play store Download Link https://play.google.com/store/apps/details?id=com.softtechnotech.mathhandbook";
        intent.putExtra(Intent.EXTRA_TEXT,shareText);
        startActivity(Intent.createChooser(intent,"Choose sharing Method"));
    }

    public void startSmwActivity(){
        Intent intent = new Intent(this, com.softtechnotech.learndsalgocoding.somethingWentWrong.class);
        startActivity(intent);
    }

    public void startLogoutActivity(){
        flag = 0;
        flagLogin = 0;
        Intent intent = new Intent(this, com.softtechnotech.learndsalgocoding.MainActivity.class);
        startActivity(intent);
    }

    public void startJavaActivity(){
        Intent intent = new Intent(this, JavaAct.class);
        startActivity(intent);
    }
    public void startDsAlgoActivity(){
        Intent intent = new Intent(this, DsAlgoAct.class);
        startActivity(intent);
    }
    public void startPythonActivity(){
        Intent intent = new Intent(this, PythonAct.class);
        startActivity(intent);
    }

    public void initDB(){
        flagLogin = login.flag;
        flag = com.softtechnotech.learndsalgocoding.MainActivity.flag;
        sharedPreferences = getApplicationContext().getSharedPreferences("Preferences", 0);
        String login_done = sharedPreferences.getString("LOGIN", null);
        if(login_done != null){
            flag = 2;
        }
        else{
            // Check if user is signed in (non-null) and update UI accordingly.
            if(mAuth.getCurrentUser() != null){
                flag = 1;
            }
        }
        if(flag == 1 || flagLogin == 1){
            FirebaseUser currentUser = mAuth.getCurrentUser();
            assert currentUser != null;
            preEmail = currentUser.getEmail();
            userName = preEmail.replaceAll("[@.]","");
            Menu nav_menu = nav_view.getMenu();
            nav_menu.findItem(R.id.updatePassword).setVisible(false);
        }
        else if(flag == 2 || flagLogin == 2){
            sharedPreferences = getApplicationContext().getSharedPreferences("Preferences", 0);
            strPassword = sharedPreferences.getString("LOGIN", null);
            preEmail = sharedPreferences.getString("EMAIL", null);
            userName = sharedPreferences.getString("USERNAME", null);
            if(strPassword != null){
                mAuth.signInWithEmailAndPassword(preEmail, strPassword).addOnCompleteListener(DsAlgoAct.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user != null) {
                                boolean emailVerified = user.isEmailVerified();
                            }
                        }
                        else{
                            Toast.makeText(DsAlgoAct.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            else{
                startSmwActivity();
            }
        }
        else{
            Toast.makeText(DsAlgoAct.this, "Flag 1",
                    Toast.LENGTH_SHORT).show();
            startSmwActivity();
        }

//Firebase Helper and Firebase Authentication //
        sharedPreferences = getApplicationContext().getSharedPreferences("Preferences", 0);
        strStudentName = sharedPreferences.getString("CUSTOMERNAME", null);
        strStudentMobile = sharedPreferences.getString("CUSTOMERMOBILE", null);
        strHighestEducation = sharedPreferences.getString("CUSTOMERADDRESS", null);
        strEducationField = sharedPreferences.getString("CUSTOMERPINCODE", null);
        strStudentEmail = sharedPreferences.getString("CUSTOMEREMAIL", null);
        Log.d("MyTag", Integer.toString(flag));
        demoRef = rootRef.child("Users").child(userName).child("userDetail");
        demoRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("userEmail").exists()){
                    String preEmail = dataSnapshot.child("userEmail").getValue().toString();
                    sharedPreferences = getApplicationContext().getSharedPreferences("Preferences", 0);
                    strPassword = sharedPreferences.getString("LOGIN", null);
                    mAuth.signInWithEmailAndPassword(preEmail, strPassword).addOnCompleteListener(DsAlgoAct.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                if (user != null) {
                                    boolean emailVerified = user.isEmailVerified();
                                    if(!emailVerified){
                                        new AlertDialog.Builder(DsAlgoAct.this)
                                                .setTitle("Email Verification")
                                                .setMessage("Please verify your email id")
                                                .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                                                }).show();
                                    }
                                }
                            }
                            else{
                                Toast.makeText(DsAlgoAct.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

}
