/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyzz;

import java.awt.event.MouseEvent;
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
import javafx.scene.control.SelectionMode;
//import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
/**
 *
 * @author vignesh
 */
public class StudentUiController implements Initializable {
   
    
    @FXML
    Button description;
    @FXML
    Button job;
    @FXML
    Button applied;
    @FXML
     TableView<ModelTable> table;
    @FXML
     TableColumn<ModelTable,String> jid;
    @FXML
     TableColumn<ModelTable,String> jname;
    ObservableList<ModelTable> data = FXCollections.observableArrayList();
    @FXML
    private void goToJobPage(ActionEvent event) throws IOException
    {
        TablePosition pos = table.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        TableColumn col = pos.getTableColumn();
        String dat = (String) col.getCellObservableValue(row).getValue();
        System.out.println(dat);
        try {
           Connection con = DBConnector.getConnection();
           Class.forName("com.mysql.jdbc.Driver");
//           con = DriverManager.getConnection("jdbc:mysql://localhost/campus_placement","root", "");
           String query = "UPDATE `openings` SET `selected`='yes',`applied`='yes' WHERE name='"+dat+"'";
           Statement stmt = null;
           stmt = con.createStatement();
           stmt.executeUpdate(query);
        }
        catch (Exception ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("job_page.fxml"));
        Stage stage = (Stage) description.getScene().getWindow();
       
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }
//    private void handleCompanyAction(ActionEvent event)
//    {
//       
//    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        try {
           Connection con = DBConnector.getConnection();
           Class.forName("com.mysql.jdbc.Driver");
//           con = DriverManager.getConnection("jdbc:mysql://localhost/campus_placement","root", "");
           String query = "select * from openings where 1";
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
    public void showjob(ActionEvent e){
         try {
            Connection con = DBConnector.getConnection();
            Class.forName("com.mysql.jdbc.Driver");
//         con =    DriverManager.getConnection("jdbc:mysql://localhost/campus_placement","root", "");
           String query = "select * from openings where 1";
           PreparedStatement statement = con.prepareStatement(query);
         
           ResultSet rs = statement.executeQuery();

           data.clear();
           
           
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
    private void showapplied(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("applied_page.fxml"));
        Stage stage = (Stage) applied.getScene().getWindow();
       
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }
}
