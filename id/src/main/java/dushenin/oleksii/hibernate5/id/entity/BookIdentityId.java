package dushenin.oleksii.hibernate5.id.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by alexeydushenin
 * on 2/18/16.
 */
@Entity
@Table(name = "book_entity_id")
public class BookIdentityId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 0, max = 256)
    private String name;

    public BookIdentityId() {
    }

    public BookIdentityId(String name) {
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
