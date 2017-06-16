package com.example.user.fcc;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends BaseActivity {


    private static final String TAGLOG = "taglog";
    private TextView txtMarca;
    private TextView txtModelo;
    private TextView txtMatricula;
    private EditText txtNuevo;
    private Button btnOk;
    private Button btnEntrar;


    Spinner spMarca;
    Spinner spModelo;
    Spinner spMatricula;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtMarca = (TextView) findViewById(R.id.txtMarca);
        txtModelo = (TextView) findViewById(R.id.txtModelo);
        txtMatricula = (TextView) findViewById(R.id.txtMatricula);
        txtNuevo = (EditText) findViewById(R.id.txtNuevo);
        btnOk = (Button) findViewById(R.id.btnOk);
        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        spMarca = (Spinner) findViewById(R.id.spMarca);
        spModelo = (Spinner) findViewById(R.id.spModelo);
        spMatricula = (Spinner) findViewById(R.id.spMatricula);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                iniciarActivity();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                txtMarca.setText(txtNuevo.getText());
                creaSpinnerModelo(txtNuevo.getText().toString());
            }
        });


        creaSpinnerMarca();
    }




    public void creaSpinnerMarca() {
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

                Toast.makeText(getApplicationContext(), "seleccionados "+ spMarca.getSelectedItemPosition(), Toast.LENGTH_SHORT).show();

              //       Log.d(TAGLOG, "----------------------- pasando8---------------" + listaMarcas.size());

                switch (spMarca.getSelectedItemPosition()) {
                    case 0:
                        //si se selecciona todos, oculta el boton enviar
                        btnEntrar.setVisibility(View.GONE);
                        txtNuevo.setVisibility(View.VISIBLE);
                        btnOk.setVisibility(View.VISIBLE);

                        //   Toast.makeText(getApplicationContext(), "todos seleccionados "+spCorreo.getSelectedItemPosition(), Toast.LENGTH_LONG).show();
                        break;
                    default:
                        //por defecto muestra el boton enviar
                        btnEntrar.setVisibility(View.VISIBLE);
                        txtNuevo.setVisibility(View.GONE);
                        btnOk.setVisibility(View.GONE);
                        creaSpinnerModelo(parent.getItemAtPosition(position).toString());
                        //   Toast.makeText(getApplicationContext(), "otros seleccionados "+spCorreo.getSelectedItemPosition(), Toast.LENGTH_LONG).show();
                        break;
                }

//                Log.d(TAGLOG, "----------------------- pasando9---------------" + listaMarcas.size());

            }

            //metodo del spinner cuando no se selecciona nada
            public void onNothingSelected(AdapterView<?> parent) {
                  Toast.makeText(getApplicationContext(), "nada en el spinner", Toast.LENGTH_SHORT).show();
            }

        });
    }


    public void creaSpinnerModelo(String marca) {
        //definición del spinner de modeloss
        //crea una lista dinamica y se carga con los elementos recibidos de la BD firebase

        DBvehiculos conn = new DBvehiculos();
        final List<String> listaModelos = conn.listaModelos(marca);
        //   listaModelos.add("caca");
            Log.d(TAGLOG, "----------------------- pasando6---------------" + listaModelos.size());
        //creacion del adaptador del spinner de seleccion de vehiculo
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaModelos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spModelo.setAdapter(adapter);

        spModelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //metodo del spinner cuando se selecciona algo
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
                Log.d(TAGLOG, "----------------------- pasando7---------------" + listaModelos.size());
                txtModelo.setText(parent.getItemAtPosition(position).toString());

                Toast.makeText(getApplicationContext(), "seleccionados "+ spModelo.getSelectedItemPosition(), Toast.LENGTH_SHORT).show();

                //       Log.d(TAGLOG, "----------------------- pasando8---------------" + listaModelos.size());

                switch (spModelo.getSelectedItemPosition()) {
                    case 0:
                        //si se selecciona todos, oculta el boton enviar
                        btnEntrar.setVisibility(View.GONE);
                        txtNuevo.setVisibility(View.VISIBLE);
                        btnOk.setVisibility(View.VISIBLE);
                        //   Toast.makeText(getApplicationContext(), "todos seleccionados "+spCorreo.getSelectedItemPosition(), Toast.LENGTH_LONG).show();
                        break;
                    default:
                        //por defecto muestra el boton enviar
                        btnEntrar.setVisibility(View.VISIBLE);
                        txtNuevo.setVisibility(View.GONE);
                        btnOk.setVisibility(View.GONE);
                        //   Toast.makeText(getApplicationContext(), "otros seleccionados "+spCorreo.getSelectedItemPosition(), Toast.LENGTH_LONG).show();
                        break;
                }

//                Log.d(TAGLOG, "----------------------- pasando9---------------" + listaModelos.size());

            }

            //metodo del spinner cuando no se selecciona nada
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "nada en el spinner", Toast.LENGTH_SHORT).show();
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

