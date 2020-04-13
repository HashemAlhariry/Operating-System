package All;

import java.awt.Choice;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.Scanner;

public class Main {
	static int harddisk = 100;

	static Allocation a;
	static int choice;

	public static void writeToFile(int choice) throws Exception{
		FileOutputStream o;
		ObjectOutputStream obj;
		if (choice == 1) {
			o = new FileOutputStream("contiguousfile");
			obj = new ObjectOutputStream(o);
			obj.writeObject(a);
		} else if (choice == 2) {
			o = new FileOutputStream("linkedfile");
			obj = new ObjectOutputStream(o);
			obj.writeObject(a);
		}
	}

	public static void readFromFile(int choice) throws Exception {
		FileInputStream o;
		ObjectInputStream obj;
		if (choice == 1) {
			o = new FileInputStream("contiguousfile");
			obj = new ObjectInputStream(o);
			a = (contiguous) obj.readObject();
		}
		/*else if (choice == 2) {
			o = new FileInputStream("linkedfile");
			obj = new ObjectInputStream(o);
			a = (linked) obj.readObject();
		}*/
	}

	public static void main(String[] args) throws Exception {


		Allocation a=null;

		System.out.println("please enter size of disk");
		Scanner capacity=new Scanner(System.in);
		harddisk=capacity.nextInt();
		System.out.println("please enter the type of allocation you want");
		System.out.println("1-contiquous allocation \n 2-linked allocation \n 3-indexed allocation");
		int choice;
		Scanner inn=new Scanner(System.in);
		choice=inn.nextInt();
		switch (choice){
			case 1:
				a=new contiguous(harddisk);
				break;
			/*case 2:
				a=new linked(harddisk);
				break;
			case 3:
				a=new indexed(harddisk);
				break;*/
			default:
				System.out.println("invalid number");
		}
      String command="";

      do {
		  System.out.print("Enter your command:");
		  Scanner cin = new Scanner(System.in);
		  command = cin.nextLine();
		  if (verify.checkcommand(command) != null) {
			  String comm = verify.checkcommand(command);
			  String[] splitcomm = command.split(" ");
			  switch (comm) {
				  case "createfile":
					  a.addnode(command);
					  break;
				  case "createfolder":
					  Node n = new Node();
					  a.addfolder(command);
					  break;
				  case "displaydiskstructure":
					  a.getdirectories();
					  break;

				  case "deletefile":
                      a.deletenode(command);
					  break;
				  case "deletefolder":
				  	a.deletenode(command);
                      break;
                  case "displaydiskstatus":
                      a.diskstatus();


                      break;
              }


          }
          else
              System.out.println("Please Enter the command right :)");
      }while(!command.equals("0"));

			  }

		  }
