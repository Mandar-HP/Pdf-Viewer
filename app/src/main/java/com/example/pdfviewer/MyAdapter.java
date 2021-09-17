package com.example.pdfviewer;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private ArrayList<File> pdfFileArray;
    private FileSelectListener selectionListnerObj;



    // MATCHING CONSTRUCTOR MATCHING SUPER :

    public MyAdapter(ArrayList<File> pdfFileArray, FileSelectListener selectionListnerObj) {
        this.pdfFileArray = pdfFileArray;
        this.selectionListnerObj = selectionListnerObj;
    }


    // DEFAULT METHODS OF ADAPTER :

    @Override
    public int getItemCount() {
        return pdfFileArray.size();
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {

            return new ContentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_view,null));

    }



    // DEFAULT METHOD PROVIDED BY ADAPTER CLASS :

    @Override
    public void onBindViewHolder(@NonNull  RecyclerView.ViewHolder holder, int position) {


                ContentViewHolder viewfromcontent = (ContentViewHolder) holder;

                // DATA FROM ARRAY IS SET TO OBJECT OF RESPECTIVE CLASS :
                viewfromcontent.titletxt.setText(pdfFileArray.get(position).getName());
                viewfromcontent.contenttxt.setText(pdfFileArray.get(position).getPath());

                viewfromcontent.layoutObj.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectionListnerObj.FileSelection(pdfFileArray.get(position));
                    }
                });





    }







    // DISTINCT VIEW HOLDER CLASS :

    public class ContentViewHolder extends RecyclerView.ViewHolder {
        public TextView titletxt, contenttxt;
        public ImageView ContentImage;
        public View layoutObj;

        public ContentViewHolder(@NonNull  View itemView) {
            super(itemView);
            ContentImage = itemView.findViewById(R.id.ContentImg);
            titletxt = itemView.findViewById(R.id.contentTitle);
            contenttxt = itemView.findViewById(R.id.contentContent);
            layoutObj = itemView.findViewById(R.id.ContentLayout);

        }
    }





}


