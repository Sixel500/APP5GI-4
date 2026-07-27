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


    @Override
    public int EvalAST() {
        if (getType() == Terminal.Type.NOMBRE) {
            return Integer.parseInt(this.terminal.chaine);
        }
        ErreurEvalAST("Évaluation impossible, puisqu'une variable est présente: " + this.terminal.chaine);
        return 0;
    }

    public Terminal.Type getType() {
      return terminal.type;
    }

    public String toString() {
        return this.terminal.chaine;
    }




 /** Lecture de chaine de caracteres correspondant a la feuille d'AST
  */
  public String LectAST( ) {
    return this.terminal.chaine;
  }

    public String LectInfix( ) {  return this.terminal.chaine;
    }
    public String LectPrefix(){  return this.terminal.chaine;
    }

    public String LectPostfix(){  return this.terminal.chaine;
    }
}
