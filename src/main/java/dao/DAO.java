package dao;

import data.Data;
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

    /////////////////////////////////////////////////////////////////////////////////
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

    ////////////////////////////////////////////////////////////////////////////////////
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

    ////////////////////////////////////////////////////////////////////////////////////
    // вставляет одну запись в SALES
    public void insertDataItemToSales(Data item) {
        String sql = "INSERT INTO sales (year, month, sum, id_catalog) VALUES (?, ?, ?, ?)";
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, item.getYear());
            statement.setInt(2, item.getMonth());
            statement.setInt(3, item.getSum());
            statement.setInt(4, item.getIdCatalog());
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    ///////////////////////////////////////////////////////////////////////
    // создает таблицу CATALOG
    public void createTableSales() {
        String sql1 = "DROP TABLE IF EXISTS sales";
        String sql2 = "CREATE TABLE sales (\n" +
                    "    year       INTEGER,\n" +
                    "    month      INTEGER,\n" +
                    "    sum        INTEGER,\n" +
                    "    id_catalog INTEGER)";
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

    ///////////////////////////////////////////////////////////////////////
    // создает таблицу CATALOG
    public void createTableCatalog() {
        String sql1 = "DROP TABLE IF EXISTS catalog";
        String sql2 = "CREATE TABLE catalog (\n" +
                "    id    INTEGER      PRIMARY KEY AUTOINCREMENT\n" +
                "                       NOT NULL,\n" +
                "    ref   VARCHAR (16) UNIQUE,\n" +
                "    title VARCHAR (64))";
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
