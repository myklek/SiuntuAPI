package savitarna.siuntusavitarna.responses;


import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class LoginResponse {

    private String token;
    private int userId;
    private long expiresIn;
}