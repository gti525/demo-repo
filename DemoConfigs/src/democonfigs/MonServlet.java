package democonfigs;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Démo fichiers propriétés, par Eric Boivin
 */
public class MonServlet extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {
	static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String nomFichier = getServletConfig().getInitParameter("global_config_file");
		//Va chercher le fichier dans son emplacement absolu et non pas relatif
		String nomEtCheminFichier = getServletConfig().getServletContext().getRealPath(nomFichier);

		Properties properties = new Properties();
	    try {
	        properties.load(new FileInputStream(nomEtCheminFichier));
	        response.getWriter().print(properties.getProperty("texte.entete")+" "+properties.getProperty("monApplication.maBD.hote"));
	        
	    } catch (IOException e) {
	    	//Fichier non trouvé
	    }
	}

}