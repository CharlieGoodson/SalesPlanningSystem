package dao;

import model.CatalogItem;
import util.ConnectSettings;

import java.sql.*;

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

    // получает ключ товара из CATALOG по введенному REF
    public int getIdCatalogItem(String ref) {
        int id = 0;
        String sql = "SELECT id FROM catalog WHERE ref = ?";
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, ref);
            ResultSet resultSet = statement.executeQuery();
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

    // вставляет новую позицию в CATALOG и возвращает ее id
    public int insertCatalogItem(CatalogItem item) {
        int id = 0;
        String sql = "INSERT INTO catalog (ref, title) VALUES (?, ?)";
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, item.getRef());
            statement.setString(2, item.getTitle());
            statement.execute();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return id;
    }

    // полностью очистить таблицу CATALOG
    public void recreateCatalog() {
        String sql1 = "DROP TABLE catalog";
        String sql2 = "CREATE TABLE catalog (\n" +
                "    id    INTEGER      PRIMARY KEY,\n" +
                "    ref   VARCHAR (16) UNIQUE,\n" +
                "    title VARCHAR (64) \n" +
                ");";
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql1);
            statement.execute();
            statement.close();
            statement = connection.prepareStatement(sql2);
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }
}
