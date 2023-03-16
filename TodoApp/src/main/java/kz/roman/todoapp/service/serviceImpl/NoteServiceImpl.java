package kz.roman.todoapp.service.serviceImpl;

import kz.roman.todoapp.mappers.Mapper;
import kz.roman.todoapp.model.dto.NoteDto;
import kz.roman.todoapp.model.entity.NoteEntity;
import kz.roman.todoapp.model.request.CreateNoteRequest;
import kz.roman.todoapp.model.request.UpdateNoteRequest;
import kz.roman.todoapp.repos.NoteRepository;
import kz.roman.todoapp.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private Mapper mapper;

    @Override
    public NoteDto createNote(CreateNoteRequest request) {
        NoteEntity entity = new NoteEntity();
        entity.setId(UUID.randomUUID());
        entity.setHeader(request.getHeader());
        entity.setData(request.getData());
        entity.setCreatedAt(LocalDateTime.now());
        noteRepository.save(entity);
        return mapper.toNoteDto(entity);
    }

    @Override
    public NoteDto updateNote(UpdateNoteRequest request) {
        NoteEntity entity = noteRepository.findById(request.getId())
                .orElseThrow(() -> new IllegalArgumentException("Note was not found by ID: " + request.getId()));
        entity.setHeader(request.getHeader());
        entity.setData(request.getData());
        noteRepository.save(entity);
        return mapper.toNoteDto(entity);
    }

    @Override
    public void deleteNote(UUID id) {
       noteRepository.findById(id).ifPresent(note -> noteRepository.deleteById(id));
    }

    @Override
    public List<NoteDto> findAllNotes() {
        return noteRepository.findAll().stream().map(noteEntity -> mapper.toNoteDto(noteEntity)).collect(Collectors.toList());
    }
}
