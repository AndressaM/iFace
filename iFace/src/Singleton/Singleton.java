package Singleton;

import iFace.CentralSystem;

public final class Singleton {
	static private CentralSystem system = null;

	private Singleton() {}

	public static CentralSystem getInstance() {
		if (system == null)
			system = new CentralSystem();
		return Singleton.system;
	}

}
