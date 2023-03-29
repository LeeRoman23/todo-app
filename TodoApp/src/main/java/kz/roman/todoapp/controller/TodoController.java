package kz.roman.todoapp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.roman.todoapp.model.dto.TodoDto;
import kz.roman.todoapp.model.request.CreateTodoRequest;
import kz.roman.todoapp.model.request.UpdateTodoRequest;
import kz.roman.todoapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/todo")
@Api(value = "API для взимодействия со списком дел")
public class TodoController {

    @Autowired
    private TodoService todoService;


    @GetMapping("/all")
    @ApiOperation(value = "Получение списка дел")
    public List<TodoDto> getAll() {
        return todoService.findAll();
    }

    @PostMapping("/create")
    @ApiOperation(value = "Создание новой записи", notes = "ID должен быть null")
    public TodoDto createNew(@RequestBody CreateTodoRequest request) {
        return todoService.create(request);
    }

    @PutMapping("/update")
    @ApiOperation(value = "Редактирование существующей записи", notes = "ID не должен быть null")
    public TodoDto update(@RequestBody UpdateTodoRequest request) {
        return todoService.update(request);
    }

    @PatchMapping("/done/{id}")
    @ApiOperation(value = "Отметить как сделанное")
    public TodoDto markAsDone(@PathVariable("id") UUID id) {
        return todoService.markAsDone(id);
    }

    @PatchMapping("/undone/{id}")
    @ApiOperation(value = "Отметить как не сделанное")
    public TodoDto markAsUnDone(@PathVariable("id") UUID id) {
        return todoService.markAsUndone(id);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Удалить запись")
    public void deleteTodoRecord(@PathVariable("id") UUID id) {
        todoService.deleteTodoRecord(id);
    }
}
