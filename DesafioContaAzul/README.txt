* A URL de acesso ficou como http://localhost:8080/DesafioContaAzul/rest/clock. 
Exemplos: http://localhost:8080/DesafioContaAzul/rest/clock/10   ou 
		  http://localhost:8080/DesafioContaAzul/rest/clock/1/12

* Sabendo que o limite de minutos � 60 e o �ngulo m�ximo � 360 graus, temos um valor de 6 graus por minuto de varia��o entre os ponteiros.

* O c�lculo utilizado foi: m�dulo da diferen�a entre os minutos e a hora transformada em minutos do rel�gio(cada hora � posicionada a cada 5 minutos no rel�gio) vezes o valor de graus por minuto (6).
Ou seja: |min-(hora*5)|*6

* Como � para exibir apenas o menor angulo, caso o resultado da conta anterior seja maior que 180 graus e menor do que 360(em caso dos dois ponteiros no mesmo lugar, existe apenas um �ngulo) a conta a seguir � realizada: (60-|min-(hora*5)|)*6

* Para evitar que a mesma consulta seja executada duas vezes, utilizei um HashMap que salva o menor �ngulo utilizando o hor�rio como chave. Fiz dessa forma porque n�o sabia se era permitido utilizar banco de dados.

