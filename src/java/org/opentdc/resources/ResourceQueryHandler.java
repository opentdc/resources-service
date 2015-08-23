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
import java.util.StringTokenizer;

import org.opentdc.query.AbstractQueryHandler;
import org.opentdc.query.QueryQuantor;
import org.opentdc.query.SortPredicate;
import org.opentdc.service.exception.NotImplementedException;
import org.opentdc.service.exception.ValidationException;

/**
 * Parses the query string and filters resource entries accordingly
 * @author Bruno Kaiser
 *
 */
public class ResourceQueryHandler extends AbstractQueryHandler {
	protected List<ResourceQueryPredicate> queryPredicates = null;

	/**
	 * Constructor.
	 */
	public ResourceQueryHandler(
			String query) 
	{
		queryPredicates = new ArrayList<ResourceQueryPredicate>();
		sortPredicates = new ArrayList<SortPredicate>();
		parsePredicates(query);
	}
	
	/**
	 * Parse the query string into Query- and Sort-Predicates.
	 * @param query the query string, a comma-separated list of predicates.
	 */
	protected void parsePredicates(
			String query) 
			throws ValidationException 
	{
		StringTokenizer _st = new StringTokenizer(query, ";");
		while (_st.hasMoreTokens()) {
			String _token = _st.nextToken();
			if (_token.startsWith("orderBy")) {
				sortPredicates.add(SortPredicate.parsePredicate(_token));
			}
			else {
				queryPredicates.add(ResourceQueryPredicate.parsePredicate(_token));
			}
		}
		logger.info("parsePredicates(" + query + ") -> OK");
	}

	/**
	 * @param model
	 * @return
	 */
	public boolean evaluate(
			ResourceModel model)
			throws NotImplementedException, ValidationException 
	{
		boolean _retVal = true;
		for (ResourceQueryPredicate _queryPredicate : queryPredicates) {
			if (_queryPredicate.getQuantor() != QueryQuantor.NONE) {
				throw new NotImplementedException("support for QueryQuantor is not yet implemented");
			}
			if (_queryPredicate.getValues().length == 0) {
				throw new ValidationException("expected value is missing");
			}
			if (_queryPredicate.getValues().length > 1) {
				throw new NotImplementedException("Multi-valued queries are not yet supported");		
			}
			switch(_queryPredicate.getFeatureType()) {
				case NAME:			_retVal = evaluateStringOperation(model.getName(), _queryPredicate.getOperator(), _queryPredicate.getValues()); break;
				case FIRSTNAME:		_retVal = evaluateStringOperation(model.getFirstName(), _queryPredicate.getOperator(), _queryPredicate.getValues()); break;
				case LASTNAME:		_retVal = evaluateStringOperation(model.getLastName(), _queryPredicate.getOperator(), _queryPredicate.getValues()); break;
				case CONTACTID:		_retVal = evaluateStringOperation(model.getContactId(), _queryPredicate.getOperator(), _queryPredicate.getValues()); break;

			// case TAGID:			_retVal = evaluateTagId(wr, _queryPredicate.getOperator(), _queryPredicate.getValues()); break;
				case CREATEDBY:		_retVal = evaluateStringOperation(model.getCreatedBy(), _queryPredicate.getOperator(), _queryPredicate.getValues()); break;
				case CREATEDAT:		_retVal = evaluateDateOperation(model.getCreatedAt(), _queryPredicate.getOperator(), _queryPredicate.getValues()); break;
				case MODIFIEDBY:	_retVal = evaluateStringOperation(model.getModifiedBy(), _queryPredicate.getOperator(), _queryPredicate.getValues()); break;
				case MODIFIEDAT:	_retVal = evaluateDateOperation(model.getModifiedAt(), _queryPredicate.getOperator(), _queryPredicate.getValues()); break;
				default: 			throw new ValidationException("FeatureType <" + _queryPredicate.getFeatureType() + "> is invalid.");
			}
			if (_retVal == false) break;
		}
		// logger.info("evaluate() -> " + _retVal);
		return _retVal;
	}
	
	/**
	 * 
	 * @param wr
	 * @param operator
	 * @param expectedTagIds
	 * @return
	 * @throws NotImplementedException
	 */
	/*
	private boolean evaluateTagId(
			TaggedWorkRecord wr,
			QueryOperator operator,
			String[] expectedTagIds) 
			throws NotImplementedException {
		logger.info("evaluateTagId(" + wr.getModel().getId() + ", " + operator + ", " + expectedTagIds[0]);
		switch(operator) {
		case EQUALTO:					return wr.containsTag(expectedTagIds[0]);
		case NOTEQUALTO:				return !wr.containsTag(expectedTagIds[0]);
		case GREATERTHAN:				
		case GREATERTHANOREQUALTO:	
		case LESSTHAN:					
		case LESSTHANOREQUALTO:		
		case ISLIKE:					
		case NONE:	
		default:						throw new NotImplementedException("operator " + operator + " is not implemented for tagId.");
		}

	}
	*/
}
