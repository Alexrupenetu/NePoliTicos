#include<stdio.h>
#include<stdlib.h>

#define SIZE 8

typedef struct eveniment{
	int ID; 
	int prio;
	int timp;
	struct eveniment *next;
	struct eveniment *last;
}eveniment;

typedef struct lista{
	eveniment *sublista;
}lista;

eveniment *coada = NULL;

lista prioritate[SIZE];

void init()
{
	for(int i=0; i<8; i++)
	{
		prioritate[i].sublista = NULL;
	}
}



void adauga_eveniment(lista *prioritate, int prio, int ID, int timp)
{
	eveniment *new_event = (eveniment*) malloc(sizeof(eveniment));
	
	if(new_event)
	{
		new_event->ID = ID;
		new_event->prio = prio;
		new_event->timp = timp;
		new_event->next = NULL;
		
		if(prioritate[prio-1].sublista==NULL)
		{
			prioritate[prio-1].sublista = new_event;
			prioritate[prio-1].sublista->last = new_event;
		}
		else
		{
			if(prioritate[prio-1].sublista->timp > timp)
			{
				new_event->next = prioritate[prio-1].sublista;
				prioritate[prio-1].sublista = new_event;
			}
			else
			{
				eveniment *aux = prioritate[prio-1].sublista;
				
				while(aux!=NULL && aux->next->timp < timp)
					aux = aux->next;
					
				if(aux->next!=NULL)
				{
				new_event->next = aux->next;
				aux->next = new_event;
				}
				else
				{
					aux->last->next = new_event;
					aux->last = new_event;
					
				}
			}
		}
	}
}


void afisare()
{
	for(int i=0; i<8; i++)
	{
		printf("P%d: \n", i+1);
		eveniment* aux = prioritate[i].sublista;
		while(aux)
		{
			printf("Event ID: %d  Time: %d\n", aux->ID, aux->timp);
			aux = aux->next;
		}
	
	}
	printf("\n");
}


void deservire_eveniment(lista *prioritate)
{
	for(int i=7; i>=0; i--)
	{
		if(prioritate[i].sublista)
		{
			if(coada == NULL)
			{
				coada = prioritate[i].sublista;
				coada->last = prioritate[i].sublista;
			}
			else
			{
				coada->last->next = prioritate[i].sublista;
				coada->last = prioritate[i].sublista;
			}
			
			prioritate[i].sublista = prioritate[i].sublista->next;
			return;
		}
	}
}

void afisare_coada(eveniment* coada)
{
	eveniment *aux = coada;
	printf("Coada: \n");
		while(aux)
		{
			printf("Event ID: %d  Time: %d\n", aux->ID, aux->timp);
			aux = aux->next;
		}
	printf("\n");
}

int main()
{
	init();
	adauga_eveniment(prioritate, 1, 123, 20);
	adauga_eveniment(prioritate, 1, 124, 15);
	adauga_eveniment(prioritate, 2, 230, 25);
	afisare();
	deservire_eveniment(prioritate);
	afisare_coada(coada);
	afisare();
	return 0;
}
