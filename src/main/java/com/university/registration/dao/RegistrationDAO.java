package com.university.registration.dao;
import com.university.registration.model.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository
public class RegistrationDAO {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public boolean registerForCourse(int studentId, int courseId) {
        String checkSql = "SELECT COUNT(*) FROM registrations WHERE student_id = ? AND course_id = ?";
        int count = jdbcTemplate.queryForObject(checkSql, new Object[]{studentId, courseId}, Integer.class);
        
        if (count > 0) {
            return false;
        }
        String sql = "INSERT INTO registrations (student_id, course_id, date) VALUES (?, ?, ?)";
        int result = jdbcTemplate.update(sql, studentId, courseId, new Date());
        return result > 0;
    }
    public List<Registration> getRegistrationsByStudentId(int studentId) {
        String sql = "SELECT * FROM registrations WHERE student_id = ?";
        return jdbcTemplate.query(sql, new Object[]{studentId}, new RegistrationRowMapper());
    }
    private class RegistrationRowMapper implements RowMapper<Registration> {
        @Override
        public Registration mapRow(ResultSet rs, int rowNum) throws SQLException {
            Registration registration = new Registration();
            registration.setRegId(rs.getInt("reg_id"));
            registration.setStudentId(rs.getInt("student_id"));
            registration.setCourseId(rs.getInt("course_id"));
            registration.setRegistrationDate(rs.getDate("date"));
            return registration;
        }
    }
}