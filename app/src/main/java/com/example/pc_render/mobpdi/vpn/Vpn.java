package com.example.pc_render.mobpdi.vpn;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.pc_render.mobpdi.R;

public class Vpn extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vpn);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        boolean isAppInstalled = appInstalledOrNot("com.vpn.powervpn");

        if (isAppInstalled) {
            Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("com.vpn.powervpn");
            startActivity(LaunchIntent);
            //Log.i("Aplikasi sudah install");
//            Toast.makeText(getActivity(),"sudah",Toast.LENGTH_LONG).show();
//
        } else {
//            Toast.makeText(getActivity(),"Belum",Toast.LENGTH_LONG).show();
            alert();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            // finish the activity
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void alert(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Vpn.this);


        alertDialog.setTitle("Informasi !");
        alertDialog.setMessage("Anda belum menginstall aplikasi VPN, apakah anda ingin menginstall sekarang?");
//        alertDialog.setIcon(R.drawable.lampu);
        alertDialog.setPositiveButton("Download",
                // errorDialog.setNeutralButton("Lanjut",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        download();

                    }
                });
        alertDialog.setNeutralButton("Cancel",new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

        // Showing Alert Message
        alertDialog.show();

    }

    public void download() {

        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.vpn.powervpn")));

    }
    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;

        } catch (PackageManager.NameNotFoundException e) {

        }
        return false;
    }



}

