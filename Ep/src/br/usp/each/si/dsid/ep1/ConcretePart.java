package br.usp.each.si.dsid.ep1;

import java.io.Serializable;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class ConcretePart implements Serializable{
	
	private static final long serialVersionUID = -7056488850145230287L;
	private String _code;
	private String _name;
	private String _description;
	private ConcurrentHashMap<ConcretePart, Integer> _subParts;

	public ConcretePart() {
		this._subParts = new ConcurrentHashMap<ConcretePart, Integer>();
	}

	public String getCode() {
		return _code;
	}

	public void setCode(String serverName, int numberOfParts) {
		this._code = serverName + (numberOfParts + 1);
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		this._name = name;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		this._description = description;
	}

	/**
	 * the part is primitive if there is no associated subpart
	 * 
	 * @return true if subpart list is empty
	 */
	public boolean isPrimitive() {
		return this._subParts.isEmpty();
	}

	public ConcurrentHashMap<ConcretePart, Integer> getSubParts() {
		return _subParts;
	}

	public void setSubParts(ConcurrentHashMap<ConcretePart, Integer> _subParts) {
		this._subParts = _subParts;
	}

	public void addSubPart(ConcretePart part, Integer quantity) {
		this._subParts.put(part, quantity);
	}

	/**
	 * hashcode according to the Part code
	 */
	@Override
	public int hashCode() {
		int hash = 7;
		for (int i = 0; i < _code.length(); i++) {
			hash = hash * 31 + _code.charAt(i);
		}
		return hash;
	}

	@Override
	public String toString() {
		String result = "CODE: " + _code;
		result += "\nNAME: " + _name;
		result += "\nDESCRIPTION: " + _description;
		result += "\nSUBPARTS CODE | QUANTITY: \n";
		for (Entry<ConcretePart, Integer> entry : _subParts.entrySet()) {
			result += "\t" + entry.getKey().getCode() + " | " + entry.getValue() + "\n";
		}
		return result;
	}

}
