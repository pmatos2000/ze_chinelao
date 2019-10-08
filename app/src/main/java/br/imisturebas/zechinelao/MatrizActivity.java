package br.imisturebas.zechinelao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import br.imisturebas.zechinelao.R;
import com.google.android.gms.common.api.GoogleApiClient;

public class MatrizActivity extends AppCompatActivity {
    private int total_ordem;
    private LinearLayout ll_matriz;
    private Button btn_mais_ordem;
    private Button btn_menos_ordem;
    private Button btn_solucao;
    private Button btn_limpa;
    private Spinner sp;
    private int tamanho;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_matriz);
        ll_matriz = (LinearLayout) this.findViewById(R.id.ll_matriz);
        btn_mais_ordem = (Button) this.findViewById(R.id.btn_mais_ordem);
        btn_menos_ordem = (Button) this.findViewById(R.id.btn_menos_ordem);
        btn_solucao = (Button) this.findViewById(R.id.btn_solucao);
        btn_limpa = (Button) this.findViewById(R.id.btn_limpa);
        sp = (Spinner) this.findViewById(R.id.sp_matriz);

        int widht = this.getResources().getDisplayMetrics().widthPixels;
        tamanho = 49 * widht / 162;
        if (tamanho > 200) tamanho = 200;

        if (savedInstanceState != null) {
            total_ordem = savedInstanceState.getInt("total_ordem", 2);
        }
        else{
            total_ordem = 2;
        }

        LinearLayout ll;
        EditText edit;
        for (int i = 0; i < total_ordem; i++) {
            ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.HORIZONTAL);
            for (int j = 0; j < total_ordem; j++) {
                edit = new EditText(this);
                edit.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
                edit.setWidth(tamanho);
                edit.setHint("a " + (i + 1) + " " + (j + 1));
                if (savedInstanceState != null) {
                    edit.setText(savedInstanceState.getString("a " + i + " " + j));
                }
                ll.addView(edit);
            }
            ll_matriz.addView(ll);
        }


        btn_mais_ordem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LinearLayout ll;
                EditText edit;

                ll = new LinearLayout(MatrizActivity.this);
                ll.setOrientation(LinearLayout.HORIZONTAL);
                for (int i = 0; i < total_ordem; i++) {
                    edit = new EditText(MatrizActivity.this);
                    edit.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
                    edit.setWidth(tamanho);
                    edit.setHint("a " + (total_ordem + 1) + " " + (i + 1));
                    ll.addView(edit);
                }
                ll_matriz.addView(ll);

                total_ordem++;

                for (int i = 0; i < total_ordem; i++) {
                    edit = new EditText(MatrizActivity.this);
                    edit.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
                    edit.setWidth(tamanho);
                    edit.setHint("a " + (i + 1) + " " + total_ordem);
                    ((LinearLayout) ll_matriz.getChildAt(i)).addView(edit);
                }


            }
        });


        btn_menos_ordem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (total_ordem > 1) {
                    total_ordem--;
                    for (int i = 0; i < total_ordem; i++) {
                        ((LinearLayout) ll_matriz.getChildAt(i)).removeViewAt(total_ordem);
                    }
                    ll_matriz.removeViewAt(total_ordem);
                } else {
                    Toast.makeText(MatrizActivity.this, "Não tem como remover mais elementos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_limpa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                for (int i = 0; i < total_ordem; i++) {
                    for (int j = 0; j < total_ordem; j++) {
                        ((EditText) ((LinearLayout) ll_matriz.getChildAt(j)).getChildAt(i)).setText("");
                    }
                }
            }
        });

        btn_solucao.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                double A[][] = new double[total_ordem][total_ordem];
                double num;
                EditText e;
                for (int i = 0; i < total_ordem; i++) {
                    for (int j = 0; j < total_ordem; j++) {
                        e = (EditText) ((LinearLayout) ll_matriz.getChildAt(i)).getChildAt(j);
                        if (!e.getText().toString().equals("") && !e.getText().toString().equals("-") && !e.getText().toString().equals(".") && !e.getText().toString().equals(",")) {
                            num = Double.valueOf(e.getText().toString());
                        } else {
                            Toast.makeText(MatrizActivity.this, "Formato invalido em a " + (i + 1) + " " + (j + 1), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        A[i][j] = num;
                    }
                }
                int a = (int)sp.getSelectedItemId();
                if(a == 0){
                    Matriz matriz = new Matriz(A);
                    Intent intent = new Intent(MatrizActivity.this, SolucaoActivity.class);
                    intent.putExtra("solução", matriz.inversa_tex());
                    MatrizActivity.this.startActivity(intent);
                }
                else if (a == 1){
                    Matriz matriz = new Matriz(A);
                    Intent intent = new Intent(MatrizActivity.this, SolucaoActivity.class);
                    intent.putExtra("solução", matriz.determinante());
                    MatrizActivity.this.startActivity(intent);
                }
                else {
                    if(A.length == 3){
                        Matriz matriz = new Matriz(A);
                        Intent intent = new Intent(MatrizActivity.this, SolucaoActivity.class);
                        intent.putExtra("solução", matriz.determinante_3x3());
                        MatrizActivity.this.startActivity(intent);
                    }
                    else{
                        Toast.makeText(MatrizActivity.this, "Para usar a Regra de Sarrus a matriz tem que ser da ordem 3x3", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        for (int i = 0; i < total_ordem; i++) {
            for (int j = 0; j < total_ordem; j++) {
                savedInstanceState.putString("a " + i + " " + j, ((EditText) ((LinearLayout) ll_matriz.getChildAt(i)).getChildAt(j)).getText().toString());
            }
        }
        savedInstanceState.putInt("total_ordem", total_ordem);
    }
}
