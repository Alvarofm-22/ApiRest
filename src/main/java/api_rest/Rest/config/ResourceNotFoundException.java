package api_rest.Rest.config;

/**
 * Lanzar cuando un recurso no existe (equivalente a HTTP 404).
 */

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String mensaje){
        super(mensaje);
    }
}
