package br.imisturebas.zechinelao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import br.imisturebas.zechinelao.R;

public class Eq2Activity extends AppCompatActivity {

    private Button btn;
    private EditText edit_a;
    private EditText edit_b;
    private EditText edit_c;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eq2);

        //Coeficientes
        edit_a = (EditText) this.findViewById(R.id.editText_a);
        edit_b = (EditText) this.findViewById(R.id.editText_b);
        edit_c = (EditText) this.findViewById(R.id.editText_c);

        spinner = (Spinner) this.findViewById(R.id.spinner);

        //Solução
        btn = (Button) this.findViewById(R.id.btn_solucao);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                double a, b, c;
                String str_a = "0", str_b = "0", str_c = "0";
                if (!edit_a.getText().toString().equals("") && !edit_a.getText().toString().equals("-")) {
                    str_a = edit_a.getText().toString();
                }
                if (!edit_b.getText().toString().equals("") && !edit_b.getText().toString().equals("-")) {
                    str_b = edit_b.getText().toString();
                }
                if (!edit_c.getText().toString().equals("") && !edit_c.getText().toString().equals("-")) {
                    str_c = edit_c.getText().toString();
                }

                if (Double.valueOf(str_a) == 0) {
                    Toast.makeText(Eq2Activity.this, "O valor de a não pode ser zero", Toast.LENGTH_SHORT).show();
                } else {
                    a = Double.valueOf(str_a);
                    b = Double.valueOf(str_b);
                    c = Double.valueOf(str_c);
                    Intent intent = new Intent(Eq2Activity.this, SolucaoActivity.class);
                    Eq2 equacao = new Eq2(a,b,c);
                    switch ((int)spinner.getSelectedItemId()){
                        case 0:
                            intent.putExtra("solução", equacao.solucao_tex());
                            Eq2Activity.this.startActivity(intent);
                            break;
                        case 1:
                            intent.putExtra("solução", equacao.solucao_bhaskara_tex());
                            Eq2Activity.this.startActivity(intent);
                            break;
                        case 2:
                            intent.putExtra("solução", equacao.solucao_complemento_tex());
                            Eq2Activity.this.startActivity(intent);
                            break;
                        case 3:
                            if(b == 0 || c == 0){
                                intent.putExtra("solução", equacao.solucao_incompleta_tex());
                                Eq2Activity.this.startActivity(intent);
                            }
                            else{
                                Toast.makeText(Eq2Activity.this, "b ou/e c tem que ser zero", Toast.LENGTH_SHORT).show();
                            }
                            break;
                    }

                }
            }

        });
    }
}
