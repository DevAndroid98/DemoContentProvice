package com.tinh.dev.poly.democontentprovice;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView contacts;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contacts = findViewById(R.id.contacts);
        isPermissionGranted();
    }

    public void loadContcts(){
        //Khai báo đường dẫn tới nơi danh bạ
        Uri uri=Uri.parse("content://contacts/people");
        ArrayList<String> danhbas=new ArrayList<>();

        Cursor cursor=getContentResolver().query(uri,null,null,null,null);
        if (cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();
            while (cursor.isAfterLast()==false){
                String id=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String name=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                danhbas.add(id+"-"+name);
                cursor.moveToNext();

            }
            cursor.close();
        }
        String text="";
        for (int i=0;i<danhbas.size();i++){
            text=text+ "\n"+danhbas.get(i);
        }
        contacts.setText(text);
    }

    public  boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_CONTACTS)
                    == PackageManager.PERMISSION_GRANTED ) {
                Log.v("TAG","Permission is granted");
                loadContcts();
                return true;
            } else {

                Log.v("TAG","Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 2);

                return false;
            }
        }
        else {
            Log.v("TAG","Permission is granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {

            case 2: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Đồng ý", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Không đồng ý", Toast.LENGTH_SHORT).show();
                }
                return;
            }


        }
    }
}
