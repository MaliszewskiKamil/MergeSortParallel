package com.company;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {

	private static final int SEED = 931353283;
	private static final int ARRAY_SMALL = 8000000;
	private static final int ARRAY_MEDIUM = 16000000;
	private static final int ARRAY_BIG = 32000000;
	private static final int ARRAY_LARGE = 648000000;
	private static final int MAX_RANDOM_NUMBER = 100000000;


	private static Integer[] data_set_small  = new Integer[ARRAY_SMALL];
	private static Integer[] data_set_medium  = new Integer[ARRAY_MEDIUM];
	private static Integer[] data_set_big  = new Integer[ARRAY_BIG];
	private static Integer[] data_set_large  = new Integer[ARRAY_LARGE];
	private static Random random = new Random(SEED);

    public static void main(String[] args) {



		int cores = Runtime.getRuntime().availableProcessors();
		System.out.println(cores);

		prepareData();
		testDataset(data_set_big, cores);

	}

	private static void prepareData(){
		System.out.println("***PREPARING DATASETS***");
		int progress = ARRAY_SMALL;
		for (int i =0; i<data_set_large.length; i++){
//			if(i<data_set_small.length)
//				data_set_small[i] = random.nextInt(MAX_RANDOM_NUMBER);
//			if(i<data_set_medium.length)
//				data_set_medium[i] = random.nextInt(MAX_RANDOM_NUMBER);
			if(i<data_set_big.length)
				data_set_big[i] = random.nextInt(MAX_RANDOM_NUMBER);
//			if(i<data_set_large.length)
//				data_set_large[i] = random.nextInt(MAX_RANDOM_NUMBER);
			if(i>progress-2){
				System.out.println("Finished dataset of size: "+progress);
				progress = progress*2;
			}
		}
	}

	private static void testDataset(Integer[] dataset, int cores){
    	String message;
    	if(cores == 0) {
			message = "-----------STARTING MERGE SORT---------------";
		} else {
    		message = "-------STARTING PARALLEL MERGE SORT----------";
		}


		System.out.println("---------------------------------------------");
		System.out.println(message);
		System.out.println("Testing the dataset of size: "+ dataset.length);
		Integer[] dataset_to_sort = dataset;
		System.out.println("Starting timer");
		long start = System.currentTimeMillis();
		if(cores == 0) {
			MergeSort.sort(dataset_to_sort);
		} else {
			ParallelMergeSort.parallelMergeSort(dataset_to_sort, cores);
		}
		long finish = System.currentTimeMillis();
		System.out.println("Mergesort finished");
		long timeElapsed = finish - start;
		int seconds = (int) (timeElapsed / 1000) % 60 ;
		int minutes = (int) ((timeElapsed / (1000*60)) % 60);
		System.out.println("Time taken:\nMinutes: " + minutes + "\nSeconds: "+ seconds);
		System.out.println("---------------------------------------------");
	}

}
