package app6;

/** @author Ahmed Khoumsi */

/** Classe representant une feuille d'AST
 */
public class FeuilleAST extends ElemAST {

  // Attribut(s)
    private Terminal terminal;

/**Constructeur pour l'initialisation d'attribut(s)
 */
  public FeuilleAST(Terminal term ) {  // avec arguments
    this.terminal = term;
  }


      /** Evaluation de feuille d'AST
       */
    public String StringEvalAST( ) {
          return this.terminal.chaine;
    }

    public int IntEvalAST( ) {
        if (getType() == Terminal.Type.NOMBRE) {
            return Integer.parseInt(this.terminal.chaine);
        }
        return 0;
    }

    @Override
    public String EvalAST() {
        return this.terminal.chaine;
    }

    public Terminal.Type getType() {
      return terminal.type;
    }





 /** Lecture de chaine de caracteres correspondant a la feuille d'AST
  */
  public String LectAST( ) {
    return this.terminal.chaine;
  }

}
