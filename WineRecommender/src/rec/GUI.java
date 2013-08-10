package rec;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI {

	public GUI() {
		JFrame frame = new JFrame("Intelligente Weinempfehlung");
		frame.setSize(1000, 850);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.lightGray);

		// Vorrübergehend
		String[] userList = { "User 1", "User 2", "User 3", "User 4", "User 5",
				"User 6", "User 7", "User 8", "User 9", "User 10", "User 11",
				"User 12", "User 13", "User 14", "User 15", "..." };
		String[] wineList = { "Wein 1", "Wein 2", "Wein 3", "Wein 4", "Wein 5",
				"Wein 6", "Wein 7", "Wein 8", "Wein 9", "Wein 10", "Wein 11",
				"Wein 12", "Wein 13", "Wein 14", "Wein 15", "..." };

		// Allgemeine Beschriftung
		JLabel normalText = new JLabel("Normale Empfehlung");
		normalText.setBounds(150, 20, 200, 20);
		normalText.setFont(new Font("Arial", Font.BOLD, 15));
		panel.add(normalText);

		JLabel orderText = new JLabel("Warenkorb Empfehlung");
		orderText.setBounds(680, 20, 200, 20);
		orderText.setFont(new Font("Arial", Font.BOLD, 15));
		panel.add(orderText);

		// DropDownBox von Usern
		JLabel userText = new JLabel("User auswählen");
		userText.setBounds(60, 70, 100, 20);
		panel.add(userText);

		JComboBox userDropDown = new JComboBox(userList);
		userDropDown.setBounds(10, 100, 200, 20);
		panel.add(userDropDown);

		// DropDownBox von Weinen
		JLabel wineText = new JLabel("User auswählen");
		wineText.setBounds(280, 70, 100, 20);
		panel.add(wineText);

		JComboBox wineDropDown = new JComboBox(wineList);
		wineDropDown.setBounds(230, 100, 200, 20);
		panel.add(wineDropDown);

		// ScrollBox mit normalen Content-Based Empfehlungen
		JLabel normalContentText = new JLabel("Content-Based Empfehlungen");
		normalContentText.setBounds(25, 300, 200, 20);
		panel.add(normalContentText);

		JPanel normalContentPanel = new JPanel();
		normalContentPanel.setLayout(null);
		normalContentPanel.setBounds(10, 330, 200, 200);
		normalContentPanel.setBackground(Color.GRAY);
		normalContentPanel.setBorder(BorderFactory
				.createLineBorder(Color.BLACK));

		// Hinzufügen von Weinen
		int y = 0;
		for (int i = 0; i < wineList.length; i++) {
			JLabel tmp = new JLabel(wineList[i]);
			tmp.setBounds(5, y, 200, 20);
			y += 20;
			normalContentPanel.add(tmp);
		}

		panel.add(normalContentPanel);

		// ScrollBox mit normalen Collaborativen Empfehlungen
		JLabel normalCollaborativeText = new JLabel(
				"Collaborative Empfehlungen");
		normalCollaborativeText.setBounds(250, 300, 200, 20);
		panel.add(normalCollaborativeText);

		JPanel normalCollaborativePanel = new JPanel();
		normalCollaborativePanel.setLayout(null);
		normalCollaborativePanel.setBounds(230, 330, 200, 200);
		normalCollaborativePanel.setBackground(Color.GRAY);
		normalCollaborativePanel.setBorder(BorderFactory
				.createLineBorder(Color.BLACK));

		// Hinzufügen von Weinen
		y = 0;
		for (int i = 0; i < wineList.length; i++) {
			JLabel tmp = new JLabel(wineList[i]);
			tmp.setBounds(5, y, 200, 20);
			y += 20;
			normalCollaborativePanel.add(tmp);
		}

		panel.add(normalCollaborativePanel);

		// ScrollBox mit normalen Hybriden Empfehlungen
		JLabel normalHybridText = new JLabel("Hybride Empfehlungen");
		normalHybridText.setBounds(160, 550, 200, 20);
		panel.add(normalHybridText);

		JPanel normalHybridPanel = new JPanel();
		normalHybridPanel.setLayout(null);
		normalHybridPanel.setBounds(120, 580, 200, 200);
		normalHybridPanel.setBackground(Color.GRAY);
		normalHybridPanel
				.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// Hinzufügen von Weinen
		y = 0;
		for (int i = 0; i < wineList.length; i++) {
			JLabel tmp = new JLabel(wineList[i]);
			tmp.setBounds(5, y, 200, 20);
			y += 20;
			normalHybridPanel.add(tmp);
		}

		panel.add(normalHybridPanel);

		// ScrollBox des Warenkorbs
		JLabel WarenkorbText = new JLabel("Warenkorb des Users");
		WarenkorbText.setBounds(585, 70, 150, 20);
		panel.add(WarenkorbText);

		JPanel orderPanel = new JPanel();
		orderPanel.setLayout(null);
		orderPanel.setBounds(550, 100, 200, 170);
		orderPanel.setBackground(Color.GRAY);
		orderPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		panel.add(orderPanel);

		// DropdownBox + Button zum Hinzufügen eines Weines
		JLabel addWineText = new JLabel("Wein hinzufügen");
		addWineText.setBounds(820, 100, 100, 20);
		panel.add(addWineText);

		JComboBox addWineDropDown = new JComboBox(wineList);
		addWineDropDown.setBounds(770, 130, 200, 20);
		panel.add(addWineDropDown);

		JButton addWineButton = new JButton("Hinzufügen");
		addWineButton.setBounds(770, 220, 100, 40);
		panel.add(addWineButton);

		// DropdownBox + Button zum Löschen eines Weines
		JLabel deleteWineText = new JLabel("Wein löschen");
		deleteWineText.setBounds(820, 160, 100, 20);
		panel.add(deleteWineText);

		JComboBox deleteWineDropDown = new JComboBox(wineList);
		deleteWineDropDown.setBounds(770, 190, 200, 20);
		panel.add(deleteWineDropDown);

		JButton deleteWineButton = new JButton("Löschen");
		deleteWineButton.setBounds(870, 220, 100, 40);
		panel.add(deleteWineButton);

		// ScrollBox mit Warenkorb Content-Based Empfehlungen
		JLabel orderContentText = new JLabel("Content-Based Empfehlungen");
		orderContentText.setBounds(565, 300, 200, 20);
		panel.add(orderContentText);

		JPanel orderContentPanel = new JPanel();
		orderContentPanel.setLayout(null);
		orderContentPanel.setBounds(550, 330, 200, 200);
		orderContentPanel.setBackground(Color.GRAY);
		orderContentPanel
				.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// Hinzufügen von Weinen
		y = 0;
		for (int i = 0; i < wineList.length; i++) {
			JLabel tmp = new JLabel(wineList[i]);
			tmp.setBounds(5, y, 200, 20);
			y += 20;
			orderContentPanel.add(tmp);
		}

		panel.add(orderContentPanel);

		// ScrollBox mit Warenkorb Collaborativen Empfehlungen
		JLabel orderCollaborativeText = new JLabel("Collaborative Empfehlungen");
		orderCollaborativeText.setBounds(790, 300, 200, 20);
		panel.add(orderCollaborativeText);

		JPanel orderCollaborativePanel = new JPanel();
		orderCollaborativePanel.setLayout(null);
		orderCollaborativePanel.setBounds(770, 330, 200, 200);
		orderCollaborativePanel.setBackground(Color.GRAY);
		orderCollaborativePanel.setBorder(BorderFactory
				.createLineBorder(Color.BLACK));

		// Hinzufügen von Weinen
		y = 0;
		for (int i = 0; i < wineList.length; i++) {
			JLabel tmp = new JLabel(wineList[i]);
			tmp.setBounds(5, y, 200, 20);
			y += 20;
			orderCollaborativePanel.add(tmp);
		}

		panel.add(orderCollaborativePanel);

		// ScrollBox mit Warenkorb Hybriden Empfehlungen
		JLabel orderHybridText = new JLabel("Hybride Empfehlungen");
		orderHybridText.setBounds(700, 550, 200, 20);
		panel.add(orderHybridText);

		JPanel orderHybridPanel = new JPanel();
		orderHybridPanel.setLayout(null);
		orderHybridPanel.setBounds(660, 580, 200, 200);
		orderHybridPanel.setBackground(Color.GRAY);
		orderHybridPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// Hinzufügen von Weinen
		y = 0;
		for (int i = 0; i < wineList.length; i++) {
			JLabel tmp = new JLabel(wineList[i]);
			tmp.setBounds(5, y, 200, 20);
			y += 20;
			orderHybridPanel.add(tmp);
		}

		panel.add(orderHybridPanel);

		// Rest
		JPanel drawPanel = new JPanel();
		drawPanel.setLayout(null);
		drawPanel.setBounds(490, 20, 1, 780);
		drawPanel.setBackground(Color.GRAY);
		drawPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		panel.add(drawPanel);

		frame.add(panel);
		frame.setVisible(true);
	}
}
