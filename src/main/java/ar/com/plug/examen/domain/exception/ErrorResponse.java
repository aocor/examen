package ar.com.plug.examen.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private String message;
    private String detail;
}
