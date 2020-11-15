package com.company;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {

	private static final int SEED = 931353283;
	private static final int ARRAY_SIZE = 60000000;
	private static final int MAX_RANDOM_NUMBER = 100000000;
    public static void main(String[] args) {


		Random random = new Random(SEED);


		Integer[] data_set  = new Integer[ARRAY_SIZE];
		for (int i =0; i<data_set.length; i++){
			data_set[i] = random.nextInt(MAX_RANDOM_NUMBER);
		}
		System.out.println("First 30 integers in the dataset: ");
		for (int i = 0; i<30; i++){
			System.out.println(data_set[i]);
		}
		long start = System.currentTimeMillis();
		System.out.println("Starting mergesort");
		MergeSort.sort(data_set);
		System.out.println("Mergesort finished");
		long finish = System.currentTimeMillis();
		long timeElapsed = finish - start;


		int seconds = (int) (timeElapsed / 1000) % 60 ;
		int minutes = (int) ((timeElapsed / (1000*60)) % 60);

		System.out.println("Time taken:\nMinutes: " + minutes + "\nSeconds: "+ seconds);




	}
}
