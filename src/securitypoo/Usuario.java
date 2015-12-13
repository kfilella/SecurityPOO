/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package securitypoo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Usuario implements Registro{
	String nombre;
    String apellido;
    String clave;
    int cedula;
    String email;
    boolean activo;
	ArrayList<Perfil> perfiles = new ArrayList<Perfil>();
	
	Scanner in = new Scanner(System.in);
	
	Usuario(){
		this.activo = true;
	}
        
    Usuario(Perfil admin){
            this.nombre = "admin";
            this.clave = "admin";
            this.perfiles.add(admin);
            this.activo = true;
    } 
	
    public void crearUsuario(ArrayList<Usuario> usuarios){
    	Scanner ing = new Scanner(System.in);
    	Usuario usuario = new Usuario();
    	System.out.println("Ingrese nombre del usuario: ");
    	usuario.nombre = ing.nextLine();
    	System.out.println("Ingrese apellido del usuario: ");
    	usuario.apellido = ing.nextLine();
    	System.out.println("Ingrese clave del usuario: ");
    	usuario.clave = ing.nextLine();
    	System.out.println("Ingrese cedula del usuario: ");
    	usuario.cedula = ing.nextInt();
    	System.out.println("Ingrese email del usuario: ");
    	usuario.email = ing.next();
    	usuarios.add(usuario);
    	Date date = new Date();
    	registro.add(nombreUsuario.get(nombreUsuario.size()-1)+" creo el usuario: " +usuario.nombre+ " el "+ date.toString());
    }
        
    public void modUsuario(){
    	if (this.esAdmin()==false){
	    	Scanner ing = new Scanner(System.in);
	    	System.out.println("Que desea modificar?");
	    	System.out.println("1. Nombre 2. Apellido 3. Clave 4. Cedula");
	    	System.out.println("5. Email 6. Perfiles");
	    	int opcion = in.nextInt();
	    	switch(opcion){
	    	case 1:
	    		System.out.println("Ingrese nombre del usuario: ");
	    		this.nombre = ing.nextLine();
	    		break;
	    	case 2:
		    	System.out.println("Ingrese apellido del usuario: ");
		    	this.apellido = ing.nextLine();
		    	break;
	    	case 3:
		    	System.out.println("Ingrese clave del usuario: ");
		    	this.clave = ing.nextLine();
		    	break;
	    	case 4:
		    	System.out.println("Ingrese cedula del usuario: ");
		    	this.cedula = ing.nextInt();
		    	break;
	    	case 5:
	    		System.out.println("Ingrese email del usuario: ");
	    		this.email = ing.nextLine();
	    		break;
	    	case 6:
	    		
	    		break;
	    	default:
	    		System.out.println("Ingrese otro numero");
	    		break;
	    	}
	    	if (opcion > 0 && opcion < 7){
	    		Date date = new Date();
	    		registro.add(nombreUsuario.get(nombreUsuario.size()-1)+" modifico el usuario: " +this.nombre+ " el "+ date.toString());
	    	}
    	}
    	else
    		System.out.println("No tiene acceso a este usuario.");
    }
    
    public void elimUsuario(){
    	if (this.esAdmin()==false){
	    	this.activo = false;  
	    	Date date = new Date();
	    	registro.add(nombreUsuario.get(nombreUsuario.size()-1)+" elimino el usuario: " +this.nombre+ " el "+ date.toString());
    	}
    	else
    		System.out.println("No tiene acceso a este usuario.");
    }
    
    public void mostrarUsuario(){
        if (this.activo == true){
        	System.out.println();
            System.out.println("Nombre de usuario: " +this.nombre);
            System.out.println("Apellido: " +this.apellido);  
            System.out.println("Clave: " +this.clave);
            System.out.println("Cedula: " +this.cedula);
            System.out.println("Email: " +this.email);  
            
        }
    }
    
    public void agregarPerfil(Perfil p){
    	boolean encontrado = false;
    	for(Perfil p1 : this.perfiles){
    		if (p1.nombre.equals(p.nombre))
    			encontrado = true;
    	}
    	if (encontrado==false){
    		this.perfiles.add(p);
    		Date date = new Date();
    		registro.add(nombreUsuario.get(nombreUsuario.size()-1)+" agrego el perfil: " +p.nombre+ " al usuario: "+this.nombre+ " el "+ date.toString());
    	}
    	else
    		System.out.println("El usuario ya contiene perfil: "+p.nombre);
    }
    
    public void quitarPerfil(Perfil p){
    	boolean encontrado = false;
    	for(Perfil p1 : this.perfiles){
    		if (p1.nombre.equals(p.nombre))
    			encontrado = true;
    	}
    	if (encontrado==false)
    		System.out.println("El usuario no contiene perfil: "+p.nombre);
    		
    	else{
    		this.perfiles.remove(p);
    		Date date = new Date();
    		registro.add(nombreUsuario.get(nombreUsuario.size()-1)+" quito perfil: " +p.nombre+ " al usuario: "+this.nombre+ " el "+ date.toString());
    	}
    }
    
    public Usuario elegirUsuario(ArrayList<Usuario> usuarios){
    	Scanner in = new Scanner(System.in);
    	int opcion;
    	int posicion=0;
		System.out.println("Elija un usuario:");
    	for(Usuario u : usuarios){
    		System.out.println(++posicion+". Nombre: "+u.nombre+" Apellido: "+u.apellido+" Cedula: "+u.cedula);
		}
    	opcion = in.nextInt();
		return usuarios.get(opcion-1);
	}
    
	public boolean esAdmin(){
		boolean esAdmin = false;
		if(this.perfiles.isEmpty())
			return esAdmin;
		for(Perfil p : this.perfiles)
			if(p.nombre.equals("Administrador General") && p.activo == true)
				esAdmin = true;
		return esAdmin;
	}
}
