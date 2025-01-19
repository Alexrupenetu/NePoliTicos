#include<stdio.h>
#include<stdlib.h>
#define SIZE 10

typedef struct proces{
	int numar_proces;
	int prioritate_proces;
}proces;

typedef struct prio{
	int prioritate;
	proces coada[SIZE];
	int front, rear, top;
	proces stiva[SIZE];
	struct prio* next;
	struct prio* last;
}prio;


prio *lista = NULL;

prio* adaugare_prioritate(prio* lista, int prioritate)  //Complexitate O(1)
{
	prio* nod_nou = (prio*)malloc(sizeof(prio));
	
	if(nod_nou)
	{
		nod_nou->prioritate = prioritate;
		nod_nou->next = NULL;
		nod_nou->front = -1;
		nod_nou->rear = -1;
		nod_nou->top = -1;
		
		if(lista==NULL)
		{
			lista = nod_nou;
			lista->last = lista;
		}
		else
		{
		        lista->last->next = nod_nou;
		        lista->last = nod_nou;
		}
	}
	return lista;
}

prio* adaugare_proces(prio* lista, int nr_proces, int prio_proces) //Complexitate O(1)
{
	prio* aux = lista;
	while(aux->prioritate != prio_proces)
		aux = aux->next;
		
	if(aux)
	{
		if(aux->rear == SIZE-1)
			printf("Coada este plina!\n");
		else
		{
		if(aux->front == -1)
			aux->front = 0;
		++(aux->rear);
		aux->coada[aux->rear].numar_proces = nr_proces;
		aux->coada[aux->rear].prioritate_proces = prio_proces;
		}
	}
	return lista;
}

prio* adaugare_proces_rulare(prio *lista, int nr_proces, int prio_proces)
{
	if(lista->top == SIZE -1)
		printf("Stiva este plina!");
	else
	{
		(lista->top)++;
		lista->stiva[lista->top].numar_proces = nr_proces;
		lista->stiva[lista->top].prioritate_proces = prio_proces;

	}
	return lista;
}

prio* descomp_stiva(prio*lista)
{
	prio *head = lista;
	if(head->top==-1)
		printf("Stiva e goala!");
	else
	{
       	for(int i=head->top; i>=0; i--)
           {
            proces proc_curent = head->stiva[i];  // Luăm procesul curent
            lista = adaugare_proces(lista, proc_curent.numar_proces, proc_curent.prioritate_proces);  // Adăugăm în coadă
            }
        }
    
    return lista;
}

void display_coada(proces coada[SIZE], int front, int rear) //O(n)
{
	if(front==-1 || front>rear)
		printf("Coada este goala!\n");
	else
	{
	for(int i=front; i<=rear; i++)
		printf("proces(%d) ", coada[i].numar_proces);
	printf("\n");
	}
}

void display_stiva(proces stiva[SIZE], int top)
{
	if(top==-1)
	{
		printf("Stiva e goala!\n");
	}
	else
	{
		for(int i=top; i>=0; i--)
			printf("proces(%d) cu prio P%d |", stiva[i].numar_proces, stiva[i].prioritate_proces);
		printf("\n");
	}
}

void afisare(prio *lista)  //O(n^2)
{
	prio *aux = lista;
	while(aux)
	{
		printf("P%d: ", aux->prioritate);
		display_coada(aux->coada, aux->front, aux->rear);
		printf("\n");
		aux = aux->next;
	}
	
	display_stiva(lista->stiva, lista->top);
	printf("\n");
}

int main()
{
	lista = adaugare_prioritate(lista, 1);
	lista = adaugare_prioritate(lista, 2);
	lista = adaugare_prioritate(lista, 3);
	lista = adaugare_prioritate(lista, 4);
	lista = adaugare_prioritate(lista, 5);
	lista = adaugare_prioritate(lista, 6);
	lista = adaugare_prioritate(lista, 7);
	lista = adaugare_prioritate(lista, 8);
	
	lista = adaugare_proces(lista, 1, 1);
	lista = adaugare_proces(lista, 2, 1);
	lista = adaugare_proces(lista, 3, 1);
	lista = adaugare_proces(lista, 1, 3);
	lista = adaugare_proces(lista, 1, 5);
	lista = adaugare_proces(lista, 4, 1);
	lista = adaugare_proces(lista, 1, 2);
	lista = adaugare_proces(lista, 1, 7);
	lista = adaugare_proces(lista, 1, 8);
	lista = adaugare_proces(lista, 2, 8);
	
	lista = adaugare_proces_rulare(lista, 5, 1);
	lista = adaugare_proces_rulare(lista, 1, 4);
	lista = adaugare_proces_rulare(lista, 2, 7);
	lista = adaugare_proces_rulare(lista, 1, 6);
	
	afisare(lista); //inainte sa adaugam procesele de rulare
	
	lista = descomp_stiva(lista);
	
	afisare(lista);
	return 0;
	
}
