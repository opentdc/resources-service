/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Arbalo AG
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.opentdc.resources;

import java.util.Comparator;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A Reference to a Rate.
 * We only keep a reference (= ID) as the foreign key to a RatesModel in RatesService
 * plus the commonly used derived attribute rateTitle.
 * @author Bruno Kaiser
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class RateRefModel {
	private String id;				// sortable
	private String rateId;			// mandatory
	private String rateTitle;		// read-only, derived
	private Date createdAt;
	private String createdBy;
	// a RateRef can not be modified; therefore, modifiedAt/By are not needed

	/******************************* Constructors *****************************/
	public RateRefModel() {
	}
	
	public RateRefModel(String rateId) {
		this.setRateId(rateId);
	}

	/********************************** setters / getters *****************************/
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Retrieve the id of the referenced RateModel
	 * @return the id of the referenced RateModel
	 */
	public String getRateId() {
		return rateId;
	}

	/**
	 * Set the id of the referenced RateModel
	 * @param rateId the id of the referenced RateModel
	 */
	public void setRateId(String rateId) {
		this.rateId = rateId;
	}
	
	/**
	 * Retrieve the title of the referenced RateModel
	 * @return the title of the referenced RateModel
	 */
	public String getRateTitle() {
		return rateTitle;
	}

	/**
	 * Set the title of the referenced RateModel.
	 * rateTitle is a derived attribute. Do not set it on the client. It will be overwritten.
	 * @param rateTitle the title of the referenced RateModel to set
	 */
	public void setRateTitle(String rateTitle) {
		this.rateTitle = rateTitle;
	}
	
	/**
	 * @return the creation date of this object
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the creation date of this object
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * Retrieve the creator of this RateRef
	 * @return the login id of the creator of this RateRef
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * Set the creator of this RateRef
	 * @param createdBy the login id of the creator of this RateRef
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	/******************************* Comparator *****************************/
	public static Comparator<RateRefModel> RatesRefComparator = new Comparator<RateRefModel>() {

		public int compare(RateRefModel obj1, RateRefModel obj2) {
			if (obj1.getId() == null) {
				return -1;
			}
			if (obj2.getId() == null) {
				return 1;
			}

			String _attr1 = obj1.getId();
			String _attr2 = obj2.getId();

			// ascending order
			return _attr1.compareTo(_attr2);

			// descending order
			// return _attr2.compareTo(_attr1);
		}
	};
}
