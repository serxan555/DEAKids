package dea.connection;

import dea.beans.Student;
import dea.beans.User;
import static dea.connection.LoginRepository.staticId;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author SS.555
 */
public class StudentRepository extends DataManager implements Repository<Student> {

    ObservableList<String> list = FXCollections.observableArrayList();

    @Override
    public Student save(Student o) {
        String query;
        try {
            if (o.getName() != null && !"".equals(o.getName())) {
                if (o.getId() == 0) {
                    query = "insert into students (name,surname,parent,birthDate,"
                            + "phone,gmail,gmailCode)"
                            + " values(?,?,?,?,?,?,?)";
                    connect();
                    statement = connection.prepareStatement(query, new String[]{"id"});
                    statement.setString(1, o.getName());
                    statement.setString(2, o.getSurname());
                    statement.setString(3, o.getParent());
                    statement.setDate(4, new Date(o.getBirthdate().getTime()));
                    statement.setString(5, o.getPhone());
                    statement.setString(6, o.getGmail());
                    statement.setString(7, o.getGmailCode());
                    statement.execute();

                    resultSet = statement.getGeneratedKeys();
                    long studentLastId = 0;
                    if (resultSet.next()) {
                        Long lastId = (Long) resultSet.getObject(1);
                        studentLastId = lastId;
                    }
                    String sgtQuery = "insert into sgt (userId,studentId,status,lessonDate,lessonTime,groupId) values (?,?,?,?,?,"
                            + "(select id from groups where groupName = '" + o.getGroupName() + "') )";
                    statement = connection.prepareStatement(sgtQuery);
                    statement.setLong(1, LoginRepository.staticId);
                    statement.setLong(2, studentLastId);
                    statement.setString(3, o.getStatus());
                    statement.setString(4, o.getLessonDate());
                    statement.setString(5, o.getLessonTime());
//                statement.setString(6, o.getPayment().toString());
                    statement.execute();
                } else {
                    query = "update students set"
                            + " name = ? , surname = ? , parent = ? ,"
                            + " birthDate = ? , phone = ? , gmail = ? ,"
                            + " gmailCode = ? where id = ? ";
                    connect();
                    statement = connection.prepareStatement(query);
                    statement.setString(1, o.getName());
                    statement.setString(2, o.getSurname());
                    statement.setString(3, o.getParent());
                    statement.setDate(4, (Date) o.getBirthdate());
                    statement.setString(5, o.getPhone());
                    statement.setString(6, o.getGmail());
                    statement.setString(7, o.getGmailCode());
                    statement.setLong(8, o.getId());
                    statement.execute();

                    String sgtQuery = "update sgt set status = ?, "
                            + " groupId = (select id from groups where groupName = '" + o.getGroupName() + "'),"
                            + " lessonDate = ?, lessonTime = ?, "
                            + " payment = ? "
                            + " where userId = ?"
                            + " and studentId = ?";
                    statement = connection.prepareStatement(sgtQuery);
                    statement.setString(1, o.getStatus());
                    statement.setString(2, o.getLessonDate());
                    statement.setString(3, o.getLessonTime());
                    statement.setDouble(4, o.getPayment());
                    statement.setLong(5, LoginRepository.staticId);
                    statement.setLong(6, o.getId());
                    statement.execute();
                }
            } else {
                query = "update sgt set lessonDate = ?,"
                        + " lessonTime = ? where groupId = (select id from groups where groupName = '" + o.getGroupName() + "')";
                connect();
                statement = connection.prepareStatement(query);
                statement.setString(1, o.getLessonDate());
                statement.setString(2, o.getLessonTime());
                statement.execute();
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            try {
                disconnect();
            } catch (SQLException ex) {
            }
        }

        return o;
    }

    @Override
    public Student find(Student o) {
        return null;
    }

    @Override
    public List<Student> findAll() {
        return null;
    }

    @Override
    public boolean delete(Student o) {
        String sgtQuery = "delete from sgt where studentId = ?";

        try {
            connect();
            statement = connection.prepareStatement(sgtQuery);
            statement.setLong(1, o.getId());
            statement.execute();

            String query = "delete from students where id = ?";
            statement = connection.prepareStatement(query);
            statement.setLong(1, o.getId());
            statement.execute();
        } catch (IOException | SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            try {
                disconnect();
            } catch (SQLException e) {
            }
        }
        return false;
    }

    public List<Student> findAllForUser(Long u) throws SQLException {

        List<Student> studentList = new ArrayList<>();
        String query = "select students.*, sgt.*, groups.* from sgt "
                + " right join students on students.id = sgt.studentId "
                + " left join groups on groups.id = sgt.groupId "
                + " where sgt.userId = ? and status = 'Active'";
        try {
            connect();
            statement = connection.prepareStatement(query, new String[]{"id"});
            statement.setLong(1, u);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Student s = new Student();
                s.setId(resultSet.getLong("id"));
                s.setName(resultSet.getString("name"));
                s.setSurname(resultSet.getString("surname"));
                s.setParent(resultSet.getString("parent"));
                s.setBirthdate(resultSet.getDate("birthDate"));
                s.setPhone(resultSet.getString("phone"));
                s.setGmail(resultSet.getString("gmail"));
                s.setGmailCode(resultSet.getString("gmailCode"));
                s.setGroupName(resultSet.getString("groupName"));
                s.setLessonDate(resultSet.getString("lessonDate"));
                s.setLessonTime(resultSet.getString("lessonTime"));
                s.setStatus(resultSet.getString("status"));
                s.setPayment(resultSet.getDouble("payment"));
                studentList.add(s);
            }
            return studentList;
        } catch (IOException | SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            disconnect();
        }
        return null;
    }

    public List<Student> studnetSearch(Map<String, String> map) throws SQLException {
        List<Student> studentList = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("select students.*, sgt.*, groups.* from sgt "
                + " right join students on students.id = sgt.studentId "
                + " left join groups on groups.id = sgt.groupId "
                + " where ");
//        StringBuilder queryBuilder = new StringBuilder("select * from students "
//                + " where ");
        for (String key : map.keySet()) {
            if (map.get(key) != null && !map.get(key).isEmpty()) {
                queryBuilder.append(key).append(" like lower(");
                queryBuilder.append("'").append(map.get(key)).append("%')").append(" and ");
            }
        }
        String query = "";
        if (queryBuilder.toString().endsWith(" and ")) {
            query = queryBuilder.toString().substring(0, queryBuilder.toString().length() - 5);
        }
        try {
            connect();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Student s = new Student();
                s.setId(resultSet.getLong("id"));
                s.setName(resultSet.getString("name"));
                s.setSurname(resultSet.getString("surname"));
                s.setParent(resultSet.getString("parent"));
                s.setBirthdate(resultSet.getDate("birthDate"));
                s.setPhone(resultSet.getString("phone"));
                s.setGmail(resultSet.getString("gmail"));
                s.setGmailCode(resultSet.getString("gmailCode"));
                if (resultSet.getLong("userId") == LoginRepository.staticId) {
                    s.setGroupName(resultSet.getString("groupName"));
                    s.setLessonDate(resultSet.getString("lessonDate"));
                    s.setLessonTime(resultSet.getString("lessonTime"));
                    s.setStatus(resultSet.getString("status"));
                    s.setPayment(Double.parseDouble(resultSet.getString("payment")));
                }
                studentList.add(s);
            }
            return studentList;
        } catch (IOException | SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            disconnect();
        }
        return null;
    }

    public ObservableList<String> allGroupname() {
        String query = "select groupName from groups";
        try {
            connect();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString("groupName"));
            }
            return list;
        } catch (IOException | SQLException e) {
        }
        return null;
    }

    public Student searchLessons(String groupName) throws SQLException {
        String query = "select lessonDate,lessonTime from sgt "
                + " where groupId = (select id from groups where groupName = '" + groupName + "')";
        try {
            connect();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Student s = new Student();
                s.setLessonDate(resultSet.getString("lessonDate"));
                s.setLessonTime(resultSet.getString("lessonTime"));
                return s;
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            disconnect();
        }
        return null;
    }

    public Long isExitstSgt(Long id) throws SQLException {
        String query = "select id from sgt where studentId = ? and userId = " + LoginRepository.staticId;
        try {
            connect();
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong("id");
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            disconnect();
        }
        return null;
    }

    public void addSgt(Student o) throws SQLException {
        String sgtQuery = "insert into sgt (userId,studentId,status,lessonDate,lessonTime,groupId) values (?,?,?,?,?,"
                + "(select id from groups where groupName = '" + o.getGroupName() + "') )";
        try {
            connect();
            statement = connection.prepareStatement(sgtQuery);
            statement.setLong(1, LoginRepository.staticId);
            statement.setLong(2, o.getId());
            statement.setString(3, o.getStatus());
            statement.setString(4, o.getLessonDate());
            statement.setString(5, o.getLessonTime());
//            statement.setDouble(6, o.getPayment());
            statement.execute();
        } catch (IOException | SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            disconnect();
        }
    }

    public String calculatePayment() throws SQLException {
        String query = "select sum(payment)*0.33 from sgt where status = 'Active' and userId = " + LoginRepository.staticId;
        try {
            connect();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                
                return Double.toString(Math.floor(resultSet.getDouble(1)));
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            disconnect();
        }
        return null;
        // 1259.3
    }
}
