package com.example.reproductor_musica_version5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ReproduccionListView extends AppCompatActivity implements View.OnClickListener {

    //Declaramos las variables de los botones que usaremos
    Button btCarpetaSDCARD, btCarpetaMemoriaInterna;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproduccion_list_view);

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            //Muestra las ventanas emergentes para aceptar los permisos de escritura y lectura de audio y grabación de audio
            ActivityCompat.requestPermissions(ReproduccionListView.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, 1000);
        }

        btCarpetaSDCARD = findViewById(R.id.btCarpetaSDCARD);
        btCarpetaSDCARD.setOnClickListener(this);


        btCarpetaMemoriaInterna = findViewById(R.id.btCarpetaMemoriaInterna);
        btCarpetaMemoriaInterna.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btCarpetaSDCARD:
                Intent intent1 = new Intent(this, ReproduccionSDCARD.class);
                startActivity(intent1);
                break;

            case R.id.btCarpetaMemoriaInterna:
                Intent intent2 = new Intent(this,ReproduccionMemoriaInterna.class);
                startActivity(intent2);
                break;
            default:
                break;
        }

    }

}
