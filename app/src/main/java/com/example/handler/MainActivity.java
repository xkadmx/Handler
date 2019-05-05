package com.example.handler;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView myText;
    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myText = (TextView) findViewById(R.id.myText);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    myText.setText((String) msg.obj);
                } else if (msg.what == 2) {
                    Log.i("Handler", "Wiadomość o id 2 dotarła");

                }
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.obj = "Hello! Wiadomość przekazana przez obiekt handler";
                message.what = 1;
                handler.sendMessageDelayed(message, 5000);
            }
        }
        ).start();


        new Thread(new Runnable() {
            @Override
            public void run() {

                handler.sendEmptyMessage(2);

            }
        }
        ).start();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "Opóźniony Toast!", Toast.LENGTH_LONG).show();

            }
        }, 5000);
    }
}