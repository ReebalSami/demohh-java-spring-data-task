package neuefisched.de.demohhjavaspringdatatask.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {

    private String name;
    private int age;
    private String profession;
}
