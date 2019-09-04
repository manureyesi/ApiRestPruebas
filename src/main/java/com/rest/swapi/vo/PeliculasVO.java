package com.rest.swapi.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PeliculasVO implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1768135799143196521L;

	private String name;
	
	@JsonProperty("release_date")
	private String release_date;

	/**
	 * @param name
	 * @param release_date
	 */
	public PeliculasVO(String name, String release_date) {
		this.name = name;
		this.release_date = release_date;
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
	 * @return the release_date
	 */
	public String getRelease_date() {
		return release_date;
	}

	/**
	 * @param release_date the release_date to set
	 */
	public void setRelease_date(String release_date) {
		this.release_date = release_date;
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
