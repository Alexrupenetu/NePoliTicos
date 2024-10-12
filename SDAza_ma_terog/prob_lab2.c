/*Sa se creeze seturi de stive pt cv chestie atelier auto. Fiecare masina are un nr inmatriculare, an fabricatie si model.*/
// add - adauga masina in stiva (doar daca nu mai e )
// remove - scoate masina aia 
// count - nr de masini din set
// Unice dupa nr de inmatriculare!! 

#include<stdio.h>
#include<stdlib.h>
#include<string.h>

#define MAX_SIZE 5
#define CHUNK 10


typedef struct{
	char nr_inmat[12];
	int an_fab;
	char model[16];
}date;

typedef struct{
	int size;
	date stiva[MAX_SIZE];
}vroom;


vroom *set = NULL;

void initialise(int chunk)
{
	for(int i=0; i<chunk; i++)
		set[i].size = 0;
}

int exista(vroom *set, char *string)
{
	for(int i=0; i<set->size; i++)
		if(strcmp(set->stiva[i].nr_inmat, string)==0)
			return 0;
	return 1;
}

void adaugare_masina(vroom *set, char *string, char *string2, int an)
{
	if(set->size == 0)
	{
		strcpy(set->stiva[0].nr_inmat, string);
		strcpy(set->stiva[0].model, string2);
		set->stiva[0].an_fab = an;
		set->size = set->size + 1;
	}
	else
	{
		if(exista(set, string)!=0 && set->size<=MAX_SIZE)
		{
			
			for(int i=set->size; i>0; i--)
				set->stiva[i] = set->stiva[i-1];
			
			strcpy(set->stiva[0].nr_inmat, string);
			strcpy(set->stiva[0].model, string2);
			set->stiva[0].an_fab = an;
			set->size = set->size + 1;
		}
		else
		{
			printf("Stiva este plina!\n");
		}
	}
}

void eliminare_masina(vroom *set) //nu are rost sa transmit ceva ca parametru, fiind stiva pot elimina doar ultimul element adaugat
{
	if(set->size)
	{
		for(int i=0; i<set->size-1; i++)
			set->stiva[i] = set->stiva[i+1];
		set->size-=1;
	}
	else
	{
		printf("Stiva este goala!\n");
	}
}

void display(vroom *set)
{
	for(int i=0; i<set->size; i++)
		{
			printf("Masina %d: ", i);
			printf("Nr inmat: %s\n", set->stiva[i].nr_inmat);
			printf("Model: %s\n", set->stiva[i].model);
			printf("An fab: %d\n", set->stiva[i].an_fab);
		}
}

void count(vroom set)
{
	printf("Size = %d\n", set.size);
}

int main()
{
	set = malloc(CHUNK * sizeof(vroom));
	if(set == NULL)
	{
		printf("Alocarea a esuat!\n");
		return -1;
	}
	
	initialise(CHUNK);
	adaugare_masina(&set[0], "TM06EHL", "Opel", 2004);
	adaugare_masina(&set[0], "TM06EHL", "Opel", 2004);
	adaugare_masina(&set[0], "TM05ESC", "Opel Astra", 2005);
	adaugare_masina(&set[0], "CS69JHK", "Audi", 2017);
	adaugare_masina(&set[0], "AR89SET", "Porsche", 1998);
	count(set[0]);
	display(&set[0]);
	printf("--------------------\n");
	eliminare_masina(&set[0]);
	count(set[0]);
	display(&set[0]);
	
	return 0;
}


