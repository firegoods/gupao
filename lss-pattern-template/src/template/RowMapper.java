package template;

import java.sql.ResultSet;

/**
 * Created by liushaoshuai on 2018/3/16.
 */
public interface RowMapper<T> {

    public T mapRow(ResultSet resultSet, int rowNum) throws Exception;
}
