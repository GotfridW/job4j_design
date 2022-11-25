package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) throws ClassNotFoundException, SQLException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("driver"));
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(url, login, password);
    }

    public void createTable(String tableName) throws Exception {
        var query = String.format("create table if not exists %s(id serial primary key);", tableName);
        processQuery(tableName, query);
    }

    public void dropTable(String tableName) throws Exception {
        var query = String.format("drop table if exists %s;", tableName);
        processQuery(tableName, query);
        System.out.printf("Table %s deleted", tableName);
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        var query = String.format("alter table %s add %s %s;",
                tableName, columnName, type);
        processQuery(tableName, query);
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        var query = String.format("alter table %s drop column %s;",
                tableName, columnName);
        processQuery(tableName, query);
    }

    public void renameColumn(String tableName, String columnName,
                             String newColumnName) throws Exception {
        var query = String.format("alter table %s rename column %s to %s;",
                tableName, columnName, newColumnName);
        processQuery(tableName, query);
    }

    private void processQuery(String tableName, String query) throws Exception {
        try (var statement = connection.createStatement()) {
            statement.execute(query);
            if (!query.startsWith("drop table")) {
                System.out.println(getTableScheme(tableName));
            }
        }
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            cfg.load(in);
        }
        TableEditor editor = new TableEditor(cfg);
        var tableName = "customers";
        editor.createTable(tableName);
        editor.addColumn(tableName, "last_name", "varchar(30)");
        editor.renameColumn(tableName, "last_name", "lname");
        editor.dropColumn(tableName, "lname");
        editor.dropTable(tableName);
    }
}
