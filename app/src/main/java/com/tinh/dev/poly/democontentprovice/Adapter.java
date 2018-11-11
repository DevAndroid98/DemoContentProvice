package com.tinh.dev.poly.democontentprovice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Viewholder> {
    private Context context;
    private ArrayList<Imgmodel> strings;

    public Adapter(Context context, ArrayList<Imgmodel> strings) {
        this.context = context;
        this.strings = strings;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.cardviewimg,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
         holder.txtName.setText(strings.get(position).getName());
         holder.txtID.setText(strings.get(position).getId());
        //holder.imageView.setImageURI(Uri.parse(strings.get(position).getUri()));
        Bitmap bitmap= BitmapFactory.decodeFile(strings.get(position).getUri());
        holder.imageView.setImageBitmap(bitmap);


    }

    @Override
    public int getItemCount() {
        return strings.size();
    }
}
