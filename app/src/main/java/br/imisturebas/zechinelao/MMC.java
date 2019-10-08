package br.imisturebas.zechinelao;

import java.math.BigInteger;

/**
 * Created by paulo on 11/02/16.
 */
public class MMC {
    static String solucao_txt(int v[]){
        String str = "MMC(";
        String str_num = "";
        int num = 2;
        BigInteger mmc = new BigInteger("1");
        for(int i = 0; i < v.length; i++){
            str_num += v[i];
            if(i+1<v.length) str_num += " , ";
        }
        str += str_num + ") = ";
        boolean aux;

        while (true){
            //Verifica se todos são 1
            aux = true;
            for(int i = 0; i < v.length; i++){
                if (v[i] != 1) {
                    aux = false;
                    break;
                }
            }

            //Sair do while
            if(aux){
                str += mmc.toString();
                return str;
            }

            //verifica se o numero divide algum
            while(aux == false){
                for(int i = 0; i < v.length; i++){
                    if(v[i] % num == 0){
                        aux = true;
                        break;
                    }
                }
                if(aux) break;
                num++;
            }
            mmc = mmc.multiply(new BigInteger("" + num));
            for(int i = 0; i < v.length; i++){
                if(v[i]%num == 0) v[i] = v[i]/num;
            }

        }

    }

    static String solucao_fatoracao_txt(int v[]){
        String str = "Calculando o MMC(";
        String num = "";
        String str_mmc = "";
        int total_div = 0;
        int a = 2;
        BigInteger mmc = new BigInteger("1");
        boolean aux = true;

        for(int i = 0; i < v.length; i++){
            num += v[i];
            if(i+1<v.length) num += " , ";
        }
        str += num + ") <br><br>";



       str += "<center><table border=0 style='border-collapse: collapse' cellpadding=4 align='justify'>";
        while(true) {
            str += "<tr>";
            //Verifica se todos são 1
            aux = true;
            for (int i = 0; i < v.length; i++) {
                if (v[i] != 1) {
                    aux = false;
                }

                str += "<td id='nada'>" + v[i] + "</td>";

            }

            if (aux) {
                str += "</tr></table></center><br>";
                if(total_div < 2 ){
                    str += "MMC(" + num + ") = " + mmc.toString();
                }
                else{
                    str += "MMC(" + num + ") = " +  str_mmc + " = " + mmc.toString();
                }
                return str;
            }

            //verifica se o numero divide algum
            while(aux == false){
                for(int i = 0; i < v.length; i++){
                    if(v[i] % a == 0){
                        aux = true;
                        break;
                    }
                }
                if(aux) break;
                a++;
            }
            mmc = mmc.multiply(new BigInteger("" + a));
            str += "<td id = 'so_dir' >" + a + "</td></tr>";
            for(int i = 0; i < v.length; i++){
                if(v[i]%a == 0) v[i] = v[i]/a;
            }

            if(total_div == 0){
                str_mmc += " " + a;
            }
            else{
                str_mmc += " × " + a;
            }
            total_div++;
        }

    }

    static String solucao_rapido_tex(int a, int b){
        String str = "Calculando o MMC(" + a + " , " + b + ")<br>";
        str += "Usando a seguinte propriedade:";
        str += "<center>" + Fmt.img_tex("MMC(a,b) = \\frac{a \\times  b}{MDC(a,b)}") + "</center><br>";
        int mdc = MDC.mdc(a,b);
        str += MDC.solucao_euclides_tex(a,b) + "<br>";
        str +=  Fmt.img_tex("MMC(" + a + " , " + b + ") = \\frac{" + a + " \\times " + b + "}{MDC(" + a + "," + b + ")}") + "<br>";
        BigInteger mmc = new BigInteger("" + a);
        mmc = mmc.multiply(new BigInteger("" + b));
        str +=  Fmt.img_tex("MMC(" + a + " , " + b + ") = \\frac{" + mmc.toString() + "}{" + mdc + "} = " + mmc.divide(new BigInteger("" + mdc)).toString());
        return str;
    }

}
