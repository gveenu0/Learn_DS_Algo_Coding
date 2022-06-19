package com.softtechnotech.learndsalgocoding;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DsAlgoAct extends AppCompatActivity {

    ProgressDialog nDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_algo);
        TextView tv_array = findViewById(R.id.tv_array);

        tv_array.setOnClickListener(view -> {
            Intent intent = new Intent(DsAlgoAct.this, ArrayDSA.class);
            startActivity(intent);
        });


    }

    public void startSomethingWentWrongActivity(){
        Intent intent = new Intent(this, somethingWentWrong.class);
        startActivity(intent);
        nDialog.dismiss();
    }
    public void startHomeActivity(){
        Intent intent = new Intent(this, home.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        startHomeActivity();
    }

}
