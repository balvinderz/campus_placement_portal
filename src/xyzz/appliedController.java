/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyzz;

import javafx.application.Application;
import java.io.IOException;
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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 *
 * @author USER
 */
public class appliedController implements Initializable{
    @FXML
    Button description;
    @FXML
    Button open;
    @FXML
    Button app;
    @FXML
     TableView<ModelTable> table;
    @FXML
     TableColumn<ModelTable,String> jid;
    @FXML
     TableColumn<ModelTable,String> jname;
    ObservableList<ModelTable> data = FXCollections.observableArrayList();
    
    @FXML
    public void job1(ActionEvent e) throws IOException{
        try {
           Connection con = DBConnector.getConnection();
           Class.forName("com.mysql.jdbc.Driver");
//           con = DriverManager.getConnection("jdbc:mysql://localhost/campus_placement","root", "");
           String query = "UPDATE `openings` SET `selected`='no' WHERE selected='yes'";
           Statement stmt = null;
           stmt=con.createStatement();
           stmt.executeUpdate(query);
        }
        catch (Exception ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Student_page.fxml"));
        Stage stage = (Stage) open.getScene().getWindow();
       
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
}
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
         try {
           Connection con = DBConnector.getConnection();
           Class.forName("com.mysql.jdbc.Driver");
//           con = DriverManager.getConnection("jdbc:mysql://localhost/campus_placement","root", "");
           String query = "select * from openings where applied='yes'";
           PreparedStatement statement = con.prepareStatement(query);
          
           ResultSet rs = statement.executeQuery();
           while(rs.next()){
               data.add(new ModelTable(rs.getString("id"),rs.getString("name")));
            }
        }
        catch (Exception ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        jid.setCellValueFactory(new PropertyValueFactory<>("id"));
        jname.setCellValueFactory(new PropertyValueFactory<>("name"));
        table.setItems(data);
    }
    @FXML
    public void goToRound(ActionEvent e)throws IOException{
        //vivek interface
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Stages.fxml"));
        Stage stage = (Stage) description.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }
}
