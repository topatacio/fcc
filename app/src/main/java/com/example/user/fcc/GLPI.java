package com.example.user.fcc;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class GLPI extends BaseActivity {

    private String URL;
    Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glpi);

        int color = this.obtenFondo();

        Intent Mainact = getIntent();
        b = Mainact.getExtras();
        URL = b.getString("URL");
       // Toast.makeText(this, URL, Toast.LENGTH_LONG).show();
        //trabaja con el webView del xml, simplemente le hace cargar una URL dentro de la app.
        WebView myWebView = (WebView) findViewById(R.id.webView);

        //Se usa el color obtenido anteriormente para que el fondo del webview cuadre con el de la actividad
        myWebView.setBackgroundColor(color);

        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl("http://ddt.esdi.es/cau");
    }
}
