//Implementare priority queue
//Elementul cu prioritate maxima este la final
//Hai sa zicem ca avem 3 prioritati {1, 2, 3}
//El cu prioritate 1 -> final
//El cu prioritate 3 ->inceput

#include<stdio.h>
#include<stdlib.h>

typedef struct node{
	char info;
	int prio;
	struct node *next;
}node;

typedef struct queue{
	node *first;
	node *last;
	int size;
}queue;

typedef struct pq{
	struct queue *prioQ[5];
	int size;
}pq;

pq *coada=NULL;

node *creare_nod(char info, int prio)
{
	node *p;
	p = (node*)malloc(sizeof(node));
	if(p==NULL)
	{
		printf("Eroare");
		exit(1);
	}
	p->info = info;
	p->prio = prio;
	p->next = NULL;
	return p;
}

void init_pq()
{
    coada = (pq *)malloc(sizeof(pq));
    if (coada == NULL)
    {
        printf("Eroare la alocarea memoriei pentru coada principală\n");
        exit(1);
    }
    for (int i = 1; i <= 3; i++)
    {
        coada->prioQ[i] = (queue *)malloc(sizeof(queue));
        if (coada->prioQ[i] == NULL)
        {
            printf("Eroare la alocarea memoriei pentru coada de prioritate %d\n", i);
            exit(1);
        }
        coada->prioQ[i]->first = NULL;
        coada->prioQ[i]->last = NULL;
        coada->prioQ[i]->size = 0;
    }
    coada->size = 0;
}


void insert_pq(pq *coada, node *to_insert) //insereaza la final
{
	int priority = to_insert->prio;
	if(coada->prioQ[priority]->first == NULL)
	{
		coada->prioQ[priority]->first = to_insert;
		coada->prioQ[priority]->last = to_insert;
		
	}
	else
	{
		coada->prioQ[priority]->last->next = to_insert;
		coada->prioQ[priority]->last = to_insert;
	}
	coada->prioQ[priority]->size++;
	coada->size++;
}

void print_pq(pq *coada)
{
	for(int i=3; i>=1; i--)
	{
		node *aux = coada->prioQ[i]->first;
		while(aux)
		{
			printf("%c ", aux->info);
			aux = aux->next;
		}
	}
	printf("\n");
}

int main()
{
	node *elem1, *elem2, *elem3, *elem4, *elem5, *elem6;
	
	init_pq();
	elem1 = creare_nod('A', 1);
	elem2 = creare_nod('B', 3);
	elem3 = creare_nod('X', 3);
	elem4 = creare_nod('Z', 2);
	elem5 = creare_nod('G', 2);
	elem6 = creare_nod('H', 3);
	insert_pq(coada, elem1);
	insert_pq(coada, elem2);
	insert_pq(coada, elem3);
	insert_pq(coada, elem4);
	insert_pq(coada, elem5);
	insert_pq(coada, elem6);
	print_pq(coada);
	return 0;
	
}


