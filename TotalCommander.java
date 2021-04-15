import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class TotalCommander implements ActionListener, MouseListener, KeyListener {

    JButton b2, b3, b4, b5, b6, b7, b8, b9, b10, b11_F5, b12_F6, b13_F7, b14_F8, b15_CMD_Q, b16GoTo, b17GoTo;
    JLabel l1, l2, l4, l5, l6, l7, l8, l9;
    // File a = new File("/Users/adamhacia/Desktop");
    File a = new File(System.getProperty("user.home"));
    File b = new File(System.getProperty("user.dir"));
    String sep = System.getProperty("file.separator");
    String root = System.getenv("SystemDrive");
    File[] files = null;
    File[] files2 = null;
    String[][] data;
    String[][] data2;
    String[] column = {"NAME", "DATE"};
    JTable jt;
    JTable jt2;
    JScrollPane sp;
    JScrollPane sp2;
    JPanel panel = new JPanel();
    JFrame f;
    int k=0;
    final javax.swing.JFileChooser fc = new javax.swing.JFileChooser();
    File x = null;
    Icon icon = null;
    int[] selectedRows = null;
    int max = 0;
    File dest;
    File fileToCopy;
    String newPath;
    File temp;

    //Setting the screen
    TotalCommander() {

        f = new JFrame("Hacia Commander");

        b2 = new JButton("\u293E");
        b2.setBounds(565,40,20,20);

        b2.addActionListener(this);

        f.add(b2);

        b3 = new JButton("\u2193");
        b4 = new JButton("\u2191");
        b5 = new JButton("\u2193");
        b6 = new JButton("\u2191");
        b7 = new JButton("\u2193");
        b8 = new JButton("\u2191");
        b9 = new JButton("\u2193");
        b10 = new JButton("\u2191");
        b11_F5 = new JButton("F5 COPY");
        b12_F6 = new JButton("F6 MOVE");
        b13_F7 = new JButton("F7 NEW FOLDER");
        b14_F8 = new JButton("F8 DELETE");
        b15_CMD_Q = new JButton("EXIT");
        b16GoTo = new JButton("Go to");
        b17GoTo = new JButton("Go to");


        b3.setBounds(15,40,15,15);
        b4.setBounds(15,55,15,15);
        b5.setBounds(685,40,15,15);
        b6.setBounds(685,55,15,15);
        b7.setBounds(430,40,15,15);
        b8.setBounds(430,55,15,15);
        b9.setBounds(1100,40,15,15);
        b10.setBounds(1100,55,15,15);
        b11_F5.setBounds(30,780,214,20);
        b12_F6.setBounds(244,780,214,20);
        b13_F7.setBounds(458,780,214,20);
        b14_F8.setBounds(672,780,214,20);
        b15_CMD_Q.setBounds(886,780,214,20);
        b16GoTo.setBounds(30,0,40,20);
        b17GoTo.setBounds(700,0,40,20);

        //Adding listeners to button
        b3.addActionListener(this);b4.addActionListener(this);b5.addActionListener(this);b6.addActionListener(this);b7.addActionListener(this);b8.addActionListener(this);b9.addActionListener(this);b10.addActionListener(this);b11_F5.addActionListener(this);b12_F6.addActionListener(this);b13_F7.addActionListener(this);b14_F8.addActionListener(this);b15_CMD_Q.addActionListener(this);b16GoTo.addActionListener(this);b17GoTo.addActionListener(this);

        //Adding buttons to frame
        f.add(b3);f.add(b4);f.add(b5);f.add(b6);f.add(b7);f.add(b8);f.add(b9);f.add(b10);f.add(b11_F5);f.add(b12_F6);f.add(b13_F7);f.add(b14_F8);f.add(b15_CMD_Q);f.add(b16GoTo);f.add(b17GoTo);
        b2.setToolTipText("Restart");
        b3.setToolTipText("Descending");
        b4.setToolTipText("Ascending");
        b5.setToolTipText("Descending");
        b6.setToolTipText("Ascending");
        b7.setToolTipText("Descending");
        b8.setToolTipText("Ascending");
        b9.setToolTipText("Descending");
        b10.setToolTipText("Ascending");

        updateTables(true);

        l9 = new JLabel("Project created by Adam Hącia", JLabel.CENTER);
        l9.setBounds(465,0,200,15);
        f.add(l9);

        f.setSize(1130,840);
        f.getContentPane().setBackground(new Color(200, 200, 200));
        f.setLayout(null);
        f.setVisible(true);
    }

    //Using buttons
    public void actionPerformed(ActionEvent e) {
        int[] g = jt.getSelectedRows();
        int[] h = jt2.getSelectedRows();
        max = Math.max(g.length,h.length);
        if (e.getSource() == b2) {
            a = new File(System.getProperty("user.home"));
            b = new File(System.getProperty("user.dir"));
            updateTables(true);
            panel.removeAll();
            panel.repaint();
        } else if (e.getSource() == b3) {
            data = sorting(data, 0,true);
            updateTables(false);
        } else if (e.getSource() == b4) {
            data = sorting(data, 0, false);
            updateTables(false);
        } else if (e.getSource() == b5) {
            data2 = sorting(data2, 0,true);
            updateTables(false);
        } else if (e.getSource() == b6) {
            data2 = sorting(data2, 0,false);
            updateTables(false);
        } else if (e.getSource() == b7) {
            data = sorting(data, 1,true);
            updateTables(false);
        } else if (e.getSource() == b8) {
            data = sorting(data, 1, false);
            updateTables(false);
        } else if (e.getSource() == b9) {
            data2 = sorting(data2, 1,true);
            updateTables(false);
        } else if (e.getSource() == b10) {
            data2 = sorting(data2, 1, false);
            updateTables(false);
        } else if (e.getSource()==b12_F6 || e.getSource()==b11_F5 || e.getSource()==b14_F8) {
            if (g.length==0 || h.length ==0) {
                for (int qwerty = 0; qwerty < max; qwerty++) {
                    if (g.length>h.length)
                    {
                        fileToCopy = new File(a.getPath() + sep + deleteBrackets(data[g[qwerty]][0]));
                        dest = new File(b + sep + fileToCopy.getName());
                    } else {
                        fileToCopy = new File(b.getPath() + sep + deleteBrackets(data2[h[qwerty]][0]));
                        dest = new File(a + sep + fileToCopy.getName());
                    }
                    if (e.getSource()==b12_F6) {
                        toMove(fileToCopy, dest);
                    } else if (e.getSource()==b11_F5){
                        toCopy(fileToCopy, dest);
                    } else {
                        deleteFiles(fileToCopy);
                    }
                }
            }
            updateTables(true);
        } else if (e.getSource()==b13_F7) {
            if (g.length==0 || h.length ==0) {
                if (max == g.length) {
                    creatingFolder(a);
                } else {
                    creatingFolder(b);
                }
            }
            updateTables(true);
        } else if (e.getSource()==b15_CMD_Q) {
            System.exit(0);
        } else if (e.getSource()==b16GoTo) {
            temp = pathChange();
            if (temp!=null) {
                a=temp;
            }
            updateTables(true);
        } else if (e.getSource()==b17GoTo) {
            temp = pathChange();
            if (temp!=null) {
                b=temp;
            }
            updateTables(true);
        }
    }

    //Updating table view
    public void updateTables(boolean hmm) {

        int i = 0;
        int j = 0;
        if (k != 0) {
            f.remove(jt);
            f.remove(jt2);
            f.remove(l1);
            f.remove(l2);
            f.remove(sp);
            f.remove(sp2);
        }
        k = 1;
        files = null;
        files2 = null;
        if (hmm) {
            data = null;
            data2 = null;
        }
        jt = null;
        sp = null;
        jt2 = null;
        sp2 = null;
        if (hmm) {
        files = a.listFiles();
        files2 = b.listFiles();
        if (!a.getPath().equals("/") && (!a.getPath().equals(root + sep))) {
            try {
                data = new String[files.length + 1][2];
            } catch (NullPointerException e) {
                data = new String[1][2];
            }
            data[0][0] = "[...]";
            data[0][1] = getCreationDate(a);
            i = 1;
        } else {
            data = new String[files.length][2];
        }
        if (!b.getPath().equals("/") && (!b.getPath().equals(root + sep))) {
            try {
                data2 = new String[files2.length + 1][2];
            } catch (NullPointerException e) {
                data2 = new String[1][2];
            }
            data2[0][0] = "[...]";
            data2[0][1] = getCreationDate(b);
            j = 1;
        } else {
            data2 = new String[files2.length][2];
        }
            for (File file : Objects.requireNonNull(files)) {

                if (file.isDirectory()) {
                    data[i][0] = "[" + file.getName() + "]";
                } else {
                    data[i][0] = file.getName();
                }
                data[i][1] = getCreationDate(file);
                i++;
            }
            for (File file : Objects.requireNonNull(files2)) {

                if (file.isDirectory()) {
                    data2[j][0] = "[" + file.getName() + "]";
                } else {
                    data2[j][0] = file.getName();

                }
                data2[j][1] = getCreationDate(file);
                j++;
            }
        }

        jt =  new JTable(data,column) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };

        jt.setBounds(30,40,400,700);
        sp=new JScrollPane(jt);
        sp.setBounds(30,40,400,700);
        f.add(sp);

        jt.setDragEnabled(true);
        jt.setDropMode(DropMode.INSERT_ROWS);

        jt2=new JTable(data2,column) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };

        jt2.setBounds(700,40,400,700);
        sp2=new JScrollPane(jt2);
        sp2.setBounds(700,40,400,700);
        f.add(sp2);

        jt2.setDragEnabled(true);
        jt2.setDropMode(DropMode.INSERT_ROWS);

        l1 = new JLabel(a.getPath());
        l2 = new JLabel(b.getPath());

        l1.setToolTipText(a.getPath());
        l2.setToolTipText(b.getPath());

        l1.setBounds(30,5,400,50);
        l2.setBounds(700,5,400,50);
        f.add(l1);
        f.add(l2);
        l1.repaint();
        l2.repaint();

        jt.addMouseListener(new MouseAdapter()  {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount()==1) {
                    updateFileInformation(a, data, jt.getSelectedRow());
                    jt2.clearSelection();
                }
                if (e.getClickCount()==2) {
                    File temp = a;
                    a = changingDirectories(a, data, jt.getSelectedRow());
                    if (!a.getPath().equals(temp.getPath())) {
                        updateTables(true);
                    }
                }
            }
            public void mousePressed(MouseEvent e) {
                jt2.setTransferHandler(new TransferHandler() {
                    public boolean canImport(TransferSupport support) {
                        if(!support.isDrop()) {
                            return false;
                        }
                        return support.isDataFlavorSupported(DataFlavor.stringFlavor);
                    }

                    public boolean importData(TransferSupport support) {
                        if(!canImport(support)) {
                            return false;
                        }
                        int[] rows = jt.getSelectedRows();

                        Object[] stringi = new String[rows.length];

                        if(jt.getSelectedRow() != - 1) {
                            for(int k = 0; k < rows.length; k++) {
                                stringi[k] = deleteBrackets(data[rows[k]][0]);
                                File temp = new File(a.getPath()+sep+stringi[k]);
                                File temp2 = new File(b.getPath()+sep+stringi[k]);
                                toCopy(temp, temp2);
                            }
                            updateTables(true);
                        }
                        return true;
                    }
                });
            }
        }
        );

        jt2.addMouseListener(new MouseAdapter()  {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount()==1) {
                    updateFileInformation(b,data2,jt2.getSelectedRow());
                    jt.clearSelection();
                } else if (e.getClickCount()==2) {
                    File temp = b;
                    b = changingDirectories(b, data2, jt2.getSelectedRow());
                    if (!b.getPath().equals(temp.getPath())) {
                        updateTables(true);
                    }
                }
            }
            public void mousePressed(MouseEvent e) {
                jt.setTransferHandler(new TransferHandler() {
                    public boolean canImport(TransferSupport support) {
                        if(!support.isDrop()) {
                            return false;
                        }

                        return support.isDataFlavorSupported(DataFlavor.stringFlavor);
                    }

                    public boolean importData(TransferSupport support) {
                        if(!canImport(support)) {
                            return false;
                        }

                        int[] rows = jt2.getSelectedRows();

                        Object[] stringi = new String[rows.length];

                        if(jt2.getSelectedRow() != - 1) {
                            for(int k = 0; k < rows.length; k++) {
                                stringi[k] = deleteBrackets(data2[rows[k]][0]);
                                File temp = new File(b.getPath()+sep+stringi[k]);
                                File temp2 = new File(a.getPath()+sep+stringi[k]);
                                toCopy(temp, temp2);
                            }
                            updateTables(true);
                        }
                        return true;
                    }
                });
            }
        }
        );

        jt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getExtendedKeyCode() == 116 || e.getExtendedKeyCode() == 117) { //F5
                    selectedRows = jt.getSelectedRows();
                    for (int selectedRow : selectedRows) {
                        File toBeCopied;
                        if (selectedRow != 0) {
                            toBeCopied = new File(a + sep + deleteBrackets(data[selectedRow][0]));
                            dest = new File(b + sep + toBeCopied.getName());
                            if (e.getExtendedKeyCode() == 117) {
                                toMove(toBeCopied, dest);
                            } else {
                                toCopy(toBeCopied, dest);
                            }
                        }
                    }
                    updateTables(true);
                } else if (e.getExtendedKeyCode() == 118){ //F7
                    creatingFolder(a);
                    updateTables(true);
                } else if (e.getExtendedKeyCode() == 119) { //F8
                    selectedRows = jt.getSelectedRows();
                    for (int selectedRow : selectedRows) {
                        if(selectedRow!=0) {
                            File toBeDeleted = new File(a + sep + deleteBrackets(data[selectedRow][0]));
                            deleteFiles(toBeDeleted);
                        }
                    }
                    updateTables(true);
                }
            }
        });

        jt2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getExtendedKeyCode() == 116 || e.getExtendedKeyCode() == 117) { //F5
                    selectedRows = jt2.getSelectedRows();
                    for (int selectedRow : selectedRows) {
                        File toBeCopied;
                        if (selectedRow != 0) {
                            toBeCopied = new File(b + sep + deleteBrackets(data2[selectedRow][0]));
                            dest = new File(a + sep + toBeCopied.getName());
                            if (e.getExtendedKeyCode() == 117) {
                                toMove(toBeCopied, dest);
                            } else {
                                toCopy(toBeCopied, dest);
                            }
                        }
                    }
                    updateTables(true);
                } else if (e.getExtendedKeyCode() == 118) { //F7
                    creatingFolder(b);
                    updateTables(true);
                } else if (e.getExtendedKeyCode() == 119) { //F8
                    selectedRows = jt2.getSelectedRows();
                    for (int selectedRow : selectedRows) {
                        if (selectedRow != 0) {
                            File toBeDeleted = new File(b + sep + deleteBrackets(data2[selectedRow][0]));
                            deleteFiles(toBeDeleted);
                        }
                    }
                    updateTables(true);
                }
            }
        });

        f.addMouseListener(this);
    }

    //Main function
    public static void main(String[] args) {
        new TotalCommander();
    }

    //Updating file information
    public void updateFileInformation(File a, String[][] data, int row) {
        f.remove(panel);
        panel=new JPanel();
        panel.setBounds(440,80,250,130);
        f.add(panel);
        panel.repaint();
        panel.setBackground(new Color(200, 200, 200));
        icon = changingIcons(a, data, row);
        if (row != 0 || (a.getPath().equals("/")  && (!b.getPath().equals(root + sep)))) {
            l4 = new JLabel(deleteBrackets(data[row][0]), icon, JLabel.CENTER);
        } else {
            l4 = new JLabel(a.getName(), icon, JLabel.CENTER);
        }
        l4.setBounds(40,20,180,15);
        panel.add(l4);
        l5 = new JLabel("Creat: " + data[row][1]);
        l5.setBounds(40,40,180,15);
        panel.add(l5);

        l6 = new JLabel("Modif: " + getLastModifiedDate(a, data, row));
        l6.setBounds(40,60,180,15);
        panel.add(l6);
        l7 = new JLabel("Size: " + getSize(a, data, row));
        l7.setBounds(40,80,180,15);
        panel.add(l7);
        l8 = new JLabel("Type: " + getType(a, data, row));
        l8.setBounds(40,100,180,15);
        panel.add(l8);
    }

    //Getting size of file
    public String getSize(File fileToGetType, String[][] data, int row) {
        if (row == 0 && !fileToGetType.getPath().equals("/") && !fileToGetType.getPath().equals(root+sep) ) {
            if(fileToGetType.isDirectory()) {
                return "<DIR>";
            } else {
                return fileToGetType.length() + " b";
            }
        } else {
            File fileName = new File(fileToGetType.getPath() + sep + deleteBrackets(data[row][0]));
            if(fileName.isDirectory()) {
                return "<DIR>";
            } else {
                return fileName.length() + " b";
                }
            }
        }

    //Getting type of file
    public String getType(File fileToGetType, String[][] data, int row) {
        if (row == 0 && !fileToGetType.getPath().equals("/") && !fileToGetType.getPath().equals(root+sep)) {
            return "Directory";
        } else {
            File fileName = new File(fileToGetType.getPath() + sep + deleteBrackets(data[row][0]));
            if (fileName.isDirectory()) {
                return "Directory";
            } else {
                if (fileName.isHidden()) {
                    return "Other";
                }
                int lastIndexOfDot = fileName.getName().lastIndexOf(".");
                if (lastIndexOfDot == -1) {
                    return "Other";
                }
                return fileName.getName().substring(lastIndexOfDot + 1);
            }
        }
    }

    //Getting last modification date
    public String getLastModifiedDate(File fileToGetDate, String[][] data, int row){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if (row == 0 && !fileToGetDate.getPath().equals("/") && !fileToGetDate.getPath().equals(root+sep)) {
            return dateFormat.format(fileToGetDate.lastModified());
        } else {
            File fileName = new File(fileToGetDate.getPath() + sep + deleteBrackets(data[row][0]));
            return dateFormat.format(fileName.lastModified());
        }
    }

    //Getting creation date
    public String getCreationDate(File fileToGetDate){
        Path p = fileToGetDate.toPath();
        BasicFileAttributes attributes = null;
        FileTime time;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String date;
        try
        {
            attributes =
                    Files.readAttributes(p, BasicFileAttributes.class);
        }
        catch (IOException ignored)
        {
        }
        try
        {
            assert attributes != null;
            time = attributes.creationTime();
            Long l = time.toMillis();
            date = dateFormat.format(l);
        }
        catch (NullPointerException exception)
        {
            date = "1970-01-01 01:00";
        }
        return date;
    }

    //Deleting directories
    public boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }

    //Deleting files
    public void deleteFiles(File toBeDeleted) {
        if (!toBeDeleted.getName().equals("...")) {
            int awe = JOptionPane.showConfirmDialog(f, "Are you sure, you want to delete '" + toBeDeleted.getName() + "' from '" + toBeDeleted + "'" + "?");
            if (awe == JOptionPane.YES_OPTION) {
                if (b.getPath().contains(toBeDeleted.getPath())) {
                    b = a;
                } else if (a.getPath().contains(toBeDeleted.getPath())) {
                    a = b;
                }
                if (toBeDeleted.isDirectory()) {
                    deleteDirectory(toBeDeleted);
                } else {
                    toBeDeleted.delete();
                }
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        }
    }

    //Copying files
    public void toCopy(File src, File dest) {
        if (!src.getName().equals("...")) {
            int awe = JOptionPane.showConfirmDialog(f, "Are you sure, you want to copy '" + src.getName() + "' from '" + src.getPath() + "' to '" + dest.getParent() + "'");
            if (awe == JOptionPane.YES_OPTION) {
                try {
                    boolean ok = copyFolder(src, dest);
                    if (!ok) {
                        JOptionPane.showMessageDialog(f, "Couldn't copy directory to its subdirectory");
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(f, "Couldn't copy directory");
                }
            }
        }
    }

    //Moving files
    public void toMove(File src, File dest) {
        if (!src.getName().equals("...")) {
            int awe = JOptionPane.showConfirmDialog(f, "Are you sure, you want to move '" + src.getName() + "' from '" + src.getPath() + "' to '" + dest.getParent() + "'");
            if (awe == JOptionPane.YES_OPTION) {
                try {
                    boolean ok = copyFolder(src, dest);
                    if (!ok) {
                        JOptionPane.showMessageDialog(f, "Couldn't move directory to its subdirectory");
                    } else {
                        deleteDirectory(src);
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(f, "Couldn't move directory");
                }
            }
        }
    }

    //Copying directories
    public boolean copyFolder(File src, File dest)
            throws IOException{
        if (!dest.getPath().contains(src.getPath())) {
            if (src.isDirectory()) {

                if (!dest.exists()) {
                    dest.mkdir();
                }

                String[] files = src.list();

                if (files != null) {
                    for (String file : files) {
                        File srcFile = new File(src, file);
                        File destFile = new File(dest, file);
                        copyFolder(srcFile, destFile);
                    }
                }

            } else {
                InputStream in = new FileInputStream(src);
                OutputStream out = new FileOutputStream(dest);

                byte[] buffer = new byte[1024];

                int length;
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }

                Path srcPath = src.toPath();
                Path destPath = dest.toPath();
                FileTime creationTime  = (FileTime) Files.readAttributes(srcPath, "creationTime").get("creationTime");
                BasicFileAttributeView attributes = Files.getFileAttributeView(destPath, BasicFileAttributeView.class);
                attributes.setTimes(creationTime,creationTime,creationTime);
                in.close();
                out.close();
            }
            return true;
        } else {
            return false;
        }
    }

    //Creating directories
    public boolean creatingFolder(File a) {
        String name;
        try {
            name = JOptionPane.showInputDialog(f, "Enter new folder's name");
            if (!name.equals("")) {
                File newFile = new File(a.getPath() + sep + name);
                if (newFile.exists()) {
                    JOptionPane.showMessageDialog(f, "Directory of name '" + name + "' already exists in '" + a.getPath() + "'");
                }
                return newFile.mkdir();
            } else {
                JOptionPane.showMessageDialog(f, "You cannot create folder without name!");
                return creatingFolder(a);
            }
        }
        catch (NullPointerException e) {
            return false;
        }

    }

    //Path change
    public File pathChange() {
        newPath = JOptionPane.showInputDialog(f, "Enter path's name");
        if(newPath!=null) {
            temp = new File(newPath);
            if (temp.exists()&&temp.isDirectory()) {
                return temp;
            } else {
                JOptionPane.showMessageDialog(f, "Path '" + newPath + "' doesn't exist or it isn't a directory");
                return null;
            }
        }
        return null;
    }

    //Sorting columns
    public String[][] sorting(String[][] data, int columnNumber, boolean desc) {
        String[] help;
        for (int k1 = 0; k1 < data.length-1; k1++) {
            int k = 1;
            if (!data[0][0].equals("[...]")) {
                k = 0;
            }
            for (; k < data.length - 1; k++) {
                if (desc) {
                    if (data[k][columnNumber].compareToIgnoreCase(data[k + 1][columnNumber]) > 0) {
                        help = data[k];
                        data[k] = data[k + 1];
                        data[k + 1] = help;
                    }
                } else {
                    if (data[k][columnNumber].compareToIgnoreCase(data[k + 1][columnNumber]) < 0) {
                        help = data[k];
                        data[k] = data[k + 1];
                        data[k + 1] = help;
                    }
                }
            }
        }
        return data;
    }

    //Changing folders
    public File changingDirectories(File a, String[][] data, int row) {
        String ab = data[row][0];
        File aCopy = null;
        if (ab.equals("[...]")) {
            if(!a.getPath().equals("/") && !a.getPath().equals(root+sep)) {
                a = new File(a.getParent());
            }
        } else {
            ab = deleteBrackets(ab);
            aCopy = a;
            a = new File(a.getPath() + sep + ab);
        }
        if (!a.isDirectory()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                if (a.exists() && !a.isHidden()) {
                    desktop.open(a);
                } else {
                    JOptionPane.showMessageDialog(f, "File '" + a.getName() + "' doesn't exist or is hidden and cannot be open!");
                }
            } catch (IOException ignored) {
            }
            a = aCopy;
        } else {
            updateTables(true);
        }
        return a;
    }

    //Changing icons
    public Icon changingIcons(File a, String[][] data, int row)  {
        String ab =data[row][0];
        if (ab.equals("[...]")) {
            if (!a.getPath().equals("/") && !a.getPath().equals(root+sep)) {
                x = new File(a.getPath());
            }
        } else {
            ab = deleteBrackets(ab);
            x = new File(a.getPath()+sep+ab);
        }
        icon = fc.getUI().getFileView(fc).getIcon(x);
        return icon;
    }

    //Deleting brackets in folders
    public String deleteBrackets(String fileName) {
        fileName = fileName.replaceAll("\\[", "");
        fileName = fileName.replaceAll("]", "");
        return fileName;
    }

    //Getting to existing directory
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 1) {
            while (!a.exists()) {
                a = a.getParentFile();
            }
            while (!b.exists()) {
                b = b.getParentFile();
            }
            if (Objects.requireNonNull(a.listFiles()).length!=data.length || Objects.requireNonNull(b.listFiles()).length!=data2.length) {
                updateTables(true);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}