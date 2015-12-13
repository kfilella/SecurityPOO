/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package securitypoo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class SistemaSeguridad implements Registro{
	Sistema sistemaSeguridad = new Sistema();
	Formulario formularioSeguridad = new Formulario();
	Perfil perfilSeguridad = new Perfil();
	Funcion funcionSeguridad = new Funcion();
	ArrayList<Sistema> sistemas = new ArrayList<Sistema>();
	ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	ArrayList<Perfil> perfiles = new ArrayList<Perfil>();
	Perfil adminPerfil = new Perfil("Administrador General");
	Usuario admin = new Usuario(adminPerfil);
	int intentosLogin = 0;
	
	SistemaSeguridad(){
		this.usuarios.add(admin);
		this.perfiles.add(adminPerfil);
	}
	
	public Usuario validarLogin(String usuario, String clave){
		for(Usuario s : usuarios){
			if (s.clave.equals(clave) && s.nombre.equals(usuario))
				return s;
		}
		return null;
	}
	
	public boolean esAdmin(Usuario user){
		boolean admin = false;
		if(user.perfiles.isEmpty())
			return admin;
		for(Perfil p : user.perfiles)
			if(p.nombre.equals("Administrador General") && p.activo == true)
				admin = true;
		return admin;
	}
	
	public void pantallaLogin(String[] param){
		Scanner in = new Scanner(System.in);
	 	if (param[0].equals("admin")){
			 System.out.println("Bienvenido Administrador");
			 String usuario = "admin";
			 String clave = "admin";
			 Usuario userValido = validarLogin(usuario,clave);
			 Date date = new Date();
			 registro.add(userValido.nombre+" inicio sesion el: "+date.toString());
			 nombreUsuario.add(userValido.nombre);
			 mostrarOpciones(userValido);
			 Date date2 = new Date();
			 registro.add(userValido.nombre+" cerro sesion el: "+date2.toString());
			 nombreUsuario.add(userValido.nombre);
	 	}
		param[0] = "NO ADMIN";
		System.out.println("------ SecurityPOO v1.0 ------");
		System.out.print("Ingrese nombre de usuario: ");
		String usuario = in.nextLine();
		System.out.print("Ingrese contrasena: ");
		String clave = in.nextLine();
		Usuario userValido = validarLogin(usuario,clave);
		if (usuarios.contains(userValido)){
			nombreUsuario.add(userValido.nombre);
			Date date = new Date();
			registro.add(userValido.nombre+" inicio sesion el: "+date.toString());
			mostrarOpciones(userValido);
		}
		else if (intentosLogin<9){
				System.out.println("Usuario y/o contrasena incorrecto(s).");
				intentosLogin++;
				System.out.println("Intentos: "+intentosLogin +"/10.");
				System.out.println();
				pantallaLogin(param);
			}
			else{
				System.out.println("Intentos de ingreso excedidos. El programa cerrara.");
				cerrarPrograma();
			}
		Date date = new Date();
		registro.add(userValido.nombre+" cerro sesion el: "+date.toString());
		pantallaLogin(param);
	}
	
	//PANTALLAS
	public int mantenimientoDeUsuarios(){
		Scanner in = new Scanner(System.in);
		int opcion=0;
		System.out.println("1. Crear usuario");
		System.out.println("2. Modificar usuario");
		System.out.println("3. Eliminar usuario");
		System.out.println("4. Agregar perfil");
		System.out.println("5. Quitar perfil");
		System.out.println("6. Regresar");
		opcion = in.nextInt();
		return opcion;
	}
	
	public int mantenimientoDePerfiles(){
		Scanner in = new Scanner(System.in);
		int opcion=0;
		System.out.println("1. Crear perfil");
		System.out.println("2. Modificar perfil");
		System.out.println("3. Eliminar perfil");
		System.out.println("4. Agregar funcion");
		System.out.println("5. Quitar funcion");
		System.out.println("6. Regresar");
		opcion = in.nextInt();
		return opcion;
	}
	
	public int mantenimientoDeSistemas(){
		Scanner in = new Scanner(System.in);
		int opcion=0;
		System.out.println("1. Crear sistema");
		System.out.println("2. Modificar sistema");
		System.out.println("3. Eliminar sistema");
		System.out.println("4. Regresar");
		opcion = in.nextInt();
		return opcion;
	}
	
	public int mantenimientoDeFormularios(){
		Scanner in = new Scanner(System.in);
		int opcion=0;
		System.out.println("1. Crear formulario");
		System.out.println("2. Modificar formulario");
		System.out.println("3. Eliminar formulario");
		System.out.println("4. Regresar");
		opcion = in.nextInt();
		return opcion;
	}
	
	public int mantenimientoDeFunciones(){
		Scanner in = new Scanner(System.in);
		int opcion=0;
		System.out.println("1. Crear funcion");
		System.out.println("2. Modificar funcion");
		System.out.println("3. Eliminar funcion");
		System.out.println("4. Regresar");
		opcion = in.nextInt();
		return opcion;
	}
	
	public int opcionesConsulta(){
		Scanner in = new Scanner(System.in);
		int opcion=0;
		System.out.println("1. Mostrar sistemas");
		System.out.println("2. Mostrar formularios");
		System.out.println("3. Mostrar perfiles");
		System.out.println("4. Mostrar registro");
		System.out.println("5. Regresar");
		opcion = in.nextInt();
		return opcion;
	}
	
	
	public void mostrarOpciones(Usuario user){
		Scanner in = new Scanner(System.in);
		int opcion=0;
		int opcion2=0;
		if(esAdmin(user)==true){
			//INGRESA COMO ADMINISTRADOR. TIENE TODOS LOS PRIVILEGIOS
			while(opcion!=8){
				System.out.println("Usuario: " +user.nombre);
				System.out.println("1. Mantenimiento de usuarios");
				System.out.println("2. Mantenimiento de perfiles");
				System.out.println("3. Mantenimiento de sistemas");
				System.out.println("4. Mantenimiento de formularios");
				System.out.println("5. Mantenimiento de funciones");
				System.out.println("6. Consultas");
				System.out.println("7. Logout");
				System.out.println("8. Salir del programa");
				opcion = in.nextInt();
				switch(opcion){
					case 1: opcion2 = mantenimientoDeUsuarios();
						switch(opcion2){
							case 1: user.crearUsuario(usuarios);
							break;
							case 2: user.elegirUsuario(usuarios).modUsuario();
							break;
							case 3: user.elegirUsuario(usuarios).elimUsuario();
							break;
							case 4: 
								if(!usuarios.isEmpty()){
									Usuario u = user.elegirUsuario(usuarios);
									if(!perfiles.isEmpty()){
										Perfil p = perfilSeguridad.elegirPerfil(perfiles);
										u.perfiles.add(p);
										Date date = new Date();
										registro.add(user.nombre+" agrego el perfil: "+p.nombre+" al usuario: "+u.nombre+ " el: "+date.toString());
										usuarios.set(usuarios.indexOf(u),u);
									}
									else
										System.out.println("No existen perfiles");
								}
								else
									System.out.println("No hay usuarios");
							break;
							case 5:
								if(!usuarios.isEmpty()){
									Usuario u = user.elegirUsuario(usuarios);
									if(!u.perfiles.isEmpty()){
										Perfil p = perfilSeguridad.elegirPerfil(perfiles);
										u.perfiles.remove(p);
										Date date = new Date();
										registro.add(user.nombre+" quito el perfil: "+p.nombre+" al usuario: "+u.nombre+ " el: "+date.toString());
										usuarios.set(usuarios.indexOf(u),u);
									}
									else
										System.out.println("El usuario no tiene perfiles");
								}
								else
									System.out.println("No hay usuarios");
							break;
							case 6:
							break;
							default:
							break;
						}
					break;
					case 2: opcion2 = mantenimientoDePerfiles(); 
						switch(opcion2){
							case 1: perfilSeguridad.crearPerfil(perfiles);
							break;
							case 2:
								if(!perfiles.isEmpty())
									perfilSeguridad.elegirPerfil(perfiles).modPerfil();
								else
									System.out.println("No hay perfiles.");
							break;
							case 3: 
								if(!perfiles.isEmpty())
									perfilSeguridad.elegirPerfil(perfiles).elimPerfil();
								else
									System.out.println("No hay perfiles.");
							break;
							case 4:
								if(!perfiles.isEmpty() && !sistemas.isEmpty()){
									Perfil pe = perfilSeguridad.elegirPerfil(perfiles);
									Sistema sis = sistemaSeguridad.elegirSistema(sistemas);
									if(!sis.formularios.isEmpty()){
										Formulario form = formularioSeguridad.elegirFormulario(sis.formularios);
										if(!form.funciones.isEmpty()){
											Funcion func = funcionSeguridad.elegirFuncion(form.funciones);
											pe.funciones.add(func);
											perfiles.set(perfiles.indexOf(pe),pe);
											Date date = new Date();
											registro.add(user.nombre+" agrego la funcion: "+func.nombre+" en el perfil: "+pe.nombre+" el: "+date.toString());
										}
										else
											System.out.println("No hay funciones en este formulario.");
									}
									else
										System.out.println("No hay formularios en este sistema.");
								}
								else
									System.out.println("No hay perfiles o sistemas.");
							break;
							case 5: 
								if(!perfiles.isEmpty()){
									Perfil pe = perfilSeguridad.elegirPerfil(perfiles);
									if(!pe.funciones.isEmpty()){
										Funcion func = funcionSeguridad.elegirFuncion(pe.funciones);
										pe.funciones.remove(func);
										perfiles.set(perfiles.indexOf(pe),pe);
										Date date = new Date();
										registro.add(user.nombre+" quito la funcion: "+func.nombre+" del perfil: "+pe.nombre+" el: "+date.toString());
									}
									else
										System.out.println("El perfil no tiene funciones.");
								}
								else 
									System.out.println("No hay perfiles.");
							break;
							case 6:
							break;
							default:
							break;
							}	
					break;
					case 3: opcion2 = mantenimientoDeSistemas(); 
						switch(opcion2){
							case 1: sistemaSeguridad.crearSistema(sistemas);
							break;
							case 2:
								if(!sistemas.isEmpty())
									sistemaSeguridad.elegirSistema(sistemas).modSistema();
								else
									System.out.println("No hay sistemas.");
							break;
							case 3: 
								if(!sistemas.isEmpty())
									sistemaSeguridad.elegirSistema(sistemas).elimSistema();
								else
									System.out.println("No hay sistemas.");
							break;
							case 4:
							break;
							default:
							break;
							}	
				break;
				case 4: opcion2 = mantenimientoDeFormularios(); 
					if(!sistemas.isEmpty()){
						switch(opcion2){
							case 1: formularioSeguridad.crearForm(sistemaSeguridad.elegirSistema(sistemas));
							break;
							case 2: 
								Sistema s = sistemaSeguridad.elegirSistema(sistemas);
								if(!s.formularios.isEmpty()){
									formularioSeguridad.elegirFormulario(s.formularios).modForm();
									sistemas.set(sistemas.indexOf(s),s);
								}
								else
									System.out.println("Sistema no tiene formularios.");
							break;
							case 3: 								
								Sistema s2 = sistemaSeguridad.elegirSistema(sistemas);
								if(!s2.formularios.isEmpty()){
									formularioSeguridad.elegirFormulario(s2.formularios).elimForm();
									sistemas.set(sistemas.indexOf(s2),s2);
								}
							else
								System.out.println("Sistema no tiene formularios.");
							break;
							case 4:
							break;
							default:
							break;
						}
					}
					else
						System.out.println("No hay sistemas");
				break;
				case 5: opcion2 = mantenimientoDeFunciones(); 
					if(!sistemas.isEmpty()){
						switch(opcion2){
								case 1: 
									Sistema s = sistemaSeguridad.elegirSistema(sistemas);
									if(!s.formularios.isEmpty()){
										funcionSeguridad.crearFunc(formularioSeguridad.elegirFormulario(s.formularios));
										sistemas.set(sistemas.indexOf(s),s);
									}
								else
									System.out.println("El sistema no tiene formularios");
								break;
								case 2: 
									Sistema s1 = sistemaSeguridad.elegirSistema(sistemas);
									if(!s1.formularios.isEmpty()){
										Formulario form = formularioSeguridad.elegirFormulario(s1.formularios);
										if(!form.funciones.isEmpty()){
											funcionSeguridad.elegirFuncion(form.funciones).modFunc();
											s1.formularios.set(s1.formularios.indexOf(form),form);
											sistemas.set(sistemas.indexOf(s1),s1);
										}
										else
											System.out.println("El formulario no tiene funciones");
									}
								else
									System.out.println("El sistema no tiene formularios");
								break;
								case 3: 								
									Sistema s2 = sistemaSeguridad.elegirSistema(sistemas);
									if(!s2.formularios.isEmpty()){
										Formulario form = formularioSeguridad.elegirFormulario(s2.formularios);
										if(!form.funciones.isEmpty()){
											funcionSeguridad.elegirFuncion(form.funciones).elimFunc();
											s2.formularios.set(s2.formularios.indexOf(form),form);
											sistemas.set(sistemas.indexOf(s2),s2);
										}
										else
											System.out.println("El formulario no tiene funciones");
									}
								else
									System.out.println("El sistema no tiene formularios");
								break;
								case 4:
								break;
								default:
								break;
							}
					}
					else
						System.out.println("No hay sistemas");
				break;
				case 6: opcion2 = opcionesConsulta();
					switch(opcion2){
						case 1:
							sistemaSeguridad.mostrarSistemas(sistemas);
							break;
						case 2:
							for(Sistema s : sistemas){
								s.mostrarSistema();
								System.out.println();
								if(!s.formularios.isEmpty()){
									System.out.println("Formularios del Sistema " +s.nombre);
									for(Formulario f : s.formularios){
										f.mostrarForm();
										System.out.println();
									}
								}
								else
									System.out.println("Sistema "+s.nombre+ " no tiene formularios.");
									System.out.println();
							}
						break;
						case 3:
							System.out.println("1. Mostrar usuarios con perfiles");
							System.out.println("1. Mostrar perfiles");
							int opc = in.nextInt();
							if(opc == 1)
								for(Usuario u : usuarios){
									u.mostrarUsuario();
									System.out.println();
									System.out.println("Perfiles del usuario: " +u.nombre);
									if(!u.perfiles.isEmpty()){
										perfilSeguridad.mostrarPerfiles(u);
									}
									else
										System.out.println("El usuario "+u.nombre+" no tiene perfiles");
										System.out.println();
								}
							if(opc == 2)
								for(Perfil p : perfiles)
									p.mostrarPerfil();
						break;
						case 4: mostrarRegistros();
						break;
						case 5:
							break;
						default: 
							break;
					}
					break;
				case 7:
					return;
				case 8: cerrarPrograma();
				default: break;
				}
			}
		}
		else if (user.activo==true){
			// INGRESA UN USUARIO DIFERENTE DE ADMIN
			while(opcion!=3){
				//SUS OPCIONES SE MUESTRAN DE ACUERDO A SUS PERFILES
				System.out.println("1. Consultar");
				System.out.println("2. Logout");
				System.out.println("3. Salir del programa");
				opcion = in.nextInt();
				switch(opcion){
					case 1: 
						if(!sistemas.isEmpty()){
							Sistema s = sistemaSeguridad.elegirSistema(sistemas);
							for (Perfil p : user.perfiles)
								for (Funcion f : p.funciones)
									for(Formulario f1 : s.formularios)
										for(Funcion f2 : f1.funciones)
											if (f.nombre.equals(f2.nombre)&&f.codigo.equals(f2.codigo)&&f.descripcion.equals(f2.descripcion)){
												f.mostrarFunc();
												Date date = new Date();
												registro.add(user.nombre+" consulto la funcion: "+f.nombre+ " el: "+date.toString());
											}
						}
						else
							System.out.println("No hay nada que consultar.");
					break;
					case 2:
					return;
					case 3: cerrarPrograma();
					default: break;
				}
			}
		}
		else
			System.out.println("El usuario esta inactivo");
	}
	
    public void mostrarRegistros(){
    	for(String s : registro)
    		System.out.println(s);
    }
    
    public void cerrarPrograma(){
	    System.out.println("Cerrando programa. Adios.");
		try{
		    Thread.sleep(4000);
		    System.exit(1);
		}
		catch(InterruptedException ex){
		    Thread.currentThread().interrupt();
		}
    }
    
}
	
