package at.campus02.GolfApp.course;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import at.campus02.GolfApp.R;

public class GolfAppCourse extends ListActivity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selectgolfcourse);

		setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item,
				GOLFCOURSES));

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
	}

	// ToDo: Scheri - DATA
	static final String[] GOLFCOURSES = new String[] { "Murhof",
			"Golf & Country Club Schloss Pichlarn",
			"Golf- & Landclub Ennstal Weißenbach/Liezen",
			"Thermengolfclub Fürstenfeld - Loipersdorf", "Bad Gleichenberg",
			"GC Gut Murstätten", "GC Schloß Frauenthal", "GC Gut Freiberg",
			"Golf & Country Club Reiting", "GC St.Lorenzen",
			"GC Erzherzog Johann", "Golfclub Murtal", "GC Almenland-Passail",
			"Golfclub Graz-Puntigam", "Golfclub Bad Waltersdorf",
			"Golfclub Graz Andritz St. Gotthard", "Golfclub Thalersee",
			"Golfclub Murau-Kreischberg", "Golfclub Mariahof" };

}
