package com.example.luis.dibujosaleatorios;

import java.util.Random;


public class UtilidadesRandom {

    private static Random random = new Random();

    public static int randomInt(int rango) {
        return(random.nextInt(rango));
    }

    public static int randomIndex(Object[] array) {
        return(randomInt(array.length));
    }

    public static <T> T randomElement(T[] array) {
        return(array[randomIndex(array)]);
    }

    public static float randomFloat(int n) {
        return((float)Math.random()*n);
    }

}