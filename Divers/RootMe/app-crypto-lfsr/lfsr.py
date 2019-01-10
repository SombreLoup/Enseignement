f = [16,15,13,5,0]
registre = "110010101111111000"


def bit(r,i):
    c = r[16-i]
    val = ord(c)-48
    return val
    
def retroaction(registre):
    return bit(registre,f[0]) ^ bit(registre,f[1]) ^ bit(registre,f[2]) ^ bit(registre,f[3]) ^ bit(registre,f[4])

def tour(r):
    s = r[16]
    b = retroaction(r)
    r = str(b)+r
    return r

def sortie(r):
    return r[16]

def boucleRetroaction(r):
    r = tour(registre)
    r = r[:18]
    return r

