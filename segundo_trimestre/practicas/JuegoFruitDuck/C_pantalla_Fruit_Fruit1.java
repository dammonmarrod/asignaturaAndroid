package com.example.juego_fruit_version_01;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class C_pantalla_Fruit_Fruit1 {

    //Creamos los patos
    int CANTIDADFRUIT = 8;
    Bitmap fruit[] = new Bitmap[CANTIDADFRUIT];

    int posicionX;//Variable que cambia según avanza los patos de derecha a izquierda
    int posicionY;//variable de arriba - abajo que mantiene el mismo valor 100
    int velocidad;//velocidad a la que va el pato
    int punteroFruit;
    Random random;

    public C_pantalla_Fruit_Fruit1(Context context) {
        //para dar efecto optico debemos poner 4 de la misma imagen y 4 de la otra
        fruit[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.buildaduck_patovuela1);
        fruit[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.buildaduck_patovuela1);
        fruit[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.buildaduck_patovuela1);
        fruit[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.buildaduck_patovuela1);
        fruit[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.buildaduck_patovuela2);
        fruit[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.buildaduck_patovuela2);
        fruit[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.buildaduck_patovuela2);
        fruit[7] = BitmapFactory.decodeResource(context.getResources(), R.drawable.buildaduck_patovuela2);
        random = new Random();
        punteroFruit = 0;
        resetPosition();
    }

    public Bitmap getBitmap(){
        return fruit[punteroFruit];
    }

    public int getWidth(){
        return fruit[0].getWidth();
    }

    public int getHeight(){
        return fruit[0].getHeight();
    }

    public void resetPosition(){
        //La posiciónX que sera de izq a derecha
        posicionX = random.nextInt(C_pantalla_Fruit_CanvasView.dWidth);
        System.out.println("Posicion en X " + posicionX);

        //La posicion de la fruta
        posicionY = 0;
        System.out.println("Posicion en Y " + posicionY);

        //Hacemos que avance
        velocidad = 1 - random.nextInt(17);
        //velocidad = 1 + random.nextInt(17); la velocidad máxima va a ser 17 o valores inferiores
        System.out.println("Velocidad: " + velocidad);
    }

}
