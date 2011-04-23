package at.campus02.GolfApp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import at.campus02.GolfApp.course.GolfAppCourse;
import at.campus02.GolfApp.data.GolfAppData;
import at.campus02.GolfApp.player.GolfAppPlayer;

public class GolfApp extends Activity {
	/** Called when the activity is first created. */

	Button managePlayer;
	Button golfCourse;
	Button history;
	Button settings;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// manage Player Button
		managePlayer = (Button) findViewById(R.id.player);
		managePlayer.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(),
						GolfAppPlayer.class);
				startActivityForResult(myIntent, 0);
			}
		});

		// Golf Course Button
		golfCourse = (Button) findViewById(R.id.golfCourse);
		golfCourse.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(),
						GolfAppCourse.class);
				startActivityForResult(myIntent, 0);
			}
		});

		// History Button
		history = (Button) findViewById(R.id.history);
		history.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(),
						GolfAppHistory.class);
				startActivityForResult(myIntent, 0);
			}
		});

		// Settings Button
		settings = (Button) findViewById(R.id.settings);
		settings.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(),
						GolfAppSettings.class);
				startActivityForResult(myIntent, 0);
			}
		});

		GolfAppData data = new GolfAppData(this);
		Cursor cursor = data.allCourses(getParent());
		while (cursor.moveToNext()) {
			System.out.println(cursor.getInt(0) + " - " + cursor.getString(1)
					+ " - " + cursor.getString(2));
		}
	}
}