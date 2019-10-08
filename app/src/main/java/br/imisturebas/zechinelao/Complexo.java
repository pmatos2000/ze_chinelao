package br.imisturebas.zechinelao;


public class Complexo {
    private double r; //Parte Real
    private double i; //Parte Imaginaria

    public Complexo(){
        r = 0;
        i = 0;
    }

    public Complexo(double r, double i){
        this.r = r;
        this.i = i;
    }


    public String paraString(Boolean Sinal){
        if(r == 0 && i == 0){
            return Fmt.fmt(0,Sinal);
        }
        else if(i == 0){
            return Fmt.fmt(r, Sinal);
        }
        else if(r == 0){
            return Fmt.fmt(i, Sinal) + "i";
        }
        else{
            return Fmt.fmt(r,Sinal) + Fmt.fmt(i, true) + "i";
        }
    }

    public String paraString(){
        return this.paraString(false);
    }

    public void add (double a){
        r += a;
    }

    public  void add (Complexo b){
        r += b.r;
        i += b.i;
    }

    public void mul (double a){
        r *= a;
        i *= a;
    }


}
