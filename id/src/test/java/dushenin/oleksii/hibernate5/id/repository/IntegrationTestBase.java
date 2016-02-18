package dushenin.oleksii.hibernate5.id.repository;

import dushenin.oleksii.hibernate5.id.Main;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by alexeydushenin
 * on 2/16/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Main.class)
@Rollback(value = false)
public abstract class IntegrationTestBase {
}
