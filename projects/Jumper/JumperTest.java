import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import info.gridworld.grid.Location;

/**
 * 
 */

/**
 * @author steve
 *
 */
class JumperTest {

	private static Jumper jumperTest;
	
	@Before
	
	public void setUp() throws Exception{
		jumperTest = new Jumper();
	}
	
	/**
	 * {@link Jumper#act()} 的测试方法。
	 */
	@Test
	void testAct() {
		Location location = new Location(0, 0);
		jumperTest = new Jumper();
		jumperTest.moveTo(location);
		jumperTest.setDirection(Location.EAST);
		jumperTest.act();
		Location newLoc = new Location(0, 2);
		assertEquals(newLoc, jumperTest.getLocation() );
	}

	/**
	 * {@link Jumper#Jumper()} 的测试方法。
	 */
	@Test
	void testJumper() {
		jumperTest = null;
		jumperTest = new Jumper();
		assertEquals(Color.cyan, jumperTest.getColor());
	}

	/**
	 * {@link Jumper#Jumper(java.awt.Color)} 的测试方法。
	 */
	@Test
	void testJumperColor() {
		jumperTest = null;
		jumperTest = new Jumper(Color.PINK);
		assertEquals(Color.PINK, jumperTest.getColor());
	}

	/**
	 * {@link Jumper#jump()} 的测试方法。
	 */
	@Test
	void testJump() {
		Location location = new Location(0, 0);
		jumperTest.moveTo(location);
		jumperTest.setDirection(Location.EAST);
		jumperTest.jump();
		Location newLoc = new Location(0, 2);
		assertEquals(newLoc,jumperTest.getLocation());
	}

	/**
	 * {@link Jumper#canJump()} 的测试方法。
	 */
	@Test
	void testCanJump() {
		Location location = new Location(0, 0);
		jumperTest.moveTo(location);
		jumperTest.setDirection(Location.NORTH);
		assertEquals(false, jumperTest.canJump());
	}

}
