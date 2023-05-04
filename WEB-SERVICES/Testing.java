package services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import data.Robot;
import data.Speeddata;

@Path("/testing")
public class Testing {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("robot");

	@POST
	@Path("/newrobotvalues")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Robot newRobotValues(Robot robot) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(robot);
		em.getTransaction().commit();
		return robot;
	}
	
	@GET
	@Path("/getlatestvalues")
	@Produces(MediaType.APPLICATION_JSON)
	public Robot getLatestStats() {
		EntityManager em = emf.createEntityManager();
		int maxId = (int) em.createQuery("SELECT MAX(R.id) FROM Robot R").getSingleResult();
		int lastId = maxId;

		List<Robot> list = em.createQuery("SELECT R FROM Robot R WHERE R.id >= :lastId").setParameter("lastId", lastId)
				.getResultList();

		em.close();
		emf.close();
		return list.get(0);
	}
	
	@GET
	@Path("/getdbspeed")
	@Produces(MediaType.APPLICATION_JSON)
	public Speeddata getDbSpeed() {
		EntityManager em = emf.createEntityManager();
		int maxId = (int) em.createQuery("SELECT MAX(R.id) FROM Speeddata R").getSingleResult();
		int lastId = maxId;

		List<Speeddata> list = em.createQuery("SELECT R FROM Speeddata R WHERE R.id >= :lastId").setParameter("lastId", lastId)
				.getResultList();

		em.close();
		emf.close();
		return list.get(0);
	}
	
	@GET
	@Path("/newrobovalues")
	@Produces(MediaType.TEXT_PLAIN)
	public String newRoboValues() {
		EntityManager em = emf.createEntityManager();
		int maxId = (int) em.createQuery("SELECT MAX(R.id) FROM Robot R").getSingleResult();
		int lastId = maxId;

		List<Robot> list = em.createQuery("SELECT R FROM Robot R WHERE R.id >= :lastId").setParameter("lastId", lastId)
				.getResultList();

		em.close();
		emf.close();
		 Robot r=list.get(0);
	     String palaute=""+r.getAcceleration()+" "+r.getSpeedmulti()+ " "+ r.getBlack_value()+ " "+ r.getWhite_value();
	     return palaute;
	}
	
	@GET
	@Path("/speedfromrobot/{lcurrent_speed}/{rcurrent_speed}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response speedFromRobot(@PathParam("lcurrent_speed") float lcurrentspeed,
	                               @PathParam("rcurrent_speed") float rcurrentspeed) {
		
	    try {
	        // Create a new connection to the database
	        EntityManager em = emf.createEntityManager();
	        em.getTransaction().begin();

	        // Create a new instance of the SpeedData entity class
	        Speeddata speedData = new Speeddata();
	        speedData.setLcurrent_speed(lcurrentspeed);
	        speedData.setRcurrent_speed(rcurrentspeed);

	        // Persist the SpeedData object in the database
	        em.persist(speedData);

	        // Commit the transaction and close the entity manager
	        em.getTransaction().commit();
	        em.close();

	        // Return a successful response
	        return Response.ok().build();
	    } catch (Exception e) {
	        // Return an error response with the error message
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
	                .entity("Failed to save data: " + e.getMessage())
	                .build();
	    }
	}
}
