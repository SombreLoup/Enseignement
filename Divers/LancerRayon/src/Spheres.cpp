//---------------------------------------------------------------------------


#include "Spheres.h"
#include "MacroMath.h"

//---------------------------------------------------------------------------





Spheres::Spheres()
	: C(), R(0), M()
{}

Spheres::Spheres(const Spheres& S)
	: C(S.C), R(S.R), M(S.M)
{}

Spheres::Spheres(const Points3D& P, double r, const Materiau& Mat)
	: C(P), R(r), M(Mat)
{}

void Spheres::Intersection(
        			const Points3D& 	O,
                    const Vecteurs3D&   V,
                    Points3D			&Inter,
                    Vecteurs3D		&Normale,
                    Materiau			&Mat,
                    bool				&Existe)
{
	Existe = false;

	Vecteurs3D	CO(C,O);
     double		NormeCarreeCO = CO.NormeCarree();
     double         CO_V = CO*V;
     double		DeltaPrime = CO_V*CO_V - NormeCarreeCO + R*R;

     if (Sup(DeltaPrime,0,EPS8))
     {
          double	RacineDeltaPrime = sqrt(DeltaPrime);
          double	t1 = MIN(-CO_V-RacineDeltaPrime, -CO_V+RacineDeltaPrime),
          		t2 = MAX(-CO_V-RacineDeltaPrime, -CO_V+RacineDeltaPrime);

		if (Sup(t1,0,EPS8))
          {
          	Inter = O+t1*V;
          	Normale = Vecteurs3D(C,Inter).VecteurNorme();
          	Mat = M;
          	Existe = true;
          }
          else if (Egal(t1,0,EPS8) && Sup(t2,0,EPS8))
          {
          	Inter = O+t2*V;
          	Normale = Vecteurs3D(C,Inter).VecteurNorme();
          	Mat = M;
          	Existe = true;
          }
     }
}

const Spheres&	Spheres::operator=(const Spheres& S)
{
	C = S.C;
     R = S.R;
     M = S.M;

     return *this;
}
