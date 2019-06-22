package com.example.android.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class ResultActivity extends AppCompatActivity {

// HTTP code
//https://www.youtube.com/watch?v=oGWJ8xD2W6k
// parse json file https://www.youtube.com/watch?v=y2xtLqP8dSQ

    private TextView mTextViewResult;

    public String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent =getIntent();
          name =intent.getStringExtra("Name");
    }

    public void getResult(View view) {
        mTextViewResult = findViewById(R.id.text_view_result);

        OkHttpClient client = new OkHttpClient();

        String url = "https://skinshit2.appspot.com/classify";

        long X= System.currentTimeMillis();
        String Y=String.valueOf(X);

        RequestBody formBody = new FormBody.Builder()
                .add("Name",name )
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();

                    ResultActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTextViewResult.setText(myResponse);
                        }
                    });
                }
            }
        });
        Toast.makeText(ResultActivity.this, "Name is "+ name , Toast.LENGTH_SHORT).show();


    }
}
