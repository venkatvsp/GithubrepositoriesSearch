package varam.githubrepositoriessearch;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText mSearchBoxEditText;

    private TextView mUrlDisplayTextView;

    private TextView mSearchResultsTextView;

    private Button mSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchBoxEditText = findViewById(R.id.search_box);

        mUrlDisplayTextView = findViewById(R.id.url_display);
        mSearchResultsTextView = findViewById(R.id.search_results);
        mSearch = findViewById(R.id.search_button);


    }

    public void  DoGithubSearch(View view) {
        String githubQuery = mSearchBoxEditText.getText().toString();
        URL githubSearchUrl = Utils.buildUrl(githubQuery);
        mUrlDisplayTextView.setText(githubSearchUrl.toString());

        new GithubSearch().execute(githubSearchUrl);
    }

    public class GithubSearch extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            URL searchUrl = urls[0];
            String SearchResults = null;
            try {
                SearchResults = Utils.ResponseForHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return SearchResults;
        }

        @Override
        public void onPostExecute(String result){
            mSearchResultsTextView.setText(result);
        }
    }

}
