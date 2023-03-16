package kz.roman.todoapp.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "todo_list")
@Getter
@Setter
public class TodoEntity extends BaseEntity {

    @Column(name = "data")
    private String data;

    @Column(name = "is_done")
    private Boolean isDone = false;
}
