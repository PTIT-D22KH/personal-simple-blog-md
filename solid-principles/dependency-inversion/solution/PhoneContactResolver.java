public class PhoneContactResolver implements ContactResolver {
    @Override
    public String getUserContact(String userId) {
        // In a real application, this would fetch the phone number from a database
        return "+1234567890";
    }
}