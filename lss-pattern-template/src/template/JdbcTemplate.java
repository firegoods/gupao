package template;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liushaoshuai on 2018/3/15.
 */
public class JdbcTemplate {
    private DataSource dataSource;

    public List<?> executeQuery(String sql, RowMapper rowMapper,Object [] values){


        try {
            //1、通过数据源获取连接
            //2、通过连接获取语句集
            //3、执行语句集获取结果
            //4、分析结果
            //5、关闭结果集
            //6、关闭语句集
            //7、关闭连接
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i=0;i<values.length;i++){
                preparedStatement.setObject(i,values[i]);
            }
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            List<Object> result = new ArrayList<Object>();
            int rowNum = 1;
            //查出来的记录数不止一条
            while (resultSet.next()){
                result.add(rowMapper.mapRow(resultSet,rowNum));
            }

            resultSet.close();
            resultSet.close();
            connection.close();




        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

}
