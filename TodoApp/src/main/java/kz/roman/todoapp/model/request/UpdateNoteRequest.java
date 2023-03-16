package kz.roman.todoapp.model.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateNoteRequest extends CreateNoteRequest {

    private UUID id;
}
