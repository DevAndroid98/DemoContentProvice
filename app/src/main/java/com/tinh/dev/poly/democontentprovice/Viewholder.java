package com.tinh.dev.poly.democontentprovice;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Viewholder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public TextView txtName;
    public TextView txtID;

    public Viewholder(View itemView) {
        super(itemView);
        imageView=itemView.findViewById(R.id.img);
        txtID=itemView.findViewById(R.id.imgid);
        txtName=itemView.findViewById(R.id.imgname);
        }
}
