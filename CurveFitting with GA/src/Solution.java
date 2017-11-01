import java.util.ArrayList;

public class Solution {
	private double fitness;
	private ArrayList<Double> chromosome;
	private int parentIndex;
	
	
	public Solution(){
		parentIndex = -1;
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
	public void setParentIndex(int parentIndex) {
		this.parentIndex = parentIndex;
	}
	
	//Getters
	public double getFitness() {
		return fitness;
	}
	public ArrayList<Double> getChromosome() {
		return chromosome;
	}
	
	public int getParentIndex() {
		return parentIndex;
	}
}
