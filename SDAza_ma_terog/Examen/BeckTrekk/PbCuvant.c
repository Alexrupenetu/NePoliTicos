#include<stdio.h>
#include<stdlib.h>

int v[11], numv[100], len, max = -1, n, k;

int valid(int p)
{
	int c_a = 0, c_b = 0, c_c = 0;
	for (int i = 0; i < p; i++)
	{
		if (v[i] == 0)
			c_a++;
		else if (v[i] == 1)
			c_b++;
		else if (v[i] == 2)
			c_c++;
	}

	if (c_a <= 3 && c_b <= 4 && c_c <= 3)
		return 1;
	else
		return 0;
}

int solutie(int p)
{
	if (p == 10)
		return 1;
	else
		return 0;
}

void afisare(int v[], int k)
{
	for (int i = 0; i < k; i++)
	{
		if (v[i] == 0)
			printf("a");
		else if (v[i] == 1)
			printf("b");
		else
			printf("c");
	}
	printf("\n");
}

void back(int p)
{
	for (int i = 0; i <= 2; i++)
	{
		v[p] = i;

		if (valid(p))
		{
			if (solutie(p))
			{
				afisare(v, p);
			}
			else
				back(p + 1);
		}

	}
}

/*
int main()
{
	back(0);
	return 0;
}
*/