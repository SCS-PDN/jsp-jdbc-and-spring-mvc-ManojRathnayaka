package com.university.registration.controller;
import com.university.registration.dao.CourseDAO;
import com.university.registration.dao.RegistrationDAO;
import com.university.registration.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;
import java.util.List;
@Controller
public class CourseController {
    @Autowired
    private CourseDAO courseDAO;
    @Autowired
    private RegistrationDAO registrationDAO;
    @GetMapping("/courses")
    public String listCourses(Model model, HttpSession session) {
        Integer studentId = (Integer) session.getAttribute("studentId");
        if (studentId == null) {
            return "redirect:/login";
        }
        List<Course> courses = courseDAO.getAllCourses();
        model.addAttribute("courses", courses);
        return "courses";
    }
    @PostMapping("/register/{courseId}")
    public String registerForCourse(@PathVariable("courseId") int courseId,
                                   HttpSession session,
                                   RedirectAttributes redirectAttributes) {
        Integer studentId = (Integer) session.getAttribute("studentId");
        if (studentId == null) {
            return "redirect:/login";
        }
        boolean success = registrationDAO.registerForCourse(studentId, courseId);
        
        if (success) {
            Course course = courseDAO.getCourseById(courseId);
            redirectAttributes.addFlashAttribute("message", 
                "Successfully registered for " + course.getName());
            return "redirect:/success";
        } else {
            redirectAttributes.addFlashAttribute("error", 
                "You are already registered for this course");
            return "redirect:/courses";
        }
    }
    @GetMapping("/success")
    public String showSuccessPage() {
        return "success";
    }
}