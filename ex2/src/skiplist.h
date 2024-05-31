/**
 * @file skiplist.h
 * @mainpage Skiplist
 * @ref find_errors.c 
 * @ref skiplist.c
 * @ref test.c
 * @author Antonino Incorvaia e Désirée Gaudio
 * @brief Library that implements a skiplist. 
 * 
*/

#ifndef SKIPLIST_H
#define SKIPLIST_H
#include <stdlib.h>

/**
 * @brief A skip list data structure that contains any number of Nodes of any type.
*/
struct SkipList {
  //! The array of pointers to the heads of the SkipList.
  struct Node **heads;
  //! The maximum number of pointers of a single Node in the Skiplist at the moment.
  size_t max_level;
  //! The maximum number possible of pointers of a Node of the SkipList.
  size_t max_height;
  //! A function pointer that represents the comparison function used to order the elements in the skip list. 
  int (*compare)(const void*, const void*);
};

/**
 * @brief  A node in a skip list data structure.
*/
struct Node {
  //! Array of double pointers to the nodes of a SkipList.
  struct Node **next;
  //! Number of pointers in the Node of a Skiplist.
  size_t size;
  //! Pointer to the data of the node.
  void *item;
};

/**
 * @brief  creates and initializes a new skip list, 
 * allocates memory for the SkipList structure and the heads array, 
 * initializes the heads array with NULL pointers, 
 * and sets the other properties of the SkipList.
 * 
 * @param list double pointer to the SkipList 
 * @param max_height is the maximum numberof pointers of the SkipList.
 * @param compar is the function used to compair the elements.
 */
void new_skiplist(struct SkipList **list, size_t max_height, int (*compar)(const void*, const void*));

/**
 * @brief frees the memory for each node and its next array, 
 * deallocates the memory for the heads array and the SkipList itself, 
 * and sets the list pointer to NULL. 
 * 
 * @param list double pointer to this SkipList
 */
void clear_skiplist(struct SkipList **list);
/**
 * @brief  performs the necessary checks, creates a new node, determines its level, 
 * and inserts it into the appropriate positions within the skip list using the function compar().
 * 
 * @param list is the pointer to the skiplist.
 * @param item is the pointer to the element to insert.
 */
void insert_skiplist(struct SkipList *list, void *item);
/**
 * @brief performs the necessary checks, searches the skip list from the highest to the lowest level, 
 * ignoring the nodes whose items are less than the target item, 
 * and returns a pointer to the item if found or NULL otherwise.
 * 
 * @param list Pointer to the skiplist.
 * @param item Pointer to the element to search.
 * @return const void* Pointer to the searched element.
 */
const void* search_skiplist(struct SkipList *list, void *item);
#endif 