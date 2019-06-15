package com.example.ikram.kustparking;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class vehicle extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText n1,editTextAdd;
    private Button btnsubmit,btnAdd;
    private DatabaseReference userRef;
    private FirebaseUser mCurrentUser;
    private  int counter = 0;
    String regNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);
        n1=(EditText)findViewById(R.id.no);
        editTextAdd=(EditText)findViewById(R.id.no1);
        btnsubmit=(Button)findViewById(R.id.check);
        btnAdd=(Button)findViewById(R.id.add);

        mAuth=FirebaseAuth.getInstance();
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        final String currentUser = mCurrentUser.getUid();
        userRef = FirebaseDatabase.getInstance().getReference().child("RegNumber:");

                btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String editNumber = n1.getText().toString().trim();
                FirebaseDatabase.getInstance().getReference("RegNumber:").child(FirebaseAuth.getInstance().getUid()).child("regNumber").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String val = dataSnapshot.getValue(String.class);

                        Log.i("dxdiag", editNumber + "   " + val);
                        if (val.equals(editNumber)) {
                            sendTosbmitActivity();
                        } else {
                            sendTorejecttActivity();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regNumber = editTextAdd.getText().toString().trim();
                counter++;
                AddInfo saveInfo = new AddInfo(regNumber);

                FirebaseDatabase.getInstance().getReference("RegNumber:").child(FirebaseAuth.getInstance().getUid()).setValue(saveInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(vehicle.this, "Successfully registered", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(vehicle.this, "Error:+"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser==null){
            sendToFrontActivity();
        }
    }

    private void sendToFrontActivity() {
        Intent loginIntent = new Intent(vehicle.this,front.class);
        startActivity(loginIntent);
    }
    private void sendTosbmitActivity() {
        Intent inIntent = new Intent(vehicle.this,sbmit.class);
        startActivity(inIntent);}

    private void sendTorejecttActivity() {
            Intent ntent = new Intent(vehicle.this,reject.class);
            startActivity(ntent);
    }
}
