/**
 * 
 */
package co.com.jafc.components.beans;

/**
 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
 * @project sgp-api
 * @class MapaPruebasDTO
 * @since 22/01/2014
 */
public class MapaPruebasDTO extends BaseEntity {
	
	private static final long serialVersionUID = -8094458736199756392L;
	
	private Integer id;
	private String nombre;
	private Integer usuario;
	private Integer totalPruebasConstruidas;
	private Integer totalPruebasEjecutadas;
	private Integer totalPruebasAnuladas;
	private String porcentajeAvance;
	private Integer totalPruebasSinEjecutar;
	private Integer totalHallazgos;
	
	private Integer totalPruebasSatisfactorias;
	private Integer totalPruebasInsatisfactorias;
	private Double indicadorEfectividad;
	
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 23/04/2014 
	 * @param nombre
	 * @param totalPruebasConstruidas
	 * @param totalPruebasEjecutadas
	 * @param porcentajeAvanceString
	 * @param totalPruebasSinEjecutar
	 * @param totalPruebasAnuladas
	 */
	public MapaPruebasDTO(String nombre, Integer totalPruebasConstruidas,
			Integer totalPruebasEjecutadas, String porcentajeAvanceString,
			Integer totalPruebasSinEjecutar, Integer totalPruebasAnuladas) {
		super();
		this.nombre = nombre;
		this.totalPruebasConstruidas = totalPruebasConstruidas;
		this.totalPruebasEjecutadas = totalPruebasEjecutadas;
		this.totalPruebasAnuladas = totalPruebasAnuladas;
		this.totalPruebasSinEjecutar = totalPruebasSinEjecutar;
		this.porcentajeAvance= porcentajeAvanceString;
	}
	
	

	/**
	 * Constructor con campos obligatorios para el reporte Avance Ejecucion Mapa de Pruebas.
	 *  
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 24/04/2014 
	 * @param nombre
	 * @param totalPruebasConstruidas
	 * @param totalPruebasEjecutadas
	 * @param totalPruebasAnuladas
	 * @param totalPruebasSatisfactorias
	 * @param totalPruebasInsatisfactorias 
	 */ 
	public MapaPruebasDTO(String nombre, Integer totalPruebasConstruidas,
			Integer totalPruebasEjecutadas, Integer totalPruebasSatisfactorias,
			Integer totalPruebasInsatisfactorias, Integer totalPruebasAnuladas) {
		super();
		this.nombre = nombre;
		this.totalPruebasConstruidas = totalPruebasConstruidas;
		this.totalPruebasEjecutadas = totalPruebasEjecutadas;
		this.totalPruebasSatisfactorias = totalPruebasSatisfactorias;
		this.totalPruebasInsatisfactorias = totalPruebasInsatisfactorias;
		this.totalPruebasAnuladas = totalPruebasAnuladas;
	}



	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 22/01/2014
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 22/01/2014
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 22/01/2014
	 * @return the usuario
	 */
	public Integer getUsuario() {
		return usuario;
	}
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 22/01/2014
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(Integer usuario) {
		this.usuario = usuario;
	}
	
	public MapaPruebasDTO(Integer id) {
		this.id = id;
	}
	
	public MapaPruebasDTO(String nombre, Integer usuario, Integer id) {
		super();
		this.nombre = nombre;
		this.usuario = usuario;
		this.id = id;
	}
	
	public MapaPruebasDTO() {
		super();
		
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 7/03/2014
	 * @return the totalPruebasConstruidas
	 */
	public Integer getTotalPruebasConstruidas() {
		return totalPruebasConstruidas;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 7/03/2014
	 * @param totalPruebasConstruidas
	 *            the totalPruebasConstruidas to set
	 */
	public void setTotalPruebasConstruidas(Integer totalPruebasConstruidas) {
		this.totalPruebasConstruidas = totalPruebasConstruidas;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 7/03/2014
	 * @return the totalPruebasEjecutadas
	 */
	public Integer getTotalPruebasEjecutadas() {
		return totalPruebasEjecutadas;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 7/03/2014
	 * @param totalPruebasEjecutadas
	 *            the totalPruebasEjecutadas to set
	 */
	public void setTotalPruebasEjecutadas(Integer totalPruebasEjecutadas) {
		this.totalPruebasEjecutadas = totalPruebasEjecutadas;
	}

	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 8/04/2014
	 * @description 
	 * @return
	 */
	public Integer getTotalHallazgos() {
		return totalHallazgos;
	}

	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 8/04/2014
	 * @description 
	 * @param totalHallazgos
	 */
	public void setTotalHallazgos(Integer totalHallazgos) {
		this.totalHallazgos = totalHallazgos;
	}

	public Integer getTotalPruebasAnuladas() {
		return totalPruebasAnuladas;
	}

	public void setTotalPruebasAnuladas(Integer totalPruebasAnuladas) {
		this.totalPruebasAnuladas = totalPruebasAnuladas;
	}

	/**
	 * 
	 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
	 * @since 22/04/2014
	 * @return
	 */
	public String getPorcentajeAvance() {
		return porcentajeAvance;
	}
	/**
	 * 
	 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
	 * @since 22/04/2014
	 * @param porcentajeAvance
	 */
	public void setPorcentajeAvance(String porcentajeAvance) {
		this.porcentajeAvance = porcentajeAvance;
	}
	/**
	 * 
	 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
	 * @since 22/04/2014
	 * @return
	 */
	public Integer getTotalPruebasSinEjecutar() {
		return totalPruebasSinEjecutar;
	}
	/**
	 * 
	 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
	 * @since 22/04/2014
	 * @param totalPruebasSinEjecutar
	 */
	public void setTotalPruebasSinEjecutar(Integer totalPruebasSinEjecutar) {
		this.totalPruebasSinEjecutar = totalPruebasSinEjecutar;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 23/04/2014 
	 * @return the totalPruebasSatisfactorias 
	 */
	public Integer getTotalPruebasSatisfactorias() {
		return totalPruebasSatisfactorias;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 23/04/2014 
	 * @param totalPruebasSatisfactorias the totalPruebasSatisfactorias to set 
	 */
	public void setTotalPruebasSatisfactorias(Integer totalPruebasSatisfactorias) {
		this.totalPruebasSatisfactorias = totalPruebasSatisfactorias;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 23/04/2014 
	 * @return the totalPruebasInsatisfactorias 
	 */
	public Integer getTotalPruebasInsatisfactorias() {
		return totalPruebasInsatisfactorias;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 23/04/2014 
	 * @param totalPruebasInsatisfactorias the totalPruebasInsatisfactorias to set 
	 */
	public void setTotalPruebasInsatisfactorias(Integer totalPruebasInsatisfactorias) {
		this.totalPruebasInsatisfactorias = totalPruebasInsatisfactorias;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 23/04/2014 
	 * @return the indicadorEfectividad 
	 */
	public Double getIndicadorEfectividad() {
		return indicadorEfectividad;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 23/04/2014 
	 * @param indicadorEfectividad the indicadorEfectividad to set 
	 */
	public void setIndicadorEfectividad(Double indicadorEfectividad) {
		this.indicadorEfectividad = indicadorEfectividad;
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 24/04/2014
	 * @description Si el parametro porcentaje = true entonces devuelve el calculo como una representacion
	 *              porcentual(%) de lo contrario devuelve la probabilidad.
	 * @param porcentaje
	 * @return
	 */
	public Double getCalculatedPorcentajeAvance(boolean porcentaje) {
		Integer divisor = totalPruebasConstruidas - totalPruebasAnuladas;
		if(divisor != 0) {
			Double val = totalPruebasEjecutadas.doubleValue() / divisor;
			Double resultado = val.doubleValue();
			return porcentaje ? resultado * 100 : resultado;
		}
		return 0d;
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 24/04/2014
	 * @description 
	 * @return
	 */
	public Integer getCalculatedPruebasSinEjecutar() {
		return totalPruebasConstruidas - (totalPruebasEjecutadas + totalPruebasEjecutadas);
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 24/04/2014
	 * @description Si el parametro porcentaje = true entonces devuelve el calculo como una representacion
	 *              porcentual(%) de lo contrario devuelve la probabilidad.
	 * @param porcentaje
	 * @return
	 */
	public Double getCalculatedIndicadorCalidadICF(boolean porcentaje) {
		if(totalPruebasEjecutadas != 0) {
			Double d = totalPruebasInsatisfactorias.doubleValue() / totalPruebasEjecutadas.doubleValue();
			Double resultado = 1 - d;
			return porcentaje ? resultado * 100 : resultado;
		}
		return 0d;
	}
	
}
