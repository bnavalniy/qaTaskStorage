package fs.mvc;

import fs.FileStorageApplication;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@ContextConfiguration(classes = {FileStorageApplication.class})
@DisplayName("Security")
class SecurityTests {
    private static final String GET_ALL_FILES_URL = "/api/v1/fileStorage/all";

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Test request secured resource without authorization")
    void testGetSecuredResourceWithoutAuthoriation() throws Exception {
        this.mvc.perform(get(GET_ALL_FILES_URL)).andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("Test request unsecured resource without authorization")
    void testUnsecuredResourceWithoutAuthorization() throws Exception {
        this.mvc.perform(get("/login")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    @DisplayName("Test request secured profile page with mock user")
    void testProfile() throws Exception {
        this.mvc.perform(get("/login")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    @DisplayName("Request logout page with mock user")
    void testLogout() throws Exception {
        mvc.perform(get("/logout")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    @DisplayName("Request upload page with mock user")
    void testUpload() throws Exception {
        mvc.perform(get("/upload")).andExpect(status().isOk());
    }
}
