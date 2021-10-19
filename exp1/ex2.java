import java.util.Scanner;

class Rectangle{
    public double length;
    public double width;
    
    public void set(){
        System.out.println("");
        Scanner scan = new Scanner(System.in);
        this.width = scan.nextDouble();
        this.length = scan.nextDouble();
        scan.close();
    }
    public void get_area(){}
    

}


class Circle{
    public double R;
    
    public void set(){
        System.out.println("");
        Scanner scan = new Scanner(System.in);
        this.R = scan.nextDouble();
        scan.close();
    }
    public void get_area(){
        System


    }
}

class Box{
    public double length;
    
    public void set(){
        System.out.println("");
        Scanner scan = new Scanner(System.in);
        this.length = scan.nextDouble();
        scan.close();
    }
    public void get_area(){}
}



public class ex2 {
    public static void main(String[] args) {
        System.out.println("选择形状");
        System.out.println("1.长方形");
        System.out.println("2.圆形");
        System.out.println("3.三角形");
        System.out.println("输入选项:");
        int i;
        Scanner scan = new Scanner(System.in);
        i = scan.nextInt();
        scan.close();
        switch(i){
            case 1:
                Rectangle rec_temp = new Rectangle();
                break;
                
            case 2:
                Circle cir_temp = new Circle();
                break;

            case 3:
                Box box_temp = new Box();
                break;
        }
    }
}
