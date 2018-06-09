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
import javafx.scene.text.Font;
import javafx.scene.text.Text;

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
        grid.setPadding(new Insets(0, 3, 0, 3));
        
        Image image=new Image(getClass().getResourceAsStream("Cool Text - Name 289069069603806.png"));
        Label nameLabel=new Label();
        nameLabel.setGraphic(new ImageView(image));
        grid.add(nameLabel,0,0);
        Text name=new Text(clanInfo.getName());
        name.setFont(Font.font("Arial",20));
        grid.add(name, 1, 0);
        
        image=new Image(getClass().getResourceAsStream("tag.png"));
        Label tagLabel=new Label();
        tagLabel.setGraphic(new ImageView(image));
        grid.add(tagLabel,0,1);
        Text tag=new Text(clanInfo.getTag());
        tag.setFont(Font.font("Arial",20));
        grid.add(tag, 1, 1);
        
        image=new Image(getClass().getResourceAsStream("description.png"));
        Label descriptionLabel=new Label();
        descriptionLabel.setGraphic(new ImageView(image));
        grid.add(descriptionLabel,0,2);
        Text description=new Text(clanInfo.getDescription());
        tag.setFont(Font.font("Arial",20));
        grid.add(description, 1, 2);
        
        image=new Image(getClass().getResourceAsStream("score.png"));
        Label scoreLabel=new Label();
        scoreLabel.setGraphic(new ImageView(image));
        grid.add(scoreLabel,0,3);
        Text score=new Text(clanInfo.getScore());
        score.setFont(Font.font("Arial",20));
        grid.add(score,1,3);
        
        image=new Image(getClass().getResourceAsStream("Required Trophies.png"));
        Label requiredLabel=new Label();
        requiredLabel.setGraphic(new ImageView(image));
        grid.add(requiredLabel,0,4);
        Text requiredScore=new Text(clanInfo.getRequiredScore());
        requiredScore.setFont(Font.font("Arial",20));
        grid.add(requiredScore,1,4);
        
        
        image=new Image(getClass().getResourceAsStream("count.png"));
        Label countLabel=new Label();
        countLabel.setGraphic(new ImageView(image));
        grid.add(countLabel,0,5);
        Text memberCount=new Text(clanInfo.getMemberCount());
        memberCount.setFont(Font.font("Arial",20));
        grid.add(memberCount,1,5);
        
        
        image=new Image(getClass().getResourceAsStream("type.png"));
        Label typeLabel=new Label();
        typeLabel.setGraphic(new ImageView(image));
        grid.add(typeLabel,0,6);
        
        image=new Image(getClass().getResourceAsStream("Cool Text - Location 289069565107223 (1).png"));
        Label locationLabel=new Label();
        locationLabel.setGraphic(new ImageView(image));
        grid.add(locationLabel,0,7);
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

