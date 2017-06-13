package com.example.user.fcc;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class VistaPDF extends AppCompatActivity {

    PDFView pdfView;
    private String nom;
    private String url="http://www.esdi.es/content/pdf/fent-memoria-2010_2011ok.pdf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_pdf);
        //Recogemos la variable
        Intent Mainact = getIntent();
        nom = Mainact.getStringExtra("nombre");
        //Distinguimos la variable dependiendo de que cardview vengamos
        if(nom.equals("Universitat")){
            url="http://www.esdi.es/content/pdf/musica-y-nuevos-espacios-de-comunicacio--769-n--digital.pdf";
        }else if (nom.equals("Escuela")){
            url="http://www.esdi.es/content/pdf/i-guia.pdf";
        }
        //Funcion para cargar un pdf de internet y mostrarlo
        new urlPdf().execute(url);
        pdfView = (PDFView) findViewById(R.id.pdfView);

        //Funcion para cargar desde un asset, para que funcione, tendriamos que crear una carpeta de assets y guardar dentro el pdf
        //pdfView.fromAsset("musica.pdf").load();


    }
    class urlPdf extends AsyncTask<String,Void,InputStream>{

        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream is = null;
            try{
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                if(urlConnection.getResponseCode()==200){
                    is=new BufferedInputStream(urlConnection.getInputStream());
                }
            }
            catch (IOException e){
                return null;
            }
            return is;
        }

        @Override
        protected void onPostExecute(InputStream is){
            pdfView.fromStream(is).load();
        }
    }
}
