package at.campus02.GolfApp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import at.campus02.GolfApp.external.NumberPicker;

public class GolfAppSetShotsForHole extends Activity {

	Button ok;
	Button cancel;
	NumberPicker shots;
	String courseName;
	int courseId;
	TextView tv;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setshotsforhole);

		// // Get vars from previous View
		// if (getIntent().hasExtra("courseName") == true
		// && getIntent().hasExtra("courseId") == true) {
		// courseName = getIntent().getExtras().getString("courseName");
		// courseId = getIntent().getExtras().getInt("courseId");
		// // Show Course Name
		// tv = (TextView) findViewById(R.id.courseName);
		// tv.setText(courseName);
		// }

		// Shots NumberPicker
		shots = (NumberPicker) findViewById(R.id.shots);
		shots.setRange(0, 15);
		shots.setCurrent(3);

		// OK Button
		ok = (Button) findViewById(R.id.ok);
		ok.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {

				// TODO: Scheri DATA

				finish();
			}
		});

		// Cancel Button
		cancel = (Button) findViewById(R.id.cancel);
		cancel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				finish();
			}
		});

	}
}
