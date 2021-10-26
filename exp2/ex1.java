package exp2_ex1;
import java.util.Scanner;

class Rectangle implements Geometricinterface{
    public double l1;
    public double l2;
    public double l3;

    public Rectangle(){
        double l1_i,l2_i,l3_i;
        System.out.println("input(l1,l2,l3):");
        Scanner input = new Scanner(System.in);
        l1_i = input.nextDouble();
        l2_i = input.nextDouble();
        l3_i = input.nextDouble();
        if(Math.abs(l1_i-l2_i) > l3_i || Math.abs(l1_i-l3_i) > l2_i || Math.abs(l3_i - l2_i) > l1_i){
            System.out.println("invaild_input");
            return;
        }
        this.l1 = l1_i;
        this.l2 = l2_i;
        this.l3 = l3_i;
    }
    public void get_area(){
        double p = (this.l1+this.l2+this.l3)/2;
        double result = Math.sqrt((p*(p-this.l1)*(p-this.l2)*(p-this.l3)));
        System.out.printf("%s%.2f\n","area is: ",result);
    }
    public void get_Perimeter(){
        double p = this.l1+this.l2+this.l3;
        System.out.println("Perimeter is: "+p);
    }
}

class Box implements Geometricinterface{
    public double length;
    public double width;
    
    public Box(){
        System.out.println("input(length,width): ");
        Scanner input = new Scanner(System.in);
        this.length = input.nextDouble();
        this.width = input.nextDouble();
    }

    public void get_area(){
        System.out.println("area is: "+(this.length*this.width));
    }
    public void get_Perimeter(){
        System.out.println("perimeter is: "+(this.length+this.width)*2);
    }
}

public class ex1 {
public static void main(String[] args) {
    Rectangle Rec_test = new Rectangle();
    Box Box_test = new Box();

    Rec_test.get_Perimeter();
    Rec_test.get_area();

    Box_test.get_Perimeter();
    Box_test.get_area();
}
}
