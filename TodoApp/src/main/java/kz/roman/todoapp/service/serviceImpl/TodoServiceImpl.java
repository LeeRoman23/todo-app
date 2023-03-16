package kz.roman.todoapp.service.serviceImpl;

import kz.roman.todoapp.mappers.Mapper;
import kz.roman.todoapp.model.dto.TodoDto;
import kz.roman.todoapp.model.entity.TodoEntity;
import kz.roman.todoapp.model.request.CreateTodoRequest;
import kz.roman.todoapp.model.request.UpdateTodoRequest;
import kz.roman.todoapp.repos.TodoRepository;
import kz.roman.todoapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private Mapper mapper;

    @Override
    public List<TodoDto> findAll() {
        return todoRepository.findAll().stream().map(todoEntity -> mapper.toTodoDto(todoEntity)).collect(Collectors.toList());
    }

    @Override
    public TodoDto create(CreateTodoRequest request) {
        TodoEntity entity = new TodoEntity();
        entity.setId(UUID.randomUUID());
        entity.setCreatedAt(LocalDateTime.now());
        entity.setData(request.getData());
        todoRepository.save(entity);
        return mapper.toTodoDto(entity);
    }

    @Override
    public TodoDto update(UpdateTodoRequest request) {
        TodoEntity entity = todoRepository.findById(request.getId())
                .orElseThrow(() -> new IllegalArgumentException("Todo record was not found by ID: " + request.getId()));
        entity.setData(request.getData());
        todoRepository.save(entity);
        return mapper.toTodoDto(entity);
    }

    @Override
    public TodoDto markAsDone(UUID id) {
        TodoEntity entity = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Todo record was not found by ID: " + id));
        entity.setIsDone(true);
        todoRepository.save(entity);
        return mapper.toTodoDto(entity);
    }

    @Override
    public TodoDto markAsUndone(UUID id) {
        TodoEntity entity = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Todo record was not found by ID: " + id));
        entity.setIsDone(false);
        todoRepository.save(entity);
        return mapper.toTodoDto(entity);
    }

    @Override
    public void deleteTodoRecord(UUID id) {
        todoRepository.findById(id).ifPresent(todo -> todoRepository.deleteById(id));
    }
}
