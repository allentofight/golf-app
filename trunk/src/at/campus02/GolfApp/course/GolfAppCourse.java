package at.campus02.GolfApp.course;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import at.campus02.GolfApp.R;
import at.campus02.GolfApp.data.GolfAppData;

public class GolfAppCourse extends ListActivity {

	Button ok;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selectgolfcourse);

		GolfAppData data = new GolfAppData(this);
		String[] courses = data.allCourses(getParent());

		setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item,
				courses));

		ListView lv = (ListView) this.getListView();
		lv.setTextFilterEnabled(true);

		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// When clicked, show a toast with the TextView text
				Toast.makeText(getApplicationContext(),
						((TextView) view).getText(), Toast.LENGTH_SHORT).show();
			}
		});

		// OK Button
		ok = (Button) findViewById(R.id.ok);
		ok.setOnClickListener(new View.OnClickListener() {
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
