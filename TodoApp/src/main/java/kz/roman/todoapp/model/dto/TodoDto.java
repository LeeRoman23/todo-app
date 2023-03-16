package kz.roman.todoapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoDto {


    private UUID id;
    private String data;
    private Boolean isDone;
    private LocalDateTime createdAt;
}
