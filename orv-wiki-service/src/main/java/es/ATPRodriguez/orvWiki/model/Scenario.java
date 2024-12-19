package es.ATPRodriguez.orvWiki.model;

import jakarta.persistence.*;

@Entity
@Table(name = "scenarios")
public class Scenario {

    private int id;
    private int number;
    private String name;
    private String objective;
    private String category;
    private String difficulty;
    private String time;
    private String reward;
    private String penalty;

    public Scenario() {
    }

    public Scenario(int id, int number, String name, String objective, String category, String difficulty, String time, String reward, String penalty) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.objective = objective;
        this.category = category;
        this.difficulty = difficulty;
        this.time = time;
        this.reward = reward;
        this.penalty = penalty;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "number", nullable = false)
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "objective", nullable = false)
    public String getObjective() {
        return objective;
    }
    public void setObjective(String objective) {
        this.objective = objective;
    }

    @Column(name = "category", nullable = false)
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    @Column(name = "difficulty", nullable = false)
    public String getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    @Column(name = "time", nullable = false)
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    @Column(name = "reward", nullable = false)
    public String getReward() {
        return reward;
    }
    public void setReward(String reward) {
        this.reward = reward;
    }

    @Column(name = "penalty", nullable = false)
    public String getPenalty() {
        return penalty;
    }
    public void setPenalty(String penalty) {
        this.penalty = penalty;
    }

    @Override
    public String toString() {
        return "Scenario{" +
                "id=" + id +
                ", number=" + number +
                ", name='" + name + '\'' +
                ", objective='" + objective + '\'' +
                ", category='" + category + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", time='" + time + '\'' +
                ", reward='" + reward + '\'' +
                ", penalty='" + penalty + '\'' +
                '}';
    }
}