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
public class Funciones{
	public static int ciudadInicial(){
		int c = (int)(Math.random()*5+0);
		return c;
	}
	
	public static void impresionDeMatriz(double[][] matriz){
		for (int i=0;i<=5;i++) {
			for (int j=0;j<=5;j++) {
				if (j==5) {
				System.out.println("\t"+matriz[i][j]);
				}else{
				System.out.print("\t"+matriz[i][j]);}
			}
		}
	}
}
