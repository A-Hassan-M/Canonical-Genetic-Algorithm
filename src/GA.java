import java.util.ArrayList;

public abstract class GA {
	protected ArrayList<Solution> population;
	protected ArrayList<Item> items;
	protected int sizeOfKS;
	protected final double P_MUTAION = 0.01;
	protected final double P_COSSOVER = 0.64;
	protected final int POPULATION_SIZE = 1000;
	protected final int GENERATION_SIZE = 700;

	public GA(ArrayList<Item> items, int sizeOfKS) {
		population = new ArrayList<>();
		this.items = items;
		this.sizeOfKS = sizeOfKS;
	}
	
	protected abstract boolean isValid(ArrayList<Double> child) ;

	protected abstract Solution getBest() ;

	protected abstract void generatePopulation() ;
	
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

	protected abstract void calculateFitness();

	protected abstract double[] getRouletteWheel() ;
	protected abstract ArrayList<Solution> select(double[] rouletteWheel) ;
	protected abstract ArrayList<Solution> Xover(ArrayList<Solution> selectedSol) ;

	protected abstract ArrayList<Solution> mutate(ArrayList<Solution> offSpring) ;

	protected abstract void replace(ArrayList<Solution> mutated) ;
}
