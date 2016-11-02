import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Chromosome {
	private ArrayList<Float> genes = new ArrayList<>();
	private float fitness;
	
	public Chromosome(int degree){
		for(int i = 0; i < degree+1; i++){
			float randomCoeff = (float) (Math.random() * (10 - (-10)) + (-10));
			genes.add(randomCoeff);
		}
	}
	
	public Chromosome(Chromosome x){
		for(Float p : x.getItemsArray()){
			genes.add(p);
		}
		this.fitness = x.fitness;
	}
	
	public ArrayList<Float> getItemsArray(){
		return genes;
	}
	
	public float getFitness(){
		return fitness;
	}
	
	public void setGene(int index, float value){
		genes.set(index, value);
	}
	
	public float getGene(int index){
		return genes.get(index);
	}
	
	public void calculateFitness(Curve curve){
		float error = 0;
		for(int i = 0; i < curve.getPointsNum(); i++){
			Point current = curve.points.get(i);
			float ycalc = genes.get(0);
			for(int j = 1; j <= curve.getDegree(); j++){
				ycalc += (genes.get(j) * Math.pow(current.x, j));
			}
			error += Math.pow((ycalc-current.y), 2);
		}
		fitness = 1/((1/curve.getPointsNum())*error);
	}
	
	public void mutate(int currentGen, int maxGen){
		for(int i = 0; i < genes.size(); i++){
			float current = genes.get(i);
			float r = ThreadLocalRandom.current().nextFloat();
			float pm = (float) 0.1;
			
			if(r <= pm){
				float y;
				float random = ThreadLocalRandom.current().nextFloat();
				if(random <= 0.5){
					y = current + (float) 10.0;
				}else{
					y = (float) 10.0 - current;
				}
				r = ThreadLocalRandom.current().nextFloat();
				float amount = (float) (y * (1 - Math.pow(r, (1-(currentGen/maxGen)))));
				float newCoeff= 0;
				
				if(random <= 0.5){
					newCoeff = current - amount;
				}else{
					newCoeff = current + amount;
				}
				genes.set(i, newCoeff);
			}
		}
	}
	
	public void print(){
		for(int i = 0; i < genes.size(); i++){
			System.out.print(genes.get(i) + "  ");
		}
	}
}
