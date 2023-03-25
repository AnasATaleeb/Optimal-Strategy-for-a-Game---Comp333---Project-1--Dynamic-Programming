package application;

import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Main extends Application {
	private OptimalSolution game = new OptimalSolution();
	private Alert error = new Alert(AlertType.ERROR);
	String array;

	@Override
	public void start(Stage primaryStage) {
		try {

			BorderPane pane = new BorderPane();
			primaryStage.getIcons().add(new Image("game-development.png"));
			primaryStage.setTitle("Optimal Strategy for a Game using Dynamic Programming");

			Label coin = new Label("Coins [] =             ");
			coin.setPadding(new Insets(29));

			TextField coinst = new TextField();
			coinst.setMinWidth(1200);
			coinst.setMinHeight(80);

			if (array != null)
				coinst.setText(array);

			coinst.setPromptText("Enter numbers so that their number is even like : 2,5,6,9  ");
			coinst.deselect();

			IconedTextFieled(coin, coinst);
			HBox h = new HBox(coin, coinst);
			h.setAlignment(Pos.CENTER);

			ImageView p = new ImageView(new Image("treasure.png"));
			p.setFitHeight(50);
			p.setFitWidth(50);
			Button expected = new Button("Expected Result", p);
			expected.setPadding(new Insets(15));
			icons(expected);
			butoonEffect(expected);

			ImageView d = new ImageView(new Image("table.png"));
			d.setFitHeight(50);
			d.setFitWidth(50);
			Button DP = new Button("DP Tabel", d);
			DP.setPadding(new Insets(15));
			icons(DP);
			butoonEffect(DP);

			ImageView c = new ImageView(new Image("coin.png"));
			c.setFitHeight(50);
			c.setFitWidth(50);
			Button simulate = new Button("Simulate Game", c);
			simulate.setPadding(new Insets(15));
			icons(simulate);
			butoonEffect(simulate);

			HBox ButtonBox = new HBox(20, expected, DP, simulate);
			ButtonBox.setAlignment(Pos.CENTER);

			expected.setOnAction(e -> {
				array = new String(coinst.getText());
				String s = coinst.getText();
				String[] s1 = s.split(",");
				if (s1.length % 2 == 0) {
					try {
						int arr1[] = new int[s1.length];
						for (int i = 0; i < s1.length; i++) {
							arr1[i] = Integer.parseInt(s1[i].trim());
						}

						expectedAction(primaryStage, arr1, s);
					} catch (Exception ex) {
						error.setContentText(ex.getMessage()
								+ "\nArray should be an integer number and number is even !\n Try again please!!");
						error.show();
					}
				} else {
					error.setContentText(
							"The Number of Coin should be even \nAnd shoould be enter like number,number eg: 2,5,6,9");
					error.show();
				}
			});

			DP.setOnAction(e -> {
				String s = coinst.getText();
				String[] s1 = s.split(",");
				if (s1.length % 2 == 0) {
					try {
						array = new String(coinst.getText());
						int arr1[] = new int[s1.length];
						for (int i = 0; i < s1.length; i++) {
							arr1[i] = Integer.parseInt(s1[i].trim());
						}
						dpAction(primaryStage, arr1);
					} catch (Exception ex) {
						error.setContentText(ex.getMessage()
								+ "\nArray should be an integer number and number is even !\n Try again please!!");
						error.show();
					}
				} else {
					error.setContentText(
							"The Number of Coin should be even \nAnd shoould be enter like number,number eg: 2,5,6,9");
					error.show();
				}
			});

			simulate.setOnAction(e -> {
				String s = coinst.getText();
				String[] s1 = s.split(",");
				if (s1.length % 2 == 0) {
					try {
						array = new String(coinst.getText());
						int arr1[] = new int[s1.length];
						for (int i = 0; i < s1.length; i++) {
							arr1[i] = Integer.parseInt(s1[i].trim());
						}
						simulation(primaryStage, arr1);
					} catch (Exception ex) {
						error.setContentText(ex.getMessage()
								+ "\nArray should be an integer number and number is even !\n Try again please!!");
						error.show();
					}
				} else {
					error.setContentText(
							"The Number of Coin should be even \nAnd shoould be enter like number,number eg: 2,5,6,9");
					error.show();
				}
			});

			VBox v = new VBox(35, h, ButtonBox);
			v.setAlignment(Pos.CENTER);
			pane.setCenter(v);

			pane.setStyle("-fx-background-color: linear-gradient(to right, #a7f9ef, #d7bce8);");
			Scene scene = new Scene(pane, 1535, 800);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

	private void dpAction(Stage primaryStage, int[] arr1) {
		BorderPane pane = new BorderPane();
		Label l = new Label("THE DP TABEL");
		l.setPadding(new Insets(10));
		l.setStyle("-fx-border-radius: 20 20 20 20;\n" + "-fx-font-size: 20;\n" + "-fx-font-family: Times New Roman;\n"
				+ "-fx-font-weight: Bold;\n" + "-fx-background-color: #f6f6f6;\n" + "-fx-border-color: #d8d9e0;\n"
				+ "-fx-border-width:  5;" + "-fx-background-radius: 20 20 20 20");

		ImageView d = new ImageView(new Image("refund.png"));
		d.setFitHeight(50);
		d.setFitWidth(50);
		Button back = new Button("Back to main page", d);
		icons(back);
		butoonEffect(back);
		back.setOnAction(e -> {
			start(primaryStage);
		});
		game.getDP().setAlignment(Pos.CENTER);
		ScrollPane scroll = new ScrollPane(game.getDP());
		scroll.setCenterShape(true);

		scroll.setMaxSize(1450, 600);
		VBox mix = new VBox(10, l, scroll, back);
		mix.setAlignment(Pos.CENTER);

		pane.setCenter(mix);

		game.getDP().getChildren().clear();
		game.getDP().setGridLinesVisible(true);
		game.getDP().vgapProperty().add(10);
		game.getDP().hgapProperty().add(10);
		game.getDP()
				.setStyle("-fx-border-radius: 20 20 20 20;\n" + "-fx-font-size: 20;\n"
						+ "-fx-font-family: Times New Roman;\n" + "-fx-font-weight: Bold;\n"
						+ "-fx-background-color: #f6f6f6;\n" + "-fx-border-color: #d8d9e0;\n" + "-fx-border-width:  4;"
						+ "-fx-background-radius: 20 20 20 20");

		game.getDP().setAlignment(Pos.CENTER);
		game.getDP().setPadding(new Insets(10));

		game.optimalStrategyOfGame(arr1);
		pane.setStyle("-fx-background-color: linear-gradient(to right, #a7f9ef, #d7bce8);");
		Scene scene = new Scene(pane, 1535, 800);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	private void expectedAction(Stage primaryStage, int[] arr1, String s) {
		BorderPane pane = new BorderPane();
		Label coin = new Label("Coins [] =             ");
		coin.setPadding(new Insets(7));

		TextField coinst = new TextField();
		coinst.setMinWidth(1200);
		IconedTextFieled(coin, coinst);
		HBox h = new HBox(coin, coinst);
		h.setAlignment(Pos.CENTER);

		coinst.setText(s);
		coinst.setEditable(false);

		Label result = new Label("Expected result =  ");
		result.setPadding(new Insets(7));

		TextField resultt = new TextField();
		resultt.setMinWidth(1200);
		IconedTextFieled(result, resultt);
		HBox h1 = new HBox(result, resultt);
		h1.setAlignment(Pos.CENTER);

		Label ply1 = new Label("The coins that give the expected result =  ");
		ply1.setPadding(new Insets(7));

		TextField p1 = new TextField();
		p1.setMinWidth(1060);
		IconedTextFieled(ply1, p1);
		HBox h2 = new HBox(ply1, p1);
		h2.setAlignment(Pos.CENTER);

		ImageView d = new ImageView(new Image("refund.png"));
		d.setFitHeight(50);
		d.setFitWidth(50);
		Button back = new Button("Back to main page", d);
		icons(back);
		butoonEffect(back);
		back.setOnAction(e -> {
			start(primaryStage);
		});

		VBox v = new VBox(10, h, h1, h2, back);
		v.setAlignment(Pos.CENTER);

		pane.setCenter(v);

		resultt.setText("" + game.optimalStrategyOfGame(arr1));
		p1.setText(game.getCoinsPlayer1());

		pane.setStyle("-fx-background-color: linear-gradient(to right, #a7f9ef, #d7bce8);");
		Scene scene = new Scene(pane, 1535, 800);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	private void simulation(Stage primaryStage, int[] arr) {
		BorderPane pane = new BorderPane();
		HBox h = new HBox(15);
		h.setAlignment(Pos.CENTER);
		h.setPadding(new Insets(120));

		for (int i = 0; i < arr.length; i++) {

			Button c = new Button(arr[i] + "");
			icons1(c);

			h.getChildren().add(c);

		}

		ImageView p = new ImageView(new Image("game-controller.png"));
		p.setFitHeight(50);
		p.setFitWidth(50);

		Button play = new Button("Play", p);
		icons(play);
		butoonEffect(play);
		play.setPadding(new Insets(15));

		ImageView d = new ImageView(new Image("refund.png"));
		d.setFitHeight(50);
		d.setFitWidth(50);
		Button back = new Button("Back to main page", d);
		icons(back);
		butoonEffect(back);
		back.setOnAction(e -> {
			start(primaryStage);
		});

		ScrollPane scroll = new ScrollPane();
		scroll.setContent(h);

		ImageView palyer1 = new ImageView(new Image("gamer (1).png"));
		palyer1.setFitHeight(50);
		palyer1.setFitWidth(50);

		Label l1 = new Label("Palyer # 1");
		l1.setPadding(new Insets(5));
		l1.setStyle("-fx-border-radius: 20 20 20 20;\n" + "-fx-font-size: 20;\n" + "-fx-font-family: Times New Roman;\n"
				+ "-fx-font-weight: Bold;\n" + "-fx-background-color: #f6f6f6;\n" + "-fx-border-color: #d8d9e0;\n"
				+ "-fx-border-width:  5;" + "-fx-background-radius: 20 20 20 20");

		ImageView palyer2 = new ImageView(new Image("gamer.png"));
		palyer2.setFitHeight(50);
		palyer2.setFitWidth(50);

		Label l2 = new Label("Palyer # 2");
		l2.setPadding(new Insets(5));
		l2.setStyle("-fx-border-radius: 20 20 20 20;\n" + "-fx-font-size: 20;\n" + "-fx-font-family: Times New Roman;\n"
				+ "-fx-font-weight: Bold;\n" + "-fx-background-color: #f6f6f6;\n" + "-fx-border-color: #d8d9e0;\n"
				+ "-fx-border-width:  5;" + "-fx-background-radius: 20 20 20 20");

		scroll.setMaxSize(1450, 300);

		HBox but = new HBox(20, play, back);
		but.setAlignment(Pos.CENTER);
		but.setPadding(new Insets(15));

		play.setOnAction(e -> {
			game.optimalStrategyOfGame(arr);
			byte[] p1 = game.getP1();
			byte[] p2 = game.getP2();
			System.out.print("p1[] = ");
			for (int i = 0; i < p1.length; i++) {
				System.out.print(p1[i] + " ");
			}
			System.out.println();
			
			System.out.print("p2[] = ");
			for (int i = 0; i < p2.length; i++) {
				System.out.print(p2[i] + " ");
			}
			System.out.println();
			
			int x = 0;
			int y = arr.length - 1;
			for (int i = 0,j=0, k = 0; i < arr.length / 2; i++,j++) {
				if (p1[i] == 0) {
					TranslateTransition t = new TranslateTransition();
					t.setDuration(Duration.seconds(2));
					if (i != 0) {
						t.setDelay(Duration.millis(k + 2000));
						k += 2000;
					}
					t.setNode(h.getChildren().get(x++));

					t.setByY(-80);
					t.play();
				} else if (p1[i] == 1) {
					TranslateTransition t = new TranslateTransition();
					t.setDuration(Duration.seconds(2));
					if(i!=0) {
						t.setDelay(Duration.millis(k + 2000));
						k += 2000;
						
					}
					t.setNode(h.getChildren().get(y--));

					t.setByY(-80);
					t.play();
				}

				if (p2[j] == 0) {
					TranslateTransition t = new TranslateTransition();
					t.setDuration(Duration.seconds(2));
					t.setDelay(Duration.millis(k + 2000));
					k += 2000;
					t.setNode(h.getChildren().get(x++));
					t.setByY(80);
					t.play();
				} else if (p2[j] == 1) {
					TranslateTransition t = new TranslateTransition();
					t.setDuration(Duration.seconds(2));
					t.setDelay(Duration.millis(k + 2000));
					k += 2000;
					t.setNode(h.getChildren().get(y--));
					t.setByY(80);
					t.play();
				}

			}
		});

		scroll.setStyle(
				"-fx-background-color: #f6f6f6;\n" + "-fx-border-color: #d8d9e0;\n" + "-fx-border-width:  3.5;");

		HBox p1 = new HBox(15, palyer1, l1);
		p1.setAlignment(Pos.CENTER);

		HBox p2 = new HBox(15, palyer2, l2);
		p2.setAlignment(Pos.CENTER);

		VBox mix = new VBox(15, p1, scroll, p2);
		mix.setAlignment(Pos.CENTER);
		pane.setCenter(mix);
		pane.setBottom(but);
		pane.setMaxSize(1500, 800);
		pane.setPadding(new Insets(10));
		pane.setStyle("-fx-background-color: linear-gradient(to right, #a7f9ef, #d7bce8);");
		Scene scene = new Scene(pane, 1535, 800);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

	private void IconedTextFieled(javafx.scene.Node l, javafx.scene.Node t) {
		l.setStyle("-fx-border-color: #d8d9e0;" + "-fx-font-size: 14;\n" + "-fx-border-width: 1;"
				+ "-fx-border-radius: 50;" + "-fx-font-weight: Bold;\n" + "-fx-background-color:#d8d9e0;"
				+ "-fx-background-radius: 50 0 0 50");

		t.setStyle("-fx-border-radius: 0 50 50 0;\n" + "-fx-font-size: 14;\n" + "-fx-font-family: Times New Roman;\n"
				+ "-fx-font-weight: Bold;\n" + "-fx-background-color: #f6f6f6;\n" + "-fx-border-color: #d8d9e0;\n"
				+ "-fx-border-width:  3.5;" + "-fx-background-radius: 0 50 50 0");
	}

	private void icons(Node l) {
		l.setStyle("-fx-border-radius: 25 25 25 25;\n" + "-fx-font-size: 14;\n" + "-fx-font-family: Times New Roman;\n"
				+ "-fx-font-weight: Bold;\n" + "-fx-background-color: transparent;\n" + "-fx-border-color: #d8d9e0;\n"
				+ "-fx-border-width:  3.5;" + "-fx-background-color: #f6f6f6;\n"
				+ "-fx-background-radius: 25 25 25 25");
	}

	private void icons1(Node l) {
		l.setStyle("-fx-border-radius: 25 25 25 25;\n" + "-fx-font-size: 14;\n" + "-fx-font-family: Times New Roman;\n"
				+ "-fx-font-weight: Bold;\n" + "-fx-background-color: transparent;\n" + "-fx-border-color: #d8d9e0;\n"
				+ "-fx-border-width:  3.5;" + "-fx-background-color: #ffc749;\n"
				+ "-fx-background-radius: 25 25 25 25");
	}

	private void butoonEffect(Node b) {
		b.setOnMouseMoved(e -> {
			b.setStyle("-fx-border-radius: 25 25 25 25;\n" + "-fx-font-size: 14;\n"
					+ "-fx-font-family: Times New Roman;\n" + "-fx-font-weight: Bold;\n" + " -fx-text-fill: #CE2029;\n"
					+ "-fx-background-color: #d8d9e0;\n" + "-fx-border-color: #d8d9e0;\n" + "-fx-border-width:  3.5;"
					+ "-fx-background-radius: 25 25 25 25");
		});

		b.setOnMouseExited(e -> {
			b.setStyle("-fx-border-radius: 25 25 25 25;\n" + "-fx-font-size: 14;\n"
					+ "-fx-font-family: Times New Roman;\n" + "-fx-font-weight: Bold;\n"
					+ "-fx-background-color: #f6f6f6;\n" + "-fx-border-color: #d8d9e0;\n" + "-fx-border-width:  3.5;"
					+ "-fx-background-radius: 25 25 25 25");
		});
	}

}
