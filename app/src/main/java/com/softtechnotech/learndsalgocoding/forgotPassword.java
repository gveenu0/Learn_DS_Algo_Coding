package com.softtechnotech.learndsalgocoding;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class forgotPassword extends AppCompatActivity {
    DatabaseReference rootRef, demoRef;

    EditText userEmail;
    Button update;
    public static String strUserEmail, strNewUserEmail;
    ProgressDialog nDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        userEmail = findViewById(R.id.yourEmail);
        update = findViewById(R.id.update);

        rootRef = FirebaseDatabase.getInstance().getReference();
        demoRef = rootRef.child("Users");
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nDialog = new ProgressDialog(forgotPassword.this);
                nDialog.setMessage("Loading..");
                nDialog.setIndeterminate(false);
                nDialog.setCancelable(true);
                nDialog.show();
                strUserEmail = userEmail.getText().toString();
                strNewUserEmail = strUserEmail.replaceAll("[@.]","");
                if(strUserEmail.isEmpty()){
                    Toast.makeText(forgotPassword.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    nDialog.dismiss();
                    return;
                }
                if(!(strUserEmail.matches("[a-zA-Z0-9.]+@[a-z]+\\.+[a-z]+") || strUserEmail.matches("[a-zA-Z0-9.]+@[a-z]+\\.+[a-z]+\\.+[a-z]+"))){
                    Toast.makeText(forgotPassword.this, "Enter valid email",Toast.LENGTH_LONG).show();
                    nDialog.dismiss();
                    return;
                }
                demoRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(strNewUserEmail).exists()){
                            FirebaseAuth.getInstance().sendPasswordResetEmail(strUserEmail)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(forgotPassword.this, "Password Reset link send to your email",
                                                        Toast.LENGTH_SHORT).show();
                                                startActivityUpdate();
                                            }
                                            else{
                                                startSomethingWentWrongActivity();
                                            }
                                        }
                                    });
                        }
                        else{
                            Toast.makeText(forgotPassword.this, "Email does not registered",
                                    Toast.LENGTH_SHORT).show();
                            nDialog.dismiss();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        startSomethingWentWrongActivity();
                    }
                });
            }
        });
    }
    public void startActivityUpdate(){
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
        nDialog.dismiss();
    }
    public void startSomethingWentWrongActivity(){
        Intent intent = new Intent(this, somethingWentWrong.class);
        startActivity(intent);
        nDialog.dismiss();
    }
}
