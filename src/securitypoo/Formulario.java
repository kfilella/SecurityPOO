/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package securitypoo;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Formulario implements Registro{
	String nombre;
	String codigo;
    boolean activo;
    String descripcion;
    ArrayList<Funcion> funciones = new ArrayList<Funcion>();
	
    
	Formulario(){
		this.activo = true;
	}
	
    public void crearForm(Sistema sis){
    	Scanner in = new Scanner(System.in);
        Formulario form = new Formulario();
        System.out.println("Ingrese nombre del formulario: ");
        form.nombre = in.nextLine();
        System.out.println("Ingrese descripcion del formulario: ");
        form.descripcion = in.nextLine();
        form.generarCodigoForm(sis);
        sis.formularios.add(form);
        Date date = new Date();
        registro.add(nombreUsuario.get(nombreUsuario.size()-1)+" creo el formulario: " +form.nombre+ " en el sistema: "+sis.nombre+ " el "+ date.toString());
    }
    
    public void modForm(){
    	Scanner in = new Scanner(System.in);
    	System.out.println("Que desea modificar?");
    	System.out.println("1. Nombre 2. Descripcion");
    	int opcion = in.nextInt();
    	switch(opcion){
    	case 1:
    		System.out.println("Ingrese nombre del formulario: ");
    		this.nombre = in.next();
    		break;
    	case 2:
    		System.out.println("Ingrese descripcion del formulario: ");
    		this.descripcion = in.next();
    		break;
    	default:
    		System.out.println("Ingrese otro numero");
    		break;
    	}
    	if (opcion==1 || opcion==2){
    		Date date = new Date();
    		registro.add(nombreUsuario.get(nombreUsuario.size()-1)+" modifico el formulario: " +this.nombre+ " el "+ date.toString());
    	}
    }
    
    public void elimForm(){
       this.activo = false;
       Date date = new Date();
       registro.add(nombreUsuario.get(nombreUsuario.size()-1)+" elimino el formulario: " +this.nombre+ " el "+ date.toString());
    }
    
    public void mostrarForm(){
        if (this.activo == true){
        	System.out.println();
            System.out.println("Nombre: " +this.nombre);
            System.out.println("Codigo: " +this.codigo);  
            System.out.println("Descripcion: " +this.descripcion);
        }
    }
    
    public void generarCodigoForm(Sistema sis){
        String secuencia = String.format("%03d", sis.formularios.size()+1);
        this.codigo = sis.codigo + secuencia;
    }
    
    // CONSULTA
    public void mostrarForms(ArrayList<Formulario> formularios){
        for (Formulario form : formularios){
            form.mostrarForm();
        }      
    }
    
    public Formulario elegirFormulario(ArrayList<Formulario> formularios){
    	if(!formularios.isEmpty()){
	    	Scanner in = new Scanner(System.in);
	    	int opcion;
	    	int posicion=0;
			System.out.println("Elija un formulario:");
	    	for(Formulario f : formularios){
	    		System.out.println(++posicion+". Nombre: "+f.nombre+" Codigo: "+f.codigo+" Descripcion: "+f.descripcion+" Activo? "+f.activo);
			}
	    	opcion = in.nextInt();
			return formularios.get(opcion-1);
    	}
    	return null;
	}
    
    public boolean tieneFormularios(Sistema s){
    	if(!s.formularios.isEmpty())
    		return true;
    	else
    		return false;
    }
    
  

}
