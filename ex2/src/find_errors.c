
/**
 * @file find_errors.c
 * @author Antonino Incorvaia e Désirée Gaudio
 * @brief Program that confronts a text file with a dictionary and finds the missplelled words.
*/
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "skiplist.h"
#include <time.h>

/**
 * @brief Compares two strings.
 * The comparison is performed using the strcmp function from the standard C library.
 *
 * @param a first string.
 * @param b second string.
 * @return int returns 1 if a is greater than b, 0 if a is equal to b and -1 if a is less than b.
 */
int compar_string(const void *a, const void *b){
    return strcasecmp((char *)a, (char *)b);
}

/**
 * @brief reads words from a file,
 * removes the newline character,
 * creates a new string for each word,
 * and inserts them into the SkipList.
 *
 * @param dictfile pointer to the dictionary file to read.
 * @param list pointer to the list in which the dictfile data will be stored.
 */
void read_dictionary(FILE *dictfile, struct SkipList *list){
    char token[1024];
    printf("Loading dictionary...\n");
    while(fgets(token, 1024, dictfile) != NULL){
        token[strcspn(token, "\n")] = 0;
        char *dictionary_word = malloc((strlen(token)+1)*sizeof(char));
        strcpy(dictionary_word, token);
        insert_skiplist(list, dictionary_word);
    }
    fclose(dictfile);
    printf("Dictionary loaded!\n");

}

/**
 * @brief reads strings from a file,
 * skips non-alphabetic characters,
 * checks if each string is present in the SkipList,
 * and prints the misspelled strings.
 *
 * @param textfile Pointer to the text File in which to search for errors.
 * @param list pointer to the SkipList in which the dictionary file data is stored.
 */
void read_text(FILE *textfile, struct SkipList *list){
    char token[1024];
    printf("Misspelled words:\n");
    while(fscanf(textfile, "%[a-zA-Z]%*[^a-zA-Z]", token) == 1){
        if((char *)search_skiplist(list, token) == NULL){
            printf("%s\n", token);
        }
    }
    fclose(textfile);
}

/**
 * @brief performs error detection in a text file by comparing the words with a dictionary.
 * It uses the read_dictionary and read_text functions.
 *
 * @param dictfile pointer to dictionary file.
 * @param textfile pointer to the text file.
 * @param max_height The maximum number possible of pointers of a Node of the SkipList.
 */
void find_errors(FILE *dictfile, FILE *textfile, size_t max_height){
    struct SkipList *list;
    clock_t start, end;
    double time;
    new_skiplist(&list, max_height, compar_string);
    start = clock();
    read_dictionary(dictfile, list);
    read_text(textfile, list);
    end = clock();
    time = ((double) (end - start)) / CLOCKS_PER_SEC;
    printf("Time taken: %f seconds\n", time);
    clear_skiplist(&list);
}

/**
 * @brief checks the command-line arguments,
 * opens the required files,
 * and calls the find_errors function to perform error detection
 * based on the provided dictionary and text files.
 *
 * @param argc number of expected arguments (4).
 * @param argv pointe to an array of strings that contains the names of the expected arguments.
 * (the program name, the dictionary file, the text file, and the maximum height).
 * @return int 0 if the program ends with succes, 1 if not.
 */
int main(int argc, char * argv[]){
    if(argc!=4){
        printf("Usage: %s <dictionary> <textfile> <max_height>\n", argv[0]);
        return 1;
    }
    FILE *dictionary = fopen(argv[1], "r");
    FILE *textfile = fopen(argv[2], "r");
    char *endptr;
    size_t max_height = strtoul(argv[3], &endptr, 10);

    if (*endptr != '\0') {
        printf("Error: Invalid max_height value or not a positive integer.\n");
        return 1;
    }
    if(dictionary==NULL){
        printf("Cannot open file %s\n", argv[1]);
        return 1;
    }
    if(textfile==NULL){
        printf("Cannot open file %s\n", argv[2]);
        return 1;
    }
    find_errors(dictionary,textfile,max_height);
    return 0;
}
