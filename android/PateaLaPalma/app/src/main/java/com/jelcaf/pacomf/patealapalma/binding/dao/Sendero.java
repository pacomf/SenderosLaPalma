package com.jelcaf.pacomf.patealapalma.binding.dao;

import android.location.Location;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.jelcaf.pacomf.patealapalma.R;
import com.mobandme.android.bind.annotations.BindTo;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jorge Carballo
 *         26/02/15
 */
@Table(name = "Senderos")
public class Sendero extends Model {

   @Column(name = "server_id")
//   @BindTo(viewId = R.id.my_sendero_detail, parser = IntegerParse.class)
   private String serverId;

   @Column(name = "name")
   @BindTo(viewId = R.id.my_sendero_name)
   private String name;

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

   public Location getGeoStart() {
      return geoStart;
   }

   public void setGeoStart(Location geoStart) {
      this.geoStart = geoStart;
   }

   public Location getGeoEnd() {
      return geoEnd;
   }

   public void setGeoEnd(Location geoEnd) {
      this.geoEnd = geoEnd;
   }

   // Fields
   private String regularName;
   private String version;
   private Double length;
   private String type;
   private String difficulty;
   private Location geoStart;
   private Location geoEnd;

   public java.util.Date getDateUpdated() {
      return new java.util.Date(this.dateUpdated.getTime());
   }

   public void setDateUpdated(java.util.Date dateU) {
      this.dateUpdated = new java.sql.Date(dateU.getTime());
   }

   private Date dateUpdated;

   public Sendero() {
      this.user_rating  = new HashMap<>();
   }

   // Coordinates
   public List<Geo> coordinates() {
      return getMany(Geo.class, "senderoFK");
   }

   // Water Points
   public List<Geo> waterPoints() {
      return getMany(Geo.class, "senderoFK");
   }

   public Double getRating() {
      return rating;
   }

   public void setRating(Double rating) {
      this.rating = rating;
   }


   private Double rating; // Global rating
   public Map<String, Integer> user_rating; // User_Rating

   // Comments
   public List<Comment> comments() {
      return getMany(Comment.class, "senderoFK");
   }

   // Fotos
   public List<Photo> photos() {
      return getMany(Photo.class, "senderoFK");
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

}
