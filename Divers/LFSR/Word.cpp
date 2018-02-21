/*
 * Word.cpp
 *
 *  Created on: 20 oct. 2009
 *      Author: yann
 */

template	<int T>
Word<T>::Word()
	: bits(), taille(T)
{
}

template	<int T>
Word<T>::Word(string chaine_binaire)
	: bits(chaine_binaire), taille(T)
{
}


template	<int T>
Word<T>::Word(unsigned char c)
	: bits(), taille(T)
{
	for (int i=0; i<8; i++)
	{
		bits[i] = c%2;
		c = c/2;
	}
}

template	<int T>
Word<T>::Word(short int c)
	: bits(), taille(T)
{
	for (int i=0; i<16; i++)
	{
		bits[i] = c%2;
		c = c/2;
	}
}

template	<int T>
Word<T>::~Word()
{
}


template	<int T>
Word<T>	Word<T>::operator^(const Word<T>& mot)	const
{
	Word<T>	res;
	res.bits = bits;
	res.bits ^= mot.bits;
	return res;
}

template <int T>
Word<T>	Word<T>::operator|(const Word<T>& mot)	const
{
	Word<T>	res;
	res.bits = bits;
	res.bits |= mot.bits;
	return res;
}

template <int T>
Word<T>	Word<T>::operator&(const Word<T>& mot)	const
{
	Word<T>	res;
	res.bits = bits;
	res.bits &= mot.bits;
	return res;
}

template <int T>
Word<T>	Word<T>::operator~()	const
{
	Word<T>	res;
	res.bits = ~bits;
	return res;
}

template <int T>
Word<T>	Word<T>::operator<<(int nb)	const
{
	Word<T>	res;
	res.bits = bits << nb;
	return res;
}


template <int T>
Word<T>	Word<T>::operator>>(int nb)	const
{
	Word<T>	res;
	res.bits = bits >> nb;
	return res;
}


template <int T>
Word<T>	Word<T>::rotl(int nb)	const
{
	Word<T>	res;
	res.bits = (bits << nb) | (bits >> (taille-nb));
	return res;
}

template <int T>
string	Word<T>::toBin() const
{
	ostringstream s(ostringstream::out);
	s << bits;
	return s.str();
}

template <int T>
string	Word<T>::toHex() const
{
	string s;
	for (int i=0; i<(taille/4); i++)
	{
		int pos = taille-1-4*i;
		int	v = bits[pos]*8+bits[pos-1]*4+bits[pos-2]*2+bits[pos-3];
		if (v<10)
			s += (unsigned char)('0'+v);
		else
			s += (unsigned char)('A'+(v-10));
	}
	return s;
}

template <int T>
bool	Word<T>::operator[](int i)	const
{
	return bits[taille-i-1];
}

template <int T>
void	Word<T>::setBit(int pos, bool v)
{
	bits[taille-pos-1] = v;
}



static string 	hexa_bin[] = { 	"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111",
						"1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"	};

template <int T>
void	Word<T>::setHexValue(string valeur)
{
	unsigned int	l = valeur.length();
	unsigned int	nbCarHexa = (taille/4);

	if (l > nbCarHexa)
	{
		return;
	}

	string			s;
	unsigned int	c;

	for (unsigned int i=0; i<nbCarHexa; i++)
	{
		if (('0' <= valeur[i]) && (valeur[i] <= '9'))
			c = valeur[i]-'0';
		else if (('A' <= valeur[i]) && (valeur[i] <= 'F'))
			c = 10+(valeur[i]-'A');
		else
		{
			return;
		}

		s=s+hexa_bin[c];
	}

	bits = bitset<T>(s);
}

template <int T>
void	Word<T>::setBinValue(string valeur)
{
	unsigned int	l = valeur.length();

	if (l > taille)
	{
		return;
	}

	bits = bitset<T>(valeur);
}


template <int T>
Word<T>::operator unsigned int()	const
{
	return bits.to_ulong();
}


