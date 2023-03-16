package kz.roman.todoapp.model.request;

import lombok.Data;

@Data
public class CreateNoteRequest {
    private String header;
    private String data;
}
