package dao;

import model.CatalogItem;
import util.ConnectSettings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO {

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
        }
    }

    String url;

    public DAO() {
        ConnectSettings settings = new ConnectSettings();
        url = settings.getUrl();
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url);
    }

    private void closeConnection(Connection connection) {
        if (connection == null)
            return;
        try {
            connection.close();
        } catch (SQLException ex) {
        }
    }

    public int getIdCatalogItem(String ref) {

        int id = 0;
        String sql = "SELECT id FROM catalog WHERE ref = '" + ref + "'";

        Connection connection = null;
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return id;
    }

    public void insertCatalogItem(CatalogItem item) {
        String sql = "INSERT INTO catalog (ref, title) values ('"
                + item.getRef() + "', '" + item.getTitle() + "')";

        Connection connection = null;
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            statement.execute(sql);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }
}
