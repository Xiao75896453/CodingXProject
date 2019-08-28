package com.example.codingxproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class DetailDrugInfo extends AppCompatActivity {

    private WebView webDrugDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_drug_info);
        String destinSite = getIntent().getStringExtra("destinSite"); //"http://140.116.253.135/NewHomePage/massrefer_handout%20forms.asp?D_id=583325&SearchItem1=%BD%C3%B1%D0%B3%E6%B"
        webDrugDetail = (WebView) findViewById(R.id.webDrugDetail);

        webDrugDetail.setWebViewClient(new WebViewClient());
        webDrugDetail.getSettings().setUseWideViewPort(true);
        webDrugDetail.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        webDrugDetail.getSettings().setLoadWithOverviewMode(true);

        webDrugDetail.loadUrl(destinSite);
    }
}