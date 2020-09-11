import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class knn {

	//lists to store the test and training data sets
	private ArrayList<wine> testingList = new ArrayList<wine>();
	private ArrayList<wine> trainingList = new ArrayList<wine>();
	
	//list of the predicted classes for each instance in the test file
	private ArrayList<Integer> predicted = new ArrayList<Integer>();
	
	//K value represents the number of neighbors to find for a given wine instance 
	private final int k = 1;
	
	//number of correctly predicted classes
	double correct =0;
	
	
	private void Knn(String test, String training) {
		
		
		testingList = load(new File(test));
		trainingList = load(new File(training));
		
			//this block goes through all the nodes in the testing set and gets the predicted class and 
	    	//compares it to its actual class, counting and printing accuracy
		for(int i =0; i < testingList.size(); i++) {
			wine[] neighbours = findNeighbours(testingList.get(i), k);
			int predicedClass = detirmineClass(neighbours);
			predicted.add(predicedClass);
			int a = testingList.get(i).getName();
			if(a == predicedClass){
				correct++;
			}
			
			System.out.println( "actual Class --	"+testingList.get(i).getName() + "		" + predicedClass+ " --	Predicted Class");
			
		}
		System.out.println(correct + "	correct");
		System.out.println(testingList.size() + "	size of data set");
		System.out.println((correct/testingList.size()) * 100 + " % ");
	}
	
 	//loads in the file passed to it and creates a wine object consisting of a line in the file
	public ArrayList<wine> load(File file){
		
		ArrayList<wine> list = new ArrayList<wine>();
		Scanner sc;
		try {
			sc = new Scanner(file);
			while (sc.hasNext()) {
			sc.nextLine();
			double x1 = sc.nextDouble();
			double x2 = sc.nextDouble();
			double x3 = sc.nextDouble();
			double x4 = sc.nextDouble();
			double x5 = sc.nextDouble();
			double x6 = sc.nextDouble();
			double x7 = sc.nextDouble();
			double x8 = sc.nextDouble();
			double x9 = sc.nextDouble();
			double x10 = sc.nextDouble();
			double x11 = sc.nextDouble();
			double x12 = sc.nextDouble();
			double x13 = sc.nextDouble();
			int wineClass = sc.nextInt();
			wine w = new wine(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13, wineClass);
			list.add(w);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	//finds the k nearest neighbors of the current wine instance
	public wine[] findNeighbours(wine testWine, int k){
		ArrayList<wineCompare> distances = new ArrayList<>();
		double distance;
		int size = testWine.feature().length;
		
		for(int i = 0; i < trainingList.size(); i++){
			distance = euclideanDistance(testWine, trainingList.get(i), size);
			distances.add(new wineCompare((wine) trainingList.get(i), distance));
		}
		Collections.sort(distances);
		wine[] neighbours = new wine[k];
		
		for(int i =0; i < k; i++){
			neighbours[i] = distances.get(i).Wine;
		}

		return neighbours;
	}
	
	
	//finds the distance between the passed node and all the other nodes in the training set
	public double euclideanDistance(wine A, wine B, int size){
		double dist =0;
		for(int i =0; i < size; i ++){
			dist+= Math.pow(A.getFeature(i) - B.getFeature(i), 2) / Math.pow(findRange(i),2); 
		}
		
		double eDist = Math.sqrt(dist);
		return eDist;	
	}
	
	
	// finds the range (max value - min value) of the current feature
	public double findRange(int featureIndex){
		double min = 10000;
		double max = 0;
		for(wine Wine : trainingList) {
			if(max < Wine.getFeature(featureIndex)) {
				max = Wine.getFeature(featureIndex);
			}
			if(min > Wine.getFeature(featureIndex)) {
				min = Wine.getFeature(featureIndex);
			}
		}
		double range = max - min;
		return range;
		
	}
	
	
	//predicts the class of a wine node by finding the most common class of the K surounding nodes
	public int detirmineClass(wine[] neighbours){
		
		int classOne =0;
		int classTwo =0;
		int classThree =0;
		
		
		for(int i = 0; i<neighbours.length; i++){
			if(neighbours[i].getName() == 1){
				classOne++;
			}
			if(neighbours[i].getName() == 2){
				classTwo++;
			}
			if(neighbours[i].getName() == 3){
				classThree++;
			}
		}
		
		
		if(classOne > classTwo && classOne > classThree){
			return 1;
		} if(classTwo > classOne && classTwo > classThree){
			return 2;
		}
		if(classThree > classOne && classThree > classTwo){
			return 3;
		}
		 
		return 0;
	}
	
	
	//compare class used to sort the wine instances based on the distance 
	class wineCompare implements Comparable<wineCompare>{

		wine Wine;
		double number;
		
		wineCompare(wine Wine, double number){
			this.number = number;
			this.Wine = Wine;
		}
		
		@Override
		public int compareTo(wineCompare w) {
			// TODO Auto-generated method stub
			if(number <= w.number) {
				return -1;
				}else {
					return 1;
				}
		}
	}
	
	
	public static void main(String[] args) {
		new knn().Knn(args[0], args[1]);
		
	}

}
