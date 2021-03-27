package generation.test.modulo2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "participante nao encontrada")

public class ParticipanteNotFound extends RuntimeException{

}
