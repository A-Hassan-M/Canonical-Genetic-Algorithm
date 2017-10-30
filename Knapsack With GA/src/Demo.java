import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Demo {
	public static void main(String args[]) throws FileNotFoundException {
		CanonicalGA CGA;
		int numOfCases;

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(new File("./input_exp.txt"));
		numOfCases = sc.nextInt();
		int c = 0;
		while (c++ < numOfCases) {
			int numOfItems, sizeOfKS;
			ArrayList<Item> items = new ArrayList<Item>();
			numOfItems = sc.nextInt();
			sizeOfKS = sc.nextInt();

			for (int i = 0; i < numOfItems; i++) {
				items.add(new Item(sc.nextInt(), sc.nextInt()));
			}

			CGA = new CanonicalGA(items, sizeOfKS);
			Solution solution = CGA.solve();
			System.out.println("Case: " + c + ' ' + solution.getValue()+'\n'+solution.numOfItems);
			for(int i=0;i<items.size();i++) {
				if(solution.getChromosome().charAt(i) == '1')
					System.out.println(items.get(i).toString());
			}
		}
	}

}
