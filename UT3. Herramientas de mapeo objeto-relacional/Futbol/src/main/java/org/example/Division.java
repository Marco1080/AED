package org.example;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "divisions")

public class Division {

    @Id
    @Column(name = "division", nullable = false, length = 255)
    private String division;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "country", length = 255)
    private String country;

    @OneToMany(mappedBy = "division", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Match> matches;

    public Division() {}

    public Division(String division, String name, String country) {
        this.division = division;
        this.name = name;
        this.country = country;
    }


    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

}
