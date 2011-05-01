package at.campus02.GolfApp;

import java.util.ArrayList;

import android.app.Activity;
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
	ArrayList<String> selectedPlayer = new ArrayList<String>();
	int par;

	protected void onStart() {
		super.onStart();
		setContentView(R.layout.setshotsforhole);

		// Get vars from previous View
		if (getIntent().hasExtra("courseName") == true
				&& getIntent().hasExtra("courseId") == true
				&& getIntent().hasExtra("ArraySelectedPlayer") == true) {
			courseName = getIntent().getExtras().getString("courseName");
			courseId = getIntent().getExtras().getInt("courseId");

			selectedPlayer = getIntent().getExtras().getStringArrayList(
					"ArraySelectedPlayer");

			tv = (TextView) findViewById(R.id.courseName);
			tv.setText(courseName);

			char[] c = courseName.toCharArray();
			for (int i = c.length - 1; i < c.length; i++) {
				char parchar = c[i];
				par = parchar - 48;
			}

			// TODO Willibald.. glaub net dass das mit dem Datepicker geht!!
			for (int i = 0; i < selectedPlayer.size(); i++) {
				tv = (TextView) findViewById(R.id.player);
				tv.setText(selectedPlayer.get(i));

				// Shots NumberPicker
				shots = (NumberPicker) findViewById(R.id.shots);
				shots.setRange(0, 15);
				shots.setCurrent(par);
				// Shots NumberPicker
				shots = (NumberPicker) findViewById(R.id.shots);
				shots.setRange(0, 15);
				shots.setCurrent(par);
			}
		}

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
