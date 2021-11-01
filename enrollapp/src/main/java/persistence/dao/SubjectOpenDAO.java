package persistence.dao;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.dto.SubjectOpenDTO;
import persistence.mapper.SubjectOpenMapper;

import java.util.List;

public class SubjectOpenDAO {
    private SqlSessionFactory sqlSessionFactory = null;

    public SubjectOpenDAO(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public List<SubjectOpenDTO> selectSubjectOpenAll(){
        List<SubjectOpenDTO> list = null;

        SqlSession session = sqlSessionFactory.openSession();
        SubjectOpenMapper mapper = session.getMapper(SubjectOpenMapper.class);
        try {
            list = mapper.selectSubjectOpenAll();
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        return list;
    }
}
