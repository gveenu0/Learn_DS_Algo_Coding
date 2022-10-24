package com.softtechnotech.learndsalgocoding;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.io.File;
import java.util.ArrayList;

public class JavaAct extends AppCompatActivity {

    NavigationView nav_view;
    private ActionBarDrawerToggle abdt;
    private DrawerLayout dl;
    public static int flag = DsAlgoAct.flag, flagLogin = DsAlgoAct.flagLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);
//        TextView tv_array = findViewById(R.id.tv_array);
        nav_view = (NavigationView)findViewById(R.id.nav_view);
        final BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.navigationView);


//        tv_array.setOnClickListener(view -> {
//            Intent intent = new Intent(JavaAct.this, ArrayDSA.class);
//            startActivity(intent);
//        });

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
        String shareText = "Please, share with friends to support my Efforts. Thanks in Advance. Play store Download Link https://play.google.com/store/apps/details?id=com.softtechnotech.learndsalgocoding";
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
}