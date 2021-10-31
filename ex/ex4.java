class book_goods{
    protected String name;
    protected int pages;
    protected double prices;
    public book_goods(int page_in,double prices_in,String name_in){
        this.pages = page_in;
        this.prices = prices_in;
        this.name = name_in;
    }    
    public void show(){
        System.out.printf("name: %s\npage: %d\nprice: %f\n",this.name,this.pages,this.prices);
    }
}

class novel extends book_goods{
    protected String type;

    public novel(int page_in,double prices_in,String name_in,String type_in){
        super(page_in, prices_in,name_in);
        this.type = type_in;
    }
    public void show(){
        super.show();
        System.out.printf("type: %s\n\n",this.type);
    }
}

class periodical extends book_goods{
    protected String time;
    
    public periodical(int page_in,double prices_in,String name_in,String time_in){
        super(page_in, prices_in, name_in);
        this.time = time_in;
    }
    public void show(){
        super.show();
        System.out.printf("time: %s\n\n",this.time);
    }
}

class textbook extends book_goods{
    protected String suitable;

    public textbook(int page_in,double prices_in,String name_in,String suitable_in){
        super(page_in, prices_in, name_in);
        this.suitable = suitable_in;
    }
    public void show(){
        super.show();
        System.out.printf("suitable: %s\n\n",this.suitable);
    }
}

public class ex4 {
    public static void main(String[] args) {
        book_goods novel_test = new novel(50,13.9,"halo","science fiction");
        novel_test.show();
        book_goods periodical = new periodical(100,23.9,"science","2021/10/15");
        periodical.show();
        book_goods textbook_test = new textbook(600,70.9,"math","college student");
        textbook_test.show();
    }
}
