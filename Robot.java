package data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="lego_control_panel")
public class Robot {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column (name="speedmulti")
	private int speedmulti;
	
	@Column (name="acceleration")
	private int acceleration;
	
	@Column (name="black_value")
	private float black_value;
	
	@Column (name="white_value")
	private float white_value;

public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSpeedmulti() {
		return speedmulti;
	}

	public void setSpeedmulti(int speedmulti) {
		this.speedmulti = speedmulti;
	}

	public int getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(int acceleration) {
		this.acceleration = acceleration;
	}

	public float getBlack_value() {
		return black_value;
	}

	public void setBlack_value(float black_value) {
		this.black_value = black_value;
	}

	public float getWhite_value() {
		return white_value;
	}

	public void setWhite_value(float white_value) {
		this.white_value = white_value;
	}

public Robot(int speedmulti, int acceleration, float black_value, float white_value) {
    this.speedmulti = speedmulti;
    this.acceleration = acceleration;
    this.black_value = black_value;
    this.white_value = white_value;
}
public Robot() {
	//Default constructor ?
}
@Override
public String toString() {
    return "Robot{" +
            "id=" + id +
            ", speed=" + speedmulti +
            ", acceleration=" + acceleration +
            ", blackValue=" + black_value +
            ", whiteValue=" + white_value +
            '}';

}
}