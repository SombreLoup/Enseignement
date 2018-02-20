//---------------------------------------------------------------------------

#ifndef SpheresH
#define SpheresH

#include	"Points3D.h"
#include	"Vecteurs3D.h"
#include	"Materiau.h"

class Spheres
{
public:
          Spheres();
        	Spheres(const Spheres&);
        	Spheres(const Points3D&, double, const Materiau&);

        	void Intersection(
        			const Points3D& 	Origine,
                    const Vecteurs3D&   Direction,
                    Points3D			&Inter,
                    Vecteurs3D		&Normale,
                    Materiau			&Mat,
                    bool				&Existe);

        	const Spheres&	operator=(const Spheres&);


private:
	Points3D		C;
     double		R;
     Materiau		M;

};



//---------------------------------------------------------------------------
#endif
