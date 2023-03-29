package kz.roman.todoapp.service.serviceImpl;

import kz.roman.todoapp.model.dto.UserDto;
import kz.roman.todoapp.model.entity.UserEntity;
import kz.roman.todoapp.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        user.setLastAccessDate(LocalDateTime.now());
        return UserDto.build(user);
    }
}
