package app6;

/** @author Ahmed Khoumsi */

/** Classe representant un noeud d'AST
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
     switch (this.operateur) {
         case "+":
             return this.gauche.EvalAST() + this.droite.EvalAST();
         case "-":
             return this.gauche.EvalAST() - this.droite.EvalAST();
         case "*":
             return this.gauche.EvalAST() * this.droite.EvalAST();
         case "/":
             return this.gauche.EvalAST() / this.droite.EvalAST();
         default:
             ErreurEvalAST("Opérateur non supporté : " + this.operateur);
             return 0;
     }
  }


  /** Lecture de noeud d'AST
   */
  public String LectAST( ) {
     return "(" + this.gauche.LectAST() + " "  +  this.operateur + " " + this.droite.LectAST() + ")";
  }

}


