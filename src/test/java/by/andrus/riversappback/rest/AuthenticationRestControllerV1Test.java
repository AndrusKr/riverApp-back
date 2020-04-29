package by.andrus.riversappback.rest;

import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthenticationRestControllerV1Test extends BaseTestMvc {

    @Test
    public void loginAdminOk() throws Exception {
        this.mockMvc.perform(post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"username\": \"andrus\", \"password\": \"andrus\"}")
        ).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void loginUserOk() throws Exception {
        this.mockMvc.perform(post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"username\": \"ld\", \"password\": \"ld\"}")
        ).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void loginForbidden() throws Exception {
        this.mockMvc.perform(post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"username\": \"nonexistent_user\", \"password\": \"nonexistent_user\"}")
        ).andDo(print()).andExpect(status().isForbidden());
    }

    @Test
    public void loginBadRequest() throws Exception {
        this.mockMvc.perform(post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("complete_nonsense")
        ).andDo(print()).andExpect(status().isBadRequest());
    }
}