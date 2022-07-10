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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class updatePassword extends AppCompatActivity {
    DatabaseReference rootRef, demoRef;
    private FirebaseAuth mAuth;

    EditText userEmail;
    Button update;
    public static String strUserEmail;
    ProgressDialog nDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        userEmail = findViewById(R.id.yourEmail);
        update = findViewById(R.id.update);

        mAuth = FirebaseAuth.getInstance();
        rootRef = FirebaseDatabase.getInstance().getReference();
        demoRef = rootRef.child("Users").child(com.softtechnotech.learndsalgocoding.DsAlgoAct.userName);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nDialog = new ProgressDialog(updatePassword.this);
                nDialog.setMessage("Loading..");
                nDialog.setIndeterminate(false);
                nDialog.setCancelable(true);
                nDialog.show();
                strUserEmail = userEmail.getText().toString();
                if(strUserEmail.isEmpty()){
                    Toast.makeText(updatePassword.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    nDialog.dismiss();
                    return;
                }
                if(com.softtechnotech.learndsalgocoding.login.strNewUsername.equals(strUserEmail)){
                    FirebaseAuth.getInstance().sendPasswordResetEmail(com.softtechnotech.learndsalgocoding.login.strNewUsername)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(updatePassword.this, "Password reset link send to your email",
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
                    Toast.makeText(updatePassword.this, "Enter correct user email",
                            Toast.LENGTH_SHORT).show();
                    nDialog.dismiss();
                }
            }
        });
    }
    public void startActivityUpdate(){
        Intent intent = new Intent(this, DsAlgoAct.class);
        startActivity(intent);
        nDialog.dismiss();
    }
    public void startSomethingWentWrongActivity(){
        Intent intent = new Intent(this, com.softtechnotech.learndsalgocoding.somethingWentWrong.class);
        startActivity(intent);
        nDialog.dismiss();
    }
}
