package ch.tutor.tutor.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/getCourse")
    public ResponseEntity<List<Course>> getCourses() {
        List<Course>courses = courseService.getAll();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping(value = "/getCourse", params = {"id"})
    public ResponseEntity<Course> getCourse(@RequestParam(name = "id") Long id) {
        Course course = courseService.getCourse(id);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

}
