package kz.roman.todoapp.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "notes")
@Getter
@Setter
public class NoteEntity extends BaseEntity{

    @Column(name = "header", length = 100)
    private String header;
    @Column(columnDefinition = "text")
    private String data;

}
