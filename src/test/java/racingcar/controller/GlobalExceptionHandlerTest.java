package racingcar.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = GlobalExceptionHandler.class)
@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    TestExceptionController testExceptionController;

    @DisplayName("IllegalArgumentException 발생시 BadRequest(400)을 응답한다.")
    @Test
    void illegalArgumentException() throws Exception {
        doThrow(IllegalArgumentException.class).when(testExceptionController).execute();
        mockMvc.perform(get("/test"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @DisplayName("IllegalStateException 발생시 InternalServerError(500)을 응답한다.")
    @Test
    void illegalStateException() throws Exception {
        doThrow(IllegalStateException.class).when(testExceptionController).execute();
        mockMvc.perform(get("/test"))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }
}

@RestController
class TestExceptionController {
    @GetMapping("/test")
    void execute() {
        /* DO NOTHING */
    }
}
