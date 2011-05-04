package at.campus02.GolfApp;

import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import at.campus02.GolfApp.data.GolfAppData;
import at.campus02.GolfApp.external.NumberPicker;

public class GolfAppSetShotsForHolePlayer extends Activity {

	Button ok;
	Button cancel;
	NumberPicker shots;
	String courseName;
	int courseId;
	String player;
	TextView tv;
	ArrayList<String> selectedPlayer = new ArrayList<String>();
	int par;
	int hole;

	protected void onStart() {
		super.onStart();
		setContentView(R.layout.setshotsforholeplayer);

		// Get vars from previous View
		if (getIntent().hasExtra("courseId") == true
				&& getIntent().hasExtra("player") == true
				&& getIntent().hasExtra("hole") == true
				&& getIntent().hasExtra("par") == true) {
			courseId = getIntent().getExtras().getInt("courseId");
			player = getIntent().getExtras().getString("player");
			par = getIntent().getExtras().getInt("par");
			hole = getIntent().getExtras().getInt("hole");

			tv = (TextView) findViewById(R.id.player);
			tv.setText(player);

			// Shots NumberPicker
			shots = (NumberPicker) findViewById(R.id.shots);
			shots.setRange(0, 15);
			shots.setCurrent(par);

		}

		// OK Button
		ok = (Button) findViewById(R.id.ok);
		ok.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {

				NumberPicker shots = (NumberPicker) findViewById(R.id.shots);
				int totalSwings = shots.getCurrent();

				GolfAppData data = new GolfAppData(getApplicationContext());
				Date date = new Date();
				data.insertRound(courseId, hole, player, totalSwings, date);

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
