package at.campus02.GolfApp.player;

import java.util.Map;

import android.app.ListActivity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import at.campus02.GolfApp.R;
import at.campus02.GolfApp.data.GolfAppData;

public class GolfAppPlayer extends ListActivity {

	Button add;
	Button ok;
	Button cancel;
	ListView lv;

	@Override
	protected void onStart() {
		super.onStart();

		setContentView(R.layout.manageplayer);

		GolfAppData data = new GolfAppData(getApplicationContext());
		Map<String, Integer> map = data.allPlayers();

		String[] players = (String[]) map.keySet().toArray(
				new String[map.keySet().size()]);

		// ListView
		setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item,
				players));

		lv = (ListView) this.getListView();
		lv.setTextFilterEnabled(true);

		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String player = (String) lv.getItemAtPosition(position);
				Intent myIntent = new Intent(getApplicationContext(),
						GolfAppDeletePlayer.class);
				myIntent.putExtra("player", player);
				startActivity(myIntent);
			}
		});

		// Ok Button
		ok = (Button) findViewById(R.id.ok);
		ok.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				finish();
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
