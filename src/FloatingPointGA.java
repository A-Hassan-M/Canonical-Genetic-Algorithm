import java.util.ArrayList;

public class FloatingPointGA extends GA {

	public FloatingPointGA(ArrayList<Item> items, int sizeOfKS) {
		super(items, sizeOfKS);
	}

	@Override
	protected boolean isValid(ArrayList<Double> child) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected Solution getBest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void generatePopulation() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void calculateFitness() {
		
	}

	@Override
	protected double[] getRouletteWheel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ArrayList<Solution> select(double[] rouletteWheel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ArrayList<Solution> Xover(ArrayList<Solution> selectedSol) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ArrayList<Solution> mutate(ArrayList<Solution> offSpring) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void replace(ArrayList<Solution> mutated) {
		// TODO Auto-generated method stub
		
	}

}