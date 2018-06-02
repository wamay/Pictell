package com.example.wamay.pictell;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.database.SQLException;
import android.util.Log;


public class ThemeDB extends SQLiteOpenHelper {

    private static final String TAG = "ThemeListDB";
    private static final String DATABASE_NAME = "themes.DB";
    private static final String THEME_TABLE_NAME = "themes";
    private static final int DATABASE_VERSION = 1;

    public ThemeDB(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //DBが存在しない状態でOpenすると、onCreateがコールされる
    //pictellの説明書に乗っているテーマをDBに入れています
    public void onCreate(SQLiteDatabase db){

        String[] themeSports = {"ゴルフ","バドミントン","卓球","なわとび","ドッジボール",
                "ボルダリング","フィギュアスケート","相撲","合気道","フェンシング","ラグビー",
                "アメリカンフットボール","ハンドボール","セパタクロー","ラクロス","カーリング",
                "ボブスレー","トライアスロン","サーフィン","アーチェリー","ボディビル",};
        String[] themeAnimations = {"ワンピース","鉄腕アトム","スラムダンク","ジョジョの奇妙な冒険",
                "デスノート","機動戦士ガンダム","時をかける少女","天空の城ラピュタ","君に届け","ポケモン",
                "バイオハザード","スプラトゥーン","テトリス","ババ抜き"};
        String[] themeEvents = {"文化祭","体育祭","誕生日","クリスマス","ハロウィン","ひな祭り",
                "バレンタインデー","正月","夏休み","ライブ","コンサート","夏祭り","卒業式","入学式",
                "合格発表","忘年会","結婚記念日","ひとりカラオケ"};
        String[] themeCharacters = {"タモリさん","キムタク","きゃりーぱみゅぱみゅ","阿部寛",
                "マツコ・デラックス","聖徳太子","織田信長","モーツァルト","ダース・ベイダー",
                "バズ・ライトイヤー","ふなっしー","ジバニャン","なまはげ","ネッシー","ミッキーマウス"};
        String[] themeMovies = {"バックトゥザ・フューチャー","リング","不思議の国のアリス","ホームアローン",
                "スターウォーズ","Ｅ.Ｔ.","千と千尋の神隠し","怪人二十面相","100万回生きた猫","昆虫図鑑",
                "となりのトトロ","紅の豚","アナと雪の女王","風の谷のナウシカ"};
        String[] themeJobs = {"歯医者","消防士","プログラマー","弁護士","デザイナー","整備士","漫画家",
                "ミュージシャン","教師","税理士","保育士","不動産屋","工芸家","建築家","宇宙飛行士",
                "バス運転手","飼育員","政治家","司会者","画家","ダンサー","ホテルマン","花屋","レーサー"};
        String[] themeFun = {"ディズニーランドに行く","ショッピング","ネイルアート","絵を描く",
                "人気のパンケーキを食べに行く","ボードゲームで遊ぶ","友達とおしゃべり","焼肉食べ放題",
                "ドライブ","温泉に行く","絵を描く","クラブで踊る","新しい知識を得る"};
        String[] themeHappy = {"受験に合格した","宝くじが当たった","電車で目の前の席が空く","確変した",
                "友達に赤ちゃんが生まれた","金のエンゼルが出た","応援している野球チームが優勝","３連休",
                "彼女ができた","信号がすべて青","旅行で快晴","靴ひもが一発で結べた"};
        String[] themeSad = {"寝坊した","クリスマスに振られる","元恋人の結婚報告をFBで見る","財布を落とす",
                "日曜日のサザエさん","買っていたカメが逃げ出した","タンスの角小指をぶつける","突然の雨",
                "スマホの画面が割れる","パソコンの容量がいっぱい"};
        String[] themeScary = {"交通事故","ホテルの部屋にお札が貼ってある","宇宙に取り残される",
                "迷子になる","飛行機が墜落する","夜中の神社","心霊写真","金縛りにあう","跳び箱","歯医者"};
        String[] themeSurprise = {"ゴキブリが出た","彼女が男だった","レジで財布の中身を見たら空だった",
                "乗った電車が逆方向だった","お布施が少ない","旅先で友人に会う","突然の地震",
                "小野妹子が男だった","親族に芸能人がいた","外国人に話しかけられる","PCが壊れる"};

        String[][] allThemes = {themeSports,themeAnimations,themeEvents,themeCharacters,themeMovies,
                themeJobs,themeFun,themeHappy,themeSad,themeScary,themeSurprise};
        String[] categories = {"スポーツ","漫画・アニメ・ゲーム","イベント","人物・キャラクター","映画・本"
                ,"職業","楽しいこと","うれしいこと","悲しい・嫌なこと","怖いこと","驚くこと"};


        //***************************************************************
        String createSQL = "create table " + THEME_TABLE_NAME +
                "(themeid integer primary key," +
                " theme text," +
                " category text" +
                ");";
        db.execSQL(createSQL);
        //****************************************************************

        //初期データとして、上で作られた配列をDBに格納しています
        db.beginTransaction();
        try{
            SQLiteStatement sql = db.compileStatement(
                    "insert into themes " +
                            "values(?, ?, ?)");

            //どのカテゴリを参照しているか示すポインタ
            int categoryPointa = 0;
            int recordCount = 1;
            for(String c:categories){
                for(String t:allThemes[categoryPointa]){
                    sql.bindLong(1,recordCount);
                    sql.bindString(2,t);
                    sql.bindString(3,c);
                    sql.executeInsert();
                    recordCount++;
                }
                categoryPointa++;
            }
            db.setTransactionSuccessful();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
