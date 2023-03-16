package kz.roman.todoapp.service;


import kz.roman.todoapp.model.dto.TodoDto;
import kz.roman.todoapp.model.request.CreateTodoRequest;
import kz.roman.todoapp.model.request.UpdateTodoRequest;

import java.util.List;
import java.util.UUID;

public interface TodoService {

    List<TodoDto> findAll();
    TodoDto create(CreateTodoRequest request);
    TodoDto update(UpdateTodoRequest request);
    TodoDto markAsDone(UUID id);
    TodoDto markAsUndone(UUID id);
    void deleteTodoRecord(UUID id);
}
