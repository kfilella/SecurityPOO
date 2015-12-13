/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package securitypoo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Sistema implements Registro{
    String nombre;
	String codigo;
    boolean activo;
    String descripcion;
    Scanner in = new Scanner(System.in);
	ArrayList<Formulario> formularios = new ArrayList<Formulario>();
	
	Sistema(){
		this.activo = true;
	}
        
    public void crearSistema(ArrayList<Sistema> sistemas){
    	Scanner ing = new Scanner(System.in);
        Sistema sis = new Sistema();
        System.out.println("Ingrese nombre del sistema: ");
        sis.nombre = ing.nextLine();
        System.out.println("Ingrese descripcion del sistema: ");
        sis.descripcion = ing.nextLine();
        sis.generarCodigoSis();
        sistemas.add(sis);
        Date date = new Date();
        registro.add(nombreUsuario.get(nombreUsuario.size()-1)+" creo el sistema: " +sis.nombre+ " el "+ date.toString());
    }
    
    public void modSistema(){
        System.out.println("Ingrese nombre del sistema: ");
        this.nombre = in.nextLine();
        this.generarCodigoSis();
        System.out.println("Ingrese descripcion del sistema: ");
        this.descripcion = in.nextLine();
        Date date = new Date();
        registro.add(nombreUsuario.get(nombreUsuario.size()-1)+" modifico el sistema: " +this.nombre+ " el "+ date.toString());
    }
    
    public void elimSistema(){
    	boolean formActivo=false;
        for (Formulario f : this.formularios){
            if (f.activo == true)
            	formActivo = true;
        }
        if (formActivo==false){
        	Date date = new Date();
        	registro.add(nombreUsuario.get(nombreUsuario.size()-1)+" elimino el sistema: " +this.nombre+ " el "+ date.toString());
        	this.activo = false;
        }
        else{
        	System.out.println("Error al eliminar el sistema:");
        	System.out.println("El sistema tiene formularios");
        }
    }
    
    
        
    public void mostrarSistema(){
        if (this.activo == true){
            System.out.println("Nombre del sistema: " +this.nombre);
            System.out.println("Codigo: " +this.codigo);  
            System.out.println("Descripcion: " +this.descripcion);
            System.out.println("Activo: " +this.activo);
        }
    }
    
    public void generarCodigoSis(){
        char[] charArray = this.nombre.toCharArray();
        this.codigo = "" + charArray[0] + charArray[1] + charArray[2];
        this.codigo = this.codigo.toUpperCase();
    }
    
    // CONSULTA
    public void mostrarSistemas(ArrayList<Sistema> sistemas){
        for (Sistema sis : sistemas){
            sis.mostrarSistema();
        }      
    } 
    
    public Sistema elegirSistema(ArrayList<Sistema> sistemas){
    	Scanner in = new Scanner(System.in);
    	int opcion;
    	int posicion=0;
		System.out.println("Elija un sistema:");
    	for(Sistema s : sistemas){
    		System.out.println(++posicion+". Nombre: "+s.nombre+" Codigo: "+s.codigo+" Descripcion: "+s.descripcion+" Activo? "+s.activo);
		}
    	opcion = in.nextInt();
		return sistemas.get(opcion-1);
	}
    
   
}

