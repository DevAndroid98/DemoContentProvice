package com.tinh.dev.poly.democontentprovice;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
ArrayList<Imgmodel> strings;
private RecyclerView recyclerView;
private Adapter adapter;
private LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        recyclerView=findViewById(R.id.recycler_view);
        getSupportActionBar().setTitle("Album");
        strings=new ArrayList<>();
        linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        isPermissionGranted();

    }

    public void imageProvice(){

        Uri uri= MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] abc={
                MediaStore.MediaColumns.DISPLAY_NAME,
                MediaStore.MediaColumns._ID,
                MediaStore.Images.Media.DATA
        };

        Cursor cursor=getContentResolver().query(uri,abc,null,null,null);
        if (cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();
            String s="";

            while (cursor.isAfterLast()==false){
                String name=cursor.getString(0);
                String id=cursor.getString(1);
                String uriimg=cursor.getString(2);
                strings.add(new Imgmodel(uriimg,name,id));
                cursor.moveToNext();

            }
            cursor.close();
            for (int i=0;i<strings.size();i++){
                Log.e("T",strings.get(i).getName()+"");
            }
            adapter=new Adapter(this,strings);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adapter);
        }
    }
    public  boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG","Permission is granted");
                imageProvice();
                return true;
            } else {

                Log.v("TAG","Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 2);
                return false;
            }
        }
        else {
            Log.v("TAG","Permission is granted");
            return true;
        }
    }
}
