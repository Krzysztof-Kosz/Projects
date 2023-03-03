function [wynik] = metodaSimpsona(wspolczynniki, a, b, N)
%Funkcja służąca obliczaniu przybliżonej wartości całki za pomocą metody
%Simpsona
H = (b-a)/N;
f_a = obliczSin(a,wspolczynniki);
f_b = obliczSin(b,wspolczynniki);
suma1 = 0;
for k = 1 : 1 : N-1
    suma1 = suma1 + obliczSin(a + k*H, wspolczynniki);
end
suma1=2*suma1;
suma2=0;
for k = 0 : 1 : N-1
    suma2 = suma2 + obliczSin(a + k*H + H/2, wspolczynniki);
end
suma2=4*suma2;

wynik = (H/6)*(f_a + f_b + suma1 + suma2);
end