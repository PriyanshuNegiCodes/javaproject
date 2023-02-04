package JukeBox.admin;
public class CredentialsValidatorImpl {
    public boolean validateUser(String name, String password){
        CredentialsValidator obj= (name1, password1) -> {
            if(name1.equals(name)&&password1.equals(password)){return true;}
            return false;
        };
        return obj.validate("admin", "1234");
    }

}