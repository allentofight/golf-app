package at.campus02.GolfApp.course;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import at.campus02.GolfApp.R;
import at.campus02.GolfApp.data.GolfAppData;
import at.campus02.GolfApp.player.GolfAppSelectPlayer;

public class GolfAppCourse extends ListActivity {

	Button ok;
	Button cancel;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selectgolfcourse);

		GolfAppData data = new GolfAppData(this);
		Cursor c = data.allCourses();

		startManagingCursor(c);
		getListView().setOnCreateContextMenuListener(this);

		final SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_1, c, new String[] { "_id",
						"name" }, new int[] { android.R.id.text2,
						android.R.id.text1 });

		getListView().setAdapter(adapter);
		getListView().setTextFilterEnabled(true);

		// OK Button
		ok = (Button) findViewById(R.id.ok);
		ok.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
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

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		String text = "" + getListView().getItemAtPosition(position);
		long course_id = getListView().getItemIdAtPosition(position);
		Intent myIntent = new Intent(v.getContext(), GolfAppSelectPlayer.class);
		myIntent.putExtra("courseName", text);
		myIntent.putExtra("courseId", course_id);
		startActivityForResult(myIntent, 0);
	}

}
