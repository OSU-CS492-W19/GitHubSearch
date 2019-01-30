package com.example.android.githubsearch;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URL;

public class GitHubUtils {
    final static String GITHUB_SEARCH_BASE_URL = "https://api.github.com/search/repositories";
    final static String GITHUB_SEARCH_QUERY_PARAM = "q";
    final static String GITHUB_SEARCH_SORT_PARAM = "sort";
    final static String GITHUB_SEARCH_SORT_VALUE = "stars";

    public static URL buildGitHubSearchURL(String query) {
        Uri githubSearchUri = Uri.parse(GITHUB_SEARCH_BASE_URL).buildUpon()
                .appendQueryParameter(GITHUB_SEARCH_QUERY_PARAM, query)
                .appendQueryParameter(GITHUB_SEARCH_SORT_PARAM, GITHUB_SEARCH_SORT_VALUE)
                .build();

        URL url = null;
        try {
            url = new URL(githubSearchUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
}
