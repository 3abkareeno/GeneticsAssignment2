import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Point{
	float x;
	float y;
	public Point(float x, float y) {
		super();
		this.x = x;
		this.y = y;
	}
}

public class Curve {
	ArrayList<Point> points = new ArrayList<>();
	private int pointsNum;
	public int getPointsNum() {
		return pointsNum;
	}

	public void setPointsNum(int pointsNum) {
		this.pointsNum = pointsNum;
	}

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	private int degree;

	public Curve(String filename){
		Scanner input = null;
		try {
			input = new Scanner (new File(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		pointsNum = input.nextInt();
		degree = input.nextInt();

		while(input.hasNextLine()){
			input.nextLine();
			float x = input.nextFloat();
			float y = input.nextFloat();
			points.add(new Point(x,y));
		}	
	}

	public void print(){
		System.out.println("Degree = " + degree);
		System.out.println("Number of points = " + pointsNum);
		for(int i = 0; i < pointsNum; i++){
			System.out.println("(" + points.get(i).x + ", " + points.get(i).y + ")");
		}
	}
}
