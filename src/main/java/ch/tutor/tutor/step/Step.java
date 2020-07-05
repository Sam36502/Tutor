package ch.tutor.tutor.step;

import ch.tutor.tutor.course.Course;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "step")
public @Data class Step implements Comparable<Step>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_step;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String image_path;

    @Column
    private Integer step_num;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @Getter(AccessLevel.NONE)
    private Course course;

    @Override
    public int compareTo(Step o) {
        if (o.getStep_num() != null && this.getStep_num() != null) {
            return this.getStep_num().compareTo(o.getStep_num());
        } else {
            return 0;
        }
    }
}
