* A URL de acesso ficou como http://localhost:8080/DesafioContaAzul/rest/clock. 
Exemplos: http://localhost:8080/DesafioContaAzul/rest/clock/10   ou 
		  http://localhost:8080/DesafioContaAzul/rest/clock/1/12

* Sabendo que o limite de minutos é 60 e o ângulo máximo é 360 graus, temos um valor de 6 graus por minuto de variação entre os ponteiros.

* O cálculo utilizado foi: módulo da diferença entre os minutos e a hora transformada em minutos do relógio(cada hora é posicionada a cada 5 minutos no relógio) vezes o valor de graus por minuto (6).
Ou seja: |min-(hora*5)|*6

* Como é para exibir apenas o menor angulo, caso o resultado da conta anterior seja maior que 180 graus e menor do que 360(em caso dos dois ponteiros no mesmo lugar, existe apenas um ângulo) a conta a seguir é realizada: (60-|min-(hora*5)|)*6

* Para evitar que a mesma consulta seja executada duas vezes, utilizei um HashMap que salva o menor ângulo utilizando o horário como chave. Fiz dessa forma porque não sabia se era permitido utilizar banco de dados.

