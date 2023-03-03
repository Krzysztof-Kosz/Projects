function [wyjscie] = sprawdzacz(N)
%funkcja sprawdza poprawność metody Simpsona dla każdej z siedmiu funkcji 
%prezentując wyniki w tabelkach w zależności od wielkości N 
N
a=0;
a1=[0 0 0 0 0 0];
b=[0.1 0.9 1 pi 10 100];
b1=0.1;
b2=0.9;
b3=1;
b4=pi;
b5=10;
b6=100;

fun1=@(x) 3*sin(x);
wspol1=3;

fun2=@(x) 2*sin(x)+0.5*sin(2*x);
wspol2=[2 0.5];

fun3=@(x) sin(x)-4*sin(2*x)+0.3*sin(3*x);
wspol3=[1 -4 0.3];

fun4=@(x) 6*sin(x)+13*sin(2*x)-3.45*sin(3*x)+2*sin(4*x);
wspol4=[6 13 -3.45 2];

fun5=@(x) 17*sin(x)+0.12*sin(2*x)+sin(3*x)-3*sin(4*x)-5*sin(5*x);
wspol5=[17 0.12 1 -3 -5];

fun6=@(x) 11*sin(x)-3*sin(2*x)-8*sin(3*x)+0.11*sin(4*x)-2*sin(5*x)+sin(6*x)-3.5*sin(7*x);
wspol6=[11 -3 -8 0.11 -2 1 -3.5];

fun7=@(x) 0.1*sin(x)-2*sin(2*x)+10*sin(3*x)+23*sin(4*x)-0.18*sin(5*x)+0.12*sin(6*x)-15*sin(7*x)+3*sin(8*x)+13*sin(9*x);
wspol7=[0.1 -2 10 23 -0.18 0.12 -15 3 13];

disp("f(x)=3*sin(x)")

wartWbud1=[integral(fun1,a,b1) integral(fun1,a,b2) integral(fun1,a,b3) integral(fun1,a,b4) integral(fun1,a,b5) integral(fun1,a,b6)];
wartFunkcji1=[metodaSimpsona(wspol1,a,b1,N) metodaSimpsona(wspol1,a,b2,N) metodaSimpsona(wspol1,a,b3,N) metodaSimpsona(wspol1,a,b4,N) metodaSimpsona(wspol1,a,b5,N) metodaSimpsona(wspol1,a,b6,N)];
blad1=bladW(wartWbud1, wartFunkcji1);
T1=table(a1',b',wartWbud1',wartFunkcji1',blad1', VariableNames=["a" "b" "WartFunkcjiWbudowanej" "WartSimpsona" "bladWzgl"])

disp("f(x)=2*sin(x)+0.5*sin(2*x)")

wartWbud2=[integral(fun2,a,b1) integral(fun2,a,b2) integral(fun2,a,b3) integral(fun2,a,b4) integral(fun2,a,b5) integral(fun2,a,b6)];
wartFunkcji2=[metodaSimpsona(wspol2,a,b1,N) metodaSimpsona(wspol2,a,b2,N) metodaSimpsona(wspol2,a,b3,N) metodaSimpsona(wspol2,a,b4,N) metodaSimpsona(wspol2,a,b5,N) metodaSimpsona(wspol2,a,b6,N)];
blad2=bladW(wartWbud2, wartFunkcji2);
T2=table(a1',b',wartWbud2',wartFunkcji2',blad2', VariableNames=["a" "b" "WartFunkcjiWbudowanej" "WartSimpsona" "bladWzgl"])

disp("f(x)=sin(x)-4*sin(2*x)+0.3*sin(3*x)")

wartWbud3=[integral(fun3,a,b1) integral(fun3,a,b2) integral(fun3,a,b3) integral(fun3,a,b4) integral(fun3,a,b5) integral(fun3,a,b6)];
wartFunkcji3=[metodaSimpsona(wspol3,a,b1,N) metodaSimpsona(wspol3,a,b2,N) metodaSimpsona(wspol3,a,b3,N) metodaSimpsona(wspol3,a,b4,N) metodaSimpsona(wspol3,a,b5,N) metodaSimpsona(wspol3,a,b6,N)];
blad3=bladW(wartWbud3, wartFunkcji3);
T3=table(a1',b',wartWbud3',wartFunkcji3',blad3', VariableNames=["a" "b" "WartFunkcjiWbudowanej" "WartSimpsona" "bladWzgl"])

disp("f(x)=6*sin(x)+13*sin(2*x)-3.45*sin(3*x)+2*sin(4*x)")

wartWbud4=[integral(fun4,a,b1) integral(fun4,a,b2) integral(fun4,a,b3) integral(fun4,a,b4) integral(fun4,a,b5) integral(fun4,a,b6)];
wartFunkcji4=[metodaSimpsona(wspol4,a,b1,N) metodaSimpsona(wspol4,a,b2,N) metodaSimpsona(wspol4,a,b3,N) metodaSimpsona(wspol4,a,b4,N) metodaSimpsona(wspol4,a,b5,N) metodaSimpsona(wspol4,a,b6,N)];
blad4=bladW(wartWbud4, wartFunkcji4);
T4=table(a1',b',wartWbud3',wartFunkcji4',blad4', VariableNames=["a" "b" "WartFunkcjiWbudowanej" "WartSimpsona" "bladWzgl"])

disp("f(x)=17*sin(x)+0.12*sin(2*x)+sin(3*x)-3*sin(4*x)-5*sin(5*x)")

wartWbud5=[integral(fun5,a,b1) integral(fun5,a,b2) integral(fun5,a,b3) integral(fun5,a,b4) integral(fun5,a,b5) integral(fun5,a,b6)];
wartFunkcji5=[metodaSimpsona(wspol5,a,b1,N) metodaSimpsona(wspol5,a,b2,N) metodaSimpsona(wspol5,a,b3,N) metodaSimpsona(wspol5,a,b4,N) metodaSimpsona(wspol5,a,b5,N) metodaSimpsona(wspol5,a,b6,N)];
blad5=bladW(wartWbud5, wartFunkcji5);
T5=table(a1',b',wartWbud5',wartFunkcji5',blad5', VariableNames=["a" "b" "WartFunkcjiWbudowanej" "WartSimpsona" "bladWzgl"])

disp("f(x)=11*sin(x)-3*sin(2*x)-8*sin(3*x)+0.11*sin(4*x)-2*sin(5*x)+sin(6*x)-3.5*sin(7*x)")

wartWbud6=[integral(fun6,a,b1) integral(fun6,a,b2) integral(fun6,a,b3) integral(fun6,a,b4) integral(fun6,a,b5) integral(fun6,a,b6)];
wartFunkcji6=[metodaSimpsona(wspol6,a,b1,N) metodaSimpsona(wspol6,a,b2,N) metodaSimpsona(wspol6,a,b3,N) metodaSimpsona(wspol6,a,b4,N) metodaSimpsona(wspol6,a,b5,N) metodaSimpsona(wspol6,a,b6,N)];
blad6=bladW(wartWbud6, wartFunkcji6);
T6=table(a1',b',wartWbud6',wartFunkcji6',blad6', VariableNames=["a" "b" "WartFunkcjiWbudowanej" "WartSimpsona" "bladWzgl"])

disp("f(x)=0.1*sin(x)-2*sin(2*x)+10*sin(3*x)+23*sin(4*x)-0.18*sin(5*x)+0.12*sin(6*x)-15*sin(7*x)+3*sin(8*x)+13*sin(9*x)")

wartWbud7=[integral(fun7,a,b1) integral(fun7,a,b2) integral(fun7,a,b3) integral(fun7,a,b4) integral(fun7,a,b5) integral(fun7,a,b6)];
wartFunkcji7=[metodaSimpsona(wspol7,a,b1,N) metodaSimpsona(wspol7,a,b2,N) metodaSimpsona(wspol7,a,b3,N) metodaSimpsona(wspol7,a,b4,N) metodaSimpsona(wspol7,a,b5,N) metodaSimpsona(wspol7,a,b6,N)];
blad7=bladW(wartWbud7, wartFunkcji7);
T7=table(a1',b',wartWbud7',wartFunkcji7',blad7', VariableNames=["a" "b" "WartFunkcjiWbudowanej" "WartSimpsona" "bladWzgl"])
wyjscie="Koniec dla danego N";
end