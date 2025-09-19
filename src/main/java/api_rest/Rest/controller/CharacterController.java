package api_rest.Rest.controller;

import api_rest.Rest.model.Character;
import api_rest.Rest.model.CharacterResponse;
import api_rest.Rest.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/characters")
public class CharacterController {

    private final CharacterService characterService;

    @GetMapping("/{id}")
    public Character getCharacter(@PathVariable int id){
        return characterService.getCharacterById(id);
    }


    @GetMapping
    public ResponseEntity<List<CharacterResponse>> getCharacters(@RequestParam(defaultValue = "1") int page){

        List<CharacterResponse> characterResponse = characterService.getListCharacter(page);

        return ResponseEntity.ok(characterResponse);
    }


}
