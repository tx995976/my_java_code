
class admin{
    public String name;
    public String password;
    
    public void show(){
        System.out.println("姓名:"+this.name+",  "+"密码: "+this.password);
    }
}

public class ex1{
    public static void main(String args[]){
        admin temp1 = new admin();
        admin temp2 = new admin();
        temp1.name = "admin1";
        temp2.name = "admin2";
        temp1.password = "admin1";
        temp2.password = "admin2";
        temp1.show();
        temp2.show();
    }
}


