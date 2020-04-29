package by.andrus.riversappback.rest.user;

import by.andrus.riversappback.model.RoleName;
import by.andrus.riversappback.rest.BaseTestMvc;
import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserRestControllerV1Test extends BaseTestMvc {

    @Test
    public void getAllUsersOk() throws Exception {
        this.mockMvc.perform(
                get("/api/v1/users/")
                        .header("Authorization", getTokenWithRole(RoleName.ROLE_USER)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getAllUsersForbidden() throws Exception {
        this.mockMvc.perform(get("/api/v1/users/"))
                .andDo(print()).andExpect(status().isForbidden());
    }

    @Test
    public void getUserByIdOk() throws Exception {
        this.mockMvc.perform(
                get("/api/v1/users/1")
                        .header("Authorization", getTokenWithRole(RoleName.ROLE_USER)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getUserByIdForbidden() throws Exception {
        this.mockMvc.perform(get("/api/v1/users/1"))
                .andDo(print()).andExpect(status().isForbidden());
    }
}