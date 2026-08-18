package dto;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UserLombok {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
}