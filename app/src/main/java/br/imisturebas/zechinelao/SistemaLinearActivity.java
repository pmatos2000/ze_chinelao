package br.imisturebas.zechinelao;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import br.imisturebas.zechinelao.R;

public class SistemaLinearActivity extends AppCompatActivity {
    private int total_eq = 2;
    private LinearLayout ll_eq;
    private Button btn_mais_eq;
    private Button btn_menos_eq;
    private Button btn_solucao;
    private Button btn_limpa;
    private Spinner sp;
    private int tamanho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_sistema_linear);
        ll_eq = (LinearLayout) this.findViewById(R.id.ll_eq);
        btn_mais_eq = (Button) this.findViewById(R.id.btn_mais_eq);
        btn_menos_eq = (Button) this.findViewById(R.id.btn_menos_eq);
        btn_solucao = (Button) this.findViewById(R.id.btn_solucao);
        btn_limpa = (Button) this.findViewById(R.id.btn_limpa);
        sp = (Spinner) this.findViewById(R.id.sp_sistema);


        int widht = this.getResources().getDisplayMetrics().widthPixels;
        tamanho = 49 * widht/162;
        if(tamanho > 200) tamanho = 200;

        if (savedInstanceState != null) {
            total_eq = savedInstanceState.getInt("total_ordem", 2);
        }
        else{
            total_eq = 2;
        }

        LinearLayout ll;
        TextView text;
        EditText edit;
        String aux;
        for(int i = 0; i <= total_eq ; i++){
            ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.VERTICAL);
            text = new TextView(this);
            text.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            if(total_eq == i){
                aux = "b";
                ll.setBackgroundColor(Color.rgb(221, 221, 221));
            }
            else {
                aux = "x" + (i + 1);
            }
            text.setText(aux);
            ll.addView(text);
            for(int j = 0; j < total_eq; j++){
                edit = new EditText(this);
                edit.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
                edit.setWidth(tamanho);
                edit.setHint("eq" + (j + 1) + " " + aux);
                if (savedInstanceState != null) {
                    edit.setText(savedInstanceState.getString("" + i + " " + j, ""));
                }
                ll.addView(edit);
            }
            ll_eq.addView(ll);
        }


        btn_mais_eq.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                for (int i = 0; i <= total_eq; i++) {
                    EditText e = new EditText(SistemaLinearActivity.this);
                    e.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
                    e.setWidth(tamanho);
                    if (i == total_eq) {
                        e.setHint("eq" + (total_eq + 1) + " b");
                    } else {
                        e.setHint("eq" + (total_eq + 1) + " x" + (i + 1));
                    }
                    ((LinearLayout) ll_eq.getChildAt(i)).addView(e);
                }

                total_eq++;

                LinearLayout ll_novo = new LinearLayout(SistemaLinearActivity.this);
                ll_novo.setOrientation(LinearLayout.VERTICAL);
                TextView t = new TextView(SistemaLinearActivity.this);
                t.setText("x" + (total_eq));
                t.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                ll_novo.addView(t);

                for (int i = 0; i < total_eq; i++) {
                    EditText e = new EditText(SistemaLinearActivity.this);
                    e.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
                    e.setWidth(tamanho);
                    e.setHint("eq" + (i + 1) + " x" + (total_eq));
                    ll_novo.addView(e);
                }
                ll_eq.addView(ll_novo, total_eq - 1);


            }
        });


        btn_menos_eq.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(total_eq > 2) {
                    for (int i = 0; i <= total_eq; i++) {
                        ((LinearLayout) ll_eq.getChildAt(i)).removeViewAt(total_eq);
                    }
                    ll_eq.removeViewAt(total_eq - 1);
                    total_eq--;
                }
                else{
                    Toast.makeText(SistemaLinearActivity.this, "O sistema precisa ser pelo menos 2x2", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_limpa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LinearLayout aux;
                for(int i = 0; i < total_eq + 1; i++){
                    aux = (LinearLayout) ll_eq.getChildAt(i);
                    for(int j = 0 ; j < total_eq; j++){
                        ((EditText)aux.getChildAt(j+1)).setText("");
                    }
                }
            }
        });

        btn_solucao.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                double A[][] = new double[total_eq][total_eq+1];
                double num;
                EditText e;
                for(int i = 0; i < total_eq; i++){
                    for(int j = 0; j <= total_eq; j++){
                        e = (EditText)((LinearLayout) ll_eq.getChildAt(j)).getChildAt(i+1);
                        if (!e.getText().toString().equals("") && !e.getText().toString().equals("-") && !e.getText().toString().equals(".")&& !e.getText().toString().equals(",")) {
                            num = Double.valueOf(e.getText().toString());
                        }
                        else{
                            if(j == total_eq){
                                Toast.makeText(SistemaLinearActivity.this, "Formato invalido em eq" + (i + 1) + " b", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(SistemaLinearActivity.this, "Formato invalido em eq" + (i + 1) + " x" + (j + 1), Toast.LENGTH_SHORT).show();
                            }
                            return;
                        }
                        A[i][j] = num;
                    }
                }
                Intent intent = new Intent(SistemaLinearActivity.this, SolucaoActivity.class);
                SistemaLinear sistema = new SistemaLinear(A);
                int id = (int) sp.getSelectedItemId();
                if(id == 0){
                    intent.putExtra("solução", sistema.solucao_tex());
                }
                else{
                    intent.putExtra("solução", sistema.gauss_jordan());
                }
                SistemaLinearActivity.this.startActivity(intent);

            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        for (int i = 0; i <= total_eq; i++) {
            for (int j = 0; j < total_eq; j++) {
                savedInstanceState.putString("" + i + " " + j, ((EditText) ((LinearLayout) ll_eq.getChildAt(i)).getChildAt(j+1)).getText().toString());
            }
        }
        savedInstanceState.putInt("total_eq", total_eq);
    }
}
