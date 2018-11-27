#pragma once

#include	<map>
#include	<string>
#include	<vector>
using namespace std;


class Kasiski
{
public:
	static	vector<int>	calculerEcarts(string chaine, map<string,int> trigrammes);

private:
	Kasiski(void);
	~Kasiski(void);
};
