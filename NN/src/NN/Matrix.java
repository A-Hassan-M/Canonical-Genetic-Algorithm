package NN;

import java.util.ArrayList;

public class Matrix<Type> {
	private ArrayList<ArrayList<Type>> Mat;
	
	

	public Matrix() {
		super();
		Mat = new ArrayList<ArrayList<Type>>();
	}
	
	public Matrix(int x,int y) {
		Mat = new ArrayList<ArrayList<Type>>();
		for (int i = 0; i < x; i++) {
			Mat.add(new ArrayList<Type>(y));
		}
	}

	public ArrayList<ArrayList<Type>> getMat() {
		return Mat;
	}

	public void setMat(ArrayList<ArrayList<Type>> mat) {
		Mat = mat;
	}
	
	

}
