package br.imisturebas.zechinelao;


public class Fmt {

    public static String fmt(double a, boolean sinal){
        String str = "";
        if(sinal == true && a >= 0){
            str += "+";
        }
        if((float) a  == (int)a){
            str += (int) a;
        }
        else{
            str += String.format("%g", a);
        }

        return str;
    }

    public static String fmt_var(double a, boolean sinal){
        if(a == 1){
            if(sinal){
                return "+";
            }
            else{
                return "";
            }
        }
        else if (a == -1){
            return "-";
        }
        else{
            return fmt(a, sinal);
        }
    }

    public static  String img_tex(String s){
        String str = "";
        str += "<img src=\"http://latex.codecogs.com/svg.latex?";
        str += s + "\"";
        str += " alt = \"";
        str += s + "\"";
        str += "border=\"0\"/>";
        return str;
    }

    public static String tabela(String s){
        return "<center><table border=0 style='border-collapse: collapse' cellpadding=4 align='justify'>"
                + s + "</tr></table></center><br>";
    }

    public static  String fmt_parentes(double a){
        if(a >= 0){
            return fmt(a, false);
        }
        return "(" + fmt(a, false) + ")";
    }
}
