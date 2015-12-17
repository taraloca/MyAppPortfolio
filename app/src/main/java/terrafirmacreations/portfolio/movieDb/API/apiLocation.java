package terrafirmacreations.portfolio.movieDb.API;


import android.graphics.Movie;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by verityt on 12/13/15.
 */
public interface apiLocation {
	@GET("/feeds/flowers.json")
	public void getData(Callback<List<Movie>> response);

}
