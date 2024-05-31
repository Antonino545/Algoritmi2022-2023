/**
 * @file client.c
 * @author Antonino Incorvaia e Désirée Gaudio
 * @brief The client program that sorts the records in the input file based on the specified field and writes the sorted records to the output file.
 * 
 */
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>
#include "merge_binary_insertion_sort.h"

/**
 * @brief The record structure is a structure that contains the fields of the csv file.
 * 
 */
typedef struct {
    //! The id field of the csv file.
    int id;
    //! The string field of the csv file.
    char *string;
    //! The integer field of the csv file.
    int integer;
    //! The floating point field of the csv file.
    double floating_point;
} Record;

/**
 * @brief This function is to compare two integers in the Record struct.
 * @param a parameter a is a first integer of the Record struct.
 * @param b parameter b is a second integer of the Record struct.
 * @return return >0 if a > b, return <0 if a < b, return 0 if a = b.
 */
int compar_int_record(const void *a, const void *b) {
  Record *ia = (Record *) a;
  Record *ib = (Record *) b;
  return (ia->integer - ib->integer);
}

/**
 * @brief This function is to compare two string in the Record struct.
 * @param a parameter a is a first string of the Record struct.
 * @param b parameter b is a second string of the Record struct.
 * @return return >0 if a > b, return <0 if a < b, return 0 if a = b.
 */
int compar_string_record(const void *a, const void *b) {
  Record *ia = (Record *) a;
  Record *ib = (Record *) b;

  return strcmp(ia->string, ib->string);
}

/**
 * @brief This function is to compare two double in the Record struct.
 * @param a parameter a is a first double of the Record struct.
 * @param b parameter b is a second double of the Record struct.
 * @return return >0 if a > b, return <0 if a < b, return 0 if a = b.
 */
int compar_double_record(const void *a, const void *b) {
  Record *ia = (Record *) a;
  Record *ib = (Record *) b;
  if (ia->floating_point > ib->floating_point) {
    return 1;
  } else if (ia->floating_point < ib->floating_point) {
    return -1;
  } else {
    return 0;

  }
}

/**
 * @brief This function write_csv is responsible for writing 
 * an array of 'Record' structures to a CSV file specified by the outfile parameter
 * 
 * @param outfile  The output file where the sorted records will be written.
 * @param records  The records to be written to the output file.
 * @param num_rows The number of records to be written to the output file.
 */
void write_csv(FILE *outfile, Record *records, size_t num_rows) {
  printf("Writing to file...\n");

  if (outfile == NULL) {
    printf("Error opening file\n");
    return;
  }
  for (size_t i = 0; i < num_rows; i++) {
    fprintf(outfile, "%d,%s,%d,%lf\n", records[i].id, records[i].string, records[i].integer, records[i].floating_point);
  }
  fclose(outfile);
}

/**
 * @brief This function read_csv is responsible for reading and parsing
 * and saves the records from the input file specified by the infile parameter
 * in an array of 'Record' structures.
 * @param infile  The input file containing the records to be sorted.
 * @param records The records to pass to the  fuction merge_binary_insertion_sort to be sorted.
 * @return  The number of records read from the input file.
 */
size_t read_csv(FILE *infile, Record **records) {
  size_t num_rows = 0;
  char line[1024];
  while (fgets(line, sizeof(line), infile)) {

    *records = realloc(*records, (size_t)((num_rows + 1) * sizeof(Record)));
    char *token;
    token = strtok(line, ",");
    (*records)[num_rows].id = atoi(token);
    token = strtok(NULL, ",");
    (*records)[num_rows].string = malloc(strlen(token) + 1);
    strcpy((*records)[num_rows].string, token);
    token = strtok(NULL, ",");
    (*records)[num_rows].integer = atoi(token);
    token = strtok(NULL, ",");
    (*records)[num_rows].floating_point = atof(token);
    num_rows++;
  }
  fclose(infile);
  return num_rows;
}

/**
 * @brief Sorts the records in the input file based on the specified field and writes the sorted records to the output file.
 * @param infile The input file containing the records to be sorted.
 * @param k the pararameter used to decide if we need to use the merge merge_binary_insertion_sort or the binary insertion merge_binary_insertion_sort
 * @param outfile The output file where the sorted records will be written.
 * @param field The field by which the records should be sorted (1 for string, 2 for integer, 3 for double).
 */
void sort_records(FILE *infile, FILE *outfile, size_t k, size_t field) {
  clock_t start, end;
  Record *records = NULL;
  size_t num_line = read_csv(infile, &records);
  printf("The file with column number %ld will be ordered by ", num_line);
  start = clock();
  switch (field) {
    case 1:
      printf("string\n");
      merge_binary_insertion_sort(records, num_line, sizeof(Record), k, compar_string_record);
      break;
    case 2:
      printf("integer\n");
      merge_binary_insertion_sort(records, num_line, sizeof(Record), k, compar_int_record);
      break;
    case 3:
      printf("double\n");
      merge_binary_insertion_sort(records, num_line, sizeof(Record), k, compar_double_record);
      break;
  }
  end = clock();
  write_csv(outfile, records, num_line);
  printf("The file was ordered in %lf seconds\n", ((double) (end - start)) / CLOCKS_PER_SEC);
  for (size_t i = 0; i < num_line; i++) {
    free(records[i].string);
  }
  free(records);
  return;
}


/**
 * @brief This is the main function of the program. 
 * It takes in 5 arguments: the input file, the output file, the number of records to be sorted, 
 * the field by which the records should be sorted, and the k value used 
 * to determine when to switch from merge sort to binary insertion sort.
 * @param argc The number of arguments passed to the program.
 * @param argv The arguments passed to the program.
 * @return int 0 if the program runs successfully, 1 if the program fails.
 */
int main(int argc, char *argv[]) {
  if (argc < 5) {
    printf("Error in the number of arguments\n");
    exit(EXIT_FAILURE);

  }
  if (!(atoi(argv[4]) > 0 && atoi(argv[4]) < 4)) {
    printf("Error in the field number\n");
    exit(EXIT_FAILURE);
  }
  FILE *input = fopen(argv[1], "r");
  FILE *output = fopen(argv[2], "w");
  if (input == NULL) {
    perror("Error opening file input");
    exit(EXIT_FAILURE);
  }
  if (output == NULL) {
    perror("Error opening file output ");
    exit(EXIT_FAILURE);
  }
  printf("The file %s will be ordered by column number %s\n", argv[1], argv[4]);
  sort_records(input, output, strtoul(argv[3], NULL, 10), strtoul(argv[4], NULL, 10));
  return 0;
}