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

   private String owner;
   private String description;
   private Date date;
   private Integer likes;
   private Location location;

}
