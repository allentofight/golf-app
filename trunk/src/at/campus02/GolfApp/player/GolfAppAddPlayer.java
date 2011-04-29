package at.campus02.GolfApp.player;

import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import at.campus02.GolfApp.R;
import at.campus02.GolfApp.data.GolfAppData;
import at.campus02.GolfApp.external.NumberPicker;

public class GolfAppAddPlayer extends Activity {

	Button ok;
	Button cancel;
	Spinner gender;
	NumberPicker handicap;
	EditText name;
	int genderNum;

	protected void onStart() {
		super.onStart();
		setContentView(R.layout.addplayer);

		// OK Button
		ok = (Button) findViewById(R.id.ok);
		ok.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {

				EditText name = (EditText) findViewById(R.id.name);
				String nameText = name.getText().toString();

				gender = (Spinner) findViewById(R.id.gender);
				String genderText = gender.getSelectedItem().toString();

				handicap = (NumberPicker) findViewById(R.id.handicap);
				int handicapNum = handicap.getCurrent();

				if (nameText != null) {
					genderNum = (genderText == "weiblich" ? 1 : 0);
				}

				GolfAppData data = new GolfAppData(getApplicationContext());
				data.insertPlayer(nameText, genderNum, handicapNum);

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
