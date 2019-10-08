package br.imisturebas.zechinelao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import br.imisturebas.zechinelao.R;

public class MDCActivity extends AppCompatActivity {
    private Button btn_solucao;
    private Button btn_mais;
    private Button btn_menos;
    private Button btn_limpa;
    private LinearLayout ll;
    private Spinner sp;
    private int total = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mdc);
        ll = (LinearLayout) this.findViewById(R.id.linearLayout);
        btn_solucao = (Button) this.findViewById(R.id.btn_solucao);
        btn_mais = (Button) this.findViewById(R.id.btn_mais);
        btn_menos = (Button) this.findViewById(R.id.btn_menos);
        btn_limpa = (Button) this.findViewById(R.id.btn_limpa);
        sp = (Spinner) this.findViewById(R.id.spinner2);


        if (savedInstanceState != null) {
            total = savedInstanceState.getInt("total", 2);
        }
        else{
            total = 2;
        }


        TextView text;
        EditText edit;
        for(int i = 0; i < total; i++){
            text = new TextView(MDCActivity.this);
            text.setText("Entre com o " + (i+1) + "º número:");
            ll.addView(text);
            edit = new EditText(this);
            edit.setInputType(InputType.TYPE_CLASS_NUMBER);
            edit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(9)});
            if (savedInstanceState != null) {
                edit.setText(savedInstanceState.getString("" + i));
            }
            ll.addView(edit);
        }

        btn_mais.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                total++;
                TextView text = new TextView(MDCActivity.this);
                text.setText("Entre com o " + total + "º número:");
                ll.addView(text);
                EditText edit = new EditText(MDCActivity.this);
                edit.setInputType(InputType.TYPE_CLASS_NUMBER);
                edit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(9)});
                ll.addView(edit);
            }
        });


        btn_menos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (total == 2) {
                    Toast.makeText(MDCActivity.this, "o calculo do MDC precisa de pelo menos 2 números", Toast.LENGTH_SHORT).show();
                } else {
                    ll.removeViewAt(total * 2);
                    ll.removeViewAt(total * 2 - 1);
                    total--;
                }
            }
        });


        btn_limpa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                for(int i = 0; i < total; i++){
                    ((EditText)ll.getChildAt(2*i+2)).setText("");
                }
            }
        });

        btn_solucao.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int u[] = new int[total];
                for(int i = 0; i < total; i++){
                    String str = "0" + ((EditText)ll.getChildAt(2*i+2)).getText().toString();
                    int num = Integer.parseInt(str);
                    if(num == 0){
                        Toast.makeText(MDCActivity.this, "O " + (i+1) + "º tem que ser diferente de zero", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    u[i] = num;
                }
                Intent intent = new Intent(MDCActivity.this, SolucaoActivity.class);
                    if(sp.getSelectedItemId() == 0) intent.putExtra("solução", MDC.solucao_tex(u));
                    else if(sp.getSelectedItemId() == 1 ) intent.putExtra("solução", MDC.solucao_fatoracao_tex(u));
                    else{
                        if(total > 2){
                            Toast.makeText(MDCActivity.this, "Para simplificar para o lado do desenvolverdor o algoritimo de euclides não aceita mais de 2 números", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        intent.putExtra("solução", MDC.solucao_euclides_tex(u[0], u[1]));
                    }

                MDCActivity.this.startActivity(intent);
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        for (int i = 0; i < total; i++) {
            savedInstanceState.putString("" + i, ((EditText) (ll.getChildAt(2 * i + 2))).getText().toString());
        }
        savedInstanceState.putInt("total", total);
    }

}
