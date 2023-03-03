function [] = stopwatch(matrix, b)
%UNTITLED3 Summary of this function goes here
%   Detailed explanation goes here
tic
Jacobi(matrix,b);
toc
tic
w=size(matrix);
w=w(1)/2
for i = 1:w
    helpMatrix=[matrix(i,i) matrix(w+i, i); matrix(w+i,i) matrix(w+i, w+i)];
    x=Jacobi(helpMatrix, [b(i) b(w+i)])
end
toc
end