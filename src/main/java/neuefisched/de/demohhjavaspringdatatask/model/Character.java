package neuefisched.de.demohhjavaspringdatatask.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("Character")

public class Character {

    private String id;
    private String name;
    private int age;
    private String profession;
}
