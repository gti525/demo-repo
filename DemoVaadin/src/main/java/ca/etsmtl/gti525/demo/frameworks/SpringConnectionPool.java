package ca.etsmtl.gti525.demo.frameworks;

import java.sql.Connection;
import java.sql.SQLException;

import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;

public class SpringConnectionPool implements JDBCConnectionPool {

	private static final long serialVersionUID = 5521146946121386514L;
	private Connection conn;
   
    public SpringConnectionPool(Connection conn) {
        this.conn = conn;
    }
   
    @Override
    public void destroy() {       
    }

    @Override
    public void releaseConnection(Connection conn) {       
    }

    @Override
    public Connection reserveConnection() throws SQLException {
        return conn;
    }
}