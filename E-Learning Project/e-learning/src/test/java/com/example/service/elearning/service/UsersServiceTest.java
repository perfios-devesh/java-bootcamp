package com.example.service.elearning.service;

import com.example.service.elearning.domain.UserNameDetails;
import com.example.service.elearning.domain.Users;
import com.example.service.elearning.dto.AddUserResponse;
import com.example.service.elearning.dto.UserDTO;
import com.example.service.elearning.repository.CourseRepository;
import com.example.service.elearning.repository.UserCourseDetailsRepository;
import com.example.service.elearning.repository.UserNameDetailsRepository;
import com.example.service.elearning.repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsersServiceTest {

    @Mock
    UsersRepository usersRepository;
    @Mock
    CourseRepository courseRepository;
    @Mock
    UserCourseDetailsRepository userCourseDetailsRepository;
    @Mock
    UserNameDetailsRepository userNameDetailsRepository;

    @InjectMocks
    UsersService usersService;

    @Test
    void addUserSuccessfully(){
        UserDTO userDTO = new UserDTO();

        userDTO.setFirstName("Devesh");
        userDTO.setLastName("Bhartiya");
        userDTO.setEmail("devesh.bhartiya0799@gmail.com");
        userDTO.setMobileNumber("70725698655");
        userDTO.setUsername("Devesh");
        userDTO.setPassword("Organic@99");

        Users users = new Users();
        users.setUserId(1l);

        UserNameDetails userNameDetails = new UserNameDetails();
        userNameDetails.setUserDetailsId(1l);
        userNameDetails.setUsername("Devesh");

        when(usersRepository.saveAndFlush(any())).thenReturn(users);
        when(userNameDetailsRepository.saveAndFlush(any())).thenReturn(userNameDetails);

        AddUserResponse addUserResponse = usersService.addUser(userDTO);
        assertNotNull(addUserResponse);
        assertTrue(addUserResponse.getApiStatusSuccessful());
        assertEquals(1l, addUserResponse.getUserId());

    }
}
