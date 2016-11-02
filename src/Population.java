import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Population {
	private ArrayList<Chromosome> population = new ArrayList<Chromosome>();
	private int populationSize;
	Curve curve;
	
	public Population(int populationSize) {
		super();
		curve = new Curve("curve1.txt");
		this.populationSize = populationSize;
		init();
	}
	
	private void init (){
		for(int i = 0; i < populationSize; i++){
			population.add(new Chromosome(curve.getDegree()));
			population.get(i).calculateFitness(curve);
		}
	}
	
	public void sort(){
		for (int i = 0; i < populationSize-1; i++) {
		      for (int j = 0; j < populationSize-i-1; j++) {
				if (population.get(j).getFitness() > population.get(j+1).getFitness()) {
				  Chromosome swap = population.get(j);
				  population.set(j, population.get(j+1));
				  population.set(j+1, swap);
				}
		      }
		}
	}
	
	private void crossover(Chromosome c1, Chromosome c2, int currentGen, int maxGen){
		int length = curve.getDegree() + 1;
		int crossoverIndex = ThreadLocalRandom.current().nextInt(length-1)+1;
		float pCross = ThreadLocalRandom.current().nextFloat();
		
		Chromosome offspring1 = new Chromosome(c1);
		Chromosome offspring2 = new Chromosome(c2);
		
		if(pCross <= 0.7){
				for(int i = crossoverIndex+1; i < length; i++){
					offspring1.setGene(i, c2.getGene(i));
					offspring2.setGene(i, c1.getGene(i));
				}
			// Mutation
			offspring1.mutate(currentGen, maxGen);
			offspring2.mutate(currentGen, maxGen);
			
			offspring1.calculateFitness(curve);
			offspring2.calculateFitness(curve);
		}
		
		population.add(offspring1);
		population.add(offspring2);
	}
	
	public void roulette(int currentGen, int maxGen){
		float fitnessSum = 0;
		for(int i = 0; i < populationSize; i++){
			fitnessSum += population.get(i).getFitness();
		}
		
		float randomSelector = (float) (Math.random() * (fitnessSum-1));
		float comulativeSum = 0;
		
		Chromosome selection1 = null;
		for(int i = 0; i < populationSize; i++){
			comulativeSum += population.get(i).getFitness();
			if(randomSelector < comulativeSum){
				selection1 = new Chromosome(population.get(i));
				population.remove(i);
				break;				
			}
		}		
		fitnessSum -= selection1.getFitness();
		randomSelector = (float) (Math.random() * (fitnessSum-1));
		comulativeSum = 0;
		
		Chromosome selection2 = null;
		for(int i = 0; i < populationSize-1; i++){
			comulativeSum += population.get(i).getFitness();
			if(randomSelector < comulativeSum){
				selection2 = new Chromosome(population.get(i));
				population.remove(i);
				break;				
			}
		}
		
		crossover(selection1,selection2, currentGen, maxGen);
	}
	
	public Chromosome getBestSolution(){
		sort();
		return population.get(populationSize-1);
	}
}
