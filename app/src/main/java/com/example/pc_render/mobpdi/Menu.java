package com.example.pc_render.mobpdi;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.net.http.SslCertificate;
import android.net.http.SslError;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.pc_render.mobpdi.gilinguang.SatuIklan;
import com.example.pc_render.mobpdi.prefmanager.PrefManager;
import com.example.pc_render.mobpdi.vpn.Vpn;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

public class Menu extends AppCompatActivity implements View.OnClickListener{
    private InterstitialAd iklanInter;
    WebView web;
    private ProgressBar progress;
    Button idtuyul,interval,vpn,apk,info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.menu);
//        getSupportActionBar().setElevation(0);



//        String url = "https://tools.ip2location.com/200x200.png";
        web = (WebView)findViewById(R.id.webview);
        idtuyul=(Button) findViewById(R.id.idtuyul);
        interval=(Button) findViewById(R.id.interval);
        vpn=(Button) findViewById(R.id.vpn);
        apk=(Button) findViewById(R.id.apk);
        info=(Button) findViewById(R.id.info);
//
//        web.loadUrl(url);
        web.setWebViewClient(new MyWebViewClient());
        String url = "http://tools.ip2location.com/200x200.png/";
        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl(url); // load a web page in a web view


        idtuyul.setOnClickListener(this);
        interval.setOnClickListener(this);
        vpn.setOnClickListener(this);
        apk.setOnClickListener(this);
        info.setOnClickListener(this);

        iklanInter = new InterstitialAd(Menu.this);
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
        iklanInter = new InterstitialAd(Menu.this);
        iklanInter.setAdUnitId("ca-app-pub-4356985467396850/8805316003");

        AdView adView1 = (AdView) this.findViewById(R.id.adview1);
        AdRequest adRequest1 = new AdRequest.Builder().build();
        adView1.loadAd(adRequest1);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.idtuyul:
                finish();
                startActivity(new Intent(Menu.this,IDadmob.class));

                break;
            case R.id.interval:
                finish();
                startActivity(new Intent(Menu.this,SatuIklan.class));
                break;

            case R.id.vpn:
                finish();
                startActivity(new Intent(Menu.this,Vpn.class));
                break;

            case R.id.apk:
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://36.66.192.46/apk.apk"));
                startActivity(browserIntent);
                break;
            case R.id.info:
                finish();
                startActivity(new Intent(Menu.this,Info.class));
                break;


        }
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        SslCertificate sslCertificateServer = error.getCertificate();

        Certificate serverCert = convertSSLCertificateToCertificate(sslCertificateServer);


    }

    public static Certificate getCertificateForRawResource(int resourceId, Context context) {
        CertificateFactory cf = null;
        Certificate ca = null;
        Resources resources = context.getResources();
        InputStream caInput = resources.openRawResource(resourceId);

        try {
            cf = CertificateFactory.getInstance("X.509");
            ca = cf.generateCertificate(caInput);
        } catch (CertificateException e) {
//            Log.e(TAG, "exception", e);
        } finally {
            try {
                caInput.close();
            } catch (IOException e) {
//                Log.e(TAG, "exception", e);
            }
        }

        return ca;
    }

    public static Certificate convertSSLCertificateToCertificate(SslCertificate sslCertificate) {
        CertificateFactory cf = null;
        Certificate certificate = null;
        Bundle bundle = sslCertificate.saveState(sslCertificate);
        byte[] bytes = bundle.getByteArray("x509-certificate");

        if (bytes != null) {
            try {
                CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
                Certificate cert = certFactory.generateCertificate(new ByteArrayInputStream(bytes));
                certificate = cert;
            } catch (CertificateException e) {

            }
        }

        return certificate;

    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {

            case R.id.action_logout:




                startActivity(new Intent(Menu.this, Pin.class));
                finish();
                PrefManager prefManager = new PrefManager(getApplicationContext());
                prefManager.setFirstTimeLaunch(true);


                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void bukaIntertialIklan() {
        if (iklanInter.isLoaded()) {
            iklanInter.show();
        }
    }
    }
