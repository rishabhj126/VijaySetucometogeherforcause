package com.example.risha.vijaysetu_cometogeherforcause;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
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
        editTextTitle=findViewById(R.id.editTextTitle);
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
    private void addvalue()
    {
        String title= editTextTitle.getText().toString();
        String category=spinnerCategory.getSelectedItem().toString();
        String description= editTextDescription.getText().toString();
        String phone= editTextPhone.getText().toString();
        if(!TextUtils.isEmpty(title))
        {
            if(!TextUtils.isEmpty(description))
            {
                if(!TextUtils.isEmpty(phone))
                    phone="Phone not Provided";
               String id= databaseNGO.push().getKey();
               NGO ngo=new NGO(id,title,category,description,phone);
               databaseNGO.child(id).setValue(ngo);
               Toast.makeText(this,"Added Successfully",Toast.LENGTH_LONG).show();

            }
            else{
                Toast.makeText(this,"You should enter a Description",Toast.LENGTH_LONG).show();
            }

        }
        else{
            Toast.makeText(this,"You should enter a Title",Toast.LENGTH_LONG).show();
        }
    }
}
