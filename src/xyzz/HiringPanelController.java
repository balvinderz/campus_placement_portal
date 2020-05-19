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
import javafx.scene.control.*;
import java.sql.*;
import java.util.*;

/**
 *
 * @author User
 */
public class HiringPanelController implements Initializable {
    public String name;
    public int count=0;
    public ArrayList <String> arr = new ArrayList <String>();
    public int currentIn =0;
    @FXML
    private ListView l1;
    
    @FXML
    private Label CandName;
    
    @FXML
    private Label CheckScore;
    
    @FXML
    private Button butPrev;
        
    @FXML
    private Button butNext;
    
    @FXML
    private Button butList;
        
    @FXML
    private Button butAccept;
           
    @FXML
    private Button butReject;

    
    @FXML
    void listOfStudent(ActionEvent event) {
        if(l1.isVisible())
        {
            l1.setVisible(false);
            butList.setText("List All");
        }
        else{
            l1.setVisible(true);
            butList.setText("Close");
            if(count==0)
            {
                for(String nam : arr)
                    {
                        l1.getItems().add(nam);
                        count++;
                    } 
            }
        }
    }    
    @FXML
    void handleButtonAction(ActionEvent ae)
    {
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String con = "jdbc:mysql://localhost:3306/campus";
            String username = "root";
            String Pass = "";
            java.sql.Connection conn = DriverManager.getConnection(con,username,Pass);
            PreparedStatement st = conn.prepareStatement("select * from studentpending");
            ResultSet res = st.executeQuery();
            while(res.next())
            {
                name = String.valueOf(res.getString(2));
                arr.add(name);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            }
        // TODO
    }    
    
    
    
}
