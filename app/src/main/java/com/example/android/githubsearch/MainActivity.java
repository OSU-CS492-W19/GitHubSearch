package com.example.android.githubsearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.net.URL;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView mSearchResultsRV;
    private EditText mSearchBoxET;
    private GitHubSearchAdapter mGitHubSearchAdapter;

    private String[] dummySearchResults = {
            "Dummy search results",
            "Dummy search results",
            "Dummy search results",
            "Dummy search results",
            "Dummy search results",
            "Dummy search results",
            "Dummy search results",
            "Dummy search results",
            "Dummy search results",
            "Dummy search results"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchBoxET = (EditText)findViewById(R.id.et_search_box);
        mSearchResultsRV = (RecyclerView)findViewById(R.id.rv_search_results);

        mSearchResultsRV.setLayoutManager(new LinearLayoutManager(this));
        mSearchResultsRV.setHasFixedSize(true);

        mGitHubSearchAdapter = new GitHubSearchAdapter();
        mSearchResultsRV.setAdapter(mGitHubSearchAdapter);

        Button searchButton = (Button)findViewById(R.id.btn_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = mSearchBoxET.getText().toString();
                if (!TextUtils.isEmpty(searchQuery)) {
                    ArrayList<String> resultsList = new ArrayList<>();
                    String results = doGitHubSearch(searchQuery);
                    resultsList.add(results);
                    mGitHubSearchAdapter.updateSearchResults(resultsList);
                    mSearchBoxET.setText("");
                }
            }
        });
    }

    private String doGitHubSearch(String query) {
        URL url = GitHubUtils.buildGitHubSearchURL(query);
        Log.d(TAG, "querying search URL: " + url);
        String results = null;
        try {
            results = NetworkUtils.doHTTPGet(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }
}