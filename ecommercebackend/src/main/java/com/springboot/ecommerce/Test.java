package com.springboot.ecommerce;

import java.util.Scanner;

public class Test {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int m = sc.nextInt();
		for(int i=1;i<=10;i++) {
			System.out.println(m + "*" + i + " = " + (m*i));
		}
		sc.close();
	}
}
