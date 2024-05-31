/**
 * @file test.c
 * @author Antonino Incorvaia e Désirée Gaudio
 * @brief The test program that tests the merge_binary_insertion_sort function.
 * 
 */
#include <stdlib.h>
//! Define the value of K for the merge_binary_insertion_sort function.
#define K 10

#include "merge_binary_insertion_sort.h"
#include<string.h>
#include "unity/src/unity.h"

void setUp(void) {}

void tearDown(void) {}

/**
 * @brief this function is to compare two integers.
 * 
 * @param a the first integer.
 * @param b the second integer.
 * @return  return >0 if a > b, return <0 if a < b, return 0 if a = b.
 */

int compar_int(const void *a, const void *b) {
  return *(int *) a - *(int *) b;
}

/**
 * @brief This function is to compare two char.
 * 
 * @param a The first char.
 * @param b The second char.
 * @return return >0 if a > b, return <0 if a < b, return 0 if a = b. 
 */
int compar_char(const void *a, const void *b) {
  return *(char *) a - *(char *) b;
}

/**
 * @brief This function is to compare two double.
 * 
 * @param a The first double.
 * @param b The second double.
 * @return return >0 if a > b, return <0 if a < b, return 0 if a = b. 
 */
int compar_double(const void *a, const void *b) {
  double diff = *(double *) a - *(double *) b;

  if (diff > 0) {
    return 1;
  } else if (diff < 0) {
    return -1;
  } else {
    return 0;
  }
}


/**
 * @brief This function is to compare two string.
 * 
 * @param a The first string.
 * @param b The second string.
 * @return return >0 if a > b, return <0 if a < b, return 0 if a = b.
 */
int compar_string(const void *a, const void *b) {
  const char **str1 = (const char **) a;
  const char **str2 = (const char **) b;
  return strcmp(*str1, *str2);
}


/**
 * @brief This function is to test the array with one element.
 * 
 */
void test_Array_one_element(void) {
  int array[] = {1};
  int expected[] = {1};

  merge_binary_insertion_sort(array, 1, sizeof(int), K, compar_int);
  TEST_ASSERT_INT_ARRAY_WITHIN(0, expected, array, 1);
}

/**
 * @brief this fuction is to test the array in descending order. 
 */
void array_ordered_reverse(void) {
  int array[] = {9, 8, 7, 6, 5, 4, 3, 2, 1};
  int expected[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};
  merge_binary_insertion_sort(array, 9, sizeof(int), K, compar_int);
  TEST_ASSERT_INT_ARRAY_WITHIN(0, expected, array, 9);
}

/**
 * @brief This function is to test the array in ascending order.
 * 
 */
void test_array_ordered(void) {
  int array[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};
  int expected[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};

  merge_binary_insertion_sort(array, 9, sizeof(int), K, compar_int);
  TEST_ASSERT_INT_ARRAY_WITHIN(0, expected, array, 9);
}

/**
 * @brief This function is to test the array with integer 
 * 
 */
void test_int_array(void) {
  int array[] = {1, 5, 3, 4, 2, 6, 8, 7, 9, 0};
  int expected[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

  merge_binary_insertion_sort(array, 10, sizeof(int), K, compar_int);
  TEST_ASSERT_INT_ARRAY_WITHIN(0, expected, array, 10);
}

/**
 * @brief This function is to test the array with char.
 * 
 */
void test_char_array(void) {
  char array[] = {'a', 'e', 'c', 'd', 'b', 'f', 'h', 'g', 'i', 'j'};
  char expected[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};

  merge_binary_insertion_sort(array, 10, sizeof(char), K, compar_char);
  TEST_ASSERT_CHAR_ARRAY_WITHIN(0, expected, array, 10);
}

/**
 * @brief This function is to test the array with string.
 * 
 */
void test_string_array(void) {
  char *array[] = {"aciao", "eciao", "cciao", "dciao", "bciao", "fciao", "hciao", "gciao", "iciao", "jciao"};
  char *expected[] = {"aciao", "bciao", "cciao", "dciao", "eciao", "fciao", "gciao", "hciao", "iciao", "jciao"};
  merge_binary_insertion_sort(array, 10, sizeof(char *), K, compar_string);
  TEST_ASSERT_CHAR_ARRAY_WITHIN(0, expected, array, 10);
}

/**
 * @brief This function is to test the array with double.
 * 
 */
void test_double_array(void) {
  double array[] = {1.1, 5.5, 3.3, 4.4, 2.2, 6.6, 8.8, 7.7, 9.9, 0.0};
  double expected[] = {0.0, 1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7, 8.8, 9.9};

  merge_binary_insertion_sort(array, 10, sizeof(double), K, compar_double);
  for (size_t i = 0; i < 10; i++) {
    TEST_ASSERT_EQUAL(expected[i], array[i]);
  }
}


int main() {
  UNITY_BEGIN();
  RUN_TEST(test_Array_one_element);
  RUN_TEST(array_ordered_reverse);
  RUN_TEST(test_array_ordered);
  RUN_TEST(test_int_array);
  RUN_TEST(test_char_array);
  RUN_TEST(test_double_array);
  RUN_TEST(test_string_array);
  return UNITY_END();
}
