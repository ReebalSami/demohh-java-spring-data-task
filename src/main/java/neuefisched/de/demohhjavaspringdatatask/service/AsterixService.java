package neuefisched.de.demohhjavaspringdatatask.service;

import lombok.RequiredArgsConstructor;
import neuefisched.de.demohhjavaspringdatatask.model.Character;
import neuefisched.de.demohhjavaspringdatatask.model.dtos.ClientDto;
import neuefisched.de.demohhjavaspringdatatask.repos.CharRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AsterixService {

    private final CharRepo charRepo;
    private final IdService idService;

    public List<Character> getAllCharacters() {
        return charRepo.findAll();
    }

    public ClientDto saveNewCharacter(Character character) {
        character.setId(idService.randomId());
        charRepo.save(character);

        return new ClientDto(character.getName(), character.getAge(), character.getProfession());
    }

    public List<ClientDto> saveNewCharacterAndGetClientDtos(Character character) {
        saveNewCharacter(character);
        return getAllClientDtos();
    }

    public Character getCharacterById(String id) {
        return charRepo.findById(id).orElse(null);
    }

    public ResponseEntity<Object> deleteCharacterById(String id) {
        if (charRepo.existsById(id)) {
            charRepo.deleteById(id);
            return ResponseEntity.ok(Map.of("message", "Character deleted successfully."));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Character not found for deletion."));
        }
    }

    public ResponseEntity<Object> updateCharacterById(String id, Character updatedCharacter) {
        Optional<Character> optionalCharacter = charRepo.findById(id);

        if (optionalCharacter.isPresent()) {
            Character existingCharacter = optionalCharacter.get();
            existingCharacter.setName(updatedCharacter.getName());
            existingCharacter.setAge(updatedCharacter.getAge());
            existingCharacter.setProfession(updatedCharacter.getProfession());

            charRepo.save(existingCharacter);

            ClientDto updatedClientDto = new ClientDto(existingCharacter.getName(), existingCharacter.getAge(), existingCharacter.getProfession());
            String message = "Character updated successfully.";

            return ResponseEntity.ok(Map.of("message", message, "updatedCharacter", updatedClientDto));
        } else {
            String message = "Character not found for update.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", message));
        }
    }

    private List<ClientDto> getAllClientDtos() {
        List<Character> temp = charRepo.findAll();
        List<ClientDto> dtoList = new ArrayList<>();

        for (Character c : temp) {
            ClientDto cdto = new ClientDto(c.getName(), c.getAge(), c.getProfession());
            dtoList.add(cdto);
        }

        return dtoList;
    }
}