package JukeBox.admin;
@FunctionalInterface
public interface CredentialsValidator {
    boolean validate(String name,String password);
}
