package com.mycompany.futbolhibernate;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "matchs")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "division_id", nullable = false)
    private Division division;

    @Temporal(TemporalType.DATE)
    private Date matchDate;

    private String homeTeam;

    private String awayTeam;

    private double fthg;

    private double ftag;

    private double ftr;

    private int season;

    public Match(Division division, Date matchDate, String homeTeam, String awayTeam, double fthg, double ftag, Double ftr, int season) {
        this.division = division;
        this.matchDate = matchDate;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.fthg = fthg;
        this.ftag = ftag;
        this.ftr = ftr;
        this.season = season;
    }

    public Match() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public double getFthg() {
        return fthg;
    }

    public void setFthg(double fthg) {
        this.fthg = fthg;
    }

    public double getFtag() {
        return ftag;
    }

    public void setFtag(double ftag) {
        this.ftag = ftag;
    }

    public double getFtr() {
        return ftr;
    }

    public void setFtr(double ftr) {
        this.ftr = ftr;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }
    /*
    @Override
    public String toString() {
        return "Match{id=" + id + ", division=" + division.getDivision()
                + ", matchDate=" + matchDate + ", homeTeam='" + homeTeam + '\''
                + ", awayTeam='" + awayTeam + '\'' + ", fthg=" + fthg
                + ", ftag=" + ftag + ", ftr='" + ftr + '\''
                + ", season=" + season + '}';
    }*/
}
