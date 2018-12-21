package com.example.risha.vijaysetu_cometogeherforcause;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.example.risha.vijaysetu_cometogeherforcause.ListActivity.mInterstitialAd;

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
        buttonSignIn.setOnClickListener(this);
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sign_in_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.button_contact_us)
            mInterstitialAd.show();
            startActivity(new Intent(this,ContactUsActivity.class));

        return super.onOptionsItemSelected(item);
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
        String email= editTextEmail.getText().toString().trim();
        String password= editTextPassword.getText().toString().trim();
        if(TextUtils.isEmpty(email))
        {
            editTextEmail.setError("Email is Required");
            editTextEmail.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(password))
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
                            Intent intent=new Intent(MainActivity.this,EntryActivity.class);
                            startActivity(intent);
                        } else {
                            mInterstitialAd.show();

                            Toast.makeText(MainActivity.this, "Authentication failed.\n If you don't have a account contact us.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
}
