package movist.springlab.controllers;

import movist.springlab.dao.EnrolleeListDao;
import movist.springlab.models.Enrollee;
import movist.springlab.models.Exam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EnrolleeController {

    EnrolleeListDao enrolleeDao = new EnrolleeListDao();

    @GetMapping("/enrollees")
    public String enrollees(Model model) {
        model.addAttribute("title", "Список абитуриентов");
        List<Enrollee> enrollees = enrolleeDao.getAll();//получение списка абитуриентов
        model.addAttribute("enrollees", enrollees);
        return "enrollees";
    }

    @GetMapping("/enrollees/{id}")
    public String enrolee(@PathVariable int id, Model model) {

        Enrollee enrollee = enrolleeDao.getOne(id);
        List<Exam> exams = enrolleeDao.getExamsById(id);
        enrollee.setExams(exams);
        model.addAttribute("enrollee", enrollee);
        model.addAttribute("exams", exams);

        return "enrollee";
    }

    @GetMapping("/add")
    public String enrolleeForm(Model model) {
        model.addAttribute("title", "Добавление абитуриента");
        Enrollee enrollee = new Enrollee();

        model.addAttribute("enrollee", enrollee);
        model.addAttribute("number", enrolleeDao.size());
        return "add";
    }

    @PostMapping("/add")
    public String enrolleeSubmit(@ModelAttribute Enrollee enrollee, Model model) {
        enrolleeDao.save(enrollee);
        List<Enrollee> enrollees = enrolleeDao.getAll();//получение списка абитуриентов
        model.addAttribute("enrollees", enrollees);
        return "redirect:/enrollees";
    }

    @GetMapping("/exam/{id}")
    public String examForm(@PathVariable int id, Model model) {
        model.addAttribute("title", "Добавление экзамена");

        Exam exam = new Exam(id);

        model.addAttribute("id", id);
        model.addAttribute("exam", exam);

        return "exam";
    }

    @PostMapping("/exam/{id}")
    public String examSubmit(@ModelAttribute Exam exam, Model model) {

        Enrollee enrollee = enrolleeDao.getOne(exam.getId());
        enrolleeDao.addExam(exam);
        List<Exam> exams = enrollee.getExams(exam.getId());
        exams.add(exam);
        enrollee.setExams(exams);
        model.addAttribute("enrollee", enrollee);
        model.addAttribute("exams", exams);

        return "redirect:/enrollees";
    }
}
