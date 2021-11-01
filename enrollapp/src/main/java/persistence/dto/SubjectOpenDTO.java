package persistence.dto;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SubjectOpenDTO {
    private int subject_id;
    private String subject_name;
    private boolean open;
    private int max_people;
    private int number_people;
    private int possible_grad;
    private String subject_plan;
    private LocalDateTime plan_modify_date;
    private int credit;
}
