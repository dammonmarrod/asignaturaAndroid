package com.example.reproductor_musica_versionfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
        import android.os.Bundle;
        import android.os.Environment;
        import android.util.Log;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.BaseAdapter;
        import android.widget.ImageView;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.io.File;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.Map;

public class ReproduccionMemoriaInterna extends AppCompatActivity {

    private  ListView mListView;
    int [] images;
    ArrayList<String> archivos;
    ArrayList<String> nombreCanciones;

    //Variables tipo File
    File ruta_definitiva;
    File[] files_directory_music;
    File ruta_memoria_interna_Xiaomi;
    File ruta_memoria_interna_resto;
    File f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproduccion_memoria_interna);

        mListView = findViewById(R.id.listView);

        //Como ver los archivos internos de tu movil y de tu tablet
        //MUY IMPORTANTE
        //https://developer.android.com/studio/debug/device-file-explorer?hl=es-419

        System.out.println("=========================================================");

        if(hasRealRemovableSdCard(getApplicationContext())){
            //Buscaremos luego en la carpeta Music dentro de la carpeta del usuario
            String file_path =  getRemovableSDCardPath(getApplicationContext());
            File dir = new File(file_path);
            System.out.println("ruta utilizada exite Music" + file_path);
            files_directory_music =dir.listFiles();//Obtengo los archivos de audio
            if (!dir.exists()) {
                //Si no existe el directorio lo creamos
                dir.mkdirs();//No funciona siempre???
                ruta_definitiva= new File(file_path);//La ruta donde deber?? estar la carpeta Music para localizar los audios
                System.out.println("ruta utilizada no exite Music" + file_path);
                files_directory_music =ruta_definitiva.listFiles();//Obtengo los archivos de audio
            }
        }

        if (files_directory_music ==null){//Sino obtengo ninguno, inicializo todos los archivos file, para evitar el error de ser nulos y que no se ejecute el Listview
            files_directory_music=new File [0];
            archivos = new ArrayList<>(0);
            nombreCanciones = new ArrayList<>(0);
            System.out.println ("Longitud del File " + files_directory_music.length);
        }


        //Si ha encontrado alg??n archivo lo muestro
        archivos = new ArrayList<>();
        nombreCanciones = new ArrayList<>();

        for (int i = 0; i < files_directory_music.length; i++) {
            System.out.println("archivos encontrados" + files_directory_music[i]);
            //En caso de que sean archivos de m??sica terminados en mps3 y en wav guardaremos los archivos.
            if (files_directory_music[i].getName().endsWith("mp3") ||files_directory_music[i].getName().endsWith("wav")  ){
                System.out.println("entrando");
                archivos.add(files_directory_music[i].getName());//A??adimos el archivo
                nombreCanciones.add(files_directory_music[i].getName().toString().replace(".mp3", "").replace(".wav", "")); //a??adimos el nombre
            }

        }


        //Definir?? cuantos items hay que crear, que ser?? igual que el tama??o del array que contiene los archivos
        images = new int[archivos.size()];

        //Si no hay canciones, es decir la longitud del array es 0, mostramos un toast con el aviso de que la carpeta esta vacia
        if(archivos.size() ==0){
            Toast.makeText(this, "Catalogo vacio.", Toast.LENGTH_SHORT).show();
        }


        //Para mostrar resultados por consola
        for (int i = 0; i < archivos.size(); i++) {

            System.out.println(archivos.get(i));
        }
        for (int i = 0; i < nombreCanciones.size(); i++) {

            System.out.println(nombreCanciones.get(i));
        }


        //Mostramos el listview con el adaptador
        MyAdapter adapter = new MyAdapter();
        mListView.setAdapter(adapter);

        //Seg??n se seleccione un item u otro se enviar??n los datos al siguente Intent
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String nombreCancion = (String) mListView.getItemAtPosition(i);//Obtengo la posici??n
                //Envio los datos de la canci??n, el nombre y su posici??n.
                startActivity(new Intent(getApplicationContext(),Onclick_ReproduccionSDCARD.class).putExtra("songs", archivos).putExtra("songname", nombreCanciones).putExtra("pos",i));
            }
        });


    }
    //Adaptador para mostrar el Listview
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
            System.out.println("archivo images" + images.length);
            System.out.println("archivo nombreCanciones" + nombreCanciones.size());
            view = getLayoutInflater().inflate(R.layout.activity_reproduccion_memoria_interna_items ,viewGroup , false);
            TextView textView = view.findViewById(R.id.titulo);
            //ImageView imageView = view.findViewById(R.id.icono);
            textView.setText(nombreCanciones.get(i));
            return  view;
        }

    }
    //Revisamos si tienes una SDCard y esta montada
    public static boolean hasRealRemovableSdCard(Context context) {
        return ContextCompat.getExternalFilesDirs(context, null).length >= 2;
    }

    //si existe entonces obt??n el path para que en esta ruta guardes tus archivos:
    public static String getRemovableSDCardPath(Context context) {
        File[] storages = ContextCompat.getExternalFilesDirs(context, null);

        if (storages.length > 1 && storages[0] != null && storages[1] != null) {
            System.out.println("Prueba 0" + storages[0]);//La ruta almacenamiento interno
            System.out.println("Prueba 1" + storages[1]);// la ruta del almacenamietno externo

            //Buscamos en la memoria interna
            File ruta_memoria = new File("/sdcard/Music/");
            System.out.println("Ruta devuelta" + ruta_memoria.toString());
            return ruta_memoria.toString();
        }else
            return "";
    }
}

