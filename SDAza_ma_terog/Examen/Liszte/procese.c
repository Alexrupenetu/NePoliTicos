#include<stdio.h>
#include<stdlib.h>

typedef struct proces{
	int id;
	int prio;
	struct proces *next;
}proces;

typedef struct multilista{
	struct proces *sublista;
	struct proces *last;
}multilista;

multilista prioritati[8];

proces *stiva = NULL;

void init()
{
	for(int i=0; i<8; i++)
	{
		prioritati[i].sublista = NULL;
		prioritati[i].last = NULL;
	}
}

void adaugare_proces(int id, int prio)
{
	proces *new_p = (proces*)malloc(sizeof(proces));
	
	if(new_p)
	{
		new_p->id = id;
		new_p->prio = prio;
		new_p->next = NULL;
		
		if(prioritati[prio-1].sublista==NULL)
		{
			prioritati[prio-1].sublista = new_p;
			prioritati[prio-1].last = new_p;
		}
		else
		{
			prioritati[prio-1].last->next = new_p;
			prioritati[prio-1].last = new_p;
		}
	}
	else
	{
		printf("caca");
		exit(1);
	}
}

void afisare()
{
	for(int i=0; i<8; i++)
	{
		printf("P%d: ", i+1);
		if(prioritati[i].sublista)
		{
			proces* aux = prioritati[i].sublista;
			while(aux)
			{
				printf("proces(%d) ", aux->id);
				aux = aux->next;
			}
		}
		printf("\n");
	}
}


void adaugare_stiva(int id, int prio)
{
	proces *p = (proces*) malloc(sizeof(proces));
	
	if(p)
	{
		p->id = id;
		p->prio = prio;
		p->next = NULL;
		
		if(stiva == NULL)
			stiva = p;
		else
		{
			p->next = stiva;
			stiva = p;
		}
	}
	else
	{
		printf("caca");
		exit(1);
	}
}

void afisare_stiva()
{
	printf("Stiva: \n");
	
	proces* aux = stiva;
			while(aux)
			{
				printf("proces(%d) cu P%d\n", aux->id, aux->prio);
				aux = aux->next;
			}
	if(aux==NULL)
		printf("Stiva e goala! \n");
	printf("\n");
	
}

void distributie()
{
	while(stiva)
	{
		adaugare_proces(stiva->id, stiva->prio);
		stiva = stiva->next;
	}
	
}
int main()
{
	adaugare_proces(1, 1);
	adaugare_proces(2, 1);
	adaugare_proces(1, 2);
	adaugare_proces(1, 6);
	afisare();
	adaugare_stiva(4, 2);
	adaugare_stiva(2, 5);
	adaugare_stiva(3, 5);
	afisare_stiva();
	printf("----------------\n");
	distributie();
	afisare();
	afisare_stiva();
	return 0;
}
