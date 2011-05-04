package at.campus02.GolfApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import at.campus02.GolfApp.course.GolfAppCourse;
import at.campus02.GolfApp.player.GolfAppPlayer;

public class GolfApp extends Activity implements GolfAppLists {
	/** Called when the activity is first created. */

	Button newRound;
	Button continueRound;
	Button managePlayer;
	Button golfCourse;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// new Round Button
		newRound = (Button) findViewById(R.id.newRound);
		newRound.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				if (selectedPlayer.isEmpty() == false) {
					Intent myIntent = new Intent(view.getContext(),
							GolfAppDeleteRound.class);
					startActivityForResult(myIntent, 0);
					return;
				}
				Intent myIntent = new Intent(view.getContext(),
						GolfAppCourse.class);
				startActivityForResult(myIntent, 0);
			}
		});

		// continue Round Button
		continueRound = (Button) findViewById(R.id.continueRound);
		continueRound.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				if (selectedPlayer.isEmpty() == true) {
					Toast.makeText(getApplicationContext(),
							"Es wurde keine Runde gestartet",
							Toast.LENGTH_SHORT).show();
					return;
				}
				// TODO Andi
				int courseId = Integer.parseInt(values.get(0));
				String course_name = values.get(1);
				Intent myIntent = new Intent(view.getContext(),
						GolfAppPlayRound.class);
				myIntent.putExtra("courseName", course_name);
				myIntent.putExtra("courseId", courseId);
				myIntent.putStringArrayListExtra("ArraySelectedPlayer",
						selectedPlayer);
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
				myIntent.putExtra("onlyCourse", "onlyCourse");
				startActivityForResult(myIntent, 0);
			}
		});

	}
}