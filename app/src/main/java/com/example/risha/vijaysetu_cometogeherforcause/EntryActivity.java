package com.example.risha.vijaysetu_cometogeherforcause;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EntryActivity extends AppCompatActivity {
    EditText editTextTitle,editTextDescription,editTextPhone;
    Spinner spinnerCategory;
    Button buttonSubmit;
    DatabaseReference databaseNGO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        databaseNGO=FirebaseDatabase.getInstance().getReference("NGO");
        editTextTitle= findViewById(R.id.padd);
        editTextDescription=findViewById(R.id.editTextDescription);
        editTextPhone=findViewById(R.id.editTextPhone);
        spinnerCategory=findViewById(R.id.spinnerCategory);
        buttonSubmit=findViewById(R.id.button_submit);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addvalue();

            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sign_in_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.button_contact_us)
            startActivity(new Intent(this,ContactUsActivity.class));

        return super.onOptionsItemSelected(item);
    }
    private void addvalue()
    {
        String title= editTextTitle.getText().toString().trim();
        String category=spinnerCategory.getSelectedItem().toString();
        String description= editTextDescription.getText().toString().trim();
        String phone= editTextPhone.getText().toString().trim();
        if(!TextUtils.isEmpty(title))
        {
            if(!TextUtils.isEmpty(description)) {
                if(TextUtils.isEmpty(phone)) {phone="Phone not Provided";}

                DatabaseReference mref = databaseNGO.push();
                mref.child("NGOName").setValue(title);
                mref.child("NGOCategory").setValue(category);
                mref.child("NGODescription").setValue(description);
                mref.child("NGOPhone").setValue(phone);
                Toast.makeText(EntryActivity.this, "Added Successfully", Toast.LENGTH_LONG).show();
                clearValues();
            }

            else{
                Toast.makeText(this,"You should enter a Description",Toast.LENGTH_LONG).show();
            }

        }
        else{
            Toast.makeText(this,"You should enter a Title",Toast.LENGTH_LONG).show();
        }
    }

    private void clearValues() {
        editTextTitle.setText("");
        editTextDescription.setText("");
        editTextPhone.setText("");
    }
}
