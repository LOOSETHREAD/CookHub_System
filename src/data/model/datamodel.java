
package data.model;

import javax.swing.Icon;



public class datamodel {

    public int getDishRequestID() {
        return dishRequestID;
    }
    public void setDishRequestID(int dishRequestID) {
        this.dishRequestID = dishRequestID;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getDishRequest() {
        return dishRequest;
    }
    public void setDishRequest(String dishRequest) {
        this.dishRequest = dishRequest;
    }
    public Icon getDishCover() {
        return dishCover;
    }

    
    public void setDishCover(Icon dishCover) {
        this.dishCover = dishCover;
    }

    public String getDishType() {
        return dishType;
    }

   
    public void setDishType(String dishType) {
        this.dishType = dishType;
    }

    
    public String getDishLevel() {
        return dishLevel;
    }

   
    public void setDishLevel(String dishLevel) {
        this.dishLevel = dishLevel;
    }

    
    public String getDishCost() {
        return dishCost;
    }

  
    public void setDishCost(String dishCost) {
        this.dishCost = dishCost;
    }

    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }

    
    public String getDishDescription() {
        return dishDescription;
    }

    
    public void setDishDescription(String dishDescription) {
        this.dishDescription = dishDescription;
    }

    
    public String getDishIngredients() {
        return dishIngredients;
    }

   
    public void setDishIngredients(String dishIngredients) {
        this.dishIngredients = dishIngredients;
    }
    public String getDishProcedures() {
        return dishProcedures;
    }

    public void setDishProcedures(String dishProcedures) {
        this.dishProcedures = dishProcedures;
    }
    
    public datamodel(String name, String dishType, String dishLevel, String dishDescription, String dishIngredients, String dishProcedures, String dishCost, Icon dishCover) {
        this.name = name;
        this.dishDescription = dishDescription;
        this.dishIngredients = dishIngredients;
        this.dishProcedures = dishProcedures;
        this.dishCost = dishCost;
        this.dishLevel = dishLevel;
        this.dishType = dishType;
        this.dishCover = dishCover;
    }

    public datamodel() {
    }
    
     
    private String userName;
    private String name;
    private String dishDescription;
    private String dishIngredients;
    private String dishProcedures;
    private String dishCost;
    private String dishType;
    private String dishLevel;
    private String dishRequest;
    private Icon dishCover;
    private int dishRequestID;
    
}
