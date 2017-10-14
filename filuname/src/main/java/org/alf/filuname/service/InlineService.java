package org.alf.filuname.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InlineService implements Service {

	private static final Logger logger = LoggerFactory.getLogger(InlineService.class);

	private List<Service> services;

	@Override
	public void init() {
		logger.info("{} initializing", getClass().getName());
		if (services != null) {
			for (Service service : services) {
				service.init();
			}
		}
	}

	@Override
	public void start() {
		logger.info("{} starting", getClass().getName());
		if (services != null) {
			for (Service service : services) {
				service.start();
			}
		}
	}

	@Override
	public void stop() {
		logger.info("{} stopping", getClass().getName());
		if (services != null) {
			for (Service service : services) {
				service.stop();
			}
		}
	}

	@Override
	public void dispose() {
		logger.info("{} disposing", getClass().getName());
		if (services != null) {
			for (Service service : services) {
				service.dispose();
			}
		}
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}
}
