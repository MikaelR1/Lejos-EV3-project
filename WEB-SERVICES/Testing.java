package services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import data.Robot;

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
	@Path("/getallvalues")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Robot> getAllStats() {
		EntityManager em = emf.createEntityManager();
		List<Robot> list = em.createQuery("SELECT R FROM Robot R").getResultList();

		em.close();
		emf.close();
		return list;
	}
	
	@GET
	@Path("/speedfromrobot")
	@Produces(MediaType.APPLICATION_JSON)
	public String getSpeed() {
		return null;
	}
}
