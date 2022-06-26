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
        demoRef = rootRef.child("Invoice").child(DsAlgoAct.userName);
        demoRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("shopDetail").exists()){
                    if(dataSnapshot.child("shopDetail").child("yourName").exists()){
                        check = 1;
                        yourName.setText(dataSnapshot.child("shopDetail").child("yourName").getValue().toString());
                    }
                    if(dataSnapshot.child("shopDetail").child("yourMobile").exists()){
                        yourMobile.setText(dataSnapshot.child("shopDetail").child("yourMobile").getValue().toString());
                    }
                    if(dataSnapshot.child("shopDetail").child("highestEducation").exists()){
                        highestEducation.setText(dataSnapshot.child("shopDetail").child("highestEducation").getValue().toString());
                    }
                    if(dataSnapshot.child("shopDetail").child("educationField").exists()){
                        educationField.setText(dataSnapshot.child("shopDetail").child("educationField").getValue().toString());
                    }
                    if(dataSnapshot.child("shopDetail").child("yourEmail").exists()){
                        yourEmail.setText(dataSnapshot.child("shopDetail").child("yourEmail").getValue().toString());
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
        demoRef = rootRef.child("Invoice").child(DsAlgoAct.userName);
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
                    Toast.makeText(updateYourDetails.this, "Enter Shop Name",Toast.LENGTH_LONG).show();
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
                    Toast.makeText(updateYourDetails.this, "Enter Address",Toast.LENGTH_LONG).show();
                    nDialog.dismiss();
                    return;
                }
                if(streducationField.matches("")){
                    Toast.makeText(updateYourDetails.this, "Enter Pincode",Toast.LENGTH_LONG).show();
                    nDialog.dismiss();
                    return;
                }
                if(streducationField.length() != 6){
                    Toast.makeText(updateYourDetails.this, "Enter valid pincode number",Toast.LENGTH_LONG).show();
                    nDialog.dismiss();
                    return;
                }

                demoRef1 = demoRef.child("shopDetail");
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
                startUpdateShopDetailsActivity();
            }
        });
    }
    public void startUpdateShopDetailsActivity(){
        Intent intent = new Intent(this, DsAlgoAct.class);
        Toast.makeText(updateYourDetails.this, "Shop details successfully updated",Toast.LENGTH_LONG).show();
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
