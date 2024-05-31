/**
 * @file merge_binary_insertion_sort.c
 * @author Antonino Incorvaia e Désirée Gaudio
 * @brief The merge binary insertion merge_binary_insertion_sort algorithm is a sorting algorithm that combines the merge merge_binary_insertion_sort algorithm with the binary insertion merge_binary_insertion_sort algorithm.
 * @copyright Copyright (c) 2023
 * 
 */
#include<stdio.h>
#include<stdlib.h>
#include<string.h>

/**
 * @brief Binary search is a search algorithm that finds the position of a target value within a sorted array.
 * @param base The base address of the first element in the array to be sorted.
 * @param nitems The number of elements in the array pointed to by base.
 * @param size The size in bytes of each element in the array.
 * @param compar The function that compares two elements.
 * @param key The key to be searched for.
 * @param left The leftmost index of the subarray.
 * @param right The rightmost index of the subarray.
 * @return the position in which the element needs to be inserted 
 */
size_t binary_search(void *base, void *key, size_t left, size_t right, size_t size,
                     int (*compar)(const void *, const void *)) {
  if (left < right) {
    size_t mean = left + (right - left) / 2;
    if (compar(key, base + mean * size) < 0) {
      return binary_search(base, key, left, mean, size, compar);
    } else {
      return binary_search(base, key, mean + 1, right, size, compar);
    }
  } else {
    return left;
  }
}

void binary_insertion_sort(void *base, size_t nitems, size_t size, int (*compar)(const void *, const void *)) {
  for (size_t j = 1; j < nitems; j++) {
    void *key = malloc(size);
    memcpy(key, base + j * size, size);
    if (!key) {
      printf("Allocation error\n");
      return;
    }
    size_t pos = binary_search(base, base + j * size, 0, j, size, compar);
    memmove(base + (pos + 1) * size, base + pos * size, size * (j - pos));
    memcpy(base + pos * size, key, size);
    free(key);
  }
}

/**
 * @brief the merge fuction is used to merge two subarrays of the array.
 * 
 * @param base The base address of the first element in the array to be sorted.
 * @param left The leftmost index of the subarray.
 * @param mid The middle index of the subarray.
 * @param right The rightmost index of the subarray.
 * @param size The size in bytes of each element in the array.
 * @param compar The function that compares two elements.
 */
void merge(void *base, size_t left, size_t mid, size_t right, size_t size, int (*compar)(const void *, const void *)) {
  size_t i, j;
  size_t nitems1 = mid - left;
  size_t nitems2 = right - mid;
  void *L = malloc(nitems1 * size);
  void *R = malloc(nitems2 * size);

  if (!L || !R) {
    printf("Allocation error\n");
    return;
  }
  for (i = 0; i < nitems1; i++)
    memcpy(L + i * size, base + (left + i) * size, size);
  for (j = 0; j < nitems2; j++)
    memcpy(R + j * size, base + (mid + j) * size, size);
  i = 0;
  j = 0;
  for (size_t q = left; q < right; q++) {
    if (j >= nitems2 || (i < nitems1 && compar(L + i * size, R + j * size) <= 0)) {
      memcpy(base + q * size, L + i * size, size);
      i++;
    } else {
      memcpy(base + q * size, R + j * size, size);
      j++;
    }
  }
  free(L);
  free(R);
}

void merge_binary_insertion_sort(void *base, size_t nitems, size_t size, size_t k,
                                 int (*compar)(const void *, const void *)) {
  if (base == NULL || nitems == 0 || size == 0 || compar == NULL || nitems == 1) {
  } else if (nitems <= k) {
    binary_insertion_sort(base, nitems, size, compar);
  } else {
    size_t mid = nitems / 2;
    merge_binary_insertion_sort(base, mid, size, k, compar);
    merge_binary_insertion_sort(base + (mid) * size, (nitems - mid), size, k, compar);
    merge(base, 0, mid, nitems, size, compar);
  }

}