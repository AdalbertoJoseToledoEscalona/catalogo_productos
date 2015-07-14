/**
 * 
 */
package vista.tags;

import java.io.IOException;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.naming.NamingException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import modelo.beans.FiltroReporte;
import modelo.utils.BaseDatos;

/**
 * @author adalberto
 *
 */
public class FiltrosTagHandler extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String titulo;
	private Integer columnas;
	private List<FiltroReporte> filtrosReporte;
	private Boolean ordenarCampos;
	private Boolean usarAjax;
	private String idTablaReporte;
	private String textoFiltrar;
	private String textoRestaurar;
	private String urlTablaReporte;
	
	/**
	 * 
	 */
	public FiltrosTagHandler() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}



	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}



	/**
	 * @return the columnas
	 */
	public Integer getColumnas() {
		return columnas;
	}



	/**
	 * @param columnas the columnas to set
	 */
	public void setColumnas(Integer columnas) {
		this.columnas = columnas;
	}



	/**
	 * @return the filtrosReporte
	 */
	public List<FiltroReporte> getFiltrosReporte() {
		return filtrosReporte;
	}



	/**
	 * @param filtrosReporte the filtrosReporte to set
	 */
	public void setFiltrosReporte(List<FiltroReporte> filtrosReporte) {
		this.filtrosReporte = filtrosReporte;
	}

	/**
	 * @return the ordenarCampos
	 */
	public Boolean getOrdenarCampos() {
		return ordenarCampos;
	}

	/**
	 * @param ordenarCampos the ordenarCampos to set
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
	 * @param usarAjax the usarAjax to set
	 */
	public void setUsarAjax(Boolean usarAjax) {
		this.usarAjax = usarAjax;
	}

	/**
	 * @return the idTablaReporte
	 */
	public String getIdTablaReporte() {
		return idTablaReporte;
	}

	/**
	 * @param idTablaReporte the idTablaReporte to set
	 */
	public void setIdTablaReporte(String idTablaReporte) {
		this.idTablaReporte = idTablaReporte;
	}

	/**
	 * @return the textoFiltrar
	 */
	public String getTextoFiltrar() {
		return textoFiltrar;
	}

	/**
	 * @param textoFiltrar the textoFiltrar to set
	 */
	public void setTextoFiltrar(String textoFiltrar) {
		this.textoFiltrar = textoFiltrar;
	}

	/**
	 * @return the textoRestaurar
	 */
	public String getTextoRestaurar() {
		return textoRestaurar;
	}
	

	public String getUrlTablaReporte() {
		return urlTablaReporte;
	}

	public void setUrlTablaReporte(String urlTablaReporte) {
		this.urlTablaReporte = urlTablaReporte;
	}

	/**
	 * @param textoRestaurar the textoRestaurar to set
	 */
	public void setTextoRestaurar(String textoRestaurar) {
		this.textoRestaurar = textoRestaurar;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			
			if(getIdTablaReporte() == null || getIdTablaReporte().trim() == ""){
				setIdTablaReporte("reporte_tabla");
			}
			
			String url = "null";
			if(getUrlTablaReporte() != null) {
				url = "'" + getUrlTablaReporte() + "'";
			}
			
			pageContext.getOut().println("<FORM name=\"form_filtro_" + getIdTablaReporte() + "\" method=\"get\" action=\"?\" onsubmit=\"return filtrarTablaReporte(this, " + getUsarAjax() + ", '" + getIdTablaReporte() + "', " + url + ");\">");
			pageContext.getOut().println("<TABLE id=\"filtro_" + getIdTablaReporte() +"\" class=\"filtro_reporte_tabla\">");
			
			if (getTitulo() != null) {
				pageContext.getOut().println(
						"<CAPTION>" + getTitulo() + "</CAPTION>");
			}
			
			int filas = 0;
			int columnas = 0;
			int indice  = 0;
			
			for (filas = 0; filas < Math.ceil((double)filtrosReporte.size()/(double)getColumnas()) ; filas++) {
				pageContext.getOut().println("<TR>");
				for (columnas = 0; columnas < getColumnas() && indice < getFiltrosReporte().size(); columnas++) {
					pageContext.getOut().println("<TD>");
					FiltroReporte filtro = getFiltrosReporte().get(indice);
					pageContext.getOut().println(filtro.getEtiqueta()+": ");
					
					if(filtro.getTipo().equals("select")){
						
						
						if(filtro.getBd() != null){
							
							BaseDatos bd = filtro.getBd();
							
							try {
							
							bd.generarSelect(bd.campos, bd.tablas, bd.uniones, bd.condicion,
									bd.disctinct, bd.camposOrden, bd.asc, bd.camposGroup,
									bd.condicionHaving);
							
							if (bd.usarJDBC) {
								bd.abrirConexionJDBC(null);
								
							} else {
								bd.abrirConexionDataSource(null);
							}

							bd.stmt = bd.con.createStatement();
							bd.rs = bd.stmt.executeQuery(bd.sql);

							ResultSetMetaData rsmd = bd.rs.getMetaData();
							
							
							String nombreSelect = "";

							if (!rsmd.getCatalogName(1).isEmpty()) {
								nombreSelect += "."
										+ rsmd.getCatalogName(1);
							}

							if (!rsmd.getSchemaName(1).isEmpty()) {
								nombreSelect += "." + rsmd.getSchemaName(1);
							}

							if (!rsmd.getTableName(1).isEmpty()) {
								nombreSelect += "." + rsmd.getTableName(1);
							}

							if (!rsmd.getColumnName(1).isEmpty()) {
								nombreSelect += "." + rsmd.getColumnName(1);
							} else {
								nombreSelect += "."
										+ rsmd.getColumnLabel(1);
							}
							
							if (!nombreSelect.isEmpty()) {
								nombreSelect = nombreSelect.substring(1);
							}

							
							
							pageContext.getOut().println("<SELECT name=\"" + nombreSelect + "_select" + "\">");
							pageContext.getOut().println("<OPTION value=\"*\">");
							pageContext.getOut().println(filtro.getNombreOpcionTodas());
							pageContext.getOut().println("</OPTION>");
							
							while (bd.rs.next()) {
								pageContext.getOut().println("<OPTION value=\"" + bd.rs.getObject(1) + "\">");
								pageContext.getOut().println((rsmd.getColumnCount() == 1)? bd.rs.getObject(1) : bd.rs.getObject(2));
								pageContext.getOut().println("</OPTION>");
							}
							
							
							
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (NamingException e) {
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
						}else{
							pageContext.getOut().println("<SELECT name=\"" + filtro.getNombre() + "_select" + "\">");
							pageContext.getOut().println("<OPTION value=\"*\">");
							pageContext.getOut().println(filtro.getNombreOpcionTodas());
							pageContext.getOut().println("</OPTION>");
							
							if(filtro.getDatos() != null){
								for (int i = 0; i < filtro.getDatos().size(); i++) {
									
									Map<String, Object> dato = filtro.getDatos().get(i);
									
									Iterator<Entry<String, Object>> it = dato.entrySet().iterator();
									while (it.hasNext()) {
										Entry<String, Object> e = it.next();
										pageContext.getOut().println("<OPTION value=\"" + e.getKey() + "\">");
										pageContext.getOut().println(e.getValue());
										pageContext.getOut().println("</OPTION>");
									}
								}
							}
						}
						
						pageContext.getOut().println("</SELECT>");
					}else if(filtro.getTipo().equals("text")){
						pageContext.getOut().println("<INPUT type=\"text\" name=\"" + filtro.getNombre() + "_text" + "\">");
					}else if(filtro.getTipo().equals("number")){
						pageContext.getOut().println("<INPUT type=\"number\" name=\"" + filtro.getNombre() + "_number" + "\">");
					}else if(filtro.getTipo().equals("date")){
						pageContext.getOut().println("<INPUT type=\"date\" name=\"" + filtro.getNombre() + "_date" + "\">");
					}else if(filtro.getTipo().equals("datetime")){
						pageContext.getOut().println("<INPUT type=\"datetime\" name=\"" + filtro.getNombre() + "_datetime" + "\">");
					}else if(filtro.getTipo().equals("email")){
						pageContext.getOut().println("<INPUT type=\"email\" name=\"" + filtro.getNombre() + "_email" + "\">");
					}
					pageContext.getOut().println("</TD>");
					indice++;
				}
				pageContext.getOut().println("</TR>");
			}
			
			Enumeration<String> parameterNames = pageContext.getRequest()
					.getParameterNames();
			
			String campoOrdenar = "";
			String ascCampoOrdenar = "";
			
			while (parameterNames.hasMoreElements()) {
				String parameter = parameterNames.nextElement();

				if (parameter.endsWith("_ordenar_" + getIdTablaReporte())) {
					
					campoOrdenar = parameter;

					ascCampoOrdenar = pageContext
							.getRequest().getParameter(parameter);
					
					break;
				}

			}
			
			pageContext.getOut().println("</TABLE>");
			pageContext.getOut().println("<INPUT type=\"hidden\" name=\"ordenar_" + getIdTablaReporte() + "\" value=\"" + getOrdenarCampos() + "\">");
			pageContext.getOut().println("<INPUT type=\"hidden\" id=\"campo_ordenar_" + getIdTablaReporte() + "_hidden\" name=\"" + campoOrdenar + "\" value=\"" + ascCampoOrdenar + "\">");
			pageContext.getOut().println("<INPUT type=\"hidden\" name=\"" + getIdTablaReporte() + "_refrescando\" value=\"true\">");
			
			pageContext.getOut().println("<INPUT type=\"submit\" value=\"" + getTextoFiltrar() + "\">");
			pageContext.getOut().println("<INPUT type=\"reset\" value=\"" + getTextoRestaurar() + "\">");
			
			pageContext.getOut().println("</FORM>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new JspTagException(
					"Fatal error: Etiqueta tabla no puede ser escrita en el bufer de salida");
		}
		return EVAL_BODY_INCLUDE;
	}
	
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

}
