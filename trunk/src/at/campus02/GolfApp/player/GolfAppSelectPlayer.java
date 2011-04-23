package at.campus02.GolfApp.player;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import at.campus02.GolfApp.R;

public class GolfAppSelectPlayer extends ListActivity {

	Button add;
	Button cancel;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selectplayer);

		// Player

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

	// ToDo: Scheri - DATA
	static final String[] PLAYER = new String[] { "Andreas (-35)",
			"Christoph (-45)", "Stephan (-18)", "Willi (-37)" };
}
