package at.campus02.GolfApp.data;

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
		String createGolfCourse = "CREATE TABLE golfcourse (id INTEGER PRIMARY KEY, name TEXT NOT NULL)";
		db.execSQL(createGolfCourse);
		// insertGolfCourse(db, 1, "Passail");
		// insertGolfCourse(db, 2, "Graz");
		// insertGolfCourse(db, 3, "Murau");
		// insertGolfCourse(db, 4, "Klöch");
		insertGolfCourse(db, 5, "Bad Gleichenberg");

		// HOLE
		String createGolfCourseHoles = "CREATE TABLE hole (course_id INTEGER PRIMARY KEY, number INTEGER PRIMARY KEY,redDistance INTEGER,yellowDistance INTEGER, par INTEGER, handicap INTEGER)";
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

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
	}

	private void insertGolfCourse(SQLiteDatabase db, int courseId, String name) {
		ContentValues values = new ContentValues();
		values.put("id", courseId);
		values.put("name", name);

		db.insertOrThrow("golfcourse", null, values);
	}

	private void insertHole(SQLiteDatabase db, int courseId, int number,
			int redDistance, int yellowDistance, int par, int handicap) {
		ContentValues values = new ContentValues();
		values.put("course_id", courseId);
		values.put("number", number);
		values.put("yellowDistance", yellowDistance);
		values.put("redDistance", redDistance);
		values.put("par", par);
		values.put("handicap", handicap);

		db.insertOrThrow("hole", null, values);
	}

	public Map<String, String> allCourses() {
		SQLiteDatabase db = getReadableDatabase();

		String[] columns = { "id", "name" };

		Cursor cursor = db.query("golfcourse", columns, null, null, null, null,
				"id");

		HashMap<String, String> courseMap = new HashMap<String, String>();

		while (cursor.moveToNext())
			courseMap.put(cursor.getString(0), cursor.getString(1));

		return courseMap;
	}

	public void insertPlayer(String name, int gender, int handicap) {
		SQLiteDatabase db = getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put("name", name);
		values.put("gender", gender);
		values.put("handicap", handicap);

		db.insertOrThrow("player", null, values);
	}
}
