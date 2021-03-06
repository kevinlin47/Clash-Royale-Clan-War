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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
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
        name.setFont(Font.font("Arial",18));
        grid.add(name, 1, 0);
        
        image=new Image(getClass().getResourceAsStream("tag.png"));
        Label tagLabel=new Label();
        tagLabel.setGraphic(new ImageView(image));
        grid.add(tagLabel,0,1);
        Text tag=new Text(clanInfo.getTag());
        tag.setFont(Font.font("Arial",18));
        grid.add(tag, 1, 1);
        
        image=new Image(getClass().getResourceAsStream("description.png"));
        Label descriptionLabel=new Label();
        descriptionLabel.setGraphic(new ImageView(image));
        grid.add(descriptionLabel,0,2);
        Text description=new Text(clanInfo.getDescription());
        tag.setFont(Font.font("Arial",18));
        grid.add(description, 1, 2);
        
        image=new Image(getClass().getResourceAsStream("score.png"));
        Label scoreLabel=new Label();
        scoreLabel.setGraphic(new ImageView(image));
        grid.add(scoreLabel,0,3);
        Text score=new Text(clanInfo.getScore());
        score.setFont(Font.font("Arial",18));
        grid.add(score,1,3);
        
        image=new Image(getClass().getResourceAsStream("Required Trophies.png"));
        Label requiredLabel=new Label();
        requiredLabel.setGraphic(new ImageView(image));
        grid.add(requiredLabel,0,4);
        Text requiredScore=new Text(clanInfo.getRequiredScore());
        requiredScore.setFont(Font.font("Arial",18));
        grid.add(requiredScore,1,4);
        
        
        image=new Image(getClass().getResourceAsStream("count.png"));
        Label countLabel=new Label();
        countLabel.setGraphic(new ImageView(image));
        grid.add(countLabel,0,5);
        Text memberCount=new Text(clanInfo.getMemberCount());
        memberCount.setFont(Font.font("Arial",18));
        grid.add(memberCount,1,5);
        
        
        image=new Image(getClass().getResourceAsStream("type.png"));
        Label typeLabel=new Label();
        typeLabel.setGraphic(new ImageView(image));
        grid.add(typeLabel,0,6);
        Text type=new Text(clanInfo.getType());
        type.setFont(Font.font("Arial",18));
        grid.add(type,1,6);
        
        image=new Image(getClass().getResourceAsStream("Cool Text - Location 289069565107223 (1).png"));
        Label locationLabel=new Label();
        locationLabel.setGraphic(new ImageView(image));
        grid.add(locationLabel,0,7);
        Text location=new Text(clanInfo.getLocation().get("name"));
        location.setFont(Font.font("Arial",18));
        grid.add(location,1,7);
        
        TableView memberTable=new TableView();
        memberTable.setEditable(false);
        TableColumn nameCol=new TableColumn("Name");
        TableColumn rankCol=new TableColumn("Rank");
        TableColumn donationsCol=new TableColumn("Donations");
        TableColumn scoreCol=new TableColumn("Trophies");
        nameCol.setSortable(false);
        rankCol.setSortable(false);
        donationsCol.setSortable(false);
        scoreCol.setSortable(false);
        
        ObservableList<Member> data=FXCollections.observableArrayList();
        
        for (int i=0;i<members.length;++i){
            data.addAll(members[i]);
        }
        
        rankCol.setCellValueFactory(
            new PropertyValueFactory<Member,String>("rank"));
        nameCol.setCellValueFactory(
            new PropertyValueFactory<Member,String>("name"));
        donationsCol.setCellValueFactory(
            new PropertyValueFactory<Member,String>("donations"));
        scoreCol.setCellValueFactory(
            new PropertyValueFactory<Member,String>("trophies"));
        
        memberTable.setItems(data);
        memberTable.getColumns().addAll(rankCol,nameCol,donationsCol,scoreCol);
        grid.add(memberTable,1,8);
        
        TableView statTable=new TableView();
        statTable.setEditable(false);
        TableColumn statNameCol=new TableColumn("Name");
        TableColumn sessionWinsCol=new TableColumn("Current Session Wins");
        TableColumn totalWinsCol=new TableColumn("Total Wins");
        TableColumn winLoseCol=new TableColumn("W:L");
        statNameCol.setSortable(false);
        sessionWinsCol.setSortable(false);
        totalWinsCol.setSortable(false);
        winLoseCol.setSortable(false);
        statNameCol.setResizable(false);
        sessionWinsCol.setResizable(false);
        totalWinsCol.setResizable(false);
        winLoseCol.setResizable(false);
        sessionWinsCol.setMinWidth(150);
        totalWinsCol.setMinWidth(150);
        winLoseCol.setMinWidth(150);
        statNameCol.setMinWidth(150);
        
        BorderPane borderPane=new BorderPane();
        borderPane.setCenter(statTable);
        statTable.getColumns().addAll(statNameCol,sessionWinsCol,totalWinsCol,winLoseCol);
        
        memberTab.setContent(grid);
        warTab.setContent(borderPane);
        
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
    
    public class MemberWarStat{
        
        private String name;
        private int currentSessionWins;
        private int totalBattles;
        private int totalWins;
        
        public void setName(String name){
            this.name=name;
        }
        
        public void setCurrentSessionWins(int sessionWins){
            this.currentSessionWins=sessionWins;
        }
        
        public void setTotalBattles(int totalBattles){
            this.totalBattles=totalBattles;
        }
        
        public void setTotalWins(int totalWins){
            this.totalWins=totalWins;
        }
        
        public String getName(){
            return this.name;
        }
        
        public int getCurrentSessionWins(){
            return this.currentSessionWins;
        }
        
        public int getTotalBattles(){
            return this.totalBattles;
        }
        
        public int getTotalWins(){
            return this.totalWins;
        }
    }
}

