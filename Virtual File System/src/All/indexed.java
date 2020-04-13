package All;

import java.util.Vector;

public class indexed implements Allocation {
	Vector<Vector<Integer>> vec = new Vector();
	int size;
	String command;
	Node root;


	public indexed(int s) {
		root= new Node();
		root.setSize(size);
		root.setName("root");
		size = s;
		for (int i = 0; i < size; i++) {
			vec.add(null);
		}
	}


	@Override
	public void addnode(String command) {
		Node c=verify.getnode(command,root);
		int temp = 0;
		for (int i = 0; i < vec.size(); i++){
			if (vec.elementAt(i) == null){
				vec.set(i, new Vector());
				temp = i;
				System.out.println(i);
				break;
			}
		}
		int start = temp;
		int file_size = c.getSize();
		while (file_size > 0 && temp < vec.size()) {
			if (vec.elementAt(temp) == null){
				vec.set(temp, new Vector<>());
				vec.elementAt(start).addElement(temp);
				file_size--;
			}
			temp++;
		}
		c.setStart(start);
		System.out.println(vec);
		c.addfile(command,root,start);
	}


	@Override
	public void addfolder(String command) {

	}

	@Override
	public void getdirectories() {

	}

	@Override
	public void deletenode(String command){
		Node c=verify.getnode(command,root);
		int start = c.getStart();

		for (int i = 0; i < vec.elementAt(start).size(); i++){

			int index = vec.elementAt(start).elementAt(i);
			vec.set(index, null);
		}
		vec.set(start, null);

		System.out.println(vec);
		Node n=new Node();
		n.deletenode(command,root);
	}

	@Override
	public void diskstatus() {

	}
}
