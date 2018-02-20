/*
 * LFSR.c
 *
 *  Created on: 5 fevr. 2015
 *      Author: yann
 */

#include	<stdio.h>
#include	<string.h>

#define TAILLE_REGISTRE		30

#define	Registre			int

char* RegistreToString(Registre registre, unsigned int tailleRegistre) {
	char	str[tailleRegistre+1];
	int		i;

	str[tailleRegistre] = '\0';

	for (i=tailleRegistre-1; i>=0; i--) {
		str[i] = (registre%2 == 0 ? '0' : '1');
		registre >>= 1;
	}

	return strdup(str);
}

int	bit(Registre registre, unsigned int i) {
	return (registre >> i) % 2;
}




/**
 * Fonctions de retroactionpour A5/1
 */
unsigned int f1(Registre registre){
	unsigned int r = 0;
int i;

	r = bit(registre,18)^bit(registre,17)^bit(registre,16)^bit(registre,13);
	if ((r!=0) && (r!=1)) {
		printf("f1 !!!!!! r=%c, b18=%c, b17=%c, b16=%c, b13=%c\n",'0'+r, '0'+bit(registre,18), '0'+bit(registre,17), '0'+bit(registre,16), '0'+bit(registre,13));
		printf("%d %s\n", registre%(1<<19), RegistreToString(registre,19));

		for (i=1; i<19; i++) {
			printf("décal : %d\n", (1L<<i));
			printf("%u %s\n", registre>>i, RegistreToString(registre>>i,19));

		}


	}
	return r;
}

unsigned int f2(Registre registre){
	unsigned int r = 0;

	r = bit(registre,21)^bit(registre,20);
	if ((r!=0) && (r!=1))
		printf("f2 !!!!!! r=%c, b21=%c, b20=%c\n",'0'+r, '0'+bit(registre,21), '0'+bit(registre,20));

	return r;
}

unsigned int f3(Registre registre){
	unsigned int r = 0;

	r = bit(registre,22)^bit(registre,21)^bit(registre,20)^bit(registre,7);
	if ((r!=0) && (r!=1))
		printf("f3 !!!!!! r=%c, b22=%c, b21=%c, b20=%c, b7=%c\n",'0'+r, '0'+bit(registre,22), '0'+bit(registre,21), '0'+bit(registre,20), '0'+bit(registre,7));

	return r;
}


/**
 * m : bit majoritaire
 */
unsigned int	cycle1(Registre *registre1, unsigned int m) {
	unsigned int	s = bit(*registre1,18);	// Le bit de sortie, c'est celui de gauche
	unsigned int 	h = bit(*registre1,8);	// bit d'horloge

	Registre masque = (1L<<19)-1;

	if (h == m) {


		unsigned int r = f1(*registre1);// On calcule le bit de retroaction
		*registre1 = *registre1 << 1;	// On decale le registre (ici vers la droite)
		*registre1 &= masque;

		*registre1 = *registre1 | r;	// On place le bit de retroaction (ici, c'est donc a droite)
	}


	return s;
}

unsigned int	cycle2(Registre *registre2, unsigned int m) {
	unsigned int	s = bit(*registre2,21); // Le bit de sortie, c'est celui de droite
	unsigned int 	h = bit(*registre2,10);

	Registre masque = (1L<<22)-1;

	if (h==m){
		unsigned int	r = f2(*registre2); // On calcule le bit de retroaction
		*registre2 = *registre2 << 1;	// On decale le registre (ici vers la droite)
		*registre2 &= masque;
		*registre2 = *registre2 | r ;		// On place le bit de retroaction (ici, c'est donc a gauche)
	}
	return s;
}

unsigned int	cycle3(Registre *registre3, unsigned int m) {
	unsigned int	s = bit(*registre3,22); // Le bit de sortie, c'est celui de droite
	unsigned int 	h = bit(*registre3,10);

	Registre masque = (1L<<23)-1;

	if (h==m){
		unsigned int	r = f3(*registre3); // On calcule le bit de retroaction
		*registre3 = *registre3 << 1;	// On decale le registre (ici vers la droite)
		*registre3 &= masque;
		*registre3 = *registre3 | r ;		// On place le bit de retroaction (ici, c'est donc a gauche)
	}
	return s;
}

unsigned int majorite(unsigned int s1, unsigned int s2, unsigned int s3){
	if (s1+s2+s3 >= 2){
			return 1;
	}

	return 0;
}

unsigned int cycle51(Registre *registre1, Registre *registre2, Registre *registre3){

	unsigned int s1 = bit(*registre1,8);
	unsigned int s2 = bit(*registre2,10);
	unsigned int s3 = bit(*registre3,10);
    unsigned int m = majorite(s1, s2, s3);

//    printf("----- Avant le cycle------\n");
//    printf("r1=%s h(r1)=%c\n", RegistreToString(*registre1,19), '0'+bit(*registre1,8));
//    printf("r2=%s h(r2)=%c\n", RegistreToString(*registre2,22), '0'+bit(*registre2,10));
//    printf("r3=%s h(r3)=%c\n", RegistreToString(*registre3,23), '0'+bit(*registre3,10));
//     printf("bit majoritaire : %c\n",'0'+m);

    s1 = cycle1(registre1,m);
    s2 = cycle2(registre2,m);
    s3 = cycle3(registre3,m);

//    printf("r1=%s s(r1)=%c\n", RegistreToString(*registre1,19), '0'+s1);
//    printf("r2=%s s(r2)=%c\n", RegistreToString(*registre2,22), '0'+s2);
//    printf("r3=%s s(r3)=%c\n", RegistreToString(*registre3,23), '0'+s3);
//    printf("bit de sortie de A5/1 : %c\n",'0'+(s1^s2^s3));
//

	return (s1^s2^s3);
}


void flux51(Registre *registre1, Registre *registre2, Registre *registre3, FILE* f, unsigned int longueur) {
	unsigned int i;

	for (i=0; i<longueur; i++) {
		int r = cycle51(registre1, registre2, registre3);
		fputc('0'+r, f);
	}
}


int main(int argc, char **argv) {
	FILE*	fichier;
	Registre	r1 = 19;
	Registre r2 = 22;
	Registre r3 = 23;

	unsigned int i;
    r1 = (((10*16)+10)*16+11)*16+12;
    r3 = (((10*16)+10)*16+11)*16+12;
    r2 = (((10*16)+10)*16+11)*16+12;


	if ((fichier=fopen("../STS/alea.txt","w"))!=NULL) {
		for (i=0; i<100; i++) {
			flux51(&r1,&r2,&r3,fichier,20000);
			fputc('\n',fichier);
		}

		fclose(fichier	);
	}
	else {
		printf("Erreur dans l'ouverture du fichier\n");
	}

	printf("Generation des chaines terminee\n");

	return 0;
}
