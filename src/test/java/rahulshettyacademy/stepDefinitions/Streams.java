package rahulshettyacademy.stepDefinitions;

import java.util.ArrayList;

public class Streams {
public static void main(String args[]) {
	int i;
Boolean b =false;
ArrayList<String> a =new ArrayList<String>();
a.add("hello");
a.add("hi");
a.add("apple");
a.add("mango");
a.add("grapes");

//.out.println(a);
for ( i = 0; i < a.size(); i++) {
 //   System.out.println(a.get(i));
    if(a.contains("apple")) {
    	b=true;
    	System.out.println(b);
    	break;
    	
    }
    else
    {
    	System.out.println(b);
}
}
}
}




















