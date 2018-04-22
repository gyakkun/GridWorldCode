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

	private static Jumper _jumper;
	
	@Before
	
	public void setUp() throws Exception{
		_jumper = new Jumper();
		System.out.println("New Jumper instance created for _jumper.");
	}
	
	/**
	 * {@link Jumper#act()} 的测试方法。
	 */
	@Test
	void testAct() {
		Location location = new Location(0, 0);
		_jumper = new Jumper();
		_jumper.moveTo(location);
		_jumper.setDirection(Location.EAST);
		_jumper.act();
		Location newLoc = new Location(0, 2);
		assertEquals(newLoc, _jumper.getLocation() );
	}

	/**
	 * {@link Jumper#Jumper()} 的测试方法。
	 */
	@Test
	void testJumper() {
		_jumper = null;
		_jumper = new Jumper();
		assertEquals(Color.cyan, _jumper.getColor());
	}

	/**
	 * {@link Jumper#Jumper(java.awt.Color)} 的测试方法。
	 */
	@Test
	void testJumperColor() {
		_jumper = null;
		_jumper = new Jumper(Color.PINK);
		assertEquals(Color.PINK, _jumper.getColor());
	}

	/**
	 * {@link Jumper#jump()} 的测试方法。
	 */
	@Test
	void testJump() {
		Location location = new Location(0, 0);
		_jumper.moveTo(location);
		_jumper.setDirection(Location.EAST);
		_jumper.jump();
		Location newLoc = new Location(0, 2);
		assertEquals(newLoc,_jumper.getLocation());
	}

	/**
	 * {@link Jumper#canJump()} 的测试方法。
	 */
	@Test
	void testCanJump() {
		Location location = new Location(0, 0);
		_jumper.moveTo(location);
		_jumper.setDirection(Location.NORTH);
		//_jumper.act();
		//Location newLoc = new Location(0, 2);
		//assertEquals(_jumper.getLocation(), newLoc);
		assertEquals(false, _jumper.canJump());
	}

}
