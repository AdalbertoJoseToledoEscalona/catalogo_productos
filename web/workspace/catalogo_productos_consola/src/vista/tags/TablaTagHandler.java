/**
 * 
 */
package vista.tags;

import java.io.IOException;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import modelo.beans.FiltroReporte;
import modelo.utils.BaseDatos;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author adalberto
 * 
 */
public class TablaTagHandler extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String titulo;
	private BaseDatos bd;
	private Integer pk;
	private List<String> encabezado;
	private List<Integer> columnaEncabezado;
	private Integer filasPorPagina;
	private Boolean paginacionJS;
	private Boolean ordenarCampos;
	private Boolean usarAjax;
	private List<FiltroReporte> filtrosReporte;
	private Map<Integer, String> tipoColumna;
	private String editar;
	private String eliminar;
	private String textoEditar;
	private String textoEliminar;
	private String textoNoFilas;
	private String mensajeConfirmacionEliminar;
	private String pagActual;
	private String id;

	/**
	 * 
	 */
	public TablaTagHandler() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo
	 *            the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return the bd
	 */
	public BaseDatos getBd() {
		return bd;
	}

	/**
	 * @param bd
	 *            the bd to set
	 */
	public void setBd(BaseDatos bd) {
		this.bd = bd;
	}

	/**
	 * @return the pk
	 */
	public Integer getPk() {
		return pk;
	}

	/**
	 * @param pk
	 *            the pk to set
	 */
	public void setPk(Integer pk) {
		this.pk = pk;
	}

	/**
	 * @return the encabezado
	 */
	public List<String> getEncabezado() {
		return encabezado;
	}

	/**
	 * @param encabezado
	 *            the encabezado to set
	 */
	public void setEncabezado(List<String> encabezado) {
		this.encabezado = encabezado;
	}

	/**
	 * @return the columnaEncabezado
	 */
	public List<Integer> getColumnaEncabezado() {
		return columnaEncabezado;
	}

	/**
	 * @param columnaEncabezado
	 *            the columnaEncabezado to set
	 */
	public void setColumnaEncabezado(List<Integer> columnaEncabezado) {
		this.columnaEncabezado = columnaEncabezado;
	}

	/**
	 * @return the filasPorPagina
	 */
	public Integer getFilasPorPagina() {
		return filasPorPagina;
	}

	/**
	 * @param filasPorPagina
	 *            the filasPorPagina to set
	 */
	public void setFilasPorPagina(Integer filasPorPagina) {
		this.filasPorPagina = filasPorPagina;
	}

	/**
	 * @return the paginacionJS
	 */
	public Boolean getPaginacionJS() {
		return paginacionJS;
	}

	/**
	 * @param paginacionJS
	 *            the paginacionJS to set
	 */
	public void setPaginacionJS(Boolean paginacionJS) {
		this.paginacionJS = paginacionJS;
	}

	/**
	 * @return the ordenarCampos
	 */
	public Boolean getOrdenarCampos() {
		return ordenarCampos;
	}

	/**
	 * @param ordenarCampos
	 *            the ordenarCampos to set
	 */
	public void setOrdenarCampos(Boolean ordenarCampos) {
		this.ordenarCampos = ordenarCampos;
	}

	/**
	 * @return the usarAjax
	 */
	public Boolean getUsarAjax() {
		return usarAjax;
	}

	/**
	 * @param usarAjax
	 *            the usarAjax to set
	 */
	public void setUsarAjax(Boolean usarAjax) {
		this.usarAjax = usarAjax;
	}

	/**
	 * @return the filtrosReporte
	 */
	public List<FiltroReporte> getFiltrosReporte() {
		return filtrosReporte;
	}

	/**
	 * @param filtrosReporte
	 *            the filtrosReporte to set
	 */
	public void setFiltrosReporte(List<FiltroReporte> filtrosReporte) {
		this.filtrosReporte = filtrosReporte;
	}

	/**
	 * @return the tipoColumna
	 */
	public Map<Integer, String> getTipoColumna() {
		return tipoColumna;
	}

	/**
	 * @param tipoColumna
	 *            the tipoColumna to set
	 */
	public void setTipoColumna(Map<Integer, String> tipoColumna) {
		this.tipoColumna = tipoColumna;
	}

	/**
	 * @return the editar
	 */
	public String getEditar() {
		return editar;
	}

	/**
	 * @param editar
	 *            the editar to set
	 */
	public void setEditar(String editar) {
		this.editar = editar;
	}

	/**
	 * @return the eliminar
	 */
	public String getEliminar() {
		return eliminar;
	}

	/**
	 * @param eliminar
	 *            the eliminar to set
	 */
	public void setEliminar(String eliminar) {
		this.eliminar = eliminar;
	}

	/**
	 * @return the textoEditar
	 */
	public String getTextoEditar() {
		return textoEditar;
	}

	/**
	 * @param textoEditar
	 *            the textoEditar to set
	 */
	public void setTextoEditar(String textoEditar) {
		this.textoEditar = textoEditar;
	}

	/**
	 * @return the textoEliminar
	 */
	public String getTextoEliminar() {
		return textoEliminar;
	}

	/**
	 * @param textoEliminar
	 *            the textoEliminar to set
	 */
	public void setTextoEliminar(String textoEliminar) {
		this.textoEliminar = textoEliminar;
	}

	/**
	 * @return the textoNoFilas
	 */
	public String getTextoNoFilas() {
		return textoNoFilas;
	}

	/**
	 * @param textoNoFilas
	 *            the textoNoFilas to set
	 */
	public void setTextoNoFilas(String textoNoFilas) {
		this.textoNoFilas = textoNoFilas;
	}

	/**
	 * @return the mensajeConfirmacionEliminar
	 */
	public String getMensajeConfirmacionEliminar() {
		return mensajeConfirmacionEliminar;
	}

	/**
	 * @param mensajeConfirmacionEliminar
	 *            the mensajeConfirmacionEliminar to set
	 */
	public void setMensajeConfirmacionEliminar(
			String mensajeConfirmacionEliminar) {
		this.mensajeConfirmacionEliminar = mensajeConfirmacionEliminar;
	}

	/**
	 * @return the pagActual
	 */
	public String getPagActual() {
		return pagActual;
	}

	/**
	 * @param pagActual the pagActual to set
	 */
	public void setPagActual(String pagActual) {
		this.pagActual = pagActual;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			
			if(getId() == null || getId().trim() == ""){
				setId("reporte_tabla");
			}

			// pageContext.getRequest().setAttribute("bd", bd);
			Enumeration<String> parameterNames = pageContext.getRequest()
					.getParameterNames();

			String condicionFiltro = "";
			final String AND = " AND ";
			// FILTROS
			while (parameterNames.hasMoreElements()) {
				String parameter = (String) parameterNames.nextElement();
				if (parameter.endsWith("_select")
						|| parameter.endsWith("_text")
						|| parameter.endsWith("_number")
						|| parameter.endsWith("_date")
						|| parameter.endsWith("_datetime")
						|| parameter.endsWith("_email")) {

					String nombreFiltro = parameter.substring(0,
							parameter.lastIndexOf("_"));
					String tipoFiltro = parameter.substring(parameter
							.lastIndexOf("_") + 1);
					if (pageContext.getRequest().getParameter(parameter) != null) {
						if (tipoFiltro.equals("select")) {
							if (!pageContext.getRequest()
									.getParameter(parameter).equals("*")) {
								condicionFiltro += nombreFiltro
										+ " = '"
										+ pageContext.getRequest()
												.getParameter(parameter) + "'"
										+ AND;
							}
						} else if (tipoFiltro.equals("text")
								|| tipoFiltro.equals("email")) {
							if (!pageContext.getRequest()
									.getParameter(parameter).trim().isEmpty()) {
								condicionFiltro += nombreFiltro
										+ " = '"
										+ pageContext.getRequest()
												.getParameter(parameter).trim()
										+ "'" + AND;
							}
						} else if (tipoFiltro.equals("number")) {
							if (!pageContext.getRequest()
									.getParameter(parameter).trim().isEmpty()) {
								condicionFiltro += nombreFiltro
										+ " = "
										+ pageContext.getRequest()
												.getParameter(parameter).trim()
										+ AND;
							}
						}
					}
				}
			}

			if (!condicionFiltro.isEmpty()) {
				condicionFiltro = condicionFiltro.substring(0,
						condicionFiltro.length() - AND.length());

				if (bd.condicion == null) {
					bd.condicion = condicionFiltro;
				} else {
					bd.condicion = "(" + bd.condicion + ") AND ("
							+ condicionFiltro + ")";
				}
			}

			// FIN FILTROS

			// ORDENAMIENTO
			parameterNames = pageContext.getRequest().getParameterNames();
			if (pageContext.getRequest().getParameter("ordenar_" + getId()) != null) {
				List<String> camposOrdenList = new ArrayList<String>();
				List<Boolean> ascList = new ArrayList<Boolean>();
				while (parameterNames.hasMoreElements()) {
					String parameter = (String) parameterNames.nextElement();

					if (parameter.endsWith("_ordenar_" + getId())) {

						Boolean ascLocal = null;

						if (!pageContext.getRequest().getParameter(parameter)
								.equals("null")) {
							ascLocal = Boolean.parseBoolean(pageContext
									.getRequest().getParameter(parameter));
						}

						if (ascLocal == null) {
							ascLocal = true;
						} else if (ascLocal == true) {
							ascLocal = false;
						} else if (ascLocal == false) {
							ascLocal = null;
						}

						if (ascLocal != null) {

							camposOrdenList.add(parameter
									.split("_ordenar_" + getId())[0]);

							ascList.add(ascLocal);
						}
					}

				}

				if (camposOrdenList.size() > 0) {
					String[] camposOrdenLocal = new String[camposOrdenList
							.size()];
					Boolean[] ascLocal = new Boolean[ascList.size()];

					camposOrdenLocal = camposOrdenList
							.toArray(camposOrdenLocal);
					ascLocal = ascList.toArray(ascLocal);

					bd.camposOrden = camposOrdenLocal;
					bd.asc = ascLocal;
				}
			}
			// FIN ORDENAMIENTO

			bd.generarSelect(bd.campos, bd.tablas, bd.uniones, bd.condicion,
					bd.disctinct, bd.camposOrden, bd.asc, bd.camposGroup,
					bd.condicionHaving);

			// System.out.println(bd.sql);

			if (bd.usarJDBC) {
				bd.abrirConexionJDBC(null);
			} else {
				bd.abrirConexionDataSource(null);
			}

			bd.stmt = bd.con.createStatement();
			bd.rs = bd.stmt.executeQuery(bd.sql);

			ResultSetMetaData rsmd = bd.rs.getMetaData();

			pageContext.getOut().println(
					"<TABLE id=\"" + getId() + "\" class=\"reporte_tabla\">");
			if (getTitulo() != null) {
				pageContext.getOut().println(
						"<CAPTION>" + getTitulo() + "</CAPTION>");
			} else {
				pageContext.getOut()
						.println(
								"<CAPTION>" + rsmd.getTableName(getPk())
										+ "</CAPTION>");
			}
			pageContext.getOut().println(
					"<TR class=\"reporte_tabla_encabezado\">");

			if (getEncabezado() == null) {
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {

					String imgOrden = "";
					Boolean ascCampo = null;
					if (bd.camposOrden != null) {
						for (int numCampo = 0; numCampo < bd.camposOrden.length; numCampo++) {
							boolean imgOrdenar = false;
							if (NumberUtils.isNumber(bd.camposOrden[numCampo])) {
								if (Integer.parseInt(bd.camposOrden[numCampo]) == i) {
									imgOrdenar = true;
								}
							} else {
								String partes[] = bd.camposOrden[numCampo]
										.trim().split("[.]");

								switch (partes.length) {
								case 1:
									if (partes[0].trim().equals(
											rsmd.getColumnName(i))) {
										imgOrdenar = true;
									}
									break;
								case 2:
									if (partes[0].trim().equals(
											rsmd.getTableName(i))
											&& partes[1].trim().equals(
													rsmd.getColumnName(i))) {
										imgOrdenar = true;
									}
									break;
								case 3:
									if (rsmd.getSchemaName(i).isEmpty()) {
										if (partes[0].trim().equals(
												rsmd.getCatalogName(i))
												&& partes[1].trim().equals(
														rsmd.getTableName(i))
												&& partes[2].trim().equals(
														rsmd.getColumnName(i))) {
											imgOrdenar = true;
										}
									} else {
										if (partes[0].trim().equals(
												rsmd.getSchemaName(i))
												&& partes[1].trim().equals(
														rsmd.getTableName(i))
												&& partes[2].trim().equals(
														rsmd.getColumnName(i))) {
											imgOrdenar = true;
										}
									}
									break;
								case 4:
									if (partes[0].trim().equals(
											rsmd.getCatalogName(i))
											&& partes[1].trim().equals(
													rsmd.getSchemaName(i))
											&& partes[2].trim().equals(
													rsmd.getTableName(i))
											&& partes[3].trim().equals(
													rsmd.getColumnName(i))) {
										imgOrdenar = true;
									}
									break;

								default:
									if (rsmd.getSchemaName(i).isEmpty()) {
										if (partes[partes.length - 3].trim()
												.equals(rsmd.getCatalogName(i))
												&& partes[partes.length - 2]
														.trim()
														.equals(rsmd
																.getTableName(i))
												&& partes[partes.length - 1]
														.trim()
														.equals(rsmd
																.getColumnName(i))) {
											imgOrdenar = true;
										}
									} else {
										if (partes[partes.length - 4].trim()
												.equals(rsmd.getCatalogName(i))
												&& partes[partes.length - 3]
														.trim()
														.equals(rsmd
																.getSchemaName(i))
												&& partes[partes.length - 2]
														.trim()
														.equals(rsmd
																.getTableName(i))
												&& partes[partes.length - 1]
														.trim()
														.equals(rsmd
																.getColumnName(i))) {
											imgOrdenar = true;
										}
									}
									break;
								}

							}

							if (imgOrdenar) {
								if (bd.asc == null || bd.asc[numCampo]) {
									imgOrden = " <IMG alt=\"asc\"class=\"reporte_tabla_img_orden\" src=\"/"
											+ ((HttpServletRequest) pageContext
													.getRequest())
													.getContextPath()
											+ "/img/reportes/asc.png" + "\">";
									ascCampo = true;
								} else {
									imgOrden = " <IMG alt=\"desc\" class=\"reporte_tabla_img_orden\" src=\"/"
											+ ((HttpServletRequest) pageContext
													.getRequest())
													.getContextPath()
											+ "/img/reportes/desc.png" + "\">";
									ascCampo = false;
								}
							}
						}
					}

					if (getOrdenarCampos() != null && getOrdenarCampos()) {

						String campoOrdenar = "";

						if (!rsmd.getCatalogName(i).isEmpty()) {
							campoOrdenar += "." + rsmd.getCatalogName(i);
						}

						if (!rsmd.getSchemaName(i).isEmpty()) {
							campoOrdenar += "." + rsmd.getSchemaName(i);
						}

						if (!rsmd.getTableName(i).isEmpty()) {
							campoOrdenar += "." + rsmd.getTableName(i);
						}

						if (!rsmd.getColumnName(i).isEmpty()) {
							campoOrdenar += "." + rsmd.getColumnName(i);
						} else {
							campoOrdenar += "." + rsmd.getColumnLabel(i);
						}

						if (!campoOrdenar.isEmpty()) {
							campoOrdenar = campoOrdenar.substring(1);
						}

						pageContext
								.getOut()
								.println(
										"<TH>"
												+ "<A href=\"?ordenar_" + getId() + "=true&"
												+ campoOrdenar
												+ "_ordenar_" + getId() + "="
												+ ascCampo
												+ "&" + getId() + "_refrescando=true\" onclick=\"ordenarTablaReporte('"
												+ campoOrdenar + "', "
												+ ascCampo + ", "
												+ getUsarAjax() + ", '"
												+ getPagActual() + "', '"
												+ getId()
												+ "', this); return false;\">"
												+ rsmd.getColumnName(i)
												+ "</A>" + imgOrden + "</TH>");
					} else {
						pageContext.getOut().println(
								"<TH>" + rsmd.getColumnName(i) + imgOrden
										+ "</TH>");
					}
				}
			} else {
				for (int i = 0; i < getEncabezado().size(); i++) {
					String imgOrden = "";
					Boolean ascCampo = null;
					if (bd.camposOrden != null) {
						for (int numCampo = 0; numCampo < bd.camposOrden.length; numCampo++) {
							boolean imgOrdenar = false;
							if (NumberUtils.isNumber(bd.camposOrden[numCampo])) {
								if (getColumnaEncabezado() == null) {
									if (Integer
											.parseInt(bd.camposOrden[numCampo]) == (i + 1)) {
										imgOrdenar = true;
									}
								} else {
									if (Integer
											.parseInt(bd.camposOrden[numCampo]) == getColumnaEncabezado()
											.get(i)) {
										imgOrdenar = true;
									}
								}
							} else {
								String partes[] = bd.camposOrden[numCampo]
										.trim().split("[.]");

								switch (partes.length) {
								case 1:
									if (getColumnaEncabezado() == null) {
										if (partes[0].trim().equals(
												rsmd.getColumnName(i + 1))) {
											imgOrdenar = true;
										}
									} else {
										if (partes[0]
												.trim()
												.equals(rsmd
														.getColumnName(getColumnaEncabezado()
																.get(i)))) {
											imgOrdenar = true;
										}
									}
									break;
								case 2:
									if (getColumnaEncabezado() == null) {
										if (partes[0].trim().equals(
												rsmd.getTableName(i + 1))
												&& partes[1]
														.trim()
														.equals(rsmd
																.getColumnName(i + 1))) {
											imgOrdenar = true;
										}
									} else {
										if (partes[0]
												.trim()
												.equals(rsmd
														.getTableName(getColumnaEncabezado()
																.get(i)))
												&& partes[1]
														.trim()
														.equals(rsmd
																.getColumnName(getColumnaEncabezado()
																		.get(i)))) {
											imgOrdenar = true;
										}
									}
									break;
								case 3:
									if (getColumnaEncabezado() == null) {
										if (rsmd.getSchemaName(i + 1).isEmpty()) {
											if (partes[0].trim().equals(
													rsmd.getCatalogName(i + 1))
													&& partes[1]
															.trim()
															.equals(rsmd
																	.getTableName(i + 1))
													&& partes[2]
															.trim()
															.equals(rsmd
																	.getColumnName(i + 1))) {
												imgOrdenar = true;
											}
										} else {
											if (partes[0].trim().equals(
													rsmd.getSchemaName(i + 1))
													&& partes[1]
															.trim()
															.equals(rsmd
																	.getTableName(i + 1))
													&& partes[2]
															.trim()
															.equals(rsmd
																	.getColumnName(i + 1))) {
												imgOrdenar = true;
											}
										}
									} else {
										if (rsmd.getSchemaName(
												getColumnaEncabezado().get(i))
												.isEmpty()) {
											if (partes[0]
													.trim()
													.equals(rsmd
															.getCatalogName(getColumnaEncabezado()
																	.get(i)))
													&& partes[1]
															.trim()
															.equals(rsmd
																	.getTableName(getColumnaEncabezado()
																			.get(i)))
													&& partes[2]
															.trim()
															.equals(rsmd
																	.getColumnName(getColumnaEncabezado()
																			.get(i)))) {
												imgOrdenar = true;
											}
										} else {
											if (partes[0]
													.trim()
													.equals(rsmd
															.getSchemaName(getColumnaEncabezado()
																	.get(i)))
													&& partes[1]
															.trim()
															.equals(rsmd
																	.getTableName(getColumnaEncabezado()
																			.get(i)))
													&& partes[2]
															.trim()
															.equals(rsmd
																	.getColumnName(getColumnaEncabezado()
																			.get(i)))) {
												imgOrdenar = true;
											}
										}
									}
									break;
								case 4:
									if (getColumnaEncabezado() == null) {
										if (partes[0].trim().equals(
												rsmd.getCatalogName(i + 1))
												&& partes[1]
														.trim()
														.equals(rsmd
																.getSchemaName(i + 1))
												&& partes[2]
														.trim()
														.equals(rsmd
																.getTableName(i + 1))
												&& partes[3]
														.trim()
														.equals(rsmd
																.getColumnName(i + 1))) {
											imgOrdenar = true;
										}
									} else {
										if (partes[0]
												.trim()
												.equals(rsmd
														.getCatalogName(getColumnaEncabezado()
																.get(i)))
												&& partes[1]
														.trim()
														.equals(rsmd
																.getSchemaName(getColumnaEncabezado()
																		.get(i)))
												&& partes[2]
														.trim()
														.equals(rsmd
																.getTableName(getColumnaEncabezado()
																		.get(i)))
												&& partes[3]
														.trim()
														.equals(rsmd
																.getColumnName(getColumnaEncabezado()
																		.get(i)))) {
											imgOrdenar = true;
										}
									}
									break;

								default:
									if (getColumnaEncabezado() == null) {
										if (rsmd.getSchemaName(i + 1).isEmpty()) {
											if (partes[partes.length - 3]
													.trim()
													.equals(rsmd
															.getCatalogName(i + 1))
													&& partes[partes.length - 2]
															.trim()
															.equals(rsmd
																	.getTableName(i + 1))
													&& partes[partes.length - 1]
															.trim()
															.equals(rsmd
																	.getColumnName(i + 1))) {
												imgOrdenar = true;
											}
										} else {

											if (partes[partes.length - 4]
													.trim()
													.equals(rsmd
															.getCatalogName(i + 1))
													&& partes[partes.length - 3]
															.trim()
															.equals(rsmd
																	.getSchemaName(i + 1))
													&& partes[partes.length - 2]
															.trim()
															.equals(rsmd
																	.getTableName(i + 1))
													&& partes[partes.length - 1]
															.trim()
															.equals(rsmd
																	.getColumnName(i + 1))) {
												imgOrdenar = true;
											}

										}
									} else {
										if (rsmd.getSchemaName(
												getColumnaEncabezado().get(i))
												.isEmpty()) {

											if (partes[partes.length - 3]
													.trim()
													.equals(rsmd
															.getCatalogName(getColumnaEncabezado()
																	.get(i)))
													&& partes[partes.length - 2]
															.trim()
															.equals(rsmd
																	.getTableName(getColumnaEncabezado()
																			.get(i)))
													&& partes[partes.length - 1]
															.trim()
															.equals(rsmd
																	.getColumnName(getColumnaEncabezado()
																			.get(i)))) {
												imgOrdenar = true;
											}

										} else {
											if (partes[partes.length - 4]
													.trim()
													.equals(rsmd
															.getCatalogName(getColumnaEncabezado()
																	.get(i)))
													&& partes[partes.length - 3]
															.trim()
															.equals(rsmd
																	.getSchemaName(getColumnaEncabezado()
																			.get(i)))
													&& partes[partes.length - 2]
															.trim()
															.equals(rsmd
																	.getTableName(getColumnaEncabezado()
																			.get(i)))
													&& partes[partes.length - 1]
															.trim()
															.equals(rsmd
																	.getColumnName(getColumnaEncabezado()
																			.get(i)))) {
												imgOrdenar = true;
											}
										}
									}
									break;
								}
							}

							if (imgOrdenar) {
								if (bd.asc == null || bd.asc[numCampo]) {
									imgOrden = " <IMG alt=\"asc\"class=\"reporte_tabla_img_orden\" src=\""
											+ ((HttpServletRequest) pageContext
													.getRequest())
													.getContextPath()
											+ "/img/reportes/asc.png" + "\">";
									ascCampo = true;
								} else {
									imgOrden = " <IMG alt=\"desc\" class=\"reporte_tabla_img_orden\" src=\""
											+ ((HttpServletRequest) pageContext
													.getRequest())
													.getContextPath()
											+ "/img/reportes/desc.png" + "\">";
									ascCampo = false;
								}
							}
						}
					}

					if (getOrdenarCampos() != null && getOrdenarCampos()) {
						String campoOrdenar = "";
						if (getColumnaEncabezado() == null) {

							if (!rsmd.getCatalogName(i + 1).isEmpty()) {
								campoOrdenar += "."
										+ rsmd.getCatalogName(i + 1);
							}

							if (!rsmd.getSchemaName(i + 1).isEmpty()) {
								campoOrdenar += "." + rsmd.getSchemaName(i + 1);
							}

							if (!rsmd.getTableName(i + 1).isEmpty()) {
								campoOrdenar += "." + rsmd.getTableName(i + 1);
							}

							if (!rsmd.getColumnName(i + 1).isEmpty()) {
								campoOrdenar += "." + rsmd.getColumnName(i + 1);
							} else {
								campoOrdenar += "."
										+ rsmd.getColumnLabel(i + 1);
							}

						} else {

							if (!rsmd.getCatalogName(
									getColumnaEncabezado().get(i)).isEmpty()) {
								campoOrdenar += "."
										+ rsmd.getCatalogName(getColumnaEncabezado()
												.get(i));
							}

							if (!rsmd.getSchemaName(
									getColumnaEncabezado().get(i)).isEmpty()) {
								campoOrdenar += "."
										+ rsmd.getSchemaName(getColumnaEncabezado()
												.get(i));
							}

							if (!rsmd.getTableName(
									getColumnaEncabezado().get(i)).isEmpty()) {
								campoOrdenar += "."
										+ rsmd.getTableName(getColumnaEncabezado()
												.get(i));
							}

							if (!rsmd.getColumnName(
									getColumnaEncabezado().get(i)).isEmpty()) {
								campoOrdenar += "."
										+ rsmd.getColumnName(getColumnaEncabezado()
												.get(i));
							} else {
								campoOrdenar += "."
										+ rsmd.getColumnLabel(getColumnaEncabezado()
												.get(i));
							}

						}

						if (!campoOrdenar.isEmpty()) {
							campoOrdenar = campoOrdenar.substring(1);
						}

						pageContext
								.getOut()
								.println(
										"<TH>"
												+ "<A  href=\"?ordenar_" + getId() + "=true&"
												+ campoOrdenar
												+ "_ordenar_" + getId() + "="
												+ ascCampo
												+ "&" + getId() + "_refrescando=true\" onclick=\"ordenarTablaReporte('"
												+ campoOrdenar + "', "
												+ ascCampo + ", "
												+ getUsarAjax() + ", '"
												+ getPagActual() + "', '"
												+ getId()
												+ "', this); return false;\">"
												+ getEncabezado().get(i)
												+ "</A>" + imgOrden + "</TH>");
					} else {
						pageContext.getOut().println(
								"<TH>" + getEncabezado().get(i) + imgOrden
										+ "</TH>");
					}
				}

				if (getEditar() != null) {
					pageContext.getOut().println("<TH></TH>");
				}

				if (getEliminar() != null) {
					pageContext.getOut().println("<TH></TH>");
				}
			}
			pageContext.getOut().println("</TR>");
			Object datoPkAnterior = null;
			List<Object[]> tdsDato = new ArrayList<Object[]>();
			List<Integer[]> rowspan = new ArrayList<Integer[]>();
			Integer indiceIgual[] = new Integer[rsmd.getColumnCount()];
			int contadorPrimario = 0;
			int contadorSecundario = 0;
			Map<String, Set<String>> datosFiltrosMap = new HashMap<String, Set<String>>();
			int maxRowsPan = 1;
			String qsTodosDatosActual = "";
			String qsTodosDatosNext = "";
			while (bd.rs.next()) {
				if(getFiltrosReporte() != null){
					/* LLenar los filtros que hacen falta llenar */
					if (pageContext.getRequest().getParameter(
							getId() + "_refrescando") == null) {
						for (int i = 0; i < getFiltrosReporte().size(); i++) {
							FiltroReporte filtro = getFiltrosReporte().get(i);
							if (filtro.getTipo().equals("select")
									&& filtro.isLlenarPorReporte()) {
								Set<String> datos = new HashSet<String>();
								if (datosFiltrosMap.containsKey(filtro.getNombre())) {
									datos = datosFiltrosMap.get(filtro.getNombre());
								}
	
								if (bd.rs.getString(filtro.getNumColumna()) != null) {
									datos.add(bd.rs.getString(filtro
											.getNumColumna()));
								}
	
								datosFiltrosMap.put(filtro.getNombre(), datos);
							}
						}
					}
					/* Fin LLenar los filtros que hacen falta llenar */
				}

				if ((datoPkAnterior != null
						&& !bd.rs.getObject(getPk()).equals(datoPkAnterior)) || (datoPkAnterior == null)) {

					qsTodosDatosActual = qsTodosDatosNext;

					if (!qsTodosDatosActual.isEmpty()) {
						qsTodosDatosActual = qsTodosDatosActual.substring(0,
								qsTodosDatosActual.length() - 1);
					}

					for (int fila = 0; fila < tdsDato.size(); fila++) {
						if (fila == 0) {
							contadorPrimario++;
							contadorSecundario = 0;
						}
						contadorSecundario++;
						pageContext
								.getOut()
								.println(
										"<TR id=\"" + getId() + "_tr_"
												+ contadorPrimario
												+ "_"
												+ contadorSecundario
												+ "\" class=\""
												+ ((contadorPrimario % 2 == 0) ? getId() + "reporte_tabla_tr_par"
														: "reporte_tabla_tr_impar")
												+ "\">");

						if (getEncabezado() == null) {
							for (int i = 1; i <= rsmd.getColumnCount(); i++) {
								if (fila == 0
										|| (fila > 0 && ((tdsDato.get(fila)[i - 1] == null && tdsDato
												.get(fila - 1)[i - 1] != null)
												|| (tdsDato.get(fila)[i - 1] != null && tdsDato
														.get(fila - 1)[i - 1] == null) || (tdsDato
												.get(fila)[i - 1] != null && !tdsDato
												.get(fila)[i - 1]
												.equals(tdsDato.get(fila - 1)[i - 1]))))) {

									/* TIPO DE DATOS */
									Object datoImprimir = (tdsDato.get(fila)[i - 1] == null) ? ""
											: tdsDato.get(fila)[i - 1];

									if (tdsDato.get(fila)[i - 1] != null
											&& getTipoColumna() != null
											&& getTipoColumna().containsKey(i)) {

										if (getTipoColumna().get(i).equals(
												"url_archivo")) {
											if (((String) tdsDato.get(fila)[i - 1])
													.toLowerCase().endsWith(
															".jpg")
													|| ((String) tdsDato
															.get(fila)[i - 1])
															.toLowerCase()
															.endsWith(".png")
													|| ((String) tdsDato
															.get(fila)[i - 1])
															.toLowerCase()
															.endsWith(".gif")
													|| ((String) tdsDato
															.get(fila)[i - 1])
															.toLowerCase()
															.endsWith(".jpeg")) {
												datoImprimir = "<IMG src=\""
														+ ((HttpServletRequest) pageContext
																.getRequest())
																.getContextPath()
														+ "/"
														+ tdsDato.get(fila)[i - 1]
														+ "\" class=\"reporte_tabla_url_archivo_img\">";
											} else if (((String) tdsDato
													.get(fila)[i])
													.toLowerCase().endsWith(
															".mid")
													|| ((String) tdsDato
															.get(fila)[i - 1])
															.toLowerCase()
															.endsWith(".midi")
													|| ((String) tdsDato
															.get(fila)[i - 1])
															.toLowerCase()
															.endsWith(".aif")
													|| ((String) tdsDato
															.get(fila)[i - 1])
															.toLowerCase()
															.endsWith(".aiff")
													|| ((String) tdsDato
															.get(fila)[i - 1])
															.toLowerCase()
															.endsWith(".au")
													|| ((String) tdsDato
															.get(fila)[i - 1])
															.toLowerCase()
															.endsWith(".wav")) {
												datoImprimir = "<EMBED src=\""
														+ ((HttpServletRequest) pageContext
																.getRequest())
																.getContextPath()
														+ "/"
														+ tdsDato.get(fila)[i - 1]
														+ "\" class=\"reporte_tabla_url_archivo_sonido\">";
											} else if (((String) tdsDato
													.get(fila)[i - 1])
													.toLowerCase().endsWith(
															".avi")
													|| ((String) tdsDato
															.get(fila)[i - 1])
															.toLowerCase()
															.endsWith(".mpg")
													|| ((String) tdsDato
															.get(fila)[i - 1])
															.toLowerCase()
															.endsWith(".mpeg")
													|| ((String) tdsDato
															.get(fila)[i - 1])
															.toLowerCase()
															.endsWith(".qt")
													|| ((String) tdsDato
															.get(fila)[i - 1])
															.toLowerCase()
															.endsWith(".mov")) {
												datoImprimir = "<EMBED src=\""
														+ ((HttpServletRequest) pageContext
																.getRequest())
																.getContextPath()
														+ "/"
														+ tdsDato.get(fila)[i - 1]
														+ "\" class=\"reporte_tabla_url_archivo_video\">";
											} else {
												datoImprimir = "<A href=\""
														+ ((HttpServletRequest) pageContext
																.getRequest())
																.getContextPath()
														+ "/"
														+ tdsDato.get(fila)[i - 1]
														+ "\" class=\"reporte_tabla_url_archivo_link\">"
														+ tdsDato.get(fila)[i - 1]
														+ "</A>";
											}
										}else if (getTipoColumna().get(i).equals(
												"checkbox")){
											
											String campo = "";

											if (!rsmd.getCatalogName(i).isEmpty()) {
												campo += "." + rsmd.getCatalogName(i);
											}

											if (!rsmd.getSchemaName(i).isEmpty()) {
												campo += "." + rsmd.getSchemaName(i);
											}

											if (!rsmd.getTableName(i).isEmpty()) {
												campo += "." + rsmd.getTableName(i);
											}

											if (!rsmd.getColumnName(i).isEmpty()) {
												campo += "." + rsmd.getColumnName(i);
											} else {
												campo += "." + rsmd.getColumnLabel(i);
											}

											if (!campo.isEmpty()) {
												campo = campo.substring(1);
											}
											
											datoImprimir = "<INPUT type=\"checkbox\" name=\"" + campo + "\" value=\""
													+ tdsDato.get(fila)[i - 1]
													+ "\" class=\"reporte_tabla_checkbox\">";
										}
									}

									/* FIN TIPO DE DATOS */

									if (rowspan.get(fila)[i - 1] == null) {
										pageContext.getOut().println(
												"<TD title=\""
														+ rsmd.getColumnName(i)
														+ "\">" + datoImprimir
														+ "</TD>");
									} else {
										pageContext
												.getOut()
												.println(
														"<TD rowspan=\""
																+ rowspan
																		.get(fila)[i - 1]
																+ "\" title=\""
																+ rsmd.getColumnName(i)
																+ "\">"
																+ datoImprimir
																+ "</TD>");
									}
								}
							}
						} else {
							for (int i = 0; i < getEncabezado().size(); i++) {
								if (fila == 0
										|| (fila > 0 && ((tdsDato.get(fila)[i] == null && tdsDato
												.get(fila - 1)[i] != null)
												|| (tdsDato.get(fila)[i] != null && tdsDato
														.get(fila - 1)[i] == null) || (tdsDato
												.get(fila)[i] != null && !tdsDato
												.get(fila)[i].equals(tdsDato
												.get(fila - 1)[i]))))) {

									/* TIPO DE DATOS */
									Object datoImprimir = (tdsDato.get(fila)[i] == null) ? ""
											: tdsDato.get(fila)[i];

									if (tdsDato.get(fila)[i] != null
											&& getTipoColumna() != null
											&& getTipoColumna().containsKey(
													getColumnaEncabezado().get(
															i))) {

										if (getTipoColumna().get(
												getColumnaEncabezado().get(i))
												.equals("url_archivo")) {
											if (((String) tdsDato.get(fila)[i])
													.toLowerCase().endsWith(
															".jpg")
													|| ((String) tdsDato
															.get(fila)[i])
															.toLowerCase()
															.endsWith(".png")
													|| ((String) tdsDato
															.get(fila)[i])
															.toLowerCase()
															.endsWith(".gif")
													|| ((String) tdsDato
															.get(fila)[i])
															.toLowerCase()
															.endsWith(".jpeg")) {
												datoImprimir = "<IMG src=\""
														+ ((HttpServletRequest) pageContext
																.getRequest())
																.getContextPath()
														+ "/"
														+ tdsDato.get(fila)[i]
														+ "\" class=\"reporte_tabla_url_archivo_img\">";
											} else if (((String) tdsDato
													.get(fila)[i])
													.toLowerCase().endsWith(
															".mid")
													|| ((String) tdsDato
															.get(fila)[i])
															.toLowerCase()
															.endsWith(".midi")
													|| ((String) tdsDato
															.get(fila)[i])
															.toLowerCase()
															.endsWith(".aif")
													|| ((String) tdsDato
															.get(fila)[i])
															.toLowerCase()
															.endsWith(".aiff")
													|| ((String) tdsDato
															.get(fila)[i])
															.toLowerCase()
															.endsWith(".au")
													|| ((String) tdsDato
															.get(fila)[i])
															.toLowerCase()
															.endsWith(".wav")) {
												datoImprimir = "<EMBED src=\""
														+ ((HttpServletRequest) pageContext
																.getRequest())
																.getContextPath()
														+ "/"
														+ tdsDato.get(fila)[i]
														+ "\" class=\"reporte_tabla_url_archivo_sonido\">";
											} else if (((String) tdsDato
													.get(fila)[i])
													.toLowerCase().endsWith(
															".avi")
													|| ((String) tdsDato
															.get(fila)[i])
															.toLowerCase()
															.endsWith(".mpg")
													|| ((String) tdsDato
															.get(fila)[i])
															.toLowerCase()
															.endsWith(".mpeg")
													|| ((String) tdsDato
															.get(fila)[i])
															.toLowerCase()
															.endsWith(".qt")
													|| ((String) tdsDato
															.get(fila)[i])
															.toLowerCase()
															.endsWith(".mov")) {
												datoImprimir = "<EMBED src=\""
														+ ((HttpServletRequest) pageContext
																.getRequest())
																.getContextPath()
														+ "/"
														+ tdsDato.get(fila)[i]
														+ "\" class=\"reporte_tabla_url_archivo_video\">";
											} else {
												datoImprimir = "<A href=\""
														+ ((HttpServletRequest) pageContext
																.getRequest())
																.getContextPath()
														+ "/"
														+ tdsDato.get(fila)[i]
														+ "\" class=\"reporte_tabla_url_archivo_link\">"
														+ tdsDato.get(fila)[i]
														+ "</A>";
											}
										}else if (getTipoColumna().get(
												getColumnaEncabezado().get(
														i)).equals(
												"checkbox")){
											
											String campo = "";

											if (!rsmd.getCatalogName(
													getColumnaEncabezado().get(
															i)).isEmpty()) {
												campo += "." + rsmd.getCatalogName(
														getColumnaEncabezado().get(
																i));
											}

											if (!rsmd.getSchemaName(
													getColumnaEncabezado().get(
															i)).isEmpty()) {
												campo += "." + rsmd.getSchemaName(
														getColumnaEncabezado().get(
																i));
											}

											if (!rsmd.getTableName(
													getColumnaEncabezado().get(
															i)).isEmpty()) {
												campo += "." + rsmd.getTableName(
														getColumnaEncabezado().get(
																i));
											}

											if (!rsmd.getColumnName(
													getColumnaEncabezado().get(
															i)).isEmpty()) {
												campo += "." + rsmd.getColumnName(
														getColumnaEncabezado().get(
																i));
											} else {
												campo += "." + rsmd.getColumnLabel(
														getColumnaEncabezado().get(
																i));
											}

											if (!campo.isEmpty()) {
												campo = campo.substring(1);
											}
											
											datoImprimir = "<INPUT type=\"checkbox\" name=\"" + campo + "\" value=\""
													+ tdsDato.get(fila)[i]
													+ "\" class=\"reporte_tabla_checkbox\">";
										}
									}

									/* FIN TIPO DE DATOS */

									if (rowspan.get(fila)[i] == null) {

										pageContext.getOut().println(
												"<TD title=\""
														+ getEncabezado()
																.get(i) + "\">"
														+ datoImprimir
														+ "</TD>");
									} else {
										pageContext.getOut().println(
												"<TD rowspan=\""
														+ rowspan.get(fila)[i]
														+ "\" title=\""
														+ getEncabezado()
																.get(i) + "\">"
														+ datoImprimir
														+ "</TD>");
									}
								}
							}
						}

						if (fila == 0) {
							if (getEditar() != null) {
								pageContext.getOut().println(
										"<TD rowspan=\"" + maxRowsPan
												+ "\" title=\""
												+ getTextoEditar()
												+ "\"><A href=\"" + getEditar()
												+ "?" + qsTodosDatosActual
												+ "\">" + getTextoEditar()
												+ "</A></TD>");
							}

							if (getEliminar() != null) {

								String datosEliminar = qsTodosDatosActual;
								if (getPk() != null) {
									datosEliminar = "id=" + datoPkAnterior;
								}

								pageContext
										.getOut()
										.println(
												"<TD rowspan=\""
														+ maxRowsPan
														+ "\" title=\""
														+ getTextoEliminar()
														+ "\"><A href=\""
														+ getEliminar()
														+ "?"
														+ datosEliminar
														+ "\" onclick=\"eliminarFilaTablaReporte('"
														+ datosEliminar
														+ "', '"
														+ getEliminar()
														+ "', "
														+ getUsarAjax()
														+ ", '"
														+ getMensajeConfirmacionEliminar()
														+ "', '"
														+ getId()
														+ "', this); return false;\">"
														+ getTextoEliminar()
														+ "</A></TD>");
							}
						}

						pageContext.getOut().println("</TR>");
					}

					qsTodosDatosNext = "";

					maxRowsPan = 1;

					tdsDato.clear();
					rowspan.clear();
					if(getPk() != null){
						datoPkAnterior = bd.rs.getObject(getPk());
					}
					indiceIgual = new Integer[rsmd.getColumnCount()];

					Integer rowspanActual[] = new Integer[rsmd.getColumnCount()];
					Object datos[] = new Object[rsmd.getColumnCount()];

					if (getEncabezado() == null) {
						for (int i = 1; i <= rsmd.getColumnCount(); i++) {
							datos[i - 1] = bd.rs.getObject(i);
						}
					} else {
						for (int i = 0; i < getEncabezado().size(); i++) {
							if (getColumnaEncabezado() == null) {
								if (getTipoColumna() != null
										&& getTipoColumna().containsKey(i + 1)) {
									datos[i] = bd.rs.getObject(i + 1);
								}
							} else {
								datos[i] = bd.rs
										.getObject(getColumnaEncabezado()
												.get(i));
							}
						}
					}

					tdsDato.add(datos);
					rowspan.add(rowspanActual);

				} else {
					// pageContext.getOut().println("<TR>");
					Object datos[] = new Object[rsmd.getColumnCount()];
					Integer rowspanActual[] = new Integer[rsmd.getColumnCount()];
					Object datosAnteriores[] = null;
					if (tdsDato.size() > 0) {
						datosAnteriores = tdsDato.get(tdsDato.size() - 1);
					}

					if (getEncabezado() == null) {
						for (int i = 1; i <= rsmd.getColumnCount(); i++) {
							datos[i - 1] = bd.rs.getObject(i);
							if (datosAnteriores != null) {

								if ((datosAnteriores[i - 1] == null && datos[i - 1] == null)
										|| (datosAnteriores[i - 1] != null
												&& datos[i - 1] != null && datosAnteriores[i - 1]
												.equals(datos[i - 1]))) {
									if (indiceIgual[i - 1] == null) {

										if (rowspan.get(rowspan.size() - 1)[i - 1] == null) {
											rowspan.get(rowspan.size() - 1)[i - 1] = 2;
											if (maxRowsPan < 2) {
												maxRowsPan = 2;
											}
										} else {
											rowspan.get(rowspan.size() - 1)[i - 1]++;
											if (maxRowsPan < rowspan
													.get(rowspan.size() - 1)[i - 1]) {
												maxRowsPan = rowspan
														.get(rowspan.size() - 1)[i - 1];
											}
										}
										indiceIgual[i - 1] = rowspan.size() - 1;
									} else {

										if (rowspan.get(indiceIgual[i - 1])[i - 1] == null) {
											rowspan.get(indiceIgual[i - 1])[i - 1] = 2;
											if (maxRowsPan < 2) {
												maxRowsPan = 2;
											}
										} else {
											rowspan.get(indiceIgual[i - 1])[i - 1]++;
											if (maxRowsPan < rowspan
													.get(indiceIgual[i - 1])[i - 1]) {
												maxRowsPan = rowspan
														.get(indiceIgual[i - 1])[i - 1];
											}
										}
									}
								} else {
									indiceIgual[i - 1] = null;
								}
							}
						}
					} else {
						for (int i = 0; i < getEncabezado().size(); i++) {
							if (getColumnaEncabezado() == null) {
								datos[i] = bd.rs.getObject(i + 1);
							} else {
								datos[i] = bd.rs
										.getObject(getColumnaEncabezado()
												.get(i));
							}

							if (datosAnteriores != null) {

								if ((datosAnteriores[i] == null && datos[i] == null)
										|| (datosAnteriores[i] != null
												&& datos[i] != null && datosAnteriores[i]
													.equals(datos[i]))) {
									if (indiceIgual[i] == null) {

										if (rowspan.get(rowspan.size() - 1)[i] == null) {
											rowspan.get(rowspan.size() - 1)[i] = 2;
											if (maxRowsPan < 2) {
												maxRowsPan = 2;
											}
										} else {
											rowspan.get(rowspan.size() - 1)[i]++;
											if (maxRowsPan < rowspan
													.get(rowspan.size() - 1)[i]) {
												maxRowsPan = rowspan
														.get(rowspan.size() - 1)[i];
											}
										}
										indiceIgual[i] = rowspan.size() - 1;
									} else {

										if (rowspan.get(indiceIgual[i])[i] == null) {
											rowspan.get(indiceIgual[i])[i] = 2;
											if (maxRowsPan < 2) {
												maxRowsPan = 2;
											}
										} else {
											rowspan.get(indiceIgual[i])[i]++;
											if (maxRowsPan < rowspan
													.get(indiceIgual[i])[i]) {
												maxRowsPan = rowspan
														.get(indiceIgual[i])[i];
											}
										}
									}
								} else {
									indiceIgual[i] = null;
								}
							}

						}
					}
					rowspan.add(rowspanActual);
					tdsDato.add(datos);
					if(getPk() != null){
						datoPkAnterior = bd.rs.getObject(getPk());
					}
					// pageContext.getOut().println("</TR>");
				}

				for (int i = 0; i < rsmd.getColumnCount(); i++) {

					if (bd.rs.getObject(i + 1) != null) {
						String campo = "";

						if (!rsmd.getCatalogName(i + 1).isEmpty()) {
							campo += "." + rsmd.getCatalogName(i + 1);
						}

						if (!rsmd.getSchemaName(i + 1).isEmpty()) {
							campo += "." + rsmd.getSchemaName(i + 1);
						}

						if (!rsmd.getTableName(i + 1).isEmpty()) {
							campo += "." + rsmd.getTableName(i + 1);
						}

						if (!rsmd.getColumnName(i + 1).isEmpty()) {
							campo += "." + rsmd.getColumnName(i + 1);
						} else {
							campo += "." + rsmd.getColumnLabel(i + 1);
						}

						if (!campo.isEmpty()) {
							campo = campo.substring(1);
						}

						if(bd.rs.getString(i + 1).indexOf("<") < 0){
							String campoValor = campo + "="
									+ bd.rs.getObject(i + 1);
							// if(!qsTodosDatosNext.contains(campoValor)){
							qsTodosDatosNext += campoValor + "&";
							// }
						}
					}

				}

			}

			qsTodosDatosActual = qsTodosDatosNext;

			if (!qsTodosDatosActual.isEmpty()) {
				qsTodosDatosActual = qsTodosDatosActual.substring(0,
						qsTodosDatosActual.length() - 1);
			}

			for (int fila = 0; fila < tdsDato.size(); fila++) {
				if (fila == 0) {
					contadorPrimario++;
					contadorSecundario = 0;
				}
				contadorSecundario++;
				pageContext
						.getOut()
						.println(
								"<TR id=\"" + getId() + "_tr_"
										+ contadorPrimario
										+ "_"
										+ contadorSecundario
										+ "\" class=\""
										+ ((contadorPrimario % 2 == 0) ? "reporte_tabla_tr_par"
												: "reporte_tabla_tr_impar")
										+ "\">");

				if (getEncabezado() == null) {
					for (int i = 1; i <= rsmd.getColumnCount(); i++) {
						if (fila == 0
								|| (fila > 0 && !tdsDato.get(fila)[i - 1]
										.equals(tdsDato.get(fila - 1)[i - 1]))) {

							/* TIPO DE DATOS */

							Object datoImprimir = (tdsDato.get(fila)[i - 1] == null) ? ""
									: tdsDato.get(fila)[i - 1];

							if (tdsDato.get(fila)[i - 1] != null
									&& getTipoColumna() != null
									&& getTipoColumna().containsKey(i)) {

								if (getTipoColumna().get(i).equals(
										"url_archivo")) {
									if (((String) tdsDato.get(fila)[i - 1])
											.toLowerCase().endsWith(".jpg")
											|| ((String) tdsDato.get(fila)[i - 1])
													.toLowerCase().endsWith(
															".png")
											|| ((String) tdsDato.get(fila)[i - 1])
													.toLowerCase().endsWith(
															".gif")
											|| ((String) tdsDato.get(fila)[i - 1])
													.toLowerCase().endsWith(
															".jpeg")) {
										datoImprimir = "<IMG src=\""
												+ ((HttpServletRequest) pageContext
														.getRequest())
														.getContextPath()
												+ "/"
												+ tdsDato.get(fila)[i - 1]
												+ "\" class=\"reporte_tabla_url_archivo_img\">";
									} else if (((String) tdsDato.get(fila)[i])
											.toLowerCase().endsWith(".mid")
											|| ((String) tdsDato.get(fila)[i - 1])
													.toLowerCase().endsWith(
															".midi")
											|| ((String) tdsDato.get(fila)[i - 1])
													.toLowerCase().endsWith(
															".aif")
											|| ((String) tdsDato.get(fila)[i - 1])
													.toLowerCase().endsWith(
															".aiff")
											|| ((String) tdsDato.get(fila)[i - 1])
													.toLowerCase().endsWith(
															".au")
											|| ((String) tdsDato.get(fila)[i - 1])
													.toLowerCase().endsWith(
															".wav")) {
										datoImprimir = "<EMBED src=\""
												+ ((HttpServletRequest) pageContext
														.getRequest())
														.getContextPath()
												+ "/"
												+ tdsDato.get(fila)[i - 1]
												+ "\" class=\"reporte_tabla_url_archivo_sonido\">";
									} else if (((String) tdsDato.get(fila)[i - 1])
											.toLowerCase().endsWith(".avi")
											|| ((String) tdsDato.get(fila)[i - 1])
													.toLowerCase().endsWith(
															".mpg")
											|| ((String) tdsDato.get(fila)[i - 1])
													.toLowerCase().endsWith(
															".mpeg")
											|| ((String) tdsDato.get(fila)[i - 1])
													.toLowerCase().endsWith(
															".qt")
											|| ((String) tdsDato.get(fila)[i - 1])
													.toLowerCase().endsWith(
															".mov")) {
										datoImprimir = "<EMBED src=\""
												+ ((HttpServletRequest) pageContext
														.getRequest())
														.getContextPath()
												+ "/"
												+ tdsDato.get(fila)[i - 1]
												+ "\" class=\"reporte_tabla_url_archivo_video\">";
									} else {
										datoImprimir = "<A href=\""
												+ ((HttpServletRequest) pageContext
														.getRequest())
														.getContextPath()
												+ "/"
												+ tdsDato.get(fila)[i - 1]
												+ "\" class=\"reporte_tabla_url_archivo_link\">"
												+ tdsDato.get(fila)[i - 1]
												+ "</A>";
									}
								}else if (getTipoColumna().get(i).equals(
										"checkbox")){
									
									String campo = "";

									if (!rsmd.getCatalogName(i).isEmpty()) {
										campo += "." + rsmd.getCatalogName(i);
									}

									if (!rsmd.getSchemaName(i).isEmpty()) {
										campo += "." + rsmd.getSchemaName(i);
									}

									if (!rsmd.getTableName(i).isEmpty()) {
										campo += "." + rsmd.getTableName(i);
									}

									if (!rsmd.getColumnName(i).isEmpty()) {
										campo += "." + rsmd.getColumnName(i);
									} else {
										campo += "." + rsmd.getColumnLabel(i);
									}

									if (!campo.isEmpty()) {
										campo = campo.substring(1);
									}
									
									datoImprimir = "<INPUT type=\"checkbox\" name=\"" + campo + "\" value=\""
											+ tdsDato.get(fila)[i - 1]
											+ "\" class=\"reporte_tabla_checkbox\">";
								}
							}

							/* FIN TIPO DE DATOS */

							if (rowspan.get(fila)[i - 1] == null) {
								pageContext.getOut().println(
										"<TD title=\"" + rsmd.getColumnName(i)
												+ "\">" + datoImprimir
												+ "</TD>");
							} else {
								pageContext.getOut().println(
										"<TD rowspan=\""
												+ rowspan.get(fila)[i - 1]
												+ "\" title=\""
												+ rsmd.getColumnName(i) + "\">"
												+ datoImprimir + "</TD>");
							}
						}
					}
				} else {
					for (int i = 0; i < getEncabezado().size(); i++) {
						if (fila == 0
								|| (fila > 0 && ((tdsDato.get(fila)[i] == null && tdsDato
										.get(fila - 1)[i] != null)
										|| ((tdsDato.get(fila)[i] != null && tdsDato
												.get(fila - 1)[i] == null)) || (tdsDato
										.get(fila)[i] != null && !tdsDato
										.get(fila)[i].equals(tdsDato
										.get(fila - 1)[i]))))) {

							/* TIPO DE DATOS */

							Object datoImprimir = (tdsDato.get(fila)[i] == null) ? ""
									: tdsDato.get(fila)[i];

							if (tdsDato.get(fila)[i] != null
									&& getTipoColumna() != null
									&& getTipoColumna().containsKey(
											getColumnaEncabezado().get(i))) {

								if (getTipoColumna().get(
										getColumnaEncabezado().get(i)).equals(
										"url_archivo")) {
									if (((String) tdsDato.get(fila)[i])
											.toLowerCase().endsWith(".jpg")
											|| ((String) tdsDato.get(fila)[i])
													.toLowerCase().endsWith(
															".png")
											|| ((String) tdsDato.get(fila)[i])
													.toLowerCase().endsWith(
															".gif")
											|| ((String) tdsDato.get(fila)[i])
													.toLowerCase().endsWith(
															".jpeg")) {
										datoImprimir = "<IMG src=\""
												+ ((HttpServletRequest) pageContext
														.getRequest())
														.getContextPath()
												+ "/"
												+ tdsDato.get(fila)[i]
												+ "\" class=\"reporte_tabla_url_archivo_img\">";
									} else if (((String) tdsDato.get(fila)[i])
											.toLowerCase().endsWith(".mid")
											|| ((String) tdsDato.get(fila)[i])
													.toLowerCase().endsWith(
															".midi")
											|| ((String) tdsDato.get(fila)[i])
													.toLowerCase().endsWith(
															".aif")
											|| ((String) tdsDato.get(fila)[i])
													.toLowerCase().endsWith(
															".aiff")
											|| ((String) tdsDato.get(fila)[i])
													.toLowerCase().endsWith(
															".au")
											|| ((String) tdsDato.get(fila)[i])
													.toLowerCase().endsWith(
															".wav")) {
										datoImprimir = "<EMBED src=\""
												+ ((HttpServletRequest) pageContext
														.getRequest())
														.getContextPath()
												+ "/"
												+ tdsDato.get(fila)[i]
												+ "\" class=\"reporte_tabla_url_archivo_sonido\">";
									} else if (((String) tdsDato.get(fila)[i])
											.toLowerCase().endsWith(".avi")
											|| ((String) tdsDato.get(fila)[i])
													.toLowerCase().endsWith(
															".mpg")
											|| ((String) tdsDato.get(fila)[i])
													.toLowerCase().endsWith(
															".mpeg")
											|| ((String) tdsDato.get(fila)[i])
													.toLowerCase().endsWith(
															".qt")
											|| ((String) tdsDato.get(fila)[i])
													.toLowerCase().endsWith(
															".mov")) {
										datoImprimir = "<EMBED src=\""
												+ ((HttpServletRequest) pageContext
														.getRequest())
														.getContextPath()
												+ "/"
												+ tdsDato.get(fila)[i]
												+ "\" class=\"reporte_tabla_url_archivo_video\">";
									} else {
										datoImprimir = "<A href=\""
												+ ((HttpServletRequest) pageContext
														.getRequest())
														.getContextPath()
												+ "/"
												+ tdsDato.get(fila)[i]
												+ "\" class=\"reporte_tabla_url_archivo_link\">"
												+ tdsDato.get(fila)[i] + "</A>";
									}
								}else if (getTipoColumna().get(
										getColumnaEncabezado().get(
												i)).equals(
										"checkbox")){
									
									String campo = "";

									if (!rsmd.getCatalogName(
											getColumnaEncabezado().get(
													i)).isEmpty()) {
										campo += "." + rsmd.getCatalogName(
												getColumnaEncabezado().get(
														i));
									}

									if (!rsmd.getSchemaName(
											getColumnaEncabezado().get(
													i)).isEmpty()) {
										campo += "." + rsmd.getSchemaName(
												getColumnaEncabezado().get(
														i));
									}

									if (!rsmd.getTableName(
											getColumnaEncabezado().get(
													i)).isEmpty()) {
										campo += "." + rsmd.getTableName(
												getColumnaEncabezado().get(
														i));
									}

									if (!rsmd.getColumnName(
											getColumnaEncabezado().get(
													i)).isEmpty()) {
										campo += "." + rsmd.getColumnName(
												getColumnaEncabezado().get(
														i));
									} else {
										campo += "." + rsmd.getColumnLabel(
												getColumnaEncabezado().get(
														i));
									}

									if (!campo.isEmpty()) {
										campo = campo.substring(1);
									}
									
									datoImprimir = "<INPUT type=\"checkbox\" name=\"" + campo + "\" value=\""
											+ tdsDato.get(fila)[i]
											+ "\" class=\"reporte_tabla_checkbox\">";
								}
							}

							/* FIN TIPO DE DATOS */

							if (rowspan.get(fila)[i] == null) {
								pageContext.getOut().println(
										"<TD title=\"" + getEncabezado().get(i)
												+ "\">" + datoImprimir
												+ "</TD>");
							} else {
								pageContext.getOut().println(
										"<TD rowspan=\"" + rowspan.get(fila)[i]
												+ "\" title=\""
												+ getEncabezado().get(i)
												+ "\">" + datoImprimir
												+ "</TD>");
							}
						}
					}
				}

				if (fila == 0) {
					if (getEditar() != null) {
						pageContext.getOut().println(
								"<TD rowspan=\"" + maxRowsPan + "\" title=\""
										+ getTextoEditar() + "\"><A href=\""
										+ getEditar() + "?"
										+ qsTodosDatosActual + "\">"
										+ getTextoEditar() + "</A></TD>");
					}

					if (getEliminar() != null) {

						String datosEliminar = qsTodosDatosActual;
						if (getPk() != null) {
							datosEliminar = "id=" + datoPkAnterior;
						}

						pageContext
								.getOut()
								.println(
										"<TD rowspan=\""
												+ maxRowsPan
												+ "\" title=\""
												+ getTextoEliminar()
												+ "\"><A href=\""
												+ getEliminar()
												+ "?"
												+ datosEliminar
												+ "\" onclick=\"eliminarFilaTablaReporte('"
												+ datosEliminar
												+ "', '"
												+ getEliminar()
												+ "', "
												+ getUsarAjax()
												+ ", '"
												+ getMensajeConfirmacionEliminar()
												+ "', '"
												+ getId()
												+ "', this); return false;\">"
												+ getTextoEliminar()
												+ "</A></TD>");
						
					}
				}
				pageContext.getOut().println("</TR>");
			}

			tdsDato.clear();
			rowspan.clear();

			if (contadorPrimario == 0) {
				if (getTextoNoFilas() != null) {
					pageContext
							.getOut()
							.println(
									"<TR id=\"" + getId() + "_tr_"
											+ 1
											+ "_"
											+ 1
											+ "\" class=\"reporte_tabla_tr_impar\"><TH colspan=\""
											+ rsmd.getColumnCount() + "\">"
											+ getTextoNoFilas() + "</TH></TR>");
				}
			}

			pageContext.getOut().println("</TABLE>");

			if (getPaginacionJS() != null && getPaginacionJS()) {
				pageContext.getOut().println(
						"<DIV id=\"" + getId() + "_paginas\"></DIV>");
				pageContext.getOut().println(
						"<SCRIPT type=\"text/javascript\">");
				if (getFilasPorPagina() != null) {
					pageContext.getOut().println(
							"filasPorPagina = " + getFilasPorPagina() + ";");
				}
				pageContext
						.getOut()
						.println(
								"colocarPagina("
										+ ((pageContext
												.getRequest()
												.getParameter(
														"pagina_js_" + getId()) == null) ? "1"
												: pageContext
														.getRequest()
														.getParameter(
																"pagina_js_" + getId()))
										+ ", '" + getId() + "');");

				/* LLeno los Filtros con los datos obtenidos */

				Iterator<Entry<String, Set<String>>> it = datosFiltrosMap
						.entrySet().iterator();
				while (it.hasNext()) {
					Entry<String, Set<String>> e = it.next();
					Set<String> datosSet = e.getValue();

					String datosArray[] = new String[datosSet.size()];

					datosArray = datosSet.toArray(datosArray);

					Arrays.sort(datosArray);

					pageContext.getOut().println(
							"var obj = document.getElementsByName(\""
									+ e.getKey() + "_select" + "\")[0];");
					for (int i = 0; i < datosArray.length; i++) {
						pageContext.getOut().println(
								"obj.options[obj.options.length] = new Option(\""
										+ datosArray[i] + "\", \""
										+ datosArray[i] + "\");");
					}

				}

				pageContext.getOut().println("</SCRIPT>");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new JspTagException(
					"Fatal error: Etiqueta tabla no puede ser escrita en el bufer de salida");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				bd.cerrarConexion();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

}