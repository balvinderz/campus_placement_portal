/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyzz;

import java.sql.*;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class quizController implements Initializable {
    public int marks=0;
    public int ti=0;
    @FXML
    private Button next;
    @FXML
    private Button submit;
    @FXML
    private RadioButton rb1;
    @FXML
    private RadioButton rb3;
    @FXML
    private RadioButton rb2;
    @FXML
    private RadioButton rb4;
    @FXML
    private TextField qno;
    @FXML 
    private Label lab;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Connection con = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/campus","root", "");
            PreparedStatement st = con.prepareStatement("select * from test");
            ResultSet res = st.executeQuery();
            res.next();
            qno.setText(res.getString(1));
            lab.setText(res.getString(2));
            rb1.setText(res.getString(3));
            rb2.setText(res.getString(4));
            rb3.setText(res.getString(5));
            rb4.setText(res.getString(6));
          
            
        }
        catch(Exception ex){
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }    

    @FXML
    private void handleNextAction(ActionEvent event) {
        Connection con = null;
        int i = 0;
        Boolean x =true;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/campus","root", "");
            PreparedStatement st = con.prepareStatement("select * from test");
            ResultSet res = st.executeQuery();
            res.next();
            while(i<(ti)){
            res.next();
            i++;
        }
            
            
            System.out.println(res.getString(7));
            if(rb1.isSelected()==true &&rb1.getText().equals(res.getString(7))){
                marks++;
            }
            else if(rb2.isSelected()==true &&rb2.getText().equals(res.getString(7))){
                marks++;
            }
            else if(rb3.isSelected()==true &&rb3.getText().equals(res.getString(7))){
                marks++;
            }
            else if(rb4.isSelected()==true &&rb4.getText().equals(res.getString(7))){
                marks++;
            }
            System.out.println(marks);
            ti++;
            res.next();
            qno.setText(res.getString(1));
            lab.setText(res.getString(2));
            rb1.setText(res.getString(3));
            rb2.setText(res.getString(4));
            rb3.setText(res.getString(5));
            rb4.setText(res.getString(6));
            x=res.next();
            if(x==false){
                next.setDisable(true);
            }
        }
        catch(Exception ex){
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void handleSubmitAction(ActionEvent event) {
        Connection con = null;
        int i = 0;
        Boolean x =true;
        String status;
        String roll;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/campus","root", "");
            String q1 = "SELECT `roll_no` FROM `student` WHERE `status`=1";
            Statement stmt;
            stmt = con.createStatement();
            PreparedStatement s = con.prepareStatement(q1);
            Statement stmt1;
            stmt1 = con.createStatement();
            PreparedStatement st = con.prepareStatement("select * from test");
            ResultSet res = st.executeQuery();
            ResultSet r = s.executeQuery();
            r.next();
            roll = r.getString(1);
            System.out.println(roll);
            res.next();
            while(i<(ti)){
            res.next();
            i++;
        }
            
            
            System.out.println(res.getString(7));
            if(rb1.isSelected()==true &&rb1.getText().equals(res.getString(7))){
                marks++;
            }
            else if(rb2.isSelected()==true &&rb2.getText().equals(res.getString(7))){
                marks++;
            }
            else if(rb3.isSelected()==true &&rb3.getText().equals(res.getString(7))){
                marks++;
            }
            else if(rb4.isSelected()==true &&rb4.getText().equals(res.getString(7))){
                marks++;
            }
            System.out.println(marks);
            ti++;
            if(marks>=2){
                stmt.executeUpdate("UPDATE `student` SET `test`='accp' WHERE roll_no='"+roll+"'");
            }
            else if(marks<2)
                stmt.executeUpdate("UPDATE `student` SET `test`='rej' WHERE roll_no='"+roll+"'");
//            stmt1.executeUpdate("UPDATE `student` SET `status`=0 WHERE roll_no='"+roll+"'");
         String q = "update openings set selected='no',applied='no' where applied='yes'";
          Statement snew = null;
           snew = con.createStatement();
         snew.executeUpdate(q);
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("Stages.fxml"));
        Stage stage = (Stage) submit.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        }
        catch(Exception ex){
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
