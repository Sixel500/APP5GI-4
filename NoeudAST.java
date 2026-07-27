package app6;

/** @author Ahmed Khoumsi */

/** Classe representant une feuille d'AST
 */
public class NoeudAST extends ElemAST {

  private ElemAST gauche;
  private ElemAST droite;
  private String operateur;

  public NoeudAST( String operateur, ElemAST gauche, ElemAST droite ) {
    this.operateur = operateur;
    this.gauche = gauche;
    this.droite = droite;
  }

 
  /** Evaluation de noeud d'AST
   */
  public int EvalAST( ) {
     if (this.operateur.equals("+")) {
       return this.gauche.EvalAST() + this.droite.EvalAST();
     }
     ErreurEvalAST("Opérateur non supporté : " + this.operateur);
     return 0;
  }


  /** Lecture de noeud d'AST
   */
  public String LectAST( ) {
     return "(" + this.gauche.LectAST() + " "  +  this.operateur + " " + this.droite.LectAST() + ")";
  }

}


