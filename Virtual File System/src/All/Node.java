package All;

import java.util.*;

public class Node {
    private int size = 0;
    private List<Node> children = new ArrayList<>(10);
    private Node parent = null;
    private String name = " ";
    private int start = -1;

    public Node(int size, Node parent, String name) {
        this.size = size;
        this.parent = parent;
        this.name = name;
    }

    public Node() {
        children = new ArrayList<>();
        parent = null;
        name = "";
        size = 0;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    public void addChild (Node child)
    {
        if (child == null) {
            child = new Node();
        }
        child.setParent(this);
        this.children.add(child);
    }

    public List<Node> getChildren ()
    {
        return children;
    }


    private void setParent (Node parent)
    {
        this.parent = parent;
    }

    public Node getParent ()
    {
        return parent;
    }


    public void addfile(String command, Node root,int start) {
        String comm = verify.checkcommand(command);
        String[] splitcomm = command.split(" ");
        int numberofslashes = verify.checkslash(splitcomm[1]);  //number of slashes
        String[] splitpath = splitcomm[1].split("/");
        String filename = splitpath[splitpath.length - 1];  //filename of the file

        if (!filename.contains(".")) {
            System.out.println("please enter valid name (.)");
            return;
        }

        if (numberofslashes == 0) {
            System.out.println("Please Enter The path");
            return;
        }
        else
         {

            if (splitpath[0].equals("root"))
            {
                int i = 0;
                Node temp = root;
                while (true)
                {
                    if (i == splitpath.length - 1)
                    {

                        Node node = new Node(Integer.parseInt(splitcomm[2]), temp, filename); // error when creating new file null exception
                        node.setStart(start);
                        temp.addChild(node);
                        verify.addspace(node);
                        break;
                    }
                    for (int j = 0; j < temp.getChildren().size(); j++)
                    {
                        if (temp.getChildren().get(j).getName().equals(splitpath[i]))
                        {
                            temp.setSize(temp.getSize() + Integer.parseInt(splitcomm[2]));
                            temp = temp.getChildren().get(j);
                            break;
                        }
                        else if (j == temp.getChildren().size() - 1 && !temp.getChildren().get(j).getName().equals(splitpath[i]))
                        {
                            System.out.println("invalid path");
                        }
                    }
                    i++;
                }

            }
        }




    }
    public void addfolder(String command,Node root){
        String comm = verify.checkcommand(command);
        String[] splitcomm = command.split(" ");
        String[] splitpath;
        System.out.println("Creating Folder ...");
                      splitpath = splitcomm[1].split("/");  //splits path
                      String filename = splitpath[splitpath.length - 1];  //filename of the file

                      if (splitpath[0].equals("root"))
                      {
                          int i = 1;
                          Node temp = root;
                          while (true)
                          {
                              if (i == splitpath.length - 1)
                              {
                                  Node node = new Node(0, temp, filename);
                                  temp.addChild(node);
                                  break;
                              }
                              for (int j = 0; j < temp.getChildren().size(); j++)
                              {
                                  if (temp.getChildren().get(j).getName().equals(splitpath[i]))
                                  {
                                    //  temp.setSize(temp.getSize() + Integer.parseInt(splitcomm[2]));
                                      temp = temp.getChildren().get(j);
                                      break;
                                  }
                                  else if (j == temp.getChildren().size() - 1)
                                  {
                                      System.out.println("invalid path");
                                  }
                              }
                              i++;
                          }
                      }
    }
    public void deletenode(String command,Node root){
        String comm = verify.checkcommand(command);
        String[] splitcomm = command.split(" ");
        int numberofslashes = verify.checkslash(splitcomm[1]);  //number of slashes
        String[] splitpath = splitcomm[1].split("/");
        String filename = splitpath[splitpath.length - 1];  //filename of the file
        splitpath = splitcomm[1].split("/");  //splits path


        if(splitpath[0].equals("root"))
        {
            Node temp=root;
            for(int i=0;i<splitpath.length;i++)
            {
                for(int j=0;j<temp.getChildren().size();j++)
                {
                    if(i==splitpath.length-1 &&splitpath[i].equals(temp.getChildren().get(j).getName()))
                    {
                        verify.subtract(temp.getChildren().get(j));
                        temp.getChildren().remove(j);
                        break;

                    }
                    if(splitpath[i].equals(temp.getChildren().get(j).getName()))
                    {
                        temp=temp.getChildren().get(j);
                        break;
                    }

                }
            }


        }
    }
}
