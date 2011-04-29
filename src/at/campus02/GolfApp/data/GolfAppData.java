package at.campus02.GolfApp.data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GolfAppData extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "golfApp.db";
	private static final int DATABASE_VERSION = 1;

	public GolfAppData(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		// GOLF_COURSE
		String createGolfCourse = "CREATE TABLE golfcourse (_id INTEGER PRIMARY KEY, name TEXT NOT NULL)";
		db.execSQL(createGolfCourse);
		insertGolfCourse(db, 1, "Passail");
		insertGolfCourse(db, 2, "Graz");
		insertGolfCourse(db, 30, "Murau");
		insertGolfCourse(db, 4, "Klöch");
		insertGolfCourse(db, 50, "Bad Gleichenberg");

		// HOLE
		String createGolfCourseHoles = "CREATE TABLE hole (_id INTEGER, number INTEGER, redDistance INTEGER, yellowDistance INTEGER, par INTEGER, handicap INTEGER, PRIMARY KEY(_id, number))";
		db.execSQL(createGolfCourseHoles);
		insertHole(db, 5, 1, 421, 463, 5, 5);
		insertHole(db, 5, 2, 98, 112, 3, 17);
		insertHole(db, 5, 3, 291, 322, 4, 11);
		insertHole(db, 5, 4, 129, 141, 3, 13);
		insertHole(db, 5, 5, 305, 325, 4, 1);
		insertHole(db, 5, 6, 254, 290, 4, 3);
		insertHole(db, 5, 7, 235, 278, 4, 15);
		insertHole(db, 5, 8, 260, 303, 4, 7);
		insertHole(db, 5, 9, 414, 477, 5, 9);
		insertHole(db, 5, 10, 421, 463, 5, 6);
		insertHole(db, 5, 11, 98, 112, 3, 18);
		insertHole(db, 5, 12, 291, 322, 4, 12);
		insertHole(db, 5, 13, 129, 141, 3, 14);
		insertHole(db, 5, 14, 305, 325, 4, 2);
		insertHole(db, 5, 15, 254, 290, 4, 4);
		insertHole(db, 5, 16, 235, 278, 4, 16);
		insertHole(db, 5, 17, 260, 303, 4, 8);
		insertHole(db, 5, 18, 414, 477, 5, 10);

		// PLAYER
		String createPlayer = "CREATE TABLE player (name STRING PRIMARY KEY, gender INTEGER, handicap INTEGER)";
		db.execSQL(createPlayer);

		// PLAYER_HOLE
		String createPlayerHole = "CREATE TABLE playerhole (_id INTEGER, hole_number INTEGER, player_name STRING, totalSwings INTEGER, date INTEGER, PRIMARY KEY(_id, hole_number, player_name))";
		db.execSQL(createPlayerHole);

		// ROUND
		String createRound = "CREATE TABLE round (_id INTEGER, hole_number INTEGER, player_name STRING, totalSwings INTEGER, date INTEGER, PRIMARY KEY(_id, hole_number, player_name))";
		db.execSQL(createRound);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
	}

	private void insertGolfCourse(SQLiteDatabase db, int courseId, String name) {
		ContentValues values = new ContentValues();
		values.put("name", name);
		values.put("_id", courseId);

		db.insertOrThrow("golfcourse", null, values);
	}

	private void insertHole(SQLiteDatabase db, int courseId, int number,
			int redDistance, int yellowDistance, int par, int handicap) {
		ContentValues values = new ContentValues();
		values.put("_id", courseId);
		values.put("number", number);
		values.put("yellowDistance", yellowDistance);
		values.put("redDistance", redDistance);
		values.put("par", par);
		values.put("handicap", handicap);

		db.insertOrThrow("hole", null, values);
	}

	public Cursor allCourses() {
		SQLiteDatabase db = getReadableDatabase();

		String[] columns = { "_id", "name" };

		return db.query("golfcourse", columns, null, null, null, null, "_id");
	}

	public Map<String, Integer> allPlayers() {
		SQLiteDatabase db = getReadableDatabase();

		String[] columns = { "name", "handicap" };

		Cursor cursor = db.query("player", columns, null, null, null, null,
				"name");

		HashMap<String, Integer> playerMap = new HashMap<String, Integer>();

		while (cursor.moveToNext())
			playerMap.put(cursor.getString(0), cursor.getInt(1));

		return playerMap;
	}

	public void insertPlayer(String name, int gender, int handicap) {
		SQLiteDatabase db = getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put("name", name);
		values.put("gender", gender);
		values.put("handicap", handicap);

		db.insertOrThrow("player", null, values);
	}

	public void insertPlayerHole(int courseId, int holeNumber,
			String playerName, int totalSwings, Date date) {
		SQLiteDatabase db = getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put("_id", courseId);
		values.put("hole_number", holeNumber);
		values.put("player_name", playerName);
		values.put("total_swings", totalSwings);
		values.put("date", Date.UTC(date.getYear(), date.getMonth(),
				date.getDay(), 0, 0, 0));

		db.insertOrThrow("playerhole", null, values);
	}

}
