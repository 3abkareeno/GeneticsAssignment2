import java.util.Scanner;

public class Simulation {

	public static void main(String[] args) {
Scanner in = new Scanner(System.in);
		
		System.out.print("Enter Population Size: ");
		int popSize = in.nextInt();
		
		System.out.print("Enter Number of generations: ");
		int genNum = in.nextInt();
		
		Population pop = new Population(popSize);
		
		for(int i = 0; i < genNum; i++){
			pop.roulette(i, genNum);
		}
		
		pop.getBestSolution().print();
		
		in.close();

	}

}
