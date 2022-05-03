package org.incubyte.celebsearch;

import java.util.List;

public class Celebrities {

    private List<SearchResult> celebrityResults;

    public List<SearchResult> getResults() {
        return celebrityResults;
    }

    public void setResults(List<SearchResult> celebrityResults) {
        this.celebrityResults = celebrityResults;
    }
}
