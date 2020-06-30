package ch.tutor.tutor.course;

import ch.tutor.tutor.step.Step;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_course;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String author;

    @OneToMany(mappedBy = "course")
    private List<Step> steps;

}
