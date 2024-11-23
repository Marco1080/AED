/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.futbolhibernate;

import java.util.Date;

/**
 *
 * @author tonyi
 */
public class Match {

    int id;
    String division;
    Date match_date;
    String HomeTeam;
    String AwayTeam;
    double FTHG;
    double FTAG;
    String FTR;
    int season;

    public Match(int id, String division, Date match_date, String HomeTeam, String AwayTeam, double FTHG, double FTAG, String FTR, int season) {
        this.id = id;
        this.division = division;
        this.match_date = match_date;
        this.HomeTeam = HomeTeam;
        this.AwayTeam = AwayTeam;
        this.FTHG = FTHG;
        this.FTAG = FTAG;
        this.FTR = FTR;
        this.season = season;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public Date getMatch_date() {
        return match_date;
    }

    public void setMatch_date(Date match_date) {
        this.match_date = match_date;
    }

    public String getHomeTeam() {
        return HomeTeam;
    }

    public void setHomeTeam(String HomeTeam) {
        this.HomeTeam = HomeTeam;
    }

    public String getAwayTeam() {
        return AwayTeam;
    }

    public void setAwayTeam(String AwayTeam) {
        this.AwayTeam = AwayTeam;
    }

    public double getFTHG() {
        return FTHG;
    }

    public void setFTHG(double FTHG) {
        this.FTHG = FTHG;
    }

    public double getFTAG() {
        return FTAG;
    }

    public void setFTAG(double FTAG) {
        this.FTAG = FTAG;
    }

    public String getFTR() {
        return FTR;
    }

    public void setFTR(String FTR) {
        this.FTR = FTR;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

}
