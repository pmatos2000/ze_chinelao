package br.imisturebas.zechinelao;

/**
 * Created by paulo on 26/02/16.
 */
public class Matriz {
    double A[][];

    public Matriz(double A[][]){
        this.A = A;
    }

    public String inversa_tex(){
        String str = "Calculando o inverso da seguinte matriz:<br>";
        str += this.toHTML();

        //Verifica se uma linha é toda zero
        for(int i = 0; i < A.length; i++){
            boolean sim = true;
            for(int j = 0; j < A.length; j++){
                if(A[i][j] != 0){
                    sim = false;
                    break;
                }
            }
            if(sim){
                str += "A linha " + (i+1) + " tem todos os elementos igual a zero por isso ∆ = 0";
                return str;
            }
        }

        //Verifica se uma linha é toda zero
        for(int j = 0; j < A.length; j++){
            boolean sim = true;
            for(int i = 0; i < A.length; i++){
                if(A[i][j] != 0){
                    sim = false;
                    break;
                }
            }
            if(sim){
                str += "A coluna " + (j+1) + " tem todos os elementos igual a zero por isso ∆ = 0";
                return str;
            }
        }


        str += "Usando a eliminação de Gauss Jordan<br>";

        //Criando a matriz indentidade
        double inversa[][] = new double[A.length][A.length];
        for(int i = 0; i < inversa.length; i++){
            for(int j = 0; j < inversa.length; j++){
                if(i == j){
                    inversa[i][j] = 1;
                }
                else{
                    inversa[i][j] = 0;
                }
            }
        }

        str += this.toHTMLComparacao(inversa);

        //Calculando a matriz inversa
        double pivo;
        for(int i = 0; i < A.length; i++){
            pivo = A[i][i];
            if(pivo == 0){
                //Troca linhas de lugares
                for(int j = i + 1; j < A.length; j++){
                    pivo = A[j][i];
                    if(pivo != 0){
                        str += "Trocando a linhas "  + (i+1) + "com a linha " + (j + 1) + "<br>";
                        for(int k = 0; k < A.length; k++){
                            double aux = A[i][k];
                            A[i][k] = A[j][k];
                            A[j][k] = aux;
                            aux = inversa[i][k];
                            inversa[i][k] = inversa[j][k];
                            inversa[j][k] = aux;
                            str += this.toHTMLComparacao(inversa);
                        }
                        break;
                    }
                }

                //Não encontrolo nenhum pivo igual a 0
                if(pivo == 0) {
                    str += "A matriz não possui inversa";
                    return str;
                }

                if(pivo != 1){
                    str += "Dividindo a linha " + (i+1) + "por " + Fmt.fmt(pivo, false) + "<br>";
                    for(int j = 0; j < A.length; j++){
                        A[i][j] /= pivo;
                        inversa[i][j] /= pivo;
                    }
                    str += this.toHTMLComparacao(inversa);
                }
            }
            else if(pivo != 1){
                //Divide a linha toda pelo pivo
                str += "Dividindo a linha " + (i+1) + " por " + Fmt.fmt(pivo, false) + "<br>";
                for(int j = 0; j < A.length; j++){
                    A[i][j] /= pivo;
                    inversa[i][j] /= pivo;
                }
                str += this.toHTMLComparacao(inversa);
            }

            //Elimina as linhas de baixo
            for(int j = i+1; j < A.length; j++){
                double aux = -A[j][i];
                if(aux == 0) continue;
                str += "Fazendo linha" + (j+1) + " = linha" + (j+1) + Fmt.fmt(aux, true) + " × linha" + (i+1) + "<br>";
                for(int k = i; k < A.length; k++){
                    A[j][k] +=  aux * A[i][k];
                }
                for(int k = 0; k < A.length; k++){
                    inversa[j][k] +=  aux * inversa[i][k];
                }
                str += this.toHTMLComparacao(inversa);
            }
        }

        //Elimina as linhas de cima
        for(int i = 0; i < A.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                double aux = -A[j][i];
                if (aux == 0) continue;
                str += "Fazendo linha" + (j + 1) + " = linha" + (j + 1) + Fmt.fmt(aux, true) + " × linha" + (i + 1) + "<br>";
                for (int k = i; k < A.length; k++) {
                    A[j][k] += aux * A[i][k];
                }
                for(int k = 0; k < A.length; k++){
                    inversa[j][k] +=  aux * inversa[i][k];
                }
                str += this.toHTMLComparacao(inversa);
            }
        }
        str += "O inverso da matriz é ";
        A = inversa;
        str += this.toHTML();
        return str;
    };

    private  String toHTMLComparacao(double B[][]){
        String str = "";
        if(A.length == 1){
            return Fmt.tabela("<tr><td id='so_esq'>" + Fmt.fmt(A[0][0], false) +"</td><td id='nada'>" + Fmt.fmt(B[0][0], false) + "</tr>");
        }
        for(int i = 0; i < A.length; i++){
            str += "<tr>";
            for(int j = 0 ; j < A.length; j++){
                if(j + 1  == A.length){
                    str += "<td id='so_esq'>" + Fmt.fmt(A[i][j], false) + "<td>";
                }
                else{
                    str += "<td id='nada'>" + Fmt.fmt(A[i][j], false) + "<td>";
                }
            }
            for(int j = 0 ; j < A.length; j++){
                str += "<td id='nada'>" + Fmt.fmt(B[i][j], false) + "<td>";
            }
            str += "</tr>";
        }
        return Fmt.tabela(str);
    }

    public  String toHTML(){
        String str = "";
        if(A.length == 1){
            return Fmt.tabela("<tr><td id='so_esq_dir'>" + Fmt.fmt(A[0][0], false) +"</td></tr>");
        }
        for(int i = 0; i < A.length; i++){
            str += "<tr>";
            for(int j = 0; j < A.length; j++){
                if(j == 0){
                    str += "<td id='so_dir'>" + Fmt.fmt(A[i][j], false) + "<td>";
                }
                else if(j + 1 == A.length){
                    str += "<td id='so_esq'>" + Fmt.fmt(A[i][j], false) + "<td>";
                }
                else{
                    str += "<td id='nada'>" + Fmt.fmt(A[i][j], false) + "<td>";
                }
            }
            str += "</tr>";
        }
        return Fmt.tabela(str);
    }

    public String determinante(){
        if(A.length > 3){
            return determinante_NXN();
        }
        else if (A.length == 3){
            return this.determinante_3x3();
        }
        else if (A.length == 2){
            return this.determinante_2x2();
        }
        else{
            String str = "Calculando o determinante da matriz<br>";
            str += this.toHTML();
            str += "∆ = " + Fmt.fmt(A[0][0], false);
            return str;
        }
    }

    public String determinante_NXN(){
        String str = "Calculando o determinante da matriz<br>";
        str += this.toHTML();
        double valor = 0;
        int fator = 1;
        double pivo;
        double aux;
        for(int i = 0; i < A.length; i++){
            pivo = A[i][i];
            if(pivo == 0){
                //Troca linhas de lugares
                for(int j = i + 1; j < A.length; j++){
                    pivo = A[j][i];
                    if(pivo != 0){
                        str += "Trocando a linhas "  + (i+1) + " com a linha " + (j + 1) + "<br>";
                        for(int k = 0; k < A.length; k++){
                            aux = A[i][k];
                            A[i][k] = A[j][k];
                            A[j][k] = aux;

                        }
                        fator *= -1;
                        str += this.toHTML();
                        str += "Fator de correção F = " + fator;
                        break;
                    }
                }

                //Não encontrolo nenhum pivo igual a 0
                if(pivo == 0) {
                    str += "∆ = 0";
                    return str;
                }

            }

            //Elimina as linhas de baixo
            for(int j = i+1; j < A.length; j++){
                aux = -A[j][i]/pivo;
                if(aux == 0) continue;
                str += "Fazendo linha" + (j+1) + " = linha" + (j+1) + Fmt.fmt(aux, true) + " × linha" + (i+1) + "<br>";
                for(int k = i; k < A.length; k++){
                    A[j][k] +=  aux * A[i][k];
                }
                for(int k = 0; k < A.length; k++){
                }
                str += this.toHTML();
                str += "Fator de correção F = " + Fmt.fmt(fator, false) + "<br>";
            }
        }

        str += "O determinante de um matriz triangular é o produto da sua diagonal principal<br>∆ = ";
        aux = 1;
        for(int i = 0; i < A.length; i++){
            aux *= A[i][i];
            str += Fmt.fmt_parentes(A[i][i]);
            if(i + 1 != A.length){
                str += " × ";
            }
            else{
                str += " = ";
            }
        }
        str += Fmt.fmt(aux, false) + "<br>";

        str += "Como F = " + Fmt.fmt(fator, false);
        if(fator == 1){
            str += " o determinante é o mesmo da matriz inicial";
        }
        else{
            str += "o determinate é: ∆ = " + Fmt.fmt(aux, false) + " × " + Fmt.fmt(fator, false);
            aux *= fator;
            str += " = " + Fmt.fmt(aux, false);
        }



        return str;

    };

    public String determinante_3x3 (){
        String str = "Calculando o determinante da matriz<br>";
        str += this.toHTML();
        double valor = 0;
        double aux;
        str += "∆ = ";
        for(int i = 0; i < 3; i++){
            aux = 1;
            for(int j = 0; j < 3; j++){
                str += Fmt.fmt_parentes(A[j][(j+i)%3]);
                aux *= A[j][(j+i)%3];
                if(j < 2) str += " × ";
                else if(i < 2) str += " + ";
            }
            valor += aux;
        }
        str += " - (";
        for(int i = 0; i < 3; i++){
            aux = 1;
            for(int j = 0; j < 3; j++){
                str += Fmt.fmt_parentes(A[j][(5-i-j)%3]);
                aux *= A[j][(5-i-j)%3];
                if(j < 2) str += " × ";
                else if(i < 2) str += " + ";
            }
            valor -= aux;
        }
        str += ")<br>∆ = " + Fmt.fmt(valor, false);

        return str;
    }

    public String determinante_2x2 (){
        String str = "Calculando o determinante da matriz<br>";
        str += this.toHTML();
        double valor = A[0][0] * A[1][1] - A[1][0] * A[0][1];
        str += "∆ = " + Fmt.fmt_parentes(A[0][0]) + " × " + Fmt.fmt_parentes(A[1][1]);
        str += " - ( " + Fmt.fmt_parentes(A[1][0]) + " × " + Fmt.fmt_parentes(A[0][1]);
        str += ")<br>∆ = " + Fmt.fmt(valor, false);

        return str;
    }
}
