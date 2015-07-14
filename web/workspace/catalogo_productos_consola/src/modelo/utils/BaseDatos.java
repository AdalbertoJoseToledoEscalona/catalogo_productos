package modelo.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BaseDatos
{
  public String host = "127.0.0.1";
  public int puerto = 3306;
  public String bd = "heat";

  public String usuario = "root";
  public String clave = "mysqladmin";

  private final String[] controladores = { 
    "com.mysql.jdbc.Driver", 
    "org.postgresql.Driver", 
    "COM.ibm.db2.jdbc.app.DB2Driver", 
    "sun.jdbc.odbc.JdbcOdbcDriver", 
    "sun.jdbc.odbc.JdbcOdbcDriver",
    "oracle.jdbc.driver.OracleDriver"};

  private final String[] conexiones = { 
    "mysql", 
    "postgresql", 
    "db2", 
    "odbc", 
    "odbc",
    "oracle:thin"};
  private InitialContext cotx;
  private DataSource ds;
  public Connection con;
  public Statement stmt;
  public PreparedStatement prstmt;
  public ResultSet rs;
  public String sql;
  public String manejador = "mysql";
  boolean limit = false;
  public int inicio = 0;
  public int contador = 1000;
  
  //////CAMPOS PARA EL SELECT ////////////////
  
  public String[] campos;
  public String[] tablas;
  public String[][] uniones;
  public String condicion;
  public boolean disctinct;
  public String[] camposOrden;
  public Boolean[] asc;
  public String[] camposGroup;
  public String condicionHaving;
  
  /////FIN CAMPOS PARA EL SELECT /////////////////
  
  public boolean usarJDBC;
  

  public void abrirConexionJDBC(String manejador)
    throws ClassNotFoundException, SQLException
  {
    int ordinal = 0;
    if (manejador == null) {
      manejadores.valueOf(this.manejador).ordinal();
    } else {
      manejadores.valueOf(manejador).ordinal();
      this.manejador = manejador;
    }

    Class.forName(this.controladores[ordinal]);

    this.con = DriverManager.getConnection("jdbc:" + this.conexiones[ordinal] + "://" + this.host + ":" + this.puerto + "/" + this.bd, this.usuario, this.clave);
    
    /*this.con = DriverManager.getConnection(
			"jdbc:oracle:thin:@localhost:1521:mkyong", "username",
			"password");*/
    
  }

  public void abrirConexionDataSource(String manejador) throws NamingException, SQLException {
    if (manejador == null) {
      this.cotx = new InitialContext();
      this.ds = ((DataSource)this.cotx.lookup("java:comp/env/jdbc/" + this.manejador));
      this.con = this.ds.getConnection();
    } else {
      this.cotx = new InitialContext();
      this.ds = ((DataSource)this.cotx.lookup("java:comp/env/jdbc/" + manejador));
      this.con = this.ds.getConnection();
      this.manejador = manejador;
    }
  }

  public void cerrarConexion() throws SQLException
  {
    if (this.rs != null) {
      this.rs.close();
      this.rs = null;
    }

    if (this.stmt != null) {
      this.stmt.close();
      this.stmt = null;
    }

    if (this.prstmt != null) {
      this.prstmt.close();
      this.prstmt = null;
    }

    this.con.close();
    this.con = null;
  }

  public String generarCreate(String nombreTabla, String[][] campos)
  {
    this.sql = ("CREATE " + nombreTabla + " (");
    for (String[] campo : campos) {
      this.sql = (this.sql + campo[0] + " " + campo[1] + ", ");
    }
    this.sql = this.sql.substring(0, this.sql.length() - 2);
    this.sql += ");";

    return this.sql;
  }

  public String generarInsert(String nombreTabla, String[] columnas, Object[][] valores) {
    this.sql = ("INSERT INTO " + nombreTabla + " ");

    if (columnas != null) {
      this.sql += "(";
      for (String columna : columnas) {
        this.sql = (this.sql + columna + ", ");
      }
      this.sql = this.sql.substring(0, this.sql.length() - 2);
      this.sql += ") ";
    }

    this.sql += "VALUES ";

    for (Object[] fila : valores) {
      this.sql += "(";
      for (Object valor : fila)
      {
        if (valor instanceof BaseDatos.Incognita) {
          this.sql += "?, ";
        }
        else if (valor instanceof BaseDatos.Nulo) {
            this.sql += "NULL, ";
        }
        else if (valor.getClass().getName().equals("Integer"))
          this.sql = (this.sql + valor + ", ");
        else if (valor.getClass().getName().equals("Date"))
          this.sql = (this.sql + "'" + valor + "', ");
        else {
          this.sql = (this.sql + "'" + valor + "', ");
        }
      }
      this.sql = this.sql.substring(0, this.sql.length() - 2);
      this.sql += "), ";
    }
    this.sql = this.sql.substring(0, this.sql.length() - 2);
    this.sql += ";";

    return (String)this.sql;
  }

  public String generarDelete(String nombreTabla, String condicion) {
	 this.sql = "";
    this.sql = ("DELETE FROM " + nombreTabla);

    if (condicion != null) {
      this.sql = (this.sql + " WHERE " + condicion);
    }

    this.sql += ";";

    return this.sql;
  }

  public String generarUpdate(String nombreTabla, Object[][] productosValores, String condicion)
  {
    this.sql = ("UPDATE " + nombreTabla + " SET ");

    for (Object[] productoValor : productosValores) {
      this.sql = (this.sql + productoValor[0] + " = ");
      if (productoValor[1] instanceof BaseDatos.Incognita) {
        this.sql += "?, ";
      }else if (productoValor[1] instanceof BaseDatos.Nulo) {
          this.sql += "NULL, ";
      }
      else if (productoValor[1].getClass().getName().equals("Integer"))
        this.sql = (this.sql + productoValor[1] + ", ");
      else if (productoValor[1].getClass().getName().equals("Date"))
        this.sql = (this.sql + "'" + productoValor[1] + "', ");
      else {
        this.sql = (this.sql + "'" + productoValor[1] + "', ");
      }
    }

    this.sql = this.sql.substring(0, this.sql.length() - 2);

    if (condicion != null) {
      this.sql = (this.sql + " WHERE " + condicion);
    }

    this.sql += ";";

    return this.sql;
  }

  public String generarSelect(String[] campos, String[] tablas, String[][] uniones, String condicion, boolean disctinct, String[] camposOrden, Boolean[] asc, String[] camposGroup, String condicionHaving)
  {
    this.sql = "SELECT ";

    if (this.limit) {
      if (this.inicio > 0) {
        if (this.contador >= 0) {
          if ((this.manejador.equals("mssqlserver")) || (this.manejador.equals("msaccess")))
            this.sql = (this.sql + "TOP " + this.contador + " START AT " + this.inicio + " ");
          else if (this.manejador.equals("informix")) {
            this.sql = (this.sql + "SKIP " + this.inicio + " FIRST " + this.contador + " ");
          }
        }
        else if ((this.manejador.equals("mssqlserver")) || (this.manejador.equals("msaccess")))
          this.sql = (this.sql + " START AT " + this.inicio + " ");
        else if (this.manejador.equals("informix")) {
          this.sql = (this.sql + "SKIP " + this.inicio + " ");
        }

      }
      else if ((this.manejador.equals("mssqlserver")) || (this.manejador.equals("msaccess")))
        this.sql = (this.sql + "TOP " + this.contador + " ");
      else if (this.manejador.equals("informix")) {
        this.sql = (this.sql + "FIRST " + this.contador + " ");
      }

    }

    if (disctinct) {
      this.sql += "DISTINCT ";
    }

    if (campos == null) {
      this.sql += "* ";
    } else {
      for (String campo : campos) {
        this.sql = (this.sql + campo + ", ");
      }
      this.sql = this.sql.substring(0, this.sql.length() - 2);
    }

    this.sql += " FROM ";

    for (String tabla : tablas) {
      this.sql = (this.sql + tabla + ", ");
    }
    this.sql = this.sql.substring(0, this.sql.length() - 2);

    if (uniones != null) {
      for (String[] union : uniones) {
        this.sql = (this.sql + " " + union[0] + " JOIN " + union[1] + " ON " + union[2] + union[3] + union[4]);
      }

    }

    if (condicion != null) {
      this.sql = (this.sql + " WHERE " + condicion);
    }

    if (camposGroup != null) {
      this.sql += " GROUP BY ";
      for (String campo : camposGroup) {
        this.sql = (this.sql + campo + ", ");
      }
      this.sql = this.sql.substring(0, this.sql.length() - 2);
    }

    if (condicionHaving != null) {
      this.sql = (this.sql + " HAVING " + condicionHaving);
    }

    if (camposOrden != null) {
      this.sql += " ORDER BY ";
      for (int i = 0; i < camposOrden.length; i++) {
        this.sql += camposOrden[i];

        if(asc != null){
	        if (asc[i] != false)
	          this.sql += " ASC, ";
	        else {
	          this.sql += " DESC, ";
	        }
        }else{
        	this.sql += ", ";
        }
      }
      this.sql = this.sql.substring(0, this.sql.length() - 2);
    }

    if (this.limit) {
      if (this.inicio > 0) {
        if (this.contador >= 0) {
          if (this.manejador.equals("mysql"))
            this.sql = (this.sql + " LIMIT " + this.inicio + "," + this.contador);
          else if (this.manejador.equals("postgresql"))
            this.sql = (this.sql + " LIMIT " + this.contador + " OFFSET " + this.inicio);
          else if (this.manejador.equals("db2")) {
            this.sql = (this.sql + " FETCH FIRST " + this.contador + " ROWS ONLY");
          }
        }
        else if (this.manejador.equals("mysql"))
          this.sql = (this.sql + " LIMIT " + this.inicio);
        else if (this.manejador.equals("postgresql"))
          this.sql = (this.sql + " OFFSET " + this.inicio);
        else if (this.manejador.equals("db2")) {
          this.sql = (this.sql + " FETCH FIRST " + this.inicio + " ROWS ONLY");
        }

      }
      else if ((this.manejador.equals("mysql")) || (this.manejador.equals("postgresql")))
        this.sql = (this.sql + " LIMIT " + this.contador);
      else if (this.manejador.equals("db2")) {
        this.sql = (this.sql + " FETCH FIRST " + this.contador + " ROWS ONLY");
      }

    }

    this.sql += ";";

    return (String)this.sql;
  }

  public class Incognita
  {
    public final String INCOGNITA = "?";

    public Incognita()
    {
    }
  }
  
  public class Nulo
  {
    public final String NULO = "NULL";

    public Nulo()
    {
    }
  }

  private static enum manejadores
  {
    mysql, postgresql, db2, msaccess, mssqlserver, oracle;
  }
}
