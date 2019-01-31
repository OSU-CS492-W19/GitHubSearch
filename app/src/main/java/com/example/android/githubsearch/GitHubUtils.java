package com.example.android.githubsearch;

import android.net.Uri;

import com.google.gson.Gson;

import java.net.MalformedURLException;
import java.net.URL;

public class GitHubUtils {
    final static String GITHUB_SEARCH_BASE_URL = "https://api.github.com/search/repositories";
    final static String GITHUB_SEARCH_QUERY_PARAM = "q";
    final static String GITHUB_SEARCH_SORT_PARAM = "sort";
    final static String GITHUB_SEARCH_SORT_VALUE = "stars";

    public static class GitHubRepo {
        public String full_name;
        public String description;
        public String html_url;
        public int stargazers_count;
    }

    public static class GitHubSearchResults {
        public GitHubRepo[] items;
    }

    public static String buildGitHubSearchURL(String query) {
        return Uri.parse(GITHUB_SEARCH_BASE_URL).buildUpon()
                .appendQueryParameter(GITHUB_SEARCH_QUERY_PARAM, query)
                .appendQueryParameter(GITHUB_SEARCH_SORT_PARAM, GITHUB_SEARCH_SORT_VALUE)
                .build()
                .toString();
    }

    public static GitHubRepo[] parseGitHubSearchResults(String json) {
        Gson gson = new Gson();
        GitHubSearchResults results = gson.fromJson(json, GitHubSearchResults.class);
        if (results != null && results.items != null) {
            return results.items;
        } else {
            return null;
        }
    }
}
