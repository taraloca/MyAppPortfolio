package terrafirmacreations.portfolio.movieDb;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.widget.Toast;

import terrafirmacreations.portfolio.R;

/**
 * Created by verityt on 12/19/15.
 */
public class PreferencesActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getFragmentManager()
				.beginTransaction()
				.replace(android.R.id.content, new MyPreferenceFragment())
				.commit();

	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
	                                      String key) {
		updatePreferences(findPreference(key));
	}

	private void updatePreferences(Preference p) {
		if (p instanceof ListPreference) {
			ListPreference listPreference = (ListPreference) p;
			p.setSummary(listPreference.getEntry());
			Toast.makeText(this, "Got the preference update", Toast.LENGTH_LONG);
		}
	}

	public static class MyPreferenceFragment extends PreferenceFragment {
		@Override
		public void onCreate(final Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.xml.preferences);
		}
	}

}