/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;



public class datamodel {

    /**
     * @return the dishRequestID
     */
    public int getDishRequestID() {
        return dishRequestID;
    }

    /**
     * @param dishRequestID the dishRequestID to set
     */
    public void setDishRequestID(int dishRequestID) {
        this.dishRequestID = dishRequestID;
    }

    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the dishRequest
     */
    public String getDishRequest() {
        return dishRequest;
    }

    /**
     * @param dishRequest the dishRequest to set
     */
    public void setDishRequest(String dishRequest) {
        this.dishRequest = dishRequest;
    }


    /**
     * @return the dishRequest
     */
    
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
  
   public static byte[] convertImageIconToByteArray(Icon icon) {
    if (icon == null) {
        return null;
    }
    try {
        BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        icon.paintIcon(null, bufferedImage.getGraphics(), 0, 0);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", outputStream);
        return outputStream.toByteArray();
    } catch (IOException ex) {
        // Handle or log the exception
        ex.printStackTrace();
        return null;
    }
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
