package persistence.dto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

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
