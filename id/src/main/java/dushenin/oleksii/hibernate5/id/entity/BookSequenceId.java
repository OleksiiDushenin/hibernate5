package dushenin.oleksii.hibernate5.id.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by alexeydushenin
 * on 2/18/16.
 */
@Entity
@Table(name = "book_sequence_id")
public class BookSequenceId {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotNull
    @Size(min = 0, max = 256)
    private String name;

    public BookSequenceId() {
    }

    public BookSequenceId(String name) {
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
