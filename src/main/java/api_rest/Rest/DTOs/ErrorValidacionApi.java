package api_rest.Rest.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ErrorValidacionApi {

    private String campo;
    private Object valorRechazado;
    private String mensaje;
}
