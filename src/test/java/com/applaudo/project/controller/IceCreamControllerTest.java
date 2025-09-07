package com.applaudo.project.controller;

import com.applaudo.project.model.APIError;
import com.applaudo.project.model.CombineIceCreamRequest;
import com.applaudo.project.model.IceCreamDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test to validate your solution
 * <p>
 * Please, don't modify if you want to create some, use other class
 */
@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IceCreamControllerTest {

    private static final IceCreamDto vanillaIceCream = IceCreamDto.builder().id(1L).name("Vanilla").cost(new BigDecimal("2.00")).build();
    private static final IceCreamDto chocolateIceCream = IceCreamDto.builder().id(2L).name("Chocolate").cost(new BigDecimal("5.00")).build();
    private static final IceCreamDto strawberryIceCream = IceCreamDto.builder().id(3L).name("Strawberry").cost(new BigDecimal("3.00")).build();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Nested
    class ConstraintTest {

        @Test
        void emptyIceCreamListTest() throws Exception {
            CombineIceCreamRequest combineIceCreamRequest = CombineIceCreamRequest.builder()
                    .iceCreams(List.of())
                    .percentage(60D)
                    .build();
            mockMvc.perform(MockMvcRequestBuilders.post("/api/ice-cream/combine")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(combineIceCreamRequest)))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andDo(MockMvcResultHandlers.print());
        }

        @Test
        void negativePercentageTest() throws Exception {
            CombineIceCreamRequest combineIceCreamRequest = CombineIceCreamRequest.builder()
                    .iceCreams(List.of(vanillaIceCream, chocolateIceCream, strawberryIceCream))
                    .percentage(-1D)
                    .build();
            mockMvc.perform(MockMvcRequestBuilders.post("/api/ice-cream/combine")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(combineIceCreamRequest)))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andDo(MockMvcResultHandlers.print());
        }

        @Test
        void zeroPercentageTest() throws Exception {
            CombineIceCreamRequest combineIceCreamRequest = CombineIceCreamRequest.builder()
                    .iceCreams(List.of(vanillaIceCream, chocolateIceCream, strawberryIceCream))
                    .percentage(0D)
                    .build();
            mockMvc.perform(MockMvcRequestBuilders.post("/api/ice-cream/combine")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(combineIceCreamRequest)))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andDo(MockMvcResultHandlers.print());
        }
    }

    @Test
    void iceCreamNotExistTest() throws Exception {
        Long id = -50L;
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/api/ice-cream/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();
        APIError actual = objectMapper.readValue(result, APIError.class);
        assertEquals(HttpStatus.NOT_FOUND.value(), actual.getCode());
        assertEquals("IceCream -50 not found", actual.getDescription());
    }

    @Test
    void getIceCreamByIdTest() throws Exception {
        Long id = 1L;
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/api/ice-cream/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();
        IceCreamDto actual = objectMapper.readValue(result, IceCreamDto.class);
        assertEquals(vanillaIceCream.getId(), actual.getId());
        assertEquals(vanillaIceCream.getName(), actual.getName());
        assertEquals(vanillaIceCream.getCost(), actual.getCost());
    }

    @Nested
    class IceCreamCombinationTest {

        @Test
        void combine1IceCreamTest() throws Exception {
            CombineIceCreamRequest combineIceCreamRequest = CombineIceCreamRequest.builder()
                    .iceCreams(List.of(vanillaIceCream))
                    .percentage(60D)
                    .build();
            String result = mockMvc.perform(MockMvcRequestBuilders.post("/api/ice-cream/combine")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(combineIceCreamRequest)))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn().getResponse().getContentAsString();
            IceCreamDto actual = objectMapper.readValue(result, IceCreamDto.class);
            assertEquals("Vanilla Ice Cream", actual.getName());
            assertEquals(new BigDecimal("2.00"), actual.getCost());
        }

        @Test
        void combine2IceCreamTest() throws Exception {
            CombineIceCreamRequest combineIceCreamRequest = CombineIceCreamRequest.builder()
                    .iceCreams(List.of(vanillaIceCream, chocolateIceCream))
                    .percentage(60D)
                    .build();
            String result = mockMvc.perform(MockMvcRequestBuilders.post("/api/ice-cream/combine")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(combineIceCreamRequest)))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn().getResponse().getContentAsString();
            IceCreamDto actual = objectMapper.readValue(result, IceCreamDto.class);
            assertEquals("Chocolate and Vanilla Ice Cream", actual.getName());
            assertEquals(new BigDecimal("6.20"), actual.getCost());
        }

        @Test
        void combine3IceCreamTest() throws Exception {
            CombineIceCreamRequest combineIceCreamRequest = CombineIceCreamRequest.builder()
                    .iceCreams(List.of(vanillaIceCream, chocolateIceCream, strawberryIceCream))
                    .percentage(60D)
                    .build();
            String result = mockMvc.perform(MockMvcRequestBuilders.post("/api/ice-cream/combine")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(combineIceCreamRequest)))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn().getResponse().getContentAsString();
            IceCreamDto actual = objectMapper.readValue(result, IceCreamDto.class);
            assertEquals("Chocolate, Strawberry and Vanilla Ice Cream", actual.getName());
            assertEquals(new BigDecimal("8.00"), actual.getCost());
        }

    }

    @Nested
    class ExtraTest {

        @Test
        void getAllIceCreamTest() throws Exception {
            String result = mockMvc.perform(MockMvcRequestBuilders.get("/api/ice-cream")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn().getResponse().getContentAsString();
            List<IceCreamDto> actual = objectMapper.readValue(result, new TypeReference<>() {
            });
            assertNotNull(actual);
            assertFalse(actual.isEmpty());
            assertEquals(5, actual.size());
        }

        @Test
        void getAllIceCreamFilterByNameTest() throws Exception {
            String result = mockMvc.perform(MockMvcRequestBuilders.get("/api/ice-cream")
                            .param("searchName", "choco")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn().getResponse().getContentAsString();
            List<IceCreamDto> actual = objectMapper.readValue(result, new TypeReference<>() {
            });
            assertNotNull(actual);
            assertFalse(actual.isEmpty());
            assertEquals(2, actual.size());
        }

        @Test
        @Disabled 
        void createIceCreamTest() throws Exception {
            String name = "Test";
            BigDecimal cost = new BigDecimal("15.00");
            IceCreamDto iceCreamDto = IceCreamDto.builder()
                    .name(name)
                    .cost(cost)
                    .build();
            String result = mockMvc.perform(MockMvcRequestBuilders.post("/api/ice-cream")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(iceCreamDto)))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn().getResponse().getContentAsString();
            IceCreamDto actual = objectMapper.readValue(result, IceCreamDto.class);
            assertNotNull(actual.getId());
            assertTrue(actual.getId() > 0);
            assertEquals(name, actual.getName());
            assertEquals(cost, actual.getCost());
        }
    }

}
