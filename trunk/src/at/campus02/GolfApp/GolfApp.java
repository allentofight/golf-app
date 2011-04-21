package at.campus02.GolfApp;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import at.campus02.GolfApp.data.GolfAppData;

public class GolfApp extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// create DB entries
		GolfAppData data = new GolfAppData(this);
		data.insertGolfCourse(5, "Bad Gleichenberg", "Testbeschreibung");
		data.insertGolfCourse(2, "Graz", "Testbeschreibung2");
		data.insertGolfCourse(1, "Wien", "Testbeschreibung3");
		data.insertGolfCourse(3, "Murau", "Testbeschreibung4");

		Cursor cursor = data.allCourses(getParent());
		while (cursor.moveToNext()) {
			System.out.println(cursor.getInt(0) + " - " + cursor.getString(1)
					+ " - " + cursor.getString(2));
		}
	}
}