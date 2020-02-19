package springLibrary.mapper;

import org.springframework.jdbc.core.RowMapper;
import springLibrary.entities.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeMapper implements RowMapper<Employee> {

    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee employee = new Employee();
        employee.setId((long)rs.getInt("id"));
        employee.setName(rs.getString("name"));
        employee.setSurname(rs.getString("surname"));
        employee.setPhoneNumber(rs.getString("phone_number"));
        employee.setSalary(rs.getFloat("salary"));
        employee.setPhotography(rs.getBytes("photography"));
        return employee;
    }

}
