package com.example.weker.mdmtest;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



public class MainActivity extends AppCompatActivity {

    private Button btnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.btnTest = (Button) findViewById(R.id.btnTest);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread t = new Thread() {
                    @Override
                    public void run() {
                        try {
                            StringBuilder result = new StringBuilder();
                            URL url = new URL("http://192.168.10.162/test8.php");
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            conn.setRequestMethod("GET");
                            conn.setDoInput(true);
                            conn.connect();

                            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream())); //creates buffer reader,.
                            String line;
                            while ((line = reader.readLine()) != null) {
                                result.append(line);
                            }
                            System.out.println(result.toString());
                        } catch (Exception e) {
                            Log.d("a", e.toString());
                        }
                    }


                };
                t.start();
            }

        });
    }
}