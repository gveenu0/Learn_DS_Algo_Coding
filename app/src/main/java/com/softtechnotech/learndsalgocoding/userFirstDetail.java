package com.softtechnotech.learndsalgocoding;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class userFirstDetail extends AppCompatActivity {
    DatabaseHelper myDb;
    DatabaseReference rootRef, demoRef, demoRef1;
    Button next;
    EditText yourName, yourMobile, highestEducation, educationField, yourEmail;
    public static String strYourName, strYourMobile, strHighestEducation, strEducationField, strYourEmail;
    ProgressDialog nDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_your_details);

        myDb = new DatabaseHelper(this);

        next = findViewById(R.id.update);
        yourName = findViewById(R.id.yourName);
        yourMobile = findViewById(R.id.yourMobile);
        highestEducation = findViewById(R.id.highestEducation);
        educationField = findViewById(R.id.educationField);
        yourEmail = findViewById(R.id.yourEmail);
        //mAuth = FirebaseAuth.getInstance();

        if(registerPage.flag == 1){
            yourEmail.setText(registerPage.userEmail);
        }
        else{
            yourEmail.setText(registerPage.strUsername);
        }
        yourEmail.setEnabled(false);

        rootRef = FirebaseDatabase.getInstance().getReference();
        demoRef = rootRef.child("Users").child(registerPage.username);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nDialog = new ProgressDialog(userFirstDetail.this);
                nDialog.setMessage("Loading..");
                nDialog.setIndeterminate(false);
                nDialog.setCancelable(true);
                nDialog.show();
                strYourName = yourName.getText().toString();
                strYourMobile = yourMobile.getText().toString();
                strHighestEducation = highestEducation .getText().toString();
                strEducationField = educationField.getText().toString();
                strYourEmail = yourEmail.getText().toString();


                if(strYourName.matches("")){
                    Toast.makeText(userFirstDetail.this, "Enter your name",Toast.LENGTH_LONG).show();
                    nDialog.dismiss();
                    return;
                }
                if(strYourMobile.matches("")){
                    Toast.makeText(userFirstDetail.this, "Enter mobile no.",Toast.LENGTH_LONG).show();
                    nDialog.dismiss();
                    return;
                }
                if(strYourMobile.length() != 10){
                    Toast.makeText(userFirstDetail.this, "Enter valid mobile number",Toast.LENGTH_LONG).show();
                    nDialog.dismiss();
                    return;
                }
                if(strHighestEducation.matches("")){
                    Toast.makeText(userFirstDetail.this, "Enter highest Education",Toast.LENGTH_LONG).show();
                    nDialog.dismiss();
                    return;
                }
                if(strEducationField.matches("")){
                    Toast.makeText(userFirstDetail.this, "Enter Education field",Toast.LENGTH_LONG).show();
                    nDialog.dismiss();
                    return;
                }
                if(strYourEmail.matches("")){
                    Toast.makeText(userFirstDetail.this, "Enter email",Toast.LENGTH_LONG).show();
                    nDialog.dismiss();
                    return;
                }
                if(!(strYourEmail.matches("[a-zA-Z0-9.]+@[a-z]+\\.+[a-z]+") || strYourEmail.matches("[a-zA-Z0-9.]+@[a-z]+\\.+[a-z]+\\.+[a-z]+"))){
                    Toast.makeText(userFirstDetail.this, "Enter valid email",Toast.LENGTH_LONG).show();
                    nDialog.dismiss();
                    return;
                }

//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx Database Helper xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx//

                if(myDb.insertData(strYourName, strYourMobile, strHighestEducation, strEducationField, strYourEmail)){
                    startDsAlgoActActivity();
                }
                else{
                    Toast.makeText(userFirstDetail.this, "Error in data insertion", Toast.LENGTH_SHORT).show();
                    startSmwActivity();
                }
//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx Firebase Helper xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx//

                demoRef1 = demoRef.child("yourDetail");
                demoRef1.child("yourName").setValue(strYourName);
                demoRef1.child("yourMobile").setValue(strYourMobile);
                demoRef1.child("highestEducation ").setValue(strHighestEducation);
                demoRef1.child("educationField").setValue(strEducationField);
                demoRef1.child("yourEmail").setValue(strYourEmail);
                startDsAlgoActActivity();
            }
        });
    }
    public void startDsAlgoActActivity(){
        Intent intent = new Intent(this, successRegister.class);
        startActivity(intent);
        nDialog.dismiss();
    }
    public void startSmwActivity(){
        Intent intent = new Intent(this, somethingWentWrong.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        Toast.makeText(userFirstDetail.this, "Please fill the remaining details", Toast.LENGTH_SHORT).show();
    }
}
