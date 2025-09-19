package api_rest.Rest.config;

import api_rest.Rest.DTOs.ErrorResponse;
import api_rest.Rest.DTOs.ErrorValidacionApi;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @Value("${app.show-stacktrace:false}")
    private boolean showStacktrace;


    // Ayudantes

    private ResponseEntity<ErrorResponse> buildResponse(Exception ex,
                                                        HttpStatus status,
                                                        String codigo,
                                                        String mensaje,
                                                        String path){
        ErrorResponse body = baseBuilder(status, codigo, mensaje, path, ex).build();
        return ResponseEntity.status(status).body(body);
    }

    private ErrorResponse.ErrorResponseBuilder baseBuilder(HttpStatus status,
                                                           String codigo,
                                                           String mensaje,
                                                           String path,
                                                           Exception ex){

        if (status.is5xxServerError()) {
            log.error(mensaje, ex);
        }
        else {
            log.warn(mensaje, ex);
        }

        ErrorResponse.ErrorResponseBuilder builder = ErrorResponse.builder()
                .status(status.value())
                .error(status.getReasonPhrase())
                .codigo(codigo)
                .mensaje(mensaje)
                .path(path)
                .timestamp(OffsetDateTime.now());

        if (showStacktrace){
            builder.excepcion(ex.getClass().getName());

            if (ex.getStackTrace().length > 0){
                builder.trace(ex.getStackTrace()[0].toString());
            }

        }

        return builder;
    }

    // ===== 404: recurso no encontrado =====
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex,
                                                        HttpServletRequest request) {
        return buildResponse(ex, HttpStatus.NOT_FOUND, "RESOURCE_NOT_FOUND",
                ex.getMessage(), request.getRequestURI());
    }

    // ERRORES DE VALIDACION
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex,
                                                          HttpServletRequest request){

        List<ErrorValidacionApi> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> new ErrorValidacionApi(err.getField(), err.getRejectedValue(), err.getDefaultMessage()))
                .collect(Collectors.toList());

        ErrorResponse body = baseBuilder(HttpStatus.BAD_REQUEST, "VALIDATION_FAILED",
                "La solicitud contiene campos invalidos", request.getRequestURI(), ex)
                .errores(fieldErrors)
                .build();

        return ResponseEntity.badRequest().body(body);
    }

    // JSON MALFORMADOS
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleMalformedJson(HttpMessageNotReadableException ex,
                                                             HttpServletRequest request){

        return buildResponse(ex, HttpStatus.BAD_REQUEST, "MALFORMED_JSON",
                "El cuerpo de la solicitud esta mal formado o es invalido",
                request.getRequestURI());
    }


    // ERRORES DE SERVICIOS EXTERNOS
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ErrorResponse> handleHttpClient(HttpClientErrorException ex,
                                                          HttpServletRequest request){

        return buildResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, "DOWNSTREAM_ERROR",
                "Error del servicio externo:" + ex.getStatusText(),
                request.getRequestURI());

    }


    // ===== Fallback: 500 genérico =====
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAll(Exception ex,
                                                   HttpServletRequest request) {
        return buildResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR",
                "Ocurrió un error inesperado. Contacte al administrador si persiste.",
                request.getRequestURI());
    }

    
}
