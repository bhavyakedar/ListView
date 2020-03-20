package com.example.listview;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "Database";
    private static final int DB_VERSION = 2;
    public static void insertDrink(SQLiteDatabase db, String name,String description) {
        ContentValues drinkValues = new ContentValues();
        drinkValues.put("NAME", name);
        drinkValues.put("DESCRIPTION", description);
        db.insert("DRINK", null, drinkValues);
    }
    DatabaseHelper(Context context)
    {
        super(context,DB_NAME,null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        updateMyDatabase(db,0,DB_VERSION);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldversion,int newversion){
        updateMyDatabase(db,oldversion,newversion);
    }
    public void updateMyDatabase(SQLiteDatabase db, int oldversion, int newversion)
    {
        if(oldversion<1)
        {
            db.execSQL("CREATE TABLE DRINK(" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "NAME TEXT," +
                    "DESCRIPTION TEXT)");
            insertDrink(db,"Latte","Good Morning");
            insertDrink(db, "Cappuccino", "Espresso, hot milk and steamed-milk foam");
            insertDrink(db, "Filter", "Our best drip coffee");
        }
        if(oldversion<2)
        {
            db.execSQL("ALTER TABLE DRINK ADD COLUMN FAVOURITE NUMERIC");
        }
    }
}
