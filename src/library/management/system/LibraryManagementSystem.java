/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.management.system;

import java.io.BufferedReader; //to read data from files
import java.io.File;             //to make files
import java.io.FileNotFoundException;  //to make an exception in case of deleting the file
import java.io.FileOutputStream;       //to make a stream file
import java.io.FileReader;              //to read data from files           
import java.io.FileWriter;              //to write in files
import java.io.IOException;             //to make IO Exception
import java.io.PrintWriter;             //to print data
import java.util.Scanner;               //used in reading from files
import java.util.logging.Level;         //used in handlling IO Exceptions
import java.util.logging.Logger;        //used in handlling IO Exceptions
import javafx.application.Application;  //to create application
import javafx.scene.Scene;              //to create Scences
import javafx.scene.control.Alert;      //to create Alerts
import javafx.scene.control.Button;     //to create Buttons
import javafx.scene.control.ComboBox;   //to create Combo Box
import javafx.scene.control.Label;      //to create labels
import javafx.scene.control.TextField;  //to create text fields
import javafx.scene.layout.HBox;        //to create HBoxes
import javafx.scene.layout.Pane;        //to Create pane
import javafx.scene.layout.VBox;        //to create VBoxes
import javafx.stage.Stage;              //to create the stage


/**
 *
 * @author ahmed sameh
 */

public class LibraryManagementSystem extends Application {
    
    //making objects 
    Scene WelcomeScene,AddBooksScene,DeleteBooksScene,SearchBooksScene,IssueBooksScene;
    Label LabelWelcome,l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11;
    Button Back1,Back2,Back3,Back4,AddBooks,DeleteBooks,SearchBooks,
           IssueBooks,Add,Delete,View,Search,Record,ViewRecords,DeleteRecord,ViewBooks;
    VBox v1,v2,v3,v4,v5;
    HBox h1,h2,h3,h4,h5,h6,h7,h8,h9,h10,h11,h12,h13,h14,h15,h16;
    Pane p1,p2,p3,p4,p5;
    ComboBox cb1,cb2,cb3,cb4;
    TextField t1,t2,t3,t4,t5;

    @Override
    public void start(Stage primaryStage)throws Exception  {
        
        //call the methods
        TheWelcomeScene(primaryStage);         
        TheAddBooksScene(primaryStage);            
        TheDeleteBooksScene(primaryStage);     
        TheSearchBooksScene(primaryStage);     
        TheIssueBooksScene(primaryStage);      
        
       //Set the title and scene of the stage
       primaryStage.setTitle("library");
       primaryStage.setScene(WelcomeScene);
       primaryStage.show();
       primaryStage.setResizable(false);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);//lauch the appplication
    }
    
    public void TheWelcomeScene(Stage primaryStage)throws Exception  //make a method
    {
        LabelWelcome=new Label(" Welcome To Our Library"
                + "\nWhat Do You Want To Do ?");
        //intialize and handle the buttons
        Back1=new Button("Back To Main Menu");
        Back1.setOnAction(e->primaryStage.setScene(WelcomeScene));
        Back2=new Button("Back To Main Menu");
        Back2.setOnAction(e->primaryStage.setScene(WelcomeScene));
        Back3=new Button("Back To Main Menu");
        Back3.setOnAction(e->primaryStage.setScene(WelcomeScene));
        Back4=new Button("Back To Main Menu");
        Back4.setOnAction(e->primaryStage.setScene(WelcomeScene));
        AddBooks=new Button("Add Books");
        AddBooks.setOnAction(e->primaryStage.setScene(AddBooksScene));
        DeleteBooks=new Button("Delete Books");
        DeleteBooks.setOnAction(e->primaryStage.setScene(DeleteBooksScene));
        SearchBooks=new Button("Search Books");
        SearchBooks.setOnAction(e->primaryStage.setScene(SearchBooksScene));
        IssueBooks=new Button("Issue Books");
        IssueBooks.setOnAction(e->primaryStage.setScene(IssueBooksScene));
                
        h15=new HBox(20);//intialize the HBox and add controls to it
        h15.getChildren().addAll(AddBooks,DeleteBooks,SearchBooks,IssueBooks);
        
        v1=new VBox(50); //intialize the VBox and add items to it 
        v1.getChildren().addAll(LabelWelcome,h15);
        v1.setLayoutX(20);   //set the position of the VBox
        v1.setLayoutY(80);
        
        p1=new Pane(v1);  //intialize a pane
        WelcomeScene=new Scene(p1,500,300);
        
    }
    
    public void TheAddBooksScene(Stage primaryStage) throws Exception  //make a method
    {
        
        l1=new Label("Departement"); //intialize a label
        
        cb1=new ComboBox<>();     //intialize a combo box
        cb1.getItems().addAll("Civil","Electrical","Mechanical");
        cb1.setValue("Electrical");
        
        l2=new Label("Book's name");
        
        t1=new TextField(); //intialize text field
        
        l11=new Label("View All The Stored Books");
        
        ViewBooks=new Button("View Books");  //intialiize button
        
        h16=new HBox(40);  //intialize HBox
        h16.getChildren().addAll(l11,ViewBooks);
        
        Add=new Button(" Add Book ");
                
        h1=new HBox(40);
        h1.getChildren().addAll(l1,cb1);
        
        h2=new HBox(40);
        h2.getChildren().addAll(l2,t1);
        
        h3=new HBox(40);
        h3.getChildren().addAll(Add,Back1);
        
        v2=new VBox(30);
        v2.getChildren().addAll(h1,h2,h16,h3);
        v2.setLayoutX(90);
        v2.setLayoutY(60);
        
        p2=new Pane(v2);    //intialize a pane
        AddBooksScene=new Scene(p2,500,300);
                
        //handle the add button
        Add.setOnAction(e->{
        try{
        PrintWriter p1=new PrintWriter(new FileOutputStream(new File ("f1.txt"),true));  //crate a PrintWriter and fileoutstream
        p1.println("Department : "+cb1.getValue().toString()+" ,  Book's Name : "+t1.getText());
        
        p1.close();//close the fille
        
        if ("".equals(t1.getText()))  //make an exception
                throw new Exception("please choose the department then enter the Book's name");
       
        Alert alert1= new Alert (Alert.AlertType.INFORMATION); //create an alert and intialize it
            alert1.setContentText("saved Succssesfully");
            alert1.showAndWait();
                    
        }catch(FileNotFoundException ex){               //catch the FileNotFoundException 
                    Alert alert2= new Alert (Alert.AlertType.INFORMATION);
                    alert2.setContentText("The Book's file isn't exist");
                    alert2.showAndWait();
        }catch (Exception lp) {   //ccatch an Excption
            Alert alert3= new Alert (Alert.AlertType.INFORMATION);
                    alert3.setContentText(lp.getMessage());
                    alert3.showAndWait();
        }
        }); 
        
        ViewBooks.setOnAction(e->{
        try {  //handle The ViewBooks Button
            String line="";  //variable declaration
            String st="";
            Scanner s=new Scanner(new File ("f1.txt"));  //intialize scanner object
            Alert alert= new Alert (Alert.AlertType.INFORMATION);
            
            while(s.hasNextLine())  //read the lines of the file
            {
              line=s.nextLine();
              if(line.contains("Department : "+cb1.getValue().toString()))
                  st+=line+"\n\n";
            }
            
            alert.setHeaderText("Found Books :");  //printing the data
            alert.setContentText(st+"\n");
            alert.showAndWait();
            s.close();
        }catch(FileNotFoundException ex){  //catch file not found exception
                    Alert alert7= new Alert (Alert.AlertType.INFORMATION);
                    alert7.setContentText("The Book's file isn't exist");
                    alert7.showAndWait();
        }
            
        });
        
    }
    
    public void TheDeleteBooksScene(Stage primaryStage)throws Exception  //make a method
    {
        l3=new Label("Departement"); //intialize a label
        
        cb2=new ComboBox<>();        //intialize a combo box
        cb2.getItems().addAll("Civil","Electrical","Mechanical");
        cb2.setValue("Electrical");
        
        l4=new Label("Book's name");
        
        t2=new TextField();  //intialize text filed
        
        l5=new Label("View The Books in Department");
        
        View=new Button("View");   //intialize the view button
        Delete=new Button("Delete");
        
        h4=new HBox(40);    //intialize a HBox
        h4.getChildren().addAll(l3,cb2);
        
        h5=new HBox(30);
        h5.getChildren().addAll(l4,t2);
        
        h6=new HBox(40);
        h6.getChildren().addAll(l5,View);
        
        h7=new HBox(40);
        h7.getChildren().addAll(Delete,Back2);
        
        v3=new VBox(30);    //intialize a VBox 
        v3.getChildren().addAll(h4,h5,h6,h7);
        v3.setLayoutX(90);
        v3.setLayoutY(60);
        
        p3=new Pane(v3);   //intialize a pane
        DeleteBooksScene=new Scene(p3,500,300);
        
        Delete.setOnAction(e->{  //handle the delete button
       
               try{
                   File oldFile =new File("f1.txt");  //create files
                   File newFile =new File("temp.txt");
                   BufferedReader br=new BufferedReader(new FileReader("f1.txt"));  //create buffered reader
                   PrintWriter pw=new PrintWriter (new FileWriter("temp.txt"));     //create printwriter
                   String s;
                   while((s=br.readLine())!=null) //read the lines of the file
                   {
                       if(false==s.startsWith("Department : "+cb2.getValue().toString()+" ,  Book's Name : "+t2.getText()))
                           pw.println(s);
                       if ("".equals(t2.getText()))//create an exception
                throw new Exception("please choose the department then enter the Book's name");
                   }
                   Alert alert4= new Alert (Alert.AlertType.INFORMATION);  //create an alert
                    alert4.setContentText("The book removed Succsesfully");
                    alert4.showAndWait();
                   br.close();
                   pw.close();
               
               oldFile.delete();  //delete the oldfile
               File dump =new File("f1.txt");
               newFile.renameTo(dump);

  
            
               }catch(Exception q)  //catch an exception
               {
                    Alert alert5= new Alert (Alert.AlertType.INFORMATION);
                    alert5.setContentText(q.getMessage());
                    alert5.showAndWait();               }
       });
        
        View.setOnAction(e->{  //handle the view button
        try {
            String line="";
            String st="";
            Scanner s=new Scanner(new File ("f1.txt"));
            Alert alert6= new Alert (Alert.AlertType.INFORMATION);
            
            
            
            while(s.hasNextLine())  //read the lines of the file
            {
              line=s.nextLine();
              if(line.contains("Department : "+cb2.getValue().toString()))
                  st+=line+"\n\n";
            }
            
            alert6.setHeaderText("Found Books :");
            alert6.setContentText(st+"\n");
            alert6.showAndWait();
            s.close();
        }catch(FileNotFoundException ex){   //catch file not found exception
                    Alert alert7= new Alert (Alert.AlertType.INFORMATION);
                    alert7.setContentText("The Book's file isn't exist");
                    alert7.showAndWait();
        }
        });
        
    }
    
    public void TheSearchBooksScene(Stage primaryStage)throws Exception //make a method
    {
        l6=new Label("Departement"); //intialize a label
        
        cb3=new ComboBox<>();        //intialize a combo box
        cb3.getItems().addAll("Civil","Electrical","Mechanical"); //adding items to the combo box
        cb3.setValue("Electrical");
        
        l7=new Label("Book's name");  //create a lablel
        
        t3=new TextField();    //create a text field
        
        Search=new Button("Search");   //intialize a button
        
        h8=new HBox(40);  //intialize a HBox and add items to it
        h8.getChildren().addAll(l6,cb3);
        
        h9=new HBox(40);
        h9.getChildren().addAll(l7,t3);
        
        h10=new HBox(40);
        h10.getChildren().addAll(Search,Back3);
        
        v4=new VBox(30);   //intialize a VBox and add items to it
        v4.getChildren().addAll(h9,h10);
        v4.setLayoutX(90);
        v4.setLayoutY(60);
        
        p4=new Pane(v4);    //create a pane
        SearchBooksScene=new Scene(p4,500,300);
        
        Search.setOnAction(e->{
        try {
            String line="";
            String st="";
            Scanner s=new Scanner(new File ("f1.txt"));
            Alert alert8= new Alert (Alert.AlertType.INFORMATION);
            
            if ("".equals(t3.getText())) //throws an exception
                throw new Exception("please choose the department then enter the Book's name");
            while(s.hasNextLine()) //read the lines of the file
            {
              line=s.nextLine();
              if(line.contains("Name : "+t3.getText()))
                  st+=line+"\n\n";
            }
            
            alert8.setHeaderText("Found Books :");
            alert8.setContentText(st+"\n");
            alert8.showAndWait();
            s.close();
        }catch(FileNotFoundException ex){  //catch a file not found exception
                    Alert alert9= new Alert (Alert.AlertType.INFORMATION);
                    alert9.setContentText("The Book's file isn't exist");
                    alert9.showAndWait();
        }   catch (IOException ex) {  //catch IO exception
                Logger.getLogger(LibraryManagementSystem.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch(Exception t){   //catch an exception
                Alert alert10= new Alert (Alert.AlertType.INFORMATION);
                alert10.setContentText(t.getMessage());
                alert10.showAndWait();
            }
        });
    }
    
    public void TheIssueBooksScene(Stage primaryStage)throws Exception  //make a method
    {
        l8=new Label("Student's Name");  //intialize a label
        t4=new TextField();             //intialize a text field
        l9=new Label("Departement");    //intialize a label
         
        cb4=new ComboBox<>();           //intialize a combo box
        cb4.getItems().addAll("Civil","Electrical","Mechanical");
        cb4.setValue("Electrical");
        
        l10=new Label("Book's name");
        t5=new TextField();
        
        Record=new Button("Record");  //intialize a button
        ViewRecords=new Button("View All Records");  
        DeleteRecord=new Button("Delete This Record");
        
        h11=new HBox(40);  //intialize HBox and add items to it
        h11.getChildren().addAll(l8,t4);
        
        h12=new HBox(40);
        h12.getChildren().addAll(l9,cb4);
        
        h13=new HBox(40);
        h13.getChildren().addAll(l10,t5);
        
        h14=new HBox(10);
        h14.getChildren().addAll(ViewRecords,Record,DeleteRecord,Back4);
        
        v5=new VBox(40);    //intialize VBox and add items to it
        v5.getChildren().addAll(h11,h12,h13,h14);
        v5.setLayoutX(20);
        v5.setLayoutY(40);
        
        p5=new Pane(v5);    //intialize Pane
        IssueBooksScene=new Scene(p5,550,300); //intialize scene
        
        Record.setOnAction(e->{
        try{
        PrintWriter p2=new PrintWriter(new FileOutputStream(new File ("f2.txt"),true));  //create new print writer
        //print data in the file
        p2.println("Student : "+t4.getText()+" ,  Department : "+cb4.getValue().toString()+" ,  Book's Name : "+t5.getText());  
        
        p2.close();
        
        if ("".equals(t4.getText())||"".equals(t5.getText())) //throws an exception
                throw new Exception("please enter Student's name then choose the Departmrnt then enter the Book's name");
       
        Alert alert11= new Alert (Alert.AlertType.INFORMATION); //create a new object
        alert11.setContentText("saved Succsesfully");
        alert11.showAndWait();
                    
        }catch(FileNotFoundException ex){ //catch new file not found exception
                    Alert alert12= new Alert (Alert.AlertType.INFORMATION);
                    alert12.setContentText("The contacts file isn't exist");
                    alert12.showAndWait();
        }   catch (Exception lp) { //catch an exception
            Alert alert13= new Alert (Alert.AlertType.INFORMATION);
                    alert13.setContentText(lp.getMessage());
                    alert13.showAndWait();
        }
        });  
        
        ViewRecords.setOnAction(e->{ //handle the view record Button
        try {
            String line="";
            String st="";
            Scanner s=new Scanner(new File ("f2.txt"));
            Alert alert14= new Alert (Alert.AlertType.INFORMATION);
            
            while(s.hasNextLine()) //read all the lines of the file
            {
              line=s.nextLine();
                  st+=line+"\n\n";
            }
            
            alert14.setHeaderText("Found Records :");
            alert14.setContentText(st+"\n");
            alert14.showAndWait();
            s.close();
        }catch(FileNotFoundException ex){ //catch file not found exception
                    Alert alert15= new Alert (Alert.AlertType.INFORMATION);
                    alert15.setContentText("The Book's file isn't exist");
                    alert15.showAndWait();
        }
            
        });
        
        DeleteRecord.setOnAction(e->{  //handle the Deleterecord Button
       
               try{
                   File oldFile2 =new File("f2.txt");  //Create new file (old file)
                   File newFile2 =new File("temp2.txt");
                   BufferedReader br2=new BufferedReader(new FileReader("f2.txt"));
                   PrintWriter pw2=new PrintWriter (new FileWriter("temp2.txt"));
                   String s;
                   while((s=br2.readLine())!=null) //check that the line isn't empty
                   {
                       if(false==s.startsWith("Student : "+t4.getText()+" ,  Department : "+cb4.getValue().toString()+" ,  Book's Name : "
                               +t5.getText()))
                           pw2.println(s);//print the lines
                       if ("".equals(t4.getText())||"".equals(t5.getText())) //throes an exception
                throw new Exception("please enter Student's name then choose the Departmrnt then enter the Book's name");
                   }
                   Alert alert16= new Alert (Alert.AlertType.INFORMATION);
                    alert16.setContentText("The record removed Succsesfully");
                    alert16.showAndWait();
                   br2.close();
                   pw2.close();
               
               oldFile2.delete(); //delete the old file
               File d =new File("f2.txt");
               newFile2.renameTo(d);

               }catch(Exception q) //catch an exception
               {
                    Alert alert17= new Alert (Alert.AlertType.INFORMATION);
                    alert17.setContentText(q.getMessage());
                    alert17.showAndWait();               }
       });
    }

}
