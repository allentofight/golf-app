package at.campus02.GolfApp;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import at.campus02.GolfApp.data.GolfAppData;

public class GolfAppPlayRound extends ListActivity implements GolfAppLists {

	Button result;
	Button cancel;
	String courseName;
	int courseId;
	TextView tv;

	// ArrayList<String> selectedPlayer = new ArrayList<String>();

	protected void onStart() {
		super.onStart();
		setContentView(R.layout.playround);

		// Get vars from previous View
		if (getIntent().hasExtra("courseName") == true
				&& getIntent().hasExtra("courseId") == true)
		// && getIntent().hasExtra("ArraySelectedPlayer") == true)
		{
			courseName = getIntent().getExtras().getString("courseName");
			courseId = getIntent().getExtras().getInt("courseId");
			// selectedPlayer = getIntent().getExtras().getStringArrayList(
			// "ArraySelectedPlayer");
			selectedPlayer.clear();
			selectedPlayer.addAll(getIntent().getExtras().getStringArrayList(
					"ArraySelectedPlayer"));
			// Show Course Name
			tv = (TextView) findViewById(R.id.courseName);
			tv.setText(courseName);
		}

		GolfAppData data = new GolfAppData(this);
		Cursor c = data.getHolesByCourse(courseId);
		startManagingCursor(c);

		String[] from = new String[] { "_id", "par" };
		int[] to = new int[] { android.R.id.text1, android.R.id.text2 };

		final SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_2, c, from, to);

		this.setListAdapter(adapter);

		// Result Button
		result = (Button) findViewById(R.id.result);
		result.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				if (getIntent().hasExtra("onlyCourse") == true) {
					Toast.makeText(getApplicationContext(),
							"Sie müssen die Runde starten", Toast.LENGTH_SHORT)
							.show();
					return;
				}
				Intent myIntent = new Intent(view.getContext(),
						GolfAppResult.class);
				myIntent.putExtra("courseName", courseName);
				myIntent.putExtra("courseId", courseId);
				myIntent.putStringArrayListExtra("ArraySelectedPlayer",
						selectedPlayer);
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

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		TextView textView = (TextView) v.findViewById(android.R.id.text1);
		String course_name = textView.getText().toString();
		// int course_id = (int) l.getItemIdAtPosition(position);

		if (getIntent().hasExtra("onlyCourse") == true) {
			Toast.makeText(getApplicationContext(),
					"Sie müssen die Runde starten", Toast.LENGTH_SHORT).show();
			return;
		}

		Intent myIntent = new Intent(getApplicationContext(),
				GolfAppSetShotsForHole.class);
		myIntent.putExtra("courseName", course_name);
		myIntent.putExtra("courseId", courseId);
		myIntent.putStringArrayListExtra("ArraySelectedPlayer", selectedPlayer);
		startActivity(myIntent);
	}
}
