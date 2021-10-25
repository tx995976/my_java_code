import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class operation{
    protected double value_l,value_r,result;
    protected Character operater;
    public operation(double value_l_i,double value_r_i){
        this.value_l = value_l_i;
        this.value_r = value_r_i;
    }
    public double get_result(){
        return result;
    }
}

class operater_plus extends operation{
    public operater_plus(double value_l_i,double value_r_i){
        super(value_l_i, value_r_i);
    }
    public double get_result(double value_l,double value_r){
        double temp = value_l+value_r;
        return temp;
    }
}

class operater_minus extends operation{
    public operater_minus(double value_l_i,double value_r_i){
        super(value_l_i, value_r_i);
    }
    public double get_result(double value_l,double value_r){
        double temp = value_l - value_r;
        return temp;
    }
}

class operater_multiply extends operation{
    public operater_multiply(double value_l_i,double value_r_i){
        super(value_l_i, value_r_i);
    }
    public double get_result(double value_l,double value_r){
        double temp = value_l * value_r;
        return temp;
    }
}

class operater_division extends operation{
    public operater_division(double value_l_i,double value_r_i){
        super(value_l_i, value_r_i);
    }
    public double get_result(double value_l,double value_r){
         
        double temp = value_l / value_r;
        return temp;
    }
}

public class ex2{

    public void math_str_get_result(StringBuilder math_changed){


    }

    public void math_str_change(String input){
        //运算符优先表
        HashMap<Character,Integer> upper_punct = new HashMap<Character,Integer>();
        upper_punct.put('(', 0);
        upper_punct.put(')', 0);
        upper_punct.put('+', 1);
        upper_punct.put('-', 1);
        upper_punct.put('*', 2);
        upper_punct.put('/', 2);
        //////
        char temp_char;
        Stack<Character> punct_stack = new Stack<Character>();
        StringBuilder math_str = new StringBuilder(input);
        StringBuilder math_str_back =new StringBuilder();
        
        Pattern nums_P =Pattern.compile("^((\\d+)(\\.\\d+)?)");
        Pattern negative_nums_P = Pattern.compile("^(-\\d+(\\.\\d+)?)");
        //负数特判
        Matcher nums_match = negative_nums_P.matcher(math_str);
        if(nums_match.find()){
            math_str_back.append(nums_match.group(1)+" ");
            math_str.delete(nums_match.start(),nums_match.end());
        }
        //
        while(math_str.charAt(0) != '='){
            nums_match = nums_P.matcher(math_str);
            if(nums_match.find()){
                math_str_back.append(nums_match.group(1)+" ");
                math_str.delete(nums_match.start(),nums_match.end());
            }

            else{
                temp_char = math_str.charAt(0);
                switch(temp_char){
                    case '(':{
                        math_str.deleteCharAt(0);
                        punct_stack.push(temp_char);
                        //负数特判  
                        nums_match = negative_nums_P.matcher(math_str);
                        if(nums_match.find()){
                            math_str_back.append(nums_match.group(1)+" ");
                            math_str.delete(nums_match.start(),nums_match.end());
                        }
                        //
                        break;
                    }
                    case ')':{
                        math_str.deleteCharAt(0);
                        while(punct_stack.peek().charValue() != '('){
                            math_str_back.append(punct_stack.pop().toString()+" ");
                        }
                        punct_stack.pop();
                        break;
                    }
                    default:{
                        math_str.deleteCharAt(0);
                        if(punct_stack.empty() || upper_punct.get(punct_stack.peek()).intValue() < upper_punct.get(temp_char).intValue()){
                            punct_stack.push(temp_char);
                        }
                        else{
                            while(!punct_stack.empty() && upper_punct.get(punct_stack.peek()).intValue() >= upper_punct.get(temp_char).intValue()){
                                math_str_back.append(punct_stack.pop().toString()+" ");
                            }
                            punct_stack.push(temp_char);
                        }
                        break;
                    }
                }
            }
        }
        while(!punct_stack.empty()){
            math_str_back.append(punct_stack.pop().toString()+" ");
        }
        System.out.println(math_str_back);
    }

    public static void main(String[] args) {
            ex2 test = new ex2();
            System.out.println("input(\"=\" is EOF):");
            String str_in = new String();
            Scanner scan = new Scanner(System.in);
            if(scan.hasNext())
                str_in = scan.next();
            test.math_str_change(str_in);
            
    }

}
