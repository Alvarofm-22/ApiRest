package api_rest.Rest.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private int status;
    private String error;
    private String codigo;
    private String mensaje;
    private String path;
    private OffsetDateTime timestamp;

    private String excepcion;
    private String trace;

    private List<ErrorValidacionApi> errores;

}
