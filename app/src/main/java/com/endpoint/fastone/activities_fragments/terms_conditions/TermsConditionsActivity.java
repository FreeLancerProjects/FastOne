package com.endpoint.fastone.activities_fragments.terms_conditions;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.endpoint.fastone.R;
import com.endpoint.fastone.language.Language_Helper;
import com.endpoint.fastone.models.AppDataModel;
import com.endpoint.fastone.remote.Api;
import com.endpoint.fastone.tags.Tags;

import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TermsConditionsActivity extends AppCompatActivity {

    private TextView tv_title;
    private ImageView arrow;
    private LinearLayout ll_back;
    private String current_lang, url;
    private WebView webView;

    @Override
    protected void attachBaseContext(Context base) {

        super.attachBaseContext(Language_Helper.updateResources(base, Language_Helper.getLanguage(base)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_conditions);
        initView();
    }




    private void initView() {
        Paper.init(this);
        current_lang = Paper.book().read("lang", "ar");
        arrow = findViewById(R.id.arrow);
        tv_title=findViewById(R.id.tv_title);
        if (current_lang.equals("ar")) {
            arrow.setImageResource(R.drawable.ic_right_arrow);
            arrow.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_IN);

        } else {
            arrow.setImageResource(R.drawable.ic_left_arrow);
            arrow.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_IN);


        }
        updateTermsContent();

        tv_title = findViewById(R.id.tv_title);

        ll_back = findViewById(R.id.ll_back);

        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                if (current_lang.equals("ar")) {
                    overridePendingTransition(R.anim.from_left, R.anim.to_right);

                } else {
                    overridePendingTransition(R.anim.from_right, R.anim.to_left);

                }
            }
        });
        webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }


    private void updateTermsContent() {
        if (current_lang.equals("ar")) {
            url = "http://quick.com.sa/Web/appDetails?type=3&lang=ar";
            tv_title.setText("الشروط والأحكام");

        } else {
            url="http://quick.com.sa/Web/appDetails?type=3&lang=en";
            tv_title.setText("Terms & Condition");
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (current_lang.equals("ar")) {
            overridePendingTransition(R.anim.from_left, R.anim.to_right);

        } else {
            overridePendingTransition(R.anim.from_right, R.anim.to_left);

        }
    }
}
