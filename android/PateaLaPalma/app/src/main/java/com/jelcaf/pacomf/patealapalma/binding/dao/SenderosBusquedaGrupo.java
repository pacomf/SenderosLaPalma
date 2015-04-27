package com.jelcaf.pacomf.patealapalma.binding.dao;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * @author Jorge Carballo
 *         25/04/15
 */
@Table(name = "SenderosBusquedaGrupos")
public class SenderosBusquedaGrupo extends Model {

   @Column(name = "resultSearch")
   public String resultSearch;

//   public String[] getListaSenderos() {
//      String[] lista = resultSearch.split(",");
//      return lista;
//   }
}
