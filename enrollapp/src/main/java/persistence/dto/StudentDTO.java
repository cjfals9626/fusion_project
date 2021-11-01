package persistence.dto;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StudentDTO {
    private int id;
    private String pw;
    private int groud_id;
    private String name;
    private LocalDateTime birth;
    private String phoneNumber;
    private String major;
    private int grade;
}
