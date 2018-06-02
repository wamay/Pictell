package com.example.wamay.pictell;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import android.util.Log;

public class ThemeSelectActivity extends AppCompatActivity {


    private ArrayList<String> spinnerItems = new ArrayList<>();
    private ThemeModel model;
    private MyTheme myTheme = new MyTheme();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_select);

        //deleteDatabase("themes.DB");

        this.model = new ThemeModel(getApplicationContext());

        String sql = "select distinct category " +
                "from themes";
        spinnerItems = this.model.searchData(sql,"category");
        spinnerItems.add(0,"すべてのカテゴリ");


        Spinner spinner = findViewById(R.id.spinnerCategories);
        final TextView showTheme = findViewById(R.id.showThemeText);

        // ArrayAdapter
        ArrayAdapter<String> adapter
                = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            //spinnerのアイテムを選択された時の処理
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner)parent;
                myTheme.setMyCategory((String)spinner.getSelectedItem());
            }

            //spinnerのアイテムが選択されなかった時の処理
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Button generateTheme = findViewById(R.id.generateThemeButton);
        generateTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sql;
                if(myTheme.getMyCategory().equals("すべてのカテゴリ")){
                    sql = "select theme " +
                            "from themes " +
                            "order by random() limit 1";
                }else {
                    sql = "select theme " +
                            "from themes " +
                            "where category = '" + myTheme.getMyCategory()  + "' " +
                            "order by random() limit 1";
                }

                String selectTheme = model.searchData(sql,"theme").get(0);
                myTheme.setMyTheme(selectTheme);
                showTheme.setText(myTheme.getMyTheme());
            }
        });


    }
}
