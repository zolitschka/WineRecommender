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

import rec.content.PreferenceProfil;
import rec.content.SimilarityList;
import rec.database.MySQLConnection;

/*
 * Graphische Oberfläche
 * 
 * GUI-Methoden:
 * aktuellen User (als String): gui.getUser()
 * aktuellen Wein (als Wine): gui.getWine()
 * aktuellen Warenkorb (als Vector von Wine): gui.getOrder()
 *
 */
public class GUI {
	private final static Vector<Wine> order = new Vector<Wine>();
	private static JComboBox<User> userDropDown;
	private static JComboBox<Wine> wineDropDown;

	public GUI(int width) {
		JFrame frame = new JFrame("Intelligente Weinempfehlung");
		frame.setSize(width, (int) (width * 0.85));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.lightGray);

		final Vector<Wine> wineList = SimilarityList.getWineList();
		final Vector<User> userList = PreferenceProfil.getUserList();
		new SimilarityList();

		// Allgemeine Beschriftung
		JLabel normalText = new JLabel("Normale Empfehlung");
		normalText.setBounds((int) (width * 0.23 - 70), (int) (width * 0.02),
				200, 20);
		normalText.setFont(new Font("Arial", Font.BOLD, 15));
		panel.add(normalText);

		JLabel orderText = new JLabel("Warenkorb Empfehlung");
		orderText.setBounds((int) (width * 0.77 - 80), (int) (width * 0.02),
				200, 20);
		orderText.setFont(new Font("Arial", Font.BOLD, 15));
		panel.add(orderText);

		// DropDownBox von Usern
		JLabel userText = new JLabel("User auswählen");
		userText.setBounds((int) (width * 0.120 - 50), (int) (width * 0.07),
				200, 20);
		panel.add(userText);

		userDropDown = new JComboBox<User>(userList);
		userDropDown.setBounds((int) (width * 0.02), (int) (width * 0.1),
				(int) (width * 0.2), 20);
		panel.add(userDropDown);

		// DropDownBox von Weinen
		JLabel wineText = new JLabel("Wein auswählen");
		wineText.setBounds((int) (width * 0.34 - 50), (int) (width * 0.07),
				200, 20);
		panel.add(wineText);

		wineDropDown = new JComboBox<Wine>(wineList);
		wineDropDown.setBounds((int) (width * 0.24), (int) (width * 0.1),
				(int) (width * 0.2), 20);
		panel.add(wineDropDown);

		// ScrollBox mit normalen Content-Based Empfehlungen
		JLabel normalContentText = new JLabel("Content-Based Empfehlungen");
		normalContentText.setBounds((int) (width * 0.12 - 85),
				(int) (width * 0.3), 200, 20);
		panel.add(normalContentText);

		final JPanel normalContentPanel = new JPanel();

		JScrollPane normalContentScrollPane = new JScrollPane(
				normalContentPanel);

		normalContentScrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		normalContentScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		normalContentPanel.setLayout(null);
		normalContentScrollPane.setBounds((int) (width * 0.02),
				(int) (width * 0.33), (int) (width * 0.2), (int) (width * 0.2));
		normalContentPanel.setBackground(Color.GRAY);
		normalContentPanel.setBorder(BorderFactory
				.createLineBorder(Color.BLACK));

		// TODO wineList2 ersetzen mit der normalen content Empfehlungsliste
		Wine tmp = search(wineList, getWine().getId());
		Vector<Wine> normalContentList = tmp.getSimilarityList();
		paintPanel(normalContentList, normalContentPanel);

		panel.add(normalContentScrollPane);

		// ScrollBox mit normalen Collaborativen Empfehlungen
		JLabel normalCollaborativeText = new JLabel(
				"Collaborative Empfehlungen");
		normalCollaborativeText.setBounds((int) (width * 0.34 - 80),
				(int) (width * 0.3), 200, 20);
		panel.add(normalCollaborativeText);

		JPanel normalCollaborativePanel = new JPanel();
		JScrollPane normalCollaborativeScrollPane = new JScrollPane(
				normalCollaborativePanel);
		normalCollaborativeScrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		normalCollaborativeScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		normalCollaborativePanel.setLayout(null);
		normalCollaborativeScrollPane.setBounds((int) (width * 0.24),
				(int) (width * 0.33), (int) (width * 0.2), (int) (width * 0.2));
		normalCollaborativePanel.setBackground(Color.GRAY);
		normalCollaborativePanel.setBorder(BorderFactory
				.createLineBorder(Color.BLACK));

		// TODO wineList2 ersetzen mit der normalen collaborativen
		// Empfehlungsliste
		// paintPanel(wineList, normalCollaborativePanel);

		panel.add(normalCollaborativeScrollPane);

		// ScrollBox mit normalen Hybriden Empfehlungen
		JLabel normalHybridText = new JLabel("Hybride Empfehlungen");
		normalHybridText.setBounds((int) (width * 0.23 - 65),
				(int) (width * 0.55), 200, 20);
		panel.add(normalHybridText);

		JPanel normalHybridPanel = new JPanel();
		JScrollPane normalHybridScrollPane = new JScrollPane(normalHybridPanel);
		normalHybridScrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		normalHybridScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		normalHybridPanel.setLayout(null);
		normalHybridScrollPane.setBounds((int) (width * 0.13),
				(int) (width * 0.58), (int) (width * 0.2), (int) (width * 0.2));
		normalHybridPanel.setBackground(Color.GRAY);
		normalHybridPanel
				.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// TODO wineList2 ersetzen mit der normalen hybriden Empfehlungsliste
		// paintPanel(wineList, normalHybridPanel);

		panel.add(normalHybridScrollPane);

		// Aktionlistener für wineDropDown
		// TODO collaborative und hybrid ergänzen
		wineDropDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Wine tmp = search(wineList, getWine().getId());
				Vector<Wine> normalContentList = tmp.getSimilarityList();
				paintPanel(normalContentList, normalContentPanel);
				normalContentPanel.repaint();
			}
		});

		// ScrollBox des Warenkorbs
		JLabel WarenkorbText = new JLabel("Warenkorb des Users");
		WarenkorbText.setBounds((int) (width * 0.66 - 65),
				(int) (width * 0.07), 200, 20);
		panel.add(WarenkorbText);

		final JPanel orderPanel = new JPanel();
		JScrollPane orderScrollPane = new JScrollPane(orderPanel);
		orderScrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		orderScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		orderPanel.setLayout(null);
		orderPanel.setPreferredSize(new Dimension(400, 170));
		orderScrollPane.setBounds((int) (width * 0.56), (int) (width * 0.1),
				(int) (width * 0.2), (int) (width * 0.17));
		orderPanel.setBackground(Color.GRAY);
		orderPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		panel.add(orderScrollPane);

		// DropdownBox + Button zum Hinzufügen/Löschen eines Weines
		JLabel chooseWineText = new JLabel("Wein auswählen");
		chooseWineText.setBounds((int) (width * 0.88 - 50),
				(int) (width * 0.11), 200, 20);
		panel.add(chooseWineText);

		final JComboBox<Wine> chooseWineDropDown = new JComboBox<Wine>(wineList);
		chooseWineDropDown.setBounds((int) (width * 0.78),
				(int) (width * 0.14), (int) (width * 0.2), 20);
		panel.add(chooseWineDropDown);

		JButton addWineButton = new JButton("Hinzufügen");
		addWineButton.setBounds((int) (width * 0.78), (int) (width * 0.19),
				(int) (width * 0.1), (int) (width * 0.04));
		panel.add(addWineButton);

		JButton deleteWineButton = new JButton("Löschen");
		deleteWineButton.setBounds((int) (width * 0.88), (int) (width * 0.19),
				(int) (width * 0.1), (int) (width * 0.04));
		panel.add(deleteWineButton);

		// Warenkorb Vector + ActionListener für Hinzufügen-/Löschen-Button
		addWineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Wine tmp = (Wine) chooseWineDropDown.getSelectedItem();
				if (!order.contains(tmp)) {
					order.add(tmp);
					paintPanel(order, orderPanel);
					orderPanel.repaint();
				}
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
		orderContentText.setBounds((int) (width * 0.66 - 85),
				(int) (width * 0.3), 200, 20);
		panel.add(orderContentText);

		JPanel orderContentPanel = new JPanel();
		JScrollPane orderContentScrollPane = new JScrollPane(orderContentPanel);
		orderContentScrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		orderContentScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		orderContentPanel.setLayout(null);
		orderContentScrollPane.setBounds((int) (width * 0.56),
				(int) (width * 0.33), (int) (width * 0.2), (int) (width * 0.2));
		orderContentPanel.setBackground(Color.GRAY);
		orderContentPanel
				.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// TODO wineList2 ersetzen mit der Warenkorb content Empfehlungsliste
		paintPanel(wineList, orderContentPanel);

		panel.add(orderContentScrollPane);

		// ScrollBox mit Warenkorb Collaborativen Empfehlungen
		JLabel orderCollaborativeText = new JLabel("Collaborative Empfehlungen");
		orderCollaborativeText.setBounds((int) (width * 0.88 - 80),
				(int) (width * 0.3), 200, 20);
		panel.add(orderCollaborativeText);

		JPanel orderCollaborativePanel = new JPanel();
		JScrollPane orderCollaborativeScrollPane = new JScrollPane(
				orderCollaborativePanel);
		orderCollaborativeScrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		orderCollaborativeScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		orderCollaborativePanel.setLayout(null);
		orderCollaborativeScrollPane.setBounds((int) (width * 0.78),
				(int) (width * 0.33), (int) (width * 0.2), (int) (width * 0.2));
		orderCollaborativePanel.setBackground(Color.GRAY);
		orderCollaborativePanel.setBorder(BorderFactory
				.createLineBorder(Color.BLACK));

		// TODO wineList2 ersetzen mit der Warenkorb collaborativen
		// Empfehlungsliste
		// paintPanel(wineList, orderCollaborativePanel);

		panel.add(orderCollaborativeScrollPane);

		// ScrollBox mit Warenkorb Hybriden Empfehlungen
		JLabel orderHybridText = new JLabel("Hybride Empfehlungen");
		orderHybridText.setBounds((int) (width * 0.77 - 65),
				(int) (width * 0.55), 200, 20);
		panel.add(orderHybridText);

		JPanel orderHybridPanel = new JPanel();
		JScrollPane orderHybridScrollPane = new JScrollPane(orderHybridPanel);
		orderHybridScrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		orderHybridScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		orderHybridPanel.setLayout(null);
		orderHybridScrollPane.setBounds((int) (width * 0.67),
				(int) (width * 0.58), (int) (width * 0.2), (int) (width * 0.2));
		orderHybridPanel.setBackground(Color.GRAY);
		orderHybridPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// TODO wineList2 ersetzen mit der Warenkorb hybriden Empfehlungsliste
		// paintPanel(wineList, orderHybridPanel);

		panel.add(orderHybridScrollPane);

		// Mittelstrich
		JPanel drawPanel = new JPanel();
		drawPanel.setLayout(null);
		drawPanel.setBounds((int) (width * 0.5), (int) (width * 0.02), 1,
				(int) (width * 0.78));
		drawPanel.setBackground(Color.GRAY);
		drawPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		panel.add(drawPanel);

		frame.add(panel);
		frame.setVisible(true);
	}

	// Zeichnet die Empfehlungs-Vektoren
	private void paintPanel(final Vector<Wine> vector, JPanel panel) {
		panel.removeAll();
		int y;
		y = 0;
		for (int i = 0; i < vector.size(); i++) {
			Wine tmpWine = vector.elementAt(i);
			JLabel tmp = new JLabel(tmpWine.toString());
			tmp.setBounds(5, y, 400, 20);
			y += 20;
			panel.add(tmp);
		}
		if (y > 170)
			panel.setPreferredSize(new Dimension(400, y));
	}

	// Vorrübergehend als String implementiert
	public static String getUser() {
		return (String) userDropDown.getSelectedItem();
	}

	public static Wine getWine() {
		return (Wine) wineDropDown.getSelectedItem();
	}

	public static Vector<Wine> getOrder() {
		return order;
	}

	// Suche nach Wein mit Hilfe der ID
	private static Wine search(Vector<Wine> wineVector, int id) {
		Wine result = null;

		for (int i = 0; i < wineVector.size(); i++) {
			Wine tmp = wineVector.elementAt(i);
			if (tmp.getId() == id)
				result = tmp;
		}

		return result;
	}
}
