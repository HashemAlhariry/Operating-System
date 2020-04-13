package All;

import java.io.Serializable;
import java.util.LinkedList;

public class linked implements Allocation, Serializable {
	private static final long serialVersionUID = 1L;
	int [] block;
    int blocksize;
    Node root;
    int harddisk;

    public linked(int disksize) {
        root= new Node();
        root.setSize(harddisk);
        root.setName("root");
        harddisk=disksize;
        block=new int[harddisk];
        this.blocksize=blocksize;
        block=new int[blocksize];
        for(int i=0;i<blocksize;i++){
            block[i]=-2;
        }
    }

    @Override
    public void addnode(String command){
         String [] split=command.split(" ");
        int size=0;
        if(split[0].equals("createfile")){
            size=Integer.parseInt(split[2]);
        }


        int index=-1;
        for(int i=0;i<blocksize;i++){
            for(int j=0;j<size;j++){
                if(index==-1 && block[i]==-2){
                    index=i;
                    block[i]=-1;
                    i++;
                }
                else if(block[i]==-2){
                    block[index]=i;
                    block[i]=-1;
                    i++;
                }
                j=0;
            }
        }
        Node c=new Node();
        c.addfile(command,root,index);
    }

    @Override
    public void addfolder(String command) {
        Node n=new Node();
        n.addfolder(command,root);
    }

    @Override
    public void getdirectories() {
        verify.getdirectories(root,0);
    }

    @Override
    public void deletenode(String command){

        Node n=verify.getnode(command,root);
        int start=n.getStart();
        int temp=start;
        int temp1=0;
        while (temp!=-1){
            temp1=block[temp];
            block[temp]=-1;
            temp=temp1;
        }
        n.deletenode(command,root);
    }

    @Override
    public void diskstatus(){
        System.out.println("total size is "+root.getSize());
        System.out.println("empty space:"+(harddisk-root.getSize()));

    }


}
