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

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bruno Kaiser
 *
 */
public class RatedResource {
	private ResourceModel model;
	ArrayList<RateRefModel> rateRefs;
	
	/**
	 * Constructor.
	 */
	public RatedResource() {
		rateRefs = new ArrayList<RateRefModel>();
	}
	
	/**
	 * Retrieve the ResourceModel.
	 * @return the model
	 */
	public ResourceModel getModel() {
		return model;
	}
	
	/**
	 * Set the ResourceModel.
	 * @param model
	 */
	public void setModel(ResourceModel model) {
		this.model = model;
	}
	
	/**
	 * Retrieve a list of all references to rates for this resource.
	 * @return the list of rate references
	 */
	public List<RateRefModel> getRateRefs() {
		return rateRefs;
	}
	
	/**
	 * Set a list of all references to rates for this resource.
	 * @param rateRefs the list of rate references
	 */
	public void setRateRefs(ArrayList<RateRefModel> rateRefs) {
		this.rateRefs = rateRefs;
	}
	
	/**
	 * Add a reference to a rate to a resource.
	 * @param rate the reference to a rate
	 */
	public void addRateRef(RateRefModel rateRef) {
		this.rateRefs.add(rateRef);
	}
	
	/**
	 * Remove a reference to a rate from a resource.
	 * @param rateRef the rate reference that needs to be removed
	 * @return true if the removal was successful
	 */
	public boolean removeRateRef(RateRefModel rateRef) {
		return this.rateRefs.remove(rateRef);
	}
}
