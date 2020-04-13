package All;

public class verify
{
    public static String checkcommand(String x)   //check the command
    {
        int index = x.indexOf(' ');
        if(index != -1)
        {
            x = x.substring(0, index);
            if(x.equals("createfolder") ||x.equals("createfile") || x.equals("deletefile") || x.equals("deletefolder"))
                return x;
        }
        else
        {
            if(x.equals("displaydiskstatus") || x.equals("displaydiskstructure"))
                return x;
        }
        return null;
    }

    public static int checkslash(String x)
    {
        int count=0;
        while(true)
        {
            int indexof = x.indexOf('/');
            if(indexof==-1)
                break;
            count++;
            x=x.substring(indexof+1);

        }

        return count;
    }
    public static void getdirectories(Node root,int level){
        for(int i=0;i<level;i++)
        {
            System.out.print("/t");
        }

        System.out.println("directory is: "+root.getName() + "size is"+root.getSize());
        for(int i=0;i<root.getChildren().size();i++)
        {
            if(root.getChildren().get(i).getName().contains("."))
            {
                Node s=root.getChildren().get(i);
                System.out.println(" name of the file is:"+s.getName()+" size is: "+s.getSize());
            }
            else
             {
                getdirectories(root.getChildren().get(i),level+1);
             }
        }
        return;
    }
    public static Node getnode(String command,Node root) {
        String[] splitcomm = command.split(" ");
        String[] splitpath;
        System.out.println("Creating Folder ...");
        splitpath = splitcomm[1].split("/");  //splits path
        String filename = splitpath[splitpath.length - 1];  //filename of the file

        if (splitpath[0].equals("root")) {
            int i = 1;
            Node temp = root;
            while (true) {
                if (i == splitpath.length - 1) {
                    Node node = new Node(0, temp, filename);
                    return node;
                }
                for (int j = 0; j < temp.getChildren().size(); j++)
                {

                    if (temp.getChildren().get(j).getName().equals(splitpath[i])) {
                        temp = temp.getChildren().get(j);
                        break;
                    } else if (j == temp.getChildren().size() - 1) {
                        System.out.println("invalid path");
                    }
                }
                i++;
            }
        }
        return null;
    }
    public static void addspace(Node n)
    {
        int size=n.getSize();
        n.getParent().setSize(size+n.getParent().getSize());
        while(!n.getName().equals("root"))
        {
            n=n.getParent();
            n.setSize(0);
            for(int i=0;i<n.getChildren().size();i++){

                n.setSize(n.getSize()+n.getChildren().get(i).getSize());
            }

        }

    }
    public static void subtract(Node n)
    {
        int size=n.getSize();
      //  n.getParent().setSize(n.getParent().getSize()-size);
        while(!n.getName().equals("root"))
        {

            n=n.getParent();
            n.setSize(n.getSize()-size);
        }
    }




}
