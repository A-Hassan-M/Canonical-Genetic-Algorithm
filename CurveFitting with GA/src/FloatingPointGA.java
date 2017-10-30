import java.util.ArrayList;

public class FloatingPointGA{
	protected ArrayList<Solution> population;
	protected ArrayList<Point> points;
	protected int degree;
	protected final double P_MUTAION = 0.01;
	protected final double P_COSSOVER = 0.64;
	protected final int POPULATION_SIZE = 1000;
	protected final int GENERATION_SIZE = 700;
	public FloatingPointGA(ArrayList<Point> points,int degree) {
		this.points = points;
		this.degree = degree;
	}

	
	protected boolean isValid(ArrayList<Double> child) {
		// TODO Auto-generated method stub
		return false;
	}

	
	protected Solution getBest() {
		// TODO Auto-generated method stub
		return null;
	}

	
	protected void generatePopulation() {
		// TODO Auto-generated method stub
		
	}

	
	protected void calculateFitness() {
		
	}

	
	protected double[] getRouletteWheel() {
		// TODO Auto-generated method stub
		return null;
	}

	
	protected ArrayList<Solution> select(double[] rouletteWheel) {
		// TODO Auto-generated method stub
		return null;
	}

	
	protected ArrayList<Solution> Xover(ArrayList<Solution> selectedSol) {
		// TODO Auto-generated method stub
		return null;
	}

	
	protected ArrayList<Solution> mutate(ArrayList<Solution> offSpring) {
		// TODO Auto-generated method stub
		return null;
	}

	
	protected void replace(ArrayList<Solution> mutated) {
		// TODO Auto-generated method stub
		
	}

}