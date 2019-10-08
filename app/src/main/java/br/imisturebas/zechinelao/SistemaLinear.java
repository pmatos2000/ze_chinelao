package br.imisturebas.zechinelao;

/**
 * Created by paulo on 22/02/16.
 */
public class SistemaLinear {
    private double A[][];

    public SistemaLinear(double A[][]){
        this.A = A;
    }

    public String solucao_tex(){
        String str = "A solução para o sistema:<br><br><center>";
        str += this.toString();

        //Gauss
        for(int i = 0; i < A.length; i++){
            if(A[i][i] == 0){
                for(int j = i + 1; j < A.length; j++) {
                    if (A[j][i] != 0) {
                        this.operacao_troca(i, j);
                        break;
                    }
                }
                if(A[i][i] == 0){
                    continue;
                }
                if(A[i][i] != 1){
                    this.operacao_div(i, A[i][i]);
                }
            }
            else if (A[i][i] != 1){
                this.operacao_div(i, A[i][i]);
            }

            for(int j = 0; j < A.length; j++){
                if(A[j][i] != 0 && i != j){
                    this.operacao_add(j,-A[j][i], i);
                }
            }

        }

        //Ver se tem linhas todas zeradas com o b diferente de zero
        boolean zero = true;
        for(int i = 0; i < A.length; i++){
            zero = true;
            for(int j = 0; j < A[i].length - 1; j++) {
                if (A[i][j] != 0) {
                    zero = false;
                    break;
                }
            }
            if(zero && (A[i][A[i].length - 1] != 0)){
                str += "O sistema é impossível (SI)";
                return str;
            }
        }
        if(zero){
            str += "O sitema é possível  indeterminado (SPI)";
            return str;
        }

        for(int i = 0; i < A.length; i++){
            str += "x<sub>" + (i+1) + "</sub> = " + Fmt.fmt(A[i][A.length], false) + "<br>" ;
        }
        str += "</center>";

        return str;

    }

    public String gauss_jordan(){
        String str = "Resolvendo o seguinte sistema:<br><br><center>";
        str += this.toString() + "</center>";
        str += "Vamos representar nosso sistema usando a tabela abaixo:<br>";
        str += this.toHTML();

        //Gauss
        for(int i = 0; i < A.length; i++){

            if(A[i][i] == 0){
                for(int j = i + 1; j < A.length; j++) {
                    if (A[j][i] != 0) {
                        str += "Trocando a eq" + (j + 1) + " com a eq" + (i + 1) + "<br>";
                        this.operacao_troca(i, j);
                        str += this.toHTML();
                        break;
                    }
                }
                if(A[i][i] == 0){
                    continue;
                }
                if(A[i][i] != 1){
                    str += "Dividindo a eq" + (i+1) + " por " +  A[i][i] + "<br>";
                    this.operacao_div(i, A[i][i]);
                    str += this.toHTML();
                }
            }
            else if (A[i][i] != 1){
                str += "Dividindo a eq" + (i+1) + " por " +  A[i][i] + "<br>";
                this.operacao_div(i, A[i][i]);
                str += this.toHTML();
            }

            for(int j = i + 1; j < A.length; j++){
                if(A[j][i] != 0){
                    str += "Somando " + Fmt.fmt(-A[j][i], false) + " × eq" + (i+1) + " na eq" + (j+1) + "<br>";
                    this.operacao_add(j,-A[j][i], i);
                    str +=  this.toHTML() + "<br>";
                }
            }

        }

        //Ver se tem linhas todas zeradas com o b diferente de zero
        boolean zero = true;
        for(int i = 0; i < A.length; i++){
            zero = true;
            for(int j = 0; j < A[i].length - 1; j++) {
                if (A[i][j] != 0) {
                    zero = false;
                    break;
                }
            }
            if(zero && (A[i][A[i].length - 1] != 0)){
                str += "O sistema é impossível (SI)";
                return str;
            }
        }
        if(zero){
            str += "O sitema é possível  indeterminado (SPI)";
            return str;
        }

        //Jordam
        for(int i = 1; i < A.length; i++){
            if(A[i][i] == 1) {
                for (int j = i - 1; j >= 0; j--) {
                    if (A[j][i] != 0) {
                        str += "Somando " + Fmt.fmt(-A[j][i], false) + " × eq" + (i + 1) + " na eq" + (j + 1) + "<br>";
                        this.operacao_add(j, -A[j][i], i);
                        str += this.toHTML() + "<br>";
                    }
                }
            }
        }

        str += "A solução do sistema é : <center>";
        for(int i = 0; i < A.length; i++){
            str += "x<sub>" + (i+1) + "</sub> = " + Fmt.fmt(A[i][A.length], false) + "<br>" ;
        }
        str += "</center>";

        return str;

    }

    public String toString(){
        String str = "";
        for(int i = 0; i < A.length; i++){
            str += Fmt.fmt(A[i][0], false) + "x<sub>1</sub>";
            for(int j = 1; j < A[i].length; j++){
                if(j + 1 == A[i].length) {
                    str += " = " + Fmt.fmt(A[i][j], false);
                }
                else {
                    str += Fmt.fmt(A[i][j], true) + "x<sub>" + (j + 1) + "</sub>";
                }
            }
            str += "     <b>[eq" + (i+1) + "]</b><br>";
        }
        return str;
    }

    public String toHTML(){
        String str = "<center><table border=1 style='border-collapse: collapse' align='justify'><tr>";
        String str_eq;
        for(int i = 0; i < A[0].length; i++){
            if(i + 1 == A[0].length) {
                str += "<td bgcolor='#dddddd' align='center'>b</td><td align='center'><b>Id</b></td><td align='center'>Equação</td></tr>";
            }
            else {
                str += "<td align='center'>x<sub>" + (i+1) + "</sub></td>";
            }
        }
        for(int i = 0; i < A.length; i++){
            str_eq = "";
            for(int j = 0; j <A[i].length; j++){
                if(j + 1 == A[i].length) {
                    if(str_eq == "") str_eq = " 0 ";
                    str_eq += " = " + Fmt.fmt(A[i][j], false);
                    str += "<td bgcolor='#dddddd'>" + Fmt.fmt(A[i][j], false) + "</td>";
                }
                else {
                    if(A[i][j] != 0) {
                        str_eq += Fmt.fmt_var(A[i][j], j != 0 && str_eq != "") + "x<sub>" + (j + 1) + "</sub>";
                    }
                    str += "<td>" + Fmt.fmt(A[i][j], false) + "</td>";

                }
            }
            str +="<td><b>" + (i+1) + "</b></td><td>" + str_eq + "</td></tr>";
        }
        str += "</table></center><br>";
        return str;
    }

    //Multiplica uma linha por um numero
    private void operacao_mul(int eq, double a){
        for(int i = 0; i < A[eq].length; i++){
            A[eq][i] *= a;
        }
    }

    //Divide uma linha por um número
    private void operacao_div(int eq, double a){
        for(int i = 0; i < A[eq].length; i++){
            A[eq][i] /= a;
        }
    }

    //Soma 5 * linha i na eq
    private void operacao_add(int eq, double a, int k){
        for(int i = 0; i < A[eq].length; i++){
            A[eq][i] += a * A[k][i];
        }
    }

    //Troca linha p com linha q
    private void operacao_troca(int p, int q){
        double aux;
        for(int i = 0; i < A[p].length; i++){
            aux = A[q][i];
            A[q][i] = A[p][i];
            A[p][i] = aux;

        }
    }

}
