/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clashroyale_clanwar;
import java.util.HashMap;

/**
 *
 * @author Kevin Lin
 */
public class ClanInformation {
    
    private String tag;
    private String name;
    private String description;
    private String type;
    private String score;
    private String memberCount;
    private String requiredScore;
    private String donations;
    private HashMap<String,String> location;
    private Member members[];
    
    public Member[] getMembers(){
        return this.members;
    }
    
    public String getDescription(){
        return this.description;
    }
    
    public String getTag(){
        return this.tag;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getType(){
        return this.type;
    }
    
    public String getScore(){
        return this.score;
    }
    
    public String getMemberCount(){
        return this.memberCount;
    }
    
    public String getRequiredScore(){
        return this.requiredScore;
    }
    
    public String getDonations(){
        return this.donations;
    }
    
    public HashMap<String,String> getLocation(){
        return this.location;
    }
    
    public class Member{
        private String name;
        private String rank;
        private String role;
        private String expLevel;
        private String trophies;
        private String donations;
        private String tag;
        private HashMap<String,String> arena;
        
        public String getName(){
            return this.name;
        }
        
        public String getRank(){
            return this.rank;
        }
        
        public String getRole(){
            return this.role;
        }
        
        public String getExpLevel(){
            return this.expLevel;
        }
        
        public String getTrophies(){
            return this.trophies;
        }
        
        public String getDonations(){
            return this.donations;
        }
        
        public String getTag(){
            return this.tag;
        }
        
        public HashMap<String,String> getArea()
        {
            return this.arena;
        }
    }
    
                
                
    
}
