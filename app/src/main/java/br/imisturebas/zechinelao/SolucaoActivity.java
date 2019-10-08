package br.imisturebas.zechinelao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import br.imisturebas.zechinelao.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class SolucaoActivity extends AppCompatActivity {

    private WebView webview;
    private AdView adview;

    String content;
    final String str = "<style type=\"text/css\">" +
            "#sem_esq {border-left:0px; border-right:1px solid black; border-bottom:1px solid black; border-top:1px solid black;}" +
            "#sem_dir {border-right:0px; border-left:1px solid black; border-bottom:1px solid black; border-top:1px solid black;}" +
            "#sem_dir_esq{ border-right:0px; border-left:0px; border-bottom:1px solid black; border-top:1px solid black; }" +
            "#so_esq{ border-right:1px solid black; border-left:0px; border-bottom:0px; border-top:0px;}" +
            "#so_dir{ border-right:0px; border-left:1px solid black; border-bottom:0px; border-top:0px;}" +
            "#so_esq_dir{ border-right:1px solid black;border-left:1px solid black; border-bottom:0px;border-top:0px;}" +
            "#nada{ border-right:0px; border-left:0px; border-bottom:0px; border-top:0px;} </style>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_solucao);
        webview = (WebView) this.findViewById(R.id.webView_sol);

        content = "<!DOCTYPE html><html><head>" + str;

        content += this.getIntent().getStringExtra("solução");

        content += "<br><br><br><br><br></body></html>";


        webview.loadData(content, "text/html; charset=utf-8", "UTF-8");


        adview = (AdView) this.findViewById(R.id.adView);
        final AdRequest.Builder adReq = new AdRequest.Builder();
        final AdRequest adRequest = adReq.build();

        adview.loadAd(adRequest);
    }
}
