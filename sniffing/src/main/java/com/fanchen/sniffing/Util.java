package com.fanchen.sniffing;

import android.text.TextUtils;

import com.fanchen.sniffing.node.Node;
import com.tencent.smtt.sdk.WebView;

import java.net.HttpURLConnection;
import java.net.URL;

public class Util {

    public static final String HTMLFLAG = "<SniffingVideo>SniffingVideo</SniffingVideo>";

    public static Object[] getContent(String url) {
        Object[] objects = new Object[2];
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) new URL(url).openConnection();
            urlConnection.setRequestMethod("HEAD");
            objects[0] = urlConnection.getContentLength();
            objects[1] = urlConnection.getContentType();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        if (objects[0] == null) objects[0] = -1;
        if (objects[1] == null) objects[1] = "";
        return objects;
    }

    public static String containsType(String url) {
        for (String type : SniffingFilter.DEFAULT_TYPE) {
            if (url.contains(type)) {
                return type;
            }
        }
        return null;
    }

    public static String extracIframe(Node ifran) {
        String aClass = ifran.attr("class");
        String id = ifran.attr("id");
        String name = ifran.attr("name");
        if ("100%".equals(ifran.attr("width")) && "100%".equals(ifran.attr("height"))) {
            return ifran.attr("src");
        }else if ("true".equals(ifran.attr("allowfullscreen"))) {
            return ifran.attr("src");
        }else if (lowerContains(aClass, "player") || lowerContains(id, "player") || lowerContains(name, "player")) {
            return ifran.attr("src");
        } else if (lowerContains(aClass, "video") || lowerContains(id, "video") || lowerContains(name, "video")) {
            return ifran.attr("src");
        } else if (lowerContains(aClass, "m3u") || lowerContains(id, "m3u") || lowerContains(name, "m3u")) {
            return ifran.attr("src");
        }
        return null;
    }

    public static boolean lowerContains(String value, String key) {
        return value.toLowerCase().contains(key);
    }

    /**
     * 對url進行包裝
     *
     * @param url
     * @return
     */
    public static  String warpUrl(String mURL,String url) {
        try {
            if (url.startsWith("//")) {
                url = "http:" + url;
            } else if (url.startsWith("/")) {
                String[] split = mURL.split("/");
                url = split[0] + "//" + split[2] + url;
            } else if (url.startsWith(".") && (mURL.contains("url=") || mURL.contains("v="))) {
                String[] split = mURL.split("=");
                int i = split[0].lastIndexOf("/");
                url = split[0].substring(0, i) + url.substring(1);
            } else if (url.startsWith(".")) {
                int i = mURL.lastIndexOf("/");
                url = mURL.substring(0, i) + url.substring(1);
            }else if(url.startsWith("http")){
                return url;
            }else{
                String[] split = mURL.split("/");
                return split[0] + "//" + split[2] + "/" + url;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     * 執行js 獲取 html
     *
     * @param js
     */
    public static void evalScript(WebView view, String js) {
        if (TextUtils.isEmpty(js) || view == null) return;
        String newJs = "javascript:" + js + "(document.getElementsByTagName('html')[0].innerHTML + '" + HTMLFLAG + "');";
        view.loadUrl(newJs);
    }

    /**
     * 執行js 獲取 html
     * @param view
     * @param js
     */
    public static void evalScript(android.webkit.WebView view, String js) {
        if (TextUtils.isEmpty(js) || view == null) return;
        String newJs = "javascript:" + js + "(document.getElementsByTagName('html')[0].innerHTML + '" + HTMLFLAG + "');";
        view.loadUrl(newJs);
    }

}
