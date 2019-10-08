package br.imisturebas.zechinelao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import br.imisturebas.zechinelao.R;


public class MenuActivity extends AppCompatActivity {

    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_menu);
        ll = (LinearLayout) this.findViewById(R.id.ll_menu);
        Button btn;



        btn = new Button(this);
        btn.setText("Divisores");
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, DivisoresActivity.class);
                MenuActivity.this.startActivity(intent);
            }
        });
        ll.addView(btn);

        btn = new Button(this);
        btn.setText("Equação do 2º Grau");
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, Eq2Activity.class);
                MenuActivity.this.startActivity(intent);
            }

        });
        ll.addView(btn);

        btn = new Button(this);
        btn.setText("Fatoração");
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, FatoracaoActivity.class);
                MenuActivity.this.startActivity(intent);
            }

        });
        ll.addView(btn);

        btn = new Button(this);
        btn.setText("Matriz");
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, MatrizActivity.class);
                MenuActivity.this.startActivity(intent);
            }

        });
        ll.addView(btn);


        btn = new Button(this);
        btn.setText("MDC");
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, MDCActivity.class);
                MenuActivity.this.startActivity(intent);
            }

        });
        ll.addView(btn);

        btn = new Button(this);
        btn.setText("MMC");
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, MMCActivity.class);
                MenuActivity.this.startActivity(intent);
            }
        });
        ll.addView(btn);

        btn = new Button(this);
        btn.setText("Sistema Linear");
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, SistemaLinearActivity.class);
                MenuActivity.this.startActivity(intent);
            }
        });
        ll.addView(btn);

    }
}