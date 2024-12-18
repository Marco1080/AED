package org.example;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "matchs")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_match")
    private int idMatch;

    @ManyToOne
    @JoinColumn(name = "division", nullable = false)
    private Division division;

    @Column(name = "match_date")
    private LocalDate matchDate;

    @Column(name = "HomeTeam", length = 255)
    private String homeTeam;

    @Column(name = "AwayTeam", length = 255)
    private String awayTeam;

    @Column(name = "FTHG")
    private float fullTimeHomeGoals;

    @Column(name = "FTAG")
    private float fullTimeAwayGoals;

    @Column(name = "FTR", length = 255)
    private String fullTimeResult;

    @Column(name = "season")
    private int season;

    public Match() {}

    public Match(Division division, LocalDate matchDate, String homeTeam, String awayTeam,
                 float fullTimeHomeGoals, float fullTimeAwayGoals, String fullTimeResult, int season) {
        this.division = division;
        this.matchDate = matchDate;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.fullTimeHomeGoals = fullTimeHomeGoals;
        this.fullTimeAwayGoals = fullTimeAwayGoals;
        this.fullTimeResult = fullTimeResult;
        this.season = season;
    }

    public String getMatch(){
        return "match";
    }

    public int getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(int idMatch) {
        this.idMatch = idMatch;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public LocalDate getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(LocalDate matchDate) {
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

    public float getFullTimeHomeGoals() {
        return fullTimeHomeGoals;
    }

    public void setFullTimeHomeGoals(float fullTimeHomeGoals) {
        this.fullTimeHomeGoals = fullTimeHomeGoals;
    }

    public float getFullTimeAwayGoals() {
        return fullTimeAwayGoals;
    }

    public void setFullTimeAwayGoals(float fullTimeAwayGoals) {
        this.fullTimeAwayGoals = fullTimeAwayGoals;
    }

    public String getFullTimeResult() {
        return fullTimeResult;
    }

    public void setFullTimeResult(String fullTimeResult) {
        this.fullTimeResult = fullTimeResult;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }
}
