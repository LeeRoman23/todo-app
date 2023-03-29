package kz.roman.todoapp.service.serviceImpl;

import kz.roman.todoapp.mappers.Mapper;
import kz.roman.todoapp.model.dto.UserDto;
import kz.roman.todoapp.model.entity.UserEntity;
import kz.roman.todoapp.repos.UserRepository;
import kz.roman.todoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Mapper mapper;

    @Override
    @Transactional(readOnly = true)
    public UserDto findByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User was not found by email: " + email));
        return mapper.toUserDto(userEntity);
    }

    @Override
    public UserDto getCurrentUser() {
        return null;
    }
}
