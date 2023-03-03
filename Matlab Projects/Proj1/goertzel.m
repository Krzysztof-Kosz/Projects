function [wynik] = goertzel(z, wspolczynniki)
%funkcja zwraca wartosc wielomianu o w punkcie z
% parametry
% z - argument dla ktorego obliczamy wartosc funkcji
% wspolczynniki - wspolczynniki funkcji

p = 2*real(z);
q = -(real(z).*real(z) + imag(z).*imag(z));

n = length(wspolczynniki);

b = zeros(n+1, 1);
b(n) = wspolczynniki(n);

for k = n-1 : -1 : 1
    b(k)=wspolczynniki(k) + p.*b(k+1) + q.*b(k+2);
end

u = wspolczynniki(1) + real(z)*b(2) + q*b(3);
v = imag(z)*b(2);

wynik = u + 1i*v;
end