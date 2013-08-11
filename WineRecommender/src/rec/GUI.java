package rec;

import java.awt.Color;

import java.awt.Dimension;
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
import javax.swing.JScrollPane;

/*
 * Graphische Oberfläche
 * 
 * Methode um aktuellen User (als String) zu bekommen: gui.getUser()
 * Methode um aktuellen Wein (als String) zu bekommen: gui.getWine()
 * Methode um aktuellen Warenkorb (als Vector) zu bekommen: gui.getOrder()
 *
 *
 */
public class GUI {
	private final Vector<String> order = new Vector<String>();
	private JComboBox<String> userDropDown;
	private JComboBox<String> wineDropDown;

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

		userDropDown = new JComboBox<String>(userList);
		userDropDown.setBounds(10, 100, 200, 20);
		panel.add(userDropDown);

		// DropDownBox von Weinen
		JLabel wineText = new JLabel("Wein auswählen");
		wineText.setBounds(280, 70, 100, 20);
		panel.add(wineText);

		wineDropDown = new JComboBox<String>(wineList);
		wineDropDown.setBounds(230, 100, 200, 20);
		panel.add(wineDropDown);

		// ScrollBox mit normalen Content-Based Empfehlungen
		JLabel normalContentText = new JLabel("Content-Based Empfehlungen");
		normalContentText.setBounds(25, 300, 200, 20);
		panel.add(normalContentText);

		JPanel normalContentPanel = new JPanel();
		JScrollPane normalContentScrollPane = new JScrollPane(
				normalContentPanel);
		normalContentScrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		normalContentScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		normalContentPanel.setLayout(null);
		normalContentScrollPane.setBounds(10, 330, 200, 200);
		normalContentPanel.setBackground(Color.GRAY);
		normalContentPanel.setBorder(BorderFactory
				.createLineBorder(Color.BLACK));

		paintPanel(wineList, normalContentPanel);

		panel.add(normalContentScrollPane);

		// ScrollBox mit normalen Collaborativen Empfehlungen
		JLabel normalCollaborativeText = new JLabel(
				"Collaborative Empfehlungen");
		normalCollaborativeText.setBounds(250, 300, 200, 20);
		panel.add(normalCollaborativeText);

		JPanel normalCollaborativePanel = new JPanel();
		JScrollPane normalCollaborativeScrollPane = new JScrollPane(
				normalCollaborativePanel);
		normalCollaborativeScrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		normalCollaborativeScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		normalCollaborativePanel.setLayout(null);
		normalCollaborativeScrollPane.setBounds(230, 330, 200, 200);
		normalCollaborativePanel.setBackground(Color.GRAY);
		normalCollaborativePanel.setBorder(BorderFactory
				.createLineBorder(Color.BLACK));

		paintPanel(wineList, normalCollaborativePanel);

		panel.add(normalCollaborativeScrollPane);

		// ScrollBox mit normalen Hybriden Empfehlungen
		JLabel normalHybridText = new JLabel("Hybride Empfehlungen");
		normalHybridText.setBounds(160, 550, 200, 20);
		panel.add(normalHybridText);

		JPanel normalHybridPanel = new JPanel();
		JScrollPane normalHybridScrollPane = new JScrollPane(normalHybridPanel);
		normalHybridScrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		normalHybridScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		normalHybridPanel.setLayout(null);
		normalHybridScrollPane.setBounds(120, 580, 200, 200);
		normalHybridPanel.setBackground(Color.GRAY);
		normalHybridPanel
				.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		paintPanel(wineList, normalHybridPanel);

		panel.add(normalHybridScrollPane);

		// ScrollBox des Warenkorbs
		JLabel WarenkorbText = new JLabel("Warenkorb des Users");
		WarenkorbText.setBounds(585, 70, 150, 20);
		panel.add(WarenkorbText);

		final JPanel orderPanel = new JPanel();
		JScrollPane orderScrollPane = new JScrollPane(orderPanel);
		orderScrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		orderScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		orderPanel.setLayout(null);
		orderScrollPane.setBounds(550, 100, 200, 170);
		orderPanel.setBackground(Color.GRAY);
		orderPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		panel.add(orderScrollPane);

		// DropdownBox + Button zum Hinzufügen/Löschen eines Weines
		JLabel chooseWineText = new JLabel("Wein auswählen");
		chooseWineText.setBounds(820, 110, 100, 20);
		panel.add(chooseWineText);

		final JComboBox<String> chooseWineDropDown = new JComboBox<String>(
				wineList);
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
				String tmp = (String) chooseWineDropDown.getSelectedItem();
				// if (!order.contains(tmp)) {
				order.add(tmp);
				paintPanel(order, orderPanel);
				orderPanel.repaint();
				// }
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
		JScrollPane orderContentScrollPane = new JScrollPane(orderContentPanel);
		orderContentScrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		orderContentScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		orderContentPanel.setLayout(null);
		orderContentScrollPane.setBounds(550, 330, 200, 200);
		orderContentPanel.setBackground(Color.GRAY);
		orderContentPanel
				.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		paintPanel(wineList, orderContentPanel);

		panel.add(orderContentScrollPane);

		// ScrollBox mit Warenkorb Collaborativen Empfehlungen
		JLabel orderCollaborativeText = new JLabel("Collaborative Empfehlungen");
		orderCollaborativeText.setBounds(790, 300, 200, 20);
		panel.add(orderCollaborativeText);

		JPanel orderCollaborativePanel = new JPanel();
		JScrollPane orderCollaborativeScrollPane = new JScrollPane(
				orderCollaborativePanel);
		orderCollaborativeScrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		orderCollaborativeScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		orderCollaborativePanel.setLayout(null);
		orderCollaborativeScrollPane.setBounds(770, 330, 200, 200);
		orderCollaborativePanel.setBackground(Color.GRAY);
		orderCollaborativePanel.setBorder(BorderFactory
				.createLineBorder(Color.BLACK));

		paintPanel(wineList, orderCollaborativePanel);

		panel.add(orderCollaborativeScrollPane);

		// ScrollBox mit Warenkorb Hybriden Empfehlungen
		JLabel orderHybridText = new JLabel("Hybride Empfehlungen");
		orderHybridText.setBounds(700, 550, 200, 20);
		panel.add(orderHybridText);

		JPanel orderHybridPanel = new JPanel();
		JScrollPane orderHybridScrollPane = new JScrollPane(orderHybridPanel);
		orderHybridScrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		orderHybridScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		orderHybridPanel.setLayout(null);
		orderHybridScrollPane.setBounds(660, 580, 200, 200);
		orderHybridPanel.setBackground(Color.GRAY);
		orderHybridPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		paintPanel(wineList, orderHybridPanel);

		panel.add(orderHybridScrollPane);

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
		panel.setPreferredSize(new Dimension(180, y));
	}

	// Vorrübergehend als String implementiert
	public String getUser() {
		return (String) userDropDown.getSelectedItem();
	}

	public String getWine() {
		return (String) wineDropDown.getSelectedItem();
	}

	public Vector<String> getOrder() {
		return order;
	}
}
