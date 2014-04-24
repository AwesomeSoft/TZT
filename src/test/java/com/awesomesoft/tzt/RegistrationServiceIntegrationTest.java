package com.awesomesoft.tzt;

import com.awesomesoft.tzt.service.RegistrationService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

@RunWith(Arquillian.class)
public class RegistrationServiceIntegrationTest {

    @Deployment
    @OverProtocol("Servlet 3.0")
    public static JavaArchive createDeployment(){
        JavaArchive archive = ShrinkWrap.create(JavaArchive.class)
                .addPackages(true, "com.awesomesoft.tzt")
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        return archive;
    }

    @Inject
    RegistrationService registrationService;

    @Test
    public void testUserInsertion() throws Exception {
        registrationService.registerSoul("jvanpoppel", "jvp@there.com", "smartkey");

    }
}
