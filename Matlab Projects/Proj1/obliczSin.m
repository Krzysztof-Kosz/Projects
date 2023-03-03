function wynik = obliczSin(t, wspolczynniki)
%funkcja zwraca wartosc wielomianu ktory jest postaci
%sumy sinusow ze wspolczynnikami
%parametry
%t - argument dla ktorego wyliczamy wartosc
%wspolczynniki
wspolczynniki=[0 wspolczynniki];
z = goertzel(cos(t) + 1i*sin(t), wspolczynniki);
wynik = imag(z);
end