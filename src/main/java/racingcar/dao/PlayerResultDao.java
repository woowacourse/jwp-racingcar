package racingcar.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import racingcar.service.PlayerResult;

@Repository
public class PlayerResultDao {

    public int insertPlayerResult(final PlayerResult playerResult) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName("org.h2.Driver");
            String url = "jdbc:h2:mem:testdb;MODE=MySQL;";
            conn = DriverManager.getConnection(url, "sa", "");
            String sql = "INSERT INTO PLAYER_RESULT (play_result_id, name, position) VALUES(?,?,?)";

            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, playerResult.getPlayResultId());
            pstmt.setString(2, playerResult.getName());
            pstmt.setInt(3, playerResult.getPosition());

            pstmt.executeUpdate();

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            generatedKeys.next();

            return generatedKeys.getInt(1);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
                if (pstmt != null && !pstmt.isClosed()) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<PlayerResult> selectPlayerResult(final int playResultId) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName("org.h2.Driver");
            String url = "jdbc:h2:mem:testdb;MODE=MySQL;";
            conn = DriverManager.getConnection(url, "sa", "");
            String sql = "select * from player_result where PLAY_RESULT_ID = ?";

            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, playResultId);

            ResultSet resultSet = pstmt.executeQuery();

            List<PlayerResult> playResults = new ArrayList<>();
            while (resultSet.next()) {
                int findPlayResultId = resultSet.getInt("play_result_id");
                String name = resultSet.getString("name");
                int position = resultSet.getInt("position");
                playResults.add(new PlayerResult(findPlayResultId, name, position));
            }

            return playResults;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
                if (pstmt != null && !pstmt.isClosed()) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

//    public PlayerResultDao(final DataSource dataSource, final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
//        this.insertActor = new SimpleJdbcInsert(dataSource)
//                .withTableName("PLAYER_RESULT")
//                .usingGeneratedKeyColumns("id");
//        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
//    }
//
//    public List<PlayerResult> selectPlayerResultByPlayResultId(final int playResultId) {
//        String sql = "select play_result_id, name, position from player_result where PLAY_RESULT_ID = :play_result_id";
//        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource("play_result_id", playResultId);
//        return namedParameterJdbcTemplate.query(sql, sqlParameterSource, actorRowMapper);
//    }
}
