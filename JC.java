import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class JC {

	public static void main(String[] args) throws IOException {
		Scanner header = new Scanner(new File("header.txt"));
		for (;;) {
			if (header.hasNextLine()) {
				System.out.println(header.nextLine());
			} else {
				break;
			}
		}
		Scanner in = new Scanner(new File("input.txt"));
		Scanner footer = new Scanner(new File("footer.txt"));

		int a = in.nextInt();
		int b = in.nextInt();

		int[][] numbers = new int[a][b];
		int[][] vertical;
		int[][] horizontal;

		for (int i = 0; i < a; i++) {
			for (int j = 0; j < b; j++) {
				numbers[i][j] = in.nextInt();
				// System.out.print(numbers[i][j]);
			}
			// System.out.println();
		}
		int c;
		vertical = new int[a][b];
		horizontal = new int[b][a];
		// System.out.println(a + " " + b);

		// filling the vertical stuff in
		for (int i = 0; i < b; i++) {
			for (int j = 0; j < a; j++) {
				vertical[i][j] = 0;
			}
		}

		for (int i = 0; i < b; i++) {
			c = 0;
			for (int j = 0; j < a; j++) {
				if (numbers[j][i] == 1) {
					vertical[i][c] = vertical[i][c] + numbers[j][i];
					if (j != a - 1) {
						if (numbers[j + 1][i] != 1) {
							c = c + 1;
						}
					}
				} else {
					if (j != a - 1) {
						if (numbers[j + 1][i] != 1) {
							c = c + 1;
						}
					}
				}
			}
		}
		int d = (b + 1) / 2;

		for (int i = 0; i < b; i++) {
			for (int j = 0; j < a; j++) {
				// System.out.print(vertical[j][i]);
			}
			// System.out.println();
		}

		// horizontal stuff
		for (int i = 0; i < b; i++) {
			for (int j = 0; j < a; j++) {
				horizontal[i][j] = 0;
			}
		}

		for (int i = 0; i < b; i++) {
			c = 0;
			for (int j = 0; j < a; j++) {
				if (numbers[i][j] == 1) {
					horizontal[i][c] = horizontal[i][c] + numbers[i][j];
				} else {
					if (i != b - 1) {
						if (numbers[i + 1][j] != 1) {
							c = c + 1;
						}
					}
				}
			}
		}

		for (int i = 0; i < b; i++) {
			for (int j = 0; j < a; j++) {
				// System.out.print(horizontal[i][j]);
			}
			// System.out.println();
		}

		int[][] horizontalFinal = new int[(b + 1) / 2][a];

		for (int i = 0; i < a; i++) {
			for (int j = 0; j < (b + 1) / 2; j++) {
				horizontalFinal[j][i] = horizontal[i][j];
				// System.out.print(horizontalFinal[j][i]);
			}
			// System.out.println();
		}

		for (int i = 0; i < a; i++) {
			for (int j = 0; j < (b + 1) / 2; j++) {
				if (j != (b + 1) / 2 - 1) {
					if (horizontalFinal[j][i] != 0) {
						if (horizontalFinal[j + 1][i] == 0) {
							horizontalFinal[j + 1][i] = horizontalFinal[j][i];
							horizontalFinal[j][i] = 0;
						}
					}
				}
				// System.out.print(horizontalFinal[j][i]);
			}
			// System.out.println();
		}

		int[][] verticalFinal = new int[b][(a + 1) / 2];

		for (int i = 0; i < (b + 1) / 2; i++) {
			for (int j = 0; j < a; j++) {
				verticalFinal[j][i] = vertical[j][i];
				// System.out.print(verticalFinal[j][i]);
			}
			// System.out.println();
		}
		for (int i = 0; i < (b + 1) / 2; i++) {
			for (int j = 0; j < a; j++) {
				if (i != (b + 1) / 2 - 1) {
					if (verticalFinal[j][i] != 0) {
						if (verticalFinal[j][i + 1] == 0) {
							verticalFinal[j][i + 1] = vertical[j][i];
							verticalFinal[j][i] = 0;
						}

					}
				}
				// System.out.print(verticalFinal[j][i]);
			}
			// System.out.println();
		}

		int k = 0;
		for (int i = 0; i < a; i++) {
			if (horizontalFinal[0][i] == 0) {
				k++;
			}
		}

		int kk = 0;
		for (int i = 0; i < b; i++) {
			if (verticalFinal[i][0] == 0) {
				kk++;
			}
		}

		int lengthAdding;
		int hightAdding;
		if (k == a) {
			lengthAdding = (b + 1) / 2 - 1;
		} else {
			lengthAdding = (b + 1) / 2;
		}
		// System.out.println(lengthAdding);

		if (kk == b) {
			hightAdding = (b + 1) / 2 - 1;
		} else {
			hightAdding = (b + 1) / 2;
		}
		// System.out.println(hightAdding);

		for (int i = 0; i < hightAdding; i++) {
			for (int j = 0; j < lengthAdding + a; j++) {
				if (j < lengthAdding) {
					System.out.print(" " );
				} else {
					if (verticalFinal[j - lengthAdding][i] == 0) {
						System.out.print(" " + "& ");
					} else {
						System.out.print(" & "
								+ verticalFinal[j - lengthAdding][i]);
					}
				}
			}
			System.out.println(" \\\\");
			System.out.println("\\hline");
			System.out.println();
		}

		for (int i = 0; i < b; i++) {
			for (int j = 0; j < hightAdding + b-2; j++) {
				if (j < lengthAdding) {
					System.out.print(horizontalFinal[lengthAdding - j][i]
							+ " & ");
				} else {
					System.out.print(" " + "& ");
				}
			}

			System.out.println(" \\\\");
			System.out.println("\\hline");
			System.out.println();
		}

		for (;;) {
			if (footer.hasNextLine()) {
				System.out.println(footer.nextLine());
			} else {
				break;
			}
		}

	}
}