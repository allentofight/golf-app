package at.campus02.GolfApp.player;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import at.campus02.GolfApp.R;

public class GolfAppSelectPlayer extends ListActivity {

	Button add;
	Button cancel;
	ListView lv;
	TextView tv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selectplayer);

		// Get vars from previous View
		if (getIntent().hasExtra("courseName") == true) {
			String courseName = getIntent().getExtras().getString("courseName");
			// Show Course Name
			tv = (TextView) findViewById(R.id.courseName);
			tv.setText(courseName);
		}

		// Select Player ListView
		lv = (ListView) this.getListView();
		// Set option as Multiple Choice. So that user can able to select more
		// the one option from list
		lv.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_multiple_choice, PLAYER));
		lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

		// Add Button
		add = (Button) findViewById(R.id.add);
		add.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(),
						GolfAppAddPlayer.class);
				startActivityForResult(myIntent, 0);
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

	// ToDo: Scheri - DATA
	static final String[] PLAYER = new String[] { "Andreas (-35)",
			"Christoph (-45)", "Stephan (-18)", "Willi (-37)" };
}
