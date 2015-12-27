package terrafirmacreations.portfolio.movieDb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import terrafirmacreations.portfolio.R;

/**
 * Created by verityt on 12/13/15.
 */
public class MovieDetailsActivity extends AppCompatActivity {

	private String movieTitle;
	private String movieSynopsis;
	private String movieRating;
	private String movieReleaseDate;
	private String moviePosterPath;
	private Toolbar topToolBar;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.movie_details);

		topToolBar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(topToolBar);
		getSupportActionBar().setDisplayShowTitleEnabled(false);

		getExtras();

		setUi();
	}

	private void getExtras() {
		movieTitle = getIntent().getStringExtra(Constants.MovieProperties.MOVIE_TITLE);
		movieSynopsis = getIntent().getStringExtra(Constants.MovieProperties.MOVIE_SYNOPSIS);
		movieRating = getIntent().getStringExtra(Constants.MovieProperties.MOVIE_RATING);
		movieReleaseDate = getIntent().getStringExtra(Constants.MovieProperties.MOVIE_REALEASE_DATE);
		moviePosterPath = getIntent().getStringExtra(Constants.MovieProperties.MOVIE_POSTER_PATH);
	}

	/**
	 * sets toolbar title to movie title
	 */
	private void setToolbarTitle() {
		topToolBar.setTitle(movieTitle);
	}

	private void setUi() {
		String url = "http://image.tmdb.org/t/p/w780";

		setToolbarTitle();

		ImageView backgroundImage = (ImageView) findViewById(R.id.backgroundImageView);
		Picasso.with(this).load(url + moviePosterPath).fit().into(backgroundImage);

		backgroundImage.setAlpha(0.2f);

		TextView rating = (TextView) findViewById(R.id.movieRatingValue);
		rating.setText(movieRating);

		TextView releaseDate = (TextView) findViewById(R.id.movieReleaseDateValue);
		releaseDate.setText(movieReleaseDate);

		TextView synopsis = (TextView) findViewById(R.id.movieSynopsisValue);
		synopsis.setMovementMethod(new ScrollingMovementMethod());
		synopsis.setText(movieSynopsis);

	}
}
