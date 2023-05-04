package data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Speeddata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name="lcurrent_speed")
    private float lcurrent_speed;
    
    @Column(name="rcurrent_speed")
    private float rcurrent_speed;
    
    public Speeddata(float lcurrent_speed, float rcurrent_speed) {
        this.lcurrent_speed = lcurrent_speed;
        this.rcurrent_speed = rcurrent_speed;
    }
    
    // default constructor
    public Speeddata() {
    }
    
    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	public float getLcurrent_speed() {
		return lcurrent_speed;
	}

	public void setLcurrent_speed(float lcurrent_speed) {
		this.lcurrent_speed = lcurrent_speed;
	}

	public float getRcurrent_speed() {
		return rcurrent_speed;
	}

	public void setRcurrent_speed(float rcurrent_speed) {
		this.rcurrent_speed = rcurrent_speed;
	}
}
