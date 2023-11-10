
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
        TextField nombreEdit = new TextField(50);
        TextField precio = new TextField(5);
        JLabel crear = new JLabel();
        JLabel edit = new JLabel();
        JLabel erase = new JLabel();
        
        crear.setVisible(false);
        edit.setVisible(false);
        erase.setVisible(false);

        buttonPanel.add(createGame);
        buttonPanel.add(editGame);
        buttonPanel.add(eraseGame);
        buttonPanel.add(buyBoletos);
        buttonPanel.add(boxMoney);
        
        mainPanel.add(buttonPanel,BorderLayout.NORTH);

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
        newGameView.add(new JLabel("                                                                                                                                       "));
        newGameView.add(crear);

        // Vista para Editar juegos
        JPanel editGameView = new JPanel();
        JButton searchGame = new JButton("Buscar");
        JButton buttonEditName = new JButton("Editar nombre");
        JButton buttonEditPrice = new JButton("Editar precio");
        TextField nuevoDato = new TextField(50);
        buttonEditName.setVisible(false);
        buttonEditPrice.setVisible(false);
        nuevoDato.setVisible(false);
        editGameView.add(new JLabel("Juego:"));
        editGameView.add(nombreEdit);
        editGameView.add(searchGame);
        editGameView.add(new JLabel("                                                                                                                                       "));
        editGameView.add(edit);
        editGameView.add(new JLabel("                                                                                                                                       "));
        editGameView.add(nuevoDato);
        editGameView.add(buttonEditName);
        editGameView.add(buttonEditPrice);
        
        // Vista para Borrar
        JPanel eraseGameView = new JPanel();
        JButton buttonEraseGame = new JButton("Borrar");
        TextField eraseText = new TextField(50);
        eraseGameView.add(new JLabel("Juego:"));
        eraseGameView.add(eraseText);
        eraseGameView.add(buttonEraseGame);
        eraseGameView.add(new JLabel("                                                                                                                                       "));
        eraseGameView.add(erase);

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

                edit.setVisible(false);
                erase.setVisible(false);
                buttonEditName.setVisible(false);
                buttonEditPrice.setVisible(false);
                nuevoDato.setVisible(false);
            }
        });

        // Acción del botón "Editar"
        editGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, "Editar");

                crear.setVisible(false);
                erase.setVisible(false);   
            }
        });

        // Acción del botón "Borrar"
        eraseGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, "Borrar");
                
                crear.setVisible(false);
                edit.setVisible(false);
                buttonEditName.setVisible(false);
                buttonEditPrice.setVisible(false);
                nuevoDato.setVisible(false);
            }
        });

        // Acción del botón "Boletos"
        buyBoletos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, "Boletos");
                
                crear.setVisible(false);
                edit.setVisible(false);
                erase.setVisible(false);
                buttonEditName.setVisible(false);
                buttonEditPrice.setVisible(false);
                nuevoDato.setVisible(false);
            }
        });
        
        // Acción del botón "Caja"        
        boxMoney.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, "Caja");
                
                crear.setVisible(false);
                edit.setVisible(false);
                erase.setVisible(false);
                buttonEditName.setVisible(false);
                buttonEditPrice.setVisible(false);
                nuevoDato.setVisible(false);  
            }
        });
        // Accion de boton "Buscar"
        searchGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, "Buscar");
                if(connection.getGame(nombreEdit.getText())!="-1"){
                    edit.setText("Juego " + nombreEdit.getText() + " tiene un precio de Q. " +
                    connection.getGame(nombreEdit.getText()));
                    buttonEditName.setVisible(true);
                    buttonEditPrice.setVisible(true);
                    nuevoDato.setVisible(true);                  
                }else{
                    edit.setText("Juego no encontrado");
                }                             
                edit.setVisible(true);
            }
        });        
        // Acción del botón "Editar Nombre"        
        buttonEditName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, "EditarNombre");
                connection.updateGameName(nombreEdit.getText(),nuevoDato.getText());
                edit.setText("Se guardo nuevo nombre");
                edit.setVisible(true);
            }
        });
        // Acción del botón "Editar Precio"        
        buttonEditPrice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, "EditarNombre");
                connection.updateGamePrice(nombreEdit.getText(),nuevoDato.getText());
                edit.setText("Se guardo nuevo precio");
                edit.setVisible(true);
            }
        });

        // Acción del botón "Borrar"        
        buttonEraseGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, "Borrar");
                if(connection.getGame(eraseText.getText())!="-1"){
                    connection.eraseGame(eraseText.getText());
                    erase.setText("Se borro juego");
                }else{
                    erase.setText("No se encontro el juego");
                }
                erase.setVisible(true);
            }
        });

        // Acción del botón "Crear" en la vista de Crear
        buttonCreateGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, "Crear");
                if((nombre.getText().length() > 0) && (precio.getText().length() > 0)){
                    connection.newGame(nombre.getText(),precio.getText());
                    crear.setText("Registro de juego " + nombre.getText() + " exitoso.");
                    nombre.setText(null);
                    precio.setText(null);
                }else{
                    crear.setText("Datos no validos, debe ingresar nombre y precio");
                }
            crear.setVisible(true);

            }
        });

        // Acción del botón "Editar" en la vista de Modificar
        buttonEditName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, "Modificar");

                crear.setVisible(false);
                edit.setVisible(false);
                erase.setVisible(false);
            }
        });

        // Acción del botón "Agregar" en la vista de Boletos
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, "Agregar");

                crear.setVisible(false);
                edit.setVisible(false);
                erase.setVisible(false);
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
