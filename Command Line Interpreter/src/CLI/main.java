package CLI;

import java.util.Scanner;

public class main {
    public static void main(String[] args)
    {
        String in="";
        parser x = new parser();
        do
            {
            Scanner input = new Scanner(System.in);
             in= input.nextLine();
            boolean good = x.parse(in);
            if (good == false) {
                System.out.println("not avaliable command");
            }

        }while(in != "exit");
    }
}
// hadeer ashraf elnaggar 20160278
//hashem khaled said 20160273