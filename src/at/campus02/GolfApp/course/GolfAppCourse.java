package at.campus02.GolfApp.course;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import at.campus02.GolfApp.R;
import at.campus02.GolfApp.data.GolfAppData;
import at.campus02.GolfApp.player.GolfAppSelectPlayer;

public class GolfAppCourse extends ListActivity {

	Button ok;
	Button cancel;
	ListView lv;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selectgolfcourse);

		GolfAppData data = new GolfAppData(this);
		// test
		Cursor c = data.allCourses();

		startManagingCursor(c);
		getListView().setOnCreateContextMenuListener(this);

		final SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				R.layout.list_item, c, new String[] { "_id" },
				new int[] { R.id.golfCourse });

		// adapter.setViewBinder(new ViewBinder() {
		// public boolean setViewValue(View view, Cursor theCursor, int column)
		// {
		// final String ColNameModel = theCursor.getString(1); // Name und
		// // Model
		// ((TextView) view).setText(ColNameModel);
		// return true;
		// }
		// });

		this.setListAdapter(adapter);

		// TODO eigener Adapter der String bei courses ausgibt aber integer
		// merkt - für die Datenbank
		// setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item,
		// courses));

		lv = (ListView) this.getListView();
		lv.setTextFilterEnabled(true);

		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// When clicked, show a toast with the TextView text
				// Toast.makeText(getApplicationContext(),
				// ((TextView) view).getText(), Toast.LENGTH_SHORT).show();

				String text = "" + lv.getItemAtPosition(position);
				Intent myIntent = new Intent(view.getContext(),
						GolfAppSelectPlayer.class);
				myIntent.putExtra("courseName", text);
				startActivityForResult(myIntent, 0);

			}
		});

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

	// ToDo: Scheri - DATA
	static final String[] GOLFCOURSES = new String[] { "Murhof (18)",
			"Golf & Country Club Schloss Pichlarn (18)",
			"Golf- & Landclub Ennstal Wei�enbach/Liezen (18)",
			"Thermengolfclub F�rstenfeld - Loipersdorf (18)",
			"Bad Gleichenberg (9)", "GC Gut Murst�tten (18)",
			"GC Schlo� Frauenthal (18)", "GC Gut Freiberg (18)",
			"Golf & Country Club Reiting (18)", "GC St.Lorenzen (18)",
			"GC Erzherzog Johann (18)", "Golfclub Murtal (18)",
			"GC Almenland-Passail (18)", "Golfclub Graz-Puntigam (18)",
			"Golfclub Bad Waltersdorf (18)",
			"Golfclub Graz Andritz St. Gotthard (18)",
			"Golfclub Thalersee (18)", "Golfclub Murau-Kreischberg (18)",
			"Golfclub Mariahof (18)" };

}
