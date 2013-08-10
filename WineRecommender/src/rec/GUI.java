package rec;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Vector;

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
		Vector<String> userList = new Vector<String>();
		Collections.addAll(userList, "User 1", "User 2", "User 3", "User 4",
				"User 5", "User 6", "User 7", "User 8", "User 9", "User 10",
				"User 11", "User 12", "User 13", "User 14", "User 15", "...");

		Vector<String> wineList = new Vector<String>();
		Collections.addAll(wineList, "Wein 1", "Wein 2", "Wein 3", "Wein 4",
				"Wein 5", "Wein 6", "Wein 7", "Wein 8", "Wein 9", "Wein 10",
				"Wein 11", "Wein 12", "Wein 13", "Wein 14", "Wein 15", "...");

		final Vector<String> order = new Vector<String>();

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
		JLabel wineText = new JLabel("Wein auswählen");
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
		for (int i = 0; i < wineList.size(); i++) {
			JLabel tmp = new JLabel(wineList.elementAt(i));
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

		paintPanel(wineList, normalCollaborativePanel);

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

		paintPanel(wineList, normalHybridPanel);

		panel.add(normalHybridPanel);

		// ScrollBox des Warenkorbs
		JLabel WarenkorbText = new JLabel("Warenkorb des Users");
		WarenkorbText.setBounds(585, 70, 150, 20);
		panel.add(WarenkorbText);

		final JPanel orderPanel = new JPanel();
		orderPanel.setLayout(null);
		orderPanel.setBounds(550, 100, 200, 170);
		orderPanel.setBackground(Color.GRAY);
		orderPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		paintPanel(order, orderPanel);

		panel.add(orderPanel);

		// DropdownBox + Button zum Hinzufügen/Löschen eines Weines
		JLabel chooseWineText = new JLabel("Wein auswählen");
		chooseWineText.setBounds(820, 110, 100, 20);
		panel.add(chooseWineText);

		final JComboBox chooseWineDropDown = new JComboBox(wineList);
		chooseWineDropDown.setBounds(770, 140, 200, 20);
		panel.add(chooseWineDropDown);

		JButton addWineButton = new JButton("Hinzufügen");
		addWineButton.setBounds(770, 190, 100, 40);
		panel.add(addWineButton);

		JButton deleteWineButton = new JButton("Löschen");
		deleteWineButton.setBounds(870, 190, 100, 40);
		panel.add(deleteWineButton);

		// Warenkorb Vector + ActionListener für Hinzufügen-/Löschen-Button
		addWineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				order.add((String) chooseWineDropDown.getSelectedItem());
				paintPanel(order, orderPanel);
				orderPanel.repaint();
			}
		});
		deleteWineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				order.remove(chooseWineDropDown.getSelectedItem());
				paintPanel(order, orderPanel);
				orderPanel.repaint();
			}
		});

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

		paintPanel(wineList, orderContentPanel);

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

		paintPanel(wineList, orderCollaborativePanel);

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

		paintPanel(wineList, orderHybridPanel);

		panel.add(orderHybridPanel);

		// Mittelstrich
		JPanel drawPanel = new JPanel();
		drawPanel.setLayout(null);
		drawPanel.setBounds(490, 20, 1, 780);
		drawPanel.setBackground(Color.GRAY);
		drawPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		panel.add(drawPanel);

		frame.add(panel);
		frame.setVisible(true);
	}

	private void paintPanel(final Vector<String> vector, JPanel panel) {
		panel.removeAll();
		int y;
		y = 0;
		for (int i = 0; i < vector.size(); i++) {
			JLabel tmp = new JLabel(vector.elementAt(i));
			tmp.setBounds(5, y, 200, 20);
			y += 20;
			panel.add(tmp);
		}
	}
}
