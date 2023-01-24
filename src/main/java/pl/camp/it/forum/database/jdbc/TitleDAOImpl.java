package pl.camp.it.forum.database.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.forum.database.IPostDAO;
import pl.camp.it.forum.database.ITitleDAO;
import pl.camp.it.forum.model.Post;
import pl.camp.it.forum.model.Title;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
public class TitleDAOImpl implements ITitleDAO {
    @Autowired
    Connection connection;
    @Autowired
    IPostDAO postDAO;

    @Override
    public void addTitle(Title title, Post post) {
    try{
        String sql = "INSERT INTO ttitle (name , date, user_id) VALUES (?,?,?);";
        PreparedStatement ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, title.getName());
        ps.setTimestamp(2, Timestamp.valueOf(title.getDate()));
        ps.setInt(3,title.getUserId());
        ps.executeUpdate();
        ResultSet rs =  ps.getGeneratedKeys();
        rs.next();
        title.setId(rs.getInt(1));
        postDAO.addPost(post, title.getId(), title.getUserId());
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    }

    @Override
    public void editTitle(Title title) {
    try{
        String sql = "UPDATE ttitle SET name = ? WHERE id = ?;";
        PreparedStatement ps = this.connection.prepareStatement(sql);
        ps.setString(1,title.getName());
        ps.setInt(2, title.getId());
        ps.executeUpdate();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    }

    @Override
    public void removeTitle(int id) {
    try{
        String sql = "DELETE FROM ttitle WHERE id= ?;";
        PreparedStatement ps = this.connection.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    }

    @Override
    public List<String> getTitleList() {
        List<String> result = new ArrayList<>();
        try{
            String sql = "SELECT name FROM ttitle;";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                result.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public List<Title> getTitle() {
        List<Title> result = new ArrayList<>();
        try{
            String sql = "SELECT* FROM ttitle;";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int titleId = rs.getInt("id");
                result.add(new Title(
                        titleId,
                        rs.getString("name"),
                        this.postDAO.getPostByTitleId(titleId),
                        rs.getTimestamp("date").toLocalDateTime(),
                        rs.getInt("user_id")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Optional<Title> getTitleById(int id) {
        try{
            String sql = "SELECT * FROM ttitle WHERE id=?;";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int titleId = rs.getInt("id");
                return Optional.of(new Title(
                                titleId,
                                rs.getString("name"),
                                this.postDAO.getPostByTitleId(titleId),
                                rs.getTimestamp("date").toLocalDateTime(),
                                rs.getInt("user_id")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}
