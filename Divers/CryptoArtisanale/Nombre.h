#pragma once

#include	<vector>
using namespace std;

class Nombre
{
public:
	static	int	pgcd(vector<int> tab);
	static	int	pgcd(int a, int b);
	static	vector<int>	getFacteursPremiers(int n);
	static	vector<int>	getNombresPremiers(int n);

private:
	Nombre(void);
	~Nombre(void);
};
