
public class wine {

	//array to hold the values that make up the wine instant
	private double[] features = new double[13];
	//class of the wine instant
	private int Class;
	
	
	//class wine made up of 13 variables of type double and a class of type int being 1,2 or 3
	public wine(double Alcohol, double Malic_acid, double Ash, 
				double Alcalinity_of_ash, 	double Magnesium,
				double Total_phenols, double Flavanoids, 
				double Nonflavanoid_phenols, double Proanthocyanins,
				double Color_intensity, double Hue, 
				double OD2802FOD315_of_diluted_wines, double Proline, int Class ) {
		
				this.features[0] = Alcohol;
				this.features[1] = Malic_acid;
				this.features[2] = Ash;
				this.features[3] = Alcalinity_of_ash;
				this.features[4] = Magnesium;
				this.features[5] = Total_phenols;
				this.features[6] = Flavanoids;
				this.features[7] = Nonflavanoid_phenols;
				this.features[8] = Proanthocyanins;
				this.features[9] = Color_intensity;
				this.features[10] = Hue;
				this.features[11] = OD2802FOD315_of_diluted_wines;
				this.features[12] = Proline;
				
				this.Class = Class;
				
	}
	//returns the actual class of the wine instants
	public int getName() {
		return Class;
	}
	//sets the class of the wine instant to the int i
	public void setName(int i){
		this.Class = i;
	}
	//returns the whole features array
	public double[] feature() {
		return this.features;
	}
	//gets a specific value from the array at position i
	public double getFeature(int i) {
		return this.features[i];
	}
}


