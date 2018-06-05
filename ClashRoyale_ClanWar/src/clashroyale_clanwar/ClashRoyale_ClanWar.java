/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clashroyale_clanwar;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Kevin Lin
 */
public class ClashRoyale_ClanWar extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Clan War v1.0");
        
        TabPane tabPane=new TabPane();
        
        Tab memberTab=new Tab("Clan Members");
        Tab warTab=new Tab("Clan War Stats");
        Tab updateTab=new Tab("Update Stats");
        
        tabPane.getTabs().addAll(memberTab,warTab,updateTab);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        
        
        Scene scene=new Scene(tabPane,800,800);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

/*
curl --header "auth: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NzgzLCJpZGVuIjoiMTgzOTk5NzgxMzAxOTExNTUyIiwibWQiOnt9LCJ0cyI6MTUyODIzMjAwMTAwOX0.fpFIZ5hLUAS8Fa_6OUyP5uhE60EwUeHNdV5jDWC3Lls" https://api.royaleapi.com/clan/8G0U9U2
*/