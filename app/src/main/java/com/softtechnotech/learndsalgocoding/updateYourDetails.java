package com.softtechnotech.learndsalgocoding;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class updateYourDetails extends AppCompatActivity {
    DatabaseHelper myDb;
    DatabaseReference rootRef, demoRef, demoRef1;
    ProgressDialog nDialog;
    Button update;
    public static int check;
    int flag = 0;
    EditText yourName, yourMobile, highestEducation, educationField, yourEmail;
    public static String stryourName, stryourMobile, strhighestEducation, streducationField, stryourEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_your_details);

        myDb = new DatabaseHelper(this);

        update = findViewById(R.id.update);
        yourName = findViewById(R.id.yourName);
        yourMobile = findViewById(R.id.yourMobile);
        highestEducation = findViewById(R.id.highestEducation);
        educationField = findViewById(R.id.educationField);
        yourEmail = findViewById(R.id.yourEmail);

        yourEmail.setText(DsAlgoAct.preEmail);
        yourEmail.setEnabled(false);

        rootRef = FirebaseDatabase.getInstance().getReference();
        String strNewyourName, strNewhighestEducation, strNewyourMobile, strNewyourEmail, strNewPincode;
        Cursor res = myDb.getAllData(DsAlgoAct.preEmail);
        if(res != null && res.getCount() > 0){
            res.moveToFirst();
            do{
                strNewyourEmail = res.getString(0);
                yourEmail.setText(strNewyourEmail);
                strNewyourName = res.getString(1);
                yourName.setText(strNewyourName);
                strNewyourMobile = res.getString(2);
                yourMobile.setText(strNewyourMobile);
                strNewhighestEducation = res.getString(3);
                highestEducation.setText(strNewhighestEducation);
                strNewPincode = res.getString(4);
                educationField.setText(strNewPincode);
                flag = 1;
            }while(res.moveToNext());
        }
        demoRef = rootRef.child("Users").child(DsAlgoAct.userName);
        demoRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("userDetail").exists()){
                    if(dataSnapshot.child("userDetail").child("yourName").exists()){
                        check = 1;
                        yourName.setText(dataSnapshot.child("userDetail").child("yourName").getValue().toString());
                    }
                    if(dataSnapshot.child("userDetail").child("yourMobile").exists()){
                        yourMobile.setText(dataSnapshot.child("userDetail").child("yourMobile").getValue().toString());
                    }
                    if(dataSnapshot.child("userDetail").child("highestEducation").exists()){
                        highestEducation.setText(dataSnapshot.child("userDetail").child("highestEducation").getValue().toString());
                    }
                    if(dataSnapshot.child("userDetail").child("educationField").exists()){
                        educationField.setText(dataSnapshot.child("userDetail").child("educationField").getValue().toString());
                    }
                    if(dataSnapshot.child("userDetail").child("yourEmail").exists()){
                        yourEmail.setText(dataSnapshot.child("userDetail").child("yourEmail").getValue().toString());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                startWrongActivity();
            }
        });


        FirebaseAuth.getInstance();
        rootRef = FirebaseDatabase.getInstance().getReference();
        demoRef = rootRef.child("Users").child(DsAlgoAct.userName);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nDialog = new ProgressDialog(updateYourDetails.this);
                nDialog.setMessage("Loading..");
                nDialog.setIndeterminate(false);
                nDialog.setCancelable(true);
                nDialog.show();
                stryourName = yourName.getText().toString();
                stryourMobile = yourMobile.getText().toString();
                strhighestEducation = highestEducation.getText().toString();
                streducationField = educationField.getText().toString();
                stryourEmail = yourEmail.getText().toString();

                if(stryourName.matches("")){
                    Toast.makeText(updateYourDetails.this, "Enter User Name",Toast.LENGTH_LONG).show();
                    nDialog.dismiss();
                    return;
                }
                if(stryourMobile.matches("")){
                    Toast.makeText(updateYourDetails.this, "Enter Mobile No.",Toast.LENGTH_LONG).show();
                    nDialog.dismiss();
                    return;
                }
                if(stryourMobile.length() != 10){
                    Toast.makeText(updateYourDetails.this, "Enter valid mobile number",Toast.LENGTH_LONG).show();
                    nDialog.dismiss();
                    return;
                }
                if(strhighestEducation.matches("")){
                    Toast.makeText(updateYourDetails.this, "Enter highest Education",Toast.LENGTH_LONG).show();
                    nDialog.dismiss();
                    return;
                }
                if(streducationField.matches("")){
                    Toast.makeText(updateYourDetails.this, "Enter Education field",Toast.LENGTH_LONG).show();
                    nDialog.dismiss();
                    return;
                }
                demoRef1 = demoRef.child("userDetail");
                demoRef1.child("yourName").setValue(stryourName);
                demoRef1.child("yourMobile").setValue(stryourMobile);
                demoRef1.child("highestEducation").setValue(strhighestEducation);
                demoRef1.child("educationField").setValue(streducationField);
                demoRef1.child("yourEmail").setValue(stryourEmail);


                //Cursor res = myDb.getInfo();
                if(flag == 1){
                    if(myDb.updateData(stryourName, stryourMobile, strhighestEducation, streducationField, stryourEmail)){
                        Toast.makeText(updateYourDetails.this, "Update Successfull", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(updateYourDetails.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        startSmwActivity();
                    }
                }
                else{
                    if(myDb.insertData(stryourName, stryourMobile, strhighestEducation, streducationField, stryourEmail)){
                        Toast.makeText(updateYourDetails.this, "Update Successfull", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(updateYourDetails.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        startSmwActivity();
                    }

                }
                startUpdateUserDetailsActivity();
            }
        });
    }
    public void startUpdateUserDetailsActivity(){
        Intent intent = new Intent(this, DsAlgoAct.class);
        Toast.makeText(updateYourDetails.this, "User details successfully updated",Toast.LENGTH_LONG).show();
        startActivity(intent);
        nDialog.dismiss();
    }
    public void startWrongActivity(){
        Intent intent = new Intent(this, wrg.class);
        startActivity(intent);
        nDialog.dismiss();
    }
    public void startSmwActivity(){
        Intent intent = new Intent(this, somethingWentWrong.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        startHomeActivity();
    }
    public void startHomeActivity(){
        Intent intent = new Intent(this, DsAlgoAct.class);
        startActivity(intent);
    }
}
