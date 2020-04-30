package by.andrus.riversappback.rest.user;

import by.andrus.riversappback.model.RoleName;
import by.andrus.riversappback.rest.BaseTestMvc;
import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RiverRestControllerV1Test extends BaseTestMvc {
    @Test
    public void getAllRiversOk() throws Exception {
        this.mockMvc.perform(
                get("/api/v1/rivers/")
                        .header("Authorization", getTokenWithRole(RoleName.ROLE_USER)))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
