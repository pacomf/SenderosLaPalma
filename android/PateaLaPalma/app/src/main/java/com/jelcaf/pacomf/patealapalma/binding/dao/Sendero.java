package com.jelcaf.pacomf.patealapalma.binding.dao;

import android.location.Location;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.jelcaf.pacomf.patealapalma.R;
import com.jelcaf.pacomf.patealapalma.binding.MyCustomViewBinder;
import com.mobandme.android.bind.annotations.BindTo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jorge Carballo
 *         26/02/15
 */
@Table(name = "Senderos")
public class Sendero extends Model {

   @Column(name = "server_id")
   private String serverId;

   @Column(name = "name")
   @BindTo(viewId = R.id.my_sendero_name)
   private String name;

   // Fields
   @Column(name = "regular_name")
   private String regularName;

   @Column(name = "version")
   private String version;

   @Column(name = "length")
   @BindTo(viewId = R.id.sendero_distance_view, binder = MyCustomViewBinder.class)
   private Double length;

   @Column(name = "type")
   @BindTo(viewId = R.id.sendero_tipo, binder = MyCustomViewBinder.class)
   private String type;

   @Column(name = "difficulty")
   @BindTo(viewId = R.id.sendero_difficulty_view, binder = MyCustomViewBinder.class)
   private String difficulty;

   @Column(name = "rating")
   private Double rating; // Global rating

   @Column(name = "date_uptades")
   private Date dateUpdated;

   @Column(name = "user_rating")
   private Integer userRating; // User_Rating

   public Sendero() {
      userRating = 0;
   }

   public String getRegularName() {
      return regularName;
   }

   public void setRegularName(String regularName) {
      this.regularName = regularName;
   }

   public String getVersion() {
      return version;
   }

   public void setVersion(String version) {
      this.version = version;
   }

   public Double getLength() {
      return length;
   }

   public void setLength(Double length) {
      this.length = length;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getDifficulty() {
      return difficulty;
   }

   public void setDifficulty(String difficulty) {
      this.difficulty = difficulty;
   }

   public java.util.Date getDateUpdated() {
      return new java.util.Date(this.dateUpdated.getTime());
   }

   public void setDateUpdated(java.util.Date dateU) {
      this.dateUpdated = new java.sql.Date(dateU.getTime());
   }
   // Coordinates
   public List<Geo> coordinates() {
      List<Geo> geos = getMany(Geo.class, "senderoFK");
      List<Geo> ret = new ArrayList<>();
      for (Geo geo: geos){
         if (geo.getType().equals("coordinate")){
            ret.add(geo);
         }
      }
      return ret;
   }

   // Water Points
   public List<Geo> waterPoints() {
      List<Geo> geos = getMany(Geo.class, "senderoFK");
      List<Geo> ret = new ArrayList<>();
      for (Geo geo: geos){
         if (geo.getType().equals("waterpoint")){
            ret.add(geo);
         }
      }
      return ret;
   }

   public Double getRating() {
      return rating;
   }

   public void setRating(Double rating) {
      this.rating = rating;
   }

   public Integer getUserRating() {
      return userRating;
   }

   public void setUserRating(Integer userRating) {
      this.userRating = userRating;
   }

   // Comments
   public List<Comment> comments() {
      return getMany(Comment.class, "senderoFK");
   }

   // Fotos
   public List<Photo> photos() {
      return getMany(Photo.class, "senderoFK");
   }

   public Geo geoStart() {
      Geo geo = getMany(Geo.class, "senderoFK").get(0);
      return geo;
   }

   public Geo geoEnd() {
      List<Geo> geos = getMany(Geo.class, "senderoFK");
      Geo geo = geos.get(geos.size() - 1);
      return geo;
   }

   public String getServerId () {
      return serverId;
   }

   public void setServerId (String serverId) {
      this.serverId = serverId;
   }

   public String getName() {
      return this.name;
   }

   public void setName (String name) {
      this.name = name;
   }

   public static Sendero getByIdServer(String idserver) {
      // This is how you execute a query
      return new Select()
              .from(Sendero.class)
              .where("server_id = ?", idserver)
              .executeSingle();
   }

   public static boolean isDBEmpty (){
      List<Sendero> senderos = new Select()
              .from(Sendero.class)
              .execute();

      if (senderos.size() != 0) {
         return false;
      }
      return true;
   }

}
