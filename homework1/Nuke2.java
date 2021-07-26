import java.io.*;
public class Nuke2 {
    public static void main(String[] arg) throws Exception{
        System.out.println( "please enter char");
        //StringBuffer keyboard_in=new StringBuffer(System.in);
        
        BufferedReader keyboard = new BufferedReader( new InputStreamReader(System.in));
        StringBuffer keyboard_in=new StringBuffer();
        keyboard_in.append(keyboard.readLine());
		keyboard_in=keyboard_in.delete(1, 2);
        System.out.println(keyboard_in);
    }
}
