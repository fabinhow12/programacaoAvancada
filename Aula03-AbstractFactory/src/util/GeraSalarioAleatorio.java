/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Random;

/**
 *
 * @author fabio
 */
public class GeraSalarioAleatorio {
    private static final int MINIMO = 900;
    private static final int MAXIMO = 2000;
    
    //Gerar salario aleatorio entre 900 e 2000
    public static int aleatorio(){
        Random random = new Random();
        return random.nextInt((MAXIMO - MINIMO) + 1) + MINIMO;
    }
}
