package com.example.ikram.kustparking;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class front extends AppCompatActivity {
private FirebaseAuth auth;
private FirebaseUser currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);
        Button registered = (Button)findViewById(R.id.reg);
        final EditText nametext = (EditText)findViewById(R.id.email);
        final EditText passtext = (EditText)findViewById(R.id.pass);
        Button login= (Button)findViewById(R.id.login);
        auth = FirebaseAuth.getInstance();
        registered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newinten = new Intent(getApplicationContext(),registeration.class);
                startActivity(newinten);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String namet = nametext.getText().toString();
                String passt = passtext.getText().toString();
                auth.signInWithEmailAndPassword(namet,passt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(front.this, "LOGIN Successfully", Toast.LENGTH_SHORT).show();
                            Intent inte = new Intent(getApplicationContext(), vehicle.class);
                            startActivity(inte);
                        }else{

                            Toast.makeText(front.this, "Incorrect Credentials"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }


}
