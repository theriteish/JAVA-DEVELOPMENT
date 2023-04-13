import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.Buffer;

public class TextEditor implements ActionListener {
    JFrame frame;

    JMenuBar menuBar;
    JMenu file , edit;

    JMenuItem newFile , openFile , saveFile;
    JMenuItem cut , copy , paste , selectAll, close;

    JTextArea textArea;

    TextEditor(){
        //Initialize a frame:
        frame = new JFrame();

        //Initialize a menubar:
        menuBar = new JMenuBar();

        //initialize file menu:
        file = new JMenu("File");

        //Initialize edit menu:
        edit = new JMenu("Edit");

        //Initialize the text area:
        textArea = new JTextArea();

        //Initialize file menu items:
        newFile = new JMenuItem("New");
        openFile = new JMenuItem("Open");
        saveFile = new JMenuItem("Save");
        //add action listeners to file menu items:
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        //Initialize edit menu items:
        cut = new JMenuItem("Cut");
        paste = new JMenuItem("Paste");
        copy = new JMenuItem("Copy");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");
        //add action listeners to edit file menu items:
        cut.addActionListener(this);
        paste.addActionListener(this);
        copy.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);



        //set MenuBar to frame;
        frame.setJMenuBar(menuBar);

       //create content pane;
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));

        //add text area to panel
        panel.add(textArea,BorderLayout.CENTER);
        //create a scroll pane;
        JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //add scrollPane to pane
        panel.add(scrollPane);
        //add panel to frame
        frame.add(panel);

        //set dimensions for frame:
        frame.setBounds(0,0,400,400);
        frame.setTitle("Text Editor");
        frame.setVisible(true);
       //frame.setLayout(null);


        //add menu in our menuBar:
        menuBar.add(file);
        menuBar.add(edit);


        //add menuItems to file menu:
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //add menuItems to our edit:
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == cut){
            //perform cut operation:
            textArea.cut();
        }
        if (ae.getSource() == copy) {
            //perform copy operations:
            textArea.copy();
        }
        if(ae.getSource() == paste){
            //perform paste operation:
            textArea.paste();
        }
        if(ae.getSource() == selectAll){
            //perform select all operations:
            textArea.selectAll();
        }
        if(ae.getSource() == close){
            //perform close editor operations:
            System.exit(0);
        }
        if(ae.getSource() == openFile){
            //open a file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            //if we have clicked on open button
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                //Getting selected file
                File file = fileChooser.getSelectedFile();
                //get the path of the selected file
                String filePath = file.getPath();
                try{
                    //Initialize file reader with our filePath
                    FileReader fileReader = new FileReader(filePath);
                    //Initialize Buffered Reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "" , output = "";
                    //Read contents of file line by line
                    while((intermediate = bufferedReader.readLine())!=null){
                        output+=intermediate+"\n";
                    }
                    //set the output String to textArea
                    textArea.setText(output);
                }catch (IOException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
            }
        }
        if(ae.getSource() == saveFile){
            //Initialize file picker
            JFileChooser fileChooser = new JFileChooser();
            //Get  choose option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            //check if we clicked on save button
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                //create a new file with chosen directory path and file name
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    //Initialize file writer
                    FileWriter fileWriter = new FileWriter(file);
                    //Initialize BufferWriter
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    //write contents of text area to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();

                }
                catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
        if(ae.getSource() == newFile){
            TextEditor newTextEditor = new TextEditor();
        }
    }

    public static void main(String[] args) {

        TextEditor editor = new TextEditor();
    }
}