package com.shangyong.thzlqb;

public class Test {

	private int A;
	private int B;
	private int C;
	private int D;
	
	private int E;
	private int F;
	private int M;
	private int N;
	
	public static void main(String[] args) {
		Test test= new Test();
		System.out.println("A="+(1400-test.getB()-test.getC()-test.getD()));
//		System.out.println("B="+(1400-test.getA()-test.getC()-test.getD()));
//		System.out.println("C="+(1400-test.getA()-test.getB()-test.getD()));
//		System.out.println("D="+(1400-test.getA()-test.getB()-test.getC()));
//		System.out.println("E="+(600-test.getF()-test.getM()-test.getN()));
//		System.out.println("F="+(600-test.getE()-test.getM()-test.getN()));
//		System.out.println("M="+(600-test.getE()-test.getF()-test.getN()));
//		System.out.println("N="+(600-test.getE()-test.getF()-test.getM()));

	}
	
	
	private int getA() {
		return 1400-getE();
	}
	
	private int getB() {
		return 300-getF();
	}
	
	private int getC() {
		return 200-getM();
	}
	
	private int getD() {
		return 100-getN();
	}
	
	private int getE() {
		return 1400-getA();
	}
	
	private int getF() {
		return 300-getB();
	}
	
	private int getM() {
		return 200-getC();
	}
	
	private int getN() {
		return 100-getD();
	}
	
}
