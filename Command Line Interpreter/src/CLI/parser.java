package CLI;


import java.io.IOException;

public class parser extends terminal
{
    String[] args; // Will be filled by arguments extracted by parse method
    String cmd; // Will be filled by the command extracted by parse method
    terminal ter;

    public parser()
    {
        args=new String[2];
        args[0]="";
        args[1]="";
        cmd="";
        ter=new terminal();
    }

    public boolean parse(String input)
    {
        cmd="";
        args[0]="";
        args[1]="";
        int no_of_commands=1;

       String [] split=input.split(" ");

       for(int i=0;i<split.length;i++){
           if(split[i].equals("|")){
               no_of_commands++;
           }
       }
       int commands=0;
       int j=0;

       while(commands!=no_of_commands)  //makes each command alone
       {
           int no_of_args=0;
           args[0]="";
           args[1]="";
           while(j<split.length && !split[j].equals("|"))
           { //gets the command and args from split
               if(j==0){
                   cmd=split[0];
               }
               else if(split[j-1].equals("|")){
                   cmd=split[j];
               }
               else
                   {
                   args[no_of_args]=split[j];
                   no_of_args++;
               }
               j++;
           }
           boolean x=do_command();
           if(x==false){
               return x;
           }
           commands++;
           j++;
       }
       return true;
   }
   public boolean do_command()
   {
       switch (cmd){
           case "cp":
               if(args[0].equals(""))
               {
                   return false;
               }
               try
               {
                   ter.cp(args[0],args[1]);
               }
               catch (IOException e) {
                   return false;
               }
               return true;
           case "mv":
               if(args[0].equals("")){
                   System.out.println("should enter source path");
                   return false;
               }
               try {
                   ter.mv(args[0],args[1]);
               } catch (IOException e) {
                   System.out.println(e.toString());
               }
               return true;
           case "rm":
               if(args[0].equals("")){
                   return false;
               }
               try {
                   ter.rm(args[0]);
               } catch (IOException e) {
                   System.out.println(e.toString());
               }
               return true;
           case "pwd":
               if(!args[0].equals("")||!args[1].equals("")){
                   System.out.println("you shouldn't enter any parameters");
               }
               ter.pwd();
               return true;
           case "cd":
               try {
                   ter.cd(args[0]);
               } catch (IOException e) {
                   System.out.println(e.toString());
               }
               return true;
           case "clear":
               if(!args[0].equals("")||!args[1].equals("")){
                   return false;
               }
               ter.clear();
               return true;
           case "ls":
               if(!args[1].equals("")){
                   return false;
               }
               ter.ls(args[0]);
               return true;
           case "mkdir":
               try {
                   ter.mkdir(args[0],args[1]);
               } catch (IOException e) {
                   System.out.println(e.toString());
               }
               return true;
           case"rmdir":
               if(args[0].equals("")){
                   return false;
               }
               try {
                   ter.rmdir(args[0]);
               } catch (IOException e) {
                   System.out.println(e.toString());
               }
               return true;

           case "cat":
               if(args[0].equals("")||!args[1].equals("")){
                   return false;
               }

               try {
                   if(!args[0].equals("") && args[1].equals(""))
                       ter.cat1(args[0]);

                   else if(!args[0].equals("") && !args[1].equals(""))
                       ter.cat2(args[0],args[1]);

               } catch (IOException e) {
                   System.out.println(e.toString());
               }
               return true;


           case"more":
               if(!args[0].equals("")||!args[1].equals("")){
                   return false;
               }
               try {
                   ter.more(args[0]);
               } catch (IOException e) {
                   System.out.println(e.toString());
               }
               return true;
           case"help":
               if(!args[0].equals("")||!args[1].equals("")){
                   return false;
               }
               ter.help();
               return true;
           case"date":
               if(!args[0].equals("")||!args[1].equals("")){
                   return false;
               }
               ter.date();
               return true;
           case"exit":
               if(!args[0].equals("")||!args[1].equals("")){
                   return false;
               }
               ter.exit();
               return true;
           default:
               return false;

       }
   }
}













