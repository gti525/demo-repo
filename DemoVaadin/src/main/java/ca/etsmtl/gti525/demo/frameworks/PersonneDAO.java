package ca.etsmtl.gti525.demo.frameworks;

import java.sql.SQLException;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.TableQuery;

public class PersonneDAO {

	SQLContainer container = null;
	private static PersonneDAO instance = new PersonneDAO();
	
	private PersonneDAO(){
		
	}
	
	public static PersonneDAO getInstance(){
		return instance;
	}
	
	public SQLContainer obtenirDonnees() {
		if (container == null){

			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
					"applicationContext.xml");

			SimpleDriverDataSource bean = (SimpleDriverDataSource) context
					.getBean("dataSource");
			JDBCConnectionPool connectionPool;

			try {
				connectionPool = new SpringConnectionPool(bean.getConnection());
				TableQuery q1 = new TableQuery("personnes", connectionPool);
				container = new SQLContainer(q1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				context.close();
			}
		}
		return container;

	}
}
