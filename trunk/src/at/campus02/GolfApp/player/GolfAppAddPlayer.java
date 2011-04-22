package at.campus02.GolfApp.player;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import at.campus02.GolfApp.R;
import at.campus02.GolfApp.external.NumberPicker;

public class GolfAppAddPlayer extends Activity {

	Button ok;
	Button cancel;
	Spinner gender;
	NumberPicker handicap;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addplayer);

		// OK Button
		ok = (Button) findViewById(R.id.ok);
		ok.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				// ToDO: Save Player
			}
		});

		// Cancel Button
		cancel = (Button) findViewById(R.id.cancel);
		cancel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				finish();
			}
		});

		// Handicap NumberPicker
		handicap = (NumberPicker) findViewById(R.id.handicap);
		handicap.setRange(0, 50);
		handicap.setCurrent(25);

		// Gender Select
		gender = (Spinner) findViewById(R.id.gender);
		ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
				R.array.genders, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		gender.setAdapter(adapter);
	}

}
