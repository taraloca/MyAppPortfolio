package terrafirmacreations.portfolio.movieDb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import terrafirmacreations.portfolio.R;
import terrafirmacreations.portfolio.movieDb.API.MovieDatabaseApiCient;
import terrafirmacreations.portfolio.movieDb.Model.MovieDbModel;
import terrafirmacreations.portfolio.movieDb.Model.MovieDbResults;


/**
 * Created by verityt on 12/13/15.
 */
public class TheMovieDbActivity extends AppCompatActivity {

    private GridView gridView;
    private List<MovieDbResults> movieDbResultsList;
	private String ENDPOINT_URL = "https://api.themoviedb.org";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_db_activity);

	    gridView = (GridView)findViewById(R.id.gridView);

	    Toolbar topToolBar = (Toolbar)findViewById(R.id.toolbar);
	    setSupportActionBar(topToolBar);
	    getSupportActionBar().setDisplayShowTitleEnabled(false);

        final RestAdapter restadapter = new RestAdapter.Builder().setEndpoint(ENDPOINT_URL).build();

        MovieDatabaseApiCient apiLocation = restadapter.create(MovieDatabaseApiCient.class);

	    apiLocation.getData(new Callback<MovieDbModel>() {
		    @Override
		    public void success(MovieDbModel movieModels, Response response) {
			    movieDbResultsList = movieModels.getResults();
			    MoviesGridViewAdapter adapter = new MoviesGridViewAdapter(getApplicationContext(), R.layout.movie_gridview_item, movieDbResultsList);
			    gridView.setAdapter(adapter);
		    }

		    @Override
		    public void failure(RetrofitError error) {
			    Log.d("ERROR", error.toString());
			    Toast.makeText(getApplicationContext(), "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
		    }
	    });

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
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}