package com.example.wamay.pictell;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowAnswerActivity extends AppCompatActivity {

    private MyTheme myTheme = new MyTheme();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_answer);

        TextView ansCategory = findViewById(R.id.ansCategoryText);
        ansCategory.setText(myTheme.getMyCategory());

        TextView ansTheme = findViewById(R.id.ansThemeText);
        ansTheme.setText(myTheme.getMyTheme());

        Button returnTopButton = findViewById(R.id.returnTopButton);
        returnTopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
