import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * BattleShipGUI.java
 * <p>
 * Runs the GUI program for the BattleShip game
 *
 * @author Andrew Peterson
 * @verson 11.13.15
 */
public class BattleShipGUI {


    private static ArrayList<JButton> player1Buttons = new ArrayList<>();
    private static ArrayList<JButton> player2Buttons = new ArrayList<>();


    private JFrame player1HitBoard;
    private JFrame player2HitBoard;


    private String[] columnLetters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    private String[] direction = new String[]{"DOWN", "UP", "LEFT", "RIGHT"};
    private int[] rowNumbers = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    private ArrayList<String> buttonNames = new ArrayList<>();

    private JPanel player1ButtonGrid = new JPanel(new GridLayout(10, 10));
    private JPanel player2ButtonGrid = new JPanel(new GridLayout(10, 10));

    private JFrame shipSetupFrame = new JFrame("Expand to see more");
    private JPanel shipSetupButtonGrid = new JPanel(new GridLayout(10, 10));
    private JFrame player1RowColSetupFrame = new JFrame();
    private JFrame player2RowColSetupFrame = new JFrame();

    private JTextField cheat = new JTextField();

    private JFrame startUpFrame;

    private Player1 p1 = null;
    private Player1 p2 = null;
    private Board player1;
    private Board player2;

    private int whichPlayer = 0;

    /**
     * Create the application.
     */
    public BattleShipGUI() {
        initialize();

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BattleShipGUI window = new BattleShipGUI();
                    window.startUpFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initialize() {

        startUpFrame = new JFrame();
        startUpFrame.getContentPane().setBackground(Color.WHITE);
        startUpFrame.setBounds(100, 100, 500, 400);
        startUpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
        Point newLocation = new Point(middle.x - (startUpFrame.getWidth() / 2),
                middle.y - (startUpFrame.getHeight() / 2));

        startUpFrame.setLocation(newLocation);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        startUpFrame.getContentPane().add(panel, BorderLayout.NORTH);
        panel.setLayout(new GridLayout(3, 6, 100, 6));

        Component verticalStrut = Box.createVerticalStrut(20);
        panel.add(verticalStrut);

        JLabel welcomeTitle = new JLabel("Welcome to BattleShip!");
        welcomeTitle.setForeground(new Color(204, 0, 0));
        welcomeTitle.setFont(new Font("Stencil", Font.PLAIN, 22));
        welcomeTitle.setVerticalAlignment(SwingConstants.BOTTOM);
        welcomeTitle.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(welcomeTitle);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.WHITE);
        FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
        flowLayout.setHgap(20);
        startUpFrame.getContentPane().add(panel_1, BorderLayout.WEST);

        JPanel panel_2 = new JPanel();
        panel_2.setBackground(Color.WHITE);
        FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
        flowLayout_1.setHgap(20);
        startUpFrame.getContentPane().add(panel_2, BorderLayout.EAST);

        JPanel panel_4 = new JPanel();
        panel_4.setBackground(Color.WHITE);
        startUpFrame.getContentPane().add(panel_4, BorderLayout.CENTER);

        startUpFrame.setUndecorated(true);
        startUpFrame.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        JButton playButton = new JButton("Play!");
        playButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                play();
                startUpFrame.setVisible(false);
                startUpFrame.dispose();
            }
        });

        JButton loadButton = new JButton("Load");
        loadButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //TODO write a method that writes all of the valuable data to a text file that can be read again later
            }
        });

        JButton quitButton = new JButton("Quit");
        quitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int option;
                option = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to quit?", "Really Closing?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                if (option == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }

            }
        });
        JButton Rules = new JButton("Rules");
        Rules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rules();
                startUpFrame.setVisible(false);
            }
        });

        JLabel battleshipPicture = new JLabel();
        battleshipPicture.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon silhouette = new ImageIcon(getClass().getResource
                ("Royal_Navy_Invincible_silhouette.png"));
        battleshipPicture.setIcon(silhouette);


        GroupLayout gl_panel_4 = new GroupLayout(panel_4);
        gl_panel_4.setHorizontalGroup(
                gl_panel_4.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gl_panel_4.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(battleshipPicture, GroupLayout.PREFERRED_SIZE, 396, Short.MAX_VALUE)
                                .addContainerGap())
                        .addGroup(gl_panel_4.createSequentialGroup()
                                .addGap(84)
                                .addGroup(gl_panel_4.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(gl_panel_4.createSequentialGroup()
                                                .addGroup(gl_panel_4.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(quitButton, GroupLayout.Alignment.TRAILING,
                                                                GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                                                        .addComponent(loadButton, GroupLayout.Alignment.TRAILING,
                                                                GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))
                                                .addGap(99))
                                        .addGroup(gl_panel_4.createSequentialGroup()
                                                .addGroup(gl_panel_4.createParallelGroup(GroupLayout.Alignment
                                                        .TRAILING, false)
                                                        .addComponent(playButton, GroupLayout.Alignment.LEADING,
                                                                GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                                                                Short.MAX_VALUE)
                                                        .addComponent(Rules, GroupLayout.Alignment.LEADING,
                                                                GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))
                                                .addContainerGap())))
        );
        gl_panel_4.setVerticalGroup(
                gl_panel_4.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gl_panel_4.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(battleshipPicture, GroupLayout.PREFERRED_SIZE, 77, Short.MAX_VALUE)
                                .addGap(24)
                                .addComponent(playButton, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Rules, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(loadButton, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(quitButton, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                                .addGap(53))
        );
        gl_panel_4.setAutoCreateGaps(true);
        gl_panel_4.setAutoCreateContainerGaps(true);
        panel_4.setLayout(gl_panel_4);

    }

    public void play() {

        JPanel devPanel = new JPanel();
        devPanel.setLayout(new BorderLayout());
        JPanel panel1 = new JPanel();
        JFrame devMenu = new JFrame("Dev Menu");
        JTextField devChoice;
        devMenu.getContentPane().add(panel1, BorderLayout.SOUTH);

        devChoice = new JTextField();
        panel1.add(devChoice);
        devChoice.setColumns(10);

        JPanel panel_1 = new JPanel();
        devMenu.getContentPane().add(panel_1, BorderLayout.CENTER);
        panel_1.setLayout(null);

        JLabel lblDevMenu = new JLabel("Dev Menu:");
        lblDevMenu.setBounds(12, 13, 61, 16);
        panel_1.add(lblDevMenu);

        JLabel lblNewLabel = new JLabel("1...Get Enemy Board");
        lblNewLabel.setBounds(12, 42, 158, 16);
        lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
        panel_1.add(lblNewLabel);

        JLabel lblgetEnemyHit = new JLabel("2...Get Enemy Hit Board");
        lblgetEnemyHit.setHorizontalAlignment(SwingConstants.LEFT);
        lblgetEnemyHit.setBounds(12, 60, 158, 16);
        panel_1.add(lblgetEnemyHit);

        JLabel lblgetOwnBoard = new JLabel("4...Get Own Hit Board");
        lblgetOwnBoard.setHorizontalAlignment(SwingConstants.LEFT);
        lblgetOwnBoard.setBounds(12, 96, 158, 16);
        panel_1.add(lblgetOwnBoard);

        JLabel label = new JLabel("3...Get Own Board");
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setBounds(12, 78, 158, 16);
        panel_1.add(label);

        JLabel lblsubInHuman = new JLabel("5...Sub in human player two");
        lblsubInHuman.setHorizontalAlignment(SwingConstants.LEFT);
        lblsubInHuman.setBounds(12, 113, 185, 16);
        panel_1.add(lblsubInHuman);

        JLabel lblsubInComputer = new JLabel("6...Sub in computer player two");
        lblsubInComputer.setHorizontalAlignment(SwingConstants.LEFT);
        lblsubInComputer.setBounds(12, 130, 185, 16);
        panel_1.add(lblsubInComputer);

        JLabel lblleaveDevMenu = new JLabel("7...Leave Dev Menu");
        lblleaveDevMenu.setHorizontalAlignment(SwingConstants.LEFT);
        lblleaveDevMenu.setBounds(12, 149, 185, 16);
        panel_1.add(lblleaveDevMenu);


        devChoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (devChoice.getText().equals("1")) {
                    //TODO Get enemy Board
                } else if (devChoice.getText().equals("2")) {
                    //TODO Get enemy hit board
                } else if (devChoice.getText().equals("3")) {
                    //TODO Get Own Board
                } else if (devChoice.getText().equals("4")) {
                    //TODO Get Own hit board
                } else if (devChoice.getText().equals("5")) {
                    //TODO Sub-in human player 2
                } else if (devChoice.getText().equals("6")) {
                    //TODO Sub-in computer player 2
                } else if (devChoice.getText().equals("7")) {
                    devMenu.dispose();
                }
            }
        });

        cheat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cheat.getText().equals("~")) {

                    devMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    devMenu.setSize(270, 300);
                    devMenu.setVisible(true);


                }
            }
        });

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                buttonNames.add("" + columnLetters[j] + rowNumbers[i]);
            }
        }
        for (int i = 0; i < 100; i++) {
            JButton button = new JButton("" + buttonNames.get(i));
            button.setActionCommand(buttonNames.get(i));
            button.addActionListener(new Listen());
        }
        for (int i = 0; i < 100; i++) {
            JButton button = new JButton("" + buttonNames.get(i));
            button.setActionCommand(buttonNames.get(i));
            button.addActionListener(new Listen());
            player2ButtonGrid.add(button);
            player2Buttons.add(button);
        }
        for (int i = 0; i < 100; i++) {
            JButton button = new JButton("" + buttonNames.get(i));
            button.setActionCommand(buttonNames.get(i));
            button.addActionListener(new Listen());
            player1ButtonGrid.add(button);
            player1Buttons.add(button);
        }
        for (int i = 0; i < 100; i++) {
            JButton button = new JButton("" + buttonNames.get(i));
            button.setBackground(Color.WHITE);
            button.setEnabled(false);
            shipSetupButtonGrid.add(button);
        }


        /**
         * Initialize the contents of the frame.
         */
        {

            String p1Name = "Player 1";
            p1 = new Player1(p1Name);
            player1 = new Board(p1);

            String p2Name = "Player 2";
            p2 = new Player1(p2Name);
            player2 = new Board(p2);

            player1HitBoard = new JFrame("" + p1.getName() + " Hit Board");
            player2HitBoard = new JFrame("" + p2.getName() + " Hit Board");
            player1HitBoard.setUndecorated(true);
            player1HitBoard.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
            player2HitBoard.setUndecorated(true);
            player2HitBoard.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));

            player1HitBoard.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    int option;
                    option = JOptionPane.showConfirmDialog(null,
                            "If you quit, Player 2 wins! Are you sure you want to quit?", "Really Closing?",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);

                    if (option == JOptionPane.YES_OPTION) {
                        player1Surrenders();
                    }

                }
            });

            player2HitBoard.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    int option;
                    option = JOptionPane.showConfirmDialog(null,
                            "If you quit, Player 1 wins! Are you sure you want to quit?", "Really Closing?",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);

                    if (option == JOptionPane.YES_OPTION) {
                        player2Surrenders();
                    }

                }

            });


            whichPlayer = 0;
            setUpBoardOne();


        }
    }

    class Listen implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < buttonNames.size(); i++) {
                if (e.getActionCommand().equals(buttonNames.get(i))) {


                    if (whichPlayer == 0) {
                        String col = buttonNames.get(i).substring(0, 1);
                        int row = Integer.parseInt(buttonNames.get(i).substring(1));
                        System.out.println("" + col + (row - 1));
                        int strike = player2.strike(row - 1, col);
                        if (p2.getNumbBoats() == 0) {
                            player1HitBoard.setVisible(false);
                            player2HitBoard.setVisible(false);
                            player2HitBoard.dispose();
                            player1HitBoard.dispose();
                            player1Win();
                            return;
                        }
                        if (strike == 0) {
                            player1Buttons.get(i).setBackground(Color.GREEN);
                            player1Buttons.get(i).setEnabled(false);
                            player1HitBoard.invalidate();
                            player1HitBoard.validate();
                            player1HitBoard.repaint();

                        } else if (strike == -1) {
                            player1Buttons.get(i).setBackground(Color.RED);
                            player1Buttons.get(i).setEnabled(false);
                            player1HitBoard.invalidate();
                            player1HitBoard.validate();
                            player1HitBoard.repaint();

                        } else if (strike == 1) {
//                            player2HitBoard.setVisible(false);
//                            player1HitBoard.setEnabled(false);
//                            player2HitBoard.setEnabled(false);
                            player2SunkShip(Board.b);
                            player1Buttons.get(i).setBackground(Color.GREEN);
                            player1Buttons.get(i).setEnabled(false);
                            player1HitBoard.invalidate();
                            player1HitBoard.validate();
                            player1HitBoard.repaint();

                        }

                        whichPlayer = 1;
                        player1Buttons.forEach((button) -> button.setEnabled(false));
                    } else {

                        String col = buttonNames.get(i).substring(0, 1);
                        int row = Integer.parseInt(buttonNames.get(i).substring(1));
                        System.out.println("" + col + (row - 1));
                        int strike = player1.strike(row - 1, col);
                        if (p1.getNumbBoats() == 0) {
                            player1HitBoard.setVisible(false);
                            player2HitBoard.setVisible(false);
                            player2HitBoard.dispose();
                            player1HitBoard.dispose();
                            player2Win();
                            return;
                        }
                        if (strike == 0) {
                            player2Buttons.get(i).setBackground(Color.GREEN);
                            player2Buttons.get(i).setEnabled(false);
                            player2HitBoard.invalidate();
                            player2HitBoard.validate();
                            player2HitBoard.repaint();

                        } else if (strike == -1) {
                            player2Buttons.get(i).setBackground(Color.RED);
                            player2Buttons.get(i).setEnabled(false);
                            player2HitBoard.invalidate();
                            player2HitBoard.validate();
                            player2HitBoard.repaint();
                        } else if (strike == 1) {
//                            player2HitBoard.setVisible(false);
//                            player1HitBoard.setEnabled(false);
//                            player2HitBoard.setEnabled(false);
                            player1SunkShip(Board.b);
//                            player2HitBoard.setAlwaysOnTop(false);
//                            player1HitBoard.setAlwaysOnTop(false);
                            player2Buttons.get(i).setBackground(Color.GREEN);
                            player2Buttons.get(i).setEnabled(false);
                            player2HitBoard.invalidate();
                            player2HitBoard.validate();
                            player2HitBoard.repaint();

                        }

                        whichPlayer = 0;
                        player2Buttons.forEach((button) -> button.setEnabled(false));
                    }


                    board();

                }
            }

        }
    }

    public void setUpBoardOne() {
        JFrame testBoats = new JFrame();
        testBoats.setSize(500, 500);
        JPanel grid = new JPanel(new GridLayout(10, 10));
        for (int i = 0; i < 100; i++) {
            grid.add(new JButton(""));
        }


        player1RowColSetupFrame.setResizable(false);
        player1RowColSetupFrame.setUndecorated(true);
        player1RowColSetupFrame.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        player1RowColSetupFrame.setTitle("Player 1 Ship Board");
        player1RowColSetupFrame.setSize(460, 380);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
        Point newLocation = new Point(middle.x - (player1RowColSetupFrame.getWidth() / 2),
                middle.y - (player1RowColSetupFrame.getHeight() / 2));

        player1RowColSetupFrame.setLocation(newLocation);
        player1RowColSetupFrame.getContentPane();

        JPanel panel = new JPanel();
        player1RowColSetupFrame.getContentPane().add(panel, BorderLayout.SOUTH);


        JPanel panel_1 = new JPanel();
        player1RowColSetupFrame.getContentPane().add(panel_1, BorderLayout.CENTER);

        JLabel lblNewLabel = new JLabel("PLAYER 1...Enter locations for each ship:");

        JLabel lblNewLabel_1 = new JLabel("*Ships may not touch each other.");

        JLabel lblAircraftCarriersize = new JLabel("Aircraft Carrier (size 5):");

        JComboBox acCol = new JComboBox();
        acCol.setModel(new DefaultComboBoxModel(new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"}));
        acCol.setSelectedIndex(0);
        JComboBox acRow = new JComboBox();
        acRow.setModel(new DefaultComboBoxModel(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
        acRow.setSelectedIndex(0);
        JComboBox acDir = new JComboBox();
        acDir.setModel(new DefaultComboBoxModel(new String[]{"DOWN", "UP", "LEFT", "RIGHT"}));
        acDir.setSelectedIndex(0);
        JLabel lblBattleshipsize = new JLabel("Battleship (Size 4):");

        JComboBox bsRow = new JComboBox();
        bsRow.setModel(new DefaultComboBoxModel(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
        bsRow.setSelectedIndex(0);
        JComboBox bsCol = new JComboBox();
        bsCol.setModel(new DefaultComboBoxModel(new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"}));

        bsCol.setSelectedIndex(0);
        JComboBox bsDir = new JComboBox();
        bsDir.setModel(new DefaultComboBoxModel(new String[]{"DOWN", "UP", "LEFT", "RIGHT"}));
        bsDir.setSelectedIndex(0);
        JLabel lblSubmarinesize = new JLabel("Submarine (Size 3):");

        JComboBox sbRow = new JComboBox();
        sbRow.setModel(new DefaultComboBoxModel(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
        sbRow.setSelectedIndex(0);
        JComboBox sbCol = new JComboBox();
        sbCol.setModel(new DefaultComboBoxModel(new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"}));
        sbCol.setSelectedIndex(0);
        JComboBox sbDir = new JComboBox();
        sbDir.setModel(new DefaultComboBoxModel(new String[]{"DOWN", "UP", "LEFT", "RIGHT"}));
        sbDir.setSelectedIndex(0);

        JLabel lblCruisersize = new JLabel("Cruiser (Size 3):");

        JComboBox crRow = new JComboBox();
        crRow.setModel(new DefaultComboBoxModel(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
        crRow.setSelectedIndex(0);
        JComboBox crCol = new JComboBox();
        crCol.setModel(new DefaultComboBoxModel(new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"}));
        crCol.setSelectedIndex(0);
        JComboBox crDir = new JComboBox();
        crDir.setModel(new DefaultComboBoxModel(new String[]{"DOWN", "UP", "LEFT", "RIGHT"}));
        crDir.setSelectedIndex(0);
        JLabel lblPatrolBoatsize = new JLabel("Patrol Boat (Size 2):");

        JComboBox pbRow = new JComboBox();
        pbRow.setModel(new DefaultComboBoxModel(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
        pbRow.setSelectedIndex(0);
        JComboBox pbCol = new JComboBox();
        pbCol.setModel(new DefaultComboBoxModel(new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"}));
        pbCol.setSelectedIndex(0);
        JComboBox pbDir = new JComboBox();
        pbDir.setModel(new DefaultComboBoxModel(new String[]{"DOWN", "UP", "LEFT", "RIGHT"}));
        pbDir.setSelectedIndex(0);

        JButton ACSet = new JButton("Set");
        ACSet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = acRow.getSelectedIndex();
                String col = columnLetters[acCol.getSelectedIndex()];
                String dir = direction[acDir.getSelectedIndex()];


                boolean setBoat = player1.setBoat(5, row, col, dir, "AIRCRAFT_CARRIER");

                if (!setBoat) {
                    JOptionPane.showMessageDialog(null, "You Aircraft Carrier cannot be here", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else if (setBoat) {

                    //TODO Set the frame to open no matter which ship is put first
                    shipSetupFrame.invalidate();
                    shipSetupFrame.validate();
                    shipSetupFrame.repaint();
                    showShipPlacement(player1);
                    shipSetupFrame.setVisible(true);
                    ACSet.setEnabled(false);
                }

            }
        });

        JButton BSSet = new JButton("Set");
        BSSet.setPreferredSize(ACSet.getPreferredSize());
        BSSet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = bsRow.getSelectedIndex();
                String col = columnLetters[bsCol.getSelectedIndex()];
                String dir = direction[bsDir.getSelectedIndex()];


                boolean setBoat = player1.setBoat(4, row, col, dir, "BATTLESHIP");

                if (!setBoat) {
                    JOptionPane.showMessageDialog(null, "You Battleship cannot be here", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else if (setBoat) {
                    shipSetupFrame.invalidate();
                    shipSetupFrame.validate();
                    shipSetupFrame.repaint();
                    showShipPlacement(player1);
                    shipSetupFrame.setVisible(true);
                    BSSet.setEnabled(false);
                }
            }

        });

        JButton SBSet = new JButton("Set");
        SBSet.setPreferredSize(ACSet.getPreferredSize());
        SBSet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = sbRow.getSelectedIndex();
                String col = columnLetters[sbCol.getSelectedIndex()];
                String dir = direction[sbDir.getSelectedIndex()];


                boolean setBoat = player1.setBoat(3, row, col, dir, "SUBMARINE");

                if (!setBoat) {
                    JOptionPane.showMessageDialog(null, "You Submarine cannot be here", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else if (setBoat) {
                    shipSetupFrame.invalidate();
                    shipSetupFrame.validate();
                    shipSetupFrame.repaint();
                    showShipPlacement(player1);
                    shipSetupFrame.setVisible(true);
                    SBSet.setEnabled(false);
                }
            }

        });

        JButton CRSet = new JButton("Set");
        CRSet.setPreferredSize(ACSet.getPreferredSize());
        CRSet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = crRow.getSelectedIndex();
                String col = columnLetters[crCol.getSelectedIndex()];
                String dir = direction[crDir.getSelectedIndex()];


                boolean setBoat = player1.setBoat(3, row, col, dir, "CRUISER");

                if (!setBoat) {
                    JOptionPane.showMessageDialog(null, "You Cruiser cannot be here", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else if (setBoat) {
                    shipSetupFrame.invalidate();
                    shipSetupFrame.validate();
                    shipSetupFrame.repaint();
                    showShipPlacement(player1);
                    shipSetupFrame.setVisible(true);
                    CRSet.setEnabled(false);
                }
            }

        });

        JButton PBSet = new JButton("Set");
        PBSet.setPreferredSize(ACSet.getPreferredSize());
        PBSet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = pbRow.getSelectedIndex();
                String col = columnLetters[pbCol.getSelectedIndex()];
                String dir = direction[pbDir.getSelectedIndex()];


                boolean setBoat = player1.setBoat(2, row, col, dir, "PATROL_BOAT");

                if (!setBoat) {
                    JOptionPane.showMessageDialog(null, "You Patrol Boat cannot be here", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else if (setBoat) {
                    shipSetupFrame.invalidate();
                    shipSetupFrame.validate();
                    shipSetupFrame.repaint();
                    showShipPlacement(player1);
                    shipSetupFrame.setVisible(true);
                    PBSet.setEnabled(false);
                }

            }
        });


        GroupLayout gl_panel_1 = new GroupLayout(panel_1);
        gl_panel_1.setHorizontalGroup(
                gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gl_panel_1.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(lblNewLabel)
                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                .addGap(10)
                                                .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblNewLabel_1)
                                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                                .addComponent(lblAircraftCarriersize)
                                                                .addGap(18)
                                                                .addComponent(acRow, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                                .addComponent(lblBattleshipsize, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18)
                                                                .addComponent(bsRow, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                                .addComponent(lblSubmarinesize, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18)
                                                                .addComponent(sbRow, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                                .addComponent(lblCruisersize, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18)
                                                                .addComponent(crRow, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                                .addComponent(lblPatrolBoatsize, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18)
                                                                .addComponent(pbRow, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                                .addGap(13)
                                                                .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(acCol, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(bsCol, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                                                                .addGap(18)
                                                                .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                                                .addComponent(bsDir, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18)
                                                                                .addComponent(BSSet, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                                                .addComponent(acDir, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18)
                                                                                .addComponent(ACSet))))
                                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                                                .addComponent(crCol, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18)
                                                                                .addComponent(crDir, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18)
                                                                                .addComponent(CRSet, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                                                .addComponent(sbCol, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18)
                                                                                .addComponent(sbDir, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18)
                                                                                .addComponent(SBSet, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                                                .addComponent(pbCol, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18)
                                                                                .addComponent(pbDir, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18)
                                                                                .addComponent(PBSet, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)))))))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        gl_panel_1.setVerticalGroup(
                gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gl_panel_1.createSequentialGroup()
                                .addGap(23)
                                .addComponent(lblNewLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNewLabel_1)
                                .addGap(18)
                                .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblAircraftCarriersize)
                                        .addComponent(acCol, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(acRow, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(acDir, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ACSet))
                                .addGap(18)
                                .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblBattleshipsize)
                                        .addComponent(bsRow, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bsCol, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bsDir, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(BSSet))
                                .addGap(18)
                                .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblSubmarinesize)
                                        .addComponent(sbRow, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(sbCol, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(sbDir, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(SBSet))
                                .addGap(18)
                                .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblCruisersize)
                                        .addComponent(crRow, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(crCol, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(crDir, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(CRSet))
                                .addGap(18)
                                .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblPatrolBoatsize)
                                        .addComponent(pbRow, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(pbCol, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(pbDir, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(PBSet))
                                .addContainerGap(45, Short.MAX_VALUE))
        );
        panel_1.setLayout(gl_panel_1);
        JButton btnOkay = new JButton("Okay!");
        btnOkay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ACSet.isEnabled() || SBSet.isEnabled() || BSSet.isEnabled() || CRSet.isEnabled() || PBSet
                        .isEnabled()) {
                    JOptionPane.showMessageDialog(null, "You must set a location for each boat!", "Error: Set " +
                            "Pieces", JOptionPane.ERROR_MESSAGE);
                } else {
                    player1RowColSetupFrame.dispose();

                    whichPlayer = 1;
                    shipSetupFrame.dispose();
                    shipSetupButtonGrid.removeAll();
                    for (int i = 0; i < 100; i++) {
                        JButton button = new JButton("");
                        button.setBackground(Color.WHITE);
                        button.setEnabled(false);
                        shipSetupButtonGrid.add(button);
                    }
                    setUpBoardTwo();
                }
            }
        });
        JButton btnReset = new JButton("Reset");
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player1 = null;
                Player1 newP1 = new Player1("Player 1");
                player1 = new Board(newP1);
                player1RowColSetupFrame.dispose();
                shipSetupFrame.dispose();
                ACSet.setEnabled(true);
                SBSet.setEnabled(true);
                BSSet.setEnabled(true);
                CRSet.setEnabled(true);
                PBSet.setEnabled(true);
                for (int i = 0; i < 100; i++) {
                    shipSetupButtonGrid.getComponent(i).setBackground(Color.WHITE);
                }
                setUpBoardOne();
                shipSetupFrame.setVisible(true);
            }
        });
        panel.add(btnOkay);
        panel.add(btnReset);
        player1RowColSetupFrame.setVisible(true);

    }

    public void setUpBoardTwo() {

        JFrame testBoats = new JFrame();
        testBoats.setSize(500, 500);
        JPanel grid = new JPanel(new GridLayout(10, 10));
        for (int i = 0; i < 100; i++) {
            grid.add(new JButton(""));
        }


        player2RowColSetupFrame.setResizable(false);
        player2RowColSetupFrame.setUndecorated(true);
        player2RowColSetupFrame.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        player2RowColSetupFrame.setTitle("Player 2 Ship Board");
        player2RowColSetupFrame.setSize(460, 380);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
        Point newLocation = new Point(middle.x - (player2RowColSetupFrame.getWidth() / 2),
                middle.y - (player2RowColSetupFrame.getHeight() / 2));

        player2RowColSetupFrame.setLocation(newLocation);

        player2RowColSetupFrame.getContentPane();

        JPanel panel = new JPanel();
        player2RowColSetupFrame.getContentPane().add(panel, BorderLayout.SOUTH);


        JPanel panel_1 = new JPanel();
        player2RowColSetupFrame.getContentPane().add(panel_1, BorderLayout.CENTER);

        JLabel lblNewLabel = new JLabel("PLAYER 2...Enter locations for each ship:");

        JLabel lblNewLabel_1 = new JLabel("*Ships may not touch each other");

        JLabel lblAircraftCarriersize = new JLabel("Aircraft Carrier (size 5):");

        JComboBox acCol = new JComboBox();
        acCol.setModel(new DefaultComboBoxModel(new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"}));
        acCol.setSelectedIndex(0);
        JComboBox acRow = new JComboBox();
        acRow.setModel(new DefaultComboBoxModel(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
        acRow.setSelectedIndex(0);
        JComboBox acDir = new JComboBox();
        acDir.setModel(new DefaultComboBoxModel(new String[]{"DOWN", "UP", "LEFT", "RIGHT"}));
        acDir.setSelectedIndex(0);
        JLabel lblBattleshipsize = new JLabel("Battleship (Size 4):");

        JComboBox bsRow = new JComboBox();
        bsRow.setModel(new DefaultComboBoxModel(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
        bsRow.setSelectedIndex(0);
        JComboBox bsCol = new JComboBox();
        bsCol.setModel(new DefaultComboBoxModel(new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"}));

        bsCol.setSelectedIndex(0);
        JComboBox bsDir = new JComboBox();
        bsDir.setModel(new DefaultComboBoxModel(new String[]{"DOWN", "UP", "LEFT", "RIGHT"}));
        bsDir.setSelectedIndex(0);
        JLabel lblSubmarinesize = new JLabel("Submarine (Size 3):");

        JComboBox sbRow = new JComboBox();
        sbRow.setModel(new DefaultComboBoxModel(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
        sbRow.setSelectedIndex(0);
        JComboBox sbCol = new JComboBox();
        sbCol.setModel(new DefaultComboBoxModel(new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"}));
        sbCol.setSelectedIndex(0);
        JComboBox sbDir = new JComboBox();
        sbDir.setModel(new DefaultComboBoxModel(new String[]{"DOWN", "UP", "LEFT", "RIGHT"}));
        sbDir.setSelectedIndex(0);

        JLabel lblCruisersize = new JLabel("Cruiser (Size 3):");

        JComboBox crRow = new JComboBox();
        crRow.setModel(new DefaultComboBoxModel(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
        crRow.setSelectedIndex(0);
        JComboBox crCol = new JComboBox();
        crCol.setModel(new DefaultComboBoxModel(new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"}));
        crCol.setSelectedIndex(0);
        JComboBox crDir = new JComboBox();
        crDir.setModel(new DefaultComboBoxModel(new String[]{"DOWN", "UP", "LEFT", "RIGHT"}));
        crDir.setSelectedIndex(0);
        JLabel lblPatrolBoatsize = new JLabel("Patrol Boat (Size 2):");

        JComboBox pbRow = new JComboBox();
        pbRow.setModel(new DefaultComboBoxModel(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
        pbRow.setSelectedIndex(0);
        JComboBox pbCol = new JComboBox();
        pbCol.setModel(new DefaultComboBoxModel(new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"}));
        pbCol.setSelectedIndex(0);
        JComboBox pbDir = new JComboBox();
        pbDir.setModel(new DefaultComboBoxModel(new String[]{"DOWN", "UP", "LEFT", "RIGHT"}));
        pbDir.setSelectedIndex(0);
        JButton ACSet = new JButton("Set");
        ACSet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = acRow.getSelectedIndex();
                String col = columnLetters[acCol.getSelectedIndex()];
                String dir = direction[acDir.getSelectedIndex()];

                boolean setBoat = player2.setBoat(5, row, col, dir, "AIRCRAFT_CARRIER");
                if (!setBoat) {
                    JOptionPane.showMessageDialog(null, "You Aircraft Carrier cannot be here", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else if (setBoat) {

                    showShipPlacement(player2);
                    shipSetupFrame.invalidate();
                    shipSetupFrame.validate();
                    shipSetupFrame.repaint();
                    shipSetupFrame.setVisible(true);
                    ACSet.setEnabled(false);
                }
            }
        });

        JButton BSSet = new JButton("Set");
        BSSet.setPreferredSize(ACSet.getPreferredSize());
        BSSet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = bsRow.getSelectedIndex();
                String col = columnLetters[bsCol.getSelectedIndex()];
                String dir = direction[bsDir.getSelectedIndex()];


                boolean setBoat = player2.setBoat(4, row, col, dir, "BATTLESHIP");

                if (!setBoat) {
                    JOptionPane.showMessageDialog(null, "You Battleship cannot be here", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else if (setBoat) {

                    shipSetupFrame.invalidate();
                    shipSetupFrame.validate();
                    shipSetupFrame.repaint();
                    showShipPlacement(player2);
                    BSSet.setEnabled(false);
                }
            }

        });

        JButton SBSet = new JButton("Set");
        SBSet.setPreferredSize(ACSet.getPreferredSize());
        SBSet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = sbRow.getSelectedIndex();
                String col = columnLetters[sbCol.getSelectedIndex()];
                String dir = direction[sbDir.getSelectedIndex()];


                boolean setBoat = player2.setBoat(3, row, col, dir, "SUBMARINE");

                if (!setBoat) {
                    JOptionPane.showMessageDialog(null, "You Submarine cannot be here", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else if (setBoat) {

                    shipSetupFrame.invalidate();
                    shipSetupFrame.validate();
                    shipSetupFrame.repaint();
                    showShipPlacement(player2);
                    SBSet.setEnabled(false);
                }
            }

        });

        JButton CRSet = new JButton("Set");
        CRSet.setPreferredSize(ACSet.getPreferredSize());
        CRSet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = crRow.getSelectedIndex();
                String col = columnLetters[crCol.getSelectedIndex()];
                String dir = direction[crDir.getSelectedIndex()];


                boolean setBoat = player2.setBoat(3, row, col, dir, "CRUISER");

                if (!setBoat) {
                    JOptionPane.showMessageDialog(null, "You Cruiser cannot be here", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else if (setBoat) {

                    shipSetupFrame.invalidate();
                    shipSetupFrame.validate();
                    shipSetupFrame.repaint();
                    showShipPlacement(player2);
                    CRSet.setEnabled(false);
                }

            }

        });

        JButton PBSet = new JButton("Set");
        PBSet.setPreferredSize(ACSet.getPreferredSize());
        PBSet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = pbRow.getSelectedIndex();
                String col = columnLetters[pbCol.getSelectedIndex()];
                String dir = direction[pbDir.getSelectedIndex()];

                boolean setBoat = player2.setBoat(2, row, col, dir, "PATROL_BOAT");

                if (!setBoat) {
                    JOptionPane.showMessageDialog(null, "You Patrol Boat cannot be here", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else if (setBoat) {

                    shipSetupFrame.invalidate();
                    shipSetupFrame.validate();
                    shipSetupFrame.repaint();
                    showShipPlacement(player2);
                    PBSet.setEnabled(false);
                }
            }
        });

        JButton btnOkay = new JButton("Okay!");
        btnOkay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ACSet.isEnabled() || SBSet.isEnabled() || BSSet.isEnabled() || CRSet.isEnabled() || PBSet
                        .isEnabled()) {
                    JOptionPane.showMessageDialog(null, "You must set a location for each boat!", "Error: Set " +
                            "Pieces", JOptionPane.ERROR_MESSAGE);

                } else {
                    player2RowColSetupFrame.dispose();

                    whichPlayer = 0;
                    shipSetupButtonGrid.removeAll();
                    shipSetupFrame.dispose();
                    board();
                }

            }
        });
        JButton btnReset = new JButton("Reset");
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player2 = null;
                Player1 newP2 = new Player1("Player 2");
                player2 = new Board(newP2);
                player2RowColSetupFrame.dispose();
                shipSetupFrame.dispose();
                ACSet.setEnabled(true);
                SBSet.setEnabled(true);
                BSSet.setEnabled(true);
                CRSet.setEnabled(true);
                PBSet.setEnabled(true);
                for (int i = 0; i < 100; i++) {
                    shipSetupButtonGrid.getComponent(i).setBackground(Color.WHITE);
                }
                setUpBoardOne();
                shipSetupFrame.setVisible(true);
            }
        });
        panel.add(btnOkay);
        panel.add(btnReset);


        GroupLayout gl_panel_1 = new GroupLayout(panel_1);
        gl_panel_1.setHorizontalGroup(
                gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gl_panel_1.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(lblNewLabel)
                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                .addGap(10)
                                                .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblNewLabel_1)
                                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                                .addComponent(lblAircraftCarriersize)
                                                                .addGap(18)
                                                                .addComponent(acRow, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                                .addComponent(lblBattleshipsize, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18)
                                                                .addComponent(bsRow, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                                .addComponent(lblSubmarinesize, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18)
                                                                .addComponent(sbRow, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                                .addComponent(lblCruisersize, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18)
                                                                .addComponent(crRow, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                                .addComponent(lblPatrolBoatsize, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18)
                                                                .addComponent(pbRow, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                                .addGap(13)
                                                                .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(acCol, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(bsCol, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                                                                .addGap(18)
                                                                .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                                                .addComponent(bsDir, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18)
                                                                                .addComponent(BSSet, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                                                .addComponent(acDir, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18)
                                                                                .addComponent(ACSet))))
                                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                                                .addComponent(crCol, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18)
                                                                                .addComponent(crDir, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18)
                                                                                .addComponent(CRSet, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                                                .addComponent(sbCol, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18)
                                                                                .addComponent(sbDir, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18)
                                                                                .addComponent(SBSet, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                                                .addComponent(pbCol, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18)
                                                                                .addComponent(pbDir, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18)
                                                                                .addComponent(PBSet, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)))))))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        gl_panel_1.setVerticalGroup(
                gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gl_panel_1.createSequentialGroup()
                                .addGap(23)
                                .addComponent(lblNewLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNewLabel_1)
                                .addGap(18)
                                .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblAircraftCarriersize)
                                        .addComponent(acCol, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(acRow, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(acDir, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ACSet))
                                .addGap(18)
                                .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblBattleshipsize)
                                        .addComponent(bsRow, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bsCol, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bsDir, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(BSSet))
                                .addGap(18)
                                .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblSubmarinesize)
                                        .addComponent(sbRow, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(sbCol, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(sbDir, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(SBSet))
                                .addGap(18)
                                .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblCruisersize)
                                        .addComponent(crRow, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(crCol, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(crDir, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(CRSet))
                                .addGap(18)
                                .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblPatrolBoatsize)
                                        .addComponent(pbRow, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(pbCol, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(pbDir, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(PBSet))
                                .addContainerGap(45, Short.MAX_VALUE))
        );
        panel_1.setLayout(gl_panel_1);

        player2RowColSetupFrame.setVisible(true);

    }

    public void showShipPlacement(Board player) {

        shipSetupFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        int[][] getID = new int[10][10];
        int inc = 0;
        int r = 0;
        int c = 0;
        while (inc < 100) {
            if (c < 10) {
                getID[r][c] = inc;
                c++;
                inc++;
            } else {
                c = 0;
                r++;
                getID[r][c] = inc;
                c++;
                inc++;
            }
        }

        shipSetupFrame.setSize(100, 100);
        shipSetupFrame.setLocation(1200, 360);

//        shipSetupFrame.setUndecorated(true);


        for (int i = 0; i < player.board.length; i++) {
            for (int j = 0; j < player.board[0].length; j++) {
                if (player.board[i][j].equals("  o  ")) {
                    shipSetupButtonGrid.getComponent(getID[i][j]).setBackground(Color.GREEN);
                }
            }
        }
        shipSetupFrame.add(shipSetupButtonGrid, BorderLayout.CENTER);
        shipSetupFrame.invalidate();
        shipSetupFrame.validate();
        if (whichPlayer == 0) {
            player1RowColSetupFrame.requestFocusInWindow();
        } else {
            player2RowColSetupFrame.requestFocusInWindow();
        }
        shipSetupFrame.setFocusable(false);
//        shipSetupFrame.setVisible(true);

    }

    public void player1Win() {


        JFrame player1WinFrame;


        player1WinFrame = new JFrame();
        player1WinFrame.setSize(450, 400);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
        Point newLocation = new Point(middle.x - (player1WinFrame.getWidth() / 2)/* - 460*/,
                middle.y - (player1WinFrame.getHeight() / 2));

        player1WinFrame.getContentPane();

        player1WinFrame.setLocation(newLocation);
        JPanel panel = new JPanel();
        panel.setBackground(SystemColor.white);
        player1WinFrame.getContentPane().add(panel, BorderLayout.CENTER);

        JLabel victoryPic = new JLabel("");
        victoryPic.setIcon(new ImageIcon(getClass().getResource("player1Victorytxt.gif")));
        victoryPic.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.exit(0); //TODO replace with play again?
            }
        });
        panel.add(victoryPic);


        player1WinFrame.setResizable(false);
        player1WinFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        player1WinFrame.setUndecorated(true);
        player1WinFrame.isAlwaysOnTop();
        player2HitBoard.setVisible(false);
        player1HitBoard.setVisible(false);
        player1WinFrame.setVisible(true);


    }

    public void player2Win() {
        JFrame player2WinFrame;

        player2WinFrame = new JFrame();
        player2WinFrame.setSize(450, 400);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
        Point newLocation = new Point(middle.x - (player2WinFrame.getWidth() / 2)/* - 460*/,
                middle.y - (player2WinFrame.getHeight() / 2));

        player2WinFrame.setLocation(newLocation);

        player2WinFrame.getContentPane();

        JPanel panel = new JPanel();
        panel.setBackground(SystemColor.white);
        player2WinFrame.getContentPane().add(panel, BorderLayout.CENTER);

        JLabel victoryPic = new JLabel("");
        victoryPic.setIcon(new ImageIcon(getClass().getResource("player2Victorytxt.gif")));
        victoryPic.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.exit(0); //TODO replace with play again?
            }
        });
        panel.add(victoryPic);


        player2WinFrame.setResizable(false);
        player2WinFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        player2WinFrame.setUndecorated(true);
        player2WinFrame.isAlwaysOnTop();
        player2HitBoard.setVisible(false);
        player1HitBoard.setVisible(false);
        player2WinFrame.setVisible(true);


    }

    private void player1Surrenders() {
        player1HitBoard.setVisible(false);
        player2HitBoard.setVisible(false);
        JFrame frame;
        frame = new JFrame();
        frame.setSize(450, 320);


        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
        Point newLocation = new Point(middle.x - (frame.getWidth() / 2)/* - 460*/,
                middle.y - (frame.getHeight() / 2));

        frame.getContentPane();

        frame.setLocation(newLocation);
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(getClass().getResource("surrenderBckgrdp1Sur.gif")));
        lblNewLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.exit(0); //TODO replace with play again?
            }
        });

        panel.add(lblNewLabel);
        frame.setResizable(false);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void player2Surrenders() {
        player1HitBoard.setVisible(false);
        player2HitBoard.setVisible(false);
        JFrame frame;
        frame = new JFrame();
        frame.setSize(450, 320);

        frame.getContentPane();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
        Point newLocation = new Point(middle.x - (frame.getWidth() / 2)/* - 460*/,
                middle.y - (frame.getHeight() / 2));

        frame.setLocation(newLocation);
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        frame.getContentPane().add(panel, BorderLayout.CENTER);

//        Icon img = new ImageIcon
        JLabel lblNewLabel = new JLabel();
        lblNewLabel.setIcon(new ImageIcon(getClass().getResource("surrenderBckgrdGif.gif")));
        lblNewLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.exit(0); //TODO replace with play again?
            }
        });

        panel.add(lblNewLabel);
        frame.setResizable(false);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void rules() {
        JFrame rulesFrame = new JFrame();
        rulesFrame.setSize(450, 450);

        rulesFrame.getContentPane();

        JPanel centerPanel = new JPanel();
        rulesFrame.getContentPane().add(centerPanel, BorderLayout.CENTER);
        centerPanel.setLayout(new BorderLayout(0, 0));

        JLabel header = new JLabel("The Rules of the Battle:");
        header.setVerticalAlignment(SwingConstants.TOP);
        header.setFont(new Font("Tahoma", Font.PLAIN, 20));
        header.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(header, BorderLayout.NORTH);

        JTextPane txtpnShipsMust = new JTextPane();
        txtpnShipsMust.setEditable(false);
        txtpnShipsMust.setDropMode(DropMode.USE_SELECTION);
        txtpnShipsMust.setBackground(SystemColor.menu);
        txtpnShipsMust.setText("      \r\n\r" +
                "\n" +
                "1.  Ships must be placed horizontally or vertically (never diagonally) across grid spaces, and can't" +
                " hang over the outer grid boundary.\r\n\r" +
                "\n" +
                "2.  Ships may not be placed next to each other, nor are ships allowed to share a space with another " +
                "ship.\r\n\r" +
                "\n" +
                "3.  When placing ships, select a number (Row), a letter (Column), and a direction.\r\n" +
                "*Tip* - Click and drag the box on the right of the ship placement screen to view where your ships " +
                "are being placed/where to place your other ship\r\n\r" +
                "\n" +
                "4.  This computer version of BattleShip keeps track of your ships, hits, misses, and sunken boats.");
        centerPanel.add(txtpnShipsMust, BorderLayout.CENTER);

        JPanel leftEmpty = new JPanel();
        centerPanel.add(leftEmpty, BorderLayout.WEST);

        JPanel rightEmpty = new JPanel();
        centerPanel.add(rightEmpty, BorderLayout.EAST);

        JPanel southButtonPanel = new JPanel();
        centerPanel.add(southButtonPanel, BorderLayout.SOUTH);

        JButton continueButton = new JButton("Continue");
        continueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startUpFrame.setVisible(true);
                rulesFrame.setVisible(false);
                rulesFrame.dispose();

            }
        });
        southButtonPanel.add(continueButton);

        JPanel northVertStrut = new JPanel();
        rulesFrame.getContentPane().add(northVertStrut, BorderLayout.NORTH);

        Component verticalStrut = Box.createVerticalStrut(20);
        northVertStrut.add(verticalStrut);

        JPanel southVertStrut = new JPanel();
        rulesFrame.getContentPane().add(southVertStrut, BorderLayout.SOUTH);

        Component verticalStrut_1 = Box.createVerticalStrut(20);
        southVertStrut.add(verticalStrut_1);


        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
        Point newLocation = new Point(middle.x - (rulesFrame.getWidth() / 2),
                middle.y - (rulesFrame.getHeight() / 2));

        rulesFrame.setLocation(newLocation);
        rulesFrame.setResizable(false);
        rulesFrame.setUndecorated(true);
        rulesFrame.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        rulesFrame.setVisible(true);
    }

    public void board() {

        JButton saveQuit = new JButton("Save & Quit!");
        JButton surrender = new JButton("Surrender!");
        JButton saveQuit2 = new JButton("Save & Quit!");
        JButton surrender2 = new JButton("Surrender!");
        JPanel quitOptions = new JPanel();
        JPanel quitOptions2 = new JPanel();
        String[] options = new String[]{"Yes", "No"};
        saveQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showOptionDialog(null, "Are you sure you want to Quit?", "Save and Quit?",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
                if (choice == 0) {
                    //TODO Save the game
                    System.exit(0);
                }
            }
        });
        surrender.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showOptionDialog(null, "Are you sure you want to Surrender?", "Surrender?",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
                if (choice == 0) {
                    player1Surrenders();
                }
            }
        });
        saveQuit2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showOptionDialog(null, "Are you sure you want to Quit?", "Save and Quit?",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
                if (choice == 0) {
                    //TODO Save the game
                    System.exit(0);
                }
            }
        });
        surrender2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showOptionDialog(null, "Are you sure you want to Surrender?", "Surrender?",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
                if (choice == 0) {
                    player2Surrenders();
                }
            }
        });


        quitOptions.add(saveQuit);
        quitOptions.add(surrender);
        quitOptions2.add(saveQuit2);
        quitOptions2.add(surrender2);
        player1HitBoard.setSize(750, 800);
        player1HitBoard.add(quitOptions, BorderLayout.SOUTH);
        player1HitBoard.setLocation(200, 150);
        player1HitBoard.setResizable(false);


        if (!p2.getName().equalsIgnoreCase("computer")) {
            player2HitBoard.setSize(750, 800);
            player2HitBoard.add(quitOptions2, BorderLayout.SOUTH);
            player2HitBoard.setLocation(970, 150);
            player2HitBoard.setResizable(false);


        }

        if (whichPlayer == 0) {
            player1HitBoard.add(player1ButtonGrid, BorderLayout.CENTER);
            saveQuit.setEnabled(true);
            surrender.setEnabled(true);
            player1Buttons.forEach((jButton -> jButton.setEnabled(true)));
            player1HitBoard.setVisible(true);
        }
        if (whichPlayer == 1) {
            player2HitBoard.add(player2ButtonGrid, BorderLayout.CENTER);
            //player2HitBoard.add(cheat, BorderLayout.SOUTH);
            saveQuit.setEnabled(false);
            surrender.setEnabled(false);
            player2Buttons.forEach((jButton -> jButton.setEnabled(true)));
            saveQuit2.setEnabled(true);
            surrender2.setEnabled(true);
            player2HitBoard.setVisible(true);
        }


    }

    public void player1SunkShip(Boat boat) {
        player1HitBoard.setEnabled(false);
        player2HitBoard.setEnabled(false);
        String boatName = boat.getBoatName();

        final JFrame sinkFrame = new JFrame();
        sinkFrame.setSize(500, 450);


        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
        Point newLocation = new Point(middle.x - (sinkFrame.getWidth() / 2),
                middle.y - (sinkFrame.getHeight() / 2));
        sinkFrame.setLocation(newLocation);

        if (boatName.equals("AIRCRAFT_CARRIER")) {


            JPanel panel = new JPanel();
            panel.setBackground(new Color(0,0,0,0));
            sinkFrame.getContentPane().add(panel, BorderLayout.CENTER);

            JLabel lblNewLabel = new JLabel();
            lblNewLabel.setIcon(new ImageIcon(getClass().getResource("player2lostAC.gif")));

            lblNewLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    player1HitBoard.setEnabled(true);
                    player2HitBoard.setEnabled(true);
                    sinkFrame.setVisible(false);
                    sinkFrame.dispose();
                }
            });
            panel.add(lblNewLabel);
            sinkFrame.setResizable(false);
            sinkFrame.setUndecorated(true);
            sinkFrame.setAlwaysOnTop(true);
            sinkFrame.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
            sinkFrame.setVisible(true);

        } else if (boatName.equals("BATTLESHIP")) {

            JPanel panel = new JPanel();
            panel.setBackground(new Color(0,0,0,0));
            sinkFrame.getContentPane().add(panel, BorderLayout.CENTER);

            JLabel lblNewLabel = new JLabel();
            lblNewLabel.setIcon(new ImageIcon(getClass().getResource("player2lostBS.gif")));

            lblNewLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    sinkFrame.dispose();
                    sinkFrame.setVisible(false);
                    player1HitBoard.setEnabled(true);
                    player2HitBoard.setEnabled(true);


                }
            });
            panel.add(lblNewLabel);
            sinkFrame.setResizable(false);
            sinkFrame.setUndecorated(true);
            sinkFrame.setAlwaysOnTop(true);
            sinkFrame.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
            sinkFrame.setVisible(true);
        } else if (boatName.equals("SUBMARINE")) {
            JPanel panel = new JPanel();
            panel.setBackground(new Color(0,0,0,0));
            sinkFrame.getContentPane().add(panel, BorderLayout.CENTER);

            JLabel lblNewLabel = new JLabel();
            lblNewLabel.setIcon(new ImageIcon(getClass().getResource("player1sunkSM.gif")));

            lblNewLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    sinkFrame.dispose();
                    sinkFrame.setVisible(false);
                    player1HitBoard.setEnabled(true);
                    player2HitBoard.setEnabled(true);


                }
            });
            panel.add(lblNewLabel);
            sinkFrame.setResizable(false);
            sinkFrame.setUndecorated(true);
            sinkFrame.setAlwaysOnTop(true);
            sinkFrame.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
            sinkFrame.setVisible(true);
        } else if (boatName.equals("CRUISER")) {
            JPanel panel = new JPanel();
            panel.setBackground(new Color(0,0,0,0));
            sinkFrame.getContentPane().add(panel, BorderLayout.CENTER);

            JLabel lblNewLabel = new JLabel();
            lblNewLabel.setIcon(new ImageIcon(getClass().getResource("player1sunkCR.gif")));

            lblNewLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    sinkFrame.dispose();
                    sinkFrame.setVisible(false);
                    player1HitBoard.setEnabled(true);
                    player2HitBoard.setEnabled(true);


                }
            });
            panel.add(lblNewLabel);
            sinkFrame.setResizable(false);
            sinkFrame.setUndecorated(true);
            sinkFrame.setAlwaysOnTop(true);
            sinkFrame.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
            sinkFrame.setVisible(true);
        } else {
            JPanel panel = new JPanel();
            panel.setBackground(new Color(0,0,0,0));
            sinkFrame.getContentPane().add(panel, BorderLayout.CENTER);

            JLabel lblNewLabel = new JLabel();
            lblNewLabel.setIcon(new ImageIcon(getClass().getResource("player1sunkPB.gif")));

            lblNewLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    sinkFrame.dispose();
                    sinkFrame.setVisible(false);
                    player1HitBoard.setEnabled(true);
                    player2HitBoard.setEnabled(true);


                }
            });
            panel.add(lblNewLabel);
            sinkFrame.setResizable(false);
            sinkFrame.setUndecorated(true);
            sinkFrame.setAlwaysOnTop(true);
            sinkFrame.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
            sinkFrame.setVisible(true);
        }
    }

    public void player2SunkShip(Boat boat) {
        //TODO Figure out how to have all other frames setVisible(false)
        player1HitBoard.setEnabled(false);
        player2HitBoard.setEnabled(false);
        String boatName = boat.getBoatName();

        final JFrame sinkFrame = new JFrame();
        sinkFrame.setSize(500, 450);


        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
        Point newLocation = new Point(middle.x - (sinkFrame.getWidth() / 2),
                middle.y - (sinkFrame.getHeight() / 2));
        sinkFrame.setLocation(newLocation);

        if (boatName.equals("AIRCRAFT_CARRIER")) {

            JPanel panel = new JPanel();
            panel.setBackground(new Color(0,0,0,0));
            sinkFrame.getContentPane().add(panel, BorderLayout.CENTER);

            JLabel lblNewLabel = new JLabel();
            lblNewLabel.setIcon(new ImageIcon(getClass().getResource("player1sunkAC.gif")));

            lblNewLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    player1HitBoard.setEnabled(true);
                    player2HitBoard.setEnabled(true);
                    sinkFrame.setVisible(false);
                    sinkFrame.dispose();
                }
            });
            panel.add(lblNewLabel);
            sinkFrame.setResizable(false);
            sinkFrame.setUndecorated(true);
            sinkFrame.setAlwaysOnTop(true);
            sinkFrame.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
            sinkFrame.setVisible(true);

        } else if (boatName.equals("BATTLESHIP")) {
            JPanel panel = new JPanel();
            panel.setBackground(new Color(0,0,0,0));
            sinkFrame.getContentPane().add(panel, BorderLayout.CENTER);

            JLabel lblNewLabel = new JLabel();
            lblNewLabel.setIcon(new ImageIcon(getClass().getResource("player1lostBS.gif")));

            lblNewLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    player1HitBoard.setEnabled(true);
                    player2HitBoard.setEnabled(true);
                    sinkFrame.setVisible(false);
                    sinkFrame.dispose();
                }
            });
            panel.add(lblNewLabel);
            sinkFrame.setResizable(false);
            sinkFrame.setUndecorated(true);
            sinkFrame.setAlwaysOnTop(true);
            sinkFrame.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
            sinkFrame.setVisible(true);

        } else if (boatName.equals("SUBMARINE")) {
            JPanel panel = new JPanel();
            panel.setBackground(new Color(0,0,0,0));
            sinkFrame.getContentPane().add(panel, BorderLayout.CENTER);

            JLabel lblNewLabel = new JLabel();
            lblNewLabel.setIcon(new ImageIcon(getClass().getResource("player2sunkSM.gif")));

            lblNewLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    player1HitBoard.setEnabled(true);
                    player2HitBoard.setEnabled(true);
                    sinkFrame.setVisible(false);
                    sinkFrame.dispose();
                }
            });
            panel.add(lblNewLabel);
            sinkFrame.setResizable(false);
            sinkFrame.setUndecorated(true);
            sinkFrame.setAlwaysOnTop(true);
            sinkFrame.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
            sinkFrame.setVisible(true);
        } else if (boatName.equals("CRUISER")) {
            JPanel panel = new JPanel();
            panel.setBackground(new Color(0,0,0,0));
            sinkFrame.getContentPane().add(panel, BorderLayout.CENTER);

            JLabel lblNewLabel = new JLabel();
            lblNewLabel.setIcon(new ImageIcon(getClass().getResource("player2sunkCR.gif")));

            lblNewLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    player1HitBoard.setEnabled(true);
                    player2HitBoard.setEnabled(true);
                    sinkFrame.setVisible(false);
                    sinkFrame.dispose();
                }
            });
            panel.add(lblNewLabel);
            sinkFrame.setResizable(false);
            sinkFrame.setUndecorated(true);
            sinkFrame.setAlwaysOnTop(true);
            sinkFrame.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
            sinkFrame.setVisible(true);
        } else {
            JPanel panel = new JPanel();
            panel.setBackground(new Color(0,0,0,0));
            sinkFrame.getContentPane().add(panel, BorderLayout.CENTER);

            JLabel lblNewLabel = new JLabel();
            lblNewLabel.setIcon(new ImageIcon(getClass().getResource("player2sunkPB.gif")));

            lblNewLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    player1HitBoard.setEnabled(true);
                    player2HitBoard.setEnabled(true);
                    sinkFrame.setVisible(false);
                    sinkFrame.dispose();
                }
            });
            panel.add(lblNewLabel);
            sinkFrame.setResizable(false);
            sinkFrame.setUndecorated(true);
            sinkFrame.setAlwaysOnTop(true);
            sinkFrame.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
            sinkFrame.setVisible(true);
        }
    }


}
