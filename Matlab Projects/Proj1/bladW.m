function [wynik] = bladW(wartosc,wartosc2)
%funkcja oblicza wartość błędu względnego wartosci podanych
wynik=abs(wartosc-wartosc2)./abs(wartosc);
end