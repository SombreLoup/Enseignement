# x^80 + x^78 + x^77 + x^70 + x^69 + x^67 + x^65 + x^64 + x^63 + x^62 + x^61 + x^59 + x^57 + x^55 + x^54 + x^52 + x^51 + x^47 + x^45 + x^44 + x^42 + x^40 + x^39 + x^38 + x^36 + x^32 + x^30 + x^28 + x^26 + x^24 + x^21 + x^19 + x^17 + x^14 + x^12 + x^11 + x^4 + x^3 + 1


def binaire(x):
    s = ""
    n=8
    while n>0:
        r = x % 2
        s = str(r)+","+s
        x = x/2
        n = n-1
    return s



fCrypte = open("challenge.png.encrypt", "rb")
fClair = open("Im1.png", "rb")
 
try:
    diffLFSR = ""
    n = 20
    while n>0:
        bCrypte = fCrypte.read(1) 
        bClair = fClair.read(1)
        n = n-1
        if bCrypte == "" or bClair=="" :
            break;

        print "%02X " % ord(bCrypte[0]), " %02X " % ord(bClair[0])
        bDiff = ord(bCrypte[0])-ord(bClair[0])
        diffLFSR = diffLFSR+binaire(bDiff)
    print diffLFSR
 
except IOError:
    pass
finally:
    fCrypte.close()    
    fClair.close()