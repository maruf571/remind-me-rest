package me.remind.rest.sandbox.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.remind.rest.sandbox.model.User;
import me.remind.rest.sandbox.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @Transactional
    public void should_create_user() throws Exception {

        // Given
        User user = new User();
        user.setGitHubProfileUrl("maruf571");
        user.setSurname("Maruf");
        user.setFirstName("Mahmudul");
        user.setPosition("SE");

        // When and Then
        this.mockMvc.perform(post(UserController.URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getObjectAsString(user))
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.firstName").value ("Mahmudul"))
                .andExpect(jsonPath("$.surname").value ("Maruf"))
                .andExpect(jsonPath("$.position").value ("SE"))
                .andExpect(jsonPath("$.gitHubProfileUrl").value ("maruf571"))
        ;

    }

    @Test
    @Transactional
    public void should_update_user() throws Exception {

        // Given
        User user = new User();
        user.setGitHubProfileUrl("maruf571");
        user.setSurname("Maruf");
        user.setFirstName("Mahmudul");
        user.setPosition("SE");

        User newUser =  userService.create(user);
        newUser.setPosition("SSE");
        newUser.setSurname("Hassan");

        // When and Then
        this.mockMvc.perform(put(UserController.URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getObjectAsString(newUser))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.id").value(newUser.getId().toString()))
                .andExpect(jsonPath("$.firstName").value("Mahmudul"))
                .andExpect(jsonPath("$.surname").value("Hassan"))
                .andExpect(jsonPath("$.position").value("SSE"))
                .andExpect(jsonPath("$.gitHubProfileUrl").value ("maruf571"))
        ;

    }

    @Test
    @Transactional
    public void should_find_all() throws Exception {

        // Given
        User user = new User();
        user.setGitHubProfileUrl("maruf571");
        user.setSurname("Maruf");
        user.setFirstName("Mahmudul");
        user.setPosition("SE");
        User newUser =  userService.create(user);

        User user1 = new User();
        user1.setGitHubProfileUrl("maruf5711");
        user1.setSurname("Maruf1");
        user1.setFirstName("Mahmudul1");
        user1.setPosition("SE1");
        User newUser1 =  userService.create(user1);

        // When and Then
        this.mockMvc.perform(get(UserController.URL)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isNotEmpty())
                .andExpect(jsonPath("$.content.length()").value(2))


                .andExpect(jsonPath("$.content[0].id").value (newUser.getId().toString()))
                .andExpect(jsonPath("$.content[0].firstName").value ("Mahmudul"))
                .andExpect(jsonPath("$.content[0].surname").value ("Maruf"))
                .andExpect(jsonPath("$.content[0].position").value ("SE"))
                .andExpect(jsonPath("$.content[0].gitHubProfileUrl").value ("maruf571"))

                .andExpect(jsonPath("$.content[1].id").value (newUser1.getId().toString()))
                .andExpect(jsonPath("$.content[1].firstName").value ("Mahmudul1"))
                .andExpect(jsonPath("$.content[1].surname").value ("Maruf1"))
                .andExpect(jsonPath("$.content[1].position").value ("SE1"))
                .andExpect(jsonPath("$.content[1].gitHubProfileUrl").value ("maruf5711"))
        ;

    }

    @Test
    @Transactional
    public void should_find_by_id() throws Exception {

        // Given
        User user = new User();
        user.setGitHubProfileUrl("maruf571");
        user.setSurname("Maruf");
        user.setFirstName("Mahmudul");
        user.setPosition("SE");

        User newUser =  userService.create(user);

        // When and Then
        this.mockMvc.perform(get(UserController.URL +"/"+ user.getId().toString())
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.id").value (newUser.getId().toString()))
                .andExpect(jsonPath("$.firstName").value ("Mahmudul"))
                .andExpect(jsonPath("$.surname").value ("Maruf"))
                .andExpect(jsonPath("$.position").value ("SE"))
                .andExpect(jsonPath("$.gitHubProfileUrl").value ("maruf571"))
        ;

    }

    @Test
    @Transactional
    public void should_delete_by_id() throws Exception {

        // Given
        User user = new User();
        user.setGitHubProfileUrl("maruf571");
        user.setSurname("Maruf");
        user.setFirstName("Mahmudul");
        user.setPosition("SE");

        User newUser =  userService.create(user);

        // When and Then
        this.mockMvc.perform(delete(UserController.URL + "/" + newUser.getId())
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
        ;
    }

    @Test
    @Transactional
    public void should_throw_bad_req_exception() throws Exception {

        // Given
        User user = new User();
        user.setGitHubProfileUrl("maruf571");
        user.setSurname("Maruf");
        user.setFirstName("Mahmudul");
        user.setPosition("SE");

        User newUser =  userService.create(user);

        // When and Then
        this.mockMvc.perform(post(UserController.URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getObjectAsString(user))
        )
                .andExpect(status().isBadRequest())
        ;
    }

    @Test
    @Transactional
    public void should_throw_not_found_exception() throws Exception {

        // Given
        UUID id = UUID.randomUUID();

        // When and Then
        this.mockMvc.perform(get(UserController.URL + "/" + id.toString())
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isNotFound())
        ;
    }

    @Test
    @Transactional
    public void should_find_response_from_github() throws Exception {

        // Given
        User user = new User();
        user.setGitHubProfileUrl("maruf571");
        user.setSurname("Maruf");
        user.setFirstName("Mahmudul");
        user.setPosition("SE");

        User newUser =  userService.create(user);

        // When and Then
        this.mockMvc.perform(get(UserController.URL +"/"+ user.getId().toString() + "/repositories")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
        ;
    }

    private String getObjectAsString(Object o) throws JsonProcessingException {
        return objectMapper.writeValueAsString(o);
    }
}
