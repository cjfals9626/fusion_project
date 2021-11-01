package persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SubjectTimeDTO {
    private int subject_time_id;
    private int subject_id;
    private String day;
    private String time;
    private String classroom;
}
