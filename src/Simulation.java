import java.util.Scanner;

public class Simulation {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.print("Enter Population Size: ");
		int popSize = in.nextInt();
		
		System.out.print("Enter Number of generations: ");
		int genNum = in.nextInt();
		
		in.close();
		
		for(int i = 1; i <= 5; i++){
			Population pop = new Population(popSize, "curve" + i + ".txt");
			
			for(int j = 0; j < genNum; j++){
				pop.roulette(j, genNum);
			}
			
			System.out.println("Case " + i);
			pop.getBestSolution().print();
			System.out.println();
			System.out.println();
		}
		
	}

}
