import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONCircuitBuilder extends Logic {
	public HashMap<String, Circuit> circuitTable = new HashMap<String, Circuit>();

	public void addCircuits( Gate gate, JSONArray circuits ) throws JSONException {
		for( int i = 0; i < circuits.length(); i++ ) {
			JSONObject circuit = circuits.getJSONObject( i );
			gate.addCircuit( build( circuit ) );
		}
	}

	public Circuit build( JSONObject circuit ) throws JSONException {
		Circuit result = null;
		String type = circuit.getString( "type" );
		boolean inverted = circuit.optBoolean( "inverted" );

		if( type.equals( "input" ) ) {
			result = circuitTable.get( circuit.getString( "name" ) );
		}
		if( type.equals( "and" ) ) {
			Gate gate = new And();
			result = gate;
			addCircuits( gate, circuit.getJSONArray( "inputs" ) );
		}
		if( type.equals( "or" ) ) {
			Gate gate = new Or();
			result = gate;
			addCircuits( gate, circuit.getJSONArray( "inputs" ) );
		}
		if( result == null )
			throw new JSONException( "unknown circuit" );
		else
			if( inverted )
				return new Inverter( result );
			else
				return result;
	}
}
