package neuefisched.de.demohhjavaspringdatatask.repos;

import neuefisched.de.demohhjavaspringdatatask.model.Character;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharRepo extends MongoRepository<Character, String> {
}
