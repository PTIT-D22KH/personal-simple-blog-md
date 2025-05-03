public class UserDAO extends DAO{
    private LogService logService;
    public UserDAO(LogService logService) {
        this.logService = logService;
    }
    public User findUser(String userId) {
        try{
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                return user;
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            logService.logError("Database error when finding user: " + e.getMessage());
        }
        return null;
    }
    public boolean saveUser(User user) {
        try {
            PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO users (id, name, email) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE name = ?, email = ?");
            stmt.setString(1, user.getId());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getName());
            stmt.setString(5, user.getEmail());
            
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            logService.logError("Database error when saving user: " + e.getMessage());
            return false;
        } 
    }
}