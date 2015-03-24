package com.jelcaf.pacomf.patealapalma.binding.dao;

import android.location.Location;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.jelcaf.pacomf.patealapalma.R;
import com.jelcaf.pacomf.patealapalma.binding.parser.IntegerParse;
import com.mobandme.android.bind.annotations.BindTo;

import java.util.List;

/**
 * @author Jorge Carballo
 *         26/02/15
 */
@Table(name = "Senderos")
public class Sendero extends Model {

   @Column(name = "server_id")
//   @BindTo(viewId = R.id.my_sendero_detail, parser = IntegerParse.class)
   private Integer serverId;

   @Column(name = "name")
   @BindTo(viewId = R.id.my_sendero_name)
   private String name;

   // Fields
   private String regularName;
   private Integer version;
   private Long length;
   private Integer type;
   private Integer difficulty;
   private Location geoStart;
   private Location geoEnd;

   private Float rating; // Global rating
   private Float ownRating;

   // Comments
   public List<Comment> comments() {
      return getMany(Comment.class, "senderoFK");
   }

   // Fotos
   public List<Photo> photos() {
      return getMany(Photo.class, "senderoFK");
   }


   public Integer getServerId () {
      return serverId;
   }

   public void setServerId (Integer serverId) {
      this.serverId = serverId;
   }

   public String getName() {
      return this.name;
   }

   public void setName (String name) {
      this.name = name;
   }

}
