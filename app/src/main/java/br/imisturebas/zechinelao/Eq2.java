package br.imisturebas.zechinelao;


public class Eq2 {
    private double a;
    private double b;
    private double c;

    public enum TipoSolucaoEq2 {
        SOLUCAO,
        SOLUCAO_BHASKARA,
        SOLUCAO_COMPLEMENTO,
        SOLUCAO_INCOMPLETA;

        static public TipoSolucaoEq2 converte(int id){
            TipoSolucaoEq2 []A = TipoSolucaoEq2.values();
            for(int i = 0; i < A.length; i++){
                if(A[i].ordinal() == id) return A[i];
            }
            return A[0];
        };
    };


    //-------------------Construtores--------------------------------------
    public Eq2() {
        a = 1;
        b = 0;
        c = 0;
    }

    public Eq2(double a, double b, double c){
        this.a = a;
        this.b = b;
        this.c = c;
    }

    //---------------------Modificadores--------------------------------------
    public void normaliza(){
        c /= a;
        b /= a;
        a = 1;
    }

    public void mul(double v){
        if(v != 0){
            a *= v;
            b *= v;
            c *= v;
        }
    }

    //--------------------Uteis------------------------------------------------
    @Override
    public String toString() {
        String aux = "";

        //Equação
        if (a == 1) {
            aux += "x^2";
        } else if (a == -1) {
            aux += "-x^2";
        } else {
            aux += Fmt.fmt(a, false) + "x^2";
        }
        if (b != 0) {
            if (b == 1) {
                aux += "+x";
            } else if (b == -1) {
                aux += "-x";
            } else {
                aux += Fmt.fmt(b, true) + "x";
            }
        }
        if (c != 0) {
            aux += Fmt.fmt(c, true);
        }
        aux += "=0";

        return Fmt.img_tex(aux);

    }

    public String solucao(Eq2.TipoSolucaoEq2 s) {
        switch (s) {
            case SOLUCAO:
                return solucao_tex();
            case SOLUCAO_BHASKARA:
                return solucao_bhaskara_tex();
            case SOLUCAO_COMPLEMENTO:
                return solucao_complemento_tex();
            case SOLUCAO_INCOMPLETA:
                return solucao_incompleta_tex();
            default:
                return "";
        }
    }



    //----------------------Solucao Equação------------------------------


    public String solucao_tex() {
        String str = "";
        str += "A solução para a equação" + toString() + " é: <br />";
        String temp;

        double delta = b * b - 4 * a * c;
        if (delta > 0) {
            double x1 = (-b + Math.sqrt(delta)) / (2 * a);
            double x2 = (-b - Math.sqrt(delta)) / (2 * a);
            if (x1 > x2) {
                temp = "S = \\lbrace" + Fmt.fmt(x2, false) + ";" + Fmt.fmt(x1, false) + "\\rbrace";
            } else {
                temp = "S = \\lbrace" + Fmt.fmt(x1, false) + ";" + Fmt.fmt(x2, false) + "\\rbrace";
            }

        } else if (delta == 0) {
            double x = (-b) / (2 * a);
            temp = "S = \\lbrace" + Fmt.fmt(x, false) + "\\rbrace";
        } else {
            Complexo x1, x2;
            x1 = new Complexo(-b, Math.sqrt(-delta));
            x1.mul(1 / (2 * a));
            x2 = new Complexo(-b, -Math.sqrt(-delta));
            x2.mul(1 / (2 * a));
            temp = "S = \\lbrace" + x1.paraString() + ";" + x2.paraString() + "\\rbrace";

        }
        str += Fmt.img_tex(temp);
        return str;

    }

    public String solucao_bhaskara_tex() {
        String str = "";
        str += "Resolvendo a equação " + toString() + " usando a Fórmula de Bhaskara";
        str += "<br/>"; //Quebra de linha
        String temp = "x = {-b \\pm \\sqrt{\\Delta} \\over 2a}";
        str += Fmt.img_tex(temp);
        str += "<br/>";
        str += "Onde ";
        temp = "\\Delta = b^2-4ac";
        str += Fmt.img_tex(temp);
        str += "<br/>";
        str += "Temos ";
        temp = "a = " + Fmt.fmt(a, false) + ", b = " + Fmt.fmt(b, false) + ", c = " + Fmt.fmt(b, false);
        str += Fmt.img_tex(temp);
        str += "<br/>";
        temp = "\\Delta = " + "(" + Fmt.fmt(b, false) + ")^2";
        temp += "-4\\times(" + Fmt.fmt(a, false) + ")";
        temp += "\\times(" + Fmt.fmt(c, false) + ")";
        str += Fmt.img_tex(temp);
        double delta = b * b - 4 * a * c;
        str += "<br/>";
        temp = "\\Delta = " + Fmt.fmt(delta, false);
        str += Fmt.img_tex(temp);
        str += "<br/>";


        if (delta > 0) {
            double x1, x2;
            temp = "\\Delta > 0";
            str += "Como " + Fmt.img_tex(temp) + "temos duas raizes reais";
            str += "<br/>";
            temp = "x_{1} = \\frac{" + Fmt.fmt(-b, false) + "+\\sqrt{" + Fmt.fmt(delta, false) + "}}{ 2 \\times(" + Fmt.fmt(a, false) + ")}";
            str += Fmt.img_tex(temp);
            str += "<br/>";
            temp = "x_{1} = \\frac{" + Fmt.fmt(-b, false) + "+" + Fmt.fmt(Math.sqrt(delta), false) + "}{" + Fmt.fmt(2 * a, false) + "}";
            str += Fmt.img_tex(temp);
            str += "<br/>";
            temp = "x_{1} = \\frac{" + Fmt.fmt(-b + Math.sqrt(delta), false) + "}{" + Fmt.fmt(2 * a, false) + "}";
            str += Fmt.img_tex(temp);
            str += "<br/>";
            temp = "x_{1} = " + Fmt.fmt((-b + Math.sqrt(delta)) / (2 * a), false);
            str += Fmt.img_tex(temp);
            str += "<br/>";

            temp = "x_{2} = \\frac{" + Fmt.fmt(-b, false) + "-\\sqrt{" + Fmt.fmt(delta, false) + "}}{ 2 \\times(" + Fmt.fmt(a, false) + ")}";
            str += Fmt.img_tex(temp);
            str += "<br/>";
            temp = "x_{2} = \\frac{" + Fmt.fmt(-b, false) + "-" + Fmt.fmt(Math.sqrt(delta), false) + "}{" + Fmt.fmt(2 * a, false) + "}";
            str += Fmt.img_tex(temp);
            str += "<br/>";
            temp = "x_{2} = \\frac{" + Fmt.fmt(-b - Math.sqrt(delta), false) + "}{" + Fmt.fmt(2 * a, false) + "}";
            str += Fmt.img_tex(temp);
            str += "<br/>";
            temp = "x_{2} = " + Fmt.fmt((-b - Math.sqrt(delta)) / (2 * a), false);
            str += Fmt.img_tex(temp);
            str += "<br/>";
            str += "<br/>";

            x1 = (-b + Math.sqrt(delta)) / (2 * a);
            x2 = (-b - Math.sqrt(delta)) / (2 * a);

            if (x1 > x2) {
                temp = "S = \\lbrace" + Fmt.fmt(x2, false) + ";" + Fmt.fmt(x1, false) + "\\rbrace";
            } else {
                temp = "S = \\lbrace" + Fmt.fmt(x1, false) + ";" + Fmt.fmt(x2, false) + "\\rbrace";
            }

            str += Fmt.img_tex(temp);
        } else if (delta == 0) {
            double x;
            temp = "\\Delta = 0";
            str += "Como " + Fmt.img_tex(temp) + "temos uma raiz real";
            str += "<br/>";
            temp = "x = \\frac{" + Fmt.fmt(-b, false) + "}{ 2 \\times(" + Fmt.fmt(a, false) + ")}";
            str += Fmt.img_tex(temp);
            str += "<br/>";
            temp = "x = \\frac{" + Fmt.fmt(-b, false) + "}{" + Fmt.fmt(2 * a, false) + "}";
            str += Fmt.img_tex(temp);
            str += "<br/>";
            x = -b / (2 * a);
            temp = "x = " + Fmt.fmt(x, false);
            str += Fmt.img_tex(temp);
            str += "<br/>";
            str += "<br/>";
            temp = "S = \\lbrace" + Fmt.fmt(x, false) + "\\rbrace";
            str += Fmt.img_tex(temp);
        } else {
            Complexo x1;
            Complexo x2;
            temp = "\\Delta < 0";
            str += "Como " + Fmt.img_tex(temp) + "temos duas raizes complexas";
            str += "<br/>";

            temp = "x_{1} = \\frac{" + Fmt.fmt(-b, false) + "+\\sqrt{" + Fmt.fmt(delta, false) + "}}{ 2 \\times(" + Fmt.fmt(a, false) + ")}";
            str += Fmt.img_tex(temp);
            str += "<br/>";
            temp = "x_{1} = \\frac{" + Fmt.fmt(-b, false) + "+" + Fmt.fmt(Math.sqrt(-delta), false) + "i}{" + Fmt.fmt(2 * a, false) + "}";
            str += Fmt.img_tex(temp);
            str += "<br/>";
            x1 = new Complexo(-b, Math.sqrt(-delta));
            temp = "x_{1} = \\frac{" + x1.paraString() + "}{" + Fmt.fmt(2 * a, false) + "}";
            str += Fmt.img_tex(temp);
            str += "<br/>";
            x1.mul(1 / (2 * a));
            temp = "x_{1} = " + x1.paraString();
            str += Fmt.img_tex(temp);
            str += "<br/>";

            temp = "x_{2} = \\frac{" + Fmt.fmt(-b, false) + "-\\sqrt{" + Fmt.fmt(delta, false) + "}}{ 2 \\times(" + Fmt.fmt(a, false) + ")}";
            str += Fmt.img_tex(temp);
            str += "<br/>";
            temp = "x_{2} = \\frac{" + Fmt.fmt(-b, false) + "-" + Fmt.fmt(Math.sqrt(-delta), false) + "i}{" + Fmt.fmt(2 * a, false) + "}";
            str += Fmt.img_tex(temp);
            str += "<br/>";
            x2 = new Complexo(-b, -Math.sqrt(-delta));
            temp = "x_{2} = \\frac{" + x2.paraString() + "}{" + Fmt.fmt(2 * a, false) + "}";
            str += Fmt.img_tex(temp);
            str += "<br/>";
            x2.mul(1 / (2 * a));
            temp = "x_{2} = " + x2.paraString();
            str += Fmt.img_tex(temp);
            str += "<br/>";
            str += "<br/>";

            temp = "S = \\lbrace" + x1.paraString() + ";" + x2.paraString() + "\\rbrace";
            str += Fmt.img_tex(temp);

        }


        return str;

    }

    public String solucao_incompleta_tex() {
        String str = "Vamos resolver a equação ";
        str += toString();
        String temp;
        double a_antigo = a;

        if (a != 1) {
            str += "<br/>";
            str += "Dividindo toda a equação por " + Fmt.fmt(a, false) + " temos ";
            str += "<br/>";
            this.normaliza();
            str += toString();
        }

        str += "<br/>";

        if (b == 0 && c == 0) {
            str += "<br/>";
            temp = "x = 0";
            str += Fmt.img_tex(temp);
            str += "<br/>";
            temp = "S = \\lbrace 0 \\rbrace";
            str += Fmt.img_tex(temp);
        } else if (b == 0) {
            temp = "x^2 = " + Fmt.fmt(-c, false);
            str += Fmt.img_tex(temp);
            str += "<br/>";
            temp = "x = \\pm\\sqrt" + Fmt.fmt(-c, false);
            str += Fmt.img_tex(temp);
            str += "<br/>";

            if (c < 0) {
                temp = "x = \\pm " + Fmt.fmt(Math.sqrt(-c), false);
                str += Fmt.img_tex(temp);
                str += "<br/>";
                temp = "S = \\lbrace " + (Fmt.fmt(-Math.sqrt(-c), false)) + ";" + Fmt.fmt(Math.sqrt(-c), false) + "\\rbrace";
                str += Fmt.img_tex(temp);
            } else {
                str += "Não existe solução real <br >";
                temp = "x = \\pm " + Fmt.fmt(Math.sqrt(c), false) + "i";
                str += Fmt.img_tex(temp);
                str += "<br />";
                temp = "S = \\lbrace " + Fmt.fmt(-Math.sqrt(c), false) + "i;" + Fmt.fmt(Math.sqrt(c), false) + "i\\rbrace";
                str += Fmt.img_tex(temp);
            }

        } else if (c == 0) {
            temp = "x (x " + Fmt.fmt(b, true) + ") = 0";
            str += Fmt.img_tex(temp);
            str += "<br />";
            temp = "x = 0";
            str += Fmt.img_tex(temp);
            str += "<br />";
            temp = "x " + Fmt.fmt(b, true) + " = 0";
            str += Fmt.img_tex(temp);
            str += "<br />";
            temp = "x = " + Fmt.fmt(-b, false);
            str += Fmt.img_tex(temp);
            str += "<br />";

            if (-b < 0) {
                temp = "S = \\lbrace " + Fmt.fmt(-b, false) + ";" + 0 + "\\rbrace";
            } else {
                temp = "S = \\lbrace " + 0 + ";" + Fmt.fmt(-b, false) + "\\rbrace";
            }

            str += Fmt.img_tex(temp);

        }
        if (a_antigo != 1) {
            this.mul(a_antigo);
        }
        return str;
    }

    public String solucao_complemento_tex() {
        String str = "Vamos resolver a equação ";
        str += toString();
        String temp;
        double a_antigo = a;
        double d;

        if (this.b == 0) {
            return solucao_incompleta_tex();
        }

        if (a != 1) {
            str += "<br/>";
            str += "Dividindo toda a equação por " + Fmt.fmt(a, false) + " temos ";
            str += "<br/>";
            this.normaliza();
            str += toString();
        }

        str += "<br/>";

        //A equação já é um quadrado perfeito
        if (2 * Math.sqrt(c) == Math.abs(b)) {
            d = Math.sqrt(c);
            if (b < 0) d = -d;
            temp = "\\left(x" + Fmt.fmt(d, true) + "\\right)^2 = 0";
            str += Fmt.img_tex(temp);
            str += "<br />";
            temp = "x" + Fmt.fmt(d, true) + " = 0";
            str += Fmt.img_tex(temp);
            str += "<br />";
            temp = "x = " + Fmt.fmt(-d, false);
            str += Fmt.img_tex(temp);
            str += "<br />";
            str += "<br />";
            temp = "S = \\lbrace" + Fmt.fmt(-d, false) + "\\rbrace";
            str += Fmt.img_tex(temp);


            if (a_antigo != 1) {
                this.mul(a_antigo);
            }

            return str;
        }

        if (c != 0) {
            temp = "x^2 " + Fmt.fmt(b, true) + "x = " + Fmt.fmt(-c, false);
            str += Fmt.img_tex(temp);
            str += "<br/>";
            temp = "x^2" + Fmt.fmt(b, true) + "x" + Fmt.fmt(b * b / 4, true) + " = " + Fmt.fmt(-c, false) + Fmt.fmt(b * b / 4, true);
            str += Fmt.img_tex(temp);
            str += "<br/>";
            d = -c + (b * b / 4);
            temp = "\\left(x" + Fmt.fmt(b / 2, true) + "\\right)^2 = " + Fmt.fmt(d, false);
            str += Fmt.img_tex(temp);
            str += "<br/>";
        } else {
            temp = "x^2" + Fmt.fmt(b, true) + "x" + Fmt.fmt(b * b / 4, true) + " = " + Fmt.fmt(b * b / 4, false);
            str += Fmt.img_tex(temp);
            str += "<br/>";
            d = b * b / 4;
            temp = "\\left(x" + Fmt.fmt(b / 2, true) + "\\right)^2 = " + Fmt.fmt(d, false);
            str += Fmt.img_tex(temp);
            str += "<br/>";
        }

        if (d >= 0) {
            temp = "x" + Fmt.fmt(b / 2, true) + " = \\pm\\sqrt" + Fmt.fmt(d, false);
            str += Fmt.img_tex(temp);
            str += "<br/>";
            d = Math.sqrt(d);
            temp = "x" + " = \\pm" + Fmt.fmt(d, false) + Fmt.fmt(-b / 2, true);
            str += Fmt.img_tex(temp);
            str += "<br/>";
            temp = "x_{1} = " + Fmt.fmt(d, false) + Fmt.fmt(-b / 2, true);
            str += Fmt.img_tex(temp);
            str += "<br/>";
            double x1 = d - b / 2;
            temp = "x_{1} = " + Fmt.fmt(x1, false);
            str += Fmt.img_tex(temp);
            str += "<br/>";

            temp = "x_{2} = " + Fmt.fmt(-d, false) + Fmt.fmt(-b / 2, true);
            str += Fmt.img_tex(temp);
            str += "<br/>";
            double x2 = -d - b / 2;
            temp = "x_{2} = " + Fmt.fmt(x2, false);
            str += Fmt.img_tex(temp);
            str += "<br/>";
            str += "<br/>";

            if (x1 < x2) {
                temp = "S = \\lbrace" + Fmt.fmt(x1, false) + ";" + Fmt.fmt(x2, false) + "\\rbrace";
            } else {
                temp = "S = \\lbrace" + Fmt.fmt(x2, false) + ";" + Fmt.fmt(x1, false) + "\\rbrace";
            }
            str += Fmt.img_tex(temp);

        } else {
            str += "Não existe solução real <br/>";
            temp = "x" + Fmt.fmt(b / 2, true) + " = \\pm\\sqrt" + Fmt.fmt(d, false);
            str += Fmt.img_tex(temp);
            str += "<br/>";
            d = Math.sqrt(-d);
            temp = "x" + " = " + Fmt.fmt(-b / 2, false) + "\\pm" + Fmt.fmt(d, false) + "i ";
            str += Fmt.img_tex(temp);
            str += "<br />";
            str += "<br />";
            temp = "S = \\lbrace" + Fmt.fmt(-b / 2, false) + "+" + Fmt.fmt(d, false) + "i" + ";" + Fmt.fmt(-b / 2, false) + "-" + Fmt.fmt(d, false) + "i" + "\\rbrace";
            str += Fmt.img_tex(temp);

        }

        if (a_antigo != 1) {
            this.mul(a_antigo);
        }

        return str;

    }
}
