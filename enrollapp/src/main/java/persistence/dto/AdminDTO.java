package persistence.dto;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class AdminDTO {
    private int id;
    private String pw;
    private int group_id;
    private String name;
    private LocalDate birth;
    private String phoneNumber;
}
