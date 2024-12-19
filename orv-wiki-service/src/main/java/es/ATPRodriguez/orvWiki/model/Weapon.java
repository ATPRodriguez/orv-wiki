package es.ATPRodriguez.orvWiki.model;

import jakarta.persistence.*;

@Entity
@Table(name = "weapons")
public class Weapon {

    private int id;
    private String name;
    private String grade;
    private String lore;
    private String effects;

    public Weapon() {
    }

    public Weapon(String name) {
        this.name = name;
    }

    public Weapon(String name, String surname, String affiliation, String origin) {
        this.name = name;
        this.grade = surname;
        this.lore = affiliation;
        this.effects = origin;
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

    @Column(name = "grade", nullable = false)
    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Column(name = "lore", nullable = false)
    public String getLore() {
        return name;
    }
    public void setLore(String lore) {
        this.lore = lore;
    }

    @Column(name = "effects", nullable = false)
    public String getEffects() { return effects; }
    public void setEffects(String effects) { this.effects = effects; }

    @Override
    public String toString() {
        return "Weapon{" + "id=" + id + ", name='" + name + '\'' + ", grade='" + grade + '\'' + ", lore='" + lore + '\'' +
                ", effects='" + effects + '\'' + '}';
    }
}