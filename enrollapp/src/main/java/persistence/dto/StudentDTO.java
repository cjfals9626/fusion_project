package persistence.dto;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StudentDTO {
    private int id;
    private String pw;
    private int group_id;
    private String name;
    private LocalDate birth;
    private String phoneNumber;
    private String major;
    private int grade;
}
