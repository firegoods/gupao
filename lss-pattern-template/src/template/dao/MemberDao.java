package template.dao;

import template.JdbcTemplate;
import template.RowMapper;
import template.entity.Member;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by liushaoshuai on 2018/3/15.
 */
public class MemberDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public List<?> query(){
        String sql = "select * from member";
        return jdbcTemplate.executeQuery(sql, new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet resultSet, int rowNum) throws Exception{
                Member member = new Member();
                member.setId(resultSet.getString("id"));
                member.setName(resultSet.getString("name"));
                member.setNickName(resultSet.getString("nickName"));
                member.setPassword(resultSet.getString("password"));
                member.setAddress(resultSet.getString("address"));
                member.setAge(resultSet.getInt("age"));

                return member;
            }
        },null);
    }
}
