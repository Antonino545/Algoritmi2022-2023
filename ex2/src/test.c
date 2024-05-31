/**
 * @file test.c
 * @author Antonino Incorvaia e Désirée Gaudio
 * @brief Program that test the skiplist function.
*/

#include <stdlib.h>
#include "skiplist.h"
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
int compar_string(const void *a, const void *b){
  return strcmp((char *)a, (char *)b);
}

/**
 * @brief This function is to test the insert and search function with integer.
*/
void test_insert_search_int() {
  struct SkipList *list;
  new_skiplist(&list, 5, compar_int);
  int a = 1;
  int b = 2;
  int c = 3;
  int d = 4;
  insert_skiplist(list, &a);
  insert_skiplist(list, &b);
  insert_skiplist(list, &c);
  insert_skiplist(list, &d);
  TEST_ASSERT_EQUAL(a, *( int *)search_skiplist(list, &a));
  TEST_ASSERT_EQUAL(b, *( int *)search_skiplist(list, &b));
  TEST_ASSERT_EQUAL(c, *( int *)search_skiplist(list, &c));
  TEST_ASSERT_EQUAL(d, *( int *)search_skiplist(list, &d));
  clear_skiplist(&list);
  }

/**
 * @brief This function is to test the insert and search function with double.
*/
  void test_insert_search_double() {
  struct SkipList *list;
  new_skiplist(&list, 5, compar_double);
  double a = 1.1;
  double b = 2.2;
  double c = 3.3;
  double d = 4.4;
  insert_skiplist(list, &a);
  insert_skiplist(list, &b);
  insert_skiplist(list, &c);
  insert_skiplist(list, &d);
  TEST_ASSERT_EQUAL(a, *( double *)search_skiplist(list, &a));
  TEST_ASSERT_EQUAL(b, *( double *)search_skiplist(list, &b));
  TEST_ASSERT_EQUAL(c, *( double *)search_skiplist(list, &c));
  TEST_ASSERT_EQUAL(d, *( double *)search_skiplist(list, &d));
  clear_skiplist(&list);
}
/**
 * @brief This function is to test the insert function with string.
*/
void test_insert_search(){
  struct SkipList *list;
  new_skiplist(&list, 10, compar_string);
  insert_skiplist(list, "ciao");
  insert_skiplist(list, "ciao2");
  TEST_ASSERT_EQUAL(10,list->max_height);
  TEST_ASSERT_EQUAL_STRING("ciao",list->heads[0]->item );
  TEST_ASSERT_EQUAL_STRING("ciao2",list->heads[0]->next[0]->item );
  clear_skiplist(&list);
}
/**
 * @brief This function is to test the search function with string.
*/
void test_search_skiplist(){
  struct SkipList *list;

  new_skiplist(&list, 10, compar_string);
  insert_skiplist(list, "ciao");
  insert_skiplist(list, "ciao2");
  TEST_ASSERT_EQUAL_STRING("ciao",search_skiplist(list, "ciao"));
  TEST_ASSERT_EQUAL_STRING("ciao2",search_skiplist(list, "ciao2"));
}
int main (){
  UNITY_BEGIN();
  RUN_TEST(test_insert_search_int);
  RUN_TEST(test_insert_search_double);
  RUN_TEST(test_insert_search);
  RUN_TEST(test_search_skiplist);
  return UNITY_END();
}