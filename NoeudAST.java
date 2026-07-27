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
  public String EvalAST( ) {
     switch (this.operateur) {
         case "+":
             if (this.getType() == Terminal.Type.NOMBRE){
                 return String.valueOf(this.gauche.IntEvalAST() + this.droite.IntEvalAST());
             }else{
                 return this.gauche.StringEvalAST() + "+" + this.droite.StringEvalAST();
             }
         case "-":
             if (this.getType() == Terminal.Type.NOMBRE){
                 return String.valueOf(this.gauche.IntEvalAST() - this.droite.IntEvalAST());
             }else{
                 return this.gauche.StringEvalAST() + "-" + this.droite.StringEvalAST();
             }
         case "*":
             if (this.getType() == Terminal.Type.NOMBRE){
                 return String.valueOf(this.gauche.IntEvalAST() * this.droite.IntEvalAST());
             }else{
                 return this.gauche.StringEvalAST() + "*" + this.droite.StringEvalAST();
             }
         case "/":
             if (this.getType() == Terminal.Type.NOMBRE){
                 return String.valueOf(this.gauche.IntEvalAST() / this.droite.IntEvalAST());
             }else{
                 return this.gauche.StringEvalAST() + "/" + this.droite.StringEvalAST();
             }
         default:
             ErreurEvalAST("Opérateur non supporté : " + this.operateur);
             return "er";
     }
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


