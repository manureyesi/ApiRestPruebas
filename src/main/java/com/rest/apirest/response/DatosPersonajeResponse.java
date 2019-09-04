package com.rest.apirest.response;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DatosPersonajeResponse implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 902849795665588198L;
	
	private String name;
	
	@JsonProperty("birth_year")
	private String birthYear;
	
	private String gender;
	
	private String homeworld;
	
	private List<String> vehicles;
	
	private List<String> films;

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
	 * @return the homeworld
	 */
	public String getHomeworld() {
		return homeworld;
	}

	/**
	 * @param homeworld the homeworld to set
	 */
	public void setHomeworld(String homeworld) {
		this.homeworld = homeworld;
	}

	/**
	 * @return the vehicles
	 */
	public List<String> getVehicles() {
		return vehicles;
	}

	/**
	 * @param vehicles the vehicles to set
	 */
	public void setVehicles(List<String> vehicles) {
		this.vehicles = vehicles;
	}

	/**
	 * @return the films
	 */
	public List<String> getFilms() {
		return films;
	}

	/**
	 * @param films the films to set
	 */
	public void setFilms(List<String> films) {
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
