/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package securitypoo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Perfil implements Registro{
	String nombre;
	String codigo;
	boolean activo;
	String descripcion;
	ArrayList<Funcion> funciones = new ArrayList<Funcion>();
	
	Perfil(){
		this.activo = true;
	}
	
	Perfil(String nombre){
		this.nombre = nombre;
		this.activo = true;
	}
	
	public void crearPerfil(ArrayList<Perfil> perfiles){
		Scanner in = new Scanner(System.in);
		Perfil pe = new Perfil();
		System.out.println("Ingrese nombre del perfil: ");
		pe.nombre = in.nextLine();
		System.out.println("Ingrese descripcion del perfil: ");
        pe.descripcion = in.nextLine();
        pe.generarCodigo();
        perfiles.add(pe);
        Date date = new Date();
        registro.add(nombreUsuario.get(nombreUsuario.size()-1)+" creo el perfil: " +pe.nombre+ " el "+ date.toString());
		
	}
	
	public void modPerfil(){
		Scanner in = new Scanner(System.in);
		System.out.println("Ingrese nombre del Perfil: ");
        this.nombre = in.nextLine();
        System.out.println("Ingrese descripcion del Perfil: ");
        this.descripcion = in.nextLine();
        Date date = new Date();
        registro.add(nombreUsuario.get(nombreUsuario.size()-1)+" modifico el perfil: " +this.nombre+ " el "+ date.toString());
	}
	
	 public void elimPerfil(){
         this.activo = false;
         Date date = new Date();
         registro.add(nombreUsuario.get(nombreUsuario.size()-1)+" elimino el perfil: " +this.nombre+ " el "+ date.toString());
     }

      public void mostrarPerfil(){
          if (this.activo == true)
          {
        	  System.out.println();
              System.out.println("Nombre de perfil: " +this.nombre);
              System.out.println("Codigo: " +this.codigo);  
              System.out.println("Descripcion: " +this.descripcion);
             
          }
      }
	
    //consulta
     public void mostrarPerfiles(Usuario user){
          for (Perfil pe : user.perfiles){
              pe.mostrarPerfil();
          }      
      }
     
     public void mostrarTodosPerfiles(ArrayList<Perfil> perfiles){
         for (Perfil pe : perfiles){
             pe.mostrarPerfil();
         }      
     }
     
     //genera codigo igual a sistema
	public void generarCodigo(){
        char[] charArray = this.nombre.toCharArray();
        this.codigo = "" + charArray[0] + charArray[1] + charArray[2];
        this.codigo = this.codigo.toUpperCase();
    }
	
	public Perfil elegirPerfil(ArrayList<Perfil> perfiles){
		Scanner in = new Scanner(System.in);
    	int opcion;
    	int posicion=0;
		System.out.println("Elija un perfil:");
    	for(Perfil p : perfiles){
    		System.out.println(++posicion+". Nombre: "+p.nombre+" Codigo: "+p.codigo+" Descripcion: "+p.descripcion);
		}
    	opcion = in.nextInt();
		return perfiles.get(opcion-1);
	}
	
}
