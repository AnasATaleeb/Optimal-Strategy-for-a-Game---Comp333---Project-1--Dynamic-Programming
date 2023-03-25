package application;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class OptimalSolution {
	private GridPane p = new GridPane();
	private int[] coinsPlayer1;
	private int[] coinsPlayer2;
	private byte[] p1;
	private byte[] p2;

	public OptimalSolution() {
		// TODO Auto-generated constructor stub

	}

	public int optimalStrategyOfGame(int arr[]) {
		// Matrix to store max value.
		int[][] Max_Value = new int[arr.length][arr.length];

		// Filling the matrix using the above algorithm.
		for (int k = 0; k < arr.length; k++) {
			for (int i = 0, j = k; j < arr.length; i++, j++) {

				int x, y, z;
				if (i + 2 <= j)
					x = Max_Value[i + 2][j];
				else
					x = 0;
				if (i + 1 <= j - 1)
					y = Max_Value[i + 1][j - 1];
				else
					y = 0;
				if (i <= j - 2)
					z = Max_Value[i][j - 2];
				else
					z = 0;

				Max_Value[i][j] = Math.max(arr[i] + Math.min(x, y), arr[j] + Math.min(y, z));
			}
		}

		p.setMaxWidth(Max_Value.length * 100);

		for (int k = 0; k < Max_Value.length; k++) {
			for (int k2 = 0; k2 < Max_Value[k].length; k2++) {
				Label l = new Label(Max_Value[k][k2] + "");
				l.setPadding(new Insets(10));
				l.setStyle("-fx-font-size: 14;\n" + "-fx-font-family: Times New Roman;\n" + "-fx-font-weight: Bold;\n");
				p.add(l, k2, k);
			}
		}

		coinsPlayer1 = new int[arr.length / 2];
		coinsPlayer2 = new int[arr.length / 2];
		p1 = new byte[arr.length / 2];
		p2 = new byte[arr.length / 2];
		findCoinP1(0, arr.length - 1, arr, Max_Value, 0);

		System.out.println(getCoinsPlayer1());
		System.out.println(getCoinsPlayer2());

		return Max_Value[0][arr.length - 1];
	}

	public GridPane getDP() {
		return p;
	}

	public void setDP(GridPane p) {
		this.p = p;
	}

	public void findCoinP1(int i, int j, int[] arr, int[][] dp, int index) {
//		System.out.println("p1111111 --> " + i + " " + j);
		if (j - i == 1) {

			if (arr[i] > arr[j]) {
				coinsPlayer1[index] = arr[i];
				coinsPlayer2[index] = arr[j];
				p1[index] = 0;
				p2[index] = 1;
				if (arr.length > 4)
					findCoinP2(i, j, arr, dp, index);
			} else if (arr[i] < arr[j]) {
				coinsPlayer1[index] = arr[j];
				coinsPlayer2[index] = arr[i];
				p1[index] = 1;
				p2[index] = 0;
			}
		} else {
			int ch1 = arr[i] + Math.min(dp[i + 2][j], dp[i + 1][j - 1]);
			int ch2 = arr[j] + Math.min(dp[i + 1][j - 1], dp[i][j - 2]);

			if (ch1 > ch2) {
				coinsPlayer1[index] = arr[i];
				p1[index] = 0;
				findCoinP2(i + 1, j, arr, dp, index);
				if (dp[i + 1][j - 1] < dp[i + 2][j])
					findCoinP1(i + 1, j - 1, arr, dp, index + 1);
				else
					findCoinP1(i + 2, j, arr, dp, index + 1);

			} else {
				coinsPlayer1[index] = arr[j];
				p1[index] = 1;
				findCoinP2(i, j - 1, arr, dp, index);
				if (dp[i + 1][j - 1] < dp[i][j - 2])
					findCoinP1(i + 1, j - 1, arr, dp, index + 1);
				else
					findCoinP1(i, j - 2, arr, dp, index + 1);
			}
		}
//		System.out.println("Playerrrrrrr1");
//		System.out.println("index = " + index);
//		System.out.println("p1 = " + p1[index]);
//		System.out.println("p2 = " + p2[index]);
	}

	public void findCoinP2(int i, int j, int[] arr, int[][] dp, int index) {
		if (j - i == 1) {
			if (arr[i] > arr[j]) {
				coinsPlayer2[index] = arr[i];
				p1[index] = 1;
				p2[index] = 0;
			} else if (arr[i] < arr[j]) {
				coinsPlayer2[index] = arr[j];
				p1[index] = 0;
				p2[index] = 1;
			}
			return;
		} else {
			int ch1 = arr[i] + Math.min(dp[i + 2][j], dp[i + 1][j - 1]);
			int ch2 = arr[j] + Math.min(dp[i + 1][j - 1], dp[i][j - 2]);

			if (ch1 > ch2) {
				coinsPlayer2[index] = arr[i];
				p2[index] = 0;

			} else {
				coinsPlayer2[index] = arr[j];
				p2[index] = 1;
			}
		}
	}

	public String getCoinsPlayer1() {
		String s = "[ ";

		for (int i = 0; i < coinsPlayer1.length; i++) {
			if (i < coinsPlayer1.length - 1)
				s += coinsPlayer1[i] + ", ";
			else
				s += coinsPlayer1[i];
		}
		s += " ]";

		return s;
	}

	public void setCoinsPlayer1(int[] coinsPlayer1) {
		this.coinsPlayer1 = coinsPlayer1;
	}

	public String getCoinsPlayer2() {
		String s = "[ ";

		for (int i = 0; i < coinsPlayer2.length; i++) {
			if (i < coinsPlayer2.length - 1)
				s += coinsPlayer2[i] + ", ";
			else
				s += coinsPlayer2[i];
		}
		s += " ]";
		return s;
	}

	public void setCoinsPlayer2(int[] coinsPlayer2) {
		this.coinsPlayer2 = coinsPlayer2;
	}

	public byte[] getP1() {
		return p1;
	}

	public void setP1(byte[] p1) {
		this.p1 = p1;
	}

	public byte[] getP2() {
		return p2;
	}

	public void setP2(byte[] p2) {
		this.p2 = p2;
	}

}