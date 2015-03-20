package ca.etsmtl.gti525.demo.servicesweb;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.map.ObjectMapper;

@Path("/")
public class DemoServiceRest {

	@POST
	@Path("/additionne")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response additionne(InputStream input) {

		HashMap<String, String> jsonMap;
		JsonFactory factory = new JsonFactory();
		ObjectMapper mapper = new ObjectMapper(factory);
		try {
			jsonMap = mapper.readValue(input, HashMap.class);
			
			int resultat = Integer.valueOf(jsonMap.get("x")) + Integer.valueOf(jsonMap.get("y"));
			System.out.println("In: X=" + jsonMap.get("x") + " , Y="+ jsonMap.get("y")+ " ==> "+String.valueOf(resultat));
			
			return Response.status(Response.Status.OK).entity(String.valueOf(resultat)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST).entity("JSON incorrect").build();
		}
	}
}
