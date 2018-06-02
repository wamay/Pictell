package com.example.wamay.pictell;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ThemeModel {

    private static ThemeDB helper = null;
    private static SQLiteDatabase db = null;
    final String THEME_TABLE_NAME = "themes";

    //DB作成用のコンストラクタ
    //DBをいじる場合は、このモデルクラスからのみとすること


    ThemeModel(Context context){
        if(helper == null || db == null){
            helper = new ThemeDB(context);
            db = helper.getWritableDatabase();
        }
    }


    /**
     * Execute SQL command using argument.
     * DISTINCTを使用する場合があったためなるべく汎用的なものにしています
     * ToDo ここではSELECT以外のDDL文を読めないようにする
     * @param sql execute SQL command
     * @param wantColumn use to getColumnIndex
     * @return result of SQL command Strings(ArrayList)
     */
    public ArrayList<String> searchData(String sql,String wantColumn){

        ArrayList<String> array = new ArrayList<>();
        Cursor cursor = null;

        //cursorを確実にcloseするためにtry{}~finallyにする
        try{
            cursor = db.rawQuery(sql,null);
            if(cursor.moveToFirst()){
                do{
                    String s = cursor.getString(cursor.getColumnIndex(wantColumn));
                    array.add(s);
                }while(cursor.moveToNext());
            }
            return array;
        }finally{
            if( cursor != null ){
                cursor.close();
            }
        }

    }

    public void dropTable(String sql){
        db.execSQL(sql);
    }
}
