import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Demo {
	public static void main(String args[]) throws FileNotFoundException {
		FloatingPointGA CGA;
		int numOfCases;

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(new File("input_exp.txt"));
		numOfCases = sc.nextInt();
		int c = 0;
		while (c++ < numOfCases) {
			int numOfPoints, degree;
			ArrayList<Point> items = new ArrayList<>();
			numOfPoints = sc.nextInt();
			degree = sc.nextInt();

			for (int i = 0; i < numOfPoints; i++) {
				items.add(new Point(sc.nextDouble(), sc.nextDouble()));
			}

			CGA = new FloatingPointGA(items, degree);
			ArrayList<Solution> solutions = CGA.solve();
			System.out.println("Case: " + c );
//			for(Solution solution : solutions) {
//			    System.out.println(solution.getFitness());
//                for (Double gene : solution.getChromosome()){
//                    System.out.print(gene + " ");
//                    if (gene == 1)
//                        System.out.println("Found 1");
//                }
//                System.out.println();
//			}
            System.out.println(CGA.getBest().getChromosome()+" "+(1.0/CGA.getBest().getFitness()));
		}

	}

}
