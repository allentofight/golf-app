package at.campus02.GolfApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import at.campus02.GolfApp.course.GolfAppCourse;
import at.campus02.GolfApp.player.GolfAppPlayer;

public class GolfApp extends Activity {
	/** Called when the activity is first created. */

	Button newRound;
	Button managePlayer;
	Button golfCourse;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// new Round Button
		newRound = (Button) findViewById(R.id.newRound);
		newRound.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(),
						GolfAppCourse.class);
				startActivityForResult(myIntent, 0);
			}
		});

		// manage Player Button
		managePlayer = (Button) findViewById(R.id.player);
		managePlayer.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(),
						GolfAppPlayer.class);
				startActivityForResult(myIntent, 0);
			}
		});

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

	}
}