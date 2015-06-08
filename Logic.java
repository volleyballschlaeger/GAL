import java.util.ArrayList;
import java.util.List;

public class Logic {
	public static interface Circuit {
		public boolean isTrue();
	}

	public static abstract class Gate implements Circuit
	{
		protected List<Circuit> circuits = new ArrayList<Circuit>();

		public void addCircuit( Circuit c ) {
			circuits.add( c );
		}

		public void removeCircuit( Circuit c )	{
			circuits.remove( c );
		}
	}

	public static class Switch implements Circuit
	{
		private boolean state = false;

		public void set( boolean s ) {
			state = s;
		}

		@Override
		public boolean isTrue() {
			return state;
		}
	}

	public static class Inverter implements Circuit
	{
		private Circuit circuit;

		public Inverter( Circuit c ) {
			circuit = c;
		}

		@Override
		public boolean isTrue() {
			return !circuit.isTrue();
		}
	}

	public static class And extends Gate
	{
		@Override
		public boolean isTrue() {
			for( Circuit c : circuits )
				if( !c.isTrue() )
					return false;
			return true;
		}
	}

	public static class Or extends Gate
	{
		@Override
		public boolean isTrue() {
			for( Circuit c : circuits )
				if( c.isTrue() )
					return true;
			return false;
		}
	}
}
