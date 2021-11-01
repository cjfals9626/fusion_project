package persistence.dto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SubjectNotOpenDTO {
    private int subject_id;
    private String subject_name;
    private boolean open;
}
