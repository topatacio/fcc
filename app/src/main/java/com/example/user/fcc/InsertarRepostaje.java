package com.example.user.fcc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static com.example.user.fcc.Mensajes.b;

public class InsertarRepostaje extends BaseActivity {

    EditText inLitros;
    EditText inKm;
    Button enviar;
    Calendar cal = Calendar.getInstance();
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_repostaje);


        inLitros = (EditText) findViewById(R.id.inLitros);
        inKm = (EditText) findViewById(R.id.inKm);
        enviar = (Button) findViewById(R.id.btnInsert);

        enviar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                insertaRepostaje("vehiculo1");

            }
        });

    }

    //metodo que se ejecuta al clickar enviar
    public void insertaRepostaje(String vehiculo) {

        if (inKm.getText().toString().equals("") || inLitros.getText().toString().equals("")) {
            Toast.makeText(this, getResources().getString(R.string.camposVacios), Toast.LENGTH_LONG).show();
        } else {

            //instancia la base de datos de firebase
            DatabaseReference dbEnviar = FirebaseDatabase.getInstance().getReference().child(vehiculo);
            //crea una lista con datos del usuario y el mensaje introducido
            Map<String, String> envio = new HashMap<>();


            //selecciona una de las 3 cajas de entrada (una por pestaña) segun la pestaña y pone el valor
            //en el array

            envio.put("litros", inLitros.getText().toString());
            envio.put("kilometros", inKm.getText().toString());
            envio.put("fechaYhora", dateFormat.format(cal.getTime()).toString());
            //vacia la caja de entrada de arrayTexto
            inLitros.setText("");
            inKm.setText("");


            //sube el arrayTexto entrado a firebase, al nodo del usuario
            //  dbVehiculos.child(txtEmail.getText().toString()).push().setValue(inChat.getText().toString());
            dbEnviar.push().setValue(envio);

        }
    }
}
