package api_rest.Rest.service;

import api_rest.Rest.config.ExternalServiceException;
import api_rest.Rest.config.ResourceNotFoundException;
import api_rest.Rest.model.CharacterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import api_rest.Rest.model.Character;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CharacterService {


    private final RestTemplate restTemplate;

    public List<CharacterResponse> getListCharacter(int page){

        String url= String.format("https://rickandmortyapi.com/api/character?page=%d", page);

        try {
            return Collections.singletonList(restTemplate.getForObject(url, CharacterResponse.class));
        }

        catch (HttpClientErrorException.NotFound ex){
            throw new ResourceNotFoundException(
              String.format("No se encontraron personajes en la pagina %d", page)
            );
        }

        catch (RestClientException ex){
            throw  new ExternalServiceException("Error al consultar servicio externo", ex);
        }

    }

    public Character getCharacterById(int id){
        String url = "https://rickandmortyapi.com/api/character/" + id;
        return restTemplate.getForObject(url, Character.class);
    }


}
