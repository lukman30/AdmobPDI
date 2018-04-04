package com.example.pc_render.mobpdi.gilinguang;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.pc_render.mobpdi.IDadmob;
import com.example.pc_render.mobpdi.Menu;
import com.example.pc_render.mobpdi.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Created by PC-RENDER on 21/03/2018.
 */

public class SatuIklan extends AppCompatActivity {
    private InterstitialAd iklanInter;
    int jumlah, jumlahbannerbiasa, jumlahkedua;
    String ambil;
    private SharedPreferences idbanner1,adint1,interval;
    String ambilidbanner;
    String ambilinterval;
    int kali;



    TextView ambiljumlah;
    final Handler handler = new Handler();
    Thread waktu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.satuiklan);
        getSupportActionBar().setElevation(0);

        idbanner1 = this.getApplication().getSharedPreferences(
                IDadmob.SATU,
                Context.MODE_PRIVATE +
                        Context.MODE_PRIVATE | Context.MODE_PRIVATE);


        ambilidbanner=(idbanner1.getString(
                IDadmob.KEY_SATU, "ca-app-pub-4356985467396850/8805316003"));


        interval = this.getApplication().getSharedPreferences(
                IDadmob.INTERVAL,
                Context.MODE_PRIVATE +
                        Context.MODE_PRIVATE | Context.MODE_PRIVATE);


        ambilinterval=interval.getString(
                IDadmob.KEY_INTERVAL, "14");






        iklanInter = new InterstitialAd(SatuIklan.this);
        iklanInter.setAdUnitId("ca-app-pub-4356985467396850/8805316003");

        AdView adView = (AdView) this.findViewById(R.id.adview);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        iklanInter.loadAd(adRequest);
        iklanInter.setAdListener(new AdListener() {
            public void onAdLoaded() {
                bukaIntertialIklan();
            }
        });
//1 ----------------------------------------------------------------------------------
        iklanInter = new InterstitialAd(SatuIklan.this);
        iklanInter.setAdUnitId("ca-app-pub-4356985467396850/8805316003");

        AdView adView1 = (AdView) this.findViewById(R.id.adview1);
        AdRequest adRequest1 = new AdRequest.Builder().build();
        adView1.loadAd(adRequest1);

//2 ----------------------------------------------------------------------------------

        iklanInter = new InterstitialAd(SatuIklan.this);
        iklanInter.setAdUnitId("ca-app-pub-4356985467396850/8805316003");

        AdView adView2 = (AdView) this.findViewById(R.id.adview2);
        AdRequest adRequest2 = new AdRequest.Builder().build();
        adView2.loadAd(adRequest2);

//3 ----------------------------------------------------------------------------------

        iklanInter = new InterstitialAd(SatuIklan.this);
        iklanInter.setAdUnitId(ambilidbanner);

        AdView adView3 = (AdView) this.findViewById(R.id.adview3);
        AdRequest adRequest3 = new AdRequest.Builder().build();
        adView3.loadAd(adRequest3);

//4 ----------------------------------------------------------------------------------

        iklanInter = new InterstitialAd(SatuIklan.this);
        iklanInter.setAdUnitId(ambilidbanner);

        AdView adView4 = (AdView) this.findViewById(R.id.adview4);
        AdRequest adRequest4 = new AdRequest.Builder().build();
        adView4.loadAd(adRequest4);

//5 ----------------------------------------------------------------------------------

        iklanInter = new InterstitialAd(SatuIklan.this);
        iklanInter.setAdUnitId(ambilidbanner);

        AdView adView5 = (AdView) this.findViewById(R.id.adview5);
        AdRequest adRequest5 = new AdRequest.Builder().build();
        adView5.loadAd(adRequest5);

//6 ----------------------------------------------------------------------------------

        iklanInter = new InterstitialAd(SatuIklan.this);
        iklanInter.setAdUnitId(ambilidbanner);

        AdView adView6 = (AdView) this.findViewById(R.id.adview6);
        AdRequest adRequest6 = new AdRequest.Builder().build();
        adView6.loadAd(adRequest6);

//7 ----------------------------------------------------------------------------------

        iklanInter = new InterstitialAd(SatuIklan.this);
        iklanInter.setAdUnitId(ambilidbanner);

        AdView adView7 = (AdView) this.findViewById(R.id.adview7);
        AdRequest adRequest7 = new AdRequest.Builder().build();
        adView7.loadAd(adRequest7);

//8 ----------------------------------------------------------------------------------

        iklanInter = new InterstitialAd(SatuIklan.this);
        iklanInter.setAdUnitId(ambilidbanner);

        AdView adView8 = (AdView) this.findViewById(R.id.adview8);
        AdRequest adRequest8 = new AdRequest.Builder().build();
        adView8.loadAd(adRequest8);

//9 ----------------------------------------------------------------------------------

        iklanInter = new InterstitialAd(SatuIklan.this);
        iklanInter.setAdUnitId(ambilidbanner);

        AdView adView9 = (AdView) this.findViewById(R.id.adview9);
        AdRequest adRequest9 = new AdRequest.Builder().build();
        adView9.loadAd(adRequest9);


        ambiljumlah = (TextView) findViewById(R.id.jumlah);

        final Handler handler = new Handler();
        Intent data = getIntent();
        final int update = data.getIntExtra("cekdua", 0);
        jumlahkedua = data.getIntExtra("jumlahkedua", 10);
        System.out.println("daribanner" + jumlahkedua);

        int ainter=Integer.parseInt(ambilinterval);
        kali=ainter*1000;

        int jumlahlg = jumlahkedua + 10;
        ambiljumlah.setText(String.valueOf(jumlahlg));
        if (update == 1) {
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
            Thread waktu = new Thread() {
                public void run() {
                    try {
                        sleep(kali);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {


                        jumlah = jumlahkedua + 10;

//                        ambiljumlah.setText(String.valueOf(jumlah));
                        Intent update1 = new Intent(SatuIklan.this, SatuIklanInterest.class);
                        update1.putExtra("cekdua", 1);
                        update1.putExtra("jumlah", jumlah);
                        startActivity(update1);
                        update1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        update1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        SatuIklan.this.finish();
                    }

                }
            };
            waktu.start();


//                }
//            }, 30000L); //3000 L = 3 detik
        } else {
            jumlah = 10;
            ambiljumlah.setText(String.valueOf(jumlah));
             waktu = new Thread() {
                public void run() {
                    try {
                        sleep(kali);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {

                        Intent update = new Intent(SatuIklan.this, SatuIklanInterest.class);
                        update.putExtra("cek", 1);
                        update.putExtra("jumlah", jumlah);
                        startActivity(update);
                        update.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        update.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        SatuIklan.this.finish();

                    }

                }
            };
            waktu.start();
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    Intent update = new Intent(getApplicationContext(), SatuIklanInterest.class);
//                    update.putExtra("cek", 1);
//                    update.putExtra("jumlah", jumlah);
//                    startActivity(update);
////                Intent i = new Intent(getApplicationContext(), SatuIklanInterest.class);
////                i.putExtra("ambiljumlah", jumlah);
////                startActivity(i);
////                startActivity(new Intent(getApplicationContext(), SatuIklanInterest.class));
//                    SatuIklan.this.finish();
//
//                }
//            }, 30000L); //3000 L = 3 detik
        }
    }

//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//
//        if (bundle != null) {
//            jumlahbannerbiasa = bundle.getInt("ambiljumlahlagi");
//            System.out.println("daribanner" + jumlahbannerbiasa);
//            jumlah = 10 + jumlahbannerbiasa;
//        } else {
//            jumlah = 10;
//        }


    public void bukaIntertialIklan() {
        if (iklanInter.isLoaded()) {
            iklanInter.show();
        }
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
//            Intent intent = new Intent(SatuIklan.this, Menu.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//            finish();
//        }
//        return false;
//    }
//
//
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        Intent intent = new Intent(getApplicationContext(), Menu.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);
//    }
//
//
//    protected void onStop() {
//        super.onStop();
//        handler.removeCallbacks(waktu);
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
        Intent intent = new Intent(SatuIklan.this, Menu.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
       System.exit(0);
        super.onBackPressed();
    }
}