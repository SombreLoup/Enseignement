//---------------------------------------------------------------------------


#include "Source.h"

//---------------------------------------------------------------------------


Source::Source(const Points3D& Pos, const Vecteurs3D& Coul)
	: P(Pos), C(Coul)
{}

Source::Source()
{}

const Points3D&	Source::Position()		const
{
	return P;
}

const Vecteurs3D&	Source::Couleur()		const
{
	return C;
}
