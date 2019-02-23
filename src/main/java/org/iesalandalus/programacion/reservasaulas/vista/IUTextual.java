package org.iesalandalus.programacion.reservasaulas.vista;

import java.util.List;

import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;
import org.iesalandalus.programacion.utilidades.Entrada;
import org.iesalandalus.programacion.reservasaulas.modelo.ModeloReservasAulas;

public class IUTextual {
	
private static final String ERROR = "ERROR: ";
private static final String NOMBRE_VALIDO = "";
private static final String CORREO_VALIDO = "";
private ModeloReservasAulas modelo;	

	public IUTextual() {
		modelo = new ModeloReservasAulas();
		Opcion.setVista(this);
	}

	public void comenzar() {
		int ordinalOpcion;
		do {
			Consola.mostrarMenu();
			ordinalOpcion = Consola.elegirOpcion();
			Opcion opcion = Opcion.getOpcionSegunOrdinal(ordinalOpcion);
			opcion.ejecutar();
		} while (ordinalOpcion != Opcion.SALIR.ordinal());
	}
	
	public void salir() {
		System.out.println("Hasta Prontooo!!!");
	}
	
	public void insertarAula() {
		Consola.mostrarCabecera("Insertar Aula");
		try {
			Aula aula = Consola.leerAula();
			modelo.insertarAula(aula);
			System.out.println("Aula insertada correctamente.");
		} catch (OperationNotSupportedException|IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}
	
	public void borrarAula() {
		Consola.mostrarCabecera("Borrar aula");
		try {
			Aula aula = Consola.leerAula();
			modelo.borrarAula(aula);
			System.out.println("Aula borrada correctamente.");
		} catch (OperationNotSupportedException|IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}
	
	public void buscarAula() {
		Consola.mostrarCabecera("Buscar aula");
		Aula aula = null;
		try {
			aula = Consola.leerAula();
			aula = modelo.buscarAula(aula);
			if (aula != null) {
				System.out.println("El aula buscada es: " + aula);
			} else {
				System.out.println("No existe ningún aula.");
			}
		} catch (IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}
	
	public void listarAulas() {
		Consola.mostrarCabecera("Listar Aulas");
		List<String> Aulas = modelo.representarAulas();
		if (!Aulas.isEmpty()) {
			for (String aula : Aulas) {
				System.out.println(aula);
			}
		} else {
			System.out.println("No hay aulas que listar.");
		}
	}
	public void insertarProfesor() {
		Consola.mostrarCabecera("Insertar Profesor");
		Profesor profesor = Consola.leerProfesor();
		String correo;
			if (profesor.getNombre().matches(NOMBRE_VALIDO)) { 
				System.out.print("Escrie un correo valido");
				correo = Entrada.cadena();
				if (profesor.getCorreo().matches(CORREO_VALIDO));
					profesor.setCorreo(correo);
				try {
			modelo.insertarProfesor(profesor);			
			System.out.println("Profesor insertado correctamente.");
		} catch (OperationNotSupportedException|IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
			}
		}
	}

	public void borrarProfesor() {
		Consola.mostrarCabecera("Borrar Profesor");
		try {
			Profesor profesor = Consola.leerProfesor();
			modelo.borrarProfesor(profesor);
			System.out.println("Profesor borrado correctamente.");
		} catch (OperationNotSupportedException|IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}
	
	public void buscarProfesor() {
		Consola.mostrarCabecera("Buscar profesor");
		Profesor profesor = null;
		try {
			profesor = Consola.leerProfesor();
			profesor = modelo.buscarProfesor(profesor);
			if (profesor != null) {
				System.out.println("El aula buscada es: " + profesor);
			} else {
				System.out.println("No existe ningún profesor con ese nombre.");
			}
		} catch (IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}
	
	public void listarProfesores() {
		Consola.mostrarCabecera("Listar Profesor");
		List<String> Profesores = modelo.representarProfesores();
		if (!Profesores.isEmpty()) {
			for (String profesor : Profesores) {
				System.out.println(profesor);
			}
		} else {
			System.out.println("No hay profesor que listar.");
		}
	}
	
	public void realizarReserva() {
		Consola.mostrarCabecera("Realizar Reserva");
		try {
			Reserva reserva = leerReserva(Consola.leerProfesor());
			modelo.realizarReserva(reserva);
			System.out.println("Has realizado correctamente la reserva.");
		} catch (OperationNotSupportedException|IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}
	
	private Reserva leerReserva(Profesor profesor) {
		
		  Aula aula = Consola.leerAula();
          Permanencia permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());
          return new Reserva(profesor, aula, permanencia);
	}
	
	public void anularReserva() {
        
		Consola.mostrarCabecera("Borrar reserva");
		try {
			Reserva reserva = leerReserva(Consola.leerProfesor());
			modelo.anularReserva(reserva);
			System.out.println("Reserva anulada correctamente.");
		} catch (OperationNotSupportedException|IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}
	
	
	
	public void listarReservas() {
		Consola.mostrarCabecera("Listar reservas");
		List<String> reservas = modelo.representarReservas();
		if (!reservas.isEmpty()) {
			for (String reserva : reservas) {
				System.out.println(reserva);
			}
		} else {
			System.out.println("No hay reservas que listar.");
		}
	}
        
        public void listarReservasAula() {
		Consola.mostrarCabecera("Listar reservas aula");
                Aula aula = Consola.leerAula();
		List<Reserva> reservasAula = modelo.getReservasAulas(aula);
		if (!reservasAula.isEmpty()) {
			for (Reserva reserva : reservasAula) {
				System.out.println(reserva);
			}
		} else {
			System.out.println("No hay reservas que listar.");
		}
	}
        public void listarReservasProfesor() {
		Consola.mostrarCabecera("Listar reservas profesor");
                Profesor profesor = Consola.leerProfesor();
		List<Reserva> reservasProfesor = modelo.getReservasProfesor(profesor);
		if (!reservasProfesor.isEmpty()) {
			for (Reserva reserva : reservasProfesor) {
				System.out.println(reserva);
			}
		} else {
			System.out.println("No hay reservas que listar.");
		}
	}
        
        public void listarReservasPermanencia() {
		Consola.mostrarCabecera("Listar reservas profesor");
                Permanencia permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());
		List<Reserva> reservasPermanencia = modelo.getReservasPermanencia(permanencia);
		if (!reservasPermanencia.isEmpty()) {
			for (Reserva reserva : reservasPermanencia) {
				System.out.println(reserva);
			}
		} else {
			System.out.println("No hay reservas que listar.");
		}
	}
        
	public void consultarDisponibilidad() {
		Consola.mostrarCabecera("Consultar disponibilidad");
		Aula aula = Consola.leerAula();
		Permanencia permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());
		boolean disponible = modelo.consultarDisponibilidad(aula, permanencia);
		if (disponible == true) {
			System.out.println("Aula disponible");
		} else {
			System.out.println("Aula no disponible");
		}

	}    
	
}
