package generation.test.modulo2;

import com.fasterxml.jackson.databind.ObjectMapper;
import generation.test.modulo2.persistence.entity.Participante;
import generation.test.modulo2.persistence.entity.Turma;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ParticipanteControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectmapper;

    private static final String apiRootPath = "http://localhost:8080";

    @Test
    void postParticipante() throws Exception {

        Participante participante = new Participante();
        participante.setNome("participante de prueba");
        participante.setId(1L);

        String response = mockMvc.perform(post(apiRootPath + "/participante/")
                .content(objectmapper.writeValueAsString(participante))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    void getAllParticipantes() throws Exception {
        Participante participante = new Participante();
        participante.setNome("participante de prueba");
        participante.setId(1L);

        mockMvc.perform(post(apiRootPath + "/participante/")
                .content(objectmapper.writeValueAsString(participante))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andReturn().getResponse().getContentAsString();

        mockMvc.perform(MockMvcRequestBuilders
                .get("/participante")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", isA(ArrayList.class)));

    }

    @Test
    void getParticipanteById() throws Exception {
        Participante participante = new Participante();
        participante.setNome("participante de prueba");
        participante.setId(1L);

        mockMvc.perform(post(apiRootPath + "/participante/")
                .content(objectmapper.writeValueAsString(participante))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andReturn().getResponse().getContentAsString();

        mockMvc.perform(MockMvcRequestBuilders
                .get("/participante")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", isA(ArrayList.class)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", is(1)));

    }

    @Test
    void getParticipanteByName() throws Exception {
        Participante participante = new Participante();
        participante.setNome("participante de prueba");
        participante.setId(1L);

        mockMvc.perform(post(apiRootPath + "/participante/")
                .content(objectmapper.writeValueAsString(participante))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andReturn().getResponse().getContentAsString();

        mockMvc.perform(MockMvcRequestBuilders
                .get("/participante?name=prueba")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", isA(ArrayList.class)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", is(1)));

    }

    @Test
    void putParticipante() throws Exception {
        Participante participante = new Participante();
        participante.setNome("participante de prueba");
        participante.setId(1L);

        mockMvc.perform(post(apiRootPath + "/participante/")
                .content(objectmapper.writeValueAsString(participante))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andReturn().getResponse().getContentAsString();

        participante.setNome("modificado");

        mockMvc.perform(put(apiRootPath + "/participante/")
                .content(objectmapper.writeValueAsString(participante))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andReturn().getResponse().getContentAsString();

        mockMvc.perform(MockMvcRequestBuilders
                .get("/participante/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome", is("modificado")));

    }


    @Test
    void deleteParticipante() throws Exception{
        Participante participante = new Participante();
        participante.setNome("participante de prueba");
        participante.setId(1L);

        mockMvc.perform(post(apiRootPath + "/participante/")
                .content(objectmapper.writeValueAsString(participante))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andReturn().getResponse().getContentAsString();

        mockMvc.perform(delete(apiRootPath + "/participante/1")
                .content(objectmapper.writeValueAsString(participante))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andReturn().getResponse().getContentAsString();

        mockMvc.perform(MockMvcRequestBuilders
                .get("/participante/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(404));
    }

}
