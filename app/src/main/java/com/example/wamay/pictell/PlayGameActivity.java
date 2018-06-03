package com.example.wamay.pictell;

import android.os.CountDownTimer;
import android.os.Handler;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

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
            }
        }
    };





    /*


    public class MyCountDownTimer extends CountDownTimer {

        private long mRestTime = -1;
        private boolean isWorkTimer = false;

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            mRestTime = millisInFuture;
        }

        @Override
        public void onFinish() {
            // カウントダウン完了後に呼ばれる
            timerText.setText("0");
        }

        @Override
        public void onTick(long millisUntilFinished) {

            // インターバル(countDownInterval)毎に呼ばれる
            timerText.setText(Long.toString(millisUntilFinished/1000/60)
                    + ":" + Long.toString(millisUntilFinished/1000%60));
            this.mRestTime--;
        }

        public long getRestTime(){
            return this.mRestTime;
        }

        public boolean getIsWorkTimer(){
            return this.isWorkTimer;
        }

        public void setIsWorkTimert(boolean b){
            this.isWorkTimer = b;
        }
    }
    */
}
