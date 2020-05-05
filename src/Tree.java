import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class Tree {

	public Tree() {
		// TODO Auto-generated constructor stub
	}
	
	public static void generateLevel(DefaultMutableTreeNode current, String name, int size) {
		if(name.length() % 2 == 0) {
			DefaultMutableTreeNode left = new DefaultMutableTreeNode(name + "0");
			DefaultMutableTreeNode right = new DefaultMutableTreeNode(name + "1");
			left.setUserObject(new List(name + "0", size));
			right.setUserObject(new List(name + "1", size));
			current.add(left);
			current.add(right);
		}else {
			DefaultMutableTreeNode s = new DefaultMutableTreeNode(name + "s");
			DefaultMutableTreeNode w = new DefaultMutableTreeNode(name + "w");
			DefaultMutableTreeNode g1 = new DefaultMutableTreeNode(name + "a");
			DefaultMutableTreeNode g2 = new DefaultMutableTreeNode(name + "b");
			s.setUserObject(new List(name + "s", size));
			w.setUserObject(new List(name + "w", size));
			g1.setUserObject(new List(name + "a", size));
			g2.setUserObject(new List(name + "b", size));
			current.add(s);
			current.add(g1);
			current.add(g2);
			current.add(w);
		}
	}
	
	private static String readUntilSpace(String s) {
		String toReturn = "";
		int k = 0;
		while(k < s.length() && s.charAt(k) != ' ') {
			toReturn += s.charAt(k);
			k++;
		}
		return toReturn;
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Tree");
		Scanner in = new Scanner(System.in);
		System.out.println("How many levels would you you like to generate?");
		String input = in.next();
		Integer numLevels = -1;
		boolean goodInt = false;
			while(!goodInt) {
				try {
					numLevels = Integer.parseInt(input);
				if(numLevels > 0) goodInt = true;
				}catch(NumberFormatException e) {
				}
				if(!goodInt) {
					System.out.println("Try again");
					input = in.next();
				}
		}
			DefaultMutableTreeNode root = new DefaultMutableTreeNode();
			ArrayList<DefaultMutableTreeNode> list = new ArrayList<DefaultMutableTreeNode>();
			list.add(root);
			for(int i = 0; i < numLevels; i++) {
				ArrayList<DefaultMutableTreeNode> childList = new ArrayList<DefaultMutableTreeNode>();
				for(DefaultMutableTreeNode n : list) {
					generateLevel(n, readUntilSpace(n.toString()), numLevels);
					for(int j = 0; j < n.getChildCount(); j ++) {
						childList.add((DefaultMutableTreeNode) n.getChildAt(j));
					}
				}
				list = childList;
			}

		      JTree tree = new JTree(root);
		      frame.setSize(550,400);
		      frame.setVisible(true);
		      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		      frame.add(tree);
			in.close();
			
	}

}
