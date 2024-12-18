package es.ATPRodriguez.orvWiki.model;

import jakarta.persistence.*;

@Entity
@Table(name = "incarnations")
public class Incarnation {

    private int id;
    private String name;
    private String surname;
    private String affiliation;
    private String origin;

    public Incarnation() {
    }

    public Incarnation(String name) {
        this.name = name;
    }

    public Incarnation(String name, String surname, String affiliation, String origin) {
        this.name = name;
        this.surname = surname;
        this.affiliation = affiliation;
        this.origin = origin;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "surname", nullable = false)
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Column(name = "affiliation", nullable = false)
    public String getAffiliation() {
        return name;
    }
    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    @Column(name = "origin", nullable = false)
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    @Override
    public String toString() {
        return "Character{ id=" + id + ", name='" + name + '\'' + '}';
    }
}