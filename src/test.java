class Test{
    public void duplicate(){
        System.out.println("This is duplicate with no argument.");
    }

    public void duplicate(int i){
        System.out.println("This is duplicate with argument " + i);
    }

    public static void main(String[] args){
        Test test = new Test();
        test.duplicate();
        test.duplicate(2);
    }
}