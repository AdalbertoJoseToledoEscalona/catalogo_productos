/**
 * 
 */
package modelo.beans;

import java.util.List;
import java.util.Map;

import modelo.utils.BaseDatos;

/**
 * @author adalberto
 *
 */
public class FiltroReporte {

	private BaseDatos bd;
	private String nombre;
	private String etiqueta;
	private String tipo;
	private String nombreOpcionTodas;
	private List<Map<String, Object>> datos;
	private boolean llenarPorReporte;
	
	/**
	 * Columna de la Base de Datos que llena la tabla
	 */
	private int numColumna;

	/**
	 * @param bd
	 * @param nombre
	 * @param etiqueta
	 * @param tipo
	 * @param nombreOpcionTodas
	 * @param numColumna DE BASE DE DATOS DEL REPORTE
	 */
	public FiltroReporte(BaseDatos bd, String nombre, String etiqueta,
			String tipo, String nombreOpcionTodas, int numColumna) {
		super();
		this.bd = bd;
		this.nombre = nombre;
		this.etiqueta = etiqueta;
		this.tipo = tipo;
		this.nombreOpcionTodas = nombreOpcionTodas;
		this.numColumna = numColumna;
		llenarPorReporte = false;
	}
	
	/**
	 * @param nombre
	 * @param etiqueta
	 * @param tipo
	 * @param nombreOpcionTodas
	 * @param datos
	 * @param numColumna DE BASE DE DATOS DEL REPORTE
	 */
	public FiltroReporte(String nombre, String etiqueta, String tipo,
			String nombreOpcionTodas, List<Map<String, Object>> datos, int numColumna) {
		super();
		this.nombre = nombre;
		this.etiqueta = etiqueta;
		this.tipo = tipo;
		this.nombreOpcionTodas = nombreOpcionTodas;
		this.datos = datos;
		this.numColumna = numColumna;
		llenarPorReporte = false;
	}
	
	/**
	 * @param nombre
	 * @param etiqueta
	 * @param tipo
	 * @param nombreOpcionTodas
	 * @param numColumna DE BASE DE DATOS DEL REPORTE
	 */
	public FiltroReporte(String nombre, String etiqueta, String tipo,
			String nombreOpcionTodas, int numColumna) {
		super();
		this.nombre = nombre;
		this.etiqueta = etiqueta;
		this.tipo = tipo;
		this.nombreOpcionTodas = nombreOpcionTodas;
		this.numColumna = numColumna;
		llenarPorReporte = true;
	}
	
	/**
	 * @return the bd
	 */
	public BaseDatos getBd() {
		return bd;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @return the etiqueta
	 */
	public String getEtiqueta() {
		return etiqueta;
	}
	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * @return the nombreOpcionTodas
	 */
	public String getNombreOpcionTodas() {
		return nombreOpcionTodas;
	}
	/**
	 * @return the datos
	 */
	public List<Map<String, Object>> getDatos() {
		return datos;
	}

	/**
	 * @return the llenarPorReporte
	 */
	public boolean isLlenarPorReporte() {
		return llenarPorReporte;
	}

	/**
	 * @return the numColumna
	 */
	public int getNumColumna() {
		return numColumna;
	}

}
