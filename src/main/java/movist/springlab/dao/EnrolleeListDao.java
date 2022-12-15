package movist.springlab.dao;

import movist.springlab.models.Enrollee;
import movist.springlab.models.Exam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EnrolleeListDao implements Dao<Enrollee> {
    private List<Enrollee> enrollees;
    private List<Exam> examList;
    private static int count;

    {
        enrollees = new ArrayList<>();
        examList = new ArrayList<>();

        examList.add(new Exam(0, "Математика", 80));
        examList.add(new Exam(0, "Информатика", 90));
        enrollees.add(new Enrollee(examList,
                0, "Фродо", LocalDate.of(1999, 10, 2)));


        examList.add(new Exam(1, "Русский", 95));
        examList.add(new Exam(1, "Литература", 92));
        enrollees.add(new Enrollee(examList,
                1, "Сэм", LocalDate.of(2000, 2, 15)));


        examList.add(new Exam(2, "Физика", 74));
        examList.add(new Exam(2, "Обществознание", 65));
        enrollees.add(new Enrollee(examList,
                2, "Гендальф", LocalDate.of(1781, 3, 25)));
    }

    public void addExam(Exam exam) {
        this.examList.add(exam);
    }

    public List<Exam> getExamsById(int id) {
        return examList.stream().filter(exam -> exam.getId() == id).collect(Collectors.toList());
    }

    @Override
    public Optional<Enrollee> get(int id) {
        return Optional.of(enrollees.get(id));
    }

    @Override
    public List<Enrollee> getAll() {
        return enrollees;
    }

    public Enrollee getOne(int id) {
        Enrollee enr = new Enrollee();
        for (Enrollee enrollee : enrollees) {
            if (enrollee.getId() == id) {
                enr.setExams(enrollee.getExams(id));
                enr.setId(enrollee.getId());
                enr.setFullName(enrollee.getFullName());
                enr.setBirthday(enrollee.getBirthday());
            }
        }
        return enr;
    }

    @Override
    public void save(Enrollee enrollee) {
        enrollee.setId(enrollees.size());
        enrollees.add(enrollee);
    }

    @Override
    public void delete(Enrollee enrollee) {
        enrollees.remove(enrollee);
    }

    public int size() {
        return enrollees.size();
    }
}
