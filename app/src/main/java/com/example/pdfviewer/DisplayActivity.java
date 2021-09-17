package com.example.pdfviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class DisplayActivity extends AppCompatActivity {

    PDFView pdfViewObj;
    String filepath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        init();
    }

    public void init(){

        pdfViewObj = findViewById(R.id.pdfViewXml);
        filepath = getIntent().getStringExtra("pathname");

        File fileObbj = new File(filepath);
        Uri uriOfSelectedFile = Uri.fromFile(fileObbj);

        // HERE ARGUMENT IS OF URI HENCE FILE OBJECT IS CREATED :
        pdfViewObj.fromUri(uriOfSelectedFile).swipeHorizontal(true).pageFling(true).load();



    }

}
