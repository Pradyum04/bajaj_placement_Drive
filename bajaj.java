import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class bajaj {
    public static void main(String[] args) {
        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get("input.json")));
            
            Gson gson = new Gson();
            StudentData data = gson.fromJson(jsonContent, StudentData.class);
            
            String first = data.student.firstName.toLowerCase();
            String roll = data.student.rollNo.toLowerCase();
            
            String concat = first + roll;
            
            String md5_Hash = get_Hash_string(concat);
            
            Files.write(Paths.get("output.txt"), md5_Hash.getBytes());
            
            System.out.println("The output is stored in output.txt file: " + md5_Hash);
        } 

        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    private static String get_Hash_string(String input) throws NoSuchAlgorithmException {
        MessageDigest message = MessageDigest.getInstance("MD5");
        byte[] hashBytes = message.digest(input.getBytes());
        
        StringBuilder output_String = new StringBuilder();
        for (byte b : hashBytes) {
            output_String.append(String.format("%02x", b));
        }
        return output_String.toString();
    }
}

class StudentData{
    Student student;
}

class Student{
    String firstName;
    String rollNo;
}



