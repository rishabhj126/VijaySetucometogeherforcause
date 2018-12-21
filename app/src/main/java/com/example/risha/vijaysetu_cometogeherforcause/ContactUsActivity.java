package com.example.risha.vijaysetu_cometogeherforcause;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ContactUsActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        findViewById(R.id.textViewMail).setOnClickListener(this);
        findViewById(R.id.textViewCall1).setOnClickListener(this);
        findViewById(R.id.textViewCall2).setOnClickListener(this);
        findViewById(R.id.textViewContactDevoloper).setOnClickListener(this);
        findViewById(R.id.textViewWebsite).setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textViewWebsite:
                String url = "http://vijaysocialwelfaresociety.org/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;
            case R.id.textViewCall1:
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+91 9300774806 "));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(intent);
                }
                break;
            case R.id.textViewCall2:
                Intent intent2 = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+91 9303229658"));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    startActivity(intent2);
                }

                break;
            case R.id.textViewContactDevoloper:
                Intent intent3 = new Intent(Intent.ACTION_SENDTO);
                intent3.setType("text/Plain");

                intent3.setData(Uri.parse("mailto:"));
                intent3.putExtra(Intent.EXTRA_EMAIL, new String[] {"rishabhj126@gmail.com"});
                intent3.putExtra(Intent.EXTRA_SUBJECT, "Regarding VijaySetu Application");

                startActivity(intent3);
            case R.id.textViewMail:
                Intent intent4 = new Intent(Intent.ACTION_SENDTO);
                intent4.setType("text/Plain");
                intent4.setData(Uri.parse("mailto:"));
                intent4.putExtra(Intent.EXTRA_EMAIL, new String[]{"contactvijay.2017@gmail.com"});
                intent4.putExtra(Intent.EXTRA_SUBJECT, "Regarding VijaySetu Application");

                startActivity(intent4);
        }
    }
}
