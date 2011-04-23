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
		// set up tables if needed
		String createGolfCourse = "CREATE TABLE golfcourse (id INTEGER PRIMARY KEY, name TEXT NOT NULL)";
		db.execSQL(createGolfCourse);
		// insertGolfCourse(db, 1, "Passail");
		// insertGolfCourse(db, 2, "Graz");
		// insertGolfCourse(db, 3, "Murau");
		// insertGolfCourse(db, 4, "Klöch");
		insertGolfCourse(db, 5, "Bad Gleichenberg");

		String createGolfCourseHoles = "CREATE TABLE hole (course_id INTEGER PRIMARY KEY, number INTEGER PRIMARY KEY,redDistance INTEGER,yellowDistance INTEGER, par INTEGER, handicap INTEGER)";
		db.execSQL(createGolfCourseHoles);
		insertHole(db, 5, 1, 421, 463, 5, 5);
		insertHole(db, 5, 2, 98, 112, 3, 5);
		insertHole(db, 5, 3, 291, 322, 4, 5);
		insertHole(db, 5, 4, 129, 141, 3, 5);
		insertHole(db, 5, 5, 305, 325, 4, 5);
		insertHole(db, 5, 6, 254, 290, 4, 5);
		insertHole(db, 5, 7, 235, 278, 4, 5);
		insertHole(db, 5, 8, 260, 303, 4, 5);
		insertHole(db, 5, 9, 414, 477, 5, 5);

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

	public Map<Integer, String> allCourses() {

		String[] columns = { "id", "name" };

		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.query("golfcourse", columns, null, null, null, null,
				"id");

		HashMap<Integer, String> courseMap = new HashMap<Integer, String>();

		while (cursor.moveToNext())
			courseMap.put(cursor.getInt(0), cursor.getString(1));

		return courseMap;
	}
}
