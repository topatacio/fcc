package com.example.user.fcc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Ayuda extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);

        String texto = "Esta es la pagina de ayuda. De momento no hay nada pero, por si te sirve de ayuda, estamos trabajando en ello. Si no te sirve... Pues no se... " +
                "Llama al 112, esta gente siempre esta dispuesta a ayudar, es más, les pagan por ello. " +
                "Tambien puedes ir a la página de contacto y enviranos un mail o llamarnos." +
                " Mira, de hecho, te voy a dejar un boton aqui debajo que te lleva alli, asi que, si quieres, púlsalo." +
                "\n \n \n O no lo hagas, no soy tu jefe...";

        ((TextView) findViewById(R.id.txtAyuda)).setText(texto);
        ((Button) findViewById(R.id.btnAyuda)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Bundle b = getIntent().getExtras();
                Intent intent = new Intent(getBaseContext(),Contacto.class);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }
}
