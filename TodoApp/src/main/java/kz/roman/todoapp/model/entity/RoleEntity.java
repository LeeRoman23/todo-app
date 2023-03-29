package kz.roman.todoapp.model.entity;


import jakarta.persistence.*;
import kz.roman.todoapp.model.enums.ERole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Setter
@Getter
public class RoleEntity extends BaseEntity {

    @Column
    @Enumerated(EnumType.STRING)
    private ERole code;

    @Column(name = "name")
    private String name;

}
