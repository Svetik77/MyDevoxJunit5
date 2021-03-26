package checkByInterface.cars;

public class Car {

	private String marke;
	private String description;
	private int quantity;

	
	public Car() {
		//  constructor 
	}
	
	public Car(String marke, String description, int quantity) {
		this.marke = marke;
		this.description = description;
		this.quantity = quantity;
	}



	public String getMarke() {
		return marke;
	}

	public void setMarke(String marke) {
		this.marke = marke;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((marke == null) ? 0 : marke.hashCode());
		result = prime * result + quantity;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (marke == null) {
			if (other.marke != null)
				return false;
		} else if (!marke.equals(other.marke))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}

}
