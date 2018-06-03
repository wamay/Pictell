package com.example.wamay.pictell;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import android.util.Log;

public class ThemeSelectActivity extends AppCompatActivity {

    private ArrayList<String> spinnerItems = new ArrayList<>();
    private ThemeModel model;
    private MyTheme myTheme = new MyTheme();

    private String tempCategory = "すべてのカテゴリ";
    private String tempTheme;
    private String spinnerItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_select);

        //deleteDatabase("themes.DB");

        //pickup categories from themeDB
        this.model = new ThemeModel(getApplicationContext());
        String sql = "select distinct category " +
                "from themes";
        spinnerItems = this.model.searchData(sql,"category");
        spinnerItems.add(0,"すべてのカテゴリ");


        //set categories in spinner items
        Spinner spinner = findViewById(R.id.spinnerCategories);
        final TextView showTheme = findViewById(R.id.showThemeText);
        ArrayAdapter<String> adapter
                = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            //selected item of spinner
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner)parent;
                spinnerItem = (String)spinner.getSelectedItem();
            }

            //not selected item of spinner
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        //init theme
        sql = "select theme " +
                "from themes " +
                "order by random() limit 1";
        tempTheme = model.searchData(sql,"theme").get(0);
        showTheme.setText(tempTheme);


        //generate new theme
        Button generateTheme = (Button)findViewById(R.id.generateThemeButton);
        generateTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sql;
                if(spinnerItem.equals("すべてのカテゴリ")){
                    sql = "select theme " +
                            "from themes " +
                            "order by random() limit 1";
                }else {
                    sql = "select theme " +
                            "from themes " +
                            "where category = '" + spinnerItem  + "' " +
                            "order by random() limit 1";
                }

                tempCategory = spinnerItem;
                tempTheme = model.searchData(sql,"theme").get(0);
                showTheme.setText(tempTheme);

            }
        });


        //transition from this to playGameActivity
        Button playGameButton = findViewById(R.id.playGameButton);
        playGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myTheme.setMyTheme(tempTheme);
                myTheme.setMyCategory(tempCategory);

                Intent intent = new Intent(getApplicationContext(), PlayGameActivity.class);
                startActivity(intent);
            }
        });
    }
}
