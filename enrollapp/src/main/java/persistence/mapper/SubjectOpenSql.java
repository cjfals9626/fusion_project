package persistence.mapper;

import org.apache.ibatis.jdbc.SQL;
import persistence.dto.SubjectOpenDTO;

public class SubjectOpenSql {
    public String selectSubjectAll() {
        SQL sql = new SQL() {{
            SELECT("*");
            FROM("subject, subject_open");
            WHERE("subject.subject_id = subject_open.subject_id");
        }};
        return sql.toString();
    }
}