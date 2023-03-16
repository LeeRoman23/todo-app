package kz.roman.todoapp.service.serviceImpl;

import kz.roman.todoapp.model.dto.TodoDto;
import kz.roman.todoapp.model.entity.TodoEntity;
import kz.roman.todoapp.model.request.CreateTodoRequest;
import kz.roman.todoapp.model.request.UpdateTodoRequest;
import kz.roman.todoapp.repos.TodoRepository;
import kz.roman.todoapp.service.TodoService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class TodoServiceImplTest {

    private static final UUID ID = UUID.fromString("a95514d7-ddd4-4bb8-89b1-aa2350fe4922");

    @Autowired
    private TodoService service;
    @MockBean
    private TodoRepository repository;


    @Test
    void when_in_db_two_record_then_must_return_size_2() {
        TodoEntity firstRecord = new TodoEntity();
        TodoEntity secondRecord = new TodoEntity();
        firstRecord.setId(UUID.randomUUID());
        firstRecord.setData("test1");
        secondRecord.setId(UUID.randomUUID());
        secondRecord.setData("test1");
        List<TodoEntity> list = List.of(firstRecord, secondRecord);
        Mockito.when(repository.findAll()).thenReturn(list);
        assertEquals(service.findAll().size(), list.size());
    }

    @Test
    void when_create_new_record_then_record_must_be_saved() {
        CreateTodoRequest request = new CreateTodoRequest();
        request.setData("Тестовая запись для создания");
        TodoEntity entity = new TodoEntity();
        entity.setData(request.getData());
        Mockito.when(repository.save(entity)).thenReturn(entity);
        TodoDto savedRecord = service.create(request);
        assertNotNull(savedRecord.getId());
        assertEquals(request.getData(), savedRecord.getData());
    }

    @Test
    void when_update_existing_record_then_must_has_new_values() {
        UpdateTodoRequest request = new UpdateTodoRequest();
        request.setData("Тестовая запись для редактирования");
        request.setId(ID);
        TodoEntity entity = new TodoEntity();
        entity.setData(request.getData());
        entity.setId(ID);
        Mockito.when(repository.findById(ID)).thenReturn(Optional.of(entity));
        Mockito.when(repository.save(entity)).thenReturn(entity);
        TodoDto savedRecord = service.update(request);
        assertNotNull(savedRecord.getId());
        assertEquals(request.getData(), savedRecord.getData());
    }

    @Test
    void when_call_this_method_then_record_must_be_marked_as_done() {
        TodoEntity entity = new TodoEntity();
        entity.setId(ID);
        Mockito.when(repository.findById(ID)).thenReturn(Optional.of(entity));
        TodoDto savedRecord = service.markAsDone(ID);
        assertTrue(savedRecord.getIsDone());
    }

    @Test
    void when_call_this_method_then_record_must_be_marked_as_undone() {
        TodoEntity entity = new TodoEntity();
        entity.setIsDone(true);
        entity.setId(ID);
        Mockito.when(repository.findById(ID)).thenReturn(Optional.of(entity));
        TodoDto savedRecord = service.markAsUndone(ID);
        assertFalse(savedRecord.getIsDone());
    }
}