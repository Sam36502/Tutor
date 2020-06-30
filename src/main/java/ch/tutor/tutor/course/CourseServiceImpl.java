package ch.tutor.tutor.course;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Data
@Service
public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAll() {
        List<Course> courses = courseRepository.findAll();

        // Make sure step lists are sorted by 'step_num' and not ID
        for (Course c: courses) {
            Collections.sort(c.getSteps());
        }

        return courses;
    }

    @Override
    public Course getCourse(Long id) {
        Course course = courseRepository.findById(id).orElse(null);

        // Make sure steps list is sorted by 'step_num' and not ID
        if (course != null)
            Collections.sort(course.getSteps());

        return course;
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
