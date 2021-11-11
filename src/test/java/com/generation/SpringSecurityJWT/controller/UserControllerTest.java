package com.generation.SpringSecurityJWT.controller;

import com.generation.SpringSecurityJWT.model.User;
import com.generation.SpringSecurityJWT.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Test
    @DisplayName("Pruebas UserController")
    void saveTest(){
        //veriricar que el userService haya sido llamado el metodo save
        UserService userService = mock(UserService.class);
        BCryptPasswordEncoder bCryptPasswordEncoder = mock(BCryptPasswordEncoder.class);

        UserController userController = new UserController(userService, bCryptPasswordEncoder);

        User user = new User();
        user.setSurname("juan");
        user.setPassword("123");
        user.setUsername("ggg@ggg.acom");
        user.setAddress("Casita");
        user.setName("Juan");

        when(userService.save(any(User.class))).thenReturn(user);
        User resultado = userController.saveUser(user);

        verify(userService, times(1)).save(any(User.class));
        verify(bCryptPasswordEncoder, atLeastOnce()).encode("123");

        assertEquals(user.getUsername(), resultado.getUsername());
    };

    @Test
    @DisplayName("Pruebas en GetUser")
    void getUserTest() {
        //verificar que se haya llamado el servicio
        //verificar que el servicio nos devuelva un objeto user
        UserService userService = mock(UserService.class);
        BCryptPasswordEncoder bCryptPasswordEncoder = mock(BCryptPasswordEncoder.class);

        UserController userController = new UserController(userService, bCryptPasswordEncoder);

        User user = new User();
        user.setSurname("juan");
        user.setPassword("123");
        user.setUsername("");
        user.setAddress("Casita");
        user.setName("Juan");

        when(userService.getUser(user.getId())).thenReturn(user);
        User resultado2 = userController.getUser(user.getId());

        verify(userService, times(1)).getUser(user.getId()); //verificar que fue llamado
        assertEquals(user.getName(), resultado2.getName()); //verificar que el objeto devuelto sea el mismo
    }

}