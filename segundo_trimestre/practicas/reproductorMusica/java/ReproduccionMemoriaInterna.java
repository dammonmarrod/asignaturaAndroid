package com.example.reproductor_musica_version5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReproduccionMemoriaInterna extends AppCompatActivity {
    private ListView mListView;
    int [] images = {R.drawable.portada , R.drawable.portada , R.drawable.portada};
    List<String> archivos;
    List<String> nombreCanciones;

    //String mediaPath = Environment.getExternalStorageDirectory().getPath() + "/Music/";
    //String mediaPath = Environment.getExternalStorageDirectory().getPath() + "/Download/";
    //String mediaPath = Environment.getExternalStorageDirectory().getPath() + "/mnt/shared/Other/";
    //String mediaPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getPath() ; Directorio memoria interna /storage/emulated/0/Music
    //String mediaPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproduccion_memoria_interna);
        mListView = findViewById(R.id.listView);

        System.out.println("=========================================================");


        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getPath()+ "/Music/");
        final File[] files = file.listFiles();
        if (files == null) {
            Log.e("error", "Catálogo vacío");
        }
        archivos = new ArrayList<>();
        nombreCanciones = new ArrayList<>();


        for (int i = 0; i < files.length; i++) {
            if (files[i].getName().endsWith("mp3")){
                archivos.add(files[i].getName());
                nombreCanciones.add(files[i].getName().toString().replace(".mp3", ""));
            }

        }

        for (int i = 0; i < archivos.size(); i++) {

            System.out.println(archivos.get(i));
        }
        for (int i = 0; i < nombreCanciones.size(); i++) {

            System.out.println(nombreCanciones.get(i));
        }





        ReproduccionMemoriaInterna.MyAdapter adapter = new ReproduccionMemoriaInterna.MyAdapter();
        mListView.setAdapter(adapter);
    }
    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.activity_reproduccion_sdcard_items ,viewGroup , false);
            TextView textView = view.findViewById(R.id.titulo);
            ImageView imageView = view.findViewById(R.id.icono);
            textView.setText(nombreCanciones.get(i));
            //imageView.setImageResource(images[i]);
            return  view;
        }

    }


}
