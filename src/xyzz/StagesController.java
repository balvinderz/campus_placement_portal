/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyzz;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;



public class StagesController implements Initializable {
    public String roll,nam,teststat,gdstat,pistat,fstat;
    
    @FXML private Label disname;
    
    @FXML private Label rollNo;
    
    @FXML private Label testStatus;
    
    @FXML private Label gdStatus;
    
    @FXML private Label piStatus;

    @FXML private Label finalStatus;

    @FXML public Button test;
    
    @FXML private Button gd;
    
    @FXML private Button pi;
    
    @FXML
    void handlethis(ActionEvent ae)
    {
        System.out.println("null");
    }
    @FXML
    void handletest(ActionEvent e)throws IOException{
         FXMLLoader loader = new FXMLLoader(getClass().getResource("quizController1.fxml"));
        Stage stage = (Stage) testStatus.getScene().getWindow();
       
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }
    
//    @FXML
//    void display()
//    {
//        
//
//    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    String roll;
        String name;
        String teststat,gdstat,pistat;
        Connection con=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/campus","root", "");
            String q1 = "SELECT * FROM `student` WHERE `status`=1";
            Statement s=null;
            s=con.createStatement();
            PreparedStatement stmt = con.prepareStatement(q1);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            roll = rs.getString("roll_no");
            name = rs.getString(3);
            System.out.println(roll);
                        System.out.println(name);

            rollNo.setText(String.valueOf(roll));
            disname.setText(String.valueOf(name));
            teststat = rs.getString(6);
//            gdstat = rs.getString(7);
//            pistat = rs.getString(8);
            testStatus.setText(String.valueOf(teststat));
            gd.setDisable(true);
            pi.setDisable(true);
            if(teststat.equals("accp"))
        {
            s.executeUpdate("UPDATE `student` SET `status`=0 WHERE roll_no='"+roll+"'");
            test.setStyle("-fx-background-color: #008000");
            test.setDisable(true);
            gd.setDisable(false);
        }
            else if(teststat.equals("rej")){
            test.setStyle("-fx-background-color: #FF0000");
            test.setDisable(true);
            }
//        if(gdstat.equals("accp"))
//        {
//            gd.setDisable(false);
//        }
//        if(pistat.equals("accp"))
//        {
//            pi.setDisable(false);
//        }
//        if(teststat.equals("accp")&&gdstat.equals("accp")&&pistat.equals("accp")){
//            finalStatus.setText("HIRED");
//        }
      }
        catch(Exception ex){
             Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
