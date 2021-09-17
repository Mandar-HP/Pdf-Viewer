package com.example.pdfviewer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FileSelectListener {

    private RecyclerView recyclerViewObj;
    private MyAdapter myAdapterReff;
    private ArrayList<File> pdfList;
    private String [] permissArray = {Manifest.permission.WRITE_EXTERNAL_STORAGE};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        permissioncheck();



    }

    public void initialize(){

        recyclerViewObj = findViewById(R.id.mainActRecycler);
        recyclerViewObj.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        pdfList = new ArrayList<>();


        myAdapterReff = new MyAdapter(pdfList,this);

    }

    public void permissioncheck(){

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,permissArray,1001);
            permissioncheck();
        }
        else if (ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            initialize();
            displaypdfFiles();
        }

    }

    public ArrayList<File> findpdf (File fileObj){

        ArrayList<File> utilArrayList = new ArrayList<>();
        File [] fileArray = fileObj.listFiles();

        for (File singleFile : fileArray){
            if (singleFile.getName().endsWith(".pdf") | singleFile.getName().endsWith(".epub")){
                utilArrayList.add(singleFile);
            }
        }
        return  utilArrayList;
    }



    public void displaypdfFiles(){
        pdfList.addAll(findpdf(Environment.getExternalStorageDirectory()));
        recyclerViewObj.setAdapter(myAdapterReff);
    }


    @Override
    public void FileSelection(File fileObject) {

        Intent intntForDisplayAct = new Intent(MainActivity.this,DisplayActivity.class);
        intntForDisplayAct.putExtra("pathname",fileObject.getAbsolutePath());
        startActivity(intntForDisplayAct);

    }








}