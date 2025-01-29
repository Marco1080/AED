package org.example;

import java.util.List;

public class Pelicula {
    private String title;
    private String writer;
    private int year;
    private List<String> actors;
    private String franchise;
    private String synopsis;

    public Pelicula(String title, String writer, int year, List<String> actors, String franchise, String synopsis) {
        this.title = title;
        this.writer = writer;
        this.year = year;
        this.actors = actors;
        this.franchise = franchise;
        this.synopsis = synopsis;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public String getFranchise() {
        return franchise;
    }

    public void setFranchise(String franchise) {
        this.franchise = franchise;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "title='" + title + '\'' +
                ", writer='" + writer + '\'' +
                ", year=" + year +
                ", actors=" + actors +
                ", franchise='" + franchise + '\'' +
                ", synopsis='" + synopsis + '\'' +
                '}';
    }
}
