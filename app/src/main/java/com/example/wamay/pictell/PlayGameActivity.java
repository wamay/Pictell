package com.example.wamay.pictell;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;


public class PlayGameActivity extends AppCompatActivity {

    private SimpleDateFormat dataFormat = new SimpleDateFormat("mm:ss", Locale.US);
    private TextView timerText;

    private Handler handler = new Handler();
    private int count;
    private int period = 1000;
    private long restTime = 180;
    private boolean isWorkTimer = false;

    AlertDialog.Builder alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        alert = new AlertDialog.Builder(this);

        timerText = findViewById(R.id.timerText);
        timerText.setText(dataFormat.format(restTime * period));

        Button startTimer = findViewById(R.id.startTimerButton);
        startTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isWorkTimer) {
                    handler.post(run);
                    isWorkTimer = true;
                }
            }
        });

        Button stopTimer = findViewById(R.id.stopTimerButton);
        stopTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isWorkTimer) {
                    handler.removeCallbacks(run);
                    timerText.setText(dataFormat.format(restTime * period));
                    isWorkTimer = false;
                }
            }
        });

        Button plusTime = findViewById(R.id.plusSecondButton);
        plusTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(restTime < 890) {
                    restTime+=10;
                    timerText.setText(dataFormat.format(restTime * period));
                }
            }
        });

        Button minusTime = findViewById(R.id.minusSecondButton);
        minusTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(restTime > 10) {
                    restTime-=10;
                    timerText.setText(dataFormat.format(restTime * period));
                }
            }
        });

        Button finishButton = findViewById(R.id.finishTimerButton);
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(restTime > 1) {
                    handler.post(run);
                    isWorkTimer = true;
                    restTime = 1;
                }
            }
        });

        Button showAnswerButton = findViewById(R.id.showAnswerButton);
        showAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), ShowAnswerActivity.class);
                startActivity(intent);
            }
        });
    }

    //1秒間隔で実行される処理
    private Runnable run = new Runnable() {
        @Override
        public void run() {
            restTime--;
            timerText.setText(dataFormat.format(restTime*period));
            handler.postDelayed(this,period);

            if(restTime < 1){
                handler.removeCallbacks(run);
                timerText.setText(dataFormat.format(0));

                alert.setMessage("タイマーが終了しました")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dialogのボタンを押したときの処理
                    }
                });
                alert.show();
                //ToDo タイマー終了時に音を鳴らす処理を追加する
            }
        }
    };

}
