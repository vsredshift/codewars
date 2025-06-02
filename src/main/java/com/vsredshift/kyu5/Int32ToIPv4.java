package main.java.com.vsredshift.kyu5;

public class Int32ToIPv4 {
    public static void main(String[] args) {
        var longToTest = 2154959208L;
        System.out.println("TESTING IP CONVERSION");
        System.out.println(longToIP(longToTest));
    }

    public static String longToIP(long ip) {
        return ((ip >> 24) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                ((ip >> 8) & 0xFF) + "." +
                (ip & 0xFF);
    }
}
