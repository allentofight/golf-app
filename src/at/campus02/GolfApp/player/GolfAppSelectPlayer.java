package at.campus02.GolfApp.player;

import java.util.ArrayList;
import java.util.Map;

import android.app.ListActivity;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;
import at.campus02.GolfApp.GolfAppPlayRound;
import at.campus02.GolfApp.R;
import at.campus02.GolfApp.data.GolfAppData;

public class GolfAppSelectPlayer extends ListActivity {

	Button add;
	Button ok;
	Button cancel;
	String courseName;
	int courseId;
	ListView lv;
	TextView tv;
	ArrayAdapter<String> adapter;
	ArrayList<String> selectedChildren = new ArrayList<String>();

	@Override
	protected void onStart() {
		super.onStart();
		setContentView(R.layout.selectplayer);

		// Get vars from previous View
		if (getIntent().hasExtra("courseName") == true
				&& getIntent().hasExtra("courseId") == true) {
			courseName = getIntent().getExtras().getString("courseName");
			courseId = getIntent().getExtras().getInt("courseId");
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
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_multiple_choice, players);
		lv.setAdapter(adapter);
		lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

		// OK Button
		ok = (Button) findViewById(R.id.ok);
		ok.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				selectedChildren.clear();
				for (int i = 0; i < lv.getChildCount(); i++) {
					CheckedTextView c = (CheckedTextView) lv.getChildAt(i);
					if (c.isChecked()) {
						String child = c.getText().toString();
						selectedChildren.add(child);
					}
				}
				Intent myIntent = new Intent(view.getContext(),
						GolfAppPlayRound.class);
				myIntent.putExtra("courseName", courseName);
				myIntent.putExtra("courseId", courseId);
				myIntent.putStringArrayListExtra("ArraySelectedPlayer",
						selectedChildren);
				startActivityForResult(myIntent, 0);
			}
		});

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
