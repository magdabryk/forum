package pl.camp.it.forum.database.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.forum.database.IPostDAO;
import pl.camp.it.forum.model.Post;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
public class PostDAOImpl implements IPostDAO {

    @Autowired
    Connection connection;

    @Override
    public List<Post> getPost() {
        List<Post> result = new ArrayList<>();
        try{
            String sql = "SELECT * FROM tpost;";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                result.add(new Post(
                                rs.getInt("id"),
                                rs.getTimestamp("date").toLocalDateTime(),
                                rs.getInt("user_id"),
                                rs.getString("content"),
                                rs.getInt("title_id")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void addPost(Post post, int titleId, int userId) {
    try{
        String sql = "INSERT INTO tpost (date, user_id, content, title_id) VALUES (?,?,?,?);";
        PreparedStatement ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
        ps.setInt(2, userId);
        ps.setString(3,post.getContent());
        ps.setInt(4, titleId);
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        post.setId(rs.getInt(1));
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    }

    @Override
    public void editPost(Post post) {
    try{
        String sql = "UPDATE tpost SET  content=?  WHERE id = ?;";
        PreparedStatement ps  = this.connection.prepareStatement(sql);
        ps.setString(1, post.getContent());
        ps.setInt(2,post.getId());
        ps.executeUpdate();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    }

    @Override
    public void removePost(int id) {
        try{
            String sql = "DELETE FROM tpost WHERE id=?;";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Post> getPostByTitleId(int titleId) {
        List<Post> result = new ArrayList<>();
        try{
            String sql = "SELECT * FROM tpost WHERE title_id = ?;";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, titleId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                result.add(new Post(
                        rs.getInt("id"),
                        rs.getTimestamp("date").toLocalDateTime(),
                        rs.getInt("user_id"),
                        rs.getString("content"),
                        rs.getInt("title_id")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void removePostByTitleId(int titleId) {
        try{
            String sql = "DELETE FROM tpost WHERE title_id=?;";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, titleId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Post> getPostById(int id) {
        try{
            String sql = "SELECT * FROM tpost WHERE id=?;";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return Optional.of(new Post(
                        rs.getInt("id"),
                        rs.getTimestamp("date").toLocalDateTime(),
                        rs.getInt("user_id"),
                        rs.getString("content"),
                        rs.getInt("title_id")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Post> getLastPostByTitleId(int titleId) {
        try{
            String sql = "SELECT * FROM tpost WHERE title_id = ? ORDER BY date DESC LIMIT 1;";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, titleId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return Optional.of(new Post(
                        rs.getInt("id"),
                        rs.getTimestamp("date").toLocalDateTime(),
                        rs.getInt("user_id"),
                        rs.getString("content"),
                        rs.getInt("title_id")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}
