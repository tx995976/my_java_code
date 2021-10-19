import java.util.Scanner;

class Box{
    public double length;
    public double width;
    
    public void set(){
        System.out.println("输入(长度,宽度):");
        Scanner scan = new Scanner(System.in);
        this.width = scan.nextDouble();
        this.length = scan.nextDouble();
        scan.close();
    }
    public void get_area(){
        System.out.printf("%s %.2f","面积为:",this.length*this.width);
    }
}


class Circle{
    public double R;
    
    public void set(){
        System.out.println("输入(半径):");
        Scanner scan = new Scanner(System.in);
        this.R = scan.nextDouble();
        scan.close();
    }
    public void get_area(){
        System.out.printf("%s %.2f","面积为:",this.R*this.R*Math.PI);    
    }
}

class Rectangle{
    public double length;
    public double H;
    public void set(){
        System.out.println("输入(底,高):");
        Scanner scan = new Scanner(System.in);
        this.length = scan.nextDouble();
        this.H = scan.nextDouble();
        scan.close();
    }
    public void get_area(){
        System.out.printf("%s %.2f","面积为:",this.length*this.H/2);    
    }
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
        switch(i){
            case 1:
               Box rec_temp = new Box();
                rec_temp.set();
                rec_temp.get_area();
                break;
                
            case 2:
                Circle cir_temp = new Circle();
                cir_temp.set();
                cir_temp.get_area();
                break;

            case 3:
                Rectangle box_temp = new Rectangle();
                box_temp.set();
                box_temp.get_area();
                break;
        }
    }
}
