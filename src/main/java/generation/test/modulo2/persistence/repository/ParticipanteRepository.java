package generation.test.modulo2.persistence.repository;

import generation.test.modulo2.persistence.entity.Participante;
import generation.test.modulo2.persistence.entity.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipanteRepository extends JpaRepository<Participante,Long> {
    @Query("from Participante p where p.nome like concat('%', :nome ,'%')")
    List<Participante> getByNome(@Param("nome") String nome);
}
