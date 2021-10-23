import java.util.regex.Pattern;
import java.util.regex.Matcher;

class Student{
    public String name;
    public int age;
    public String  Mail;
    public void emailType(){
        String match_str = "@(\\S+)\\.";
        Pattern p = Pattern.compile(match_str);
        Matcher M = p.matcher(this.Mail);
        if(M.find()){
            System.out.println("emailType is: "+M.group(1));
        }
        else
            System.out.println("no email");
    }
}


public class ex1 {
    public static void main(String[] args) {
        Student temp1 = new Student();
        temp1.Mail = "tx995976@163.com";
        temp1.age = 18;
        temp1.name = "tx";
        temp1.emailType();
    }
}
