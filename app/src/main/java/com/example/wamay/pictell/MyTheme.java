package com.example.wamay.pictell;

public class MyTheme {
    private static String theme;
    private static String category;

    public void setMyTheme(String theme){
        MyTheme.theme = theme;
    }

    public String getMyTheme(){
        return MyTheme.theme;
    }

    public void setMyCategory(String category){
        MyTheme.category = category;
    }

    public String getMyCategory(){
        return MyTheme.category;
    }
}
