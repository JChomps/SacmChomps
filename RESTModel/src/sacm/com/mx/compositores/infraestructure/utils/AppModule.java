package sacm.com.mx.compositores.infraestructure.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;

import javax.validation.ValidationException;

import oracle.adf.share.logging.ADFLogger;

import sacm.com.mx.compositores.infraestructure.repositories.SacmEstado;

public class AppModule {
    public AppModule() {
        super();
    }

    private static ADFLogger _logger = ADFLogger.createADFLogger(SacmEstado.class);
    private static final String driver = "oracle.jdbc.OracleDriver";
    private static final String database =
        "jdbc:oracle:thin:@150.136.245.171:1521/pdbdes01.subnetsacm.vcnsacm.oraclevcn.com";
    private static final String usuario = "PORTALCLOUD";
    private static final String password = "P0rt4l_2019_Des";

    public static Connection getDbConexionJNDI() {
        Context ctx = null;
        Connection conn = null;
        Hashtable ht = new Hashtable();
        ht.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
        //ht.put(Context.PROVIDER_URL, "t3://localhost:7101");
        ht.put(Context.PROVIDER_URL, "t3://129.213.56.20:7002");
        try {
            ctx = new InitialContext(ht);
            DataSource ds;
            ds = (DataSource) ctx.lookup("jdbc/CloudConnDS");
            ctx.getEnvironment();
            conn = ds.getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
            ValidationException execption = new ValidationException(e);
            _logger.severe(e.getMessage());
            throw execption;
        } catch (SQLException e) {
            e.printStackTrace();
            ValidationException execption = new ValidationException(e);
            _logger.severe(e.getMessage());
            throw execption;
        }
        return conn;
    }

    public static Connection getDbConexionJDBC() {
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(database, usuario, password);
            return conn;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            ValidationException execption = new ValidationException(e);
            _logger.severe(e.getMessage());
            throw execption;
        }
        return conn;
    }

}
