function [wynik] = Jacobi(A, b, d, x, it)
%Jacobi - funkcja przyjmuje 5 argumentów i w wyniku zwraca rozwiązanie
%układu równań (za pomocą metody Jacobiego) w postaci wektora
%   Argumenty:
%   A - macierz A współczynników układu równań
%   b - wektor b współczynników układu równań
%   d - wielkość warunku końcowego (różnica norm wektora wynikowego k+1-ej i
%   k-tej iteracji musi być mniejsza od d)
%   x - wektor przybliżenia początkowego x
%   it - maksymalna ilość iteracji
n = size(A);
n = n(1);
B = zeros(n);
c = zeros(n,1);
%Sprawdzanie ilości argumentów wejściowych
switch nargin
    case 2
        x = zeros(n,1);
        d = 10^(-40);
        it = 1000;
    case 3
        x = zeros(n,1);
        it = 1000;
    case 4
        it = 1000;
end
%Wyznaczanie wartości wektora c i macierzy B służących do wyznaczania
%wektorów x w kolejnych iteracjach
for i = 1:n
    c(i) = b(i)/A(i,i);
    for j = 1:n
        if i~=j
            B(i,j) = -(A(i,j)/A(i,i));
        end
    end
end
y=eig(B);
prom=max(abs(y))
if prom >=1
    disp('Metoda Jacobiego nie jest zbieżna dla danej macierzy')
   return
end
lastx = [1:n]';
i=1;
solvex=linsolve(A,b);
%Wyznaczanie kolejnych waryości wektora x
while abs(norm(lastx-x))>d && i<it
    lastx = x;
    x = B*x + c;
    iteracja(i)=i;
    norma(i)=sum(abs(x-solvex));
    i=i+1;
end
%Wykres obrazujący zależność błędu względnego w zależności od numeru
%iteracji
plot(iteracja,norma)
xlabel("Numer iteracji");
ylabel("Błąd bezwzględny aktualnej wartości wyniku");
title("Zależność błędu bezwględnego wyniku w zależności od iteracji");
wynik=x;
end