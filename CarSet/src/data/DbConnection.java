/*
* conexion.java
* Maneja la conexión con mysql y todas sus operaciones (select, insert,update y
* delete).
*
* Necesita el conector J.
* Bajar el conector J de mysql.
* NetBeans: boton derecho sobre Biblioteca->Agregar JAR
*/
package data;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lito
 */
public class DbConnection
{
	private Connection conn;
	private Statement stmt;
/*
* Constructor
* Necesita como parametros los datos de conexión a Base de Datos.
* El método constructor inicializa las dos variables de clase, conexion y
* statement.
*/
	public DbConnection() throws UnknownHostException
	{
                String db = "carset";
                String user = "root";
                String pass = "rcortes";
                String url="";
                String IPReal="";

        /*try {
            @SuppressWarnings("static-access")
            InetAddress direccion = InetAddress.getLocalHost().getByName("luis1");
            //InetAddress direccion = InetAddress.getLocalHost().getByName("W5602969");
            IPReal=direccion.getHostAddress();
        } catch (UnknownHostException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }
       
        if(System.getenv("COMPUTERNAME").equals("luis1"))
        {

		//Ruta de conexion. Conecta al server especificado y la Base de datos
                System.out.println(InetAddress.getLocalHost().toString());
		url = "jdbc:mysql://localhost/"+db;
                //String url = "jdbc:mysql://10.25.11.22/"+db;
                //url = "jdbc:mysql://"+IPReal+":3306/"+db;
        }
        else
        { */
                //url = "jdbc:mysql://"+IPReal+":3306/"+db;
                url = "jdbc:mysql://localhost/"+db;
        //}
	try
	{
            //Instanciación del conextor jdbc
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //Realiza la conexión con la base de datos, es variable de clase privada.
            conn = DriverManager.getConnection(url, user, pass);
            //Crea un ejecutador de sentencia, es variable de clase privada.
            stmt = conn.createStatement();
        }
            catch(Exception e)
	{
            //Error generico.
            e.printStackTrace();
	}
}

	/*
	* getConexion()
	* Devuelve la variable privada del tipo Connection.
	*/
	public Connection getConexion()
	{
		return this.conn;
	}

	/*
	* getStatement()
	* Devuelve la variable privada del tipo Statement.
	*/
	private Statement getStatement()
	{
		return this.stmt;
	}

	/*
	* select(String sentencia)
	* Dada una sentencia SQL del tipo select, devuelve un ResulSet con los
	* resultados de la consulta.
	*/
	public ResultSet select(String sentencia)
	{
		ResultSet rs = null;
		try
		{
		rs = this.getStatement().executeQuery(sentencia);
		}
		catch (SQLException ex)
		{
			Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
		}
		return rs;
	}

	/*
	* manipuladorDatos(String sentencia)
	* Soporta sentencias del tipo insert, update y delete.
	* Dada la sentencia, la ejecuta.
	* Devuelve false si no ha funcionado, true si todo ha ido correcto.
	*/
	public boolean manipuladorDatos(String sentencia)
	{
		boolean rs = false;
		try
		{
                    rs = this.getStatement().execute(sentencia);
		}
		catch (SQLException ex)
		{
			Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
		}
		return rs;
	}

	/*
	* numeroFilas(String sentencia)
	* Devuelve el numero de filas devueltas por una sentencia sql.
	*/
	public int numeroFilas(String sentencia)
	{
		ResultSet rs = null;
		int numero = 0;
		try
		{
			rs = this.getStatement().executeQuery(sentencia);
			while(rs.next())
			{
				numero++;
			}
			rs.close();
			}
			catch (SQLException ex)
			{
				Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
			}
			return numero;
	}

	/*
	* cerrarConexion()
	* Termina la conexión establecida en la instanciación de la clase.
	*/
	public void cerrarConexion()
	{
		try
		{
			this.getStatement().close();
			this.getConexion().close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}