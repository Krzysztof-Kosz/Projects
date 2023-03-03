function [matrix] = testMaker(n)
%testMaker - tworzy macierz, dla której metoda Jacobiego będzie zbieżna
%   Argumenty które przyjmuje, to n czyli połowa ilości kolumn/wierszy
%   macierzy
vectc=(-pi/6)+(pi/3).*rand(1,n);
C=diag(cos(vectc));
S=diag(sin(vectc));
matrix=[C S; -S C];
end