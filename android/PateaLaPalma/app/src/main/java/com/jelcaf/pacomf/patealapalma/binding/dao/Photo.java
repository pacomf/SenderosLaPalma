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
@Table(name = "Photo")
public class Photo extends Model {

   @Column(name = "senderoFK")
   private Sendero sendero;

   @Column(name = "url")
   private String url;

   @Column(name = "date")
   private Date date;

   @Column(name = "owner")
   private String owner;

   @Column(name = "likes")
   private Integer likes;

   //@Column(name = "location")
   //private Location location;

   public Photo() {
      super();
   }

   public Photo(Sendero sendero, String url, String owner, java.util.Date date, Integer likes, Location location) {
      this.sendero = sendero;
      this.url = url;
      this.owner = owner;
      this.likes = likes;
      this.date = new java.sql.Date(date.getTime());
      //this.location = location;
   }

   public String getUrl() {
      return this.url;
   }
}
