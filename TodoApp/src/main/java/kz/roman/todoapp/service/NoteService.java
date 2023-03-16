package kz.roman.todoapp.service;

import kz.roman.todoapp.model.dto.NoteDto;
import kz.roman.todoapp.model.request.CreateNoteRequest;
import kz.roman.todoapp.model.request.UpdateNoteRequest;
import java.util.List;
import java.util.UUID;

public interface NoteService {

    NoteDto createNote(CreateNoteRequest request);
    NoteDto updateNote(UpdateNoteRequest request);
    void deleteNote(UUID id);
    List<NoteDto> findAllNotes();

}
