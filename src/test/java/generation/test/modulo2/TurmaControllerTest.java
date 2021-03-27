package generation.test.modulo2;

import generation.test.modulo2.persistence.entity.Turma;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

@SpringBootTest
@AutoConfigureMockMvc

class TurmaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectmapper;

    private static final String apiRootPath = "http://localhost:8080";

    @Test
    void postTurma() throws Exception {
        Turma turma = new Turma();
        turma.setDescricao("Turma de prueba");
        turma.setId(1L);

        String response = mockMvc.perform(post(apiRootPath + "/turma/")
                .content(objectmapper.writeValueAsString(turma))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    void getAllTurma() throws Exception {
        Turma turma = new Turma();
        turma.setDescricao("Turma de prueba");
        turma.setId(1L);

        mockMvc.perform(post(apiRootPath + "/turma/")
                .content(objectmapper.writeValueAsString(turma))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andReturn().getResponse().getContentAsString();

        mockMvc.perform(MockMvcRequestBuilders
                .get("/turma")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", isA(ArrayList.class)));

    }

    @Test
    void getTurmaById() throws Exception {
        Turma turma = new Turma();
        turma.setDescricao("Turma de prueba");
        turma.setId(1L);

        mockMvc.perform(post(apiRootPath + "/turma/")
                .content(objectmapper.writeValueAsString(turma))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andReturn().getResponse().getContentAsString();

        mockMvc.perform(MockMvcRequestBuilders
                .get("/turma")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", isA(ArrayList.class)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", is(1)));

    }

    @Test
    void getTurmaByName() throws Exception {
        Turma turma = new Turma();
        turma.setDescricao("Turma de prueba");
        turma.setId(1L);

        mockMvc.perform(post(apiRootPath + "/turma/")
                .content(objectmapper.writeValueAsString(turma))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andReturn().getResponse().getContentAsString();

        mockMvc.perform(MockMvcRequestBuilders
                .get("/turma?name=prueba")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", isA(ArrayList.class)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", is(1)));

    }

    @Test
    void putTurma() throws Exception {
        Turma turma = new Turma();
        turma.setDescricao("Turma de prueba");
        turma.setId(1L);

        mockMvc.perform(post(apiRootPath + "/turma/")
                .content(objectmapper.writeValueAsString(turma))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andReturn().getResponse().getContentAsString();

        turma.setDescricao("modificado");

        mockMvc.perform(put(apiRootPath + "/turma/")
                .content(objectmapper.writeValueAsString(turma))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andReturn().getResponse().getContentAsString();

        mockMvc.perform(MockMvcRequestBuilders
                .get("/turma/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.descricao", is("modificado")));

    }


    @Test
    void deleteTurma() throws Exception{
        Turma turma = new Turma();
        turma.setDescricao("Turma de prueba");
        turma.setId(1L);

        mockMvc.perform(post(apiRootPath + "/turma/")
                .content(objectmapper.writeValueAsString(turma))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andReturn().getResponse().getContentAsString();

        mockMvc.perform(delete(apiRootPath + "/turma/1")
                .content(objectmapper.writeValueAsString(turma))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andReturn().getResponse().getContentAsString();

        mockMvc.perform(MockMvcRequestBuilders
                .get("/turma/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(404));
    }
}
