
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui{
    private static CardLayout cardLayout;
    private static JPanel contentPanel;
    private ConnectionDB connection = new ConnectionDB();

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
        JButton editGame = new JButton("Editar");
        JButton eraseGame = new JButton("Borrar");
        JButton buyBoletos = new JButton("Boletos");
        JButton boxMoney = new JButton("Caja");

        TextField nombre = new TextField(50);
        TextField precio = new TextField(5);
        JLabel resultado = new JLabel();

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
        JPanel newGameView = new JPanel();
        JButton buttonCreateGame = new JButton("Crear");
        newGameView.add(new JLabel("Juego:"));
        newGameView.add(nombre);
        newGameView.add(new JLabel("Precio:"));
        newGameView.add(precio);
        newGameView.add(buttonCreateGame);
        resultado.setVisible(false);        
        newGameView.add(resultado);
        
        // Vista para Editar juegos
        JPanel editGameView = new JPanel();
        JButton searchGame = new JButton("Buscar");
        JButton buttonEditGame = new JButton("Editar");
        editGameView.add(new JLabel("Juego:"));
        editGameView.add(new TextField(45));
        editGameView.add(searchGame);
        editGameView.add(new JTextArea(null, 1, 35));
        editGameView.add(buttonEditGame);

        // Vista para Borrar
        JPanel eraseGameView = new JPanel();
        JButton backButtonModify = new JButton("Buscar");
        JButton buttonEraseGame = new JButton("Borrar");
        eraseGameView.add(new JLabel("Juego:"));
        eraseGameView.add(new TextField(45));
        eraseGameView.add(backButtonModify);
        eraseGameView.add(new JTextArea(null, 1, 35));
        eraseGameView.add(buttonEraseGame);

        // Vista para Boletos
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
        JPanel showCajaView = new JPanel();
        JButton openBox = new JButton("Apertura");
        JButton closeBox = new JButton("Cierre");        
        showCajaView.add(new JTextArea("Query ventas", 23, 35));
        showCajaView.add(openBox);
        showCajaView.add(closeBox);

        //Paneles de vistas
        contentPanel.add(newGameView,"Nuevo");
        contentPanel.add(editGameView,"Editar");
        contentPanel.add(eraseGameView,"Borrar");
        contentPanel.add(showBuyView,"Boletos");
        contentPanel.add(showCajaView,"Caja");

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Acción del botón "Crear"
        createGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, "Nuevo");
            }
        });

        // Acción del botón "Editar"
        editGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, "Editar");

                resultado.setVisible(false);
            }
        });

        // Acción del botón "Borrar"
        eraseGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, "Borrar");
                
                resultado.setVisible(false);
            }
        });

        // Acción del botón "Vender"
        buyBoletos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, "Boletos");
                
                resultado.setVisible(false);
            }
        });
        
        // Acción del botón "Caja"        
        boxMoney.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, "Caja");
                
                resultado.setVisible(false);
            }
        });

        // Acción del botón "Crear" en la vista de Crear
        buttonCreateGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, "Crear");
                connection.newGame(nombre.getText(),precio.getText());
                resultado.setText("Registro de juego " + nombre.getText() + " exitoso.");
                resultado.setVisible(true);
                nombre.setText(null);
                precio.setText(null);
            }
        });

        // Acción del botón "Editar" en la vista de Modificar
        buttonEditGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, "Modificar");

                resultado.setVisible(false);
            }
        });

        // Acción del botón "Agregar" en la vista de Boletos
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, "Agregar");

                resultado.setVisible(false);
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
