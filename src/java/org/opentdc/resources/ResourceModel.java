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

import java.util.Formatter;
import java.util.Locale;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class ResourceModel {
	public final static String DEF_XRI = "XRI_UNDEFINED";
	public final static String DEF_FIRSTNAME = "FIRSTNAME_UNDEFINED";
	public final static String DEF_LASTNAME = "LASTNAME_UNDEFINED";

	private String id;
	private String xri;
	private String firstName;
	private String lastName;

	public ResourceModel() {
		this.id = UUID.randomUUID().toString();
		this.xri = DEF_XRI;
		this.setFirstName(DEF_FIRSTNAME);
		this.setLastName(DEF_LASTNAME);
	}

	public ResourceModel(String firstName, String lastName) {
		this.id = UUID.randomUUID().toString();
		this.xri = DEF_XRI;
		this.setFirstName(firstName);
		this.setLastName(lastName);
	}

	/**
	 * 
	 * @return the ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * Set the ID to a unique random number
	 */
	public void setId() {
		this.id = UUID.randomUUID().toString();
	}

	/**
	 * Return the object ID in the backend.
	 * @return the xri 
	 */
	public String getXri() {
		return xri;
	}

	/**
	 * Set the object ID for the backend.
	 * @param xri
	 *            the xri to set
	 */
	public void setXri(String xri) {
		this.xri = xri;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder _sb = new StringBuilder();
		Formatter _formatter = new Formatter(_sb, Locale.US);
		_formatter
				.format("{\n\tid:\t%s\n\txri:\t%s\n\tfirstName:\t%s\n\tlastName:\t%s\n}",
						getId(), getXri(), getFirstName(), getLastName());
		_formatter.close();
		return _sb.toString();
	}
}
