package at.campus02.GolfApp;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import at.campus02.GolfApp.data.GolfAppData;
import at.campus02.GolfApp.external.NumberPicker;

public class GolfAppPlayRound extends ListActivity {

	Button ok;
	Button cancel;
	NumberPicker shots;
	String courseName;
	int courseId;
	TextView tv;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.playround);

		// Get vars from previous View
		if (getIntent().hasExtra("courseName") == true
				&& getIntent().hasExtra("courseId") == true) {
			courseName = getIntent().getExtras().getString("courseName");
			courseId = getIntent().getExtras().getInt("courseId");
			// Show Course Name
			tv = (TextView) findViewById(R.id.courseName);
			tv.setText(courseName);
		}

		GolfAppData data = new GolfAppData(this);
		Cursor c = data.getHolesByCourse(courseId);
		startManagingCursor(c);

		String[] from = new String[] { "par", "_id" };
		int[] to = new int[] { android.R.id.text1, android.R.id.text2 };

		final SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_2, c, from, to);

		this.setListAdapter(adapter);

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
