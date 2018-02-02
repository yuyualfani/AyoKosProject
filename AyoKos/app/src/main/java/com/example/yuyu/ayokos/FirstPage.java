package com.example.yuyu.ayokos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstPage extends AppCompatActivity {

    Button btnAdmin,btnUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        btnAdmin = (Button) findViewById(R.id.UntukAdmin);
        btnUser = (Button) findViewById(R.id.UntukUser);


        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FirstPage.this,Awal.class );
                startActivity(i);
            }
        });


        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FirstPage.this,FisrtPageUser.class );
                startActivity(i);
            }
        });

    }
}
