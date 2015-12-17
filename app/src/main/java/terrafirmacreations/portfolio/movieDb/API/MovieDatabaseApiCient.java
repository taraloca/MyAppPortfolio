package terrafirmacreations.portfolio.movieDb.API;


import retrofit.Callback;
import retrofit.http.GET;
import terrafirmacreations.portfolio.movieDb.Model.MovieDbModel;

/**
 * Created by verityt on 12/13/15.
 */
public interface MovieDatabaseApiCient {
	@GET("/3/movie/popular?api_key=556d85348e9deb2014a44e2c7938323c")
		void getData(Callback<MovieDbModel> response);

}