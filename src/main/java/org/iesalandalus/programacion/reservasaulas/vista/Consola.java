package org.iesalandalus.programacion.reservasaulas.vista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.*;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
	
	private static final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			
	private Consola() {
		//Evito que se cree el constructor por defecto
	}
	
	public static void mostrarMenu() {
		mostrarCabecera("Reservas de aulas");
		for (Opcion opcion : Opcion.values()) {
			System.out.println(opcion);
		}
	}
	
	public static void mostrarCabecera(String mensaje) {
		System.out.printf("%n%s%n", mensaje);
		String cadena = "%0" + mensaje.length() + "d%n";
		System.out.println(String.format(cadena, 0).replace("0", "-"));
	}
	
	public static int elegirOpcion() {
		int ordinalOpcion;
		do {
			System.out.print("Elige una opción: ");
			ordinalOpcion = Entrada.entero();
		} while (!Opcion.esOrdinalValido(ordinalOpcion));
		return ordinalOpcion;
	}
	
	public static Aula leerAula() {

		Aula aula = new Aula(leerNombreAula());
		return aula;
	}
	
	public static String leerNombreAula() {
		String nombreAula;
		do {
			System.out.print("Introduce el aula: ");
			nombreAula = Entrada.cadena();
		} while (nombreAula.trim().equals(""));
		return nombreAula;
	}
	public static Profesor leerProfesor() {
		
		System.out.print("Introduce numero de telefono");
		String telefono = Entrada.cadena();
		System.out.print("Introduce el correo");
		String correo = Entrada.cadena();
		Profesor profesor = new Profesor(leerNombreProfesor(),telefono,correo);
		return profesor;
	}
	
	public static String leerNombreProfesor() {
		String nombreProfesor;
		do {
			System.out.print("Introduce el nombre del profesor: ");
			nombreProfesor = Entrada.cadena();
		} while (nombreProfesor.trim().equals(""));
		return nombreProfesor;
	}
	public static Tramo leerTramo() {
		int numero = 0;
		do {
		System.out.print("Introduce tramo horario");
		System.out.println("1.Mañana,2.Tarde");
		numero = Entrada.entero();
		}while (numero !=1 || numero!=2 );
		if (numero == 1) {
			return Tramo.MANANA;
		}
		if (numero == 2) {
			return Tramo.TARDE;
		}
		return Tramo.values()[numero];
	}
	public static LocalDate leerDia(){
	     LocalDate fecha=null;
	        do{
	        System.out.println("Introduce una fecha con formato dd/mm/aaaa: ");
	        fecha=LocalDate.parse(Entrada.cadena(), FORMATO_DIA);
	       }while(fecha!=null);
	        return fecha;
	    }
}

