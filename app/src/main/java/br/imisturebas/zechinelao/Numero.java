package br.imisturebas.zechinelao;

import java.util.ArrayList;

/**
 * Created by paulo on 17/02/16.
 */
public class Numero {

    //Verifica se um numero é primo
    static boolean ehPrimo(int n){
        if(n % 2 == 0) return false;
        int limite = (int)Math.sqrt(n);
        for(int i = 3; i <= limite; i+=2){
            if(n % i == 0) return false;
        }
        return true;
    }

    static ArrayList<Integer> fatores(int n){
        ArrayList<Integer> fat = new ArrayList<>();
        //Fatora o numero
        int num = 2;
        while(n != 1){
            if(n % num == 0){
                fat.add(num);
                n /= num;
            }
            else{
                num++;
            }
        }
        return fat;
    }
}
