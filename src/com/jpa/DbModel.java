package com.jpa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DbModel {
	Context mContext;
	DbHelper mDbHelper;
	SQLiteDatabase mDb;

	private static final int DB_VERSION = 1;

	private static final String DB_NAME = "jpadb";

	private static final String JPA_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS "
			+ MetaData.JPA_TABLE
			+ " ("
			+ MetaData.ID
			+ " integer primary key autoincrement, "
			+ MetaData.JPA_URL_KEY
			+ " text not null, "
			+ MetaData.JPA_PASSWORD_KEY
			+ " text not null);"; // integer

	static class MetaData {
		static final String JPA_TABLE = "jpa_auth";
		static final String ID = "_id";
		static final String JPA_URL_KEY = "url";
		static final String JPA_PASSWORD_KEY = "password";
	}

	private class DbHelper extends SQLiteOpenHelper {
		public DbHelper(Context context, String name, CursorFactory factory,
				int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase _db) {
			// create db => create table
			_db.execSQL(JPA_TABLE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
			// just in case...
		}
	}

	public DbModel(Context ctx) {
		mContext = ctx;
		mDbHelper = new DbHelper(ctx, DB_NAME, null, DB_VERSION);
	}

	public void close() {
		mDb.close();
	}

	public Cursor fetch() {
		// fetch all
		return mDb
				.query(MetaData.JPA_TABLE, null, null, null, null, null, null);
	}

	public Cursor getDbAuth() {
		return mDb.query(MetaData.JPA_TABLE, new String[] {MetaData.ID, MetaData.JPA_PASSWORD_KEY, MetaData.JPA_URL_KEY}, 
				MetaData.ID + " = 1", null, null, null, null);
	}
	
	public void insert(String url, String password) {
		ContentValues cv = new ContentValues();
		cv.put(MetaData.JPA_URL_KEY, url);
		cv.put(MetaData.JPA_PASSWORD_KEY, password);
		mDb.insert(MetaData.JPA_TABLE, null, cv);
	}

	// http://www.codeguru.com/forum/showthread.php?t=483073
	/*
	 * public boolean updateTitle(long _Id, String FNAME, String LNAME, String
	 * DOB, String HEIGHT, String WEIGHT) { ContentValues args = new
	 * ContentValues(); args.put(KEY_FNAMEID, FNAME); args.put(KEY_LNAMEID,
	 * LNAME); args.put(KEY_DOBID, DOB); return db.update(DATABASE_TABLE, args,
	 * KEY_STDID + "=" + _Id, null) > 0; }
	 */

	public void open() {
		mDb = mDbHelper.getWritableDatabase(); // readable/writable DB
	}

	public void update(long _Id, String url, String password) {
		ContentValues cv = new ContentValues();

		cv.put(MetaData.JPA_URL_KEY, url);
		cv.put(MetaData.JPA_PASSWORD_KEY, password);

		mDb.update(MetaData.JPA_TABLE, cv, MetaData.ID + "=" + _Id, null);
//		mDb.update(MetaData.JPA_TABLE, cv, MetaData.JPA_PASSWORD_KEY + "=?",
//				new String[] { Long.toString(_Id) });
	}
}