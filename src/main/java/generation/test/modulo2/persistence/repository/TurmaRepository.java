package generation.test.modulo2.persistence.repository;

import generation.test.modulo2.persistence.entity.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TurmaRepository extends JpaRepository<Turma,Long> {

    @Query("from Turma t where t.descricao like concat('%', :descricao ,'%')")
    List<Turma> getByDescricrao(@Param("descricao") String descricao);


}
