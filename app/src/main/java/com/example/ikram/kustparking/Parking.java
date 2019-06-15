package com.example.ikram.kustparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Parking extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);
        Thread time = new Thread(){

            public void run(){

                try{

                    sleep(3000);
                }catch (Exception e){

                    e.printStackTrace();
                }
                finally {
                    Intent i = new Intent(getApplicationContext(),front.class);
                    startActivity(i);
                }
            }
        };time.start();
    }
}
