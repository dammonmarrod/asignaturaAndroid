package com.example.reproductor_musica_version1;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ReproduccionCarpetaRaw extends AppCompatActivity {

    //Variables de los botones y del imageView
    Button btPlay;
    Button btRepetir;
    ImageView ivImagenesAudio;


    int seguirReproduciendo =2;//Variable para hacer que se repita o no el audio
    int posicion= 0; //Posicion en el array de canciones

    //Array de la clase MediaPlayer para las canciones de la carpeta raw
    final int DIMENSIONESARRAY = 3;
    MediaPlayer [] canciones = new MediaPlayer [DIMENSIONESARRAY];


    //Objeto de la clase MediaPlayer
    MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproduccion_carpeta_raw);

        btPlay = findViewById(R.id.btPlay);
        btRepetir = findViewById(R.id.btRepetir);
        ivImagenesAudio = findViewById(R.id.ivImagenesAudio);

        //Rellenamos el array
        canciones[0]= MediaPlayer.create(this,R.raw.race );
        canciones[1]= MediaPlayer.create(this,R.raw.sound );
        canciones[2]= MediaPlayer.create(this,R.raw.tea );

    }

    //Metodo para reproducir cancion
    public void playPause(View view){
        //el método isPlaying nos indica si el audio se esta reproduciendo o no
        if(canciones[posicion].isPlaying()){//Si se esta reproduciendo
            //Si el audio se reproduce, al pulsar el botón lo que tiene que hacer es lo contrarío es decir pararse la música
            canciones[posicion].pause();

            //Cambiamos la apariencia del audio a una imagen de reproducir
            btPlay.setBackgroundResource(R.drawable.reproducir);
            Toast.makeText(this, "Pausa", Toast.LENGTH_SHORT).show();
        }else{//Si la canción no se esta reproducciendo
            //Si el audio no se reproduce, al pulsar el botón lo que tiene que hacer es lo contrarío es decir empezar la música
            canciones[posicion].start();

            //Cambiamos la apariencia del audio a una imagen de pausa
            btPlay.setBackgroundResource(R.drawable.pausa);
            Toast.makeText(this, "Reproducir", Toast.LENGTH_SHORT).show();
        }
    }

    public void pantallaBienvenida(View view) {
    }
}
