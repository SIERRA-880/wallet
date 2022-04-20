package com.example.Api.repository;


import com.example.Api.exception.ApiNotFoundException;
import com.example.Api.language.Language;
import com.example.Api.language.LanguageRepository;
import com.example.Api.user.User;
import com.example.Api.user.UserRepository;
import com.example.Api.user.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private LanguageRepository languageRepository;


    private UserService underTest;

    @BeforeEach
    void setupService(){
        underTest = new UserService(userRepository, languageRepository);
    }
    @AfterEach
    void tearDown(){
        userRepository.deleteAll();
    }

    @Test
    void canCreateUser(){
        String userID = "test";
        assertTrue(underTest.canAddUser(userID));
    }

    @Test
    void CannotCreateUser(){
        String userID = "test";
        Optional<User> user = Optional.of(new User(
                userID,
                "natID",
                "1234",
                "lastName",
                "firstName",
                new Language("FR")
        ));

        when(userRepository.findUserByUserID(userID)).thenReturn(user);

        assertFalse(underTest.canAddUser(userID));
    }

    @Test
    void shouldThrowWhenUserNotFoundById(){
        String userID = "idTest";

        assertThatThrownBy(() -> underTest.getOneUser(userID)).isInstanceOf(ApiNotFoundException.class).hasMessageContaining("No user with id : " + userID);
    }


}
