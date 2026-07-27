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

    public int EvalAST() {
        Integer g = this.gauche.EvalAST();
        Integer d = this.droite.EvalAST();

        switch (this.operateur) {
                case "+": return g + d;
                case "-": return g - d;
                case "*": return g * d;
                case "/": return g / d;
                default:
                    ErreurEvalAST("Opérateur non supporté : " + this.operateur);
                    return 0;
            }
    }

    public Terminal.Type getType() {
        if (this.gauche.getType() == Terminal.Type.NOMBRE && this.droite.getType() == Terminal.Type.NOMBRE){
            return Terminal.Type.NOMBRE;
        }
        return Terminal.Type.OPERANDE;
    }

    public String toString(){
        return operateur;
    }
    /** Lecture de noeud d'AST
   */
  public String LectAST( ) {

     return "Infix: " + LectInfix() + "\n Prefix: " + LectPrefix() + "\n Postfix: " + LectPostfix();
  }

  public String LectInfix( ) {
      return "(" + this.gauche.LectInfix() + " " +  this.operateur + " " + this.droite.LectInfix() + ")";
  }
  public String LectPrefix(){
      return "(" +  this.operateur + " " + this.gauche.LectPrefix() + " " + this.droite.LectPrefix() + ")";
  }

  public String LectPostfix(){
      return "(" + this.gauche.LectPostfix() + " " + this.droite.LectPostfix() + " " + this.operateur + ")";
  }
}


