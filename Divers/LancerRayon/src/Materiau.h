//---------------------------------------------------------------------------

#ifndef MateriauH
#define MateriauH

#include	"Vecteurs3D.h"

class Materiau
{
public:
		Materiau()
		: ka(0), kd(0), ks(0), kt(0), CDif(), CSpec(), Refr(0) {}
        Materiau(double Ka, double Kd, double Ks, double Kt, const Vecteurs3D& Dif, const Vecteurs3D& Spec, int R)
        		: ka(Ka), kd(Kd), ks(Ks), kt(Kt), CDif(Dif), CSpec(Spec), Refr(R) {}

		double	ka,kd,ks,kt;
		Vecteurs3D CDif, CSpec;
		int		Refr;

};

//---------------------------------------------------------------------------
#endif
