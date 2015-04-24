package com.undebugged.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class HeapSort {

	private static Random random = new Random();

	private HeapSort() {
	}

	public static <T extends Comparable<T>> List<T> heapSort(List<T> unsortedList) {
		assert unsortedList != null;
		assert unsortedList.size() > 1;
		List<T> sortedList = new ArrayList<T>(unsortedList);
		for (int i = 1; i < sortedList.size(); i++) {
			sortedList = siftUp(sortedList, i);
		}
		for (int i = sortedList.size() - 1; i >= 1; i--) {
			sortedList = swap(sortedList, 0, i);
			sortedList = siftDown(sortedList, i - 1);
		}
		return sortedList;
	}

	private static <T extends Comparable<T>> List<T> siftUp(List<T> unsortedList, int n) {
		assert n > 0;
		for (int i = n;;) {
			if (i == 0) {
				break;
			}
			int p = i / 2;
			if (unsortedList.get(p).compareTo(unsortedList.get(i)) >= 0) {
				break;
			}
			unsortedList = swap(unsortedList, i, p);
			i = p;
		}
		return unsortedList;
	}

	private static <T extends Comparable<T>> List<T> siftDown(List<T> unsortedList, int n) {
		for (int i = 0;;) {
			int c = 2 * (i + 1) - 1;

			if (c > (n)) {
				break;
			}
			if (c + 1 <= (n)) {
				if (unsortedList.get(c + 1).compareTo(unsortedList.get(c)) > 0) {
					c += 1;
				}
			}
			if (unsortedList.get(i).compareTo(unsortedList.get(c)) >= 0) {
				break;
			}
			unsortedList = swap(unsortedList, i, c);
			i = c;

		}
		return unsortedList;
	}

	private static <T> List<T> swap(List<T> list, int firstIndex, int secondIndex) {
		assert list != null;
		assert list.size() > 0;
		assert 0 <= firstIndex;
		assert firstIndex < list.size();
		assert 0 <= secondIndex;
		assert secondIndex < list.size();
		if (firstIndex == secondIndex) {
			return list;
		}
		T tmpT = list.get(secondIndex);
		list.set(secondIndex, list.get(firstIndex));
		list.set(firstIndex, tmpT);
		return list;
	}

	public static void main(String[] args) {
		List<Integer> unsorted = new ArrayList<Integer>();
		for (int i = 0; i < 7; i++) {
			unsorted.add(Integer.valueOf(random.nextInt(1000)));
		}
		System.out.println("init:         " + unsorted);
		List<Integer> hsorted = heapSort(unsorted);
		System.out.println("sorted:       " + hsorted);
		for (int i = 0; i < 7 - 1; i++) {
			if (hsorted.get(i + 1).compareTo(hsorted.get(i)) < 0) {
				System.out.println("verify:    FAILED");
				break;
			}
		}
	}
}
