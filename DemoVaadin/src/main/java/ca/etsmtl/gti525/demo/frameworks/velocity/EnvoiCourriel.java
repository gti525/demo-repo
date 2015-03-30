package ca.etsmtl.gti525.demo.frameworks.velocity;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import ca.etsmtl.gti525.demo.frameworks.Personne;

public class EnvoiCourriel {
	public void construireCourriel(Personne personne) {
		
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		ve.init();
		VelocityContext context = new VelocityContext();
		context.put("personne",personne);
		Template t = ve.getTemplate( "templateCourriel.vm" , "UTF-8");
		StringWriter writer = new StringWriter();
		t.merge( context, writer );
		//J'affiche dans la console plutôt que d'écrire un courriel
		System.out.println(writer);
	}
}
