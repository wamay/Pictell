package com.example.wamay.pictell;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ThemeListActivity extends AppCompatActivity {

    private String spinnerItem;
    private String sql;
    private ThemeModel model;
    ArrayList<String> spinnerItems = new ArrayList<>();
    ArrayList<String> listViewItems = new ArrayList<>();
    ListView list;
    ArrayAdapter<String> listAdapter;

    //getApplication()だとListViewの文字が白くなるので、
    // thisを格納して、ListView作成時に使用します
    Context thisContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_list);

        model = new ThemeModel(getApplicationContext());
        thisContext = this;

        sql = "select distinct category " +
                "from themes";
        spinnerItems = model.searchData(sql,"category");
        Spinner spinner = findViewById(R.id.spinnerCategories);
        ArrayAdapter<String> spinnerAdapter
                = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, spinnerItems);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);


        list = findViewById(R.id.themeListView);
        listViewItems = model.searchData(sql,"category");

        listAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, listViewItems);
        list.setAdapter(listAdapter);

        //listView長押し処理
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                String getItem = (String) ((TextView)view).getText();

                //Todo　選択したItemを削除してよいかポップアップダイアログを表示する
                /**
                 * 「テーマ名」を削除しますがよろしいですか？
                 *           YES   /   NO
                 *  Yesが選ばれた場合、そのテーマをDBからDeleteする
                 */

                return false;
            }
        });


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            //selected item of spinner
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner)parent;
                spinnerItem = (String)spinner.getSelectedItem();

                sql = "select theme " +
                        "from themes " +
                        "where category = '" + spinnerItem + "';";
                listViewItems = model.searchData(sql,"theme");
                listAdapter = new ArrayAdapter<>(thisContext,
                        android.R.layout.simple_list_item_1, listViewItems);
                list.setAdapter(listAdapter);

            }

            //not selected item of spinner
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



    }
}
