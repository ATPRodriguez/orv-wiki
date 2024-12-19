package es.ATPRodriguez.orvWiki.model;

import jakarta.persistence.*;

@Entity
@Table(name = "constellations")
public class Constellation {

    private int id;
    private String modifiers;
    private String rank;
    private String nebula;

    public Constellation() {
    }

    public Constellation(String modifiers) {
        this.modifiers = modifiers;
    }

    public Constellation(String modifiers, String rank, String nebula) {
        this.modifiers = modifiers;
        this.rank = rank;
        this.nebula = nebula;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "modifiers", nullable = false)
    public String getModifiers() {
        return modifiers;
    }
    public void setModifiers(String modifiers) {
        this.modifiers = modifiers;
    }

    @Column(name = "rank", nullable = false)
    public String getRank() {
        return rank;
    }
    public void setRank(String rank) {
        this.rank = rank;
    }

    @Column(name = "nebula", nullable = false)
    public String getNebula() {
        return nebula;
    }
    public void setNebula(String nebula) {
        this.nebula = nebula;
    }

    @Override
    public String toString() {
        return "Constellation{" +
                "id=" + id +
                ", modifiers='" + modifiers + '\'' +
                ", rank='" + rank + '\'' +
                ", nebula='" + nebula + '\'' +
                '}';
    }
}