/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyzz;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author balvinder
 */
public class FXMLDocumentController implements Initializable {
    Boolean visible = true;
    @FXML
    private Button button ;
    @FXML
    private Button login;
    @FXML
    private TextField rollNo;
    @FXML
    private PasswordField password;
    
    
    @FXML
    private void handleLoginAction(ActionEvent event)
    {
        String roll = rollNo.getText();
        String pass = password.getText();
        
        System.out.println(roll);
        System.out.println(pass);
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
         con =    DriverManager.getConnection("jdbc:mysql://localhost/campus","root", "");
           String query = "select * from student where roll_no ='"+roll+"' and password = '"+pass+"'";
           String q = "UPDATE `student` SET `status`= 1 WHERE roll_no='"+roll+"'";
           Statement stmt = null;
           stmt = con.createStatement();
           stmt.executeUpdate(q);
           PreparedStatement statement = con.prepareStatement(query);
           
           ResultSet rs = statement.executeQuery();
           while(rs.next())
           {
            
            System.out.println(rs.getString(3));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Student_page.fxml"));
        Stage stage = (Stage) login.getScene().getWindow();
       
        Scene scene = new Scene(loader.load());
                stage.setScene(scene);   
                

           }

        } catch (Exception ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
     @FXML
    private void handleCompanyAction(ActionEvent event)
    {
        System.out.println("hello");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        // TODO
    }    
    
}
