import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class List {
	//Cached list to improve performance
	private static Map<String, ArrayList<Integer>> mapL1 = new TreeMap<String, ArrayList<Integer>>();
	private static Map<String, ArrayList<Integer>> mapL2 = new TreeMap<String, ArrayList<Integer>>();

	String name = "";
	int i = -1;
	int j = -1;
	ArrayList<Integer> L1 = new ArrayList<Integer>();
	ArrayList<Integer> L2 = new ArrayList<Integer>();
	ArrayList<Integer> newL1 = new ArrayList<Integer>();
	ArrayList<Integer> newL2 = new ArrayList<Integer>();


	public List(String n, int totalSize) {

		name = n;

		if(name.length() > 1) {
			L1 = mapL1.get(name.substring(0, name.length()-1));
			L2 = mapL2.get(name.substring(0, name.length()-1));
			System.out.println(name);
			this.newL1 = copy(L1);
			this.newL2 =  copy(L2);
		}
		calculateI(L1,L2);
		calculateJ(L2);
		if(name.charAt(name.length() - 1) == 's' || name.charAt(name.length() - 1) == 'w') {
			L2.add(pair(i,j));
		}
		if(name.charAt(name.length() - 1) == '0') {
			newL1.add(i);
		}
		if(name.charAt(name.length() - 1) == '1' || name.charAt(name.length() - 1) == 'b' || name.charAt(name.length() - 1) == 'a' ) {
			newL1.add(i);
			for(int k = 0; k < totalSize; k ++) {
				newL2.add(pair(i, k));
			}
		}
		calculateI(newL1, newL2);
		calculateJ(newL2);
		mapL1.put(name, newL1);
		mapL2.put(name, newL2);
 	}
	
	private ArrayList<Integer> copy(ArrayList<Integer> l) {
		ArrayList<Integer> copy = new ArrayList<Integer>();
		for(Integer x : l) {
			//destroy refrence
			int x1 = x;
			copy.add(x1);
		}
		return copy;
	}

	private static int pair(int x, int y) {
		double x1 = x;
		double y1 = y;
		return (int) (x1 + Math.pow((y1 + Math.floor((x1+1)/2) ),2));
	}
	
	private void calculateI(ArrayList<Integer> L1, ArrayList<Integer> L2) {
		if(name.length() == 0) {
			i = 0;
		}else {
			if(name.length() %2 == 1) {
				i = getMinNotIn(L1);
			}else{
				i = invertPairX(getMinNotIn(L2));
			}
		}
	}
	
	
	public int invertPairX(int z) {
		double z1 = z;
		return (int) (z1 - Math.pow(Math.floor(Math.sqrt(z)),2));
	}
	
	
	
	public int invertPairY(int z) {
		double z1 = z;
		return (int) (Math.floor(Math.sqrt(z1)) - (invertPairX(z)+1)/2);
	}
	
	
	private void calculateJ(ArrayList<Integer> L2) {
		if(name.length() == 0) {
			i = 0;
		}else {
				j = invertPairY(getMinNotIn(L2));
			}
		}
	
	
	private static int getMin(ArrayList<Integer> l) {
		if(l.size() == 0) return 0;
		int min = l.get(0);
		for(Integer x : l) {
			if(x < min) min = x;
		}
		return min;
	}
	
	private static int getMax(ArrayList<Integer> l) {
		if(l.size() == 0) return 0;
		int max = l.get(0);
		for(Integer x : l) {
			if(x > max) max = x;
		}
		return max;
	}
	
	private static int getMinNotIn(ArrayList<Integer> l) {
		if(l.size() == 0) return 0;
		ArrayList<Integer> bigList = new ArrayList<Integer>();
		int big = getMax(l);
		for(int k = 0; k < (big+2); k++) {
			bigList.add(k);
		}

		for(Integer x : l) {
			bigList.remove(x);
		}
		return getMin(bigList);
	}
	

	public int getI() {
		return i;
	}
	
	public int getJ() {
		if(name.length() % 2 != 0) {
			System.out.println("Fatal error");
			System.exit(0);
		}
		if(i > -1) {
			return j;
		}else {
			
		}
		return 0;
	}
	
	@Override
	public String toString() {
		if(name.length() % 2 == 1) {
			return name+ " i: "+ i;
		}else {
			return name+ " <i,j>: <"+i+","+j+">";
		}
	}

}
