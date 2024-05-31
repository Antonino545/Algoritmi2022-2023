/**
 * @file skiplist.c
 * @author Antonino Incorvaia e Désirée Gaudio
 * @brief Library that implements a skiplist.
*/

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "skiplist.h"

/**
 * @brief Assign to the node a random number of pointers, which is always less than max_height.
 * 
 * @param max_height Maximum number of pointers assigned to a node.
 * @return size_t Number of pointers assigned to this node.
 */
size_t random_level(size_t max_height){
  size_t lvl = 1;
  while ((double)rand() / RAND_MAX < 0.5 && lvl < max_height) {
    lvl = lvl + 1;
  }
  return lvl;
}

/**
 * @brief (Creates and initializes a new node for the skipList.)
 *  creates a new node, assigns the provided item and size, allocates memory for the next array of pointers,
 *  initializes the array with NULL pointers, and returns the newly created node.
 *
 * @param size Number of pointers assigned to this node.
 * @param item Pointer to the element to insert in the node.
 * @return struct Node* Newly created node.
 */
struct Node *new_node(size_t size, void *item){
  struct Node *new_node = malloc(sizeof(struct Node));
  if (new_node == NULL) {
    printf("memory allocation error\n");
    return NULL;
  }
  new_node->item = item;
  new_node->size = size;
  new_node->next = malloc(sizeof(struct Node *) * size);
  if (new_node->next == NULL) {
    free(new_node);
    printf("memory allocation error\n");
    return NULL;
  }
  for (size_t i = 0; i < size; i++)
    new_node->next[i] = NULL;
  return new_node;
}
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
void  new_skiplist(struct SkipList **list, size_t max_height, int (*compar)(const void*, const void*)){
  *list = malloc(sizeof(struct SkipList));
  if (*list == NULL) {
    printf("errore di allocazione della memoria\n");
    return;
  }
  (*list)->heads = malloc(sizeof(struct Node *) * max_height);
  if ((*list)->heads == NULL) {
    free(*list);
    printf("errore di allocazione della memoria\n");
    return;
  }
  for (size_t i = 0; i < max_height; i++){
    (*list)->heads[i] = NULL;
  }
  (*list)->max_height = max_height;
  (*list)->max_level = 0;
  (*list)->compare = compar;
}
/**
 * @brief frees the memory for each node and its next array,
 * deallocates the memory for the heads array and the SkipList itself,
 * and sets the list pointer to NULL.
 *
 * @param list double pointer to this SkipList
 */
void clear_skiplist(struct SkipList **list){
  struct Node *x = (*list)->heads[0];
  struct Node *next;
  while (x != NULL) {
    next = x->next[0];
    free(x->next);
    free(x);
    x = next;
  }
  free((*list)->heads);
  free(*list);
  *list = NULL;
}
/**
 * @brief  performs the necessary checks, creates a new node, determines its level,
 * and inserts it into the appropriate positions within the skip list using the function compar().
 *
 * @param list is the pointer to the skiplist.
 * @param item is the pointer to the element to insert.
 */
void insert_skiplist(struct SkipList *list, void *item){
  if(item == NULL) {
    printf("item is null\n");
    return;
  }
  if(list==NULL){
    printf("list is null\n");
    return;
  }
  struct  Node *new = new_node(random_level(list->max_height), item);
  if (new->size > list->max_level)
    list->max_level = new->size;
  struct Node **x = list->heads;
  for (int k = (int) list->max_level-1; k >= 0; k--){
    if(x[k] == NULL||list->compare(item,(x[k]->item)) <=0){
      if (k <(int ) new->size){
        new->next[k] = x[k];
        x[k] = new;
      }
    }else{
      x= x[k]->next;
      k++;
    }
 if(k==0)
    break;


  }
}
/**
 * @brief performs the necessary checks, searches the skip list from the highest to the lowest level,
 * ignoring the nodes whose items are less than the target item,
 * and returns a pointer to the item if found or NULL otherwise.
 *
 * @param list Pointer to the skiplist.
 * @param item Pointer to the element to search.
 * @return const void* Pointer to the searched element.
 */
const void* search_skiplist(struct SkipList *list, void *item){
  if (item == NULL) {
    printf("item is null\n");
    return NULL;
  }
  if (list == NULL) {
    printf("list is null\n");
    return NULL;
  }

  struct Node **x = list->heads;

  for (int i =(int)  list->max_level - 1; i >= 0; i--) {

    while (x[i] != NULL && list->compare((x[i]->item), item) < 0) {
      x = x[i]->next;
    }
   if(i==0)
    break;
  }

  if (x[0] != NULL && list->compare((x[0]->item), item) == 0) {
    return x[0]->item;
  } else {
    return NULL;
  }
}

