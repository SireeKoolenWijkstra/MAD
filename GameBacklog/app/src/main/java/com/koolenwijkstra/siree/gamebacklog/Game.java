package com.koolenwijkstra.siree.gamebacklog;

import java.util.Date;

public class Game {
    String naam;
    String platform;
    String notes;
    int status;
    public Date date;

    public Game() {
        this.date = new Date();
    }

    public Game(String naam, String platform, String notes, int status) {
        this.naam = naam;
        this.platform = platform;
        this.notes = notes;
        this.status = status;
        this.date = new Date();
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
