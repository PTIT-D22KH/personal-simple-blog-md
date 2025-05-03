public class EmailContactResolver implements ContactResolver {
    @Override
    public String getUserContact(String userId) {
        // In a real application, this would fetch the email from a database
        return userId + "@example.com";
    }
}