package at.campus02.GolfApp.player;

import java.util.Map;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import at.campus02.GolfApp.R;
import at.campus02.GolfApp.data.GolfAppData;

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

		GolfAppData data = new GolfAppData(getApplicationContext());
		Map<String, Integer> map = data.allPlayers();

		String[] players = (String[]) map.keySet().toArray(
				new String[map.keySet().size()]);

		// Select Player ListView
		lv = (ListView) this.getListView();
		// Set option as Multiple Choice. So that user can able to select more
		// the one option from list
		lv.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_multiple_choice, players));
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
}
