
public interface operation{
    public double cal(double value_l,double value_r);
}

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
    
}
