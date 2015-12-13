/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package securitypoo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Funcion implements Registro{
    String nombre;
    String codigo;
    boolean activo;
    String descripcion;
    String variable;
    String tipo_var;
    
    
    Funcion(){
    	this.activo = true;
    }
    
    public void crearFunc(Formulario form){
    	Scanner in = new Scanner(System.in);
        Funcion func = new Funcion();
        System.out.println("Ingrese nombre de la funcion: ");
        func.nombre = in.nextLine();
        System.out.println("Ingrese descripcion de la funcion: ");
        func.descripcion = in.nextLine();
        System.out.println("Ingrese variable asociada a la funcion: ");
        func.variable = in.nextLine();
        System.out.println("Ingrese el tipo de dato de la variable: ");
        func.tipo_var = in.nextLine();
        func.generarCodigoFunc(form);
        form.funciones.add(func);
        Date date = new Date();
        registro.add(nombreUsuario.get(nombreUsuario.size()-1)+" creo la funcion: " +func.nombre+ " en el formulario: " +form.nombre+ " el "+ date.toString());
    }
    
    public void modFunc(){
    	Scanner in = new Scanner(System.in);
        System.out.println("Ingrese nombre de la funcion: ");
        this.nombre = in.nextLine();
        System.out.println("Ingrese descripcion de la funcion: ");
        this.descripcion = in.nextLine();
        System.out.println("Ingrese variable asociada a la funcion: ");
        this.variable = in.nextLine();
        System.out.println("Ingrese el tipo de dato de la variable: ");
        this.tipo_var = in.nextLine();
        Date date = new Date();
        registro.add(nombreUsuario.get(nombreUsuario.size()-1)+" modifico la funcion: " +this.nombre+ " el "+ date.toString());
    }
    
    public void elimFunc(){
        this.activo = false;
        Date date = new Date();
        registro.add(nombreUsuario.get(nombreUsuario.size()-1)+" elimino la funcion: " +this.nombre+ " el "+ date.toString());
        
    }
    
    public void generarCodigoFunc(Formulario form){
        String secuencia = String.format("%03d", form.funciones.size()+1);
        this.codigo = secuencia;
    }
    
    public void mostrarFunc(){
        if (this.activo == true){
        	System.out.println();
            System.out.println("Nombre de funcion: " +this.nombre);
            System.out.println("Codigo de funcion: " +this.codigo);  
            System.out.println("Descripcion de funcion: " +this.descripcion);
            System.out.println("Variable asociada: " +this.variable);
            System.out.println("Tipo de dato: " +this.tipo_var);  
            System.out.println();
        }
    }
    
    // CONSULTA
    public void mostrarFuncs(ArrayList<Funcion> funciones){
        for (Funcion func : funciones){
            func.mostrarFunc();
        }      
    }
    
    public Funcion elegirFuncion(ArrayList<Funcion> funciones){
    	if(!funciones.isEmpty()){
	    	Scanner in = new Scanner(System.in);
	    	int opcion;
	    	int posicion=0;
			System.out.println("Elija una funcion:");
	    	for(Funcion f : funciones){
	    		System.out.println(++posicion+". Nombre: "+f.nombre+" Codigo: "+f.codigo+" Descripcion: "+f.descripcion+" Activo? "+f.activo);
			}
	    	opcion = in.nextInt();
			return funciones.get(opcion-1);
    	}
    	return null;
	}
}
