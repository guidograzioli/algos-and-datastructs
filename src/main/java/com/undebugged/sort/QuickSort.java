package com.undebugged.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class QuickSort {

	private static Random random = new Random();

	private QuickSort() {
	}

	public static <T extends Comparable<T>> List<T> quickSort(List<T> unsortedList) {
		assert unsortedList != null;
		assert unsortedList.size() > 1;
		List<T> sortedList = new ArrayList<T>(unsortedList);
		return quickSort(sortedList, 0, sortedList.size() - 1);
	}

	private static <T extends Comparable<T>> List<T> quickSort(List<T> unsortedList, int l, int u) {
		if (l >= u) {
			return unsortedList;
		}
		int rnd = random.nextInt(u - l) + l;
		unsortedList = swap(unsortedList, l, rnd);
		T divide = unsortedList.get(l);
		int i = l + 1, j = u + 1;
		for (;;) {
			while (i <= u && unsortedList.get(i).compareTo(divide) < 0) {
				i = i + 1;
			}
			do {
				j = j - 1;
			} while (unsortedList.get(j).compareTo(divide) > 0);
			if (i >= j) {
				break;
			}
			unsortedList = swap(unsortedList, i, j);
		}
		unsortedList = swap(unsortedList, l, j);
		unsortedList = quickSort(unsortedList, l, j - 1);
		unsortedList = quickSort(unsortedList, j + 1, u);
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
		for (int i = 0; i < 500; i++) {
			unsorted.add(Integer.valueOf(random.nextInt(1000)));
		}
		System.out.println("init:      " + unsorted);
		List<Integer> qsorted = quickSort(unsorted);
		System.out.println("sorted:    " + qsorted);
		for (int i = 0; i < 500 - 1; i++) {
			if (qsorted.get(i + 1).compareTo(qsorted.get(i)) < 0) {
				System.out.println("verify:    FAILED");
				break;
			}
		}
	}
}
