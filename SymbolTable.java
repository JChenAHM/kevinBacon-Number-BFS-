import java.util.HashMap;

public class SymbolTable {
	
	@SuppressWarnings("rawtypes")
	private HashMap st = new HashMap();

	@SuppressWarnings("unchecked")
	public void put(String key, Object value) { st.put(key, value);   }
    public Object get(String key)             { return st.get(key);   }

}
