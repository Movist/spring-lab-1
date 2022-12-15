package movist.springlab.models;

import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class Enrollee {
    private List<Exam> exams = List.of();

    private int id;

    @Size(min = 2, max = 30)
    private String fullName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    public Enrollee() {
    }

    public Enrollee(List<Exam> exams, int id, String fullName, LocalDate birthday) {
        this.exams = exams;
        this.id = id;
        this.fullName = fullName;
        this.birthday = birthday;
    }

    public List<Exam> getExams(int ids) {
        return exams.stream().filter(exam -> exam.getId() == ids).collect(Collectors.toList());
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enrollee enrollee = (Enrollee) o;
        return id == enrollee.id && Objects.equals(exams, enrollee.exams) && Objects.equals(fullName, enrollee.fullName) && Objects.equals(birthday, enrollee.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exams, id, fullName, birthday);
    }

    @Override
    public String toString() {
        return "Enrollee{" +
                "exams=" + exams +
                ", id=" + id +
                ", fullName='" + fullName + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
