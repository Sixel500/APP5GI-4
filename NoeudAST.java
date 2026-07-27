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

    public String EvalAST() {
        Integer g = null;
        Integer d = null;
        if (this.gauche.getType() == Terminal.Type.NOMBRE){
            g = this.gauche.IntEvalAST();
        }
        if (this.droite.getType() == Terminal.Type.NOMBRE){
            d = this.droite.IntEvalAST();
        }

// 1. Calcul mathématique complet si les deux côtés sont des nombres
        if (g != null && d != null) {
            switch (this.operateur) {
                case "+": return String.valueOf(g + d);
                case "-": return String.valueOf(g - d);
                case "*": return String.valueOf(g * d);
                case "/": return String.valueOf(g / d);
                default:
                    ErreurEvalAST("Opérateur non supporté : " + this.operateur);
                    return "0";
            }
        }
        return "(" + this.gauche.EvalAST() + " " + this.operateur + " " + this.droite.EvalAST() + ")";
    }

    @Override
    public int IntEvalAST() {
        switch (this.operateur) {
            case "+":
                return this.gauche.IntEvalAST() + this.droite.IntEvalAST();
            case "-":
                return this.gauche.IntEvalAST() - this.droite.IntEvalAST();
            case "*":
                return this.gauche.IntEvalAST() * this.droite.IntEvalAST();
            case "/":
                    return this.gauche.IntEvalAST() / this.droite.IntEvalAST();
            default:
                ErreurEvalAST("Opérateur non supporté : " + this.operateur);
                return 0;
        }
    }

    @Override
    public Terminal.Type getType() {
        if (this.gauche.getType() == Terminal.Type.NOMBRE && this.droite.getType() == Terminal.Type.NOMBRE){
            return Terminal.Type.NOMBRE;
        }
        return Terminal.Type.OPERANDE;
    }

    @Override
    public String StringEvalAST() {
        return this.gauche.StringEvalAST() + this.operateur + this.droite.StringEvalAST();
    }


    /** Lecture de noeud d'AST
   */
  public String LectAST( ) {
     return "(" + this.gauche.LectAST() + " "  +  this.operateur + " " + this.droite.LectAST() + ")";
  }

}


