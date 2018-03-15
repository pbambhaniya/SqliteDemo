package com.multipz.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {
    //public static final String PASSPHRASE = "Dswebapp@gmail.com.ak";
    public static final String DBNAME = "MessageManager.db";
    public static final String DBLOCATION = "/data/data/com.multipz.sqlite/databases/";
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public DatabaseHelper(Context context) {
        super(context, DBNAME, null, 3);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       /* String sql = "create table if not exists tbl_category (cat_id INTEGER PRIMARY KEY, lang_id INTEGER,sort_order INTEGER, cat_name TEXT)";
        db.execSQL(sql);

        String sql1 = "create table if not exists tbl_jokes (cat_id INTEGER NOT NULL,lang_id INTEGER, jokes_id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , jokes VARCHAR, favourite INTEGER DEFAULT 0)";
        db.execSQL(sql1);

        String sql2 = "create table if not exists tbl_version (version_id INTEGER PRIMARY KEY, version TEXT)";
        db.execSQL(sql2);

        String sql3 = "insert into tbl_version (version_id, version) values(1,'0')";
        db.execSQL(sql3);*/

    }

    public boolean copyDatabase(Context context) {
        try {

            InputStream inputStream = context.getAssets().open(DBNAME);
            String outFileName = DBLOCATION + DBNAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[] buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            //Log.w("db_copy", "DB copied");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDatabase() {
        String dbPath = mContext.getDatabasePath(DBNAME).getPath();
        if (mDatabase != null && mDatabase.isOpen()) {
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase() {
        if (mDatabase != null) {
            mDatabase.close();
        }
    }

    public List<BadCategory> getListCategory() {

        BadCategory category = null;
        List<BadCategory> categoryList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM message", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            category = new BadCategory(cursor.getInt(0), cursor.getString(1));
            categoryList.add(category);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return categoryList;
    }


    public List<BadCategory> getListStatus(int catId) {

        BadCategory status = null;
        List<BadCategory> slocksList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM slocks WHERE a_id =" + catId + "", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            status = new BadCategory(cursor.getInt(1), cursor.getString(4));
            slocksList.add(status);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return slocksList;
    }


    public List<BadCategory> getCategoryWiseData(String c_id) {

        BadCategory category = null;
        List<BadCategory> categoryList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("select * from message where category = '" + c_id + "'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            category = new BadCategory(cursor.getInt(0), cursor.getString(1));
            categoryList.add(category);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return categoryList;
    }

}