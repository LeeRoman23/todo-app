package kz.roman.todoapp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.roman.todoapp.model.dto.NoteDto;
import kz.roman.todoapp.model.request.CreateNoteRequest;
import kz.roman.todoapp.model.request.UpdateNoteRequest;
import kz.roman.todoapp.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/note")
@Api(value = "API для взаимодействия с заметками")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("/create")
    @ApiOperation(value = "Создание новой заметки", notes = "ID должен быть null")
    public NoteDto createNote(@RequestBody CreateNoteRequest request) {
        return noteService.createNote(request);
    }

    @PutMapping("/update")
    @ApiOperation(value = "Редактирование заметки", notes = "ID не должен быть null")
    public NoteDto updateNote(@RequestBody UpdateNoteRequest request) {
        return noteService.updateNote(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Удаление заметки")
    public void deleteNote(@PathVariable UUID id) {
        noteService.deleteNote(id);
    }

    @GetMapping("/all")
    @ApiOperation(value = "Получение списка заметок")
    public List<NoteDto> findAllNotes() {
        return noteService.findAllNotes();
    }
}
