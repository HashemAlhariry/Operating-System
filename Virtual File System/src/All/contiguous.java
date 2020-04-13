package All;

import java.io.Serializable;

public class contiguous implements Allocation, Serializable
{
	private static final long serialVersionUID = 1L;
	  //int arr[];
      int harddisk;
     int block[];
     Node root;
    public contiguous(int disksize){
        root= new Node();
        root.setSize(harddisk);
        root.setName("root");
        harddisk=disksize;
        block=new int[harddisk];
        for(int i=0;i<harddisk;i++)
        {
            block[i]=1;

        }
    }


    @Override
    public void addnode(String command)
    {
        String split[]=command.split(" ");
        int x=0;
        if(split[0].equals("createfile")){
            x=Integer.parseInt(split[2]);
        }


        int counter=0;
        int firstindex=0,lastindex=0;
        Boolean check=false;
        for(int i=0;i<block.length;i++ )
        {
            if(i==block.length-1 && counter<x){
                firstindex=-1;
                break;
            }
            if(block[i]==1)
            {
                if(!check)
                   firstindex=i;


                check=true;
                counter++;

                if(counter==x)
                {
                 lastindex=i;
                 for(int j=firstindex;j<lastindex;j++)
                 {
                     block[j]=0;  //allocate file with its place in hd
                 }
                 break;
                }
            }
            else
            {
                firstindex=-1;
                check=false;
                counter=0;
            }

        }
        if(firstindex==-1){
            System.out.println("no contiguous space");
        }
        else{
            Node c=new Node();
                c.addfile(command,root,firstindex);
        }


    }
    @Override
    public void addfolder(String command){
        Node n=new Node();
        n.addfolder(command,root);
    }

    @Override
    public void deletenode(String command)
    {

        Node n=verify.getnode(command,root);
        int start=n.getStart();
        int size=n.getSize();

        for(int i=start;i<size && i>0 ;i++){
            block[i]=1;
        }
        n.deletenode(command,root);

    }

    @Override
    public void getdirectories(){
        verify.getdirectories(root,0);
    }

    @Override
    public void diskstatus(){
        System.out.println("total size is "+root.getSize());
    }

}
