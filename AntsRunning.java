/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ACO;

/**
 *
 * @author Erick
 */
import java.util.Random;

public class AntsRunning{
	public static void main(String[] args){
		System.out.println("Matriz:");
	//Declarar matriz____________________________________________________
		double matriz[][]= new double[6][6];
		matriz[0][0]=0;
		matriz[0][1]=20;
		matriz[0][2]=5;
		matriz[0][3]=3;
		matriz[0][4]=15;
		matriz[0][5]=6;
		matriz[1][0]=20;
		matriz[1][1]=0;
		matriz[1][2]=8;
		matriz[1][3]=2;
		matriz[1][4]=33;
		matriz[1][5]=10;
		matriz[2][0]=5;
		matriz[2][1]=8;
		matriz[2][2]=0;
		matriz[2][3]=22;
		matriz[2][4]=17;
		matriz[2][5]=23;
		matriz[3][0]=3;
		matriz[3][1]=2;
		matriz[3][2]=22;
		matriz[3][3]=0;
		matriz[3][4]=7;
		matriz[3][5]=13;
		matriz[4][0]=15;
		matriz[4][1]=33;
		matriz[4][2]=17;
		matriz[4][3]=7;
		matriz[4][4]=0;
		matriz[4][5]=24;
		matriz[5][0]=6;
		matriz[5][1]=10;
		matriz[5][2]=23;
		matriz[5][3]=13;
		matriz[5][4]=24;
		matriz[5][5]=0;

		//Impresion de matriz de distancias
		Funciones.impresionDeMatriz(matriz);
		
		//Feromonas	
		double feromonas[][]= new double [6][6];
		for (int i=0;i<6;i++) {
			for (int j=0;j<6;j++) {
				feromonas[i][j]= 0.3;
			}
		}

                
                int hormigas = 2;
                
		/****AQUI EMPIEZA EL CICLO DE NUEVO*****/
                while(hormigas > 0){
                int repeticion = 0;    
                double visibilidadind[][]= new double [6][6];
                //Ciudad inicio
		//System.out.println("Ciudad inicio");
		int ciudad_inicio= Funciones.ciudadInicial();
		//System.out.println(ciudad_inicio);
		int ciudades[]= new int[6];
                int numeroDeCiudad = 0;
		ciudades[numeroDeCiudad]=ciudad_inicio;	
                double costoT = 0;
                while(repeticion <= 4){
		double visibilidadtotal=0.0;
		double  visibilidadporcaminos[]= new double [6];
			for (int j=0;j<6;j++) {
				if(matriz[ciudades[numeroDeCiudad]][j]!=0){
				visibilidadind[ciudades[numeroDeCiudad]][j]=((feromonas[ciudades[numeroDeCiudad]][j])*(1/matriz[ciudades[numeroDeCiudad]][j]));
				}
				if(matriz[ciudades[numeroDeCiudad]][j]==0)
				{
					visibilidadind[ciudades[numeroDeCiudad]][j]=0;
                                }
				for(int count = 0; count <= numeroDeCiudad ; count++){
                                    //System.out.println(ciudades[count] + " " + j);
                                    if(j == ciudades[count]){
                                        visibilidadind[ciudades[numeroDeCiudad]][j]=0.0;
                                        //System.out.println("son iguales");
                                    }
                                }
				//System.out.println("De ciudad " + ciudades[numeroDeCiudad] + " a ciudad " + j + " = " + visibilidadind[ciudades[numeroDeCiudad]][j]);
				visibilidadtotal+=visibilidadind[ciudades[numeroDeCiudad]][j];
				/*if(j==5){
					visibilidadporcaminos[ciudades[i]]=visibilidadtotal;
					visibilidadtotal=0;
					System.out.println("visibilidad por caminos: "+ciudades[i]+""+visibilidadporcaminos[ciudades[i]]);
					System.out.println("visibilidadtotal "+visibilidadtotal);
				}*/

				//System.out.println("Visibilidad total " + visibilidadtotal);
			}
		
		double probabilidad[]= new double[6];
		
		for (int j=0;j<6;j++) {
			probabilidad[j]=visibilidadind[ciudades[numeroDeCiudad]][j]/visibilidadtotal;
			//System.out.println("probabilidad: "+probabilidad[j]);
		}		
		
		//Funcion para tomar el mejor camino **Full greedy**
		/*int caminoElegido = 0;
		int temp = 0;
		double tempDelValor = 0;
		for(double q : probabilidad){
			if(q > tempDelValor){
				caminoElegido = temp;
				tempDelValor = q;
			}
			temp++;
		}
		System.out.println("Camino a ciudad: " + caminoElegido );
		*/
		
                /**
                 * Implementación de ruleta
                 */
		double probabilidadAcumuladaTemp = 0;
		double probabilidadAcumuladaTemp2 = 0;
                int caminoElegido = 0;
		double rand = Math.random();
		//System.out.println(rand);
		if(rand > 0 && rand < probabilidad[0]){
			//System.out.println("Camino a ciudad: " + 0);
                        caminoElegido = 0;
		}
		probabilidadAcumuladaTemp = probabilidad[0];
		probabilidadAcumuladaTemp2 = probabilidadAcumuladaTemp + probabilidad[1];
		if(rand > probabilidadAcumuladaTemp && rand < probabilidadAcumuladaTemp2){
			//System.out.println("Camino a ciudad: " + 1);
                        caminoElegido = 1;
		}
		probabilidadAcumuladaTemp += probabilidad[1];
		probabilidadAcumuladaTemp2 += probabilidad[2];
		if(rand > probabilidadAcumuladaTemp && rand < probabilidadAcumuladaTemp2){
			//System.out.println("Camino a ciudad: " + 2);
                        caminoElegido = 2;
                }
		
		probabilidadAcumuladaTemp += probabilidad[2];
		probabilidadAcumuladaTemp2 += probabilidad[3];
		if(rand > probabilidadAcumuladaTemp && rand < probabilidadAcumuladaTemp2){
			//System.out.println("Camino a ciudad: " + 3);
                        caminoElegido = 3;
		}
		probabilidadAcumuladaTemp += probabilidad[3];
		probabilidadAcumuladaTemp2 += probabilidad[4];
		if(rand > probabilidadAcumuladaTemp && rand < probabilidadAcumuladaTemp2){
			//System.out.println("Camino a ciudad: " + 4);
                        caminoElegido = 4;
		}
		probabilidadAcumuladaTemp += probabilidad[4];
		probabilidadAcumuladaTemp2 += probabilidad[5];
		if(rand > probabilidadAcumuladaTemp && rand < probabilidadAcumuladaTemp2){
			//System.out.println("Camino a ciudad: " + 5);
                        caminoElegido = 5;
		}
		
                costoT += matriz[ciudades[numeroDeCiudad]][caminoElegido];
                
                //System.out.println("Costo total: " + costoT);
                feromonas[ciudades[numeroDeCiudad]][caminoElegido] += 0.3;
                for(int k = 0; k<6; k++){
                    feromonas[ciudades[numeroDeCiudad]][k] -= 0.1;
                }
                if(numeroDeCiudad < 6){
                    numeroDeCiudad++;
                ciudades[numeroDeCiudad] = caminoElegido;
                }
                repeticion++;
                }
                costoT += matriz[ciudades[(ciudades.length)-1]][ciudades[0]];
                System.out.print("Tour final: ");
                for(int tourFinal : ciudades){
                    System.out.print(tourFinal + " ");
                }
                System.out.print(ciudades[0]);
                System.out.println(" Costo total: " + costoT);
                hormigas--;
                }
	}
}
