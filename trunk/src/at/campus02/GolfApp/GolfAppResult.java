package at.campus02.GolfApp;

import java.util.ArrayList;

import android.app.ListActivity;
import android.database.Cursor;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import at.campus02.GolfApp.data.GolfAppData;

public class GolfAppResult extends ListActivity {

	Button end;
	Button cancel;
	TextView tv;
	ListView lv;
	String courseName;
	int courseId;
	ArrayList<String> player = new ArrayList<String>();

	protected void onStart() {
		super.onStart();
		setContentView(R.layout.result);

		// Get vars from previous View
		if (getIntent().hasExtra("courseName") == true
				&& getIntent().hasExtra("courseId") == true
				&& getIntent().hasExtra("ArraySelectedPlayer") == true) {

			player = getIntent().getExtras().getStringArrayList(
					"ArraySelectedPlayer");
			courseName = getIntent().getExtras().getString("courseName");
			courseId = getIntent().getExtras().getInt("courseId");

			// setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item,
			// player));
			// lv = (ListView) this.getListView();
			// lv.setTextFilterEnabled(true);

			// TODO Stephan
			tv = (TextView) findViewById(R.id.courseName);
			tv.setText(courseName);

			GolfAppData data = new GolfAppData(this);
			Cursor c = data.getResult(courseId, "Hans"); // Derzeit noch
															// Hardcodiert, weil
															// es von einer
															// ArrayList kommt
			startManagingCursor(c);

			String[] from = new String[] { "player_name", "total_swings" };
			int[] to = new int[] { android.R.id.text1, android.R.id.text2 };

			final SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
					android.R.layout.simple_list_item_2, c, from, to);

			this.setListAdapter(adapter);

		}

		// Cancel Button
		cancel = (Button) findViewById(R.id.cancel);
		cancel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				finish();
			}
		});

	}
}
