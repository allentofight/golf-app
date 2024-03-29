package at.campus02.GolfApp.course;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import at.campus02.GolfApp.GolfAppLists;
import at.campus02.GolfApp.GolfAppPlayRound;
import at.campus02.GolfApp.R;
import at.campus02.GolfApp.data.GolfAppData;
import at.campus02.GolfApp.player.GolfAppSelectPlayer;

public class GolfAppCourse extends ListActivity implements GolfAppLists {

	Button ok;
	Button cancel;

	protected void onStart() {
		super.onStart();
		setContentView(R.layout.selectgolfcourse);

		GolfAppData data = new GolfAppData(this);
		Cursor c = data.allCourses();
		startManagingCursor(c);

		String[] from = new String[] { "name", "_id" };
		int[] to = new int[] { android.R.id.text1, android.R.id.text2 };

		final SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_2, c, from, to);

		this.setListAdapter(adapter);

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
		int course_id = (int) l.getItemIdAtPosition(position);
		Intent myIntent = new Intent();

		if (getIntent().hasExtra("onlyCourse") == true) {
			myIntent = new Intent(getApplicationContext(),
					GolfAppPlayRound.class);
			myIntent.putStringArrayListExtra("ArraySelectedPlayer",
					selectedPlayer);
		} else {
			myIntent = new Intent(getApplicationContext(),
					GolfAppSelectPlayer.class);
		}
		myIntent.putExtra("courseName", course_name);
		myIntent.putExtra("courseId", course_id);
		myIntent.putExtra("onlyCourse", "onlyCourse");
		startActivity(myIntent);
	}
}
