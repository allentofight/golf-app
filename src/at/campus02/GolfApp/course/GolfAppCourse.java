package at.campus02.GolfApp.course;

import java.util.Map;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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
		Map<String, String> map = data.allCourses();

		String[] courses = (String[]) map.values().toArray(
				new String[map.values().size()]);

		setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item,
				courses));

		ListView lv = (ListView) this.getListView();
		lv.setTextFilterEnabled(true);

		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// When clicked, show a toast with the TextView text
				// Toast.makeText(getApplicationContext(),
				// ((TextView) view).getText(), Toast.LENGTH_SHORT).show();

				Intent myIntent = new Intent(view.getContext(),
						GolfAppSelectPlayer.class);
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
