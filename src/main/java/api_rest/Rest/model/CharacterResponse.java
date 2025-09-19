package api_rest.Rest.model;

import lombok.Data;

import java.util.List;

@Data
public class CharacterResponse {

    private Info info;
    private List<Character> results;

}
