import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class CheckPhoneNumbers {
    private StringBuilder TemplatePhoneNumer = new StringBuilder();
    private StringBuilder PhoneNumer = new StringBuilder();
    private String phoneNumer = "";
    private String stringNumber = "0123456789";
    private String signPhoneNumer = "- ()";
    private String templatePhoneNumer1 = "(xxx) xxx-xxxx";
    private String templatePhoneNumer2 = "xxx-xxx-xxxx";

    public boolean isPhoneNumer(String s){

        if (s.contains("\n")){
            phoneNumer = PhoneNumer.toString();
            boolean isPhoneNumer = false;

            if (templatePhoneNumer1.equals(TemplatePhoneNumer.toString())) isPhoneNumer = true;
            if (templatePhoneNumer2.equals(TemplatePhoneNumer.toString())) isPhoneNumer = true;

            TemplatePhoneNumer.delete(0, TemplatePhoneNumer.length());
            PhoneNumer.delete(0, PhoneNumer.length());

            return isPhoneNumer;
        }

        if (signPhoneNumer.contains(s) || stringNumber.contains(s)){
            String x = (stringNumber.contains(s)) ? "x" : s;
            TemplatePhoneNumer.append(x);
            PhoneNumer.append(s);
        }
        return false;
    }

    public String getPhoneNumer(){
        return phoneNumer;
    }

    public static void main(String[] args) {

        //String absolutePath= "C:\\Users\\User\\IdeaProjects\\Module_10_Input_output_streams\\phone_numbers";
        String absolutePath= "phone_numbers";
        File file = new File(absolutePath);

        if(!file.exists()) {
            throw new RuntimeException("File with name "+file.getName()+" not exists");
        }

        try(BufferedInputStream buffeedInputStream = new BufferedInputStream(new FileInputStream(absolutePath))) {
            int readByte = buffeedInputStream.read();

            CheckPhoneNumbers checkPhoneNumbers = new CheckPhoneNumbers();
            while(readByte != -1){
                char ch = (char) readByte;

                if (checkPhoneNumbers.isPhoneNumer(""+ch)){
                    System.out.println("tel. "+checkPhoneNumbers.getPhoneNumer());
                }
                readByte = buffeedInputStream.read();
            }
            if (checkPhoneNumbers.isPhoneNumer("\n")){
                System.out.println("tel. "+checkPhoneNumbers.getPhoneNumer());
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
