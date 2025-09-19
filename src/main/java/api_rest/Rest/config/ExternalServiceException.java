package api_rest.Rest.config;

/**
 * Lanzar cuando falla una llamada a un servicio externo y quieres encapsularla.
 */

public class ExternalServiceException extends RuntimeException{

    public ExternalServiceException(String mensaje, Throwable causa){
        super(mensaje, causa);
    }
}
