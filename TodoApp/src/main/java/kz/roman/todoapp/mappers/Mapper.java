package kz.roman.todoapp.mappers;

import kz.roman.todoapp.model.dto.NoteDto;
import kz.roman.todoapp.model.dto.TodoDto;
import kz.roman.todoapp.model.dto.UserDto;
import kz.roman.todoapp.model.entity.NoteEntity;
import kz.roman.todoapp.model.entity.TodoEntity;
import kz.roman.todoapp.model.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class Mapper {

    public TodoDto toTodoDto(TodoEntity entity) {
        TodoDto dto = new TodoDto();
        dto.setId(entity.getId());
        dto.setData(entity.getData());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setIsDone(entity.getIsDone());
        return dto;
    }

    public NoteDto toNoteDto(NoteEntity entity) {
        NoteDto dto = new NoteDto();
        dto.setId(entity.getId());
        dto.setHeader(entity.getHeader());
        dto.setData(entity.getData());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }

    public UserDto toUserDto(UserEntity entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setLastAccessDate(entity.getLastAccessDate());
        return dto;
    }
}
