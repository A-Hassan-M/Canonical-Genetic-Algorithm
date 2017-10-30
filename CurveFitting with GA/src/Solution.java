import java.util.ArrayList;

public class Solution {
	private double fitness;
	private ArrayList<Double> chromosome;
	private int value;
	private int weight;
	int numOfItems;
	
	
	public Solution(){
		value = 0;
		weight = 0;
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
	public void setValue(int value) {
		this.value = value;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	//Getters
	public double getFitness() {
		return fitness;
	}
	public ArrayList<Double> getChromosome() {
		return chromosome;
	}
	public int getValue() {
		return value;
	}
	public int getWeight() {
		return weight;
	}

}
