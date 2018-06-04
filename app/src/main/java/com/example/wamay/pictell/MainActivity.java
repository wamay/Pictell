package com.example.wamay.pictell;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = findViewById(R.id.starttButton);
        Button themeListButton = findViewById(R.id.themeListButton);

        startButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                /*テーマ決定画面に移動する
                 * intentに現在画面。遷移先画面に設定する
                 * 次画面でデータとデータの引継ぎや変数共有がないため、別のアクテビティに移動します
                 * */
                Intent intent = new Intent(getApplication(), ThemeSelectActivity.class);
                startActivity(intent);
            }
        });

        themeListButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplication(),ThemeListActivity.class);
                startActivity(intent);
            }
        });
    }

}
