public class Hello {
    private static int integer;
    public static void main (String args []) {
        System.out.println("Hello");
        int c;
        multiply(5,10);

    }

    public static Integer multiply(int a, int b) {
        integer = a * b;
        return integer;
    }
}