package at.campus02.GolfApp.player;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import at.campus02.GolfApp.R;
import at.campus02.GolfApp.data.GolfAppData;

public class GolfAppDeletePlayer extends Activity {

	Button yes;
	Button no;
	TextView tv;
	String player;

	protected void onStart() {
		super.onStart();

		setContentView(R.layout.deleteplayer);

		if (getIntent().hasExtra("player") == true) {
			player = getIntent().getExtras().getString("player");
		}

		tv = (TextView) findViewById(R.id.courseName);
		tv.setText("Der Spieler " + player + " wird gelöscht!");
		tv = (TextView) findViewById(R.id.sure);
		tv.setText("Sind Sie sicher, dass Sie fortfahren möchten?");

		// Yes Button
		yes = (Button) findViewById(R.id.yes);
		yes.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				GolfAppData data = new GolfAppData(getApplicationContext());
				data.deletePlayer(player);
				Intent myIntent = new Intent(view.getContext(),
						GolfAppPlayer.class);
				startActivityForResult(myIntent, 0);
			}
		});

		// No Button
		yes = (Button) findViewById(R.id.no);
		yes.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				finish();
			}
		});
	}
}
