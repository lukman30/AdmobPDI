package com.example.pc_render.mobpdi;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.pc_render.mobpdi.config.AppController;
import com.example.pc_render.mobpdi.gilinguang.SatuIklan;
import com.example.pc_render.mobpdi.prefmanager.PrefManager;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pin extends AppCompatActivity implements View.OnClickListener{
    private InterstitialAd iklanInter;
    int success;
    int PICK_IMAGE_REQUEST = 1;
    int PICK_IMAGE_REQUEST1 = 2;
    int PICK_IMAGE_REQUEST2 = 3;

    private double latitude, longitude;
    // sesuiakan ip address laptop/pc atau ip emulator android bawaan 10.0.2.2

    // private String UPLOAD_URL = "http://www.daihatsubaru.com/android/upload_image/upload.php";
    private String UPLOAD_URL = "http://36.66.192.46/pinapp/cekapp.php";
    private String ambiltagid;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    private Uri mUri;
    private Spinner spinner;
    private String sesi;


    String tag_json_obj = "json_obj_req";
    static final int regg = 2;
    TextView btnlogin;
    private EditText email1, password1,ambillevel1;
    public static final String SATU = "PREFS_WORLD_READABLE_WRITABLE";
    public static final String KEY_SATU = "KEY_WORLD_READ_WRITE";
    private SharedPreferences prefssatu;
    public static final String SATU1 = "Prefpass";
    public static final String KEY_SATU1 = "keypass";
    private SharedPreferences prefssatu1;
    private ProgressDialog pDialog;

    //String emailambil,passwordambil;
    private String email, password,ambillevel;
    private CheckBox saveLoginCheckBox;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;
    private Spinner spinnerlevel;
    private String loginsales,stringsession;
    private EditText ambilsesion;
    String item[]={"Y","N"};
    private PrefManager prefManager;
    static final int tampil_error = 1;
    public String lo_Koneksi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Checking for first time launch - before calling setContentView()
        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.pin);


        getSupportActionBar().setElevation(0);
        btnlogin = (TextView) findViewById(R.id.btnpin);
        password1 = (EditText) findViewById(R.id.pin);


        btnlogin.setOnClickListener(this);


        iklanInter = new InterstitialAd(Pin.this);
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
        iklanInter = new InterstitialAd(Pin.this);
        iklanInter.setAdUnitId("ca-app-pub-4356985467396850/8805316003");

        AdView adView1 = (AdView) this.findViewById(R.id.adview1);
        AdRequest adRequest1 = new AdRequest.Builder().build();
        adView1.loadAd(adRequest1);

    }


            private void launchHomeScreen() {
                prefManager.setFirstTimeLaunch(false);
                startActivity(new Intent(Pin.this, com.example.pc_render.mobpdi.Menu.class));
                finish();
            }


            public void keluar() {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Anda yakin ingin keluar?")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();

                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        }).show();
            }

            public boolean onKeyDown(int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    keluar();

                }
                return super.onKeyDown(keyCode, event);
            }


            private void Pin() {
                //menampilkan progress dialog
                final ProgressDialog loading = ProgressDialog.show(this, "Verifikasi Pin...", " Mohon Tunggu...", false, false);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject jObj = new JSONObject(response);
                                    success = jObj.getInt(TAG_SUCCESS);

                                    if (success == 1) {

                                        Toast.makeText(Pin.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                                        Intent i = new Intent(Pin.this, com.example.pc_render.mobpdi.Menu.class);
                                        finish();
                                        startActivity(i);
                                        launchHomeScreen();

                                    } else {
                                        Toast.makeText(Pin.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                //menghilangkan progress dialog
                                loading.dismiss();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                //menghilangkan progress dialog
                                loading.dismiss();

                                //menampilkan toast
                                Toast.makeText(Pin.this, "Maaf Ada Kesalahan!!", Toast.LENGTH_LONG).show();

                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {
                        //membuat parameters
                        Map<String, String> params = new HashMap<String, String>();


                        params.put("cekapp", password1.getText().toString().trim());


                        return params;
                    }
                };

                AppController.getInstance().addToRequestQueue(stringRequest, tag_json_obj);
            }

    @Override
    public void onClick(View view) {
        if (password1.length() < 1) {
            Toast.makeText(getApplicationContext(), "Masukkan pin dengan benar", Toast.LENGTH_SHORT).show();
            return;
        }


        password = password1.getText().toString();

        Pin();
    }

    public void bukaIntertialIklan() {
        if (iklanInter.isLoaded()) {
            iklanInter.show();
        }
    }
}
