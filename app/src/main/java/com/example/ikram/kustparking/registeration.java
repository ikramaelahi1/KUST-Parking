package com.example.ikram.kustparking;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class registeration extends AppCompatActivity {
    private FirebaseAuth mauth;
    private DatabaseReference UserRef;
    private EditText fname,email,createPass,num1;
    private Button b1;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        fname = (EditText)findViewById(R.id.name);
        email = (EditText)findViewById(R.id.mail);
        createPass = (EditText)findViewById(R.id.create);
        num1 = (EditText)findViewById(R.id.num);
        b1 = (Button)findViewById(R.id.acc);
        UserRef = FirebaseDatabase.getInstance().getReference().child("users");
        mauth = FirebaseAuth.getInstance();
        b1.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String fullName = fname.getText().toString();
                String maile = email.getText().toString();
                String crps = createPass.getText().toString();
                String nm = num1.getText().toString();
                if (fname.equals("")){
                    Toast.makeText(registeration.this, "Enter Full Name", Toast.LENGTH_SHORT).show();
                }
                if (email.equals("")){
                    Toast.makeText(registeration.this, "Enter E-Mail", Toast.LENGTH_SHORT).show();
                }
                if (createPass.equals("")){
                    Toast.makeText(registeration.this, "Enter password", Toast.LENGTH_SHORT).show();
                }
                if (num1.equals("")){
                    Toast.makeText(registeration.this, "Enter Roll Number", Toast.LENGTH_SHORT).show();
                }
                if (!TextUtils.isEmpty(fullName)&&!TextUtils.isEmpty(maile)&&!TextUtils.isEmpty(crps)&&!TextUtils.isEmpty(nm)){
                    mauth.createUserWithEmailAndPassword(maile,crps).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(registeration.this, "ACCOUNT SUCCESSFULLY ", Toast.LENGTH_SHORT).show();
                                Intent a = new Intent(getApplicationContext(),front.class);
                                startActivity(a);
                            }else
                            {
                                Toast.makeText(registeration.this, "ERROR"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
