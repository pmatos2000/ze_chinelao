package br.imisturebas.zechinelao;

import java.util.ArrayList;

/**
 * Created by paulo on 18/02/16.
 */
public class Divisores {
    static  public int[] divisores(int n){
        ArrayList<Integer> divisores = new ArrayList<Integer>();
        for(int i = 1; i <= n; i++){
            if(n%i == 0){
                divisores.add(i);
            }
        }
        //Converte para vetor
        int m = divisores.size();
        int div[] = new int[m];
        for(int i = 0; i < m; i++){
            div[i] = divisores.get(i);
        }
        return div;
    }

    static public String divisores_solucao_tex(int  n){
        String str = "Os divisores do número " + n +  " são: ";
        int[] div = divisores(n);
        for(int i = 0; i < div.length; i++){
            str += div[i];
            if(i+1<div.length) str += " , ";
        }
        return str;
    }

    static  public String divisores_solucao_tex(int  v[]){
        String str = "";
        for(int i = 0; i < v.length; i++){
            str += divisores_solucao_tex(v[i]) + "<br><br>";
        }
        return str;
    }

    static public String divisores_fatoracao_tex(int n){
        String str = "Encontrando os divisores do número " + n  + "<br>";
        str += Fatoracao.fatoracao_tex(n);
        int fat[] = Fatoracao.fatores(n);
        str += "O número de divisores do número " + n + " é:<br><center>";
        int t = 0, k = 0, m = 1;
        for(int i = 0; i < fat.length; i++){
            if(fat[k] == fat[i]){
                t++;
            }
            else{
                str += "(" + t + " + 1)";
                m *= (t + 1);
                t = 1;
                k = i;
            }
        }
        m *= (t + 1);
        str += "(" + t + " + 1) = " + m + "</center><br>";
        str += "Calculando os divisores<br>";

        String tabela = "<tr><td id='nada'></td><td  id='so_esq'></td><td id='nada'>1</td></tr>";
        int aux = n;
        ArrayList<int []> divisores = new ArrayList<int []>();
        int cel[] = new int[1];
        cel[0] = 1;
        divisores.add(cel);
        k = 0;
        for(int i = 0; i < fat.length; i++){
            tabela += "<tr><td>" + aux + "</td><td  id='so_esq_dir' >" +  fat[i] + "</td>";
            aux /= fat[i];
            if(fat[k] == fat[i]){
                cel = divisores.get(i).clone();
                for(int j = 0; j < cel.length; j++){
                    cel[j] *= fat[i];
                    if(j+1 == cel.length) {
                        tabela += "<td id='nada'>" + cel[j] + "</td>";
                    }
                    else{
                        tabela += "<td id='nada'>" + cel[j] + ",</td>";
                    }
                }
                divisores.add(cel);
                tabela += "</tr>";
            }
            else{
                k = i;
                //Calcula o valor da nova celula
                m = 0;
                for(int j = 0; j < divisores.size(); j++){
                   m += divisores.get(j).length;
                }
                cel = new int[m];

                //Copia os valores para nova celular
                m = 0;
                for(int j = 0; j < divisores.size(); j++){
                    for(int p = 0; p < divisores.get(j).length; p++){
                        cel[p + m] = divisores.get(j)[p] * fat[i];
                        if(j + 1 == divisores.size() && p + 1 == divisores.get(j).length){
                            tabela += "<td id='nada'>" + cel[p + m] + "</td>";
                        }
                        else{
                            tabela += "<td id='nada'>" + cel[p + m] + ",</td>";
                        }

                    }
                    m += divisores.get(j).length;
                }
                divisores.add(cel);
            }


            tabela += "</tr>";
        }
        str += Fmt.tabela(tabela);
        str +=  divisores_solucao_tex(n);

        return str;
    }

    static public String divisores_fatoracao_tex (int  v[]){
        String str = "";
        for(int i = 0; i < v.length; i++){
            str += divisores_fatoracao_tex(v[i]) + "<br><br>";
        }
        return str;
    }
}
