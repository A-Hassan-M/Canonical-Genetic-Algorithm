import java.util.ArrayList;
import java.util.Random;

public class FloatingPointGA{
	private ArrayList<Solution> population;
	private ArrayList<Point> points;
	private int degree;
	private final double P_MUTAION = 0.001;
	private final double P_COSSOVER = 0.68;
	private final int POPULATION_SIZE = 1000;
	private final int GENERATION_SIZE = 200;
	
	
	public FloatingPointGA(ArrayList<Point> points,int degree) {
	    population = new ArrayList<>();
		this.points = points;
		this.degree = degree;
	}
	
	public ArrayList<Solution> solve() {
		int i =0;
		generatePopulation();
		while (i++<GENERATION_SIZE) {
			calculateFitness();
			double[] rouletteWheel = getRouletteWheel();
			ArrayList<Solution> selectedSols = select(rouletteWheel);
			Xover(selectedSols);
			mutate(selectedSols,i);
			replace(selectedSols);
		}
		calculateFitness();
		return population;
	}

	
	public Solution getBest() {
	    Solution best = population.get(0);
	    for(Solution solution:population)
            best = solution.getFitness()>best.getFitness()?solution:best;

		return best;
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
				squareError += (hY - points.get(j).y  )*(hY - points.get(j).y );
			}
			
			squareError = (1.0*squareError)/points.size();
			fitness = 1.0/(squareError == 0?1:squareError);
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
		double[] rouletteWheel = new double[population.size()];
		double cumulativeFitness = 0;
		for (int i = 0; i < population.size(); i++) {
			cumulativeFitness += population.get(i).getFitness();
			rouletteWheel[i] = cumulativeFitness;
		}
		return rouletteWheel;
	}

	
	private ArrayList<Solution> select(double[] rouletteWheel) {
		ArrayList<Solution> selectedSols = new ArrayList<>();
		double maxFitness = rouletteWheel[population.size() - 1];
		Random randGenerator = new Random();
		for (int i = 0; i < population.size(); i++) {
			double r = maxFitness*randGenerator.nextDouble();
			for (int j = 0; j < rouletteWheel.length; j++) {
				if (r <= rouletteWheel[j] && (j == 0 || r > rouletteWheel[j - 1])) {
					Solution selectedSol = new Solution();
					selectedSol.setChromosome(population.get(j).getChromosome());
					selectedSol.setFitness(population.get(j).getFitness());
					selectedSol.setParentIndex(j);
					selectedSols.add(selectedSol);
					break;
				}
			}
		}
		return selectedSols;
	}

	
	private ArrayList<Solution> Xover(ArrayList<Solution> selectedSols) {
		for (int i = 0; i < selectedSols.size() - 1; i += 2) {
			int chromosomeSize = selectedSols.get(i).getChromosome().size();
			Random randGenerator = new Random();
			int x = randGenerator.nextInt(chromosomeSize);
			double r = randGenerator.nextDouble();
			if (r >= P_COSSOVER) {
				continue;
			}
			x = (x == 0 ? 1 : x);
			ArrayList<Double> child1 = new ArrayList<Double>();
			ArrayList<Double> child2 = new ArrayList<Double>();
			for (int j = 0; j < chromosomeSize; j++) {
				child1.add(selectedSols.get(j<x ?i:i+1).getChromosome().get(j));
				child2.add(selectedSols.get(j<x ?i+1:i).getChromosome().get(j));
			}
			
			selectedSols.get(i).setChromosome(child1);
			selectedSols.get(i+1).setChromosome(child2);
		}
		return selectedSols;
	}

	

	private ArrayList<Solution> mutate(ArrayList<Solution> offSpring, int genration_Num) {
		ArrayList<Solution> Mutation = new ArrayList<>();
		for (int i = 0; i < offSpring.size(); i++) {
			Solution solution = offSpring.get(i);
			Random randGenerator = new Random();
				for (int j = 0; j < solution.getChromosome().size(); j++) {
                    double P = randGenerator.nextDouble();
                    if (P <= P_MUTAION) {
				    double x = solution.getChromosome().get(j);
					double r = randGenerator.nextDouble();
					double delta, amountOfMutation;
					double b = 0.5;
					if (r >= 0.5) {
                        double tmp = Math.pow(randGenerator.nextDouble(), Math.pow((1 - (genration_Num*1.0 / GENERATION_SIZE)), b));
						delta = Math.abs(10 - x);
						amountOfMutation = delta * (1 - tmp);
						solution.getChromosome().set(j, solution.getChromosome().get(j) + amountOfMutation);
					} else {
                        double tmp = Math.pow(randGenerator.nextDouble(), Math.pow((1 - (genration_Num *1.0/ GENERATION_SIZE)), b));
						delta = Math.abs(x + 10);
						amountOfMutation = delta * (1 - tmp);
						solution.getChromosome().set(j, solution.getChromosome().get(j) - amountOfMutation);
					}

				}

			}
			Mutation.add(solution);
		}

		return Mutation;
	}


	
	private void replace(ArrayList<Solution> mutated) {

//        Solution best = mutated.get(0);
        for (Solution aMutated : mutated) {
            if (aMutated.getFitness() >= population.get(aMutated.getParentIndex()).getFitness())
                population.set(aMutated.getParentIndex(), aMutated);
//            else
//                population.set(aMutated.getParentIndex(),aMutated);
//        }
//		if(population.get(best.getParentIndex()).getFitness()<best.getFitness())
//		    population.set(best.getParentIndex(),best);
//
        }
	}

}
