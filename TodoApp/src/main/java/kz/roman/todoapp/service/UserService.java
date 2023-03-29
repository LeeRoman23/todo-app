package kz.roman.todoapp.service;

import kz.roman.todoapp.model.dto.UserDto;

public interface UserService {

    UserDto findByEmail(String email);
    UserDto getCurrentUser();


}
