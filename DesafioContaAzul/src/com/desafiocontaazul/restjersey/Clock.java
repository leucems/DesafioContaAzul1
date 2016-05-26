package com.desafiocontaazul.restjersey;

import java.util.*; 
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;

	 
	@Path("/clock")
	public class Clock {		

				
		@Path("{hora}")
		@GET
		@Produces("application/json")
		public Response  calculaAngulos(@PathParam("hora") int hour) throws JSONException {
			
			if(hour>0 && hour<13)
			{
				if(Variaveis.angulosCalculados.get(new String(hour+":0"))==null)
				{
				
					int angle = calculaAngulo(hour, 0);
					//o parametro de minutos está fixo em 60 devido a apenas a hora ser passada como parametro 
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("angle", angle); 
					
					Variaveis.angulosCalculados.put(new String(hour+":0"), angle);
					
					String result = jsonObject.toString();
					return Response.status(200).entity(result).build();
				}
				else
				{
					String result = "Menor angulo ja calculado para esses parametros. O valor e: " + Variaveis.angulosCalculados.get(new String(hour+":0"));
					return Response.status(200).entity(result).build();
				}
			}
			else
			{
				String result = "Hora invalida. Por favor digite um numero inteiro entre 1 e 12";
				return Response.status(200).entity(result).build();
			}
			
			
		}
		
		@Path("{hora}/{minuto}")
		@GET
		@Produces("application/json")
		public Response calculaAngulosComMinutos(@PathParam("hora") int hour, @PathParam("minuto") int minute) throws JSONException {
			
			if(hour>0 && hour<13)
			{
				if(minute>-1 && minute<60)
				{
					if(Variaveis.angulosCalculados.get(new String(hour+":"+minute))==null)
					{
						int angle = calculaAngulo(hour, minute);
						
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("angle", angle); 
					
						Variaveis.angulosCalculados.put(new String(hour+":"+minute), angle);
								
						String result = jsonObject.toString();
						return Response.status(200).entity(result).build();
					}
					else
					{
						String result = "Menor angulo ja calculado para esses parametros. O valor e: " + Variaveis.angulosCalculados.get(new String(hour+":"+minute));
						return Response.status(200).entity(result).build();
					}	
				}
				else
				{
					String result = "Minutos invalidos. Por favor digite um numero inteiro entre 0 e 59";
					return Response.status(200).entity(result).build();
				}
				
			}
			else
			{
				String result = "Hora invalida. Por favor digite um numero inteiro entre 1 e 12";
				return Response.status(200).entity(result).build();
			}
		}
		
		public int calculaAngulo(int hour, int minute)
		{
			//como o ângulo máximo é 360 graus e os minutos chegam apenas a 60, o valor de angulo por minuto é 360/60 = 6
			int anguloMinuto = 6;
			int ponteiroHora = horaParaMinuto(hour);
			
	
			//numero sempre tem que ser positivo
			int distanciaPonteirosMinuto = Math.abs(minute - ponteiroHora);
			int angulo = distanciaPonteirosMinuto * anguloMinuto;
			
			if((angulo > 180 && angulo < 360) || angulo == 0)
			{
				//pego a sobra do calculo anterior para descobrir o angulo menor que 180
				angulo = (60 - distanciaPonteirosMinuto) * anguloMinuto;
			}
			
			return angulo;
		}
		
		public int horaParaMinuto (int hour)
		{
			//cada hora é exibida a cada 5 minutos em um relógio
			int minute = 5 * hour;
			return minute;
		}
				

	}



