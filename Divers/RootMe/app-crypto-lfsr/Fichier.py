# x^80 + x^78 + x^77 + x^70 + x^69 + x^67 + x^65 + x^64 + x^63 + x^62 + x^61 + x^59 + x^57 + x^55 + x^54 + x^52 + x^51 + x^47 + x^45 + x^44 + x^42 + x^40 + x^39 + x^38 + x^36 + x^32 + x^30 + x^28 + x^26 + x^24 + x^21 + x^19 + x^17 + x^14 + x^12 + x^11 + x^4 + x^3 + 1
from Util import xoreBytes
import lfsr
import struct

def binaire(x):
    s = ""
    n=8
    while n>0:
        r = x % 2
        s = str(r)+s
        x = x/2
        n = n-1
    return s

def binaireToByte(s):
    v=0
    for i in range(0,len(s)):
        v = v*2+int(s[i])
        
    return v

def calculerCle(chiffre,clair,taille):
    fCrypte = open(chiffre, "rb")
    fClair = open(clair, "rb")
 
    try:
        diffLFSR = ""
        n = taille/8
        while n>0:
            bCrypte = fCrypte.read(1) 
            bClair = fClair.read(1)
            n = n-1
            if bCrypte == "" or bClair=="" :
                break;
    
    #        print "%02X " % ord(bCrypte[0]), " %02X " % ord(bClair[0])
            bDiff = xoreBytes(ord(bCrypte[0]), ord(bClair[0]))
            diffLFSR = diffLFSR+binaire(bDiff)
            
        return diffLFSR
     
    except IOError:
        pass
    finally:
        fCrypte.close()    
        fClair.close()
        
def dechiffreFichier(fichier,cle,taille):
    fCrypte = open(fichier, "rb")
    fRes = open(fichier+"_res.png", "wb")
    
    try:
        ic=0
        while True:
            car = fCrypte.read(1) 
            if car=="":
                break;
            
            bCrypte = ord(car)
            binaireCrypte = binaire(bCrypte)
            
            sByteClair=""
            for j in range(0,8):
                r = (int(binaireCrypte[j])+int(cle[ic])) % 2
                sByteClair = sByteClair+str(r)
                ic = (ic+1) % taille
            fRes.write(struct.pack("B",binaireToByte(sByteClair)))
                
    except IOError:
        pass
    finally:
        fCrypte.close()    
        fRes.close()


codon = calculerCle("challenge.png.encrypt", "Im1.png", 256)
print codon
