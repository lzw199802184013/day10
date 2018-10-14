package com.example.lzw.day10.net;

import android.os.Handler;
import android.os.Message;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class Helper {
    private static final int HTTP_FAILURE = 100;
    private static final int HTTP_SUCCESS = 101;

    //get请求
    public Helper get(String url) {
        OkHttpClient client = new OkHttpClient();
        Request resquest = new Request.Builder()
                .get().url(url).build();

        client.newCall(resquest).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                handler.sendEmptyMessage(HTTP_FAILURE);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String string = response.body().string();
                Message message = Message.obtain();
                message.what = HTTP_SUCCESS;
                message.obj = string;
                handler.sendMessage(message);
            }
        });
        return  this;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case HTTP_SUCCESS:
                    String data = (String) msg.obj;
                    listener.success(data);
                    break;
                case HTTP_FAILURE:
                    listener.fail();
                    break;
            }

        }
    };
    private HttpListener listener;

    public void result(HttpListener listener) {

        this.listener = listener;
    }

    public interface HttpListener {
        void success(String data);

        void fail();
    }
}
