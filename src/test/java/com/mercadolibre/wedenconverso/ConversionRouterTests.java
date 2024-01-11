package com.mercadolibre.wedenconverso;
import com.mercadolibre.wedenconverso.DTO.ResultDto;
import com.mercadolibre.wedenconverso.Entity.Conversion;
import com.mercadolibre.wedenconverso.router.ConversionRouter;
import com.mercadolibre.wedenconverso.usecases.ConversionUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(ConversionRouter.class)
public class ConversionRouterTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConversionUseCase conversionUseCase;

    @BeforeEach
    public void setUp() {
        // Configurar comportamento simulado do ConversionUseCase aqui
    }

    @Test
    void testSetRate() throws Exception {
        // Simule uma solicitação POST para o endpoint /rate/{currency}/{rate} e verifique a resposta
        mockMvc.perform(MockMvcRequestBuilders.post("/rate/USD/1.0"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testConvert() throws Exception {
        // Simule uma solicitação GET para o endpoint /convert/{amount}/{from}/{to} e verifique a resposta
        mockMvc.perform(MockMvcRequestBuilders.get("/convert/100.0/USD/EUR"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testFindLastFiveConversions() throws Exception {
        // Configure o comportamento simulado para o ConversionUseCase
        List<Conversion> mockConversions = new ArrayList<>(); // Simule as conversões
        when(conversionUseCase.getLastFiveConversionFromDataBase()).thenReturn(mockConversions);

        // Simule uma solicitação GET para o endpoint /find/last e verifique a resposta
        mockMvc.perform(MockMvcRequestBuilders.get("/find/last"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}