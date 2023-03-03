function [] = wykresy(A,b)
%UNTITLED Summary of this function goes here
%   Detailed explanation goes here
x=linsolve(A,b);
wyniki=zeros(1,100);
d=zeros(1,100);
i=1:100;
for i=1:100
    d(i)=10^(-1*i);
    wyniki(i)=sum(bladW(x,Jacobi(A,b,d(i))));
end
plot(wyniki,d)
end