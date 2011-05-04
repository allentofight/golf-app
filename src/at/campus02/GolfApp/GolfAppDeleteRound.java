package at.campus02.GolfApp;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import at.campus02.GolfApp.course.GolfAppCourse;
import at.campus02.GolfApp.data.GolfAppData;

public class GolfAppDeleteRound extends Activity implements GolfAppLists {

	Button yes;
	Button no;
	TextView tv;

	protected void onStart() {
		super.onStart();

		setContentView(R.layout.deleteround);
		tv = (TextView) findViewById(R.id.courseName);
		tv.setText("Die vorherige Runde wird gelöscht!");
		tv = (TextView) findViewById(R.id.sure);
		tv.setText("Sind Sie sicher dass Sie fortfahren möchten?");

		// Yes Button
		yes = (Button) findViewById(R.id.yes);
		yes.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				GolfAppData data = new GolfAppData(getApplicationContext());
				data.newRound();
				selectedPlayer.clear();
				Intent myIntent = new Intent(view.getContext(),
						GolfAppCourse.class);
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
