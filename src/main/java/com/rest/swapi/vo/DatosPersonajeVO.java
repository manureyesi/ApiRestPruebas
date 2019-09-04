package com.rest.swapi.vo;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DatosPersonajeVO implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1657449856973853209L;
	
	private String name;
	
	@JsonProperty("birth_year")
	private String birthYear;
	
	private String gender;
	
	@JsonProperty("planet_name")
	private String planetName;
	
	@JsonProperty("fastest_vehicle_driven")
	private String fastestVehicleDriven;
	
	private List<PeliculasVO> films;
	
	/**
	 * @param name
	 * @param birthYear
	 * @param gender
	 * @param planetName
	 * @param fastestVehicleDriven
	 * @param films
	 */
	public DatosPersonajeVO(String name, String birthYear, String gender, String planetName,
			String fastestVehicleDriven, List<PeliculasVO> films) {
		this.name = name;
		this.birthYear = birthYear;
		this.gender = gender;
		this.planetName = planetName;
		this.fastestVehicleDriven = fastestVehicleDriven;
		this.films = films;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the birthYear
	 */
	public String getBirthYear() {
		return birthYear;
	}

	/**
	 * @param birthYear the birthYear to set
	 */
	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the planetName
	 */
	public String getPlanetName() {
		return planetName;
	}

	/**
	 * @param planetName the planetName to set
	 */
	public void setPlanetName(String planetName) {
		this.planetName = planetName;
	}

	/**
	 * @return the fastestVehicleDriven
	 */
	public String getFastestVehicleDriven() {
		return fastestVehicleDriven;
	}

	/**
	 * @param fastestVehicleDriven the fastestVehicleDriven to set
	 */
	public void setFastestVehicleDriven(String fastestVehicleDriven) {
		this.fastestVehicleDriven = fastestVehicleDriven;
	}

	/**
	 * @return the films
	 */
	public List<PeliculasVO> getFilms() {
		return films;
	}

	/**
	 * @param films the films to set
	 */
	public void setFilms(List<PeliculasVO> films) {
		this.films = films;
	}

	/**
	 * toString
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(
				this,
				ToStringStyle.MULTI_LINE_STYLE);
    }
	
}
