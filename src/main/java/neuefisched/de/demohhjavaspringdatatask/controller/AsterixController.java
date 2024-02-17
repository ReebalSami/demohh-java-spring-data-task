package neuefisched.de.demohhjavaspringdatatask.controller;

import lombok.RequiredArgsConstructor;
import neuefisched.de.demohhjavaspringdatatask.model.dtos.ClientDto;

import neuefisched.de.demohhjavaspringdatatask.model.Character;
import neuefisched.de.demohhjavaspringdatatask.service.AsterixService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/asterix")
@RequiredArgsConstructor
public class AsterixController {

    private final AsterixService service;

    @PostMapping
    public ResponseEntity<Object> saveNewCharacter(@RequestBody Character character) {
        ClientDto savedClientDto = service.saveNewCharacter(character);
        String message = "Character saved successfully.";

        return ResponseEntity.status(201).body(Map.of("message", message, "savedCharacter", savedClientDto));
    }

    @GetMapping
    public List<Character> getAllCharacters() {
        return service.getAllCharacters();
    }

    @GetMapping("/{id}")
    public Character getCharacterById(@PathVariable String id) {
        return service.getCharacterById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCharacterById(@PathVariable String id) {
        return service.deleteCharacterById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCharacterById(@PathVariable String id, @RequestBody Character updatedCharacter) {
        return service.updateCharacterById(id, updatedCharacter);
    }
}