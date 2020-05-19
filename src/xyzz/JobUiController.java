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
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author vignesh
 */
public class JobUiController implements Initializable {
   
    
    @FXML
    Label title;
    @FXML
    Label desc;
    @FXML
    Label ctc;
    @FXML
    Label comp_name;
    @FXML
    Button applied;
    @FXML
    Label description;
    @FXML
    Label ctcText;
//    @FXML
//    Button back;
    
    ObservableList<ModelTable> data = FXCollections.observableArrayList();
//    private void handleCompanyAction(ActionEvent event)
//    {
//        System.out.println("Soja");
//    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
           Connection con = DBConnector.getConnection();
           Class.forName("com.mysql.jdbc.Driver");
//           con = DriverManager.getConnection("jdbc:mysql://localhost/campus_placement","root", "");
           String query = "SELECT * FROM openings WHERE selected='yes'";
           PreparedStatement statement = con.prepareStatement(query);
           ResultSet rs = statement.executeQuery();
           rs.next();
//           String val = comp_name.getText();
//           while(val==rs.getString(2)&& val!="Company Name"){
           System.out.println(rs.getString(2));
//           rs.next();
//        }
           String x = rs.getString(2);
           description.setText(rs.getString(6));
           comp_name.setText(x);
           ctcText.setText(rs.getString("ctc"));
        }
        catch (Exception ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    @FXML
    public void apply(ActionEvent e) throws IOException{
        try{
        Class.forName("com.mysql.jdbc.Driver");
                Connection con=null;

            con = DriverManager.getConnection("jdbc:mysql://localhost/campus","root", "");
            String checkQuery ="select * from applied where roll_no ='17CE1013' and id ='";
            String q1 = "insert into applied(roll_no,id) values('17CE1013',1)";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(q1);
           // ResultSet rs = stmt.executeQuery();
       
        }catch(Exception p)
        {
            System.out.println(String.valueOf(p));
        }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("applied_page.fxml"));
        Stage stage = (Stage) applied.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }

}
