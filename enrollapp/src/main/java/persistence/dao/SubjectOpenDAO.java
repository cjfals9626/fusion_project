package persistence.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.dto.SubjectOpenDTO;
import persistence.mapper.SubjectOpenMapper;

import java.util.List;
import java.util.Scanner;

public class SubjectOpenDAO {
    private SqlSessionFactory sqlSessionFactory = null;
    Scanner s;

    public SubjectOpenDAO(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public List<SubjectOpenDTO> selectSubjectOpenAll(){
        List<SubjectOpenDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        SubjectOpenMapper mapper = session.getMapper(SubjectOpenMapper.class);
        try{
            list = mapper.getSubjectOpenAll();
        }catch ( Exception e)
        {
            e.printStackTrace();
            session.rollback();
        }
        finally {
            session.close();
        }
        return list;
    }

    public List<SubjectOpenDTO> selectSubjectGradeOpenAll(int possible_grade){
        List<SubjectOpenDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        SubjectOpenMapper mapper = session.getMapper(SubjectOpenMapper.class);
        try{
            list = mapper.getSubjectOpenGradeAll(possible_grade);
        }catch ( Exception e)
        {
            e.printStackTrace();
            session.rollback();
        }
        finally {
            session.close();
        }
        return list;
    }

    public List<SubjectOpenDTO> getSubjectOpenProfessorAll(int professor_id){
        List<SubjectOpenDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        SubjectOpenMapper mapper = session.getMapper(SubjectOpenMapper.class);
        try{
            list = mapper.getSubjectOpenProfessorAll(professor_id);
        }catch ( Exception e)
        {
            e.printStackTrace();
            session.rollback();
        }
        finally {
            session.close();
        }
        return list;
    }

    public List<SubjectOpenDTO> getSubjectOpenProfessorAndGradeAll(int professor_user_id, int possible_grade){
        List<SubjectOpenDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        SubjectOpenMapper mapper = session.getMapper(SubjectOpenMapper.class);
        try{
            list = mapper.getSubjectOpenProfessorAndGradeAll(professor_user_id, possible_grade);
        }catch ( Exception e)
        {
            e.printStackTrace();
            session.rollback();
        }
        finally {
            session.close();
        }
        return list;
    }

    public void insertSubjectOpen(SubjectOpenDTO subjectOpenDTO){
        s = new Scanner(System.in);
        SqlSession session = sqlSessionFactory.openSession();
        SubjectOpenMapper mapper = session.getMapper(SubjectOpenMapper.class);
        try{
            mapper.insertSubjectOpen(subjectOpenDTO.getSubject_id(), subjectOpenDTO.getSubject_name(), subjectOpenDTO.getPossible_grade(), subjectOpenDTO.getMax_people(), subjectOpenDTO.getNumber_people(), subjectOpenDTO.getSubject_plan(), subjectOpenDTO.getPlan_modify_date(), subjectOpenDTO.getCredit(), subjectOpenDTO.getDay(), subjectOpenDTO.getTime(), subjectOpenDTO.getClassroom(), subjectOpenDTO.getProfessor_user_id());
            session.commit();
        }catch ( Exception e)
        {
            e.printStackTrace();
            session.rollback();
        }
        finally {
            session.close();
        }
    }

    public void updateSubjectOpen(int subject_id, int max_people, String classroom){
        List<SubjectOpenDTO> list = null;
        SubjectOpenDTO subjectOpenDTO = new SubjectOpenDTO();
        SqlSession session = sqlSessionFactory.openSession();
        SubjectOpenMapper mapper = session.getMapper(SubjectOpenMapper.class);
        try{
            mapper.updateSubjectOpen(subject_id, max_people, classroom);
            session.commit();
        }catch ( Exception e)
        {
            e.printStackTrace();
            session.rollback();
        }
        finally {
            session.close();
        }
    }
}
