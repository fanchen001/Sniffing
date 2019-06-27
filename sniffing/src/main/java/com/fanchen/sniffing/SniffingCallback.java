package com.fanchen.sniffing;

import android.view.View;

import java.util.List;

public interface SniffingCallback {

    /**
     * 视频嗅探成功
     * @param webView
     * @param url
     * @param videos
     */
    void onSniffingSuccess(View webView,String url,List<SniffingVideo> videos);

    /**
     * 视频嗅探失败成功
     * @param webView
     * @param url
     * @param errorCode
     */
    void onSniffingError(View webView, String url, int errorCode);

}
