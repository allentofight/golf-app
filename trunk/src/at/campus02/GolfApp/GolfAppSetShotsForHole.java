package at.campus02.GolfApp;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import at.campus02.GolfApp.external.NumberPicker;

public class GolfAppSetShotsForHole extends ListActivity {

	Button ok;
	Button cancel;
	NumberPicker shots;
	String courseName;
	int courseId;
	TextView tv;
	ListView lv;
	ArrayList<String> selectedPlayer = new ArrayList<String>();
	int par;
	int hole;

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

			// Get from coursedata (courseName) the par and hole;
			char[] ch = courseName.toCharArray();
			for (int i = ch.length - 1; i < ch.length; i++) {
				char parchar = ch[i];
				par = parchar - 48;
			}
			char parchar = ch[0];
			hole = parchar - 48;

			// ListView
			setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item,
					selectedPlayer));

			lv = (ListView) this.getListView();
			lv.setTextFilterEnabled(true);
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

	protected void onListItemClick(ListView l, View v, int position, long id) {
		String player = (String) l.getItemAtPosition(position);
		Intent myIntent = new Intent(getApplicationContext(),
				GolfAppSetShotsForHolePlayer.class);
		myIntent.putExtra("hole", hole);
		myIntent.putExtra("courseId", courseId);
		myIntent.putExtra("player", player);
		myIntent.putExtra("par", par);
		startActivity(myIntent);
	}
}
