package com.jelcaf.pacomf.patealapalma.binding.dao;

import android.location.Location;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.sql.Date;

/**
 * @author Jorge Carballo
 *         24/03/15
 */
@Table(name = "Comment")
public class Comment extends Model {

   @Column(name = "senderoFK")
   private Sendero sendero;

   public Comment(){
      super();
   }

   public Comment(Sendero sendero, String owner, String nameowner, String description, java.util.Date date, Integer likes, Location location) {
      this.sendero = sendero;
      this.owner = owner;
      this.nameowner = nameowner;
      this.description = description;
      this.date = new java.sql.Date(date.getTime());
      this.likes = likes;
      //this.location = location;
   }

   public String getOwner() {
      return owner;
   }

   public String getDescription() {
      return description;
   }

   public Date getDate() {
      return date;
   }

   public Integer getLikes() {
      return likes;
   }

   public String getNameowner() {
      return nameowner;
   }

   @Column(name = "owner")
   private String owner;

   @Column(name = "description")
   private String description;

   @Column(name = "date")
   private Date date;

   @Column(name = "likes")
   private Integer likes;

   //@Column(name = "location")
   //private Location location;

   @Column(name = "nameowner")
   private String nameowner;

}
