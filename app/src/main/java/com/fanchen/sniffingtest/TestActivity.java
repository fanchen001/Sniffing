package com.fanchen.sniffingtest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.fanchen.sniffing.*;
import com.fanchen.sniffing.web.SniffingUtil;

import java.util.List;

public class TestActivity extends Activity implements SniffingUICallback {

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        dialog = new ProgressDialog(this);
        dialog.setTitle("正在处理....");
    }

    public void onClick(View v){
        EditText editText = findViewById(R.id.editText);
        if(v.getId() == R.id.button1){
            SniffingUtil.get().activity(this).referer("http://aaqqw.com/vod-play-id-9465-src-2-num-1.html").callback(this).url(editText.getText().toString().trim()).start();
        }else  if(v.getId() == R.id.button2){
            com.fanchen.sniffing.x5.SniffingUtil.get().activity(this).referer("http://aaqqw.com/")
                    .callback(this).connTimeOut(30 * 1000).readTimeOut(30 * 1000)
                    .filter(new DefaultFilter()).url(editText.getText().toString().trim()).start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SniffingUtil.get().releaseAll();
        com.fanchen.sniffing.x5.SniffingUtil.get().releaseAll();
    }

    @Override
    public void onSniffingStart(View webView, String url) {
        dialog.show();
    }

    @Override
    public void onSniffingFinish(View webView, String url) {
        dialog.dismiss();
    }

    @Override
    public void onSniffingSuccess(View webView, String url, List<SniffingVideo> videos) {
        TextView textView = findViewById(R.id.textView);
        textView.setText(videos.toString());
    }

    @Override
    public void onSniffingError(View webView, String url, int errorCode) {
        Toast.makeText(this,"解析失败...",Toast.LENGTH_SHORT).show();
    }

}
