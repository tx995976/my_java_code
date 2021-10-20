import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ex3 {
    public static void main(String[] args) {
        String input_str = "<html><body><a href=\"http://www.baidu.com\">百度</a>，<a href='www.webmeteor.cn/course/java' target='_blank'>网易</a>，<a href='/news/13432.html' target=\"_blank\">内部地址</a></body></html>";
        String match_str = "href=['\"](\\S*)['\"]";
        Pattern P = Pattern.compile(match_str);
        Matcher M = P.matcher(input_str);
        while(M.find()){
            System.out.println(M.group(1));
        }
    }
}
