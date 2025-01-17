#include<stdio.h>
int steaguri[4], culori[] = { 0, 1, 2, 3, 4, 5 };
//  1 - galben    3 - verde  mijloc 

void afisare2(int steaguri[], int p)
{
	for(int i=0; i<=p; i++)
		switch(steaguri[i])
	{
		case(0):
		{
			printf("alb ");
			break;
		}
		case(1):
		{
			printf("galben ");
			break;
		}
		case(2):
		{
			printf("rosu ");
			break;
		}
		case(3):
		{
			printf("verde ");
			break;
		}
		case(4):
		{
			printf("albastru ");
			break;
		}
		case(5):
		{
			printf("negru ");
			break;
		}
	}
	printf("\n");
}

int valid2(int steaguri[], int p)
{
	for (int i = 0; i < p; i++)
		if (steaguri[i] == steaguri[p])
			return 0;
	return 1;
}	

int solutie2(int steaguri[], int p)
{
	if (p == 2)
	{
		if (steaguri[1] == 1 || steaguri[1] == 3)
			return 1;
		else
			return 0;
	}
	return 0;
}

void back2(int p)
{
	for (int i = 0; i < 6; i++)
	{
		steaguri[p] = culori[i];

		if (valid2(steaguri, p))
		{
			if (solutie2(steaguri, p))
			{
				afisare2(steaguri, p);
			}
			else
			{
				back2(p + 1);
			}
		}
	}
}

/*

int main()
{
	back2(0);
	return 0;
}
*/