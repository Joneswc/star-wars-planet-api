package br.edu.b2w.jones.planet.starwars.starwarsplanetapi.entity;

import java.io.Serializable;
import java.util.List;

public class ListBody implements Serializable {

    private Long count;
    private String next;
    private String previous;
    private List<Results> results;

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

}
