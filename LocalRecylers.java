
package LocalRecylers;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.SpringLayout;
//
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// 
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class LocalRecylers extends Frame implements ActionListener, WindowListener
        
{
    // SOME BACKGROUND INFORMATION:
    // An IPAddresses.txt data file has been added to the directory of this update.
    // If using NetBeans as your IDE, this file might be copied into the IPAddressDatabase project
    //     folder (within your Netbeans project folder), but placed outside the project's src folder.
    //     Note: Your IPAddressDatabase project folder MAY be located within the default Netbeans
    //           project folder - which may be within your 'My Documents' folder.
    // The IPAddressDatabase application will load all the data from the data file and
    //     place a copy into RAM memory (or equivalent).  Then the first record (line/entry)
    //     of this data will be displayed on screen.
    // Three arrays are being introduced in this update to store the data from the data file
    //     within RAM memory.


    // DECLARATIONS --------------------------------------------------------------------------

    int maxEntries = 100;     // Global variable to define the maximum size of the 3 arrays.
    int numberOfEntries = 0;  // Global variable to remember how many actual entries are currently in the 3 arrays.
    int currentEntry = 0;     // Global variable to remember which entry in the arrays we are currently focused on.
    
    // Declare the 3 arrays for storing the PC/IP data in memory - each has a maximum size of
	//         maxEntries (currently equal to 100 entries)
    String[] BussinessName = new String[maxEntries];   
    String[] Address = new String[maxEntries];
    String[] Phone = new String[maxEntries];
    String[] WebSite = new String[maxEntries];
    String[] Recycles = new String[maxEntries];
    

    Label lblBussinessName, lblAddress, lblPhone, lblWebsite,lblRecyles, lblFind;
    TextField txtBussinessName, txtAddress, txtPhone,txtWebsite,txtRecycles, txtFind;
    Button btnNew, btnSave, btnDel, btnFind, btnExit, btnFirst, btnPrev, btnNext, btnLast;

    public static void main(String[] args)
    {
        Frame myFrame = new LocalRecylers();
        myFrame.setSize(500,400);
        myFrame.setLocation(400, 200);
        myFrame.setResizable(false);
        myFrame.setVisible(true);
    }

    public LocalRecylers()
    {
        setTitle("Local Recylers");
        setBackground(Color.yellow);

        SpringLayout myLayout = new SpringLayout();
        setLayout(myLayout);
        
	// Call the methods below to instantiate and place the various screen components

        LocateLabels(myLayout);
        LocateTextFields(myLayout);
        LocateButtons(myLayout);

        this.addWindowListener(this);
    }

    //------------------------------------------------------------------------------------------
    // Method that manages the adding of multiple Labels to the screen.
    // Each line requests the LocateALabel method to instantiate, add and place a specific Label 
 
    public void LocateLabels(SpringLayout myLabelLayout)

    {
        lblBussinessName = LocateALabel(myLabelLayout, lblBussinessName, "Buss.Name", 30, 25);
        lblAddress = LocateALabel(myLabelLayout, lblAddress, "Address", 30, 50);
        lblPhone = LocateALabel(myLabelLayout, lblPhone, "Phone", 30, 75);
        lblWebsite = LocateALabel(myLabelLayout, lblWebsite, "Website", 30, 100);
        lblRecyles = LocateALabel(myLabelLayout, lblRecyles, "Recyles", 30, 125);
        lblFind = LocateALabel (myLabelLayout, lblFind, "Find", 320,100);
        
    }

    /**
    * Method with low coupling and high cohesion 
    *    for adding individual labels:
    *    - reduces overall code, especially in the
    *         LocateLabels method.
    *    - makes this method re-usable with minimal
    *         adjustment as it is moved from one
    *         program to another.
    */

    public Label LocateALabel(SpringLayout myLabelLayout, Label myLabel, String  LabelCaption, int x, int y)
    {
        myLabel = new Label(LabelCaption);
        add(myLabel);        
        myLabelLayout.putConstraint(SpringLayout.WEST, myLabel, x, SpringLayout.WEST, this);
        myLabelLayout.putConstraint(SpringLayout.NORTH, myLabel, y, SpringLayout.NORTH, this);
        return myLabel;
    }
   

    //------------------------------------------------------------------------------------------
    // Method that manages the adding of multiple TextFields to the screen.
    // Each line requests the LocateATextField method to instantiate, add and place a specific TextField  

    public void LocateTextFields(SpringLayout myTextFieldLayout)
    {
        txtBussinessName  = LocateATextField(myTextFieldLayout, txtBussinessName, 20, 130, 25);
        txtAddress = LocateATextField(myTextFieldLayout, txtAddress, 20, 130, 50);
        txtPhone = LocateATextField(myTextFieldLayout, txtPhone, 20, 130, 75);
        txtWebsite = LocateATextField(myTextFieldLayout, txtWebsite, 20,130,100);
        txtRecycles = LocateATextField(myTextFieldLayout,txtRecycles,20,130,125);
        txtFind = LocateATextField(myTextFieldLayout, txtFind, 20, 320, 125);
    }

    /**
    * Method with low coupling and high cohesion 
    *    for adding individual textboxes:
    *    - reduces overall code, especially in the
    *         LocateTextFields method.
    *    - makes this method re-usable with minimal
    *         adjustment as it is moved from one
    *         program to another.
    */
    public TextField LocateATextField(SpringLayout myTextFieldLayout, TextField myTextField, int width, int x, int y)
    {
        myTextField = new TextField(width);
        add(myTextField);        
        myTextFieldLayout.putConstraint(SpringLayout.WEST, myTextField, x, SpringLayout.WEST, this);
        myTextFieldLayout.putConstraint(SpringLayout.NORTH, myTextField, y, SpringLayout.NORTH, this);
        return myTextField;
    }


    //------------------------------------------------------------------------------------------
    // Method that manages the adding of multiple Buttons to the screen.
    // Each line requests the LocateAButton method to instantiate, add and place a specific Button  

    public void LocateButtons(SpringLayout myButtonLayout)
    {
        btnNew = LocateAButton(myButtonLayout, btnNew, "New", 320, 25, 80, 25);
        btnSave = LocateAButton(myButtonLayout, btnSave, "Save", 320, 50, 80, 25);
        btnDel = LocateAButton(myButtonLayout, btnDel, "Delete", 320, 75, 80, 25);
        btnFind = LocateAButton(myButtonLayout, btnFind, "Find", 360, 100, 80, 25);
        btnExit = LocateAButton(myButtonLayout, btnExit, "Exit", 320, 220, 80, 25);
        btnFirst = LocateAButton(myButtonLayout, btnFirst, "|<", 140, 220, 30, 25);
        btnPrev = LocateAButton(myButtonLayout, btnPrev, "<", 180, 220, 30, 25);
        btnNext = LocateAButton(myButtonLayout, btnNext, ">", 220, 220, 30, 25);
        btnLast = LocateAButton(myButtonLayout, btnLast, ">|", 260, 220, 30, 25);
    }

    /**
    * Method with low coupling and high cohesion 
    *    for adding individual buttons:
    *    - reduces overall code, especially in the
    *         LocateButtons method.
    *    - makes this method re-usable with minimal
    *         adjustment as it is moved from one
    *         program to another.
    */

    public Button LocateAButton(SpringLayout myButtonLayout, Button myButton, String  ButtonCaption, int x, int y, int w, int h)
    {    
        myButton = new Button(ButtonCaption);
        add(myButton);

        // Add an ActionListener to each button.

        myButton.addActionListener(this);
        myButtonLayout.putConstraint(SpringLayout.WEST, myButton, x, SpringLayout.WEST, this);
        myButtonLayout.putConstraint(SpringLayout.NORTH, myButton, y, SpringLayout.NORTH, this);
        myButton.setPreferredSize(new Dimension(w,h));
        return myButton;
    }
  
// Set up the structure for adding the code later that will be required for each button.
    //     IE: to respond to user action events, such as clicking the New button.

   public void actionPerformed(ActionEvent e)
    {
        // BUTTON FIRST

        if(e.getSource() == btnFirst)
        {
        }

        // BUTTON PREVIOUS

        if(e.getSource() == btnPrev)
        {
        }

        // BUTTON NEXT

        if (e.getSource()== btnNext)
        {
        }

        // BUTTON LAST

        if(e.getSource() == btnLast)
        {
        }

        // BUTTON NEW

        if(e.getSource() == btnNew)
        {
            // This is a temporary line of code so we can test that
            //      this new actionPerformed method is working.

            txtBussinessName.setText("New button may be clicked...");
        }

        // BUTTON SAVE

        if(e.getSource() == btnSave)
        {
        }

        // BUTTON DELETE

        if(e.getSource()== btnDel)
        {
        }

        // BUTTON FIND

        if(e.getSource() == btnFind)
        {   
        }
        
        // BUTTON EXIT

        if(e.getSource() == btnExit)
        {
            // Exit the Program

            System.exit(0);
        }
           
    }

    // Manage responses to the various Window events

    public void windowClosing(WindowEvent we)
    {
        // Call the method below that writes the data back to the data file
        writeFile();
        System.exit(0);
    }

    public void windowIconified(WindowEvent we)
    {
    }

    public void windowOpened(WindowEvent we)
    {
        // Call the method below that reads the data from the data file (when the Frame first opens)

        readFile();

	// Display the first data entry (record) in the Frame

        displayEntry(currentEntry);
    }

    public void windowClosed(WindowEvent we)
    {
    }

    public void windowDeiconified(WindowEvent we)
    {
    }

    public void windowActivated(WindowEvent we)
    {
    }

    public void windowDeactivated(WindowEvent we)
    {
    }

// NEW METHODS: --------------------------------------------------------------------------------

    // Display the required data entry (record) in the Frame
    // The calling method must specify the number (index) of the entry that this
    //     method needs to currently display on screen.

    public void displayEntry(int index)
    {
        // Take the required entry from the PCName array and display it
        //      in the txtPCName TextField.

        txtBussinessName.setText(BussinessName[index]);
        txtAddress.setText(Address[index]);
        txtPhone.setText(Phone[index]);
        txtRecycles.setText(Recycles[index]);
        txtWebsite.setText(WebSite[index]);
    }

    
    // Take the current record displayed on screen and save it back into the 'currentEntry' position
    //      of the 3 arrays.

    public void saveEntry(int index)
    {
        // Take the current entry in the txtPCName TextField (on screen) and copy it 
        //      into the appropriate (currentEntry) position of the PCName array.

        BussinessName[index] = txtBussinessName.getText();
        Address[index] = txtAddress.getText();
        Phone[index] = txtPhone.getText();
        Recycles[index] = txtRecycles.getText();
        WebSite[index] = txtWebsite.getText();

		
        // (If required) Call the method below that writes the data back to the data file.
        writeFile();
    }

        
    // Read in the data from the data file - IPAddresses.txt - one line at a time and store in the 3 arrays.
    // Remember the number of entries read in, in the global variable: numberOfEntries.

    public void readFile()
    {
        // Try to read in the data and if an exception occurs go to the Catch section 

        try
        {
            // Set up vaious streams for reading in the content of the data file.

            FileInputStream fstream = new FileInputStream("BussinessName.txt");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            
            int i = 0;   // i is used as the line counter

            String line; // line is used to temporarily store the line read in from the data file
			
            // Read a line from the data file into the buffer and then check whether
            //      it is null.  The while loop continues until a line read in is null.

            while ((line = br.readLine()) != null)
            {
                // Split the line of data (from the text file) and put each entry into the
                //                                     temporary array - temp[]

                String[] temp = line.split(",");

                // Save each entry into its respective array:

                BussinessName[i] = temp[0];      //Takes the first entry in temp and puts it in the PCName array at the current location
                Address[i] = temp[1];        //Takes the second entry in temp and puts it in the PCID array at the current location
                Phone[i] = temp[2]; //Takes the third entry in temp and puts it in the IPAddress array at the current location
                WebSite[i] = temp[3];
                Recycles[i] = temp[4];
                
                
                i++;  // Increment i so we can keep a count of how many entries have been read in.
            }

            numberOfEntries = i;   // Set numberOfEntries equal to i, so as to remember how many entries are now in the arrays 

            br.close();            // Close the BufferedReader
            in.close();            // Close the DataInputStream
            fstream.close();       // Close the FileInputStream
        }
        catch (Exception e)
        {
            // If an exception occurs, print an error message on the console.

            System.err.println("Error Reading File: " + e.getMessage());
        }
    }

    
    // Write the data back out to the data file - one line at a time
    // Note: You may wish to use a different data file name while initially
    //       developing, so as not to accidently corrupt your input file.

    public void writeFile()
    {
        // Try to print out the data and if an exception occurs go to the Catch section 

        try
        {
            // After testing has been completed, replace the hard-coded filename: "IPAddresses_New.txt"
            //       with the parameter variable: fileName 
            // Set up a PrintWriter for printing the array content out to the data file.

            PrintWriter out = new PrintWriter(new FileWriter("BussinessName_New.txt"));
            
            // Print out each line of the array into your data file.
            // Each line is printed out in the format:  PCName,PCID,IPAddress

            for(int m = 0; m < numberOfEntries; m++){
                out.println(BussinessName[m] +"," +Address[m] + "," + Phone[m] + WebSite[m] + "," + Recycles[m]);
            }

            // Close the printFile (and in so doing, empty the print buffer)

             out.close();
        }
        catch (Exception e)
        {
            // If an exception occurs, print an error message on the console.

            System.err.println("Error Writing File: " + e.getMessage());
        }
    }
  
}

// end.
