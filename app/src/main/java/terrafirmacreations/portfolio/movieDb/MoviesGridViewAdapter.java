package terrafirmacreations.portfolio.movieDb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import terrafirmacreations.portfolio.R;
import terrafirmacreations.portfolio.movieDb.Model.MovieDbResults;

/**
 * Created by verityt on 12/13/15.
 */
public class MoviesGridViewAdapter extends ArrayAdapter<MovieDbResults> {

	final String DEBUG = getClass().getSimpleName();

	String url = "http://image.tmdb.org/t/p/w185";

	private Context context;
	private int layoutResourceId;

	private List<MovieDbResults> movieDbResultsList;
	private MovieDbResults movieResults;

	public MoviesGridViewAdapter(Context context, int layoutResourceId, List<MovieDbResults> objects) {
		super(context, layoutResourceId, objects);
		this.context = context;
		this.layoutResourceId = layoutResourceId;
		this.movieDbResultsList = objects;

	}

	/**
	 * Updates grid data and refresh grid items.
	 *
	 * @param movieDbResultsList
	 */
	public void setGridData(ArrayList<MovieDbResults> movieDbResultsList) {
		this.movieDbResultsList = movieDbResultsList;
		notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		ViewHolder viewHolder;

		if (row == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService((Context.LAYOUT_INFLATER_SERVICE));
			row = inflater.inflate(layoutResourceId, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.imageView = (ImageView) row.findViewById(R.id.grid_item_image);
			row.setTag(viewHolder);

		} else {
			viewHolder = (ViewHolder) row.getTag();
		}

		movieResults = movieDbResultsList.get(position);
		Picasso.with(getContext()).load(url + movieResults.getPoster_path()).into(viewHolder.imageView);
		return row;
	}

	static class ViewHolder {
		ImageView imageView;
	}
}
