package org.emfjson.jackson.junit.tests.cdo;

import org.eclipse.emf.cdo.eresource.*;
import org.eclipse.emf.cdo.net4j.*;
import org.eclipse.emf.cdo.server.*;
import org.eclipse.emf.cdo.server.mem.MEMStoreUtil;
import org.eclipse.emf.cdo.server.net4j.CDONet4jServerUtil;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.transaction.*;
import org.eclipse.emf.cdo.util.*;
import org.eclipse.net4j.Net4jUtil;
import org.eclipse.net4j.connector.IConnector;
import org.eclipse.net4j.jvm.JVMUtil;
import org.eclipse.net4j.util.container.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class CDOTest {

	private static IConnector connector;
	private static IManagedContainer container;
	private static CDOSession session;

	public static IManagedContainer container() {
		IManagedContainer container = ContainerUtil.createContainer();
		Net4jUtil.prepareContainer(container);
		JVMUtil.prepareContainer(container);
		CDONet4jUtil.prepareContainer(container);
		CDONet4jServerUtil.prepareContainer(container);

		return container;
	}

	public static IConnector connector(IManagedContainer container) {
		if (!container.isActive()) container.activate();

		IStore store = MEMStoreUtil.createMEMStore();
		Map<String, String> properties = new HashMap<>();
		properties.put(IRepository.Props.OVERRIDE_UUID, "true");
		properties.put(IRepository.Props.SUPPORTING_AUDITS, "true");
		properties.put(IRepository.Props.SUPPORTING_BRANCHES, "true");

		IRepository repository = CDOServerUtil.createRepository("test", store, properties);

		CDOServerUtil.addRepository(container, repository);
		JVMUtil.getAcceptor(container, "default");
		return JVMUtil.getConnector(container, "default");
	}

	@BeforeClass
	public static void setUpClass() {
		container = container();
		connector = connector(container);

		CDONet4jSessionConfiguration configuration = CDONet4jUtil.createNet4jSessionConfiguration();
		configuration.setConnector(connector);
        configuration.setRepositoryName("test");

		session = configuration.openNet4jSession();
	}

	@AfterClass
	public static void setDownClass() {
		session.close();
		container.deactivate();
		connector.close();
		session = null;
		container = null;
		connector = null;
	}

	@Test
	public void shouldCreateAResource() throws CommitException {
		CDOTransaction tr = session.openTransaction();
		tr.createResource("Hello");
		tr.commit();
		tr.close();

		CDOTransaction tr2 = session.openTransaction();
		CDOResource r = tr2.getResource("Hello");

		assertNotNull(r);
	}

}