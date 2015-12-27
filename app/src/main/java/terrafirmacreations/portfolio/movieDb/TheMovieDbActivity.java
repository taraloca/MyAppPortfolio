package terrafirmacreations.portfolio.movieDb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.squareup.okhttp.OkHttpClient;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import terrafirmacreations.portfolio.R;
import terrafirmacreations.portfolio.movieDb.API.MovieDatabaseApiClient;
import terrafirmacreations.portfolio.movieDb.Model.MovieDbModel;
import terrafirmacreations.portfolio.movieDb.Model.MovieDbResults;


/**
 * Created by verityt on 12/13/15.
 */
public class TheMovieDbActivity extends AppCompatActivity {

	private final String DEBUG = getClass().getSimpleName();
	Toolbar topToolBar;
	ProgressBar progressBar;
	private GridView gridView;
	private List<MovieDbResults> movieDbResultsList;
	private String ENDPOINT_URL = "https://api.themoviedb.org";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.movie_db_activity);

		gridView = (GridView) findViewById(R.id.gridView);
		gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

				//Get item at position
				MovieDbResults item = (MovieDbResults) parent.getItemAtPosition(position);

				Intent intent = new Intent(TheMovieDbActivity.this, MovieDetailsActivity.class);

				setIntentExtras(intent, item);

				//Start details activity
				startActivity(intent);
			}
		});

		topToolBar = (Toolbar) findViewById(R.id.toolbar);

		setSupportActionBar(topToolBar);

		getSupportActionBar().setDisplayShowTitleEnabled(false);

		progressBar = (ProgressBar) findViewById(R.id.progressBar);

		retrieveSavedPreferences();

	}

	@Override
	protected void onStart() {
		super.onStart();

		retrieveSavedPreferences();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			Intent i = new Intent(this, PreferencesActivity.class);
			startActivity(i);
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	private void setIntentExtras(Intent intent, MovieDbResults item) {
		intent.putExtra(Constants.MovieProperties.MOVIE_TITLE, item.getTitle());
		intent.putExtra(Constants.MovieProperties.MOVIE_SYNOPSIS, item.getOverview());
		intent.putExtra(Constants.MovieProperties.MOVIE_REALEASE_DATE, item.getRelease_date());
		intent.putExtra(Constants.MovieProperties.MOVIE_RATING, item.getVote_average());
		intent.putExtra(Constants.MovieProperties.MOVIE_POSTER_PATH, item.getPoster_path());
	}

	private void retrieveSavedPreferences() {
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(this);

		String name = sharedPreferences.getString("movieSortOrder", "-1");
		setTitleAndSubtitle(name);

		if (name != null) {
			name = name.equals("1") ? this.getString(R.string.sort_order_popularity) : this.getString(R.string.sort_order_vote_average);

			makeDataCall(name);
		}
	}

	/**
	 * Sets the Title and Subtitle on the Toolbar.
	 *
	 * @param name
	 */
	private void setTitleAndSubtitle(String name) {
		name = name.equals("1") ? this.getString(R.string.popularity) : this.getString(R.string.highest_rating);

		topToolBar.setTitle(getString(R.string.movieDatabase));
		topToolBar.setSubtitle(name);
	}

	/**
	 * Gets requested data with user choice of sort order. Default run is sort order of Popularity.
	 *
	 * @param sortOrderName
	 */
	private void makeDataCall(String sortOrderName) {
		// creating a RestAdapter using the custom client
		OkHttpClient okHttpClient = new OkHttpClient();
		RestAdapter restAdapter = new RestAdapter.Builder()
				.setEndpoint(ENDPOINT_URL)
				.setLogLevel(RestAdapter.LogLevel.FULL)
				.setClient(new OkClient(okHttpClient))
				.build();

		MovieDatabaseApiClient client = restAdapter.create(MovieDatabaseApiClient.class);
		progressBar.setVisibility(View.VISIBLE);

		retrofit.Callback<MovieDbModel> callback = new Callback<MovieDbModel>() {
			@Override
			public void success(MovieDbModel movieModels, retrofit.client.Response response) {
				movieDbResultsList = movieModels.getResults();
				MoviesGridViewAdapter adapter = new MoviesGridViewAdapter(getApplicationContext(), R.layout.movie_gridview_item, movieDbResultsList);
				gridView.setAdapter(adapter);
				progressBar.setVisibility(View.GONE);
			}

			@Override
			public void failure(RetrofitError error) {
				Log.e(DEBUG, "RetrofitError is " + error.toString());
			}
		};

		client.getData(getResources().getString(R.string.api_key), sortOrderName, callback);
	}
}