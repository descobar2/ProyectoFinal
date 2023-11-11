
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;

public class Gui{
    private static CardLayout cardLayout;
    private static JPanel contentPanel;
    private boolean vender = false;
    private ConnectionDB connection = new ConnectionDB();


    public void createAndShowGUI() throws SQLException {
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
        JButton buttonAdd = new JButton("Vender");
        //JButton buttonBuy = new JButton("Vender");
        TextField textNit = new TextField(10);
        TextField textNombre = new TextField(30);
        TextField textCant = new TextField(1);
        TextField textDir = new TextField(43);
        JCheckBox adultos = new JCheckBox("Adultos");
        JCheckBox ninos = new JCheckBox("Niños");
        JLabel serie = new JLabel();
        
        JComboBox<String> listJuego = new JComboBox<>();
        listJuego.removeAllItems();
        for(int i = 0; i < connection.getJuegos().size(); i++){
            String dato = connection.getJuegos().get(i).getNombre();
            listJuego.addItem(dato);
        }

        showBuyView.add(new JLabel("Nit:"));
        showBuyView.add(textNit);
        showBuyView.add(new JLabel("Nombre:"));
        showBuyView.add(textNombre);
        showBuyView.add(new JLabel("    Dirección:"));
        showBuyView.add(textDir);
        showBuyView.add(new JLabel("Cantidad:"));
        showBuyView.add(textCant);
        showBuyView.add(adultos);
        showBuyView.add(ninos);
        showBuyView.add(listJuego);
        showBuyView.add(buttonAdd);
        //showBuyView.add(buttonBuy);
        showBuyView.add(new JLabel("                                                                                                                                       "));
        serie.setVisible(false);
        showBuyView.add(serie);

        // Vista para Caja
        JPanel showCajaView = new JPanel();
        JButton openBox = new JButton("Consultar");
        JButton closeBox = new JButton("Cierre");
        JTextArea textCaja = new JTextArea("Consultar ventas", 23,35);
        closeBox.setVisible(false);        
        showCajaView.add(textCaja);
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
                String sSerie=null;
                cardLayout.show(contentPanel, "Vender");
                if((textNit.getText().length() > 0) && (textNombre.getText().length() > 0)
                    && (textCant.getText().length() > 0) && (adultos.isSelected() || ninos.isSelected())){
                    if(vender == false){
                        sSerie = String.valueOf(1+connection.getUltimoID("Serie","Boleto"));
                        serie.setText("Serie: A00"+ sSerie);
                        vender = true;
                    }
                    if(connection.getNit(textNit.getText()) != "-1"){
                        connection.setBoleto(sSerie,adultos.isSelected(),ninos.isSelected(),textCant.getText(),String.valueOf(listJuego.getSelectedItem()),textNit.getText());
                    }else{
                        connection.setCliente(textNit.getText(),textNombre.getText(),textDir.getText());
                        connection.setBoleto(sSerie,adultos.isSelected(),ninos.isSelected(),textCant.getText(),String.valueOf(listJuego.getSelectedItem()),textNit.getText());
                    }
                    adultos.setSelected(false);
                    ninos.setSelected(false);
                    textCant.setText(null);
                }else{
                    serie.setText("Debe ingresar datos para vender");
                    vender = false;                   
                }
            serie.setVisible(true);
            }
        });

        // Acción del botón "Vender" en la vista de Boletos
        openBox.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, "Vender");
                String dato="";
                try {
                    for(int i = 0; i < connection.getBoleto().size(); i++){
                        int serie = connection.getBoleto().get(i).getSerie();
                        Date fecha = connection.getBoleto().get(i).getFecha();
                        int cantidad = connection.getBoleto().get(i).getCantidad();
                        String nit = connection.getBoleto().get(i).getNitCliente();
                        
                        dato = dato + ("Serie: A00"+serie + " Nit: "+ nit + " Cantidad: "+cantidad+" Fecha: "+fecha+"\n");
                    }
                    textCaja.setText(dato);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

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
