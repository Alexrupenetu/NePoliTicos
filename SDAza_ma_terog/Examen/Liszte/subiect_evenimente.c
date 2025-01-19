#include<stdio.h>
#include<stdlib.h>

#define SIZE 10

typedef struct eveniment{
	int ID;
	int prio;
	long int timp;
	struct eveniment* next;
	struct eveniment* last;
}eveniment;

typedef struct data{
	int ID;
	int prio;
	long int timp;
}data;

typedef struct multilista{
	int big_prio;
	eveniment* lista_prio;
	struct multilista* next;
	struct multilista* last;
}multilista;

int front = -1, rear = -1;
data queue[SIZE];

multilista* lista = NULL;

multilista* init_lista(multilista* lista)
{
	for(int i=1; i<=8; i++)
	{
		multilista *new_prio = (multilista*)malloc(sizeof(multilista));
		
		if(new_prio)
		{
			new_prio->big_prio = i;
			new_prio->next = NULL;
			new_prio->last = NULL;
			new_prio->lista_prio = NULL;
			
			if(lista==NULL)
			{
				lista = new_prio;
				lista->last = new_prio;
			}
			else
			{
				lista->last->next = new_prio;
				lista->last = new_prio;
			}
		}
	}
	return lista;
}

eveniment* creare_eveniment(int ID, long int timp, int prio)
{
	eveniment* new_event = (eveniment*)malloc(sizeof(eveniment));
	
	if(new_event)
	{
		new_event->ID = ID;
		new_event->prio = prio;
		new_event->timp = timp;
		new_event->next = NULL;
		new_event->last = NULL;
	}
	return new_event;
}

eveniment* adauga_eveniment_lista_mica(eveniment* lista, eveniment* event)
{
	if(lista == NULL)
	{
		lista = event;
		lista->last = event;
	}
	else
	{
		if(lista->timp > event->timp)
		{
			event->next = lista;
			lista = event;
		}
		else
		{
			eveniment *aux = lista;
			while(aux!=NULL && aux->next!=NULL && aux->next->timp < event->timp)
				aux = aux->next;
			
			if(aux->next != NULL)
			{
				event->next = aux->next;
				aux->next = event;
			}
			else
			{
				lista->last->next=event;
				lista->last = event;
			}
		}
	}
	return lista;
}

multilista* adaugare_eveniment(multilista* lista, eveniment* event)
{
	multilista* aux = lista;
	
	while(aux->big_prio!=event->prio)
		aux = aux->next;
	
	
	aux->lista_prio = adauga_eveniment_lista_mica(aux->lista_prio, event);
	
	
	return lista;
}

void print(multilista* lista)
{
	multilista* aux = lista;
	while(aux)
	{
		printf("P%d: \n", aux->big_prio);
		if(aux->lista_prio)
		{
			eveniment* aux2 = aux->lista_prio;
			while(aux2)
			{
				printf("Event %d  Time %ld \n", aux2->ID, aux2->timp);
				aux2 = aux2->next;
			}
		}
		aux = aux->next;
	}
	printf("\n");
}

void enqueue(eveniment *event)
{
	if(rear == SIZE-1)
	{
		printf("Coada e plina!\n");
	}
	if(front==-1) front = 0;
	++rear;
	queue[rear].ID = event->ID;
	queue[rear].timp = event->timp;
	queue[rear].prio = event->prio;
}

void display()
{
	if(front == -1)
	{
		printf("Coada e goala!\n");
	}
	printf("FIFO\n");
	for(int i=front; i<=rear; i++)
		printf("Event %d  Time %ld  Priority %d\n", queue[i].ID, queue[i].timp, queue[i].prio);
	printf("\n");
}

eveniment* gasire(multilista* lista)
{
	multilista* aux = lista;
	eveniment* event = NULL;
	while(aux)
	{
		if(aux->lista_prio)
			event = aux->lista_prio;
			
		aux = aux->next;
	}
	
	return event;
}

multilista* stergere_adaugare_coada(multilista* lista, eveniment* event)
{
	multilista* aux = lista;
	
	if(event==NULL)
		return lista;
	
	while(aux->big_prio!=event->prio)
		aux = aux->next;
	
	eveniment* aux2 = aux->lista_prio;
	
	if(aux2->ID == event->ID)
	{
		aux2 = aux2->next;
		aux->lista_prio = aux2;
	}
	else
	{
		while(aux2!=NULL && aux2->next->ID!=event->ID)
			aux2 = aux2->next;
			
		aux2->next = aux2->next->next;
	}
	return lista;
}

int main()
{
	lista = init_lista(lista);
	lista = adaugare_eveniment(lista, creare_eveniment(123, 10, 1));
	lista = adaugare_eveniment(lista, creare_eveniment(124, 14, 1));
	lista = adaugare_eveniment(lista, creare_eveniment(124, 12, 1));
	lista = adaugare_eveniment(lista, creare_eveniment(124, 16, 1));
	lista = adaugare_eveniment(lista, creare_eveniment(233, 15, 2));
	lista = adaugare_eveniment(lista, creare_eveniment(234, 13, 2));
	//lista = adaugare_eveniment(lista, creare_eveniment(511, 10, 5));
	print(lista);
	
	eveniment* event = gasire(lista);
	printf("EVent %d \n", event->ID);
	
	lista = stergere_adaugare_coada(lista, gasire(lista));
	enqueue(event);
	print(lista);
	
	event = gasire(lista);
	lista = stergere_adaugare_coada(lista, gasire(lista));
	enqueue(event);
	print(lista);
	
	display();
	return 0;
}
