package neuefisched.de.demohhjavaspringdatatask.service;

import neuefisched.de.demohhjavaspringdatatask.model.Character;
import neuefisched.de.demohhjavaspringdatatask.model.dtos.ClientDto;
import neuefisched.de.demohhjavaspringdatatask.repos.CharRepo;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AsterixServiceTest {

    private final CharRepo charRepoMock = mock(CharRepo.class);
    private final IdService idMock = mock(IdService.class);
    AsterixService asterixService = new AsterixService(charRepoMock, idMock);

    @Test
    void getAllCharacters() {
        //GIVEN
        List<Character> expected = new ArrayList<>();
        when(charRepoMock.findAll()).thenReturn(new ArrayList<>());
        //WHEN
        List<Character> actual = asterixService.getAllCharacters();
        //THEN

        assertEquals(expected, actual);
    }

    @Test
    void getCharacterById() {

        //GIVEN
       Character expected = new Character("1", "Zorro", 4, "King");
        when(charRepoMock.findById("1")).thenReturn(Optional.of(new Character("1", "Zorro", 4, "King")));
        //WHEN
       Character actual = asterixService.getCharacterById("1");
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    void deleteCharacterById() {
        //GIVEN
        Character char1 = new Character("1", "Zorro", 4, "King");

        when(charRepoMock.existsById(char1.getId())).thenReturn(true);

        // WHEN
        ResponseEntity<Object> actual = asterixService.deleteCharacterById("1");

        // THEN
        assertNotNull(actual);
        assertEquals("Character deleted successfully.", actual.getBody());


    }

    @Test
    void updateCharacterById() {
    }
}