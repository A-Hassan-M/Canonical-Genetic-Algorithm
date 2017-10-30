import java.util.ArrayList;
import java.util.Random;

public class FloatingPointGA{
	private ArrayList<Solution> population;
	private ArrayList<Point> points;
	private int degree;
	private final double P_MUTAION = 0.01;
	private final double P_COSSOVER = 0.64;
	private final int POPULATION_SIZE = 1000;
	private final int GENERATION_SIZE = 700;
	
	
	public FloatingPointGA(ArrayList<Point> points,int degree) {
		this.points = points;
		this.degree = degree;
	}
	
	public Solution solve() {
		int i =0;
		generatePopulation();
		while (i++<GENERATION_SIZE) {
			calculateFitness();
			double[] rouletteWheel = getRouletteWheel();
			ArrayList<Solution> selectedSol = select(rouletteWheel);
			ArrayList<Solution> offSpring = Xover(selectedSol);
			ArrayList<Solution> mutated = mutate(offSpring);
			replace(mutated);
		}
		calculateFitness();
		return getBest();
	}

	
	private Solution getBest() {
		// TODO Auto-generated method stub
		return null;
	}

	
	private void generatePopulation() {
		for (int i = 0; i < POPULATION_SIZE; i++) {

			Random randGenerator = new Random();
			ArrayList<Double> temp = new ArrayList<Double>();
			for (int j = 0; j <= degree; j++) {
				double randCell = -10 + (20) * randGenerator.nextDouble();
				temp.add(randCell);
			}
			population.add(new Solution(0, temp));
		}
		
	}

	
	private void calculateFitness() {
		for (int i = 0; i < population.size(); i++) {
			ArrayList<Double> solution = population.get(i).getChromosome();
			double fitness = 0, squareError = 0;
			
			for (int j = 0; j < points.size(); j++) {
				double hY = func(points.get(j),solution);
				squareError += (points.get(j).y - hY)*(points.get(j).y - hY);
			}
			
			squareError = (1.0/points.size())*squareError;
			fitness = 1.0/squareError;
			
			population.get(i).setError(squareError);
			population.get(i).setFitness(fitness);

		}
	}

	
	private double func(Point point, ArrayList<Double> solution) {
		double y = solution.get(0);
		for(int i=1;i<solution.size();i++) {
			y += solution.get(i)*Math.pow(point.x,i);
		}
		return y;
	}

	private double[] getRouletteWheel() {
		// TODO Auto-generated method stub
		return null;
	}

	
	private ArrayList<Solution> select(double[] rouletteWheel) {
		// TODO Auto-generated method stub
		return null;
	}

	
	private ArrayList<Solution> Xover(ArrayList<Solution> selectedSol) {
		// TODO Auto-generated method stub
		return null;
	}

	
	private ArrayList<Solution> mutate(ArrayList<Solution> offSpring) {
		// TODO Auto-generated method stub
		return null;
	}

	
	private void replace(ArrayList<Solution> mutated) {
		// TODO Auto-generated method stub
		
	}

}