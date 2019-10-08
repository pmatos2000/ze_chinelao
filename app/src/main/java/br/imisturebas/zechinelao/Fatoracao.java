package br.imisturebas.zechinelao;

import java.util.ArrayList;

/**
 * Created by paulo on 17/02/16.
 */
public class Fatoracao {


    static int [] fatores(int n){
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
        int m = fat.size();
        int v[] = new int[m];
        for(int i = 0; i < m; i++){
            v[i] = fat.get(i);
        }

        return v;
    }

    static String fatores_tex(int n){
        String str = "O número " + n + " pode ser fatorado da seguinte forma:<br><center>";
        int v[] = fatores(n);
        int k = 0;
        int t = 0;
        for(int i = 0; i < v.length; i++){
            if(v[k] == v[i]){
                t++;
            }
            else{
                if(t == 1){
                    str += v[k] + "×";
                }
                else{
                    str += v[k] + "<sup>" + t + "</sup> × ";
                }
                t = 1;
                k = i;
            }
        }
        if(t == 1){
            str += v[k];
        }
        else{
            str += v[k] + "<sup>" + t + "</sup>";
        }
        str +=  "</center><br>";
        return str;
    }

    static String fatores_tex(int v[]){
        String str = "";
        for(int i = 0; i < v.length; i++){
            str += fatores_tex(v[i]);
        }
        return str;
    }

    static  String fatoracao_tex(int n){
        String str = "Fatorando o número " + n + "<br>";
        int a = n;
        String tabela = "";
        //Fatora o numero
        int num = 2;
        while(n != 1){
            if(n % num == 0){
                tabela += "<tr><td id='nada'>" + n  + "</td><td id='so_dir' >" + num + "</td></tr>";
                n /= num;
            }
            else{
                num++;
            }
        }
        tabela += "<tr><td>" + 1 + "</td></tr>";
        str += Fmt.tabela(tabela);
        str += fatores_tex(a);
        return str;
    }

    static String fatoracao_tex(int v[]){
        String str = "";
        for(int i = 0; i < v.length; i++){
            str += fatoracao_tex(v[i]);
        }
        return str;
    }
}
