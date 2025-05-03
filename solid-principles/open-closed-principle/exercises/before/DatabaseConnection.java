public class DAO {
    protected static Connection connection;
    public DAO(){
        if (connection == null) {
            try {
                String dbURL = "jdbc:mysql://localhost:3306/your_database";
                String dbUsername = "your_username";
                String dbPassword = "your_password";
                connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
                System.out.println("Connect to database successfully!");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static Connection getConnection() {
        return connection;
    }
    
    
}