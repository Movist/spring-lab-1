package movist.springlab.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

public class Exam {
    private int id;
    private String subject;
    private int score;

    public Exam() {
    }

    public Exam(int id) {
        this.id = id;
    }

    public Exam(int id, String subject, int score) {
        this.id = id;
        this.subject = subject;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exam exam = (Exam) o;
        return id == exam.id && score == exam.score && Objects.equals(subject, exam.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subject, score);
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", score=" + score +
                '}';
    }
}
