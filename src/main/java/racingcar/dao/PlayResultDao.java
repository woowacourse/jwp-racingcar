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
import racingcar.service.PlayResult;

@Repository
public class PlayResultDao {

    public int insertPlayResult(final PlayResult playResult) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName("org.h2.Driver");
            String url = "jdbc:h2:mem:testdb;MODE=MySQL;";
            conn = DriverManager.getConnection(url, "sa", "");
            String sql = "INSERT INTO PLAY_RESULT (winners, trial_count) VALUES(?,?)";

            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, playResult.getWinners());
            pstmt.setInt(2, playResult.getTrialCount());

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

    public List<PlayResult> selectAllResults() {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            String url = "jdbc:h2:mem:testdb;MODE=MySQL";
            conn = DriverManager.getConnection(url, "sa", "");
            String sql = "select id, winners, trial_count from play_result order by id desc";
            pstmt = conn.prepareStatement(sql);

            ResultSet resultSet = pstmt.executeQuery();

            List<PlayResult> playResults = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String winners = resultSet.getString("winners");
                int trialCount = resultSet.getInt("trial_count");
                playResults.add(new PlayResult(id, winners, trialCount));
            }

            return playResults;
        } catch (SQLException e) {
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
}
