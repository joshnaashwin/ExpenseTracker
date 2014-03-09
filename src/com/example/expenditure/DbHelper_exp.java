package com.example.expenditure;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper_exp extends SQLiteOpenHelper {
	
	
	public DbHelper_exp(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	

	final static String TABLE_NAME="MY_EXP";
	private static String DATABASE_TABLE_CREATE=
	"create table if not exists "+TABLE_NAME+" ( _id integer primary key autoincrement," +"Date text not null , Items text not null , Amount text not null)"; 
			
	
	
	

	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
	db.execSQL(DATABASE_TABLE_CREATE);
	


	
	
	
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

	

}

