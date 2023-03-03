%Przykład 1 rozbieżny
vect=(2*pi).*rand(1,5);
C=diag(sin(vect));
S=diag(cos(vect));
A1=[C S; -S C]
b1=100.*rand(10,1)
Jacobi(A1,b1);

%Przykład 1 zbieżny
A2=testMaker(1)
b2=100.*rand(2,1)
wynik2=Jacobi(A2,b2)
bladWzgledny=bladW(linsolve(A2,b2),wynik2)

%Przyklad 2 zbieżny
A3=testMaker(2)
b3=100.*rand(4,1)
wynik3=Jacobi(A3,b3)
bladWzgledny=bladW(linsolve(A3,b3),wynik3)

%Przyklad 3 zbieżny
A4=testMaker(5)
b4=100.*rand(10,1)
wynik4=Jacobi(A4,b4);
bladWzgledny=mean(bladW(linsolve(A4,b4),wynik4))

%Przyklad 4 zbieżny
A5=testMaker(50);
b5=100.*rand(100,1);
wynik5=Jacobi(A5,b5);
bladWzgledny=mean(bladW(linsolve(A5,b5),wynik5))

%Przyklad 5 zbieżny
A6=testMaker(100);
b6=100.*rand(200,1);
wynik6=Jacobi(A6,b6);
bladWzgledny=mean(bladW(linsolve(A6,b6),wynik6))

%Przyklad 6 zbieżny
A7=testMaker(200);
b7=100.*rand(400,1);
wynik7=Jacobi(A7,b7);
bladWzgledny=mean(bladW(linsolve(A7,b7),wynik7))