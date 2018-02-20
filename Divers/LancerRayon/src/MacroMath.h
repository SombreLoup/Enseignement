//*****************************************************************************
// 
// Fichier	: MacroMath.h
// 
// Auteur	: Y. LANUEL							
// 
// Date creat.	: 27/05/96							
// 
// Commentaires	: Macros indispensables.
// 
// 
//*****************************************************************************



#include	<math.h>



#define	MIN(a,b)	((a)<(b)?(a):(b))
#define	MAX(a,b)	((a)>(b)?(a):(b))

#define	MIN3(a,b,c)	MIN(MIN(a,b),c)
#define	MAX3(a,b,c)	MAX(MAX(a,b),c)

#define	EPS2	0.01
#define	EPS4	0.0001
#define	EPS6	0.000001
#define	EPS8	0.00000001
#define	MaxReel	MAXDOUBLE
#define	PI	M_PI


#define	True	true
#define	False	false

#define	Egal(a,b,e)	(fabs((a)-(b)) < e ? True : False)
#define	Sup(a,b,e)	(((a)-(b))>e ? True : False)
#define	Inf(a,b,e)	(((b)-(a))>e ? True : False)
#define	InfEgal(a,b,e)	(((b)-(a))>-e ? True : False)
#define	SupEgal(a,b,e)	(((a)-(b))>-e ? True : False)

