package com.example.reproductor_musica_version1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EleccionReproduccion extends AppCompatActivity implements View.OnClickListener {

    Button btReproduccionRaw, btReproduccionListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleccion_reproduccion);

        //Asociamos cada boton con su parte gráfica
        btReproduccionRaw = (Button) findViewById(R.id.btReproduccionRaw);
        btReproduccionRaw.setOnClickListener(this);

        btReproduccionListView = (Button) findViewById(R.id.btReproduccionListView);
        btReproduccionListView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btReproduccionRaw:
                Intent intent = new Intent(this, ReproduccionCarpetaRaw.class);
                startActivity(intent);
                break;
            case R.id.btReproduccionListView:
                Intent intent1 = new Intent(this, ReproduccionListView.class);
                startActivity(intent1);
                break;

            default:
                break;
        }

    }

    //Metodo para controlar el botón back de la barra inferior de la pantalla
    @Override
    public void onBackPressed(){
        //Si lo dejamos sin nada, no funcionará
    }
}
