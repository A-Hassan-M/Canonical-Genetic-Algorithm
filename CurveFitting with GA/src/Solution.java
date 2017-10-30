import java.util.ArrayList;

public class Solution {
	private double fitness;
	private ArrayList<Double> chromosome;
	private double error;
	
	
	public Solution(){
	}
	public Solution(double fitness, ArrayList<Double> chromosome) {
		this.fitness = fitness;
		this.chromosome = chromosome;
	}
	
	//Setters
	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
	public void setChromosome(ArrayList<Double> chromosome) {
		this.chromosome = chromosome;
	}
	public void setError(double error) {
		this.error = error;
	}
	
	//Getters
	public double getFitness() {
		return fitness;
	}
	public ArrayList<Double> getChromosome() {
		return chromosome;
	}
	public double getError() {
		return error;
	}

}
