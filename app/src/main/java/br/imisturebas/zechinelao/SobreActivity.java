package br.imisturebas.zechinelao;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.imisturebas.zechinelao.R;

public class SobreActivity extends AppCompatActivity {
    private Button btn_email;
    private Button btn_blog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);
        btn_email = (Button) this.findViewById(R.id.btn_email);
        btn_blog = (Button) this.findViewById(R.id.btn_blog);

        btn_email.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","pmatos2000@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Zé Chinelão");
                startActivity(Intent.createChooser(emailIntent, "Enviando email..."));
            }
        });

        btn_blog.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Uri uri = Uri.parse("http://imisturebas.blogspot.com.br");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                SobreActivity.this.startActivity(intent);
            }
        });
    }
}
