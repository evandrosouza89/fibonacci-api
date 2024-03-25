package com.github.evandrosouza89.fibonacciapi.controller;

import com.github.evandrosouza89.fibonacciapi.service.FibonacciService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.github.evandrosouza89.fibonacciapi.controller.FibonacciRest.QUERY_PARAM_N;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FibonacciRestTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FibonacciService fibonacciService;

    @Test
    void shouldReturnDefaultMessage() throws Exception {

        // Given

        final String n = "1";

        final String expectedResponse = "1";

        when(fibonacciService.calculate(n))
                .thenReturn(expectedResponse);

        // When

        mockMvc
                .perform(get("/fib").queryParam(QUERY_PARAM_N, n))
                .andDo(print())

                // Then

                .andExpect(status().isOk())
                .andExpect(content().string(containsString(expectedResponse)));

        verify(fibonacciService).calculate(n);

    }

}
