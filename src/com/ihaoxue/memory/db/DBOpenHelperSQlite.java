package com.ihaoxue.memory.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelperSQlite extends SQLiteOpenHelper {

	public static final String MEMORY_DBNAME = "memory_db.db" ;
	public static final int MEMORY_DBVERSION = 1 ;
	
	public DBOpenHelperSQlite(Context context){
		super(context, DBOpenHelperSQlite.MEMORY_DBNAME, null, DBOpenHelperSQlite.MEMORY_DBVERSION) ;
	}
	
	public DBOpenHelperSQlite(Context context, String name, CursorFactory factory, int version){
		super(context, DBOpenHelperSQlite.MEMORY_DBNAME, factory, DBOpenHelperSQlite.MEMORY_DBVERSION) ;
	}
	
	public DBOpenHelperSQlite(Context context, String name, CursorFactory factory, int version,
			DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(SQL_CREATE_CONFIG_TABLE) ;
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL(SQL_CREATE_CONFIG_TABLE) ;
	}
	
	public final static String CACHEDB_NAME = "cache_JSON"; 
	public final static String CACHEDB_URL = "url"; 
	public final static String CACHEDB_MD5 = "md5"; 
	public final static String CACHEDB_DATE = "date"; 
	public final static String CACHEDB_DATA = "data";
	public final static String CACHEDB_HEADER = "header";
	
	//数据缓存
	String SQL_CREATE_CONFIG_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ CACHEDB_NAME + " ( " + "id INTEGER primary key AUTOINCREMENT,"
			+ CACHEDB_URL + " varchar(200)," + CACHEDB_MD5 + " varchar(200),"
			+ CACHEDB_DATE + " long," + CACHEDB_DATA + " varchar(10000),"
			+ CACHEDB_HEADER + " varchar(200)" + ");";
}
