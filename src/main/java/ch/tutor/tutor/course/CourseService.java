package ch.tutor.tutor.course;

import java.util.List;

public interface CourseService {

    List<Course> getAll();

    Course getCourse(Long id);

    void createCourse(Course course);

    void deleteCourse(Long id);

}
