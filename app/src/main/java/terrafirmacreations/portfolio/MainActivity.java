package terrafirmacreations.portfolio;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import terrafirmacreations.portfolio.movieDb.TheMovieDbActivity;


public class MainActivity extends AppCompatActivity {

	Button spotifyStreamerBtn;
	Button scoresApp;
	Button libraryApp;
	Button buidItBigger;
	Button xyzReader;
	Button capstone;
	Context context;
	// Anonymous implementation of OnClickListener
	private View.OnClickListener myButtonOnClickHandler = new View.OnClickListener() {
		public void onClick(View v) {
			Toast toast;

			switch (v.getId()) {
				case R.id.button_movie_db:
					Intent intent = new Intent(context, TheMovieDbActivity.class);
					startActivity(intent);
					break;
				case R.id.button_scores_app:
					toast = Toast.makeText(context, R.string.toastMsgScoresApp, Toast.LENGTH_LONG);
					toast.show();
					break;
				case R.id.button_library_app:
					toast = Toast.makeText(context, R.string.toastMsgLibraryApp, Toast.LENGTH_LONG);
					toast.show();
					break;
				case R.id.button_build_it_bigger:
					toast = Toast.makeText(context, R.string.toastMsgBiggerApp, Toast.LENGTH_LONG);
					toast.show();
					break;
				case R.id.button_xyz_reader:
					toast = Toast.makeText(context, R.string.toastMsgXyzReader, Toast.LENGTH_LONG);
					toast.show();
					break;
				case R.id.button_capstone_my_app:
					toast = Toast.makeText(context, R.string.toastMsgCapstoneApp, Toast.LENGTH_LONG);
					toast.show();
					break;
				default:
					return;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main_activity);
		context = this;

		spotifyStreamerBtn = (Button) findViewById(R.id.button_movie_db);
		spotifyStreamerBtn.setOnClickListener(myButtonOnClickHandler);
		scoresApp = (Button) findViewById(R.id.button_scores_app);
		scoresApp.setOnClickListener(myButtonOnClickHandler);
		libraryApp = (Button) findViewById(R.id.button_library_app);
		libraryApp.setOnClickListener(myButtonOnClickHandler);
		buidItBigger = (Button) findViewById(R.id.button_build_it_bigger);
		buidItBigger.setOnClickListener(myButtonOnClickHandler);
		xyzReader = (Button) findViewById(R.id.button_xyz_reader);
		xyzReader.setOnClickListener(myButtonOnClickHandler);
		capstone = (Button) findViewById(R.id.button_capstone_my_app);
		capstone.setOnClickListener(myButtonOnClickHandler);

		Toolbar topToolBar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(topToolBar);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		topToolBar.setTitle(getString(R.string.myNanoDegreeApps));
	}
}