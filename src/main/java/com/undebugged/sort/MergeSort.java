package com.undebugged.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class MergeSort {

	private static Random random = new Random();

	private MergeSort() {
	}

	public static <T extends Comparable<T>> List<T> mergeSort(List<T> unsortedList) {
		assert unsortedList != null;
		assert unsortedList.size() > 1;
		List<T> sortedList = new ArrayList<T>(unsortedList);
		List<T> helperList = new ArrayList<T>(unsortedList);
		return mergeSort(sortedList, helperList, 0, sortedList.size() - 1);
	}

	public static <T extends Comparable<T>> List<T> mergeSort(List<T> unsortedList, List<T> list, int low, int high) {
		if (low < high) {
			int middle = low + (high - low) / 2;
			mergeSort(unsortedList, list, low, middle);
			mergeSort(unsortedList, list, middle + 1, high);
			list = merge(unsortedList, list, low, middle, high);
		}
		return list;
	}

	private static <T extends Comparable<T>> List<T> merge(List<T> unsortedList, List<T> list, int low, int m, int high) {
		for (int i = low; i <= high; i++) {
			list.set(i, unsortedList.get(i));
		}
		int i = low;
		int j = m + 1;
		int k = low;
		while (i <= m && j <= high) {
			if (list.get(i).compareTo(list.get(j)) <= 0) {
				unsortedList.set(k, list.get(i));
				i += 1;
			} else {
				unsortedList.set(k, list.get(j));
				j += 1;
			}
			k++;
		}
		while (i <= m) {
			unsortedList.set(k, list.get(i));
			k += 1;
			i += 1;
		}
		return unsortedList;
	}

	public static void main(String[] args) {
		List<Integer> unsorted = new ArrayList<Integer>();
		for (int i = 0; i < 50; i++) {
			unsorted.add(Integer.valueOf(random.nextInt(1000)));
		}
		System.out.println("init:      " + unsorted);
		List<Integer> msorted = mergeSort(unsorted);
		System.out.println("sorted:    " + msorted);
		for (int i = 0; i < 50 - 1; i++) {
			if (msorted.get(i + 1).compareTo(msorted.get(i)) < 0) {
				System.out.println("verify:    FAILED");
				break;
			}
		}
	}
}
