package com.rest.apirest.response;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BusquedaNombreUsuariosResponse implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2975276821257127124L;
	
	private Integer count;
	private List<DatosPersonajeResponse> results;
	
	/**
	 * @return the count
	 */
	public Integer getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
	/**
	 * @return the result
	 */
	public List<DatosPersonajeResponse> getResults() {
		return results;
	}
	/**
	 * @param result the result to set
	 */
	public void setResults(List<DatosPersonajeResponse> results) {
		this.results = results;
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
