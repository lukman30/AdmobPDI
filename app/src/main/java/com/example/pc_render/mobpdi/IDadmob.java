package com.example.pc_render.mobpdi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * Created by PC-RENDER on 21/03/2018.
 */

public class IDadmob extends AppCompatActivity implements View.OnClickListener{
    Button submit;
    private SharedPreferences idbanner,idint,interval;
    public static final String SATU = "PREFS_WORLD_READABLE_WRITABLE";
    public static final String KEY_SATU = "KEY_WORLD_READ_WRITE";
    private SharedPreferences idbanner1,adint1,interval2;
    public static final String SATU1 = "Prefpass";
    public static final String KEY_SATU1 = "keypass";

    public static final String INTERVAL = "Prefpass1";
    public static final String KEY_INTERVAL = "keypass1";

    EditText ambilidbanner,ambilint,interval1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.idadmob);
        getSupportActionBar().setElevation(0);



        submit=(Button)findViewById(R.id.button3);
        ambilidbanner=(EditText)findViewById(R.id.banner);
        ambilint=(EditText)findViewById(R.id.initersial);
        interval1=(EditText)findViewById(R.id.interval1);


        idbanner1 = this.getApplication().getSharedPreferences(
                IDadmob.SATU,
                Context.MODE_PRIVATE +
                        Context.MODE_PRIVATE | Context.MODE_PRIVATE);


        ambilidbanner.setText(idbanner1.getString(
                IDadmob.KEY_SATU, "ca-app-pub-3213151724420912/6353121482"));


        adint1 = this.getApplication().getSharedPreferences(
                IDadmob.SATU1,
                Context.MODE_PRIVATE +
                        Context.MODE_PRIVATE | Context.MODE_PRIVATE);


        ambilint.setText(adint1.getString(
                IDadmob.KEY_SATU1, "ca-app-pub-3213151724420912/8787713130"));



        interval2 = this.getApplication().getSharedPreferences(
                IDadmob.INTERVAL,
                Context.MODE_PRIVATE +
                        Context.MODE_PRIVATE | Context.MODE_PRIVATE);


        interval1.setText(interval2.getString(
                IDadmob.KEY_INTERVAL, "14"));




        submit.setOnClickListener(this);






    }

    @Override
    public void onClick(View view) {



        idbanner = getSharedPreferences(IDadmob.SATU,
                Context.MODE_PRIVATE + Context.MODE_PRIVATE
                        | Context.MODE_PRIVATE);
        SharedPreferences.Editor worldReadWriteEdit = idbanner.edit();
        worldReadWriteEdit.putString(IDadmob.KEY_SATU, ambilidbanner.getText()
                .toString());
        worldReadWriteEdit.commit();



        idint = getSharedPreferences(IDadmob.SATU1,
                Context.MODE_PRIVATE + Context.MODE_PRIVATE
                        | Context.MODE_PRIVATE);
        SharedPreferences.Editor worldReadWriteEdit1 = idint.edit();
        worldReadWriteEdit1.putString(IDadmob.KEY_SATU1, ambilint.getText()
                .toString());
        worldReadWriteEdit1.commit();


        interval = getSharedPreferences(IDadmob.INTERVAL,
                Context.MODE_PRIVATE + Context.MODE_PRIVATE
                        | Context.MODE_PRIVATE);
        SharedPreferences.Editor worldReadWriteEdit12 = interval.edit();
        worldReadWriteEdit12.putString(IDadmob.KEY_INTERVAL, interval1.getText()
                .toString());
        worldReadWriteEdit12.commit();

        finish();
        startActivity(new Intent(this,Menu.class));
        Toast.makeText(getApplicationContext(),"Setting berhasil disimpan!",Toast.LENGTH_LONG).show();
    }
}