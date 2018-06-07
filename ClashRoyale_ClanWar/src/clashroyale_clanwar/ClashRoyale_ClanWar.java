/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clashroyale_clanwar;

import clashroyale_clanwar.ClanInformation.Member;
import java.util.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import okhttp3.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.sql.*;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Kevin Lin
 */
public class ClashRoyale_ClanWar extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Clan War v1.0");
        
        TabPane tabPane=new TabPane();
        
        Tab memberTab=new Tab("Clan Members");
        Tab warTab=new Tab("Clan War Stats");
        Tab updateTab=new Tab("Update Stats");
        
        tabPane.getTabs().addAll(memberTab,warTab,updateTab);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        
        
        ResourceBundle reader=ResourceBundle.getBundle("clashroyale_clanwar/config");
        String apiKey=reader.getString("key");
        
        OkHttpClient client = new OkHttpClient();
        
        Request request = new Request.Builder()
        .url("https://api.royaleapi.com/clan/8G0U9U2")
        .get()
        .addHeader("auth", apiKey)
        .build();
        Response response = client.newCall(request).execute();
        
        Gson gson=new GsonBuilder().create();
        ClanInformation clanInfo=gson.fromJson(response.body().string(),ClanInformation.class);
        
        Member members[]=clanInfo.getMembers();
        
        Arrays.stream(members).forEach(e->System.out.println(e.getName()));
        
        /*Testing SQL Connection
        try{
            java.sql.Connection conn=DriverManager.getConnection(reader.getString("db.url"),reader.getString("db.username"),reader.getString("db.password"));
            Statement st=conn.createStatement();
            
            System.out.println("Connection Successful");
        } catch(SQLException ex){
            System.out.println("Connection Failed");
            ex.printStackTrace();
        }
        */
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));
        
        Image image = new Image(getClass().getResourceAsStream("cooltext.png"));
        Label clanLabel=new Label();
        clanLabel.setGraphic(new ImageView(image));
        
        memberTab.setContent(grid);
        
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

