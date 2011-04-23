package at.campus02.GolfApp.data;

import java.util.ArrayList;

import android.app.Activity;
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

		String createGolfCourseHoles = "CREATE TABLE hole (course_id INTEGER PRIMARY KEY, number INTEGER PRIMARY KEY,redDistance INTEGER,yellowDistance INTEGER, par INTEGER, handicap INTEGER)";
		db.execSQL(createGolfCourseHoles);

		insertGolfCourse(db, 1, "Passail");
		insertGolfCourse(db, 2, "Graz");
		insertGolfCourse(db, 3, "Murau");
		insertGolfCourse(db, 4, "Klöch");
		insertGolfCourse(db, 5, "Bad Gleichenberg");
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

	public String[] allCourses(Activity activity) {

		String[] columns = { "id", "name" };

		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.query("golfcourse", columns, null, null, null, null,
				"id");

		ArrayList<String> golfCourses = new ArrayList<String>();

		while (cursor.moveToNext())
			golfCourses.add(cursor.getString(1));

		return (String[]) golfCourses.toArray(new String[golfCourses.size()]);
	}
}
