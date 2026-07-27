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
        // Si tout ce sous-arbre est purement numérique, on calcule directement sa valeur entière
        if (this.getType() == Terminal.Type.NOMBRE) {
            return String.valueOf(this.IntEvalAST());
        }

        // Sinon, c'est un nœud mixte : on évalue d'abord chaque enfant séparément
        String evalG = this.gauche.EvalAST();
        String evalD = this.droite.EvalAST();

        // Si par hasard les deux enfants (après évaluation) sont devenus des nombres purs,
        // on peut tenter de faire le calcul localement
        if (estUnNombre(evalG) && estUnNombre(evalD)) {
            int g = Integer.parseInt(evalG);
            int d = Integer.parseInt(evalD);
            return String.valueOf(calculer(g, d));
        }

        // Sinon, on concatène la représentation symbolique
        return evalG + this.operateur + evalD;
    }

    @Override
    public int IntEvalAST() {
        return calculer(this.gauche.IntEvalAST(), this.droite.IntEvalAST());
    }

    /** Méthode utilitaire pour effectuer le calcul selon l'opérateur */
    private int calculer(int g, int d) {
        switch (this.operateur) {
            case "+": return g + d;
            case "-": return g - d;
            case "*": return g * d;
            case "/":
                if (d == 0) ErreurEvalAST("Division par zéro");
                return g / d;
            default:
                ErreurEvalAST("Opérateur non supporté : " + this.operateur);
                return 0;
        }
    }

    /** Vérifie si une chaîne représente un entier */
    private boolean estUnNombre(String s) {
        if (s == null || s.isEmpty()) return false;
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public Terminal.Type getType() {
        if (this.gauche.getType() == Terminal.Type.NOMBRE && this.droite.getType() == Terminal.Type.NOMBRE) {
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


