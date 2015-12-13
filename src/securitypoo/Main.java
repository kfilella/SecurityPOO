/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package securitypoo;

public class Main implements Registro{
	public static void main (String[] args){
		SistemaSeguridad ss = new SistemaSeguridad();
		ss.pantallaLogin(args);
	}
}