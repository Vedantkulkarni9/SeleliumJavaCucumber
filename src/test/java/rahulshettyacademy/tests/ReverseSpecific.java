package rahulshettyacademy.tests;

public class ReverseSpecific {
	public static void main(String[] args) {
        String v = "vedant is a good boy";

        // find index of 'b'
        int index = v.indexOf('b');

        // replace only that 'b' with 'B'
        String result = v.substring(0, index) 
                        + 'B' 
                        + v.substring(index +1);

        System.out.println(result);
    }
}

