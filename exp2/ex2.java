package cal;
import java.util.Scanner;
import java.util.Stack;


class operater_plus implements operation{
    public double cal(double value_l,double value_r){
        double temp = value_l+value_r;
        return temp;
    }
}

class operater_minus implements operation{
    public double cal(double value_l,double value_r){
        double temp = value_l - value_r;
        return temp;
    }
}

class operater_multiply implements operation{
    public double cal(double value_l,double value_r){
        double temp = value_l * value_r;
        return temp;
    }
}

class operater_division implements operation{
    public double cal(double value_l,double value_r){
         
        double temp = value_l / value_r;
        return temp;
    }
}

public class ex2{
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        if(scan.hasNext()){
            Double i = scan.nextDouble();
            System.out.println(i);
        }
        scan.close();        
    }


}
