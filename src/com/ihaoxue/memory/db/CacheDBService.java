package com.ihaoxue.memory.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

/**
 * @author weibin Sqlite数据库实现 数据缓存
 *  网络请求时 根据
 */
public class CacheDBService {

	public static CacheDBService instance = null;

	private DBOpenHelperSQlite dbOpenHelperSQlite = null;

	public static CacheDBService getInsance(Context mContext) {

		if (instance == null) {
			instance = new CacheDBService();
		}
		if (instance.dbOpenHelperSQlite == null) {
			instance.dbOpenHelperSQlite = new DBOpenHelperSQlite(mContext);
		}
		return instance;
	}

	/**
	 * @return true 正确 false 发生错误
	 */
	public synchronized boolean removeAll() {
		SQLiteDatabase db = dbOpenHelperSQlite.getWritableDatabase();

		String sql = "delete * from " + DBOpenHelperSQlite.CACHEDB_NAME;
		try {
			db.beginTransaction();
			db.execSQL(sql);
			db.setTransactionSuccessful();
			db.endTransaction();
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (db != null) {
				db.close();
			}
		}
	}

	public synchronized boolean insert(Bundle bundle) {

		SQLiteDatabase db = dbOpenHelperSQlite.getWritableDatabase();

		String sql = "insert into " + DBOpenHelperSQlite.CACHEDB_NAME + "(" + DBOpenHelperSQlite.CACHEDB_URL + ","
				+ DBOpenHelperSQlite.CACHEDB_MD5 + "," + DBOpenHelperSQlite.CACHEDB_HEADER + ","
				+ DBOpenHelperSQlite.CACHEDB_DATE + "," + DBOpenHelperSQlite.CACHEDB_DATA + ") values ('"
				+ bundle.getString(DBOpenHelperSQlite.CACHEDB_URL) + "','"
				+ bundle.getString(DBOpenHelperSQlite.CACHEDB_MD5) + "','"
				+ bundle.getString(DBOpenHelperSQlite.CACHEDB_HEADER) + "','"
				+ bundle.getLong(DBOpenHelperSQlite.CACHEDB_DATE) + "','"
				+ bundle.getString(DBOpenHelperSQlite.CACHEDB_DATA) + "')";
		try {
			db.beginTransaction();
			db.execSQL(sql);
			db.setTransactionSuccessful();
			db.endTransaction();
			return true;
		} catch (SQLException e) {
			return false;
		} finally {
			if (db != null) {
				db.close();
			}
		}
	}

	public synchronized boolean exist(String urlMD5) {
		SQLiteDatabase db = dbOpenHelperSQlite.getReadableDatabase();
		String sql = "select * from " + DBOpenHelperSQlite.CACHEDB_NAME + " where " + DBOpenHelperSQlite.CACHEDB_MD5
				+ " ='" + urlMD5 + "'";
		Cursor cursor = null;
		try {
			cursor = db.rawQuery(sql, null);
			if (!cursor.isAfterLast()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db != null) {
				db.close();
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param urlMD5
	 * @return 获取时间
	 */
	public synchronized long getDate(String urlMD5){
		
		SQLiteDatabase db = dbOpenHelperSQlite.getReadableDatabase() ;
		String sql = "select * from "+DBOpenHelperSQlite.CACHEDB_NAME+" where "+DBOpenHelperSQlite.CACHEDB_MD5 +"="+"'"+urlMD5+"'" ;
		Cursor cursor = null;
		long tempTime ;
		
		try {
			cursor = db.rawQuery(sql, null) ;
			if (!cursor.isAfterLast()) {
				cursor.moveToFirst() ;
			}
			tempTime = cursor.getLong(cursor.getColumnIndex(DBOpenHelperSQlite.CACHEDB_DATE)) ;
			
			return tempTime ;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (cursor !=null) {
				cursor.close() ;
			}
			db.close() ;
		}
		return 0 ;
	}
	
	public synchronized boolean update(Bundle bundle){
		
		SQLiteDatabase db = dbOpenHelperSQlite.getWritableDatabase() ;
		
		ContentValues mContentValues = new ContentValues() ;
		mContentValues.put(DBOpenHelperSQlite.CACHEDB_DATA, bundle.getString(DBOpenHelperSQlite.CACHEDB_DATA)) ;
		mContentValues.put(DBOpenHelperSQlite.CACHEDB_DATE, bundle.getString(DBOpenHelperSQlite.CACHEDB_DATE)) ;
		mContentValues.put(DBOpenHelperSQlite.CACHEDB_HEADER, bundle.getString(DBOpenHelperSQlite.CACHEDB_HEADER)) ;
		mContentValues.put(DBOpenHelperSQlite.CACHEDB_MD5, bundle.getString(DBOpenHelperSQlite.CACHEDB_MD5)) ;
		mContentValues.put(DBOpenHelperSQlite.CACHEDB_URL, bundle.getString(DBOpenHelperSQlite.CACHEDB_URL)) ;
		
		try {
			db.beginTransaction() ;
			db.update(DBOpenHelperSQlite.CACHEDB_NAME, mContentValues, DBOpenHelperSQlite.CACHEDB_MD5 +"=?", new String[]{bundle.getString(DBOpenHelperSQlite.CACHEDB_MD5)}) ;
			db.setTransactionSuccessful() ;
			return true ;
		} catch (Exception e) {
			return false ;
		}finally{
			if (db !=null) {
				db.close() ;
			}
		}
	}
	
	/**
	 * 根据网址 获取数据
	 * @param urlMD5
	 * @return
	 */
	public synchronized String getData(String urlMD5){
		
		SQLiteDatabase db = dbOpenHelperSQlite.getReadableDatabase() ;
		String sql = "select * from "+ DBOpenHelperSQlite.CACHEDB_NAME+" where "+DBOpenHelperSQlite.CACHEDB_MD5+" = "+"'"+urlMD5+"'" ;
		Cursor mCursor = null ;
		String data = null;
		
		try {
			mCursor = db.rawQuery(sql, null) ;
			if (mCursor.moveToNext()) {
				data = mCursor.getString(mCursor.getColumnIndex(DBOpenHelperSQlite.CACHEDB_MD5)) ;
			}
			return data ;
		} catch (Exception e) {
			return null;
		}finally{
			if (mCursor!=null) {
				mCursor.close() ;
			}
			db.close() ;
		}
		
	}
	
}
