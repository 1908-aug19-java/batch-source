package com.revature.Exercise1;

public class BeerNonAlcoholic  {
	public Boolean AgeRestriction=false; 


	public BeerNonAlcoholic() {
		// TODO Auto-generated constructor stub
	}

	// implments inhereted methode
	public void AgeRestriction() {
		System.out.println("BeerNonAlcoholic.AgeRestriction()");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((AgeRestriction == null) ? 0 : AgeRestriction.hashCode());
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
		BeerNonAlcoholic other = (BeerNonAlcoholic) obj;
		if (AgeRestriction == null) {
			if (other.AgeRestriction != null)
				return false;
		} else if (!AgeRestriction.equals(other.AgeRestriction))
			return false;
		return true;
	}

}
