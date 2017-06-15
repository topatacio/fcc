package com.example.user.fcc;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends BaseActivity {


    private static final String TAGLOG = "taglog";
    private TextView txtMarca;
    private Button btnEntrar;

    Spinner spMarca;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtMarca = (TextView) findViewById(R.id.txtMarca);
        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        spMarca = (Spinner) findViewById(R.id.spMarca);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                iniciarActivity();
            }
        });


        creaSpinnerVehiculos();
    }




    public void creaSpinnerVehiculos() {
        //definición del spinner de marcas
        //crea una lista dinamica y se carga con los elementos recibidos de la BD firebase

        DBvehiculos conn = new DBvehiculos();
        final List<String> listaMarcas = conn.listaMarcas();
     //   listaMarcas.add("caca");
    //    Log.d(TAGLOG, "----------------------- pasando6---------------" + listaMarcas.size());
        //creacion del adaptador del spinner de seleccion de vehiculo
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaMarcas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMarca.setAdapter(adapter);

        spMarca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //metodo del spinner cuando se selecciona algo
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
                       Log.d(TAGLOG, "----------------------- pasando7---------------" + listaMarcas.size());
                txtMarca.setText(parent.getItemAtPosition(position).toString());

                Toast.makeText(getApplicationContext(), "seleccionados "+spMarca.getSelectedItemPosition(), Toast.LENGTH_LONG).show();

              //       Log.d(TAGLOG, "----------------------- pasando8---------------" + listaMarcas.size());

                switch (spMarca.getSelectedItemPosition()) {
                    case 0:
                        //si se selecciona todos, oculta el boton enviar
                        btnEntrar.setVisibility(View.GONE);
                        //   Toast.makeText(getApplicationContext(), "todos seleccionados "+spCorreo.getSelectedItemPosition(), Toast.LENGTH_LONG).show();
                        break;
                    default:
                        //por defecto muestra el boton enviar
                        btnEntrar.setVisibility(View.VISIBLE);
                        //   Toast.makeText(getApplicationContext(), "otros seleccionados "+spCorreo.getSelectedItemPosition(), Toast.LENGTH_LONG).show();
                        break;
                }

//                Log.d(TAGLOG, "----------------------- pasando9---------------" + listaMarcas.size());

            }

            //metodo del spinner cuando no se selecciona nada
            public void onNothingSelected(AdapterView<?> parent) {
                  Toast.makeText(getApplicationContext(), "nada en el spinner", Toast.LENGTH_LONG).show();
            }

        });
    }



    void iniciarActivity() {

        Bundle b = new Bundle();
        b.putString("marca", txtMarca.getText().toString());

        Intent intent = new Intent(MainActivity.this, MenuPrincipal.class);

        intent.putExtras(b);

        startActivity(intent);
    }



}

