package dushenin.oleksii.hibernate5.id.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by alexeydushenin
 * on 2/20/16.
 */
@Entity
@Table(name = "book_enhanced_sequence_id")
public class BookEnhancedSequenceId {
    @Id
    @GeneratedValue(generator = "enhance_generator")
    @GenericGenerator(
            name = "enhance_generator",
            strategy = "enhanced-sequence",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "sequence_name",
                            value = "hibernate_enhance_sequence"
                    ),
                    @org.hibernate.annotations.Parameter(
                            name = "optimizer",
                            value = "pooled"
                    ),
                    @org.hibernate.annotations.Parameter(
                            name = "initial_value",
                            value = "1"
                    ),
                    @org.hibernate.annotations.Parameter(
                            name = "increment_size",
                            value = "1000"
                    )
            })
    private Long id;
    @NotNull
    @Size(min = 0, max = 256)
    private String name;

    public BookEnhancedSequenceId() {
    }

    public BookEnhancedSequenceId(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
