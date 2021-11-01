package persistence.mapper;

import org.apache.ibatis.annotations.*;
import persistence.dto.SubjectOpenDTO;

import java.util.List;

public interface SubjectOpenMapper {
    final String getAll = "select * from subject natural join subject_open;";

    @Select(getAll)
    @Results(id="subjectOpenResultSet", value ={
            @Result(property = "subject_id", column = "subject_id"),
            @Result(property = "subject_name", column = "subject_name"),
            @Result(property = "open", column = "open"),
            @Result(property = "max_people", column = "max_people"),
            @Result(property = "number_people", column = "number_people"),
            @Result(property = "possible_grade", column = "possible_grade"),
            @Result(property = "subject_plan", column = "subject_plan"),
            @Result(property = "plan_modify_date", column = "plan_modify_date"),
            @Result(property = "credit", column = "credit")
    })

    @SelectProvider(type = persistence.mapper.SubjectOpenMapper.class, method = "selectSubjectAll")
    @ResultMap("subjectOpenResultSet")
    List<SubjectOpenDTO> selectSubjectOpenAll();
}