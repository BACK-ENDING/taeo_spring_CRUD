package MSG.crud.repository;

import MSG.crud.domain.Posting;
import org.springframework.jdbc.datasource.DataSourceUtils;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCPostingRepository implements PostingRepository{

    private final DataSource dataSource;

    public JDBCPostingRepository(DataSource dataSource) {
        this.dataSource = dataSource;

    }

    @Override
    public Posting save(Posting posting) {
        String sql = "insert into posting (title, content, name) values(?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs =null;

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

//        @Override
//        public Optional<Posting> findByTitle(String title) {
//            String sql = "SELECT * FROM posting WHERE title = ?";
//
//            Connection conn = null;
//            PreparedStatement pstmt = null;
//        }

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
