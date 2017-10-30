import java.util.ArrayList;
import java.util.Random;

public class CanonicalGA extends GA {
	
	public CanonicalGA(ArrayList<Item> items, int sizeOfKS) {
		super(items, sizeOfKS);
	}
	
	protected boolean isValid(String child) {
		int weight =0 ;
		for(int i=0;i<items.size();i++) {
			weight += items.get(i).getWeight() * (child.charAt(i)-'0');
		}
		return weight <= sizeOfKS;
	}

	protected Solution getBest() {
		Solution best = population.get(0);
		for (Solution sol : population) {
			if (sol.getFitness() >= best.getFitness() && sol.getWeight() <= sizeOfKS)
				best = sol;
		}
		return best;

	}

	protected void generatePopulation() {

		for (int i = 0; i < POPULATION_SIZE; i++) {

			Random randGenerator = new Random();
			String temp = "";
			for (int j = 0; j < items.size(); j++) {
				if (randGenerator.nextDouble() <= 0.5) {
					temp += '1';
				} else
					temp += '0';
			}
			if(!isValid(temp)) {
				i--;
				continue;
			}
			population.add(new Solution(0, temp));
		}
		
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

	protected void calculateFitness() {

		for (int i = 0; i < population.size(); i++) {
			String solution = population.get(i).getChromosome();
			double fitness = 0;
			int weight = 0, value = 0, numOfItems = 0;
			for (int j = 0; j < solution.length(); j++) {
				int bit = solution.charAt(j) - '0';
				fitness += items.get(j).getValue() * bit/items.get(j).getWeight()*1.0;
				weight += items.get(j).getWeight() * bit;
				value += items.get(j).getValue() * bit;
				numOfItems += bit;
			}

			population.get(i).numOfItems = numOfItems;
			population.get(i).setValue(value);
			population.get(i).setWeight(weight);
			population.get(i).setFitness((weight > sizeOfKS ? 0: fitness));
		}

	}

	protected double[] getRouletteWheel() {
		double[] rouletteWheel = new double[population.size()];
		double cumulativeFitness = 0;
		for (int i = 0; i < population.size(); i++) {
			cumulativeFitness += population.get(i).getFitness();
			rouletteWheel[i] = cumulativeFitness;
		}
		return rouletteWheel;
	}

	protected ArrayList<Solution> select(double[] rouletteWheel) {
		ArrayList<Solution> selectedSols = new ArrayList<>();
		double maxFitness = rouletteWheel[population.size() - 1];
		Random randGenerator = new Random();
		for (int i = 0; i < population.size(); i++) {
			int r = randGenerator.nextInt((int)maxFitness);
			for (int j = 0; j < rouletteWheel.length; j++) {
				if (r <= rouletteWheel[j] && (j == 0 || r > rouletteWheel[j - 1])) {
					selectedSols.add(population.get(j));
					break;
				}
			}
		}
		return selectedSols;
	}

	protected ArrayList<Solution> Xover(ArrayList<Solution> selectedSol) {
		ArrayList<Solution> offSpring = new ArrayList<>();
		for (int i = 0; i < selectedSol.size() - 1; i += 2) {
			int chromosomeSize = selectedSol.get(i).getChromosome().length();
			Random randGenerator = new Random();
			int x = randGenerator.nextInt(chromosomeSize);
			double r = randGenerator.nextDouble();
			if (r >= P_COSSOVER) {
				offSpring.add(selectedSol.get(i));
				offSpring.add(selectedSol.get(i+1));
				continue;
			}
			x = (x == 0 ? 1 : x);
			String child1 = "", child2 = "";
			for (int j = 0; j < x; j++) {
				child1 += selectedSol.get(i).getChromosome().charAt(j);
				child2 += selectedSol.get(i + 1).getChromosome().charAt(j);
			}
			for (int j = x; j < chromosomeSize; j++) {
				child1 += selectedSol.get(i + 1).getChromosome().charAt(j);
				child2 += selectedSol.get(i).getChromosome().charAt(j);
			}
			if(!isValid(child1)||!isValid(child2)) {
				i-=2;
				continue;
			}
			offSpring.add(new Solution(0, child1));
			offSpring.add(new Solution(0, child2));
		}
		return offSpring;
	}

	protected ArrayList<Solution> mutate(ArrayList<Solution> offSpring) {
		for (int i = 0; i < offSpring.size(); i++) {
			Random randGenerator = new Random();
			String Chromosome = offSpring.get(i).getChromosome();
			String tmp = "";
			for (int j = 0; j < Chromosome.length(); j++) {
				double r = randGenerator.nextDouble();

				if (r <= P_MUTAION) {
					tmp += (Chromosome.charAt(j) == '1') ? '0' : '1';
				} else
					tmp += Chromosome.charAt(j);
			}
			if(!isValid(tmp)) {
				i--;
				continue;
			}
			offSpring.get(i).setChromosome(tmp);

		}
		return offSpring;
	}

	protected void replace(ArrayList<Solution> mutated) {
		population = mutated;
	}
}
