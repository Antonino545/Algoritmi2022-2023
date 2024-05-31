/**
 * @file merge_binary_insertion_sort.h
 * @mainpage Merge Binary Insertion Sort 
 * @brief The merge binary insertion merge_binary_insertion_sort algorithm is a sorting algorithm that combines the merge merge_binary_insertion_sort algorithm with the binary insertion merge_binary_insertion_sort algorithm.\n\n
 * @ref merge_binary_insertion_sort.h is the file that contains the prototype of the merge binary insertion merge_binary_insertion_sort algorithm.\n
 * @ref merge_binary_insertion_sort.c is the file that contains the implementation of the merge binary insertion merge_binary_insertion_sort algorithm.\n
 * @ref client.c is the client program that sorts the records in the input file based on the specified field and writes the sorted records to the output file.\n
 * @ref test.c is the file that contains the test of the merge binary insertion merge_binary_insertion_sort algorithm.\n
 * @author Antonino Incorvaia e Désirée Gaudio
 */
#ifndef merge_binary_insertion_sort_h
#define merge_binary_insertion_sort_h
/**
 * @brief  Merge Binary Insertion Sort is an enhanced sorting algorithm that combines 
 * the concepts of Merge Sort and Binary Insertion Sort. It divides the input array 
 * into smaller subarrays, applies Merge Sort on those subarrays, 
 * and then performs Binary Insertion Sort on the resulting subarrays to achieve
 *  a more efficient and optimized sorting process.
 * @param base The base address of the first element in the array to be sorted.
 * @param nitems The number of elements in the array pointed to by base.
 * @param size The size in bytes of each element in the array.
 * @param k the pararameter used to decide if we need to use the merge merge_binary_insertion_sort or the binary insertion merge_binary_insertion_sort
 * @param compar  The function that compares two elements.
 */
void merge_binary_insertion_sort(void *base, size_t nitems, size_t size, size_t k, int (*compar)(const void *, const void*));

#endif 