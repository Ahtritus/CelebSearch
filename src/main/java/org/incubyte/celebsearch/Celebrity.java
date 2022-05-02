package org.incubyte.celebsearch;

public class Celebrity
{
    private int page;
    private Result[] results;
    private int total0Pages;
    private int totalResults;

    public Celebrity() {
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Result[] getResults() {
        return results;
    }

    public void setResults(Result[] results) {
        this.results = results;
    }

    public int getTotal0Pages() {
        return total0Pages;
    }

    public void setTotal0Pages(int total0Pages) {
        this.total0Pages = total0Pages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }
}
