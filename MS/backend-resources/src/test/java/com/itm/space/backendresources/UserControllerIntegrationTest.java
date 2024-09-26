package com.itm.space.backendresources;

import com.itm.space.backendresources.api.request.UserRequest;
import com.itm.space.backendresources.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.UUID;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerIntegrationTest extends BaseIntegrationTest {

    @MockBean
    UserService userService;


    @Test
    @WithMockUser(roles = "MODERATOR")
    public void createUserTest() throws Exception {
        UserRequest userRequest = new UserRequest("username", "email@example.com", "password", "firstName", "lastName");
        mvc.perform(requestWithContent(post("/api/users"), userRequest))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "MODERATOR")
    public void getUserByIdTest() throws Exception {
        UUID existingUserId = UUID.fromString("89894cd7-433b-4e21-93bb-6e671e661813");
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/users/{id}", existingUserId);
        mvc.perform(requestToJson(request))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "MODERATOR")
    public void helloTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/users/hello"))
                .andExpect(status().isOk());
    }
}