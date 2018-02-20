//---------------------------------------------------------------------------

#ifndef SourceH
#define SourceH

#include	"Points3D.h"
#include	"Vecteurs3D.h"

class Source
{
public:
	Source();
	Source(const Points3D&, const Vecteurs3D&);

     const Points3D&	Position()	const;
     const Vecteurs3D&	Couleur()		const;

private:
	Points3D		P;
     Vecteurs3D	C;
};

//---------------------------------------------------------------------------
#endif
 