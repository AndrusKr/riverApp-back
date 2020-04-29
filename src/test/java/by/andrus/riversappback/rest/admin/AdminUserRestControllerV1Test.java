package by.andrus.riversappback.rest.admin;

import by.andrus.riversappback.model.RoleName;
import by.andrus.riversappback.rest.BaseTestMvc;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdminUserRestControllerV1Test extends BaseTestMvc {

    @Test
    public void getAllUsersOk() throws Exception {
        this.mockMvc.perform(
                get("/api/v1/admin/users/")
                        .header("Authorization", getTokenWithRole(RoleName.ROLE_ADMIN))
        ).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void a1CreateUserCreated() throws Exception {
        ResultActions resultActions = mockMvc.perform(
                post("/api/v1/admin/users/")
                        .header("Authorization", getTokenWithRole(RoleName.ROLE_ADMIN))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\n" +
                                "    \"username\": \"test_user\",\n" +
                                "    \"firstName\": \"test_user\",\n" +
                                "    \"lastName\": \"test_user\",\n" +
                                "    \"email\": \"test_user@email.com\",\n" +
                                "    \"password\": \"test_user\",\n" +
                                "    \"roleNames\": [\n" +
                                "        \"ROLE_USER\",\n" +
                                "        \"ROLE_ADMIN\"\n" +
                                "    ]\n" +
                                "}")
        ).andDo(print()).andExpect(status().isCreated());
        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
    }

    @Test
    public void a2UpdateUserOk() throws Exception {
        ResultActions resultActions = mockMvc.perform(
                put("/api/v1/admin/users/")
                        .header("Authorization", getTokenWithRole(RoleName.ROLE_ADMIN))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\n" +
                                "    \"id\": " + getUserId("test_user") + ",\n" +
                                "    \"firstName\": \"UPDATED\",\n" +
                                "    \"lastName\": \"UPDATED\",\n" +
                                "    \"roleNames\": [\n" +
                                "        \"ROLE_USER\"\n" +
                                "    ]\n" +
                                "}")
        ).andDo(print()).andExpect(status().isOk());
        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
    }

    @Test
    public void a3GetUserByIdOk() throws Exception {
        this.mockMvc.perform(
                get("/api/v1/admin/users/" + getUserId("test_user"))
                        .header("Authorization", getTokenWithRole(RoleName.ROLE_ADMIN))
        ).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void a4DeleteUserByIdNoContent() throws Exception {
        this.mockMvc.perform(
                delete("/api/v1/admin/users/" + getUserId("test_user"))
                        .header("Authorization", getTokenWithRole(RoleName.ROLE_ADMIN))
        ).andDo(print()).andExpect(status().isNoContent());
    }
}
