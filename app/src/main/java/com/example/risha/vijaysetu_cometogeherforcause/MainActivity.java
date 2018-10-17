package com.example.risha.vijaysetu_cometogeherforcause;

import android.content.Intent;
import android.support.annotation.NonNull;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editTextEmail, editTextPassword;
    Button buttonSignIn;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        editTextEmail =findViewById(R.id.editTextEmail);
        editTextPassword =findViewById(R.id.editTextPassword);
        buttonSignIn=findViewById(R.id.button_sign_in);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.button_sign_in:
                authenticateUser();
                break;
        }
    }

    private void authenticateUser() {
        String email= editTextEmail.getText().toString();
        String password= editTextPassword.getText().toString();
        if(email.isEmpty())
        {
            editTextEmail.setError("Email is Required");
            editTextEmail.requestFocus();
            return;
        }
        else if(password.isEmpty())
        {
            editTextPassword.setError("Password is Required");
            editTextPassword.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(MainActivity.this,EntryActivity.class));
                        } else {
                            Toast.makeText(MainActivity.this, "Authentication failed.\n If you don't have a account contact us.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
}
