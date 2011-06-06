package at.campus02.GolfApp.data;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GolfAppData extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "golfApp.db";
	private static final int DATABASE_VERSION = 1;

	public GolfAppData(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		// GOLF_COURSE
		String createGolfCourse = "CREATE TABLE golfcourse (_id INTEGER PRIMARY KEY, name TEXT NOT NULL)";
		db.execSQL(createGolfCourse);

		// HOLE
		String createGolfCourseHoles = "CREATE TABLE hole (course_id INTEGER, _id INTEGER, redDistance INTEGER, yellowDistance INTEGER, par INTEGER, handicap INTEGER, PRIMARY KEY(course_id, _id))";
		db.execSQL(createGolfCourseHoles);

		loadGolfCoursesIntoDb(db);

		insertHole(db, 1, 1, 421, 463, 5, 5);
		insertHole(db, 1, 2, 98, 112, 3, 17);
		insertHole(db, 1, 3, 291, 322, 4, 11);
		insertHole(db, 1, 4, 129, 141, 3, 13);
		insertHole(db, 1, 5, 305, 325, 4, 1);
		insertHole(db, 1, 6, 254, 290, 4, 3);
		insertHole(db, 1, 7, 235, 278, 4, 15);
		insertHole(db, 1, 8, 260, 303, 4, 7);
		insertHole(db, 1, 9, 414, 477, 5, 9);
		insertHole(db, 1, 10, 421, 463, 5, 6);
		insertHole(db, 1, 11, 98, 112, 3, 18);
		insertHole(db, 1, 12, 291, 322, 4, 12);
		insertHole(db, 1, 13, 129, 141, 3, 14);
		insertHole(db, 1, 14, 305, 325, 4, 2);
		insertHole(db, 1, 15, 254, 290, 4, 4);
		insertHole(db, 1, 16, 235, 278, 4, 16);
		insertHole(db, 1, 17, 260, 303, 4, 8);
		insertHole(db, 1, 18, 414, 477, 5, 10);

		insertHole(db, 2, 1, 421, 463, 5, 5);
		insertHole(db, 2, 2, 98, 112, 3, 17);
		insertHole(db, 2, 3, 291, 322, 4, 11);
		insertHole(db, 2, 4, 129, 141, 3, 13);
		insertHole(db, 2, 5, 305, 325, 4, 1);
		insertHole(db, 2, 6, 254, 290, 4, 3);
		insertHole(db, 2, 7, 235, 278, 4, 15);
		insertHole(db, 2, 8, 260, 303, 4, 7);
		insertHole(db, 2, 9, 414, 477, 5, 9);
		insertHole(db, 2, 10, 421, 463, 5, 6);
		insertHole(db, 2, 11, 98, 112, 3, 18);
		insertHole(db, 2, 12, 291, 322, 4, 12);
		insertHole(db, 2, 13, 129, 141, 3, 14);
		insertHole(db, 2, 14, 305, 325, 4, 2);
		insertHole(db, 2, 15, 254, 290, 4, 4);
		insertHole(db, 2, 16, 235, 278, 4, 16);
		insertHole(db, 2, 17, 260, 303, 4, 8);
		insertHole(db, 2, 18, 414, 477, 5, 10);

		insertHole(db, 3, 1, 18, 18, 2, 5);
		insertHole(db, 3, 2, 14, 14, 3, 17);
		insertHole(db, 3, 3, 12, 12, 4, 11);
		insertHole(db, 3, 4, 23, 23, 3, 13);
		insertHole(db, 3, 5, 15, 15, 2, 1);
		insertHole(db, 3, 6, 32, 32, 5, 3);
		insertHole(db, 3, 7, 19, 19, 2, 15);
		insertHole(db, 3, 8, 20, 20, 3, 7);
		insertHole(db, 3, 9, 22, 21, 4, 9);

		// PLAYER
		String createPlayer = "CREATE TABLE player (name STRING PRIMARY KEY, gender INTEGER, handicap INTEGER)";
		db.execSQL(createPlayer);

		// ROUND
		String createRound = "CREATE TABLE round (_id INTEGER, hole_number INTEGER, player_name STRING, total_swings INTEGER, date INTEGER, PRIMARY KEY(_id, hole_number, player_name))";
		db.execSQL(createRound);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
	}

	public void newRound() {
		SQLiteDatabase db = getWritableDatabase();
		db.delete("round", null, null);
	}

	private void insertGolfCourse(SQLiteDatabase db, int courseId, String name) {
		ContentValues values = new ContentValues();
		values.put("name", name);
		values.put("_id", courseId);

		db.insertOrThrow("golfcourse", null, values);
	}

	private void insertHole(SQLiteDatabase db, int courseId, int number,
			int redDistance, int yellowDistance, int par, int handicap) {
		ContentValues values = new ContentValues();
		values.put("course_id", courseId);
		values.put("_id", number);
		values.put("yellowDistance", yellowDistance);
		values.put("redDistance", redDistance);
		values.put("par", par);
		values.put("handicap", handicap);

		db.insertOrThrow("hole", null, values);
	}

	public Cursor allCourses() {
		SQLiteDatabase db = getReadableDatabase();

		String[] columns = { "_id", "name" };

		return db.query("golfcourse", columns, null, null, null, null, "_id");
	}

	public Cursor getHolesByCourse(int courseId) {
		SQLiteDatabase db = getReadableDatabase();

		return db
				.rawQuery(
						"select _id || '. Loch - Par ' || par as _id, 'Gelb: ' ||"
								+ " cast(yellowDistance as varchar(10)) ||"
								+ " ' m - Rot: ' || cast(redDistance as varchar(3))|| 'm - Handicap: ' || cast(handicap as varchar(3))"
								+ " as par From hole WHERE course_id = "
								+ courseId, null);
	}

	public Cursor getResult(int courseId, ArrayList<String> player) {
		SQLiteDatabase db = getReadableDatabase();

		String names = "";
		for (int i = 0; i < player.size(); i++) {
			names += " player_name = \"" + player.get(i) + "\" OR";
		}
		String namesquery = names.substring(0, names.length() - 2);

		return db.rawQuery(
				"SELECT _id,player_name,SUM(total_swings) AS total_swings FROM"
						+ " round WHERE _id = " + courseId + " AND ("
						+ namesquery + ") GROUP BY _id,player_name", null);
	}

	public Map<String, Integer> allPlayers() {
		SQLiteDatabase db = getReadableDatabase();

		String[] columns = { "name", "handicap" };

		Cursor cursor = db.query("player", columns, null, null, null, null,
				"name");

		HashMap<String, Integer> playerMap = new HashMap<String, Integer>();

		while (cursor.moveToNext())
			playerMap.put(cursor.getString(0), cursor.getInt(1));

		db.close();

		return playerMap;
	}

	public void insertPlayer(String name, int gender, int handicap) {
		SQLiteDatabase db = getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put("name", name);
		values.put("gender", gender);
		values.put("handicap", handicap);

		db.insertOrThrow("player", null, values);
	}

	public void deletePlayer(String name) {
		SQLiteDatabase db = getWritableDatabase();
		String[] names = { name };
		db.delete("player", "name = ?", names);
	}

	public void insertRound(int courseId, int holeNumber, String playerName,
			int totalSwings, Date date) {
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("_id", courseId);
		values.put("hole_number", holeNumber);
		values.put("player_name", playerName);
		values.put("total_swings", totalSwings);
		values.put("date", Date.UTC(date.getYear(), date.getMonth(),
				date.getDay(), 0, 0, 0));
		try {
			db.insertOrThrow("round", null, values);
		} catch (Exception e) {
			db.replaceOrThrow("round", null, values);
		}
	}

	private void loadGolfCoursesIntoDb(SQLiteDatabase db) {

		int counter = 0;

		for (int i = 1; i <= 9; i++) {
			try {
				insertCourseIntoDb(db, 6 + String.format("%02d", i));
				counter++;
			} catch (Exception e) {
				break;
			}
		}

	}

	private void insertCourseIntoDb(SQLiteDatabase db, String clubId)
			throws MalformedURLException, IOException {

		URL url = new URL("http://www.golf.at/clubs/platzdaten.asp?ClubNr="
				+ clubId + "&Course=1");
		Document document = Jsoup.parse(url, 3000);
		Elements elems = document.getElementsByTag("h3");

		// create an instance of HtmlCleaner
		HtmlCleaner cleaner = new HtmlCleaner();

		// take default cleaner properties
		CleanerProperties props = cleaner.getProperties();

		HttpGet httpGet = new HttpGet(
				"http://www.golf.at/clubs/platzdaten.asp?ClubNr=" + clubId
						+ "&Course=1");
		HttpClient httpclient = new DefaultHttpClient();
		// Execute HTTP Get Request
		HttpResponse response = httpclient.execute(httpGet);
		BufferedInputStream buffer = new BufferedInputStream(response
				.getEntity().getContent(), 8096);

		TagNode node = cleaner.clean(buffer, "iso-8859-1");

		// optionally find parts of the DOM or modify some nodes
		TagNode[] golfclub = node.getElementsByName("h3", true);
		TagNode[] myNodes2 = node.getElementsByAttValue("class", "cell02",
				true, true);

		// skip all clubs which are not as expected
		if (elems.isEmpty())
			return;

		insertGolfCourse(db, Integer.valueOf(clubId), elems.get(0).text());

		// for (int i = 0; i < myNodes2.length; i++) {
		// TagNode tagNode = myNodes2[i];
		//
		// if ((i % 18) == 0)
		// System.out.print("\n");
		//
		// if (!tagNode.getChildren().isEmpty())
		// System.out.print(tagNode.getChildren().get(0).toString() + ";");
		// }

	}
}
