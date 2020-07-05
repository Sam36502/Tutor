package ch.tutor.tutor.course;

import ch.tutor.tutor.step.Step;
import ch.tutor.tutor.step.StepRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@RestController
public class CourseController {

    private final CourseService courseService;

    @Autowired
    private StepRepository stepRepository;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/getCourse")
    public ResponseEntity<List<Course>> getCourses() {
        List<Course> courses = courseService.getAll();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping(value = "/getCourse", params = {"id"})
    public ResponseEntity<Course> getCourse(@RequestParam(name = "id") Long id) {
        Course course = courseService.getCourse(id);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @PostMapping(value = "/createCourse", consumes = "application/json")
    public ResponseEntity<HttpResponse> createCourse(@RequestBody String courseJSON) {
        ObjectMapper mapper = new ObjectMapper();
        Course course = null;
        try {
            course = mapper.readValue(courseJSON, Course.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        courseService.createCourse(course);
        for (Step s: course.getSteps()) {
            s.setCourse(course);
            stepRepository.save(s);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/deleteCourse", params = {"id"})
    public ResponseEntity<HttpStatus> deleteCourse(@RequestParam(name = "id") Long id) {
        courseService.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
