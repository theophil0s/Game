package milkyway.earth.generation;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class DungeonGenerator {

	public static void main(String[] args) {
		DungeonGenerator generator = new DungeonGenerator();
		long seed = 1000340000L;
		int sizeX = 500;
		int sizeY = 500;
		int minX = 10;
		int minY = 10;
		int minRoomX = 6;
		int minRoomY = 6;
		drawByteImage(generator, seed, sizeX, sizeY, minX, minY, minRoomX, minRoomY);
		drawNodeImage(generator, seed, sizeX, sizeY, minX, minY, minRoomX, minRoomY);
	}

	private static void drawByteImage(DungeonGenerator generator, long seed, int sizeX, int sizeY, int minX, int minY,
			int minRoomX, int minRoomY) {
		boolean[][] dungeon = generator.generateDungeonByte(seed, sizeX, sizeY, minX, minY, minRoomX, minRoomY);

		try {
			int fieldWidth = 30;
			int fieldHeight = 30;
			int width = sizeX * fieldWidth;
			int height = sizeY * fieldHeight;
			BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

			Graphics2D ig2 = bi.createGraphics();

			for (int y = 0; y < sizeY; y++) {
				for (int x = 0; x < sizeX; x++) {
					// System.out.print(dungeon[x][y] ? "\u001b[47m \u001b[0m" :
					// "\u001b[40m \u001b[0m");
					// System.out.print(dungeon[x][y] ? "R" : "X");
					if (dungeon[x][y]) {
						ig2.setPaint(Color.white);
					} else {
						ig2.setPaint(Color.black);
					}
					ig2.fillRect(x * fieldWidth, y * fieldHeight, fieldWidth, fieldHeight);
				}
				// System.out.println();
			}
			File file = new File("dungeon.PNG");
			ImageIO.write(bi, "PNG", file);
			Desktop.getDesktop().open(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void drawNodeImage(DungeonGenerator generator, long seed, int sizeX, int sizeY, int minX, int minY,
			int minRoomX, int minRoomY) {
		Node rootNode = generator.generateDungeon(seed, sizeX, sizeY, minX, minY, minRoomX, minRoomY);

		try {
			int fieldWidth = 10;
			int fieldHeight = 10;
			int width = sizeX * fieldWidth;
			int height = sizeY * fieldHeight;
			BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

			Graphics2D ig2 = bi.createGraphics();

			drawNode(rootNode, ig2, fieldWidth, fieldHeight, 0, 0);

			File file = new File("dungeon2.PNG");
			ImageIO.write(bi, "PNG", file);
			Desktop.getDesktop().open(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void drawNode(Node node, Graphics2D g, int fieldWidth, int fieldHeight, int offsetX, int offsetY) {
		if (node.children == null) {
			g.setPaint(Color.black);
			g.drawRect((offsetX + node.startX) * fieldWidth, (offsetY + node.startY) * fieldWidth,
					node.sizeX * fieldWidth, node.sizeY * fieldHeight);
			g.setPaint(Color.white);
			g.fillRect(((offsetX + node.startX) * fieldWidth) + 2, ((offsetY + node.startY) * fieldWidth) + 2,
					(node.sizeX * fieldWidth) - 4, (node.sizeY * fieldHeight) - 4);
		} else {
			// Node is not a leaf node
			for (Node child : node.children) {
				drawNode(child, g, fieldWidth, fieldHeight, offsetX + node.startX, offsetY + node.startY);
			}
		}
	}

	// #######################################################################################################################################
	// #######################################################################################################################################
	// #######################################################################################################################################
	// #######################################################################################################################################

	public Node generateDungeon(long seed, int sizeX, int sizeY, int minX, int minY, int minRoomX, int minRoomY) {
		System.out.println(String.format("Generating dungeon for seed=%s; sizeX=%s; sizeY=%s; minX=%s; minY=%s", seed,
				sizeX, sizeY, minX, minY));
		final Random rndVertHor = new Random(seed);
		final Random rndSplitPos = new Random(seed);
		final Random rndRoom = new Random(seed);

		// Create the first / root node
		Node rootNode = this.createNode(0, 0, sizeX, sizeY);
		// Fill the root node with children and these children recursively with
		// their children
		System.out.println("Creating children...");
		this.createAndFillChildren(rootNode, rndVertHor, rndSplitPos, minX, minY, 0);
		System.out.println("Finished creating children.");

		// Generate rooms for ever leaf node
		System.out.println("Creating rooms...");
		this.createRooms(rootNode, rndRoom, minRoomX, minRoomY);
		System.out.println("Finished creating rooms.");

		return rootNode;
	}

	public boolean[][] generateDungeonByte(long seed, int sizeX, int sizeY, int minX, int minY, int minRoomX,
			int minRoomY) {
		Node node = this.generateDungeon(seed, sizeX, sizeY, minX, minY, minRoomX, minRoomY);

		// Build an boolean array to represent room and not room fields
		System.out.println("Creating array representation...");
		boolean[][] dungeon = new boolean[sizeX][sizeY];
		this.convertToArray(node, dungeon, node.startX, node.startY);
		System.out.println("Finished creating array representation.");
		return dungeon;
	}

	private Node createNode(int startX, int startY, int sizeX, int sizeY) {
		Node node = new Node();
		node.startX = startX;
		node.startY = startY;
		node.sizeX = sizeX;
		node.sizeY = sizeY;
		return node;
	}

	private void createAndFillChildren(Node node, Random rndVertHor, Random rndSplitPos, int minX, int minY,
			int level) {
		for (int i = 0; i < level; i++) {
			System.out.print("\t");
		}
		System.out.println("Node: " + node);
		node.children = this.createChildren(rndVertHor, rndSplitPos, node.sizeX, node.sizeY, minX, minY);
		if (node.children != null) {
			++level;
			for (Node child : node.children) {
				this.createAndFillChildren(child, rndVertHor, rndSplitPos, minX, minY, level);
			}
		}
	}

	private Node[] createChildren(Random rndVertHor, Random rndSplitPos, int sizeX, int sizeY, int minX, int minY) {
		if (sizeX <= minX * 2 && sizeY <= minY * 2) {
			return null;
		}

		boolean horizontal = false;
		if (sizeX <= minX * 2) {
			// if x is already to small to split, we split on the y axis
			horizontal = true;
		} else if (sizeY <= minY * 2) {
			// if y is already to small to split, we split on the x axis
			horizontal = false;
		} else {
			// if we can split an both the x-axis and y-axis, we let random
			// decide
			horizontal = rndVertHor.nextBoolean();
		}

		int splitPos = -1;
		do {
			splitPos = rndSplitPos.nextInt(horizontal ? sizeY : sizeX);
		} while (horizontal ? (splitPos < minY || sizeY - splitPos < minY)
				: (splitPos < minX || sizeX - splitPos < minX));

		Node[] children = new Node[2];
		if (horizontal) {
			children[0] = this.createNode(0, 0, sizeX, splitPos);
			children[1] = this.createNode(0, splitPos, sizeX, sizeY - splitPos);
		} else {
			children[0] = this.createNode(0, 0, splitPos, sizeY);
			children[1] = this.createNode(splitPos, 0, sizeX - splitPos, sizeY);
		}
		return children;
	}

	private void createRooms(Node node, Random rndRoom, int minRoomX, int minRoomY) {
		if (node.children == null) {
			// Node is a leaf node -> create a room in its bounds
			int roomStartX = -1;
			int roomStartY = -1;
			int roomEndX = -1;
			int roomEndY = -1;
			// first quarter: node.startX;node.startY ->
			// node.sizeX/2;node.sizeY/2
			do {
				do {
					roomStartX = rndRoom.nextInt(node.sizeX / 2);
				} while (roomStartX >= node.sizeX / 2);

				do {
					roomStartY = rndRoom.nextInt(node.sizeY / 2);
				} while (roomStartY >= node.sizeY / 2);

				// fourth quarter: node.sizeX/2;node.sizeY/2 ->
				// node.sizeX;node.sizeY
				do {
					roomEndX = rndRoom.nextInt(node.sizeX);
				} while (roomEndX <= node.sizeX / 2);

				do {
					roomEndY = rndRoom.nextInt(node.sizeY);
				} while (roomEndY <= node.sizeY / 2);
			} while (roomEndX - roomStartX < minRoomX || roomEndY - roomStartY < minRoomY);
			node.roomStartX = roomStartX;
			node.roomStartY = roomStartY;
			node.roomEndX = roomEndX;
			node.roomEndY = roomEndY;
			System.out.println("Node: " + node);
		} else {
			// Node is not a leaf node
			for (Node child : node.children) {
				this.createRooms(child, rndRoom, minRoomX, minRoomY);
			}
		}
	}

	private void convertToArray(Node node, boolean[][] dungeon, int offsetX, int offsetY) {
		if (node.children == null) {
			// This node should have a room
			for (int x = node.startX; x < node.startX + node.sizeX; x++) {
				for (int y = node.startY; y < node.startY + node.sizeY; y++) {
					if (x >= node.startX + node.roomStartX && x <= node.startX + node.roomEndX
							&& y >= node.startY + node.roomStartY && y <= node.startY + node.roomEndY) {
						dungeon[x + offsetX][y + offsetY] = true;
					} else {
						dungeon[x + offsetX][y + offsetY] = false;
					}
				}
			}
		} else {
			for (Node child : node.children) {
				this.convertToArray(child, dungeon, offsetX + node.startX, offsetY + node.startY);
			}
		}
	}

	private class Node {
		public Node[] children = new Node[2];
		public int startX;
		public int startY;
		public int sizeX;
		public int sizeY;

		public int roomStartX;
		public int roomStartY;
		public int roomEndX;
		public int roomEndY;

		@Override
		public String toString() {
			return String.format(
					"startX: %s; startY=%s; sizeX=%s; sizeY=%s; roomStartX=%s; roomStartY=%s; roomEndX=%s; roomEndY=%s",
					startX, startY, sizeX, sizeY, roomStartX, roomStartY, roomEndX, roomEndY);
		}
	}
}
