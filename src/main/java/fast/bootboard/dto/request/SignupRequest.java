package fast.bootboard.dto.request;


public record SignupRequest(
        String username,
        String password,
        String confirmPassword
) {

    public static SignupRequest of(String username, String password, String confirmPassword) {
        return new SignupRequest(username, password, confirmPassword);
    }


}
