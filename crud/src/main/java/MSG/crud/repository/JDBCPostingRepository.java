package MSG.crud.repository;

import MSG.crud.domain.Posting;
import org.springframework.jdbc.datasource.DataSourceUtils;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCPostingRepository implements PostingRepository {

    private final DataSource dataSource;

    public JDBCPostingRepository(DataSource dataSource) {
        this.dataSource = dataSource;

    }

    @Override
    public Posting save(Posting posting) {
        String sql = "insert into posting (title, content, name) values(?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, posting.getTitle());
            pstmt.setString(2, posting.getContent());
            pstmt.setString(3, posting.getName());

            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                posting.setTitle(rs.getString(1));
                posting.setContent(rs.getString(2));
                posting.setName(rs.getString(3));

            } else {
                throw new SQLException("게시물 조회 실패");
            }
            return posting;
        }
        catch (Exception e) {
            throw new IllegalStateException(e);
        }
        finally {
            close(conn, pstmt, rs);
        }
    }

        @Override
        public Optional<Posting> findByTitle(String title) {
            String sql = "SELECT * FROM posting WHERE title = ?";

            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            try {
                    conn = dataSource.getConnection();
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, title);

                rs = pstmt.executeQuery();

                if (rs.next()) {
                    Posting posting = new Posting();
                    posting.setPostid(rs.getLong("Postid"));
                    posting.setTitle(rs.getString("Title"));
                    posting.setContent(rs.getString("Content"));
                    posting.setName(rs.getString("Name"));
                    return Optional.of(posting);
                } else {
                    return Optional.empty();
                }
            }
                catch(Exception e) {
                    throw new IllegalStateException(e);
                }
                finally{
                    close(conn, pstmt, rs);
                }
        }

    @Override
    public Optional<Posting> findByName(String name) {
        String sql = "SELECT * FROM posting WHERE name = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                Posting posting = new Posting();
                posting.setPostid(rs.getLong("Postid"));
                posting.setTitle(rs.getString("Title"));
                posting.setContent(rs.getString("Content"));
                posting.setName(rs.getString("Name"));
                return Optional.of(posting);
            } else {
                return Optional.empty();
            }
        }
            catch(Exception e) {
                throw new IllegalStateException(e);
            }
                finally{
                close(conn, pstmt, rs);
            }
    }


    @Override
    public Optional<Posting> findByPostid(Long postid) {
        String sql = "SELECT * FROM posting WHERE postid = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, postid);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                Posting posting = new Posting();
                posting.setPostid(rs.getLong("Postid"));
                posting.setTitle(rs.getString("Title"));
                posting.setContent(rs.getString("Content"));
                posting.setName(rs.getString("Name"));
                return Optional.of(posting);
            } else {
                return Optional.empty();
            }
        }
        catch(Exception e) {
            throw new IllegalStateException(e);
        }
        finally{
            close(conn, pstmt, rs);
        }
    }

    @Override
        public List<Posting> findAll(){
            String sql = "SELECT * FROM Posting";

            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            try {
                conn = dataSource.getConnection();
                pstmt =conn.prepareStatement(sql);

                rs = pstmt.executeQuery();
                List<Posting> postings = new ArrayList<>();
                while(rs.next()) {
                    Posting posting = new Posting();
                    posting.setPostid(rs.getLong("Postid"));
                    posting.setTitle(rs.getString("Title"));
                    posting.setContent(rs.getString("Content"));
                    posting.setName(rs.getString("Name"));
                    postings.add(posting);
                }
                return postings;
            }
            catch (Exception e) {
                throw new IllegalStateException(e);
            }
            finally {
                close(conn, pstmt, rs);
            }
        }

    @Override
    public Optional<Posting> updateByAll(String title, String content, String name) {
        String sql = "UPDATE posting SET title = ?, content = ?, name = ? WHERE postid = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            if(rs.next()) {
                Posting posting = new Posting();
                pstmt.setString(1, posting.getTitle());
                pstmt.setLong(4, posting.getPostid());
                pstmt.setString(2, posting.getContent());
                pstmt.setString(3, posting.getName());
                return Optional.empty();
            }
            else {
                return Optional.empty();
            }
        }

        catch (Exception e){
            throw new IllegalStateException(e);
        }
        finally {
            close(conn, pstmt, rs);
        }
    }

    public Optional<Posting> deleteByAll(String title, Long postid, String content, String name) {
        String sql = "DELETE * FROM WHERE postid = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<Posting> postings= new ArrayList<>();
        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, postid);

            rs = pstmt.executeQuery();
            return Optional.empty();
        }
        catch (Exception e) {
            throw new IllegalStateException(e);
        }

        finally {
            close(conn, pstmt, rs);
        }
    }
    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs ) {
        try {
            if (rs != null) {
                rs.close();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if(pstmt != null) {
                pstmt.close();
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }

        try {
            if(conn != null) {
                close(conn);
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }

}
