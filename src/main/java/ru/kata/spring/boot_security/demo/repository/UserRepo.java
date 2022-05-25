package ru.kata.spring.boot_security.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.annotation.PostConstruct;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findUsersByEmail (String email);
    User findUserByEmail (String email);

}
