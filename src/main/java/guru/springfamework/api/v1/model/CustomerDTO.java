package guru.springfamework.api.v1.model;

import lombok.Data;

@Data
public class CustomerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    // generated on the fly value, doesn't exist in domain! (no url in db). But it exists in DTO
    private String customerUri;
}
