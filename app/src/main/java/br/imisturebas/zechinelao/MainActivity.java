package br.imisturebas.zechinelao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.imisturebas.zechinelao.R;


public class MainActivity extends AppCompatActivity {

    private Button btn_menu;
    private Button btn_sobre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        btn_menu = (Button) this.findViewById(R.id.btn_menu);
        btn_sobre = (Button) this.findViewById(R.id.btn_sobre);

        btn_menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        btn_sobre.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SobreActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });


    }
}