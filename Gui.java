
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui{
    private static CardLayout cardLayout;
    private static JPanel contentPanel;

    public void createAndShowGUI() {
        JFrame frame = new JFrame("EXTREME GAMES");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 500);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton createGame = new JButton("Nuevo");
        JButton eraseGame = new JButton("Borrar");
        JButton buyBoletos = new JButton("Boletos");
        JButton editGame = new JButton("Editar");
        JButton boxMoney = new JButton("Caja");

        buttonPanel.add(createGame);
        buttonPanel.add(editGame);
        buttonPanel.add(eraseGame);
        buttonPanel.add(buyBoletos);
        buttonPanel.add(boxMoney);
        
        mainPanel.add(buttonPanel, BorderLayout.NORTH);

        contentPanel = new JPanel();
        cardLayout = new CardLayout();
        contentPanel.setLayout(cardLayout);

        // Vista para Crear
        JPanel newGame = new JPanel();
        JButton buttonCreateGame = new JButton("Crear");
        newGame.add(new JLabel("Juego:"));
        newGame.add(new TextField(50));
        newGame.add(new JLabel("Precio:"));
        newGame.add(new TextField(5));
        newGame.add(buttonCreateGame);
        newGame.add(new JLabel(""));
        
        // Vista para Editar juegos
        JPanel modifiGameView = new JPanel();
        JButton searchGame = new JButton("Buscar");
        JButton buttonEditGame = new JButton("Editar");
        modifiGameView.add(new JLabel("Juego:"));
        modifiGameView.add(new TextField(45));
        modifiGameView.add(searchGame);
        modifiGameView.add(new JTextArea(null, 1, 35));

        modifiGameView.add(buttonEditGame);

        // Vista para Borrar
        JPanel modifyView = new JPanel();
        JButton backButtonModify = new JButton("Buscar");
        JButton deleteGame = new JButton("Borrar");
        modifyView.add(new JLabel("Juego:"));
        modifyView.add(new TextField(45));
        modifyView.add(backButtonModify);
        modifyView.add(new JTextArea(null, 1, 35));
        modifyView.add(deleteGame);

        // Vista para Mostrar
        JPanel showBuyView = new JPanel();
        JButton buttonAdd = new JButton("Agregar");
        JButton buttonBuy = new JButton("Vender");
        showBuyView.add(new JLabel("Nit:"));
        showBuyView.add(new TextField(10));
        showBuyView.add(new JLabel("Nombre:"));
        showBuyView.add(new TextField(30));
        showBuyView.add(new JLabel("Cantidad:"));
        showBuyView.add(new TextField(2));
        showBuyView.add(new JLabel("Adultos"));
        showBuyView.add(new JCheckBox());
        showBuyView.add(new JLabel("Niños"));
        showBuyView.add(new JCheckBox());
        showBuyView.add(new JComboBox<>(new String[] 
        {"Nombre de juego 1",
         "Nombre de juego 2",
         "Nombre de juego 3"
        }));
        showBuyView.add(buttonAdd);
        showBuyView.add(buttonBuy);

        // Vista para Caja
        JPanel showCaja = new JPanel();
        JButton openBox = new JButton("Apertura");
        JButton closeBox = new JButton("Cierre");        
        showCaja.add(new JTextArea("Query ventas", 23, 35));
        showCaja.add(openBox);
        showCaja.add(closeBox);

        contentPanel.add(newGame, "Crear");
        contentPanel.add(modifyView, "Modificar");
        contentPanel.add(showBuyView, "Mostrar");
        contentPanel.add(modifiGameView, "Editar");
        contentPanel.add(showCaja, "Caja");

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Acción del botón "Crear"
        createGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, "Crear");
            }
        });

        // Acción del botón "Modificar"
        eraseGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, "Modificar");
            }
        });

        // Acción del botón "Mostrar"
        buyBoletos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, "Mostrar");
            }
        });
        
        // Acción del botón "Caja"        
        boxMoney.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, "Caja");
            }
        });

        // Acción del botón "Crear" en la vista de Crear
        buttonCreateGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, "Crear");
            }
        });

        // Acción del botón "Buscar" en la vista de Modificar
        backButtonModify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, "Modificar");
            }
        });

        // Acción del botón "Agregar" en la vista de Boletos
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, "Agregar");
            }
        });

        // Acción del botón "Vender" en la vista de Boletos
        buttonBuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, "Vender");
            }
        });
        
        // Acción del botón "Editar juego"        
        editGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, "Editar");
            }
        });           

        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
