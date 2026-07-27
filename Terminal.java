package app6;

/** @author Ahmed Khoumsi */

/** Cette classe identifie les terminaux reconnus et retournes par
 *  l'analyseur lexical
 */
public class Terminal {

  public enum Type {
    PLUS,
    MOINS,
    FOIS,
    DIVISE,
    PARANTHESE,
    FINPARANTHESE,
    OPERANDE,
    NOMBRE,
    OPERANDE,
    EOF
  }

  public Type type;
  public String chaine;


/** Un ou deux constructeurs (ou plus, si vous voulez)
  *   pour l'initalisation d'attributs 
 */	
  public Terminal(Type type, String chaine ) {   // arguments possibles
    this.type = type;
    this.chaine = chaine;
  }

}
