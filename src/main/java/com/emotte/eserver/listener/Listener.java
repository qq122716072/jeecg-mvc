package com.emotte.eserver.listener;

public interface Listener {

	void startMonitor();
	
	void stopMonitor();

	long getAccessCount();
}
