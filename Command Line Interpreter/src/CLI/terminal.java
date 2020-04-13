package CLI;
import org.apache.commons.io.FileUtils;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class terminal
{
    public Path defaultpath;
    public Path path;

public terminal()
{
    this.defaultpath=Paths.get("E:\\");
    this.path=defaultpath;
}

    public void cp(String sourcePath, String destinationPath) throws IOException
    {
        if (destinationPath == "") {
            destinationPath=path.toString();
        }
        File sourcefile = new File(sourcePath);
        File destinationfile = new File(destinationPath);
        File tempfile = new File("/" + sourcePath);
        File check = new File(tempfile.getAbsolutePath());

            if(FileUtils.directoryContains(path.toFile(),tempfile) && check.isFile())
            {
                Path temp1= tempfile.toPath().toAbsolutePath();
                FileUtils.copyFileToDirectory(temp1.toFile(), destinationfile);
            }
            else
            {
                FileUtils.copyDirectory(sourcefile,destinationfile);
            }

    }


    public void mv(String sourcePath, String destinationPath) throws IOException
    {
        if (destinationPath == "") {
            destinationPath=path.toString();
        }
        File sourcefile = new File(sourcePath);
        File destinationfile = new File(destinationPath);
        File tempfile = new File("/" + sourcePath);
        File check = new File(tempfile.getAbsolutePath());

        if((FileUtils.directoryContains(path.toFile() , tempfile ) && check.isFile() )&& sourcefile.isFile())
        {

            Path temp1= tempfile.toPath().toAbsolutePath();
            FileUtils.moveFileToDirectory(temp1.toFile(), destinationfile, true);
        }

        else
        {
            FileUtils.moveDirectoryToDirectory(sourcefile, destinationfile,true );
        }

    }
    public void rm(String sourcePath)throws IOException
    {

        File source = new File("/" + sourcePath);
        Path temp1= source.toPath().toAbsolutePath();
        Files.delete(temp1);

    }
    public void pwd()  //getting the relative directory
    {


        System.out.println(path.toString());

    }
    public void cd(String arg)throws IOException
    {

        if(arg.equals("") || arg.equals(" "))
        {
            path = defaultpath.toAbsolutePath();
        }

        else if (arg .equals(".."))
        {
            path = path.getParent();
        }

        else
        {
            path = Paths.get(arg+"\\");
        }

        System.out.println(path);
    }
    public void clear()
    {

        System.out.print("\033[H\033[2J");
        System.out.flush();

    }
    public void ls(String source)
    {
        ArrayList<File> names;
        if(source!=""){
            Path temp=Paths.get(source+"\\");
             names = new ArrayList<File>(Arrays.asList(temp.toFile().listFiles()));
        }
        else {
             names= new ArrayList<File>(Arrays.asList(path.toFile().listFiles()));
        }
        for(int i = 0 ; i < names.size() ; i++)
        {
            System.out.println(names.get(i).getName());
        }

    }
    public void mkdir(String pathh , String name)throws IOException{
    if(pathh=="")
    {
        pathh=path.toString();
     }
     if(name==""){
        name="new_folder";
     }
        Files.createDirectory(Paths.get(pathh+"/"+name));
    }

    public void rmdir(String sourcepath)throws IOException{
        File temp = new File("/" + sourcepath);
        Path temp1= temp.toPath().toAbsolutePath();
        FileUtils.deleteDirectory(temp1.toFile());

    }
    public void cat1(String filename) throws IOException //low one file
    {

        File my = new File(filename);

        if (!my.exists()) {
            System.out.println("Please enter valid file " + filename);
            return;
        }
        try {
            Scanner input = new Scanner(my);
            while (input.hasNextLine()) {
                System.out.println(input.nextLine());
            }
        } catch (Exception e) {
            System.out.println("Error");
        }

    }
    public void cat2(String firstfile,String secondfile) throws IOException //low two files
    {

        File myFile = new File(firstfile);
        File myFile2 = new File(secondfile);
        if (!myFile.exists() && !myFile2.exists()) {
            System.out.println("No such file named: " + firstfile + secondfile);
            return;
        }
        try {
            Scanner input = new Scanner(myFile);
            Scanner input2 = new Scanner(myFile2);
            while (input.hasNextLine() && input2.hasNextLine()) {
                System.out.println(input.nextLine());
                System.out.println(input2.nextLine());
            }
        } catch (Exception e) {
            System.out.println("ERROR");
        }

    }
    public void more(String sourcepath)throws IOException
    {






    }


    public void help()
    {
        args();
        System.out.println("date  Number of args is 0");
        System.out.println("exit  Number of args is 0");

    }
    public void args()
    {
        System.out.println("cp  Number of args is 2 : Source path,Destination path ");
        System.out.println("mv  Number of args is 2 : Source path,Destination path ");
        System.out.println("rm  Number of args is 1 : Source path ");
        System.out.println("pwd  Number of args is 0 ");
        System.out.println("clear  Number of args is 0 ");
        System.out.println("cd  Number of args is 1 : Destination ");
        System.out.println("ls  Number of args is 0  ");
        System.out.println("mkdir  Number of args is 2 : Path,FolderName ");
        System.out.println("cat : Number of args is 0 ");
        System.out.println("More  Number of args is 0 ");

    }

    public void date(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));

    }
    public void exit()
    {
        System.out.println("exit : stop all ");
                System.exit(1);
    }

}
