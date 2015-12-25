package terrafirmacreations.portfolio.movieDb.API;


import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import terrafirmacreations.portfolio.movieDb.Model.MovieDbModel;

/**
 * Created by verityt on 12/13/15.
 */

public interface MovieDatabaseApiClient {
	@GET("/3/discover/movie")
	void getData(@Query("api_key") String apiKey, @Query("sort_by") String sortByValue, Callback<MovieDbModel> response);
}
