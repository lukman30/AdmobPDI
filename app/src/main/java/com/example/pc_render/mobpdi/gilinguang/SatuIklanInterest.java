package com.example.pc_render.mobpdi.gilinguang;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc_render.mobpdi.IDadmob;
import com.example.pc_render.mobpdi.Menu;
import com.example.pc_render.mobpdi.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Created by PC-RENDER on 21/03/2018.
 */

public class SatuIklanInterest extends AppCompatActivity {
    private InterstitialAd interAd;
    TextView ambiljumlah;
    int jumlah;
    private SharedPreferences idbanner1,adint1,interval;
    String ambilidbanner;
    String ambilinterval;
    int kali;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.satuiklaninteres);
        getSupportActionBar().setElevation(0);
        interAd = new InterstitialAd(this);
        interAd.setAdUnitId("ca-app-pub-3213151724420912/8787713130");

        AdRequest adRequest = new AdRequest.Builder().build();
        interAd.loadAd(adRequest);
        interAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showIntertialIklan();
            }
        });



        ambiljumlah=(TextView)findViewById(R.id.jumlah);
//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//
//        if(bundle != null){
//            jumlahbannerbiasa = bundle.getInt("ambiljumlah");
//            System.out.println("dari interest"+jumlahbannerbiasa);
//        }
        final Handler handler = new Handler();
        Intent data = getIntent();
        final int update = data.getIntExtra("cek", 0);
        jumlah = data.getIntExtra("jumlah", 0);


        System.out.println("dariinterest" + jumlah);
        ambiljumlah.setText(String.valueOf(jumlah));

//        if (jumlah==20){
//            Toast.makeText(getApplicationContext(),"NGEKLIK",Toast.LENGTH_LONG).show();
//        }


        interval = this.getApplication().getSharedPreferences(
                IDadmob.INTERVAL,
                Context.MODE_PRIVATE +
                        Context.MODE_PRIVATE | Context.MODE_PRIVATE);


        ambilinterval=interval.getString(
                IDadmob.KEY_INTERVAL, "14");
        int ainter=Integer.parseInt(ambilinterval);
        kali=ainter*1000;
        if (update == 1) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent update1 = new Intent(getApplicationContext(), SatuIklan.class);
                    update1.putExtra("cekdua", 1);
                    update1.putExtra("jumlahkedua", jumlah);
                    startActivity(update1);
                    update1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    update1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    SatuIklanInterest.this.finish();
                }
            }, kali); //3000 L = 3 detik
        } else {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent update1 = new Intent(getApplicationContext(), SatuIklan.class);
                    update1.putExtra("cekdua", 1);
                    update1.putExtra("jumlahkedua", jumlah);
                    startActivity(update1);
                    update1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    update1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    SatuIklanInterest.this.finish();
                }
            }, kali); //3000 L = 3 detik
        }

    }
    private void showIntertialIklan() {
        if (interAd.isLoaded()) {
            interAd.show();
        }
    }
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
//
//        }
//        return false;
//    }
//
//
//    @Override
//    public void onBackPressed() {
//
//        Intent intent = new Intent(getApplicationContext(), Menu.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);
//        super.onBackPressed();
//    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            onBackPressed();
        }
        return false;

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SatuIklanInterest.this, Menu.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
        System.exit(0);
        super.onBackPressed();
    }
}