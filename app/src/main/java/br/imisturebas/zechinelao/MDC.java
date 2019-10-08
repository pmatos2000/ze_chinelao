package br.imisturebas.zechinelao;

/**
 * Created by paulo on 13/02/16.
 */
public class MDC {

    static public int mdc(int a, int b){
        if(b == 0) return a;
        return mdc(b, a%b);

    }

    static public int solucao(int v[]){
        int mdc = 1;
        int num = 2;
        boolean controle;

        while(true){
            //Verifica se todos os numeros são 1
            controle = true;
            for(int i = 0; i < v.length; i++){
                if(v[i] != 1){
                    controle = false;
                    break;
                }
            }

            //Sai do laço
            if(controle){
                return mdc;
            }

            //Encontre um numero que divide pelo menos um deles
            while(!controle){
                for(int i = 0; i < v.length; i++){
                    if(v[i] % num == 0){
                        controle = true;
                        break;
                    }
                }
                if(controle) break;
                num++;
            }

            //Verifica se o numero divide todos
            for(int i = 0; i < v.length; i++){
                if(v[i]%num == 0){
                    v[i] = v[i]/num;
                }
                else{
                    controle = false;
                }
            }

            if(controle) mdc *= num;
        }
    }


    static public String solucao_fatoracao_tex(int v[]){
        int mdc = 1;
        int num = 2;
        int total_div = 0;
        boolean controle;
        String str_num = "";
        String str = "Calculando o MDC(";
        String str_mdc = "";

        for(int i = 0; i < v.length; i++){
            str_num += v[i];
            if(i+1<v.length) str_num += " , ";
        }

        str += str_num + ") <br><br>";
        str += "<center><table border=0 style='border-collapse: collapse' cellpadding=4 align='justify'>";
        while(true){
            str += "<tr>";
            //Verifica se todos os numeros são 1
            controle = true;
            for(int i = 0; i < v.length; i++){
                if(v[i] != 1){
                    controle = false;
                }
                str += "<td id='sem'>" + v[i] + "</td>";
            }

            //Sai do laço
            if(controle){
                str += "</tr></table></center><br>";
                if(total_div < 2 ){
                    str += "MDC(" + str_num + ") = " + mdc;
                }
                else{
                    str += "MDC(" + str_num + ") = " +  str_mdc + " = " + mdc;
                }
                return str;
            }

            //Encontre um numero que divide pelo menos um deles
            while(!controle){
                for(int i = 0; i < v.length; i++){
                    if(v[i] % num == 0){
                        controle = true;
                        break;
                    }
                }
                if(controle) break;
                num++;
            }

            //Verifica se o numero divide todos
            for(int i = 0; i < v.length; i++){
                if(v[i]%num == 0){
                    v[i] = v[i]/num;
                }
                else{
                    controle = false;
                }
            }


            if(controle){
                mdc *= num;
                if(total_div == 0) {
                    str_mdc += num;
                }
                else{
                    str_mdc += " × " + num;

                }
                str += "<td id='so_dir'>" + num  + "</td><td id='sem'>*</td></tr>";
                total_div++;
            }
            else{
                str += "<td id='so_dir'>" + num  + "</td id='sem'></tr>";
            }


        }
    }


    static public String solucao_tex(int v[]){
        String str = "MDC(";
        for(int i = 0; i < v.length; i++){
            str += v[i];
            if(i+1<v.length) str += " , ";
        }
        str += ") = " + solucao(v);
        return str;
    }

    static public String solucao_euclides_tex(int a, int b){
        String str = "Calculando o MDC(" + a + " , " + b +")<br><br>";
        str += "<center><table border=1 style='border-collapse: collapse' align='justify'>";
        str += "<tr><td>Dividendo</td><td>Divisor</td><td>Resto</td><td>Quociente</td></tr>";
        int c, d;
        if(a > b) {
            c = a;
            d = b;
        }
        else{
            c = b;
            d = a;
        }
        int resto = 1;
        int quociente;
        while(resto != 0){
            resto = c%d;
            quociente = c/d;
            str += "<tr><td>" + c + "</td><td>" + d + "</td><td>" + resto + "</td><td>" + quociente + "</td></tr>";
            c = d;
            d = resto;
        }
        str += "</table></center><br>MDC(" + a + " , " + b +") = " + c;
        return str;

    }
}
